import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window {
    public static void createGUI() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	GamePanel panel = new GamePanel();
	frame.setContentPane(panel);

	frame.setAlwaysOnTop(true);
	frame.setUndecorated(true);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setSize(screenSize);

	frame.setLocationRelativeTo(null);
	frame.setBackground(new Color(0,0,0,0));

        frame.setVisible(true);

    }

}