import java.util.ArrayList;

import javax.swing.event.*;

//stores the game data for a mancala board
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

	public void addChangeListener(ChangeListener c)
	{
		listeners.add(c);
	}

	public int getPitValue(Player player, int pit)
	{
		if (player == Player.ONE)
		{
			return playerOnePits[pit-1];
		}
		else return playerTwoPits[pit-1];
	}

	public int getMancalaValue(Player player)
	{
		if(player.equals(Player.ONE))
		{
			return playerOneMancala;
		}
		else
			return playerTwoMancala;
	}

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	//checks if game has ended
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
			return true;
		}
		else return false;
	}

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

	}

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

	//undo function
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

	//returns true if move ends in current player's mancala or no move was made
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
							playerOneMancala++;
							player = Player.TWO;
							pitNumber = -1;
							currentPits = playerTwoPits;
						}
						else
						{
							playerTwoMancala++;
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

				//if -1, then move ends in mancala
				if(pitNumber == -1)
				{
					madeMoveThisTurn = false;
					return true;
				}
				else return false;
			}
			else return true;
		}
		else return true;
	}

	public void notifyListeners()
	{
		for (ChangeListener c: listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}

	public void printOut()
	{
		for(int i = 0; i <6; i++)
		{
		System.out.print(i + ": " + playerOnePits[i] + " ");
		System.out.println(playerTwoPits[i]);
		}
	}

}
