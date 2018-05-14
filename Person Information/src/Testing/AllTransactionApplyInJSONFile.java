package Testing;

import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.condition.WindowShowingCondition;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;
import com.windowtester.runtime.swing.locator.JTextComponentLocator;
import javax.swing.JTextField;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JPanel;
import java.awt.Point;
import com.windowtester.runtime.swing.locator.JTableItemLocator;

public class AllTransactionApplyInJSONFile extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public AllTransactionApplyInJSONFile() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testAllTransactionApplyInJSONFile() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Browse"));
		ui.wait(new WindowShowingCondition("Open"));
		ui.click(new JListLocator("Person Information"));
		ui.click(new JButtonLocator("Open"));
		ui.click(new JListLocator("myJSON.json"));
		ui.click(new JButtonLocator("Open"));
		ui.wait(new WindowDisposedCondition("Open"));
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new LabeledTextLocator("First Name"));
		ui.enterText("New");
		ui.click(new JTextComponentLocator(JTextField.class, 1,
				new SwingWidgetLocator(JPanel.class)));
		ui.enterText("Record");
		ui.click(new LabeledTextLocator("Age"));
		ui.enterText("55");
		ui.click(new LabeledTextLocator("Title"));
		ui.enterText("79070970");
		ui.click(new LabeledTextLocator("Email"));
		ui.enterText("new@new.new");
		ui.click(new JButtonLocator("Add"));
		ui.wait(new WindowShowingCondition("Person Information System !"));
		ui.click(new JButtonLocator("OK"));
		ui.wait(new WindowDisposedCondition("Person Information System !"));
		ui.click(new JTableItemLocator(new Point(9, 0)));
		ui.click(new LabeledTextLocator("First Name"));
		ui.enterText("Update");
		ui.click(new JTextComponentLocator(JTextField.class, 1,
				new SwingWidgetLocator(JPanel.class)));
		ui.enterText("Record");
		ui.click(new LabeledTextLocator("Age"));
		ui.enterText("54");
		ui.click(new LabeledTextLocator("Title"));
		ui.enterText("0112512321");
		ui.click(new LabeledTextLocator("Email"));
		ui.enterText("Update@u.com");
		ui.click(new JButtonLocator("Update"));
		ui.wait(new WindowShowingCondition("Person Information System !"));
		ui.click(new JButtonLocator("OK"));
		ui.wait(new WindowDisposedCondition("Person Information System !"));
		ui.click(new JTableItemLocator(new Point(9, 1)));
		ui.click(new JButtonLocator("Delete"));
		ui.wait(new WindowShowingCondition("Person Information System !"));
		ui.click(new JButtonLocator("OK"));
		ui.wait(new WindowDisposedCondition("Person Information System !"));
		ui.click(new JButtonLocator("Exit"));
	}

}