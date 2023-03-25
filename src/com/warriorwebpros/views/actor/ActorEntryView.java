package com.warriorwebpros.views.actor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.warriorwebpros.colors.RoundKeeperColorConstants;
import com.warriorwebpros.listeners.DigitVerificationListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.views.IRoundKeeperView;

public class ActorEntryView implements IRoundKeeperView {

	private Label lblName;
	private Text txtName;
	private Label lblInitiative;
	private Text txtInitiative;
	private Label lblHitPoints;
	private Text txtHitPoints;
	private Button btnAddToOrder;
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
        lblName = new Label(group, SWT.SINGLE);
        lblName.setText("Name:");
        lblName.setLayoutData(gridDataLeft);
        
        
        txtName = new Text(group, SWT.SINGLE);
        txtName.setLayoutData(gridDataRight);
        
        //Initiative Row
        lblInitiative = new Label(group, SWT.SINGLE);
        lblInitiative.setText("Initiative:");
        lblInitiative.setLayoutData(gridDataLeft);
        
        txtInitiative = new Text(group, SWT.SINGLE);
        txtInitiative.setLayoutData(gridDataRight);
        txtInitiative.addVerifyListener(digitVerifier);
        
        //HitPoints Row
        lblHitPoints = new Label(group, SWT.SINGLE);
        lblHitPoints.setText("Hit Points:");
        lblHitPoints.setLayoutData(gridDataLeft);
        
        txtHitPoints = new Text(group, SWT.SINGLE);
        txtHitPoints.setLayoutData(gridDataRight);
        txtHitPoints.addVerifyListener(digitVerifier);
		txtHitPoints.addListener(101, new Listener() {
			@Override
			public void handleEvent(Event event) {
				txtHitPoints.setText("");
			}
		});
        
        //Button Row

        /*btnAddToOrder = new Button(group, SWT.PUSH);
        btnAddToOrder.setText("Add To Order");*/
		GridData gridDataButtons = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridDataButtons.horizontalSpan = 1;
		btnAddToOrder = new AddActorButton<>(group, gridDataButtons, dataService);
	}

	
	/**
	 * SWT doesn't automatically garbage collect its UI components.
	 * Calling dispose on each one will  
	 */
	@Override
	public void cleanUpUI() {
		lblName.dispose();
		txtName.dispose();
		lblInitiative.dispose();
		btnAddToOrder.dispose();
		btnAddToOrder.removeSelectionListener(null);
	}


	

	
	private void clearFields(){
		txtName.setText("");
		txtInitiative.setText("");
		txtHitPoints.setText("");
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
	
	public void setDigitVerifier(DigitVerificationListener digitVerifier) {
		this.digitVerifier = digitVerifier;
	}
}
