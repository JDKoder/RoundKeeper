package com.warriorwebpros.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;

public class ActorEntryView implements IRoundKeeperView{

	private Label lbl_Name;
	private Text txt_Name;
	private Label lbl_Initiative;
	private Text txt_Initiative;
	private Button btn;
	private ActorDataService dataService;
	
	@Override
	public void initUI(Shell shell) {
		GridLayout gl = new GridLayout(2, true);
        gl.horizontalSpacing = 4;
        gl.verticalSpacing = 4;
        gl.marginBottom = 5;
        gl.marginTop = 5;
        shell.setLayout(gl);

        GridData gridData = new GridData();

        //Name Row
        lbl_Name = new Label(shell, SWT.SINGLE);
        lbl_Name.setText("Name:");
        lbl_Name.setLayoutData(gridData);
        
        txt_Name = new Text(shell, SWT.SINGLE);
        gridData.horizontalSpan = 1;
        gridData.horizontalAlignment = GridData.FILL;
        txt_Name.setLayoutData(gridData);
        
        //Initiative Row
        lbl_Initiative = new Label(shell, SWT.SINGLE);
        lbl_Initiative.setText("Inititative:");
        lbl_Initiative.setLayoutData(gridData);
        
        txt_Initiative = new Text(shell, SWT.SINGLE);
        txt_Initiative.setLayoutData(gridData);
        
        //Button Row
        btn = new Button(shell, SWT.PUSH);
        btn.setText("Add To Order");
        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.widthHint = 90;
        gd.heightHint = 30;
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
		return new Actor(name, initiative);
	}
	
	private void clearFields(){
		txt_Name.setText("");
		txt_Initiative.setText("");
	}
	
	public void setDataService(ActorDataService dataService) {
		this.dataService = dataService;
	}
}
