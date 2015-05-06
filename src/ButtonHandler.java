import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;


public class ButtonHandler implements ActionListener{
	
	private static Canvas drawPanel;
	private boolean type;
	private boolean add;
	
	public ButtonHandler(boolean t,boolean a){
		super();
		type = t;
		add = a;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(type && add){
			Random r = new Random();
			String imageName;
			
			imageName = JOptionPane.showInputDialog("Image name");
			Sprite s;
			try {
				
				s = new Sprite(Color.getHSBColor(r.nextFloat(), r.nextFloat(), r.nextFloat())
						,drawPanel.getWidth()/2,drawPanel.getHeight()/2,Canvas.getNrDrawings(),imageName);
				
				drawPanel.pushDrawing(s);
				if(Canvas.getNrDrawings()>1)
					Canvas.getDrawPanelHand().getDepthSlider().getOwner().setMaximum(
							Canvas.getDrawPanelHand().getDepthSlider().getOwner().getMaximum()+1);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Image name is invalid!");
			}
		}
		else if(type && !add){
			String imageName;
			try{
				imageName = JOptionPane.showInputDialog("Image name");
				for(int i=0;i<Canvas.getNrDrawings();i++){
					if(imageName.equals(drawPanel.getDrawing(i).getName()))
						drawPanel.deleteDrawing(i);
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Something went wrong!");
				e.printStackTrace();
			}
		}
		else if(!type && add){
			
		}
		else{
			
		}
	}
	
	public static void setDrawPanel(Canvas d){
		drawPanel = d;
	}
}
