package com.warriorwebpros.views.actor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.warriorwebpros.colors.RoundKeeperColorConstants;
import com.warriorwebpros.listeners.DigitVerificationListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.views.IRoundKeeperView;

public class ActorEntryView implements IRoundKeeperView {
	private ActorDataService dataService;

	//TODO determine how to identify the widgets as data input widgets.
	private final List<Widget> managedWidgets = new ArrayList<>();

	//TODO: Make this injectable
	public ActorEntryView(ActorDataService dataService) {
		this.dataService = dataService;
	}


	@Override
	public void initUI(Shell shell) {
        //Composite composite = new Composite(shell,SWT.FILL);
        //composite.setBounds(0, 0, 400, 200);
        Group group = new Group(shell,SWT.FILL);
        group.setBackground(
        		RoundKeeperColorConstants.GROUP_BACKGROUND.getColor(shell.getDisplay()));
        group.setForeground(
        		RoundKeeperColorConstants.LABEL_TEXT.getColor(shell.getDisplay()));
        GridLayout compositeGL = new GridLayout(2, false);
        group.setLayout(compositeGL);
        group.setText("Add Actor");

        GridData gridDataLeft = new GridData();
        GridData gridDataRight = new GridData(SWT.FILL, SWT.FILL, false, false);
        gridDataRight.widthHint=220;


        
        //Name Row
		Label lbl_Name = new Label(group, SWT.SINGLE);
        lbl_Name.setText("Name:");
		lbl_Name.setLayoutData(gridDataLeft);
		managedWidgets.add(lbl_Name);

		Text txt_Name = new Text(group, SWT.SINGLE);
        txt_Name.setLayoutData(gridDataRight);
		managedWidgets.add(txt_Name);
        
        //Initiative Row
		Label lbl_Initiative = new Label(group, SWT.SINGLE);
        lbl_Initiative.setText("Initiative:");
        lbl_Initiative.setLayoutData(gridDataLeft);
		managedWidgets.add(lbl_Initiative);

		Text txt_Initiative = new Text(group, SWT.SINGLE);
        txt_Initiative.setLayoutData(gridDataRight);
		//TODO:  use guice to provide a digit verifier
        txt_Initiative.addVerifyListener(new DigitVerificationListener());
		managedWidgets.add(txt_Initiative);
        
        //HitPoints Row
		Label lbl_HitPoints = new Label(group, SWT.SINGLE);
        lbl_HitPoints.setText("Hit Points:");
        lbl_HitPoints.setLayoutData(gridDataLeft);
		managedWidgets.add(lbl_HitPoints);

		Text txt_HitPoints = new Text(group, SWT.SINGLE);
        txt_HitPoints.setLayoutData(gridDataRight);
		//TODO:  use guice to get a fresh dependency
        txt_HitPoints.addVerifyListener(new DigitVerificationListener());
		managedWidgets.add(txt_HitPoints);

        //Button Row
		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd.horizontalSpan = 1;
		AddActorButton addButton = new AddActorButton(group, gd);
		managedWidgets.add(addButton);

		group.addListener(AddActorButton.SELECT_ADD_ACTOR_EVENT_TYPE, event -> {
			dataService.addActor(
					buildActor(
							txt_Name.getText(),
							txt_Initiative.getText(),
							txt_HitPoints.getText()));
			resetInputs();
		});
	}

	/**
	 * Iterates through the widgets in the view and
	 * Uses reflection to:
	 * <ul>
	 *     <li>Clear the text inputs of Text widgets</li>
	 * </ul>
	 */
	private void resetInputs() {
		for (Widget widget : managedWidgets) {
			if(!widget.isDisposed() && widget instanceof Text) {
				((Text)widget).setText("");
			}
		}
	}
	/**
	 * SWT doesn't automatically garbage collect its UI components.
	 * Calling dispose on each one will  
	 */
	@Override
	public void cleanUpUI() {
		for (Widget widget : managedWidgets) {
			widget.dispose();
		}
	}
	
	private Actor buildActor(String nameStr, String initiativeStr, String hitPointsStr){
		initiativeStr = initiativeStr.isBlank() ? "0" : initiativeStr;
		hitPointsStr = hitPointsStr.isBlank() ? "0" : hitPointsStr;
		return new Actor(nameStr, Integer.parseInt(initiativeStr), Integer.parseInt(hitPointsStr));
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
}
