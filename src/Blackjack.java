
/*
 * David Sternheim
 * Blackjack implementation in Java. This version of blackjack pits you vs AI.
 * The strategy options available right now are hit and stand but splitting maybe be included in a later version.
 * */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Blackjack {

	private static DeckOfCards[] deck = new DeckOfCards[52];
	private static ArrayList<DeckOfCards> currentUserHand = new ArrayList<DeckOfCards>();
	private static ArrayList<DeckOfCards> currentDealerHand = new ArrayList<DeckOfCards>();
	private static boolean[][] cardUsed = new boolean[4][13];
	private static int spotInDeck = 0;
	private static int currentUserScore = 0;
	private static int currentDealerScore = 0;

	public static void main(String[] args) {

		System.out.println(
				"Welcome to Blackjack. " + "This game will be played with you versus the AI who is the dealer");
		System.out.println("There will be no wagers so doubling and surrendering have been removed from the game.");
		System.out.println("As of the current version splitting hands is not part of the game.");

		boolean playAgain = true;
		currentDealerScore = 22;

		do {

			generateDeck();
			dealUserCards();
			dealDealerCards();

			performUserTurn();

			if (currentUserScore > 21) {

				System.out.println("You lose!");
				playAgain = playAgain();

			} else {

				performDealerTurn();

				if (currentDealerScore > 21) {

					System.out.println("You Win!");
					playAgain = playAgain();

				} else {

					determineWinner();
					playAgain = playAgain();

				}

			}

		} while (playAgain);
	}

	/*
	 * Generates a shuffled deck by creating random cards in randomized order
	 */
	public static void generateDeck() {

		for (int i = 0; i < cardUsed.length; i++) {

			for (int j = 0; j < cardUsed[i].length; j++) {

				cardUsed[i][j] = false;

			}

		}

		for (int i = 0; i < deck.length;) {

			boolean validCard = false;

			while (!validCard) {

				int rank = (int) (Math.random() * 13 + 1);
				int suit = (int) (Math.random() * 4);

				if (!cardUsed[suit][rank - 1]) {

					cardUsed[suit][rank - 1] = true;
					DeckOfCards newCard = new DeckOfCards(rank, suit);
					deck[i] = newCard;
					validCard = true;
					i++;

				}

			}

		}

	}

	/*
	 * Deals the user the initial 2 cards
	 */
	public static void dealUserCards() {

		System.out.println();
		System.out.println("Dealer deals you two cards. Take a look at your hand:");

		currentUserHand.add(deck[spotInDeck]);
		spotInDeck++;
		currentUserHand.add(deck[spotInDeck]);
		spotInDeck++;

		for (int i = 0; i < currentUserHand.size(); i++) {

			DeckOfCards temp = currentUserHand.get(i);

			if (temp.getRank() == 1 || temp.getRank() > 10) {

				System.out.println(rankToString(temp.getRank()) + " of " + suitToString(temp.getSuit()));

			} else {

				System.out.println(temp.getRank() + " of " + suitToString(temp.getSuit()));

			}

		}

	}

	/*
	 * Performs the user turn, continues until user exceeds total value of 21 or
	 * hits the stand command
	 */
	public static void performUserTurn() {

		Scanner input = new Scanner(System.in);

		int card1 = currentUserHand.get(0).getRank();
		int card2 = currentUserHand.get(1).getRank();

		if (card1 > 10) {

			card1 = 10;

		}

		if (card2 > 10) {

			card2 = 10;

		}

		currentUserScore = card1 + card2;

		if (currentUserScore <= 11 && card1 == 1) {

			currentUserScore += 10;

		}

		if (currentUserScore <= 11 && card2 == 1) {

			currentUserScore += 10;

		}
		
		
		System.out.println();
		System.out.println("It's your turn!");

		try {

			while (currentUserScore <= 21) {

				System.out.println("Selection Menu:");
				System.out.println("S) Stand");
				System.out.println("H) Hit");
				// TODO Option adding Splitting
				System.out.print("Select an option from the Selection Menu:");
				String response = input.next();

				if (response.toLowerCase().equals("s")) {

					break;

				} else if (response.toLowerCase().equals("h")) {

					spotInDeck++;
					DeckOfCards temp = deck[spotInDeck];
					currentUserHand.add(temp);
					System.out.println(
							"You just drew " + rankToString(temp.getRank()) + " of " + suitToString(temp.getSuit()));

					if (temp.getRank() > 10) {

						currentUserScore += 10;

					} else {

						if (currentUserScore < 11 && temp.getRank() == 1) {

							currentUserScore += 11;

						} else {

							currentUserScore += temp.getRank();

						}

					}

					System.out.println();

					printUserHand();

					System.out.println();

					if (currentUserScore == 21) {

						System.out.println("User has blackjack");
						break;

					}

					System.out.println("Current User Score is: " + currentUserScore);
					System.out.println();
				} else{
					
					System.out.println("Please enter a valid option from the selection menu.");
					
				}

			}

		} catch (InputMismatchException ex) {

			System.out.println("An error occured while inputting the response. Avoid spaces at the end of "
					+ "the response and please try to select an option from the selection menu.");

		}

	}

	/*
	 * Determines the correct suit of the int value of the suit passed in
	 * 
	 * @param suit the int value of the suit of the card
	 * 
	 * @return the name of the suit as a String
	 */
	public static String suitToString(int suit) {

		if (suit == 0) {

			return "Spades";

		} else if (suit == 1) {

			return "Hearts";

		} else if (suit == 2) {

			return "Clubs";

		} else if (suit == 3) {

			return "Diamonds";

		}

		return "";
	}

	/*
	 * Determines the correct value of the rank passed in
	 * 
	 * @param rank the int value of the rank of the card
	 * 
	 * @return the card rank as a String
	 */
	public static String rankToString(int rank) {

		if (rank == 1) {

			return "Ace";

		} else if (rank == 11) {

			return "Jack";

		} else if (rank == 12) {

			return "Queen";

		} else if (rank == 13) {

			return "King";

		} else {

			return "" + rank;

		}
	}

	/*
	 * Prints the current cards in the user's hand
	 */
	public static void printUserHand() {

		System.out.println("Currently you have these cards in your hand:");

		for (int i = 0; i < currentUserHand.size(); i++) {

			DeckOfCards temp = currentUserHand.get(i);
			System.out.println(rankToString(temp.getRank()) + " of " + suitToString(temp.getSuit()));

		}

	}

	/*
	 * Deals the dealer two cards, only revealing the first card drawn to the
	 * user.
	 */
	public static void dealDealerCards() {
		System.out.println();
		System.out.println("Dealer takes two cards!");

		currentDealerHand.add(deck[spotInDeck]);
		spotInDeck++;
		currentDealerHand.add(deck[spotInDeck]);
		spotInDeck++;

		System.out.println("Dealer's first card: ");
		System.out.println(rankToString(currentDealerHand.get(0).getRank()) + " of "
				+ suitToString(currentDealerHand.get(0).getSuit()));

	}

	/*
	 * Performs the dealer's turn
	 */
	public static void performDealerTurn() {
		System.out.println("It's the dealer's turn!");
		int card1 = currentDealerHand.get(0).getRank();
		int card2 = currentDealerHand.get(1).getRank();

		if (card1 > 10) {

			card1 = 10;

		}

		if (card2 > 10) {

			card2 = 10;

		}

		currentDealerScore = card1 + card2;

		if (card1 == 1 && currentDealerScore <= 11) {

			currentDealerScore += 10;

		}

		if (card2 == 1 && currentDealerScore <= 11) {

			currentDealerScore += 10;

		}

		System.out.println();

		boolean finished = false;

		while (!finished) {

			if (currentDealerScore > 18) {

				finished = true;

			} else {

				System.out.println("Dealer hits.");

				spotInDeck++;
				DeckOfCards temp = deck[spotInDeck];
				currentDealerHand.add(temp);
				if (temp.getRank() == 1 && currentDealerScore <= 11) {

					currentDealerScore += 10;

				} else if (temp.getRank() > 10) {

					currentDealerScore += 10;

				} else {

					currentDealerScore += temp.getRank();

				}

			}

		}

		System.out.println();
		System.out.println("Dealer's hand: ");

		for (int i = 0; i < currentDealerHand.size(); i++) {

			System.out.println(rankToString(currentDealerHand.get(i).getRank()) + " of "
					+ suitToString(currentDealerHand.get(i).getSuit()));

		}

		System.out.println();
		System.out.println("Current Dealer score is: " + currentDealerScore);

		if (currentDealerScore == 21) {

			System.out.println("Dealer has blackjack!");

		}

		System.out.println();

	}

	/*
	 * Determines the winner based on the scores of the user and the dealer
	 */
	public static void determineWinner() {

		if (currentUserScore == 21) {

			System.out.println("You Win!");
			return;

		} else if (currentDealerScore == 21) {

			System.out.println("You Lose!");
			return;

		}

		int userDifference = 21 - currentUserScore;
		int dealerDifference = 21 - currentDealerScore;

		if (userDifference < dealerDifference) {

			System.out.println("You Win!");

		} else if (userDifference == dealerDifference) {

			System.out.println("It's a tie!");

		} else {

			System.out.println("You Lose!");

		}

	}

	/*
	 * Prompts user if they wish to play again
	 * 
	 * @return true if the user enters 'y', false if the user enters 'n'
	 */
	public static boolean playAgain() {

		Scanner input = new Scanner(System.in);
		System.out.println();

		try {

			System.out.print("Would you like to play again?(y/n)");
			String response = input.next();

			if (response.toLowerCase().equals("y")) {

				spotInDeck = 0;
				currentUserHand.clear();
				currentDealerHand.clear();
				currentUserScore = 0;
				currentDealerScore = 0;

			} else if (response.toLowerCase().equals("n")) {

				System.out.println("Thanks for playing!");
				System.exit(0);

			} else {
				
				System.out.println("Please enter a y or n for the input.");
				playAgain();
				
			}

		} catch (InputMismatchException ex) {

			System.out.println("An error occurred while inputting the response."
					+ " Please avoid spaces and simply type a 'y' for yes or a 'n' for no.");
			playAgain();

		}

		return true;
	}
}
