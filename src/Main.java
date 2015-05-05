import java.awt.BorderLayout;
import java.awt.GridLayout;
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
		
		//Setting handler for canvas
		DrawHandler.setDrawPanel(drawPanel);
		DrawHandler drawPanelHand = new DrawHandler();
		drawPanel.addMouseListener(drawPanelHand);
		drawPanel.addMouseMotionListener(drawPanelHand);
		Canvas.setDrawPanelHand(drawPanelHand);
		
		
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
		ButtonHandler addImgHand = new ButtonHandler(true,true);
		addImg.addActionListener(addImgHand);
		
		ButtonHandler deleteImgHand = new ButtonHandler(true, false);
		deleteImg.addActionListener(deleteImgHand);
		
		
		
		//Create sliders
		JSlider rotationSlider = new JSlider(-6292,6292);
		rotationSlider.setValue(0);
		JSlider depthSlider = new JSlider(0,0);
		depthSlider.setValue(0);
		JSlider scaleSlider = new JSlider(1,1000);
		scaleSlider.setValue(1);
		
		
		//Setting handlers for sliders
		SliderHandler rotationSliderHand = new SliderHandler(1,rotationSlider);
		rotationSlider.addChangeListener(rotationSliderHand);
		SliderHandler scaleSliderHand = new SliderHandler(2, scaleSlider);
		scaleSlider.addChangeListener(scaleSliderHand);
		SliderHandler depthSliderHand = new SliderHandler(3,depthSlider);
		depthSlider.addChangeListener(depthSliderHand);

		//Connecting DrawHandler with slidersHandlers
		drawPanelHand.setRotationSlider(rotationSliderHand);
		drawPanelHand.setScaleSlider(scaleSliderHand);
		drawPanelHand.setDepthSlider(depthSliderHand);
		
		//Create labels
		JLabel rotationLabel = new JLabel("Rotation");
		JLabel depthLabel = new JLabel("Depth");
		JLabel scaleLabel = new JLabel("Scale");
		
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
		sliderPanel.add(scaleLabel);
		sliderPanel.add(scaleSlider);
		
		//Set window
		window.add(buttonPanel,BorderLayout.PAGE_START);
		window.add(sliderPanel,BorderLayout.PAGE_END);
		window.add(drawPanel,BorderLayout.CENTER);
		window.setVisible(true);
		
									//Program start//
		
		
		drawPanel.setDrawings(0, drawings);
		while(true){
			drawPanel.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
