import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class GamePanel extends JPanel{
	float playerX;
	float playerY;

	float velocityY;
	float velocityX;
	float gravity;

	boolean isDragging;
	int lastMouseX;
	int lastMouseY;

	int frameCount = 0;
	List<Rectangle> obstacles = new ArrayList<>();

	public GamePanel() {
		gravity = 1.2f;
		playerY = MouseInfo.getPointerInfo().getLocation().y;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		setOpaque(false);
		
		Timer timer = new Timer(16, e-> {
			Point p = MouseInfo.getPointerInfo().getLocation();

			if(isDragging) {
				lastMouseX = (int)p.x;
				lastMouseY = (int)p.y;			

				playerX = p.x;
				playerY = p.y;
			} else {
				playerX += velocityX;
				velocityY += gravity;
				playerY += velocityY;
				velocityX *= 0.95;
			}
			
			//Ground, ceiling
			if(playerY > screenSize.height-25) {
				playerY = screenSize.height-25;
				velocityY = -velocityY * 0.6f;
			} else if(playerY < 25) {
				playerY = 25;
				velocityY = -velocityY*0.6f;
			}
			
			//Walls
			if(playerX > screenSize.width-25) {
				playerX = screenSize.width-25;
				velocityX = -velocityX;

			} else if (playerX < 25) {
				playerX = 25;
				velocityX = -velocityX;
				
			}
			
			//Window List Update
			frameCount++;
			if(frameCount % 60 == 0) {
				obstacles = WindowDetector.getWindowRects();
			}
			
			for(Rectangle rect : obstacles) {
				boolean withinX = playerX > rect.x && playerX < rect.x + rect.width;
            			boolean onTop = playerY >= rect.y && playerY <= rect.y + velocityY + 5;
            			if (withinX && onTop && velocityY > 0) {
                			playerY = rect.y;
                			velocityY = 0;
            			}


			}


	
			repaint();
		});

		timer.start();

		//Track Drag and Drop
		Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
		MouseEvent me = (MouseEvent) event;
		if(me.getID() == MouseEvent.MOUSE_PRESSED) {

			//check distance to mouse
			double distance = Math.sqrt(Math.pow(me.getX() - playerX,2) + Math.pow(me.getY() - playerY,2));
				if(distance < 35 ) {
					isDragging = true;
				}
			}
		
			if(me.getID() == MouseEvent.MOUSE_RELEASED) {
				isDragging = false;
				velocityX = me.getX() - lastMouseX;
				velocityY = me.getY() - lastMouseY;
			}
		}, AWTEvent.MOUSE_EVENT_MASK);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval((int)playerX-25, (int)playerY-25, 50, 50);
	}

}