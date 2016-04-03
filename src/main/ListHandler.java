package main;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListHandler implements ListSelectionListener {
	private static String[] listElements;
	private static int nrElements = 0;
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
	public static String[] getListElements() {
		return listElements;
	}
	public static void setListElements(String[] listElements, int nrElem) {
		ListHandler.listElements = listElements;
		nrElements = nrElem;
	}
	public static void addListElement(String element){
		listElements[nrElements] = element;
		nrElements++;
	}

}
