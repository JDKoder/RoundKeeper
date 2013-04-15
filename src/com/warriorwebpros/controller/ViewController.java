package com.warriorwebpros.controller;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.views.ActorEntryView;
import com.warriorwebpros.views.ActorTableView;

public class ViewController {
	private Shell shell;
	private ActorDataService dataService;
	private ActorEntryView entry;
	private ActorTableView table;
	
	public ViewController(Display display, Shell shell){
		this.shell = shell;
		shell.setText("Round Keeper");
		shell.setSize(400,200);
		center(shell);
	}
	
	public void initializeDataEntryView(){
		//TODO: Inject these views
		dataService.setListeners(new ArrayList<ActorListChangedListener>());
		entry = new ActorEntryView();
		entry.setDataService(dataService);
		entry.initUI(shell);
		table = new ActorTableView();
		table.setDataService(dataService);
		dataService.addListener(table);
		table.initUI(shell);
		shell.pack();
		shell.open();
	}
	
	public void dispose(){
		//call dispose() all initialized views.
	}
	/**
	 * Centers the shell within its own display.
	 * @param shell
	 */
	private void center(Shell shell){
		Rectangle bds = shell.getMonitor().getBounds();
		Point p = shell.getSize();
		int nLeft = (bds.width - p.x) / 2;
		System.out.println("bds.width: " + bds.width);
		System.out.println("p.x: " + p.x);
		System.out.println("nLeft: " + nLeft);
        int nTop = (bds.height - p.y) / 2;
        System.out.println("bds.height: " + bds.height);
        System.out.println("p.y: " + p.y);
        System.out.println("nTop: " + nTop);
        
        Rectangle r= new Rectangle(nLeft, nTop, p.x, p.y);
        
        shell.setBounds(r);
	}

	public void setTable(ActorTableView table) {
		this.table = table;
	}

	public void setEntry(ActorEntryView entry) {
		this.entry = entry;
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
}