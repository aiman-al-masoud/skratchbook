package com.luxlunaris.skratchbook.tests;

import java.awt.Color;

import com.luxlunaris.skratchbook.model.Organizer;
import com.luxlunaris.skratchbook.model.Tag;

public class Settings extends Organizer {
	
	private static final String PATH = "test_files/settings";
	private static Settings instance;
	
	public static final Tag TAG_COLOR_BG = new Tag("bg_color", Color.yellow.getRGB()+"");
	
	private Settings() {
		super(PATH);
	}
	
	public static Settings instance() {
		return instance!=null? instance : (instance=new Settings() );
	}
	
	
	

}
