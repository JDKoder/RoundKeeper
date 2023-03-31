package com.warriorwebpros.views;

import static com.warriorwebpros.binders.ViewModule.*;
import java.util.EventObject;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.warriorwebpros.binders.ViewModule;
import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;


public class ActorTableView extends AbstractView implements ActorListChangedListener{

	ActorDataService dataService;
	Table table;

	@Inject
	public ActorTableView(ActorDataService dataService) {
		this.dataService = dataService;
	}

	@Override
	public void initUI(Shell shell) {
		table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
	    table.setHeaderVisible(true);

	    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
	    data.horizontalSpan=2;
	    data.heightHint = 200;
	    table.setLayoutData(data);

	    String[] titles = { "Name", "Initiative", "Order", "Hit Points", "Status" };
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	      table.getColumn(i).pack();
	    }
	    
	    setTableItems();
	    
	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }
		managedWidgets.add(table);
	    addTableSelectionListener();
	}
	
	private void setTableItems(){
		table.removeAll();
		for (Actor actor : dataService.getMasterList()){
	      TableItem item = new TableItem(table, SWT.NONE);
	      item.setText (0, actor.getName());
	      item.setText (1, String.valueOf(actor.getInitiative()));
	      item.setText (2, String.valueOf(actor.getOrder()));
	      item.setText (3, String.valueOf(actor.getHitpoints()));
	    }
		
	}
	
	private void addTableSelectionListener(){
	    table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int itemIndex = table.getSelectionIndex();
				if(itemIndex > -1){
					//System.out.println(itemIndex);
					dataService.setSelectedActor(dataService.getMasterList().get(itemIndex));
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
				
			}
		});
    }
	
	@Override
	public void handleActorListChanged(EventObject event, List<Actor> list) {
		setTableItems();
	}

}
