import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class ButtonHandler implements MouseListener{
	
	private static Canvas drawPanel;
	private boolean type;
	
	public ButtonHandler(boolean t){
		super();
		this.type = t;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(type){
			Random r = new Random();
			Sprite s = new Sprite(Color.getHSBColor(r.nextFloat(), r.nextFloat(), r.nextFloat()),0,0,10,10);
			drawPanel.pushDrawing(s);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void setDrawPanel(Canvas d){
		drawPanel = d;
	}
}
