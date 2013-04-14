import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.warriorwebpros.controller.ViewController;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.service.ActorSortingService;

public class Main {
 
	ViewController viewController;
	Shell shell;
	Display display;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.display = new Display();
		main.shell = new Shell(main.display);
        main.viewController = new ViewController(main.display, main.shell);
        //This makes me cringe.  Spring needs to happen soon.
        main.viewController.setDataService(new ActorDataService(new ArrayList<Actor>(), new ActorSortingService()));
        main.viewController.initializeDataEntryView();
        main.runProgramLoop();
        main.viewController.dispose();
        main.display.dispose();
	}
	
	private void runProgramLoop(){
		while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	
	public void setShell(Shell shell){
		this.shell = shell;
	}
	
	public void setDisplay(Display display){
		this.display = display;
	}
  }