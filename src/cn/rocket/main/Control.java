package cn.rocket.main;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Control {

	@FXML TextArea textArea;
	@FXML Button button;
	@FXML Pane pane;
	@FXML Label label;
	
	@FXML public void pressed() {
		textArea.getText();
		textArea.setDisable(true);
		button.setDisable(true);
		CopyThread ct;
		try {
			ct = new CopyThread(textArea.getText(), textArea, button);
		} catch (Exception e1) {
			textArea.setDisable(false);
			label.setVisible(true);
			e1.printStackTrace();
			return;
		}
		ct.start();
		MyHotkeyListener mhl = new MyHotkeyListener(ct);
		JIntellitype hotkey = JIntellitype.getInstance();
		hotkey.registerHotKey(0, JIntellitype.MOD_CONTROL, 'B');
		hotkey.addHotKeyListener(mhl);
	}

	@FXML public void textChanged() {
		if(label.isVisible()) {
			label.setVisible(false);
			button.setDisable(false);
		}
	}

}

class MyHotkeyListener implements HotkeyListener {
	private final CopyThread ct;

	protected MyHotkeyListener(CopyThread ct) {
		this.ct = ct;
	}

	@Override
	public void onHotKey(int identifier) {
		if (identifier == 0) {
			ct.unpark();
		}
	}
}
