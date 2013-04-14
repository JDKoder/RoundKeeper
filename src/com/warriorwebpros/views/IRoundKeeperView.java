package com.warriorwebpros.views;

import org.eclipse.swt.widgets.Shell;

public interface IRoundKeeperView {
	
	/**
	 * Initialize each of UI components for the view with the passed in shell.
	 * Ideally, we are just using this method for layout, but we won't force that
	 * so we can keep the implementation fast and loose.
	 * @param shell
	 */
	void initUI(Shell shell);
	
	/**
	 * Perform any cleanup such as calling dispose on any object that extends
	 * Widget.
	 */
	void cleanUpUI();
	
}
