package com.tetris;

import com.tetris.model.Tetris;
import com.tetris.view.ViewCUI;

public class Controller
{
	// Attributes
	private Tetris  tetris;
	private ViewCUI viewCUI;


	// Constructor
	public Controller()
	{
		// Initialisation
		this.tetris  = new Tetris();
		this.viewCUI = new ViewCUI(this.tetris);

		// Loop
		char action = ' ';
		while (action != 'Q')
		{
			action = this.viewCUI.enterAction();
			switch (action)
			{
				case 'S' -> this.viewCUI.showGame();
				case 'R' -> this.viewCUI.notImplemented();
				case 'L' -> this.viewCUI.notImplemented();
				case 'D' -> this.viewCUI.notImplemented();
				case 'T' -> this.viewCUI.notImplemented();
				case 'Q' -> this.viewCUI.quit();
			}
		}
	}


	// Methods


	// Main
	public static void main(String[] args)
	{
		new Controller();
	}
}
