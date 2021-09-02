package com.luxlunaris.skratchbook.tests.color_picker;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.luxlunaris.skratchbook.interfaces.TagListener;
import com.luxlunaris.skratchbook.model.Tag;

public class ColorFrame extends JFrame implements TagListener {



	public ColorFrame() {
		//start listening to the TAG_COLOR_BG tag.
		Settings.instance().addTagListener(Settings.TAG_COLOR_BG, this);
		//set frame parameters...
		setTitle("RGB Color Picker");
		setIconImage(loadImage("res/skratchbook.png"));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		add(new ColorSetterPanel(), BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		//set the color to the one previously stored in Settings.
		setColor(Settings.instance().getInt(Settings.TAG_COLOR_BG));
		//show frame
		setVisible(true);
	}
	
	
	/**
	 * Called by Settings the instant a tag ColorFrame is registered for
	 * changes value.
	 */
	@Override
	public void onTagUpdated(Tag tag) {
		//get updated color from settings
		int newColor = Settings.instance().getInt(tag);
		//set it:
		setColor(newColor);
	}
	
	
	/**
	 * Changes the color of the frame.
	 * @param colorRGB
	 */
	public void setColor(int colorRGB) {
		getContentPane().setBackground(new Color(colorRGB));
		repaint();
		revalidate();
	}
	
	
	
	
	class ColorSetterPanel extends JPanel{
		
		JTextField redField, blueField, greenField;
		JButton confirmButton;
		
		public ColorSetterPanel() {
			
			//set the text fields to the stored rgb component values.
			Color storedColor = new Color(Settings.instance().getInt(Settings.TAG_COLOR_BG));
			redField = new JTextField(storedColor.getRed()+"");
			greenField = new JTextField(storedColor.getGreen()+"");
			blueField = new JTextField(storedColor.getBlue()+"");
			
			//add the components...
			confirmButton = new JButton("Confirm");
			setLayout(new GridLayout(4, 1));
			add(redField);
			add(greenField);
			add(blueField);
			add(confirmButton);
			
			//set confirm button's action listener
			confirmButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					//get the total rgb int for the color that was selected:
					int r = toInt(redField.getText());
					int b = toInt(blueField.getText());
					int g = toInt(greenField.getText());
					int rgb = new Color(r, g, b).getRGB();
					
					//set it as the current color:				
					Settings.instance().setTag(Settings.TAG_COLOR_BG, rgb+"");
					
				}
			});
		}
		
	
		
	}
	
	
	/**
	 * Converts string to integer.
	 * @param intStr
	 * @return
	 */
	public static int toInt(String intStr) {
		try {
			return Integer.parseInt(intStr.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * Loads image as BufferedImage.
	 * @param pathname
	 * @return
	 */
	public static BufferedImage loadImage(String pathname) {
		try {
			return ImageIO.read(new File(pathname));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
}
