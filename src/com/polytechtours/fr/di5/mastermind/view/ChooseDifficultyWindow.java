package com.polytechtours.fr.di5.mastermind.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.polytechtours.fr.di5.mastermind.util.Constant;

/**
 * window for choosing a difficulty of game among [easy, normal, difficult]
 * 
 * @author Boyang Wang
 * @version 1.0
 * 
 */
public class ChooseDifficultyWindow extends JFrame implements ActionListener {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	JLabel titleLabel;

	/**
	 * button for easy mode
	 */
	JButton easyButton;

	/**
	 * button for normal mode
	 */
	JButton normalButton;

	/**
	 * button for difficult mode
	 */
	JButton hardButton;

	/**
	 * constructor
	 */
	public ChooseDifficultyWindow() {

		super("Mastermind");
		initUI();

	}

	/**
	 * initialize the UI
	 */
	public void initUI() {
		// title label
		titleLabel = new JLabel("Mastermind V2.0", JLabel.CENTER);
		titleLabel.setBounds(100, 30, 300, 60);
		Font f_title = new Font("calibri", Font.BOLD, 25);
		titleLabel.setFont(f_title);
		titleLabel.setForeground(Color.WHITE);

		// style of buttons
		Font f_button = new Font("calibri", Font.BOLD, 18);

		// easy button
		easyButton = new JButton("Easy Mode");
		easyButton.setBounds(150, 120, 200, 50);
		easyButton.setFont(f_button);
		easyButton.setForeground(Color.WHITE);
		easyButton.setBackground(new Color(189, 183, 107));
		easyButton.addActionListener(this);

		// normal button
		normalButton = new JButton("Normal Mode");
		normalButton.setBounds(150, 200, 200, 50);
		normalButton.setFont(f_button);
		normalButton.setForeground(Color.WHITE);
		normalButton.setBackground(Color.BLUE);
		normalButton.addActionListener(this);

		// hard button
		hardButton = new JButton("Hard Mode");
		hardButton.setBounds(150, 280, 200, 50);
		hardButton.setFont(f_button);
		hardButton.setForeground(Color.WHITE);
		hardButton.setBackground(Color.RED);
		hardButton.addActionListener(this);

		// add all components
		this.add(titleLabel);
		this.add(easyButton);
		this.add(normalButton);
		this.add(hardButton);

		// configuration for the window
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(500, 400);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(easyButton)){
			MainWindow window = new MainWindow(Constant.EASY_MODE);
			window.setVisible(true);
		}
		else if(e.getSource().equals(normalButton)){
			MainWindow window = new MainWindow(Constant.NORMAL_MODE);
			window.setVisible(true);
		}
		else if(e.getSource().equals(hardButton)){
			MainWindow window = new MainWindow(Constant.HARD_MODE);
			window.setVisible(true);
		}
		this.dispose();
	}
	
	

}
