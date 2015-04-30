import javax.swing.JFrame;


public class CreatorWindow extends JFrame{
	
	private int width, height;
	
	public CreatorWindow(int w,int h){
		super("Animation Creator");
		
		width = w;
		height = h;
		
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
}
