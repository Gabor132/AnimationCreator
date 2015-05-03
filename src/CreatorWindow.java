import javax.swing.*;


@SuppressWarnings("serial")
public class CreatorWindow extends JFrame{
	
	private int width, height;
	public CreatorWindow(int w,int h){
		super("Animation Creator");
		
		width = w;
		height = h;
		
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	
}
