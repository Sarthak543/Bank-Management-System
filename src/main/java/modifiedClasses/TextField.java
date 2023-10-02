package modifiedClasses;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

// in this class we are overriding the gained focus methods so that we can use place holder in the JTextField
public class TextField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	public TextField(String text,int x,int y, int width, int height,Color c) {
		setForeground(c);
		TextField.this.text=text;
		setText(text);
		setBounds(x,y,width,height);
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(getText().isEmpty()) {
					setText(TextField.this.text);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().equals(TextField.this.text)) {
					setText("");
				}
			}
		});
	}

}
