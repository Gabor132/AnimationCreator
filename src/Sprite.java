import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Sprite{
	
	
	private Color color;
	private int x,y;
	private int width,height;
	
	public Sprite(){
		super();
		
	}
	public Sprite(Color c,int a,int b,int w,int h){
		color = c;
		x = a;y = b; width = w; height = h;
	}
	public void drawSprite(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		g2d.fillOval(x,y,width,height);
	}
	
	//update functions
	public void update(){
		Random r = new Random();
		setX(r.nextInt(300));
		setY(r.nextInt(300));
	}
	
	//get/set functions
	public void setX(int a){
		x = a;
	}
	public void setY(int b){
		y = b;
	}
	public void setColor(Color c){
		color = c;
	}
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		height = h;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	

}
