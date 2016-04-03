package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{
	
	private static Canvas drawPanel;
	private static boolean shiftDown = false;
	private static int moveValue = 10;
	public KeyHandler(){
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(Canvas.getSelectedOne(0) != null){
			if(e.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("UP");
				for(int i = 0;i < Canvas.getNrSelected();i++)
					Canvas.getSelectedOne(i).setY(Canvas.getSelectedOne(i).getY()-moveValue);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				System.out.println("DOWN");
				for(int i = 0;i < Canvas.getNrSelected();i++)
					Canvas.getSelectedOne(i).setY(Canvas.getSelectedOne(i).getY()+moveValue);
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				System.out.println("LEFT");
				for(int i = 0;i < Canvas.getNrSelected();i++)
				Canvas.getSelectedOne(i).setX(Canvas.getSelectedOne(i).getX()-moveValue);
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				System.out.println("RIGHT");
				for(int i = 0;i < Canvas.getNrSelected();i++)
					Canvas.getSelectedOne(i).setX(Canvas.getSelectedOne(i).getX()+moveValue);
			}
			if(drawPanel != null){
				drawPanel.repaint();
				DrawHandler.updateLabels();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			setShiftDown(true);
			moveValue = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			System.out.println("NOT SHIFT!");
			setShiftDown(false);
			moveValue = 10;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}


	public static Canvas getDrawPanel() {
		return drawPanel;
	}


	public static void setDrawPanel(Canvas drawPanel) {
		KeyHandler.drawPanel = drawPanel;
	}


	public static boolean isShiftDown() {
		return shiftDown;
	}


	public static void setShiftDown(boolean shiftDown) {
		KeyHandler.shiftDown = shiftDown;
	}
}
