package main;

public class Frame {
	
	private Sprite[] images;
	private long time;
	private int index;
	private int nrImages;
	private static int nrFrames = 0;
	
	public Frame(){
		setIndex(nrFrames);
		nrImages = 0;
		images = new Sprite[100];
	}
	
	public Frame(int i){
		setIndex(i);
		nrImages = 0;
		images = new Sprite[100];
	}
	
	public static void setNrFrames(int i){
		if(i>=0 && i<100)
			nrFrames = i;
	}
	public static int getNrFrames(){
		return nrFrames;
	}
	public void pushPicture(Sprite s){
		if(nrImages<100){
			images[nrImages] = s;
			nrImages++;
		}
	}
	public Sprite popPicture(){
		if(nrImages>=0){
			nrImages--;
			return images[nrImages+1];
		}
		return null;
	}
	public Sprite peekPicture(){
		if(nrImages>=0)
			return images[nrImages];
		return null;
	}
	public Sprite getPicture(int index){
		if(index>=0 && index<nrImages)
			return images[index];
		return null;
	}
	public Sprite[] getPictures() {
		return images;
	}
	public void setPictures(Sprite[] picture, int nr) {
		images = picture;
		nrImages = nr;
	}
	public void setNrPictures(int i){
		if(i>=0 && i<100)
			nrImages = i;
	}
	public int getNrPictures(){
		return nrImages;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}
	
	
	public void printFrame(){
		for(int i=0;i<nrImages;i++){
			System.out.println(images[i].getName());
		}
	}
}
