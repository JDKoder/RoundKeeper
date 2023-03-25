package com.warriorwebpros.views.actor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;

public class AddActorButton <T extends ActorDataService> extends Button {
    T service;
    public AddActorButton(Group group, GridData gd, T service) {
        super(group, SWT.PUSH);
        setText("Add To Order");
        setLayoutData(gd);
    }

    private void addListeners(){
        addSelectionListener(new SelectionListener() {
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

    private void addNewActor() {
        notifyListeners(101, new Event());
        //service.addActor(buildActorFromFields());
    }

    /*private Actor buildActorFromFields(){
        String name = txtName.getText();
        String initiativeRaw = txtInitiative.getText();
        Integer initiative = Integer.parseInt(initiativeRaw);
        String hitPointsRaw = txtHitPoints.getText();
        if(hitPointsRaw == ""){
            hitPointsRaw = "0";
        }
        Integer hitPoints = Integer.parseInt(hitPointsRaw);
        return new Actor(name, initiative, hitPoints);
    }*/

    @Override
    public void dispose() {
        super.dispose();
        removeSelectionListener(null);
    }

    @Override
    protected void checkSubclass() {
        //fuck you SWT I do what I want!
    }
}
