package com.warriorwebpros.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.warriorwebpros.colors.RoundKeeperColorConstants;
import com.warriorwebpros.listeners.DigitVerificationListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;

public class ActorEntryView implements IRoundKeeperView{

	private Label lbl_Name;
	private Text txt_Name;
	private Label lbl_Initiative;
	private Text txt_Initiative;
	private Label lbl_HitPoints;
	private Text txt_HitPoints;
	private Button btn;
	private ActorDataService dataService;
	private DigitVerificationListener digitVerifier;
	
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
        lbl_Name = new Label(group, SWT.SINGLE);
        lbl_Name.setText("Name:");
        lbl_Name.setLayoutData(gridDataLeft);
        
        
        txt_Name = new Text(group, SWT.SINGLE);
        txt_Name.setLayoutData(gridDataRight);
        
        //Initiative Row
        lbl_Initiative = new Label(group, SWT.SINGLE);
        lbl_Initiative.setText("Initiative:");
        lbl_Initiative.setLayoutData(gridDataLeft);
        
        txt_Initiative = new Text(group, SWT.SINGLE);
        txt_Initiative.setLayoutData(gridDataRight);
        txt_Initiative.addVerifyListener(digitVerifier);
        
        //HitPoints Row
        lbl_HitPoints = new Label(group, SWT.SINGLE);
        lbl_HitPoints.setText("Hit Points:");
        lbl_HitPoints.setLayoutData(gridDataLeft);
        
        txt_HitPoints = new Text(group, SWT.SINGLE);
        txt_HitPoints.setLayoutData(gridDataRight);
        txt_HitPoints.addVerifyListener(digitVerifier);
        
        //Button Row
        btn = new Button(group, SWT.PUSH);
        btn.setText("Add To Order");
        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        
        gd.horizontalSpan = 1;
        btn.setLayoutData(gd);
        addListeners();
	}
	
	private void addListeners(){
		btn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addNewActor();
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
		lbl_Name.dispose();
		txt_Name.dispose();
		lbl_Initiative.dispose();
		btn.dispose();
		btn.removeSelectionListener(null);
	}

	public void addNewActor() {
		dataService.addActor(buildActorFromFields());
		clearFields();
	}
	
	private Actor buildActorFromFields(){
		String name = txt_Name.getText();
		String initiativeRaw = txt_Initiative.getText();
		Integer initiative = Integer.parseInt(initiativeRaw);
		String hitPointsRaw = txt_HitPoints.getText();
		if(hitPointsRaw == ""){
			hitPointsRaw = "0";
		}
		Integer hitPoints = Integer.parseInt(hitPointsRaw);
		return new Actor(name, initiative, hitPoints);
	}
	
	private void clearFields(){
		txt_Name.setText("");
		txt_Initiative.setText("");
		txt_HitPoints.setText("");
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
	
	public void setDigitVerifier(DigitVerificationListener digitVerifier) {
		this.digitVerifier = digitVerifier;
	}
}
