package Testing;

import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.condition.WindowShowingCondition;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import com.windowtester.runtime.swing.locator.JComboBoxLocator;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JPanel;

public class SortByChoosingField extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public SortByChoosingField() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testSortByChoosingField() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Browse"));
		ui.wait(new WindowShowingCondition("Open"));
		ui.click(new JListLocator("Person Information"));
		ui.click(new JButtonLocator("Open"));
		ui.click(new JListLocator("myJSON.json"));
		ui.click(new JButtonLocator("Open"));
		ui.wait(new WindowDisposedCondition("Open"));
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new JComboBoxLocator("Age", 1, new SwingWidgetLocator(
				JPanel.class)));
		ui.click(new JButtonLocator("Sort"));
		ui.click(new JButtonLocator("Exit"));
	}

}