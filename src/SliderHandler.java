import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class SliderHandler implements ChangeListener{
	
	private double value,value2;
	private JSlider owner;
	private int type;
	private boolean skipForDrawHandler;
	
	public SliderHandler(int t,JSlider js){
		if(t==1)
			value = js.getValue()/1000;
		else if(t==2){
			value = js.getValue();
			value2 = js.getValue();
		}else{
			value = js.getValue();
		}
		owner = js;
		type = t;
		skipForDrawHandler = false;
	}
	
	@Override
	public void stateChanged(ChangeEvent change) {
		double ownerValue = owner.getValue();
		
		if(!skipForDrawHandler){
			switch(type){
				case 1:{
					ownerValue /= 1000;
					if(Canvas.getSelectedOne()!=null)
						Canvas.getSelectedOne().setRotation(Canvas.getSelectedOne().getRotation() + ownerValue-value);
				}break;
				case 2:{
					if(Canvas.getSelectedOne()!=null){
						value2 = Canvas.getSelectedOne().getHeight()/Canvas.getSelectedOne().getWidth();
						Canvas.getSelectedOne().setScaleW(Canvas.getSelectedOne().getScaleW() + ownerValue-value);
						Canvas.getSelectedOne().setScaleH(Canvas.getSelectedOne().getScaleH() + (ownerValue-value)*value2);
					}
				}break;
				case 3:{
					if(Canvas.getSelectedOne()!=null){
						if(Canvas.getNrDrawings() > 1)
							Canvas.getSelectedOne().setDepth((int)(Canvas.getSelectedOne().getDepth() + ownerValue-value));
					}
				}break;
			}
		}
		else
			skipForDrawHandler = false;
		value = ownerValue;
	}
	
	public JSlider getOwner(){
		return owner;
	}
	public void setValue(double newValue){
		value = newValue;
	}
	public void setValue2(double newValue){
		value2 = newValue;
	}
	public void skipThis(){
		skipForDrawHandler = true;
	}
	
}
