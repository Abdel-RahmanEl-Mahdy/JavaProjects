package Testing;
import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.condition.WindowShowingCondition;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import java.awt.Point;
import com.windowtester.runtime.swing.locator.JTableItemLocator;

public class DeletePersonInformation extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public DeletePersonInformation() {
		super(Person.PersonPreview.class);
	}

	/**
	 * Main test method.
	 */
	public void testDeletePerson() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Browse"));
		ui.wait(new WindowShowingCondition("Open"));
		ui.click(new JListLocator("Person Information"));
		ui.click(new JButtonLocator("Open"));
		ui.click(new JListLocator("myJSON.json"));
		ui.click(new JButtonLocator("Open"));
		ui.wait(new WindowDisposedCondition("Open"));
		ui.click(new JButtonLocator("Import JSON"));
		ui.click(new JTableItemLocator(new Point(9, 0)));
		ui.click(new JButtonLocator("Delete"));
		ui.wait(new WindowShowingCondition("Person Information System !"));
		ui.click(new JButtonLocator("OK"));
		ui.wait(new WindowDisposedCondition("Person Information System !"));
		ui.click(new JButtonLocator("Exit"));
	}

}