package Testing;

import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.condition.WindowShowingCondition;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JScrollPane;
import java.awt.Point;
import com.windowtester.runtime.swing.locator.JTableItemLocator;
import java.awt.event.InputEvent;

public class AddingDataToTable extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public AddingDataToTable() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testAddingDataToTable() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Browse"));
		ui.wait(new WindowShowingCondition("Open"));
		ui.click(new JListLocator("Person Information"));
		ui.click(new JButtonLocator("Open"));
		ui.click(new JListLocator("myJSON.json"));
		ui.click(new JButtonLocator("Open"));
		ui.wait(new WindowDisposedCondition("Open"));
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new SwingWidgetLocator(JScrollPane.class));
		ui.click(2, new JTableItemLocator(new Point(7, 2)),
				InputEvent.BUTTON1_MASK);
		ui.click(new JTableItemLocator(new Point(5, 2)));
		ui.click(2, new JTableItemLocator(new Point(5, 2)),
				InputEvent.BUTTON1_MASK);
		ui.click(new JButtonLocator("Exit"));
	}

}