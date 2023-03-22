package com.warriorwebpros.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.warriorwebpros.colors.RoundKeeperColorConstants;
import com.warriorwebpros.service.ActorDataService;

public class ButtonControlView implements IRoundKeeperView{

	private Button btn_delayTurn;
	private Button btn_remove;
	private ActorDataService dataService;
	
	@Override
	public void initUI(Shell shell) {
        Group composite = new Group(shell,SWT.FILL);
        composite.setText("Table Controls");
        GridLayout groupGL = new GridLayout(2, true);
        composite.setLayout(groupGL);
        
        composite.setBackground(
        		RoundKeeperColorConstants.GROUP_BACKGROUND.getColor(shell.getDisplay()));

        GridData gridDataLeft = new GridData();
        GridData gridDataRight = new GridData();
        gridDataLeft.widthHint = 155;
        gridDataRight.widthHint = 155;

        //Delay Turn
        btn_delayTurn = new Button(composite, SWT.PUSH);
        btn_delayTurn.setText("Delay Turn");
        btn_delayTurn.setLayoutData(gridDataLeft);
        //Remove
        btn_remove = new Button(composite, SWT.PUSH);
        btn_remove.setText("Remove Actor");
        btn_remove.setLayoutData(gridDataRight);
        
        addListeners();
	}
	
	private void addListeners(){
		btn_delayTurn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dataService.delayActorsTurn();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);				
			}
		});
		
		btn_remove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dataService.removeActor();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);				
			}
		});
	}

	
	/**
	 * SWT doesn't automatically garbage collect its UI components.
	 * Calling dispose on each one will  
	 */
	@Override
	public void cleanUpUI() {
		btn_delayTurn.dispose();
		btn_remove.dispose();
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
}