import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ButtonHandler implements ActionListener{
	
	private static Canvas drawPanel;
	private static Frame[] album;
	private static Frame selectedFrame = null;
	private Sprite tempSprite;
	private Frame tempFrame;
	private static JButton[] buttonList = new JButton[9];
	private static int nrButton = 0;
	private static boolean play = false;
	
	public ButtonHandler(){
		super();
	}
	
	public static void addButton(JButton b){
		buttonList[nrButton] = b;
		nrButton++;
	}
	
	@Override
	public void actionPerformed(ActionEvent button) {
		//add image
		if(button.getSource().equals(buttonList[0])){
			Random r = new Random();
			String imageName;
			imageName = JOptionPane.showInputDialog("Image name");
			Sprite s;
			try {
				
				s = new Sprite(Color.getHSBColor(r.nextFloat(), r.nextFloat(), r.nextFloat())
						,drawPanel.getWidth()/2,drawPanel.getHeight()/2,Canvas.getNrDrawings(),imageName);
				
				Canvas.pushDrawing(s);
				ListHandler.addListElement(s.getName());
				if(Canvas.getNrDrawings()>1)
					SliderHandler.getSliderAt(2).setMaximum(
							SliderHandler.getSliderAt(2).getMaximum()+1);
				buttonList[1].setEnabled(true);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Image name is invalid!");
			}
		}// delete image
		else if(button.getSource().equals(buttonList[1])){
			String imageName;
			try{
				if(Canvas.getNrSelected()!=1){
					imageName = JOptionPane.showInputDialog("Image name");
					if(imageName!=null){
						for(int i=0;i<Canvas.getNrDrawings();i++){
							if(imageName.equals(Canvas.getDrawing(i).getName()))
								Canvas.deleteDrawing(i);
						}
					}
				}else{
					imageName = Canvas.getSelectedOne(0).getName();
					for(int i=0;i<Canvas.getNrDrawings();i++){
						if(imageName.equals(Canvas.getDrawing(i).getName()))
							Canvas.deleteDrawing(i);
					}
				}
				if(Canvas.getNrDrawings()==0)
					buttonList[1].setEnabled(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Something went wrong!");
				e.printStackTrace();
			}
		}//add frame
		else if(button.getSource().equals(buttonList[2])){
			try{
				long time = Long.parseLong(JOptionPane.showInputDialog("Frame time (s)?"));
				time *=1000;
				tempFrame = new Frame(Frame.getNrFrames());
				tempFrame.setTime(time);
				Sprite tempSprite;
				for(int i = 0;i<Canvas.getNrDrawings();i++){
					tempSprite = new Sprite();
					tempSprite.copySprite(Canvas.getDrawing(i));
					tempFrame.pushPicture(tempSprite);
				}
				album[Frame.getNrFrames()] = tempFrame;
				selectedFrame = tempFrame;
				Frame.setNrFrames(Frame.getNrFrames()+1);
				DrawHandler.getCurrentFrame().setText("Current frame: " + Frame.getNrFrames());
				DrawHandler.getCurrentFrameTime().setText("Frame time: ");
				buttonList[3].setEnabled(true);
				Canvas.setSelectedOne(null,0);
				if(Frame.getNrFrames()>1){
					buttonList[4].setEnabled(true);
					buttonList[6].setEnabled(true);
					buttonList[8].setEnabled(true);
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Invalid time value!");
			}
			
		}//delete frame
		else if(button.getSource().equals(buttonList[3])){
			int frameNr;
			if(Frame.getNrFrames()>0){
				try{
					frameNr = Integer.parseInt(JOptionPane.showInputDialog("Frame number (0 - "+(Frame.getNrFrames()-1)+")?"));
					for(int i=frameNr;i<Frame.getNrFrames()-1;i++){
						album[i] = album[i+1];
					}
					if(frameNr>0 && frameNr<Frame.getNrFrames()-1){
						Frame f = album[frameNr];
						selectedFrame = f;
						
						Canvas.setNrDrawings(0);
						for(int i=0;i<f.getNrPictures();i++){
							tempSprite = new Sprite();
							tempSprite.copySprite(f.getPicture(i));
							Canvas.pushDrawing(tempSprite);
						}
						DrawHandler.getCurrentFrame().setText("Current frame: " + frameNr);
						DrawHandler.getCurrentFrameTime().setText("Frame time: " + f.getTime());
					}else{
						selectedFrame = null;
						Canvas.setNrDrawings(0);
						DrawHandler.getCurrentFrame().setText("Current frame: " + 0);
						DrawHandler.getCurrentFrameTime().setText("Frame time: " + 0);
						buttonList[5].setEnabled(false);
					}
					if(frameNr <=Frame.getNrFrames()-1){
						if(Frame.getNrFrames()-1>0){
							Frame.setNrFrames(Frame.getNrFrames()-1);
							DrawHandler.getCurrentFrame().setText("Current frame: " + Frame.getNrFrames());
							DrawHandler.getCurrentFrameTime().setText("Frame time: ");
							if(Frame.getNrFrames()<2)
								buttonList[4].setEnabled(false);
						}
						else{
							Frame.setNrFrames(Frame.getNrFrames()-1);
							DrawHandler.getCurrentFrame().setText("Current frame: " + Frame.getNrFrames());
							DrawHandler.getCurrentFrameTime().setText("Frame time: ");
							buttonList[3].setEnabled(false);
						}
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Invalid frame number!");
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Empty album!");
		}//select frame
		else if(button.getSource().equals(buttonList[4])){
			int wantedFrame = -1;
			if(Frame.getNrFrames()>0){
				try{
					wantedFrame = Integer.parseInt(JOptionPane.showInputDialog(
						"What frame (from 0 to "+ (Frame.getNrFrames()-1) +")?"));
					
					if(wantedFrame>=0 && wantedFrame<Frame.getNrFrames()){
						Frame f = album[wantedFrame];
						selectedFrame = f;
						
						Canvas.setNrDrawings(0);
						for(int i=0;i<f.getNrPictures();i++){
							tempSprite = new Sprite();
							tempSprite.copySprite(f.getPicture(i));
							Canvas.pushDrawing(tempSprite);
						}
						DrawHandler.getCurrentFrame().setText("Current frame: " + wantedFrame);
						DrawHandler.getCurrentFrameTime().setText("Frame time: " + f.getTime());
						buttonList[5].setEnabled(true);
					}else
						JOptionPane.showMessageDialog(null,"Not a valid frame number!");
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Not a valid frame number!");
				}
			}else
				JOptionPane.showMessageDialog(null, "Empty album!");
		}//update frame
		else if (button.getSource().equals(buttonList[5])) {
			try{
				
				if(selectedFrame != null){
					Canvas.setNrDrawings(0);
					for(int i =0;i<Canvas.getNrDrawings();i++){
						tempSprite = new Sprite();
						tempSprite.copySprite(selectedFrame.getPicture(i));
						Canvas.pushDrawing(tempSprite);
					}
					long time = Long.parseLong(JOptionPane.showInputDialog("Frame time (ms)?"));
					selectedFrame.setTime(time);
					DrawHandler.getCurrentFrameTime().setText("Frame time: " + selectedFrame.getTime());
				}
				else
					JOptionPane.showMessageDialog(null, "Empty album!");
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Not a valid frame number!");
			}
		}
		//play frame
		else if(button.getSource().equals(buttonList[6])){
			buttonList[7].setEnabled(true);
			buttonList[6].setEnabled(false);
			setPlay(true);
		}
		//stop frame
		else if(button.getSource().equals(buttonList[7])){
			buttonList[6].setEnabled(true);
			buttonList[7].setEnabled(false);
			setPlay(false);
		}
		//fill frame
		else if(button.getSource().equals(buttonList[8])){
			int a,b,nrFrames;
			try{
				a = Integer.parseInt(JOptionPane.showInputDialog("From what frame?"));
				b = Integer.parseInt(JOptionPane.showInputDialog("To what frame?"));
				nrFrames = Integer.parseInt(JOptionPane.showInputDialog("How many frames?"));
				if(a<0 || b>=Frame.getNrFrames()) 
					throw new Exception();
				else{
					Frame firstFrame = album[a];
					Frame lastFrame = album[b];
					try{
						if(firstFrame.getNrPictures() != lastFrame.getNrPictures()){
							throw new Exception();
						}else{
							for(int i = 0;i<firstFrame.getNrPictures();i++){
								boolean found = false;
								System.out.println(firstFrame.getPicture(i).getName() + " " + lastFrame.getPicture(0).getName());
								for(int j = 0;j<lastFrame.getNrPictures();j++){
									if(firstFrame.getPicture(i).getName().equals(lastFrame.getPicture(j).getName()) && i == j){
										System.out.println("WOOP WOOP");
										found = true;
										j = lastFrame.getNrPictures();
									}
								}
								if(!found)
									throw new Exception();
							}
							
							Frame[] albumTemp = new Frame[nrFrames];
							Sprite tempImg;
							System.out.println("Nr frames to be added = " + nrFrames + " between frame "+ a +" and frame "+b);
							for(int z = 0; z<nrFrames;z++){
								albumTemp[z] = new Frame();
								for(int i = 0;i<firstFrame.getNrPictures();i++){
									Sprite img1 = firstFrame.getPicture(i);
									Sprite img2 = lastFrame.getPicture(i);
									int tempX,tempY,tempD;
									double tempR;
									tempX = img1.getX() + ((img2.getX() - img1.getX())/nrFrames)*(z+1);
									tempY = img1.getY() + ((img2.getY() - img1.getY())/nrFrames)*(z+1);
									tempR = img1.getRotation() + ((img2.getRotation() - img1.getRotation())/nrFrames)*(z+1);
									tempD = i;
									tempImg = new Sprite(img1.getColor(),tempX,tempY,tempD,img1.getName());
									tempImg.setRotation(tempR);
									albumTemp[z].pushPicture(tempImg);
									albumTemp[z].setTime(1);
								}
								System.out.println("ADDED FRAME TO temporaryAlbum");
							}
							for(int i = Frame.getNrFrames()-1;i>=b;i--){
								album[i+nrFrames] = album[i];
								System.out.println("Moved frame from " + i + " to " + (i+nrFrames));
							}
							int j = 0;
							Frame.setNrFrames(Frame.getNrFrames()+nrFrames);
							try{
								for(int i = a+1;i<=a+nrFrames;i++){
									album[i] = albumTemp[j];
									System.out.println("Copied frame from album to temporary album at index " + i + " from index " + j);
									j++;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								System.out.println("Tried to acces albumTemp with index "+ j);
								e.printStackTrace();
							}
							
						}
					}catch(Exception x){
						JOptionPane.showMessageDialog(null, "Frames are different!");
						x.printStackTrace();
					}
				}
					
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Invalid interval!");
			}
		}
	}
	
	public static void setDrawPanel(Canvas d){
		drawPanel = d;
	}

	public static Frame[] getAlbum() {
		return album;
	}

	public static void setAlbum(Frame[] a) {
		album = a;
	}

	public static boolean isPlay() {
		return play;
	}

	public static void setPlay(boolean play) {
		ButtonHandler.play = play;
	}
}
