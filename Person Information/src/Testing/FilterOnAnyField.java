package Testing;

import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.condition.WindowShowingCondition;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import com.windowtester.runtime.swing.locator.JTextComponentLocator;
import javax.swing.JTextField;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JPanel;

public class FilterOnAnyField extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public FilterOnAnyField() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testFilterOnAnyField() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Browse"));
		ui.wait(new WindowShowingCondition("Open"));
		ui.click(new JListLocator("Person Information"));
		ui.click(new JButtonLocator("Open"));
		ui.click(new JListLocator("myJSON.json"));
		ui.click(new JButtonLocator("Open"));
		ui.wait(new WindowDisposedCondition("Open"));
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new JTextComponentLocator(JTextField.class, 5,
				new SwingWidgetLocator(JPanel.class)));
		ui.enterText("ahmed");
		ui.click(new JButtonLocator("Filter"));
		ui.click(new JButtonLocator("Exit"));
	}

}