package com.warriorwebpros.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public abstract class AbstractView implements IRoundKeeperView{

    protected final List<Widget> managedWidgets = new ArrayList<>();

    /**
     * SWT doesn't automatically garbage collect its UI components.
     * Calling dispose on each one will
     */
    @Override
    public void cleanUpUI() {
        for(Widget widget : managedWidgets) {
            widget.dispose();
        }
    }

    /**
     * Iterates through the widgets in the view and
     * Uses reflection to:
     * <ul>
     *     <li>Clear the text inputs of Text widgets</li>
     * </ul>
     */
    public void resetInputs() {
        for (Widget widget : managedWidgets) {
            if(!widget.isDisposed() && widget instanceof Text) {
                ((Text)widget).setText("");
            }
        }
    }
}
