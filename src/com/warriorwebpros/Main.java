package com.warriorwebpros;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.warriorwebpros.binders.ActorDataModule;
import com.warriorwebpros.binders.VerificationListenerModule;
import com.warriorwebpros.binders.ViewModule;
import com.warriorwebpros.controller.MainViewController;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ActorDataModule(),
												 new VerificationListenerModule(),
												 new ViewModule());
        MainViewController mainViewController = injector.getInstance(MainViewController.class);
        mainViewController.initializeDataEntryView();
		mainViewController.runProgramLoop();
	}
  }