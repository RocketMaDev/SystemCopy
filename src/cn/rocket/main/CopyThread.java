package cn.rocket.main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.locks.LockSupport;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
* A thread that is used to copy specified strings by 6 
* characters in once.
* @author RocketBD
*/
public class CopyThread extends Thread{
	/** 
	* The whole string you want to copy.
	*/
	private String content;
	
	/**
	* The instence of the TextArea in the GUI window.
	*/
	private TextArea ta;
	
	/**
	* The instence of the Button in the GUI window.
	*/
	private Button b;
	
	/**
	* Self-unlocking method.
	*/
	protected void unpark(){
		LockSupport.unpark(this);
	}
	
	/*
	* The constructor of this class.
	* @param String - the content you want to copy.
	* @param TextArea -  the instence of the TextArea in the GUI window.
	* @param Button -  the instence of the Button in the GUI window.
	* @throws Exception
	*/
	protected CopyThread(String content,TextArea textArea,Button button) throws Exception {
		super();
		if(content.length()>0) {//检查字符串大小是否大于0
			this.content = content;
			this.ta = textArea;
			this.b = button;
		} else 
			throw new Exception("Text Area is Empty!");
	}
	
	/**
	* The overwriten run() method in Thread.
	*/
	@Override
	public void run() {
		int p = -1;//指针
		int len;
		char[] cs = content.toCharArray();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪贴板
		while(p != -2) {
			char[] temp;
			if((len = cs.length - p - 1) > 6) {//若未使用的字符串长度大于6
				temp = new char[6];
				System.arraycopy(cs, p+1, temp, 0, 6);
				p += 6;
			} else {//若未使用的字符串小于等于6
				temp = new char[len];
				System.arraycopy(cs, p+1, temp, 0, len);
				p = -2;
			}
			clipboard.setContents(new StringSelection(new String(temp)), null);//向剪贴板里复制6个字符或不足6个的剩余字符
			LockSupport.park();//锁住当前线程
		}
		ta.setDisable(false);//复制完整个字符串后将TextArea和Button设为可用状态
		b.setDisable(false);
		clipboard.setContents(new StringSelection(new String("")), null);//清空剪贴板
	}
}
