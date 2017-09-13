/*
 * David Sternheim
 * Class containing the hand of the human player
 * */
import java.util.ArrayList;

public class userHand {
	private ArrayList<DeckOfCards> currentHand;
	
	/*
	 * Default constructor for the userHand class
	 * @param currentHand
	 * 	The current hand the user has
	 * */
	public userHand(ArrayList<DeckOfCards> currentHand){
		this.setCurrentHand(currentHand);
	}

	/*
	 * Getter for the currentHand data member
	 * @return 
	 * 	The currentHand of the user as an ArrayList of DeckOfCards
	 * */
	public ArrayList<DeckOfCards> getCurrentHand() {
		return currentHand;
	}

	/*
	 * Setter for the currentHand data member
	 * @param newCurrentHand
	 * 	The new ArrayList that changes the old currentHand to a new currentHand
	 * */
	public void setCurrentHand(ArrayList<DeckOfCards> newCurrentHand) {
		this.currentHand = newCurrentHand;
	}
	
	
}
