import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    	public static void main(String[] args) {
        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
                	Window.createGUI();
            	}
        	});
	}
}