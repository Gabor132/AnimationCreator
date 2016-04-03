package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel{
	
	private static Sprite[] drawings;
	private static int nrDrawings = 0;
	private static Sprite[] selectedOne = new Sprite[100];
	private static int nrSelected = 0;
	private static DrawHandler drawPanelHand;
	
	public Canvas(){
		setSelectedOne(null,0);
		setFocusable(true);
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
		if(DrawHandler.getMouseSelection()){
			Canvas.paintMouseSelection(g,DrawHandler.xMouseSelect, DrawHandler.yMouseSelect,
					DrawHandler.xMouseSelect2, DrawHandler.yMouseSelect2);
		}
		
	}
	public static void paintMouseSelection(Graphics g,int a,int b,int c,int d){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.drawLine(a, b, a, d);
		g2d.drawLine(a, b, c, b);
		g2d.drawLine(a, d, c, d);
		g2d.drawLine(c, b, c, d);
		
		/*g2d.drawString("("+a+" "+b+")", a, b);
		g2d.drawString("("+a+" "+d+")", a, d);
		g2d.drawString("("+c+" "+b+")", c, b);
		g2d.drawString("("+c+" "+d+")", c, d);*/
		
		g2d.setColor(Color.BLACK);
	}
	public static void pushDrawing(Sprite s){
		drawings[nrDrawings] = s;
		nrDrawings++;
	}
	public static void setDrawings(int nrDraw,Sprite[] Drawings){
		drawings = Drawings;
		nrDrawings = nrDraw;
	}
	public static Sprite[] getDrawings(){
		return drawings;
	}
	public static void setNrDrawings(int nrDraw){
		nrDrawings = nrDraw;
	}
	public static int getNrDrawings(){
		return nrDrawings;
	}
	public static void popDrawing(){
		if(nrDrawings>0)
			nrDrawings--;
	}
	public static void setDrawing(Sprite s,int index){
		if(index<nrDrawings && index>=0)
			drawings[index] = s;
	}
	public static Sprite getDrawing(int index){
		if(index<nrDrawings && index>=0)
			return drawings[index];
		return null;
	}
	public static void deleteDrawing(int index){
		if(index < nrDrawings){
			for(int i = index;i<nrDrawings-1;i++){
				drawings[i] = drawings[i+1];
				drawings[i].setDepth(drawings[i].getDepth()-1);
			}
			nrDrawings--;
		}
	}

	public static Sprite getSelectedOne(int index) {
		return selectedOne[index];
	}

	public static void setSelectedOne(Sprite sO,int index) {
		selectedOne[index] = sO;
	}
	public static void setDrawPanelHand(DrawHandler newDrawPanelHand){
		drawPanelHand = newDrawPanelHand;
	}
	public static DrawHandler getDrawPanelHand(){
		return drawPanelHand;
	}

	public static int getNrSelected() {
		return nrSelected;
	}

	public static void setNrSelected(int nrSelected) {
		Canvas.nrSelected = nrSelected;
	}
	
	public static String[] getSelectedList(){
		String[] data = new String[100];
		for(int i = 0;i<nrSelected;i++){
			data[i] = selectedOne[i].getName();
		}
		return data;
	}
	
}
