import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel{
	
	private static Sprite[] drawings;
	private static int nrDrawings = 0;
	
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
	public void setNrDrawings(int nrDraw){
		nrDrawings = nrDraw;
	}
	public int getNrDrawings(){
		return nrDrawings;
	}
	public void popDrawing(){
		
	}
	public void deleteDrawing(int index){
		
	}
	
}
