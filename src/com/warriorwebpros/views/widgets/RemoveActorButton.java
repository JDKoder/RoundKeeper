package com.warriorwebpros.views.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

public class RemoveActorButton extends Button {

    public static final int SELECT_REMOVE_ACTOR_EVENT_TYPE = 103;
    public RemoveActorButton(Group group, GridData gd) {
        super(group, SWT.PUSH);
        setText("Remove Actor");
        setLayoutData(gd);
        addListeners(group);
    }

    private void addListeners(Group group){
        addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                group.notifyListeners(SELECT_REMOVE_ACTOR_EVENT_TYPE, new Event());
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                widgetSelected(arg0);
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        removeSelectionListener(null);
    }

    @Override
    protected void checkSubclass() {
        //Fuck you SWT! I do what I want!
    }
}
