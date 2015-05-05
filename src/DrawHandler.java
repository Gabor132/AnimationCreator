import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;


public class DrawHandler implements MouseInputListener{

	private static Canvas drawPanel;
	private int xMOUSE;
	private int yMOUSE;
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
		
	}

	public void mouseDragged(MouseEvent mouse) {
		
		//System.out.println("Trying to drag...");
		
		if(Canvas.getSelectedOne() != null){
			
			//System.out.println("DRAGGING " + selectedOne.getName());
			
			int difX = xMOUSE - mouse.getX();
			int difY = yMOUSE - mouse.getY();
			
			//System.out.println("DRAGGING " + selectedOne.getName() + " from (" + xMOUSE + "," + yMOUSE + ") to ("
			//+ mouse.getX()+","+mouse.getY() + ")");
			
			Canvas.getSelectedOne().setX(Canvas.getSelectedOne().getX() - difX);
			Canvas.getSelectedOne().setY(Canvas.getSelectedOne().getY() - difY);
			Canvas.getSelectedOne().drawSprite(drawPanel.getGraphics());
			xMOUSE = mouse.getX();
			yMOUSE = mouse.getY();
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
		for(i = 0;i<Canvas.getNrDrawings();i++){
			Sprite drawing = drawPanel.getDrawing(i);
			int xDrawing = drawing.getX();
			int yDrawing = drawing.getY();
			double widthDrawing = drawing.getWidth()+drawing.getScaleW()/drawing.getWidth();
			double heightDrawing = drawing.getHeight()+drawing.getScaleH()/drawing.getHeight();
			if(xMOUSE>=xDrawing && xMOUSE<xDrawing+widthDrawing && yMOUSE>=yDrawing && yMOUSE<yDrawing+heightDrawing){
				drawing.setSelected(true);
				Canvas.setSelectedOne(drawing);
				if(rotationSliderHand != null){
					rotationSliderHand.skipThis();
					rotationSliderHand.getOwner().setValue((int) (drawing.getRotation()*1000));
					rotationSliderHand.setValue((drawing.getRotation()));
				}
				if(scaleSliderHand != null){
					scaleSliderHand.skipThis();
					scaleSliderHand.getOwner().setValue((int) (drawing.getScaleW()));
					scaleSliderHand.setValue(drawing.getScaleW());
				}
				if(depthSliderHand != null){
					scaleSliderHand.skipThis();
					depthSliderHand.getOwner().setValue(0);
					depthSliderHand.setValue(0);
					scaleSliderHand.skipThis();
					depthSliderHand.getOwner().setValue(drawing.getDepth());
					depthSliderHand.setValue(drawing.getDepth());
				}
				break;
			}
			else
				drawing.setSelected(false);
		}
		if(i==Canvas.getNrDrawings()){
			Canvas.setSelectedOne(null);
			if(rotationSliderHand != null){
				rotationSliderHand.getOwner().setValue(0);
				rotationSliderHand.setValue(0);
			}
			if(scaleSliderHand != null){
				scaleSliderHand.getOwner().setValue(1);
				scaleSliderHand.setValue(1);
				scaleSliderHand.setValue2(1);
			}
			if(depthSliderHand != null){
				depthSliderHand.getOwner().setValue(0);
				depthSliderHand.setValue(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent mouse) {
		xMOUSE = mouse.getX();
		yMOUSE = mouse.getY();
	}
	
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

}
