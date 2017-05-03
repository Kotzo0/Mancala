import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MancalaTest {

	private int startingBeads = 0;
	private BoardStyle style;
	private MancalaModel model;

	public static void main(String[] args) {

		MancalaTest test = new MancalaTest();

		JFrame mainFrame = new JFrame();
		mainFrame.setMinimumSize(new Dimension(1100,600));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		JPanel board = new JPanel(new BorderLayout());

		GridLayout g = new GridLayout(2, 5);

		JPanel pits = new JPanel(g);


		//create mancala for both players
		MancalaIcon m1 = new MancalaIcon(20,200, "Player A",Player.ONE,test.model);
		JLabel p1Mancala = new JLabel(m1);

		MancalaIcon m2 = new MancalaIcon(20,200, "Player B",Player.TWO,test.model);
		JLabel p2Mancala = new JLabel(m2);

		//create pits for both players
		PitIcon a1 = new PitIcon(0,0,"A1",1,Player.ONE,test.model);
		JLabel pitA1 = new JLabel(a1);
		pitA1.setBounds(0, 0, 100, 100);
		PitIcon a2 = new PitIcon(0,0,"A2",2,Player.ONE,test.model);
		JLabel pitA2 = new JLabel(a2);
		PitIcon a3 = new PitIcon(0,0,"A3",3,Player.ONE,test.model);
		JLabel pitA3 = new JLabel(a3);
		PitIcon a4 = new PitIcon(0,0,"A4",4,Player.ONE,test.model);
		JLabel pitA4 = new JLabel(a4);
		PitIcon a5 = new PitIcon(0,0,"A5",5,Player.ONE,test.model);
		JLabel pitA5 = new JLabel(a5);
		PitIcon a6 = new PitIcon(0,0,"A6",6,Player.ONE,test.model);
		JLabel pitA6 = new JLabel(a6);

		PitIcon b1 = new PitIcon(0,0,"B1",1,Player.TWO,test.model);
		JLabel pitB1 = new JLabel(b1);
		PitIcon b2 = new PitIcon(0,0,"B2",2,Player.TWO,test.model);
		JLabel pitB2 = new JLabel(b2);
		PitIcon b3 = new PitIcon(0,0,"B3",3,Player.TWO,test.model);
		JLabel pitB3 = new JLabel(b3);
		PitIcon b4 = new PitIcon(0,0,"B4",4,Player.TWO,test.model);
		JLabel pitB4 = new JLabel(b4);
		PitIcon b5 = new PitIcon(0,0,"B5",5,Player.TWO,test.model);
		JLabel pitB5 = new JLabel(b5);
		PitIcon b6 = new PitIcon(0,0,"B6",6,Player.TWO,test.model);
		JLabel pitB6 = new JLabel(b6);

		pits.add(pitB6);
		pits.add(pitB5);
		pits.add(pitB4);
		pits.add(pitB3);
		pits.add(pitB2);
		pits.add(pitB1);

		pits.add(pitA1);
		pits.add(pitA2);
		pits.add(pitA3);
		pits.add(pitA4);
		pits.add(pitA5);
		pits.add(pitA6);

		JButton undoButton = new JButton("UNDO");
		JButton endTurnButton = new JButton("End Turn");

		board.add(p1Mancala,BorderLayout.EAST);
		board.add(p2Mancala,BorderLayout.WEST);
		board.add(pits,BorderLayout.CENTER);
		board.add(undoButton,BorderLayout.SOUTH);
		board.add(endTurnButton,BorderLayout.NORTH);

		//display choice for starting number of beads
		JPanel numberChoice = new JPanel();
		JPanel styleChoice = new JPanel();

		JButton choose3 = new JButton("3");
		choose3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				test.startingBeads = 3;
				mainFrame.remove(numberChoice);
				mainFrame.add(styleChoice);
				test.model = new MancalaModel(test.startingBeads);
				mainFrame.pack();
				mainFrame.repaint();

				a1.setModel(test.model);
				a2.setModel(test.model);
				a3.setModel(test.model);
				a4.setModel(test.model);
				a5.setModel(test.model);
				a6.setModel(test.model);
				b1.setModel(test.model);
				b2.setModel(test.model);
				b3.setModel(test.model);
				b4.setModel(test.model);
				b5.setModel(test.model);
				b6.setModel(test.model);
				m1.setModel(test.model);
				m2.setModel(test.model);

				test.model.addChangeListener(a1);
				test.model.addChangeListener(a2);
				test.model.addChangeListener(a3);
				test.model.addChangeListener(a4);
				test.model.addChangeListener(a5);
				test.model.addChangeListener(a6);
				test.model.addChangeListener(b1);
				test.model.addChangeListener(b2);
				test.model.addChangeListener(b3);
				test.model.addChangeListener(b4);
				test.model.addChangeListener(b5);
				test.model.addChangeListener(b6);
				test.model.addChangeListener(m1);
				test.model.addChangeListener(m2);
			}
		});
		JButton choose4 = new JButton("4");
		choose4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				test.startingBeads = 4;
				test.model = new MancalaModel(test.startingBeads);
				mainFrame.remove(numberChoice);
				mainFrame.add(styleChoice);
				mainFrame.pack();
				mainFrame.repaint();

				a1.setModel(test.model);
				a2.setModel(test.model);
				a3.setModel(test.model);
				a4.setModel(test.model);
				a5.setModel(test.model);
				a6.setModel(test.model);
				b1.setModel(test.model);
				b2.setModel(test.model);
				b3.setModel(test.model);
				b4.setModel(test.model);
				b5.setModel(test.model);
				b6.setModel(test.model);
				m1.setModel(test.model);
				m2.setModel(test.model);

				test.model.addChangeListener(a1);
				test.model.addChangeListener(a2);
				test.model.addChangeListener(a3);
				test.model.addChangeListener(a4);
				test.model.addChangeListener(a5);
				test.model.addChangeListener(a6);
				test.model.addChangeListener(b1);
				test.model.addChangeListener(b2);
				test.model.addChangeListener(b3);
				test.model.addChangeListener(b4);
				test.model.addChangeListener(b5);
				test.model.addChangeListener(b6);
				test.model.addChangeListener(m1);
				test.model.addChangeListener(m2);
			}
		});

		numberChoice.add(choose3);
		numberChoice.add(choose4);

		mainFrame.add(numberChoice);
		mainFrame.setVisible(true);
		mainFrame.pack();

		//display choice for style of board

		JButton chooseA = new JButton("A");
		chooseA.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				mainFrame.remove(styleChoice);
				mainFrame.add(board);
				
				BoardStyle a = new BoardStyleA();
	
				a1.setStyle(a);
				a2.setStyle(a);
				a3.setStyle(a);
				a4.setStyle(a);
				a5.setStyle(a);
				a6.setStyle(a);
				b1.setStyle(a);
				b2.setStyle(a);
				b3.setStyle(a);
				b4.setStyle(a);
				b5.setStyle(a);
				b6.setStyle(a);
				
				mainFrame.pack();
				mainFrame.repaint();
			}
		});
		JButton chooseB = new JButton("B");
		chooseB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				mainFrame.remove(styleChoice);
				mainFrame.add(board);
				mainFrame.pack();
				mainFrame.repaint();
			}
		});

		styleChoice.add(chooseA);
		styleChoice.add(chooseB);



		//listeners for the pits
		mainFrame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if ((pitA1.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 0);
				}
				else if ((pitA2.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 1);
				}
				else if ((pitA3.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 2);
				}
				else if ((pitA4.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 3);
				}
				else if ((pitA5.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 4);
				}
				else if ((pitA6.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.ONE)
						test.model.makeMove(test.model.getCurrentPlayer(), 5);
				}
				else if ((pitB1.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 0);
				}
				else if ((pitB2.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 1);
				}
				else if ((pitB3.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 2);
				}
				else if ((pitB4.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 3);
				}
				else if ((pitB5.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 4);
				}
				else if ((pitB6.getBounds().contains(new Point(e.getX()-225,e.getY()))))
				{
					if (test.model.getCurrentPlayer() == Player.TWO)
						test.model.makeMove(test.model.getCurrentPlayer(), 5);
				}
				if (test.model.checkGameOver()) {
					Player winner = test.model.declareWinner();
					//dump the frame here
					
					//Display winner onto the screen here
				}

				mainFrame.repaint();
			}
		});


		//end turn and undo button listeners
		undoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				test.model.undo();

			}

		});
		endTurnButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				test.model.changeCurrentPlayer();

			}

		});
	}



}
