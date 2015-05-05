import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
	
	private String name;
	private Color bgColor;
	private int x,y;
	private double width,height;
	private BufferedImage image;
	private boolean selected;
	private double rotation;
	private double scaleW;
	private double scaleH;
	private int depth;
	private AffineTransform rotator;
	private AffineTransform mover;
	private AffineTransform scaler;
	public Sprite(){
		super();
		rotation = 0;
		scaleW = 1;
		scaleH = 1;
		depth = 0;
		setSelected(false);
	}
	public Sprite(Color c,int a,int b,int d,String path) throws IOException{
		bgColor = c;
		x = a;y = b; 
		name = path;
		loadImage(path);
		width = image.getWidth();
		height = image.getHeight();
		setSelected(false);
		rotation = 0;
		depth = d;
		scaleW = width;
		scaleH = height;
	}
	public void drawSprite(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.drawString(x+" "+y + " " + selected + " " + rotation + " " + scaleW + " & " + scaleH + " (" + depth + ")", x, y);
		mover = new AffineTransform();
		rotator = new AffineTransform();
		scaler = new AffineTransform();
		scaler.scale(scaleW/width, scaleH/height);
		mover.translate(x, y);
		rotator.translate(scaleW/2, scaleH/2);
		rotator.rotate(rotation);
		rotator.translate(-scaleW/2,-scaleH/2);
		mover.concatenate(rotator);
		mover.concatenate(scaler);
		g2d.drawImage(image,mover,null);
	}
	private void loadImage(String path) throws IOException{
		path = "Images\\" + path + ".png";
		image = ImageIO.read(new File(path));
	}
	
	//update functions
	public void update(){
	}
	
	//get/set functions
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	public void setRotation(double newRotation){
		rotation = newRotation;
	}
	public void setScaleW(double newScale){
		scaleW = newScale;
	}
	public void setScaleH(double newScale){
		scaleH = newScale;
	}
	public void setDepth(int newDepth){
		depth = newDepth;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return bgColor;
	}
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	public String getName(){
		return name;
	}
	public double getRotation(){
		return rotation;
	}
	public double getScaleW(){
		return scaleW;
	}
	public double getScaleH(){
		return scaleH;
	}
	public int getDepth(){
		return depth;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean s) {
		selected = s;
	}
	

}
