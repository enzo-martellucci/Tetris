package com.tetris.view;

import com.tetris.constant.Type;
import com.tetris.model.Piece;
import com.tetris.model.Tetris;

import java.util.Scanner;

public class ViewCUI
{
	// Attributes
	private Tetris  tetris;
	private Scanner input;


	// Constructor
	public ViewCUI(Tetris tetris)
	{
		this.tetris = tetris;
		this.input  = new Scanner(System.in);
	}


	// Methods
	public char enterAction()
	{
		System.out.println("What to do :");
		System.out.println("[S] Show the game");
		System.out.println("[R] Move right");
		System.out.println("[L] Move left");
		System.out.println("[D] Move down");
		System.out.println("[T] Turn");
		System.out.println("[Q] Quit");
		System.out.println();
		System.out.print("Your choice : ");

		return Character.toUpperCase(this.input.next().charAt(0));
	}

	public void showGame()
	{
		StringBuilder game = new StringBuilder();

		// Adding the piece, and it's preview to the grid temporary
		char[][]    grid      = this.tetris.getGrid();
		boolean[][] structure = this.tetris.getPiece().getStructure();
		char        type      = this.tetris.getPiece().getType();

		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[0].length; c++)
				if (structure[l][c])
					grid[l + this.tetris.getL()][c + this.tetris.getC()] = type; // = grid[l + this.tetris.getPreviewL()][c + this.tetris.getPreviewC()]

		// Adding the game display
		game.append('\n');
		for (int l = 0; l < grid.length; l++, game.append('\n'))
			for (int c = 0; c < grid[l].length; c++)
			     game.append(grid[l][c]);
		game.append('\n');
		game.append('\n');

		// Remove temporary piece and it's preview
		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[0].length; c++)
				if (structure[l][c])
					grid[l + this.tetris.getL()][c + this.tetris.getC()] = Type.VOID; // = grid[l + this.tetris.getPreviewL()][c + this.tetris.getPreviewC()]

		// Adding the queue
		game.append("Queue : ");
		for (Piece p : this.tetris.getQueue())
		{
			game.append(p.getType());
			game.append(' ');
		}

		// Display
		System.out.println(game.toString());
	}

	public void notImplemented()
	{
		System.out.println("Not implemented yet!");
	}

	public void quit()
	{
		System.out.println("Good bye!");
	}
}
