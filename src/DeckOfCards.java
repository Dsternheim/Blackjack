/*
 * David Sternheim
 * Card class for Blackjack
 * */
public class DeckOfCards {
	private int rank;
	private int suit;
	
	
	/*
	 * This is the default constructor for the DeckOfCards class
	 * @param rank
	 * 	the rank of the card i.e 1-King. The face cards will be represented by an integer value based on the rules of blackjack.
	 * @param suit
	 * 	the suit of the card
	 * 
	 * */
	public DeckOfCards(int rank, int suit){
		this.setRank(rank);
		this.setSuit(suit);
	}

	/*
	 * Getter for rank data member
	 * @return
	 * 	the rank of the card as an int
	 * */
	public int getRank() {
		return rank;
	}


	/*
	 * Setter for the rank data member
	 * @param newRank
	 * 	the new value for rank of the card
	 * */
	public void setRank(int newRank) {
		this.rank = newRank;
	}

	/*
	 * Getter for the suit data member
	 * @return 
	 * 	the suit of the card as an int
	 * */
	public int getSuit() {
		return suit;
	}

	/*
	 * Setter for the suit data member
	 * @param newSuit
	 * 	the new value for the suit of the card
	 * */
	public void setSuit(int newSuit) {
		this.suit = newSuit;
	}
	
}
