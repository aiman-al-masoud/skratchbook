package com.luxlunaris.skratchbook.tests;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.luxlunaris.skratchbook.interfaces.TagListener;
import com.luxlunaris.skratchbook.model.Tag;

public class ColorFrame extends JFrame implements TagListener {



	public ColorFrame() {
		Settings.instance().addTagListener(Settings.TAG_COLOR_BG, this);
		setTitle("RGB Color Picker");
		setLayout(new BorderLayout());
		add(new ColorSetterPanel(), BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setColor(Settings.instance().getInt(Settings.TAG_COLOR_BG));
		setSize(400,400);
		setVisible(true);
	}
	
	
	@Override
	public void onTagUpdated(Tag tag) {
		int newColor = Settings.instance().getInt(tag);
		setColor(newColor);
	}
	
	
	public void setColor(int colorRGB) {
		getContentPane().setBackground(new Color(colorRGB));
		repaint();
		revalidate();
	}
	
	
	
	
	class ColorSetterPanel extends JPanel{
		
		JTextField redField, blueField, greenField;
		JButton confirmButton;
		
		public ColorSetterPanel() {
			
			Color storedColor = new Color(Settings.instance().getInt(Settings.TAG_COLOR_BG));
			
			
			redField = new JTextField(storedColor.getRed()+"");
			greenField = new JTextField(storedColor.getGreen()+"");
			blueField = new JTextField(storedColor.getBlue()+"");
			confirmButton = new JButton("Confirm");
			setLayout(new GridLayout(4, 1));
			add(redField);
			add(greenField);
			add(blueField);
			add(confirmButton);
			confirmButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					int r = toInt(redField.getText());
					int b = toInt(blueField.getText());
					int g = toInt(greenField.getText());
					int rgb = new Color(r, g, b).getRGB();
					
										
					Settings.instance().setTag(Settings.TAG_COLOR_BG, rgb+"");

					setColor(rgb);
				}
			});
		}
		
	
		
	}
	
	
	public static int toInt(String intStr) {
		try {
			return Integer.parseInt(intStr.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	
	
	
	
	
	
}
