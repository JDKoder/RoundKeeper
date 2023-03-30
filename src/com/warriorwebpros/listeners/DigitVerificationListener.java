package com.warriorwebpros.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * Only allows the user to enter digits into the text field.  
 * The user is still able to paste non-decimal values into the field.
 *
 * When setting the field to empty string on a widget
 * the verifiers get called automatically.
 * Therefore, this needs to handle allowing the event when
 * character is /u0000 and the text is "".
 */
public class DigitVerificationListener implements VerifyListener {

		@Override
		public void verifyText(VerifyEvent event) {
			switch(event.keyCode){
				case SWT.BS:           // Backspace  
	            case SWT.DEL:          // Delete  
	            case SWT.HOME:         // Home  
	            case SWT.END:          // End  
	            case SWT.ARROW_LEFT:   // Left arrow  
	            case SWT.ARROW_RIGHT:  // Right arrow
	            	return;
			}
			if(!Character.isDigit(event.character)
					&& !(event.text.isEmpty() && event.character == '\u0000')) {
				event.doit = false;
			}
		}
}
