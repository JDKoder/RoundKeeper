package com.warriorwebpros;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.warriorwebpros.binders.ActorDataModule;
import com.warriorwebpros.controller.ViewController;
import com.warriorwebpros.service.ActorDataService;

public class Main {
 
	ViewController viewController;
	Shell shell;
	Display display;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.display = new Display();
		main.shell = new Shell(main.display);
        main.viewController = new ViewController(main.display, main.shell);
		Injector injector = Guice.createInjector(new ActorDataModule());
		ActorDataService dataService = injector.getInstance(ActorDataService.class);
        main.viewController.setDataService(dataService);
        main.viewController.initializeDataEntryView();
        main.runProgramLoop();
        main.viewController.dispose();
        main.display.dispose();
	}
	
	private void runProgramLoop(){
		while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	
	public void setShell(Shell shell){
		this.shell = shell;
	}
	
	public void setDisplay(Display display){
		this.display = display;
	}
  }