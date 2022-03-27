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
				case 'R' -> this.moveR();
				case 'L' -> this.moveL();
				case 'D' -> this.moveD();
				case 'M' -> this.moveMaxD();
				case 'T' -> this.viewCUI.notImplemented();
				case 'Q' -> this.viewCUI.quit();
			}
		}
	}


	// Methods
	private void moveR()
	{
		if (this.tetris.moveR())
			this.viewCUI.showGame();
	}

	private void moveL()
	{
		if (this.tetris.moveL())
			this.viewCUI.showGame();
	}

	private boolean moveD()
	{
		boolean success = this.tetris.moveD();
		if (success)
			this.viewCUI.showGame();
		else
			System.out.println("Perdu");
		return success;
	}

	private void moveMaxD()
	{
		for (int i = 0; i < 5; i++)
		     this.moveD();
	}


	// Main
	public static void main(String[] args)
	{
		new Controller();
	}
}
