package com.polytechtours.fr.di5.mastermind.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
 
/**
 * Class to make a circle button used by colors
 * 
 * @author Boyang Wang
 * @version 1.0
 * 
 */
public class JCircleButton extends JButton {
 
    /**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param label text of label
	 */
	public JCircleButton(String label) {
        super(label);
 
        // get a preferred size 
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
 
        setContentAreaFilled(false);
    }
 
	/**
	 * draw the background and label 
	 */ 
    protected void paintComponent(Graphics g) {
 
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray); // highlight when clicked
        } else {
            g.setColor(getBackground());
        }
        
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
        super.paintComponent(g);
    }
 
    /**
     * draw the border
     */
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 
    /**
     * keep the shape for listener
     */
    Shape shape;
 
    /**
     * to verify if clicked in range
     */
    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // create an object
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // to verify if the location is in the circle
        return shape.contains(x, y);
    }
 
}