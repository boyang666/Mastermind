package com.polytechtours.fr.di5.mastermind.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
 
/**
 * make a circle button
 */
public class JCircleButton extends JButton {
 
    /**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	public JCircleButton(String label) {
        super(label);
 
        // get a preferred size 
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
 
        setContentAreaFilled(false);
    }
 
    // draw the background and label 
    protected void paintComponent(Graphics g) {
 
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray); // highlight when clicked
        } else {
            g.setColor(getBackground());
        }
        
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
        super.paintComponent(g);
    }
 
    // draw the border
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 
    // keep the shape for listener
    Shape shape;
 
    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // create an object
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // to verify if the location is in the circle
        return shape.contains(x, y);
    }
 
    public static void main(String[] args) {
        JButton button = new JCircleButton("");
        button.setBackground(Color.orange);
 
        JFrame frame = new JFrame("circle button");
        frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(button);
        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
}