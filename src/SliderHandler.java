import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class SliderHandler implements ChangeListener{
	
	private double value,value2;
	private boolean skipForDrawHandler;
	private static JSlider[] sliders;
	private static int nrSliders = 0;
	
	
	
	public SliderHandler(){
		value = 0;
		value2 = 0;
		skipForDrawHandler = false;
	}
	
	@Override
	public void stateChanged(ChangeEvent change) {
		JSlider subject = new JSlider();
		if(!skipForDrawHandler){
			for(int i=0;i<nrSliders;i++){
				subject = (JSlider) change.getSource();
				if(subject == sliders[i]){
					i = nrSliders;
				}
			}
			if(subject.getName() == "Rotation"){
				double ownerValue = subject.getValue();
				ownerValue /= 1000;
				if(Canvas.getSelectedOne(0)!=null){
					for(int i = 0;i < Canvas.getNrSelected();i++)
						Canvas.getSelectedOne(i).setRotation(Canvas.getSelectedOne(i).getRotation() + (ownerValue-value));
				}
			}else if(subject.getName() == "Scale"){
				double ownerValue = subject.getValue();
				if(Canvas.getSelectedOne(0)!=null){
					for(int i = 0;i < Canvas.getNrSelected();i++){
						value2 = Canvas.getSelectedOne(i).getHeight()/Canvas.getSelectedOne(i).getWidth();
						Canvas.getSelectedOne(i).setScaleW(Math.round(Canvas.getSelectedOne(i).getScaleW() + (ownerValue-value)));
						Canvas.getSelectedOne(i).setScaleH(Math.round(Canvas.getSelectedOne(i).getScaleH() + (ownerValue-value)*value2));
					}
				}
			}else if(subject.getName() == "Depth"){
				double ownerValue = subject.getValue();
				if(Canvas.getSelectedOne(0)!=null && Canvas.getNrSelected() == 1){
					if(Canvas.getNrDrawings() > 1){
						int actualDepth = Canvas.getSelectedOne(0).getDepth();
						Sprite tempImg = Canvas.getSelectedOne(0);
						Canvas.getSelectedOne(0).setDepth((int)(Canvas.getSelectedOne(0).getDepth() + ownerValue-value));
						int wantedDepth = Canvas.getSelectedOne(0).getDepth();
						int j;
						if(actualDepth> wantedDepth){
							for(j = actualDepth;j>wantedDepth;j--){
								Canvas.setDrawing(Canvas.getDrawing(j-1), j);
								Canvas.getDrawing(j).setDepth(j);
							}
							Canvas.setDrawing(tempImg, j);
						}
						else{
							for(j = actualDepth;j<wantedDepth;j++){
								Canvas.setDrawing(Canvas.getDrawing(j+1), j);
								Canvas.getDrawing(j).setDepth(j);
							}
							Canvas.setDrawing(tempImg, j);
						}
					}
				}
			}
			DrawHandler.updateLabels();
		}else{
			skipForDrawHandler = false;
		}
		if(subject != null){
			double ownerValue = subject.getValue();
			value = ownerValue;
		}
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

	public static JSlider[] getSliders() {
		return sliders;
	}

	public static void setSliders(JSlider[] sliders) {
		SliderHandler.sliders = sliders;
		nrSliders = sliders.length;
	}
	public static void addSlider(JSlider slider){
		sliders[nrSliders] = slider;
		nrSliders++;
	}
	public static JSlider getSliderAt(int index){
		if(index<nrSliders){
			return sliders[index];
		}
		return null;
	}
}
