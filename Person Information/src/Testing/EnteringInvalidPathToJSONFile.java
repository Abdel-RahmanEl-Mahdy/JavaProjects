package Testing;
import com.windowtester.runtime.swing.locator.JTextComponentLocator;
import javax.swing.JTextField;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JPanel;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.locator.JButtonLocator;

public class EnteringInvalidPathToJSONFile extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public EnteringInvalidPathToJSONFile() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testEnteringInvalidPathToJSONFile() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JTextComponentLocator(JTextField.class, 6,
				new SwingWidgetLocator(JPanel.class)));
		ui.enterText("safgfsagasg");
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new JButtonLocator("Exit"));
	}

}