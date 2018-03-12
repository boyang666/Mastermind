package com.polytechtours.fr.di5.mastermind.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.polytechtours.fr.di5.mastermind.service.AutoSolveService;
import com.polytechtours.fr.di5.mastermind.service.CalculateService;
import com.polytechtours.fr.di5.mastermind.util.Constant;
import com.polytechtours.fr.di5.mastermind.util.JCircleButton;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * type of difficulty
	 */
	private int typeGame;

	/**
	 * title label
	 */
	private JLabel titleLabel;

	/**
	 * hit label
	 */
	private JLabel hitLabel;

	/**
	 * clear current line button
	 */
	private JButton clearLineButton;

	/**
	 * restart button
	 */
	private JButton restartButton;

	/**
	 * verify the current line
	 */
	private JButton verifyButton;

	/**
	 * solve button
	 */
	private JButton solveButton;

	/**
	 * buttons array for colors
	 */
	private JButton[] colorButtons;

	/**
	 * places to place the colors
	 */
	private JLabel[][] places;

	/**
	 * solution label
	 */
	private JLabel[] solution;

	/**
	 * width of places
	 */
	private int widthPlaces;

	/**
	 * length of places
	 */
	private int lengthPlaces;

	/**
	 * to stock every choice
	 */
	private int[][] choices;

	/**
	 * the sequence to solve
	 */
	private int[] sequence;

	/**
	 * the current row to place a choice
	 */
	private int currentRow;

	/**
	 * the current column to place a choice
	 */
	private int currentCol;

	/**
	 * if the game is solved
	 */
	private boolean solved = false;

	/**
	 * constructor
	 */
	public MainWindow(int typeGame) {
		super("Mastermind");
		this.typeGame = typeGame;
		initUI();
		choices = new int[lengthPlaces][widthPlaces - 2];
		for (int i = 0; i < lengthPlaces; i++) {
			for (int j = 0; j < widthPlaces - 2; j++) {
				choices[i][j] = -1;
			}
		}
		initSequence();
		currentRow = 0;
		currentCol = 1;
		for (int i = 0; i < sequence.length; i++) {
			// System.out.println(sequence[i]);
		}
		solved = false;
	}

	public void initUI() {

		// title label
		titleLabel = new JLabel("", JLabel.CENTER);
		if (this.typeGame == Constant.EASY_MODE) {
			titleLabel.setText("Mastermind V2.0 - easy");
		}
		if (this.typeGame == Constant.NORMAL_MODE) {
			titleLabel.setText("Mastermind V2.0 - normal");
		}
		if (this.typeGame == Constant.HARD_MODE) {
			titleLabel.setText("Mastermind V2.0 - hard");
		}
		titleLabel.setBounds(100, 20, 300, 50);
		Font f_title = new Font("calibri", Font.BOLD, 25);
		titleLabel.setFont(f_title);
		titleLabel.setForeground(Color.WHITE);

		// hit label
		Font f_hit = new Font("calibri", Font.BOLD, 18);
		hitLabel = new JLabel("", JLabel.CENTER);
		hitLabel.setText("H: Hit, P: Pseudo hit");
		hitLabel.setBounds(100, 70, 300, 20);
		hitLabel.setFont(f_hit);
		hitLabel.setForeground(Color.WHITE);

		// style of buttons
		Font f_button = new Font("calibri", Font.BOLD, 15);

		// clear line button
		clearLineButton = new JButton("Clear the current line");
		clearLineButton.setBounds(50, 100, 180, 30);
		clearLineButton.setFont(f_button);
		clearLineButton.setBackground(new Color(160, 82, 45));
		clearLineButton.setForeground(Color.WHITE);
		clearLineButton.addActionListener(this);

		// restart button
		restartButton = new JButton("Restart");
		restartButton.setBounds(270, 100, 80, 30);
		restartButton.setFont(f_button);
		restartButton.setBackground(new Color(160, 82, 45));
		restartButton.setForeground(Color.WHITE);
		restartButton.addActionListener(this);

		// solve button
		solveButton = new JButton("Solve");
		solveButton.setBounds(370, 100, 80, 30);
		solveButton.setFont(f_button);
		solveButton.setBackground(new Color(160, 82, 45));
		solveButton.setForeground(Color.WHITE);
		solveButton.addActionListener(this);

		// verify button
		verifyButton = new JButton("Verify the current line");
		verifyButton.setBounds(150, 150, 200, 30);
		verifyButton.setFont(f_button);
		verifyButton.setBackground(new Color(100, 149, 237));
		verifyButton.setForeground(Color.WHITE);
		verifyButton.addActionListener(this);

		// color buttons
		if (typeGame == Constant.EASY_MODE) {
			colorButtons = new JCircleButton[4];

			colorButtons[0] = new JCircleButton("");
			colorButtons[0].setBackground(Color.YELLOW);
			colorButtons[0].setBounds(140, 200, 35, 35);
			colorButtons[0].addActionListener(this);

			colorButtons[1] = new JCircleButton("");
			colorButtons[1].setBackground(Color.GREEN);
			colorButtons[1].setBounds(200, 200, 35, 35);
			colorButtons[1].addActionListener(this);

			colorButtons[2] = new JCircleButton("");
			colorButtons[2].setBackground(Color.BLUE);
			colorButtons[2].setBounds(260, 200, 35, 35);
			colorButtons[2].addActionListener(this);

			colorButtons[3] = new JCircleButton("");
			colorButtons[3].setBackground(Color.RED);
			colorButtons[3].setBounds(320, 200, 35, 35);
			colorButtons[3].addActionListener(this);
		} else if (typeGame == Constant.HARD_MODE || typeGame == Constant.NORMAL_MODE) {
			colorButtons = new JCircleButton[6];

			colorButtons[0] = new JCircleButton("");
			colorButtons[0].setBackground(Color.YELLOW);
			colorButtons[0].setBounds(80, 200, 35, 35);
			colorButtons[0].addActionListener(this);

			colorButtons[1] = new JCircleButton("");
			colorButtons[1].setBackground(Color.GREEN);
			colorButtons[1].setBounds(140, 200, 35, 35);
			colorButtons[1].addActionListener(this);

			colorButtons[2] = new JCircleButton("");
			colorButtons[2].setBackground(Color.BLUE);
			colorButtons[2].setBounds(200, 200, 35, 35);
			colorButtons[2].addActionListener(this);

			colorButtons[3] = new JCircleButton("");
			colorButtons[3].setBackground(Color.RED);
			colorButtons[3].setBounds(260, 200, 35, 35);
			colorButtons[3].addActionListener(this);

			colorButtons[4] = new JCircleButton("");
			colorButtons[4].setBackground(Color.ORANGE);
			colorButtons[4].setBounds(320, 200, 35, 35);
			colorButtons[4].addActionListener(this);

			colorButtons[5] = new JCircleButton("");
			colorButtons[5].setBackground(new Color(128, 0, 128));
			colorButtons[5].setBounds(380, 200, 35, 35);
			colorButtons[5].addActionListener(this);
		}

		// table to display the choices
		Font f_num = new Font("calibri", Font.BOLD, 25);
		if (typeGame == Constant.EASY_MODE || typeGame == Constant.NORMAL_MODE) {
			this.widthPlaces = 6;
			this.lengthPlaces = 10;
			places = new JLabel[lengthPlaces][widthPlaces];
			int xStart = 80;
			int yStart = 260;
			String path = "img/0.gif";
			for (int i = 0; i < lengthPlaces; i++) {
				for (int j = 0; j < widthPlaces; j++) {
					places[i][j] = new JLabel("", JLabel.CENTER);
					places[i][j].setBounds(xStart + 50 * j, yStart + 50 * i, 50, 50);
					places[i][j].setForeground(Color.WHITE);

					if (j > 0 && j < widthPlaces - 1) {
						ImageIcon image = new ImageIcon(path);
						places[i][j].setIcon(image);
					} else if (j == 0) {
						places[i][j].setText(Integer.toString(i + 1));
						places[i][j].setFont(f_num);
					} else if (j == widthPlaces - 1) {
						places[i][j].setSize(120, 50);
						places[i][j].setText("");
						places[i][j].setFont(f_num);
						ImageIcon imageHit = new ImageIcon("img/hit.gif");
						places[i][j].setIcon(imageHit);
					}

				}
			}
		} else if (typeGame == Constant.HARD_MODE) {
			this.widthPlaces = 8;
			this.lengthPlaces = 10;
			places = new JLabel[lengthPlaces][widthPlaces];
			int xStart = 20;
			int yStart = 260;
			String path = "img/0.gif";
			for (int i = 0; i < lengthPlaces; i++) {
				for (int j = 0; j < widthPlaces; j++) {
					places[i][j] = new JLabel("", JLabel.CENTER);
					places[i][j].setBounds(xStart + 50 * j, yStart + 50 * i, 50, 50);
					places[i][j].setForeground(Color.WHITE);

					if (j > 0 && j < widthPlaces - 1) {
						ImageIcon image = new ImageIcon(path);
						places[i][j].setIcon(image);
					} else if (j == 0) {
						places[i][j].setText(Integer.toString(i + 1));
						places[i][j].setFont(f_num);
					} else if (j == widthPlaces - 1) {
						places[i][j].setSize(120, 50);
						places[i][j].setText("");
						places[i][j].setFont(f_num);
						ImageIcon imageHit = new ImageIcon("img/hit.gif");
						places[i][j].setIcon(imageHit);
					}

				}
			}
		}

		// solution label
		solution = new JLabel[widthPlaces - 1];
		solution[0] = new JLabel("Solution: ");
		solution[0].setFont(f_num);
		solution[0].setForeground(Color.WHITE);
		int xStart;
		if (typeGame == Constant.EASY_MODE || typeGame == Constant.NORMAL_MODE) {
			xStart = 80;
		} else {
			xStart = 40;
		}
		solution[0].setBounds(xStart, 800, 100, 50);
		ImageIcon img = new ImageIcon("img/sol.gif");
		for (int i = 1; i < solution.length; i++) {
			solution[i] = new JLabel();
			solution[i].setIcon(img);
			solution[i].setBounds(xStart + 120 + 50 * (i - 1), 800, 50, 50);
		}

		// add all components
		this.add(titleLabel);
		this.add(hitLabel);
		this.add(clearLineButton);
		this.add(restartButton);
		this.add(solveButton);
		this.add(verifyButton);
		for (int i = 0; i < colorButtons.length; i++) {
			this.add(colorButtons[i]);
		}
		for (int i = 0; i < lengthPlaces; i++) {
			for (int j = 0; j < widthPlaces; j++) {
				this.add(places[i][j]);
			}
		}
		for (int i = 0; i < solution.length; i++) {
			this.add(solution[i]);
		}

		// configurations of window
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(500, 930);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initSequence() {
		Random random = new Random();
		if (typeGame == Constant.EASY_MODE) {
			sequence = new int[4];
			for (int i = 0; i < 4; i++) {
				sequence[i] = 1 + random.nextInt(4);
			}
		} else if (typeGame == Constant.NORMAL_MODE) {
			sequence = new int[4];
			for (int i = 0; i < 4; i++) {
				sequence[i] = 1 + random.nextInt(6);
			}
		} else if (typeGame == Constant.HARD_MODE) {
			sequence = new int[6];
			for (int i = 0; i < 6; i++) {
				sequence[i] = 1 + random.nextInt(6);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// click of restart button
		if (e.getSource().equals(restartButton)) {
			this.dispose();
			ChooseDifficultyWindow window = new ChooseDifficultyWindow();
			window.setVisible(true);
		}

		// click of clear line button
		if (e.getSource().equals(clearLineButton) && !solved) {
			String path = "img/0.gif";
			ImageIcon image = new ImageIcon(path);
			for (int j = 0; j < widthPlaces; j++) {
				if (j > 0 && j < widthPlaces - 1) {
					places[currentRow][j].setIcon(image);
				}
			}
			currentCol = 1;
		}

		// click of verify button
		if (e.getSource().equals(verifyButton) && !solved) {
			if (currentCol != widthPlaces - 1) {
				JOptionPane.showMessageDialog(null, "Please fill the line !", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				HashMap<String, Integer> eval = CalculateService.evaluate(sequence, choices[currentRow],
						colorButtons.length);
				int hit = eval.get("hit");
				int pseudoHit = eval.get("pseudoHit");
				StringBuilder result = new StringBuilder("");
				result.append("H=").append(hit).append(",").append("P=").append(pseudoHit);
				places[currentRow][widthPlaces - 1].setIcon(null);
				places[currentRow][widthPlaces - 1].setText(result.toString());
				currentRow += 1;
				currentCol = 1;

				// success
				if (hit == widthPlaces - 2) {
					solved = true;
					JOptionPane.showMessageDialog(null, "Bravo! You win the game with " + currentRow + " steps!",
							"Success", JOptionPane.INFORMATION_MESSAGE);
					for (int i = 1; i < solution.length; i++) {
						ImageIcon icon = new ImageIcon("img/" + sequence[i - 1] + ".gif");
						solution[i].setIcon(icon);
					}
				}
				// end of guess
				else if (currentRow == lengthPlaces) {
					solved = true;
					JOptionPane.showMessageDialog(null, "Sorry! Gamo over!", "Fail", JOptionPane.INFORMATION_MESSAGE);
					for (int i = 1; i < solution.length; i++) {
						ImageIcon icon = new ImageIcon("img/" + sequence[i - 1] + ".gif");
						solution[i].setIcon(icon);
					}
				}
			}
		}
		
		// click of the solve button
		if(e.getSource().equals(solveButton) && !solved){
			ArrayList<int[]> steps = AutoSolveService.solve(colorButtons.length, widthPlaces - 2, sequence);
			for(int i=0; i<lengthPlaces; i++){
				for(int j=0; j<widthPlaces-2; j++){
					choices[i][j] = 0;
				}
			}
			currentRow = 0;
			currentCol = 1;
			
			for(int i=0; i<steps.size(); i++){
				for(int j=0; j<steps.get(i).length; j++){
					choices[i][j] = steps.get(i)[j];
				}
			}
			
			for(int i=0; i<lengthPlaces; i++){
				for(int j=1; j<widthPlaces-1; j++){
					ImageIcon icon = new ImageIcon("img/"+choices[i][j-1]+".gif");
					places[i][j].setIcon(icon);
				}
				if(choices[i][0] != 0){
					HashMap<String, Integer> eval = CalculateService.evaluate(sequence, choices[i], colorButtons.length);
					int hit = eval.get("hit");
					int pseudoHit = eval.get("pseudoHit");
					places[i][widthPlaces - 1].setIcon(null);
					places[i][widthPlaces - 1].setText("H="+hit+","+"P="+pseudoHit);
				}
			}
			
			for(int i=1; i<solution.length; i++){
				ImageIcon icon = new ImageIcon("img/"+sequence[i-1]+".gif");
				solution[i].setIcon(icon);
			}
			
			JOptionPane.showMessageDialog(null, "Computer solved the problem!", "Information", JOptionPane.INFORMATION_MESSAGE);
			
			solved = true;
		}

		// click of color buttons
		for (int i = 0; i < colorButtons.length; i++) {
			if (e.getSource().equals(colorButtons[i]) && !solved) {
				int currentColor = i + 1;
				if (currentCol != widthPlaces - 1) {
					ImageIcon img = new ImageIcon("img/" + currentColor + ".gif");
					places[currentRow][currentCol].setIcon(img);
					choices[currentRow][currentCol - 1] = currentColor;
					currentCol++;
				}
				break;
			}
		}

	}
}
