package imageSelector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.ButtonHandler;
import main.Canvas;

public class imageSelector extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JList<String> listImages;
	private  File imageFile;
	private  Vector<String> imagesNames = new Vector<String>();
	private  JButton addImg = new JButton("Add Image");
	private  JButton cancel = new JButton("Cancel");
	public imageSelector mainWindow;
	
	public imageSelector(){
		super("ImageSelector");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setFocusable(false);
		setSize(400, 400);
		mainWindow = this;
		try{
		setup();
		}catch(IOException e){ JOptionPane.showMessageDialog(null, "There is a problem with the file!");}
		setResizable(false);
		setVisible(true);
	}
	
	private void setup() throws IOException{
		imageFile = new File("images.in");
		if(!imageFile.exists()){
			imageFile.createNewFile();
		}
		FileReader reader = new FileReader(imageFile);
		BufferedReader bReader = new BufferedReader(reader);
		while(bReader.ready()){
			String s = bReader.readLine();
			imagesNames.addElement(s);
		}
		listImages = new JList<String>(imagesNames);
		this.add(listImages,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		Canvas drawPanel = new Canvas();
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
			}
		});
		addImg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String wanted  = listImages.getSelectedValue();
				System.out.println("Adding "+ wanted);
				ButtonHandler.setWantedImageName(wanted);
				mainWindow.dispose();
			}
			
		});
		buttonPanel.add(addImg);
		buttonPanel.add(cancel);
		this.add(buttonPanel,BorderLayout.SOUTH);
		this.add(drawPanel, BorderLayout.EAST);
		reader.close();
	}
}
