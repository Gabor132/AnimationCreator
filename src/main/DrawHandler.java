package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;


public class DrawHandler implements MouseInputListener,MouseWheelListener{

	private static JLabel selectedName;
	private static JLabel selectedX;
	private static JLabel selectedY;
	private static JLabel selectedRotation;
	private static JLabel selectedScale;
	private static JLabel selectedDepth;
	private static JLabel currentFrame;
	private static JLabel currentFrameTime;
	private static Canvas drawPanel;
	private static int xMOUSE;
	private static int yMOUSE;
	public static int xMouseSelect;
	public static int yMouseSelect;
	public static int xMouseSelect2;
	public static int yMouseSelect2;
	private static boolean mouseSelection = false;
	private static SliderHandler rotationSliderHand;
	private static SliderHandler scaleSliderHand;
	private static SliderHandler depthSliderHand;
	
	
	public DrawHandler(){
		xMOUSE = 0;
		yMOUSE = 0;
	}
	
	public static void setDrawPanel(Canvas d){
		drawPanel = d;
	}
	
	public void mouseClicked(MouseEvent mouse) {
		if(mouse.getSource().getClass() == JPanel.class){
			JPanel subjectPanel = (JPanel) mouse.getSource();
			for(int i = 0;i<subjectPanel.getComponentCount();i++){
				JLabel subjectLabel = (JLabel) subjectPanel.getComponent(i);
				int x = subjectLabel.getX();
				int y = subjectLabel.getY();
				if(mouse.getX() >= x && mouse.getX()<=x+subjectLabel.getWidth() && mouse.getY()>=y && mouse.getY()<=y+subjectLabel.getHeight()){
					String s = subjectLabel.getText().substring(0, subjectLabel.getText().lastIndexOf(":"));
					try{
						if(s.equals("Rotation")){
							int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Set " + s + ":"));
							if(Canvas.getSelectedOne(0)!=null){
								Canvas.getSelectedOne(0).setRotation(a);
								
							}
						}
						else if(s.equals("Scale")){
							int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Set " + s + ":"));
							if(Canvas.getSelectedOne(0)!=null){
								Canvas.getSelectedOne(0).setScaleW(Canvas.getSelectedOne(0).getScaleW()*a);
								Canvas.getSelectedOne(0).setScaleH(Canvas.getSelectedOne(0).getScaleH()*a);
							}
						}
						else if(s.equals("Depth")){
							int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Set " + s + ":"));
							if(Canvas.getSelectedOne(0)!=null)
								Canvas.getSelectedOne(0).setDepth(a);
						}
						else if(s.equals("X")){
							int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Set " + s + ":"));
							if(Canvas.getSelectedOne(0)!=null)
								Canvas.getSelectedOne(0).setX(a);
						}
						else if(s.equals("Y")){
							int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Set " + s + ":"));
							if(Canvas.getSelectedOne(0)!=null)
								Canvas.getSelectedOne(0).setY(a);
						}
						updateLabels();
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}
				}
			}
		}
		if(mouse.getSource().getClass() == JLabel.class.getClass()){
			System.out.println("CLICKED ON LABEL");
		}
	}

