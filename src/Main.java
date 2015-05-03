import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class Main {
	
	
	
	int width,height;
	public static void main(String[] args) {
		Sprite[] drawings = new Sprite[100];
		
		
								//Program setup//
		
		//Create window
		CreatorWindow window = new CreatorWindow(800,600);
		//Create drawing panel
		Canvas drawPanel = new Canvas();
		
		
		//Create & set sliderLayout
		GridLayout sliderLayout = new GridLayout();
		sliderLayout.setColumns(6);
		sliderLayout.setRows(1);
		
		//Create & set buttonLayout 
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.setColumns(4);
		
		//Create button and slider panel
		JPanel buttonPanel = new JPanel(buttonLayout);
		JPanel sliderPanel = new JPanel(sliderLayout);
		
		//Create buttons
		JButton addImg = new JButton("Add Image");
		JButton addFrame = new JButton("Add Frame");
		JButton deleteImg = new JButton("Delete Image");
		JButton deleteFrame = new JButton("Delete Frame");
		
		//Setting handlers for buttons
		ButtonHandler.setDrawPanel(drawPanel);
		ButtonHandler addImgHand = new ButtonHandler(true);
		addImg.addMouseListener(addImgHand);
		
		
		
		//Create sliders
		JSlider rotationSlider = new JSlider(0,100);
		JSlider depthSlider = new JSlider(0,1);
		JSlider timeSlider = new JSlider(1,10);
		
		//Create labels
		JLabel rotationLabel = new JLabel("Rotation");
		JLabel depthLabel = new JLabel("Depth");
		JLabel timeLabel = new JLabel("Time");
		
		//Set buttonPanel
		buttonPanel.add(addImg);
		buttonPanel.add(addFrame);
		buttonPanel.add(deleteImg);
		buttonPanel.add(deleteFrame);
		
		//Set sliderPanel
		sliderPanel.add(rotationLabel);
		sliderPanel.add(rotationSlider);
		sliderPanel.add(depthLabel);
		sliderPanel.add(depthSlider);
		sliderPanel.add(timeLabel);
		sliderPanel.add(timeSlider);
		
		//Set window
		window.add(buttonPanel,BorderLayout.PAGE_START);
		window.add(sliderPanel,BorderLayout.PAGE_END);
		window.add(drawPanel,BorderLayout.CENTER);
		window.setVisible(true);
		
									//Program start//
		
		drawPanel.setDrawings(0, drawings);
		Random r = new Random();
		while(true){
			drawPanel.repaint();
			try {
				Thread.sleep(r.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
