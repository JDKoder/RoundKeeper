package com.warriorwebpros.controller;

import static com.warriorwebpros.binders.ViewModule.MainShell;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.views.ActorEntryView;
import com.warriorwebpros.views.ActorTableView;
import com.warriorwebpros.views.ButtonControlView;

public class MainViewController {
	private final Shell shell;
	private final ActorDataService dataService;
	private final ActorEntryView entry;
	private final ActorTableView table;
	private final ButtonControlView buttons;
	
	@Inject
	public MainViewController(@MainShell Shell shell, ActorEntryView entry, ActorTableView table, ButtonControlView bcView, ActorDataService dataService){
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
		//layout is determined by order
		entry.initUI(shell);
		buttons.initUI(shell);
		table.initUI(shell);

		//Add Event Listeners
		shell.addListener(ActorEntryView.ACTOR_CREATED_EVENT_TYPE, event -> {
			table.handleActorListChanged(dataService.addActorAt(((Actor)event.data)));
		});
		shell.addListener(ButtonControlView.ACTOR_REMOVAL_REQUEST_EVENT_TYPE, event -> {
			table.handleActorListChanged(dataService.removeActorAt(table.getSelectedIndex()));
		});
		shell.addListener(ButtonControlView.ACTOR_DELAY_REQUEST_EVENT_TYPE, event -> {
			table.handleActorListChanged(dataService.delayActorAt(table.getSelectedIndex()));
		});

		//Display the shell
		shell.pack();
		shell.open();
	}
	
	public void dispose(){
		shell.dispose();
		entry.cleanUpUI();
		buttons.cleanUpUI();
		table.cleanUpUI();
		shell.getDisplay().dispose();
	}

}
