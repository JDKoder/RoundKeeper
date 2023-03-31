package com.warriorwebpros.views;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.google.inject.Inject;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;


public class ActorTableView extends AbstractView {

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
	    
	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }
		managedWidgets.add(table);
	}


	public int getSelectedIndex() {
		return table.getSelectionIndex();
	}
	
	public void handleActorListChanged(List<Actor> list) {
		setTableItems(list);
	}

	private void setTableItems(List<Actor> actors){
		table.removeAll();
		for (Actor actor : actors) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText (0, actor.getName());
			item.setText (1, String.valueOf(actor.getInitiative()));
			item.setText (2, String.valueOf(actor.getOrder()));
			item.setText (3, String.valueOf(actor.getHitpoints()));
		}

	}

}
