package Testing;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.locator.JTextComponentLocator;
import javax.swing.JTextField;
import com.windowtester.runtime.swing.SwingWidgetLocator;
import javax.swing.JPanel;
import com.windowtester.runtime.swing.locator.JButtonLocator;

public class EnteringInvalidDataTybe extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public EnteringInvalidDataTybe() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testEnteringInvalidDataTybe() throws Exception {
		IUIContext ui = getUI();
		ui.click(new LabeledTextLocator("First Name"));
		ui.enterText("24214");
		ui.click(new JTextComponentLocator(JTextField.class, 1,
				new SwingWidgetLocator(JPanel.class)));
		ui.enterText("21412412");
		ui.click(new LabeledTextLocator("Age"));
		ui.enterText("asdasda");
		ui.click(new LabeledTextLocator("Title"));
		ui.enterText("dasdasda");
		ui.click(new JButtonLocator("Exit"));
		assertEquals("", new LabeledTextLocator("Age").getText(ui));
		assertEquals("", new LabeledTextLocator("Phone").getText(ui));
		assertEquals("", new LabeledTextLocator("First Name").getText(ui));
		assertEquals("", new LabeledTextLocator("Last Name").getText(ui));
	}

}