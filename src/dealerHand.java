/*
 * David Sternheim
 * Class for the dealer's hand
 * */
import java.util.ArrayList;

public class dealerHand {
	private ArrayList<DeckOfCards> currentHand;
	
	/*
	 * Default constructor for the dealerHand class
	 * @param currentHand
	 * 	The current hand for the dealer
	 * */
	public dealerHand(ArrayList<DeckOfCards> currentHand){
		this.setCurrentHand(currentHand);
	}

	/*
	 * Getter for the currentHand data member
	 * @return 
	 * 	The currentHand of the dealer as a DeckofCards ArrayList
	 * */
	public ArrayList<DeckOfCards> getCurrentHand() {
		return currentHand;
	}

	/*
	 * Setter for the currentHand data member
	 * @param newCurrentHand
	 * 	The new currentHand that will replace the old currentHand ArrayList
	 * */
	public void setCurrentHand(ArrayList<DeckOfCards> newCurrentHand) {
		this.currentHand = newCurrentHand;
	}
	
	
}
