package com.warriorwebpros.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;

public class ButtonControlView implements IRoundKeeperView{

	private Button btn_delayTurn;
	private ActorDataService dataService;
	
	@Override
	public void initUI(Shell shell) {
        Composite composite = new Composite(shell,SWT.FILL);
        GridLayout compositeGL = new GridLayout(2, true);
        composite.setLayout(compositeGL);

        GridData gridDataLeft = new GridData();
        GridData gridDataRight = new GridData();
        gridDataLeft.widthHint = 110;
        gridDataRight.widthHint = 110;

        //Button Row
        btn_delayTurn = new Button(composite, SWT.PUSH);
        btn_delayTurn.setText("Delay Turn");
        
        btn_delayTurn.setLayoutData(gridDataLeft);
        addListeners();
	}
	
	private void addListeners(){
		btn_delayTurn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dataService.delayActorsTurn(dataService.getSelectedActor());
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
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
}
