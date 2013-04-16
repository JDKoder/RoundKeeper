package com.warriorwebpros.views;

import java.util.EventObject;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;

public class ActorTableView implements IRoundKeeperView, ActorListChangedListener{

	ActorDataService dataService;
	Table table;
	
	@Override
	public void initUI(Shell shell) {
		shell.setLayout(new GridLayout());
		table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
	    table.setLinesVisible(true);
	    table.setHeaderVisible(true);
	    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
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
	
	/**
	 * SWT doesn't automatically garbage collect its UI components.
	 * Calling dispose on each one will  
	 */
	@Override
	public void cleanUpUI() {
	}
	
	@Override
	public void handleActorListChanged(EventObject event, List<Actor> list) {
		setTableItems();
	}
	
	public void setDataService(ActorDataService service){
		dataService = service;
	}

}
