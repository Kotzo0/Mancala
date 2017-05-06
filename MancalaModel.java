import java.util.ArrayList;

import javax.swing.event.*;

//stores the game data for a mancala board
/**
 * class that serves as model for a mancala game
 * @author Justin Benassi
 *
 */
public class MancalaModel {

	//pits for the respective players
	private int[] playerOnePits;
	private int[] playerTwoPits;

	//mancala pits for each player
	private int playerOneMancala;
	private int playerTwoMancala;

	private boolean madeMoveThisTurn;

	//for undo function
	private int[] previousOnePits;
	private int[] previousTwoPits;
	private int previousOneMancala;
	private int previousTwoMancala;
	private int oneUndos;
	private int twoUndos;
	private boolean undoThisTurn;
	private boolean firstMove;

	private Player currentPlayer;

	private ArrayList<ChangeListener> listeners;

	/**
	 * constructor for mancala model
	 * @param initialPitCount initial number of beads in the pits, should be > 0
	 */
	public MancalaModel(int initialPitCount)
	{
		playerOnePits = new int[6];
		playerTwoPits = new int[6];
		playerOneMancala = 0;
		playerTwoMancala = 0;

		for(int i = 0; i < playerOnePits.length; i++)
		{
			playerOnePits[i] = initialPitCount;
		}
		for(int i = 0; i < playerTwoPits.length; i++)
		{
			playerTwoPits[i] = initialPitCount;
		}

		previousOneMancala = playerOneMancala;
		previousOnePits = playerOnePits.clone();
		previousTwoMancala = playerTwoMancala;
		previousTwoPits = playerTwoPits.clone(); 

		currentPlayer = Player.ONE;
		undoThisTurn = false;
		madeMoveThisTurn = false;
		firstMove = false;

		oneUndos = 3;
		twoUndos = 3;

		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * add change listener to the list
	 * @param c the change listener to add
	 */
	public void addChangeListener(ChangeListener c)
	{
		listeners.add(c);
	}

	/**
	 * returns the pit value of a chosen pit
	 * @param player either player one or two
	 * @param pit chosen pit, 1 through 6
	 * @return the value of the selected pit
	 */
	public int getPitValue(Player player, int pit)
	{
		if (player == Player.ONE)
		{
			return playerOnePits[pit-1];
		}
		else return playerTwoPits[pit-1];
	}


	/**
	 * gets mancala value for respective player
	 * @param player the player whos mancala to return
	 * @return the value of a mancala
	 */
	public int getMancalaValue(Player player)
	{
		if(player.equals(Player.ONE))
		{
			return playerOneMancala;
		}
		else
			return playerTwoMancala;
	}

	/**
	 * returns current player
	 * @return the current player
	 */
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	/**
	 * checks of the game has ended (one side has 0 beads)
	 * @return true if the game has ended, else false
	 */
	public boolean checkGameOver()
	{
		int one = 0;
		int two = 0;
		for(int i = 0; i < playerOnePits.length; i++)
		{
			one += playerOnePits[i];
		}
		for(int i = 0; i < playerTwoPits.length; i++)
		{
			two += playerTwoPits[i];
		}
		if(one == 0 || two == 0)
		{
			playerOneMancala += one;
			playerTwoMancala += two;
			return true;
		}
		else return false;
	}

	/**
	 * switches the current player, effectively the end turn function. Resets undo counts.
	 */
	public void changeCurrentPlayer()
	{
		if(madeMoveThisTurn)
		{
			if(getCurrentPlayer() == Player.ONE)
			{
				currentPlayer = Player.TWO;
			}
			else currentPlayer = Player.ONE;
			madeMoveThisTurn = false;
			undoThisTurn = false;
		}
		oneUndos = 3;
		twoUndos = 3;

	}

	/**
	 * Used after a game has finished to determine who won
	 * @return returns player one if one won, two if two, or tie otherwise
	 */
	public Player declareWinner()
	{
		if (playerOneMancala > playerTwoMancala)
		{
			return Player.ONE;
		}
		else if (playerTwoMancala > playerOneMancala)
		{
			return Player.TWO;
		}
		else return Player.TIE;
	}

	/**
	 * Resets the board to the previous state if a move was made this turn, limit of 3 times a turn
	 */
	public void undo()
	{
		if (firstMove)
		{
			if (getCurrentPlayer() == Player.ONE)
			{
				if(oneUndos > 0 && !undoThisTurn)
				{
					oneUndos--;
					playerOneMancala = previousOneMancala;
					playerOnePits = previousOnePits.clone();
					playerTwoMancala = previousTwoMancala;
					playerTwoPits = previousTwoPits.clone();
					madeMoveThisTurn = false;
					notifyListeners();

				}
			}
			else if (getCurrentPlayer() == Player.TWO)
			{
				if(twoUndos > 0 && !undoThisTurn)
				{
					twoUndos--;
					playerOneMancala = previousOneMancala;
					playerOnePits = previousOnePits.clone();
					playerTwoMancala = previousTwoMancala;
					playerTwoPits = previousTwoPits.clone();
					madeMoveThisTurn = false;
					notifyListeners();
				}
			}

			undoThisTurn = true;
		}
	}

	/**
	 * major mancala function for moving the beads. notifies the listeners of the changes it makes
	 * @param player the current player
	 * @param pitNumber which pit the move starts in
	 * @return returns true if the move ends in a mancala or if the selected pit was empty, else false (end of turn)
	 */
	public boolean makeMove(Player player, int pitNumber)
	{
		if(!madeMoveThisTurn)
		{
			//make copy for undo function
			previousOneMancala = playerOneMancala;
			previousOnePits = playerOnePits.clone();
			previousTwoMancala = playerTwoMancala;
			previousTwoPits = playerTwoPits.clone();

			//make move
			int[] currentPits;
			if(player == Player.ONE)
			{
				currentPits = playerOnePits;
			}
			else currentPits = playerTwoPits;

			//collect beads out of pit
			int remaining = currentPits[pitNumber];
			if (remaining != 0)
			{
				currentPits[pitNumber] = 0;

				while (remaining > 0)
				{
					pitNumber++;
					//if outside pit range, bead goes to mancala
					if(pitNumber > 5)
					{
						if (player == Player.ONE)
						{
							if(getCurrentPlayer() == Player.ONE)
							{ playerOneMancala++; }
							else remaining++;
							player = Player.TWO;
							pitNumber = -1;
							currentPits = playerTwoPits;
						}
						else
						{
							if(getCurrentPlayer() == Player.TWO)
							{playerTwoMancala++;}
							else remaining++;
							player = Player.ONE;
							pitNumber = -1;
							currentPits = playerOnePits;
						}

					}
					else //otherwise bead goes into pit
					{
						currentPits[pitNumber] += 1;
					}
					remaining--;
				}

				//allows one move a turn
				madeMoveThisTurn = true;

				//notify change listeners
				notifyListeners();

				//first move check for undos
				firstMove = true;

				//allow for an undo
				undoThisTurn = false;

				//if -1, then move ends in mancala
				if(pitNumber == -1)
				{
					madeMoveThisTurn = false;
					return true;
				}
				else if(currentPlayer.equals(Player.ONE) && currentPits[pitNumber] == 1)
				{
					int total = 1 + playerTwoPits[5-pitNumber];
					playerOnePits[pitNumber] = 0;
					playerTwoPits[5-pitNumber] = 0;
					playerOneMancala += total;
					notifyListeners();
					return false;
				}
				else if(currentPlayer.equals(Player.TWO) && currentPits[pitNumber] == 1)
				{
					int total = 1 + playerOnePits[5-pitNumber];
					playerTwoPits[pitNumber] = 0;
					playerOnePits[5-pitNumber] = 0;
					playerOneMancala += total;
					notifyListeners();
					return false;
				}
				else return false;
			}
			else return true;
		}
		else return true;
	}

	/**
	 * notifies the change Listeners
	 */
	public void notifyListeners()
	{
		for (ChangeListener c: listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}

}
