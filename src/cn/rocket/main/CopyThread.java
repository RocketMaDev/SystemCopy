package cn.rocket.main;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.locks.LockSupport;

public class CopyThread extends Thread {
	private final String content;
	private final TextArea ta;
	private final Button b;

	protected void unpark() {
		LockSupport.unpark(this);
	}

	protected CopyThread(String content, TextArea textArea, Button button) throws Exception {
		super();
		if (content.length() > 0) {
			this.content = content;
			this.ta = textArea;
			this.b = button;
		} else {
			throw new Exception("Text Area is Empty!");
		}
	}
	
	@Override
	public void run() {
		int p = -1;
		int len;
		char[] cs = content.toCharArray();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		while (p != -2) {
			char[] temp;
			if ((len = cs.length - p - 1) > 6) {
				temp = new char[6];
				System.arraycopy(cs, p + 1, temp, 0, 6);
				p += 6;
			} else {
				temp = new char[len];
				System.arraycopy(cs, p + 1, temp, 0, len);
				p = -2;
			}
			clipboard.setContents(new StringSelection(new String(temp)), null);
			LockSupport.park();
		}
		ta.setDisable(false);
		b.setDisable(false);
		clipboard.setContents(new StringSelection(""), null);
	}
}
