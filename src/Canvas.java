import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel{
	
	private static Sprite[] drawings;
	private static int nrDrawings = 0;
	private static Sprite selectedOne;
	private static DrawHandler drawPanelHand;
	
	public Canvas(){
		setSelectedOne(null);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		//paint background//
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		//paint drawings
		for(int i = 0;i<nrDrawings;i++){
			drawings[i].update();
			drawings[i].drawSprite(g);
		}
		
	}
	public void pushDrawing(Sprite s){
		drawings[nrDrawings] = s;
		nrDrawings++;
	}
	public void setDrawings(int nrDraw,Sprite[] Drawings){
		drawings = Drawings;
		nrDrawings = nrDraw;
	}
	public static void setNrDrawings(int nrDraw){
		nrDrawings = nrDraw;
	}
	public static int getNrDrawings(){
		return nrDrawings;
	}
	public void popDrawing(){
		if(nrDrawings>0)
			nrDrawings--;
	}
	public Sprite getDrawing(int index){
		if(index<nrDrawings && index>=0)
			return drawings[index];
		return null;
	}
	public void deleteDrawing(int index){
		if(index < nrDrawings){
			for(int i = index;i<nrDrawings-1;i++){
				drawings[i] = drawings[i+1];
			}
			nrDrawings--;
		}
	}

	public static Sprite getSelectedOne() {
		return selectedOne;
	}

	public static void setSelectedOne(Sprite sO) {
		selectedOne = sO;
	}
	public static void setDrawPanelHand(DrawHandler newDrawPanelHand){
		drawPanelHand = newDrawPanelHand;
	}
	public static DrawHandler getDrawPanelHand(){
		return drawPanelHand;
	}
	
}
