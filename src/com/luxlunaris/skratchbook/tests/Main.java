package com.luxlunaris.skratchbook.tests;

public class Main {

	public static void main(String[] args) {
		
		if(args.length==0) {
			System.out.println("Enter 'help' for a list of available commands:");
			return;
		}
		
		switch (args[0]) {
		case "color-picker":
			com.luxlunaris.skratchbook.tests.color_picker.Main.main(args);
			break;
			
		case "help":	
		default:
			printHelp();
			break;
		}
		
	}
	
	
	public static void printHelp() {
		System.out.println("color-picker : to start the color-picker demo.");
	}
	
	
	
	

}
