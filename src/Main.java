import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class Main {
	
	
	
	int width,height;
	public static void main(String[] args) {
		Sprite[] drawings = new Sprite[100];
		Frame[] album = new Frame[1000];
		String[] layerNames = new String[100];
		
		//Setting up the layerNames for the frame
		layerNames[0] = "Frame Layers";
		
								//Program setup//
		
		//Create window
		CreatorWindow window = new CreatorWindow(800,600);
		window.addKeyListener(new KeyHandler());
		//Create drawing panel
		Canvas drawPanel = new Canvas();
		KeyHandler.setDrawPanel(drawPanel);
		//Setting handler for canvas
		DrawHandler.setDrawPanel(drawPanel);
		DrawHandler drawPanelHand = new DrawHandler();
		
		drawPanel.addMouseListener(drawPanelHand);
		drawPanel.addMouseMotionListener(drawPanelHand);
		drawPanel.addMouseWheelListener(drawPanelHand);
		
		Canvas.setDrawPanelHand(drawPanelHand);
		
		
		//Create button, slider panel and properties panel
		JPanel imageBPanel = new JPanel(new GridLayout(1,2));
		JPanel frameBPanel = new JPanel(new GridLayout(2,4));
		JPanel buttonPanel = new JPanel(new GridLayout(1,6));
		JPanel headerPanel = new JPanel(new GridLayout(2,1));
		JPanel sliderPanel = new JPanel(new GridLayout(1,7));
		JPanel propPanel = new JPanel(new GridLayout(2,4));
		JPanel layerPanel = new JPanel(new GridLayout(1,1));
		
		//Create buttons
		JButton addImg = new JButton("Add Image");
		JButton addFrame = new JButton("Add Frame");
		JButton deleteImg = new JButton("Delete Image");
		JButton deleteFrame = new JButton("Delete Frame");
		JButton selectFrame = new JButton("Select Frame");
		JButton updateFrame = new JButton("Update Frame");
		JButton playFrames = new JButton("Play Frames");
		JButton stopFrames = new JButton("Stop Frames");
		JButton fillFrames = new JButton("Fill Frames");
		addImg.setFocusable(false);
		addFrame.setFocusable(false);
		deleteImg.setFocusable(false);
		deleteFrame.setFocusable(false);
		selectFrame.setFocusable(false);
		updateFrame.setFocusable(false);
		playFrames.setFocusable(false);
		stopFrames.setFocusable(false);
		updateFrame.setFocusable(false);
		fillFrames.setFocusable(false);
		
		//Setting handlers for buttons
		ButtonHandler.setDrawPanel(drawPanel);
		ButtonHandler.setAlbum(album);
		ButtonHandler buttonListener = new ButtonHandler();
		addImg.addActionListener(buttonListener);
		ButtonHandler.addButton(addImg);
		
		deleteImg.addActionListener(buttonListener);
		deleteImg.setEnabled(false);
		ButtonHandler.addButton(deleteImg);
		
		addFrame.addActionListener(buttonListener);
		ButtonHandler.addButton(addFrame);
		
		deleteFrame.addActionListener(buttonListener);
		deleteFrame.setEnabled(false);
		ButtonHandler.addButton(deleteFrame);
		
		selectFrame.addActionListener(buttonListener);
		selectFrame.setEnabled(false);
		ButtonHandler.addButton(selectFrame);
		
		updateFrame.addActionListener(buttonListener);
		updateFrame.setEnabled(false);
		ButtonHandler.addButton(updateFrame);
		
		playFrames.addActionListener(buttonListener);
		playFrames.setEnabled(false);
		ButtonHandler.addButton(playFrames);
		
		stopFrames.addActionListener(buttonListener);
		stopFrames.setEnabled(false);
		ButtonHandler.addButton(stopFrames);
		
		fillFrames.addActionListener(buttonListener);
		fillFrames.setEnabled(false);
		ButtonHandler.addButton(fillFrames);
		
		//List handler for the layers list
		ListHandler listHandler = new ListHandler();
		ListHandler.setListElements(layerNames,1);
		
		//Setting up the list
		JList<String> layerList = new JList<String>(layerNames);
		layerList.setEnabled(false);
		layerList.setFont(new Font("Verdun",20,15));
		
		layerList.addListSelectionListener(listHandler);
		layerPanel.add(layerList);
		
		//Create sliders
		JSlider rotationSlider = new JSlider(-6292,6292);
		rotationSlider.setValue(0);
		JSlider depthSlider = new JSlider(0,0);
		depthSlider.setValue(0);
		JSlider scaleSlider = new JSlider(1,1000);
		scaleSlider.setValue(1);
		
		
		//Setting handlers for sliders
		JSlider[] sliderList = new JSlider[3];
		SliderHandler rotationSliderHand = new SliderHandler();
		rotationSlider.addChangeListener(rotationSliderHand);
		rotationSlider.setName("Rotation");
		rotationSlider.setFocusable(false);
		SliderHandler scaleSliderHand = new SliderHandler();
		scaleSlider.addChangeListener(scaleSliderHand);
		scaleSlider.setName("Scale");
		scaleSlider.setFocusable(false);
		SliderHandler depthSliderHand = new SliderHandler();
		depthSlider.addChangeListener(depthSliderHand);
		depthSlider.setName("Depth");
		depthSlider.setFocusable(false);
		sliderList[0] = rotationSlider;
		sliderList[1] = scaleSlider;
		sliderList[2] = depthSlider;
		SliderHandler.setSliders(sliderList);

		//Connecting DrawHandler with slidersHandlers
		
		drawPanelHand.setRotationSlider(rotationSliderHand);
		drawPanelHand.setScaleSlider(scaleSliderHand);
		drawPanelHand.setDepthSlider(depthSliderHand);
		
		
		//Create labels
		JLabel rotationLabel = new JLabel("Rotation");
		rotationLabel.addMouseListener(drawPanelHand);
		
		JLabel depthLabel = new JLabel("Depth");
		JLabel scaleLabel = new JLabel("Scale");
		JLabel selectedName = new JLabel("Name: ");
		JLabel selectedX = new JLabel("X: ");
		JLabel selectedY = new JLabel("Y: ");
		JLabel selectedRotation = new JLabel("Rotation: ");
		JLabel selectedScale = new JLabel("Scale: ");
		JLabel selectedDepth = new JLabel("Depth: ");
		JLabel currentFrame = new JLabel("Current frame: ");
		JLabel currentFrameTime = new JLabel("Frame time: ");
		
		DrawHandler.setSelectedName(selectedName);
		DrawHandler.setSelectedX(selectedX);
		DrawHandler.setSelectedY(selectedY);
		DrawHandler.setSelectedRotation(selectedRotation);
		DrawHandler.setSelectedScale(selectedScale);
		DrawHandler.setSelectedDepth(selectedDepth);
		DrawHandler.setCurrentFrame(currentFrame);
		DrawHandler.setCurrentFrameTime(currentFrameTime);
		
		
		//Set buttonPanel
		imageBPanel.add(addImg);
		imageBPanel.add(deleteImg);
		frameBPanel.add(addFrame);
		frameBPanel.add(deleteFrame);
		frameBPanel.add(selectFrame);
		frameBPanel.add(updateFrame);
		frameBPanel.add(playFrames);
		frameBPanel.add(stopFrames);
		frameBPanel.add(fillFrames);
		buttonPanel.add(imageBPanel);
		buttonPanel.add(frameBPanel);
		headerPanel.add(buttonPanel);
		headerPanel.add(propPanel);
		//Set sliderPanel
		sliderPanel.add(rotationSlider);
		sliderPanel.add(rotationLabel);
		sliderPanel.add(depthSlider);
		sliderPanel.add(depthLabel);
		sliderPanel.add(scaleSlider);
		sliderPanel.add(scaleLabel);
		
		//Set propPanel
		propPanel.add(selectedName);
		propPanel.add(selectedX);
		propPanel.add(selectedY);
		propPanel.add(selectedRotation);
		propPanel.add(selectedScale);
		propPanel.add(selectedDepth);
		propPanel.add(currentFrame);
		propPanel.add(currentFrameTime);
		propPanel.addMouseListener(drawPanelHand);
		propPanel.setFocusable(false);
		
		
		
		//Set window
		window.add(headerPanel,BorderLayout.PAGE_START);
		window.add(layerPanel,BorderLayout.EAST);
		window.add(sliderPanel,BorderLayout.PAGE_END);
		window.add(drawPanel,BorderLayout.CENTER);
		window.setVisible(true);
									//Program start//
		
		
		Canvas.setDrawings(0, drawings);
		long pastTime = 0;
		int indexFrame = 0;
		while(true){
			for(int i=0;i<Canvas.getNrDrawings();i++){
				layerNames[i+1] = Canvas.getDrawing(i).getName();
			}
			layerList.setListData(layerNames);
			if(ButtonHandler.isPlay()){
				pastTime = pastTime + 1000;
				System.out.println(pastTime + "");
				if(ButtonHandler.getAlbum()[indexFrame].getTime() < pastTime){
					System.out.println("Change frame!");
					pastTime = pastTime%ButtonHandler.getAlbum()[indexFrame].getTime();
					if(indexFrame+1 >= Frame.getNrFrames())
						indexFrame = 0;
					else
						indexFrame++;
					System.out.println(indexFrame);
					Canvas.setDrawings(ButtonHandler.getAlbum()[indexFrame].getNrPictures(), ButtonHandler.getAlbum()[indexFrame].getPictures());
					
				}
				
			}
			drawPanel.repaint();
			try {
				if(ButtonHandler.isPlay())
					Thread.sleep(100);
				else
					Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
