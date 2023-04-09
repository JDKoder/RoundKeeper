package com.warriorwebpros.binders;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.warriorwebpros.colors.RoundKeeperColorConstants;

import javax.inject.Qualifier;

public class ViewModule extends AbstractModule {

    static Shell mainShell;

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MainGridLayout{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MainShell {}

    @Provides
    @MainShell
    public Shell provideMainShell() {
        if(mainShell == null) {
            mainShell = new Shell();
            mainShell.setText("Round Keeper");
            mainShell.setSize(400,200);
            //Center
            Rectangle bds = mainShell.getMonitor().getBounds();
            Point p = mainShell.getSize();
            int nLeft = (bds.width - p.x) / 2;
            System.out.println("bds.width: " + bds.width);
            System.out.println("p.x: " + p.x);
            System.out.println("nLeft: " + nLeft);
            int nTop = (bds.height - p.y) / 2;
            System.out.println("bds.height: " + bds.height);
            System.out.println("p.y: " + p.y);
            System.out.println("nTop: " + nTop);
            Rectangle r = new Rectangle(nLeft, nTop, p.x, p.y);
            mainShell.setBounds(r);
            GridLayout gl = provideMainGridLayout();
            mainShell.setLayout(gl);
            mainShell.setBackground(
                    RoundKeeperColorConstants.GROUP_BACKGROUND.getColor(mainShell.getDisplay()));
        }
        return mainShell;
    }

    private GridLayout provideMainGridLayout() {
        GridLayout gl = new GridLayout(2, true);
        gl.horizontalSpacing = 4;
        gl.verticalSpacing = 4;
        gl.marginBottom = 5;
        gl.marginTop = 5;
        return gl;
    }

}
