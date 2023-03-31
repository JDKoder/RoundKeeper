package com.warriorwebpros.controller;

import static com.warriorwebpros.binders.ViewModule.*;
import java.util.ArrayList;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.views.ActorTableView;
import com.warriorwebpros.views.ButtonControlView;
import com.warriorwebpros.views.actor.ActorEntryView;

public class ViewController {
	private Shell shell;
	private ActorDataService dataService;
	private ActorEntryView entry;
	private ActorTableView table;
	private ButtonControlView buttons;
	private GridLayout gl;
	
	@Inject
	public ViewController(@MainShell Shell shell, ActorEntryView entry, ActorTableView table, ButtonControlView bcView, ActorDataService dataService){
		this.shell = shell;
		this.entry = entry;
		this.table = table;
		this.buttons = bcView;
		this.dataService = dataService;
	}

	public void runProgramLoop(){
		while (!shell.isDisposed()) {
			Display display = shell.getDisplay();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}
	public void initializeDataEntryView(){
		//TODO: Inject these views
		entry.initUI(shell);
		dataService.addListener(table);
		table.initUI(shell);
		buttons.initUI(shell);

		//Add Event Listeners
		shell.addListener(ActorEntryView.ACTOR_CREATED_EVENT_TYPE, event -> dataService.addActor(((Actor)event.data)));
		shell.addListener(ButtonControlView.ACTOR_REMOVAL_REQUEST_EVENT_TYPE, event -> dataService.removeActor());
		shell.addListener(ButtonControlView.ACTOR_DELAY_REQUEST_EVENT_TYPE, event -> dataService.delayActorsTurn());

		//Display the shell
		shell.pack();
		shell.open();
	}
	
	public void dispose(){
		//TODO: Call dispose on all dependent views
		shell.dispose();
		shell.getDisplay().dispose();
	}

}
