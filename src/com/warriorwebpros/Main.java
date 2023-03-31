package com.warriorwebpros;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.warriorwebpros.binders.ActorDataModule;
import com.warriorwebpros.binders.VerificationListenerModule;
import com.warriorwebpros.binders.ViewModule;
import com.warriorwebpros.controller.ViewController;
import com.warriorwebpros.service.ActorDataService;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ActorDataModule(),
												 new VerificationListenerModule(),
												 new ViewModule());
        ViewController viewController = injector.getInstance(ViewController.class);
        viewController.initializeDataEntryView();
		viewController.runProgramLoop();
	}
  }