package main;
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
		name = "NULL";
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
	public void copySprite(Sprite s){
		image = s.image;
		rotation = s.rotation;
		scaleW = s.scaleW;
		scaleH = s.scaleH;
		x = s.x;
		y = s.y;
		depth = s.depth;
		width = s.width;
		height = s.height;
		name = s.name;
	}
	public void drawSprite(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
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
		if(this.getSelected()){
			//draw selected image
			g2d.setTransform(mover);
			//g2d.drawString(this.getName()+"is selected", 0, 0);
			/*
			g2d.drawLine(0, 0, -1000, 0);
			g2d.drawLine(0, 0, 0,-1000);
			g2d.drawLine(0, 0, 1000, 0);
			g2d.drawLine(0, 0, 0,1000);
			g2d.drawLine(1000, (int)(scaleH/height + scaleH), -1000, (int)(scaleH/height + scaleH));
			g2d.drawLine((int)(scaleW/width +scaleW), 1000, (int)(scaleW/width +scaleW), -1000);
			*/
			g2d.setColor(Color.orange);
			g2d.drawRect(0, 0, (int)scaleW, (int)scaleH);
			g2d.drawImage(image,0,0,null);
			g2d.fillArc((int)(scaleW/2)-5, (int)(scaleH/2)-5, 10, 10, 0, 360);
			g2d.drawArc((int)(scaleW/2)-7, (int)(scaleH/2)-7, 14, 14, 0, 360);
			g2d.drawLine((int)(scaleW/2), (int)(scaleH/2)-10, (int)(scaleW/2), (int)(scaleH/2)+10);
			g2d.drawLine((int)(scaleW/2)-10, (int)(scaleH/2), (int)(scaleW/2)+10, (int)(scaleH/2));
			
		}else{
			g2d.setTransform(mover);
			g2d.drawImage(image,0,0,null);
		}
		rotator.translate(-scaleW/2,-scaleH/2);
		rotator.rotate(-rotation);
		rotator.translate(scaleW/2, scaleH/2);
		mover.translate(-x, -y);
		scaler.scale(scaleW/width, scaleH/height);
		mover.concatenate(rotator);
		mover.concatenate(scaler);
		g2d.setTransform(mover);
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
