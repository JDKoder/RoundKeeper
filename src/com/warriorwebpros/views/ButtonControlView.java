package com.warriorwebpros.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.warriorwebpros.colors.RoundKeeperColorConstants;
import com.warriorwebpros.views.widgets.DelayActorTurnButton;
import com.warriorwebpros.views.widgets.RemoveActorButton;

import javax.inject.Inject;

public class ButtonControlView extends AbstractView{

	public static final int ACTOR_REMOVAL_REQUEST_EVENT_TYPE = 104;
	public static final int ACTOR_DELAY_REQUEST_EVENT_TYPE = 106;
	@Inject
	public ButtonControlView() {}

	@Override
	public void initUI(Shell shell) {
        Group group = new Group(shell,SWT.FILL);
        group.setText("Table Controls");
        GridLayout groupGL = new GridLayout(2, true);
        group.setLayout(groupGL);
        
        group.setBackground(
        		RoundKeeperColorConstants.DARK_BACKGROUND.getColor(shell.getDisplay()));
		managedWidgets.add(group);

        //Delay Turn
        GridData gridDataLeft = new GridData();
        gridDataLeft.widthHint = 155;
        Button btn_delayTurn = new DelayActorTurnButton(group, gridDataLeft);
		group.addListener(DelayActorTurnButton.SELECT_DELAY_TURN_EVENT_TYPE, event -> {
			shell.notifyListeners(ACTOR_DELAY_REQUEST_EVENT_TYPE, event);
		});
		managedWidgets.add(btn_delayTurn);

		//Remove Actor
        GridData gridDataRight = new GridData();
        gridDataRight.widthHint = 155;
        Button btn_remove = new RemoveActorButton(group, gridDataRight);
		group.addListener(RemoveActorButton.SELECT_REMOVE_ACTOR_EVENT_TYPE, event -> {
			shell.notifyListeners(ACTOR_REMOVAL_REQUEST_EVENT_TYPE, event);
		});
		managedWidgets.add(btn_remove);
	}


}