	public void mouseDragged(MouseEvent mouse) {
		
		//System.out.println("Trying to drag...");
		if(mouseSelection){
			xMouseSelect2 = mouse.getX();
			yMouseSelect2 = mouse.getY();
		}
		if(Canvas.getSelectedOne(0) != null){
			
			//System.out.println("DRAGGING " + selectedOne.getName());
			
			int difX = xMOUSE - mouse.getX();
			int difY = yMOUSE - mouse.getY();
			
			//System.out.println("DRAGGING " + selectedOne.getName() + " from (" + xMOUSE + "," + yMOUSE + ") to ("
			//+ mouse.getX()+","+mouse.getY() + ")");
			for(int i = 0;i < Canvas.getNrSelected();i++){
				Canvas.getSelectedOne(i).setX(Canvas.getSelectedOne(i).getX() - difX);
				Canvas.getSelectedOne(i).setY(Canvas.getSelectedOne(i).getY() - difY);
				Canvas.getSelectedOne(i).drawSprite(drawPanel.getGraphics());

				xMOUSE = mouse.getX();
				yMOUSE = mouse.getY();
			}
			
			updateLabels();
			drawPanel.repaint();
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
	public void mousePressed(MouseEvent mouse) {
		int i;
		boolean foundSelect = false;
		Sprite multipleSelectDrawing = new Sprite();
		for(i = 0;i<Canvas.getNrDrawings();i++){
			Sprite drawing = Canvas.getDrawing(i);
			int xDrawing = drawing.getX();
			int yDrawing = drawing.getY();
			double widthDrawing = drawing.getScaleW()+drawing.getScaleW()/drawing.getWidth();
			double heightDrawing = drawing.getScaleH()+drawing.getScaleH()/drawing.getHeight();
			if(xMOUSE>=xDrawing && xMOUSE<xDrawing+widthDrawing && yMOUSE>=yDrawing && yMOUSE<yDrawing+heightDrawing){
				for(int k = 0;k < Canvas.getNrSelected();k++){
					if(!KeyHandler.isShiftDown()){
						if(Canvas.getSelectedOne(k)!=null){
							System.out.println("DESELECTED " + Canvas.getSelectedOne(k).getName());
							Canvas.getSelectedOne(k).setSelected(false);
						}
					}
				}
				if(!drawing.getSelected()){
					if(KeyHandler.isShiftDown() && Canvas.getNrSelected()>=0){
						if(foundSelect){
							System.out.println("DESELECTED " + multipleSelectDrawing.getName());
							multipleSelectDrawing.setSelected(false);
							Canvas.setNrSelected(Canvas.getNrSelected()-1);
						}else{
							foundSelect = true;
						}
						System.out.println("SELECTED + " + drawing.getName());
						Canvas.setSelectedOne(drawing,Canvas.getNrSelected());
						Canvas.setNrSelected(Canvas.getNrSelected()+1);
						drawing.setSelected(true);
						multipleSelectDrawing = drawing;
					}
					else{
						drawing.setSelected(true);
						System.out.println("SELECTED ONLY " + drawing.getName());
						Canvas.setSelectedOne(drawing, 0);
						Canvas.setNrSelected(1);
					}
				}
				selectedName.setText("Name: " + drawing.getName());
				selectedX.setText("X: " + drawing.getX());
				selectedY.setText("Y: " + drawing.getY());
				selectedRotation.setText("Rotation: " + drawing.getRotation());
				selectedScale.setText("Scale: " + drawing.getScaleW() + " " + drawing.getScaleH());
				selectedDepth.setText("Depth: " + drawing.getDepth());
				if(rotationSliderHand != null){
					SliderHandler.skipThis();
					SliderHandler.getSliderAt(0).setValue((int) (drawing.getRotation()*1000));
					rotationSliderHand.setValue((drawing.getRotation()));
				}
				if(scaleSliderHand != null){
					SliderHandler.skipThis();
					SliderHandler.getSliderAt(1).setValue((int) (drawing.getScaleW()));
					scaleSliderHand.setValue(drawing.getScaleW());
				}
				if(depthSliderHand != null){
					SliderHandler.skipThis();
					SliderHandler.getSliderAt(2).setValue(0);
					depthSliderHand.setValue(0);
					SliderHandler.skipThis();
					SliderHandler.getSliderAt(2).setValue(drawing.getDepth());
					depthSliderHand.setValue(drawing.getDepth());
				}
			}
			else{
				if(mouse.getSource().getClass() != JPanel.class){
					if(!KeyHandler.isShiftDown()){
						if(drawing.getSelected()){
							System.out.println("DESELECTED " + drawing.getName());
							drawing.setSelected(false);
						}
					}
				}
			}
		}
		if(Canvas.getSelectedOne(0)==null || !Canvas.getSelectedOne(0).getSelected()){
			Canvas.setSelectedOne(null,0);
			Canvas.setNrSelected(0);
			xMouseSelect = mouse.getX();
			yMouseSelect = mouse.getY();
			xMouseSelect2 = mouse.getX();
			yMouseSelect2 = mouse.getY();
			mouseSelection = true;
			if(rotationSliderHand != null){
				SliderHandler.getSliderAt(0).setValue(0);
				rotationSliderHand.setValue(0);
			}
			if(scaleSliderHand != null){
				SliderHandler.getSliderAt(1).setValue(1);
				scaleSliderHand.setValue(1);
				scaleSliderHand.setValue2(1);
			}
			if(depthSliderHand != null){
				SliderHandler.getSliderAt(2).setValue(0);
				depthSliderHand.setValue(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouseSelection = false;
		for(int i = 0;i<Canvas.getNrDrawings();i++){
			Sprite x = Canvas.getDrawing(i);
			int a,b;
			a = (int)(x.getX() + x.getScaleW())/2;
			b = (int)(x.getY() + x.getScaleH())/2;
			int difAC, difBD, difAX,difBY;
			difAC = xMouseSelect - xMouseSelect2;
			difBD = yMouseSelect - yMouseSelect2;
			difAX = xMouseSelect - a;
			difBY = yMouseSelect - b;
			try{
				if((difAC>=0 && difAX>=0 || difAC<0 && difAX<0) && (difBD>=0 && difBY>=0 || difBD<0 && difBY<0)){
					System.out.println(difAC + " " + difBD + " " + difAX + " " + difBY);
					if( Math.abs(difAC) >= Math.abs(difAX) && Math.abs(difBD) >= Math.abs(difBY)){
						x.setSelected(true);
					}
				}
			}catch(ArithmeticException e){}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouse) {
		xMOUSE = mouse.getX();
		yMOUSE = mouse.getY();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(Canvas.getSelectedOne(0)!=null){
			for(int i = 0;i < Canvas.getNrSelected();i++){
				Canvas.getSelectedOne(i).setRotation(Canvas.getSelectedOne(i).getRotation() + (double)(e.getWheelRotation())/10);
				updateLabels();
			}
		}
	}
	
	
	//get/set functions
	
	public void setRotationSlider(SliderHandler newRotationSliderHand){
		rotationSliderHand = newRotationSliderHand;
	}
	public void setScaleSlider(SliderHandler newScaleSliderHand){
		scaleSliderHand = newScaleSliderHand;
	}
	public void setDepthSlider(SliderHandler newDepthSliderHand){
		depthSliderHand = newDepthSliderHand;
	}
	public SliderHandler getRotationSlider(){
		return rotationSliderHand;
	}
	public SliderHandler getScaleSlider(){
		return scaleSliderHand;
	}
	public SliderHandler getDepthSlider(){
		return depthSliderHand;
	}

	public static JLabel getSelectedName() {
		return selectedName;
	}

	public static void setSelectedName(JLabel selectedName) {
		DrawHandler.selectedName = selectedName;
	}

	public static JLabel getSelectedX() {
		return selectedX;
	}

	public static void setSelectedX(JLabel selectedX) {
		DrawHandler.selectedX = selectedX;
	}

	public static JLabel getSelectedY() {
		return selectedY;
	}

	public static void setSelectedY(JLabel selectedY) {
		DrawHandler.selectedY = selectedY;
	}

	public static JLabel getSelectedRotation() {
		return selectedRotation;
	}

	public static void setSelectedRotation(JLabel selectedRotation) {
		DrawHandler.selectedRotation = selectedRotation;
	}

	public static JLabel getSelectedScale() {
		return selectedScale;
	}

	public static void setSelectedScale(JLabel selectedScale) {
		DrawHandler.selectedScale = selectedScale;
	}

	public static JLabel getSelectedDepth() {
		return selectedDepth;
	}

	public static void setSelectedDepth(JLabel selectedDepth) {
		DrawHandler.selectedDepth = selectedDepth;
	}
	
	public static void updateLabels(){
		if(Canvas.getSelectedOne(0)!= null){
			selectedName.setText("Name: " + Canvas.getSelectedOne(0).getName());
			selectedX.setText("X: " + Canvas.getSelectedOne(0).getX());
			selectedY.setText("Y: " + Canvas.getSelectedOne(0).getY());
			selectedRotation.setText("Rotation: " + Canvas.getSelectedOne(0).getRotation());
			selectedScale.setText("Scale: " + Canvas.getSelectedOne(0).getScaleW() + " " + Canvas.getSelectedOne(0).getScaleH());
			selectedDepth.setText("Depth: " + Canvas.getSelectedOne(0).getDepth());
		}
	}

	public static JLabel getCurrentFrame() {
		return currentFrame;
	}

	public static void setCurrentFrame(JLabel currentFrame) {
		DrawHandler.currentFrame = currentFrame;
	}

	public static JLabel getCurrentFrameTime() {
		return currentFrameTime;
	}

	public static void setCurrentFrameTime(JLabel currentFrameTime) {
		DrawHandler.currentFrameTime = currentFrameTime;
	}
	public static boolean getMouseSelection(){
		return mouseSelection;
	}
	
	
}
