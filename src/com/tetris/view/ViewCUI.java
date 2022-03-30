package com.tetris.view;

import com.tetris.constant.Type;
import com.tetris.model.Piece;
import com.tetris.model.Tetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewCUI
{
	// Constants
	private static final String                 RESET      = "\u001B[0m";
	private static final Map<Character, String> TYPE_COLOR = new HashMap<>();

	static
	{
		TYPE_COLOR.put(Type.VOID, "\u001B[0m");
		TYPE_COLOR.put(Type.WALL, "\u001B[40m");
		TYPE_COLOR.put(Type.WALL_INV, "\u001B[47m");
		TYPE_COLOR.put(Type.PREVIEW, "\u001B[47m");
		TYPE_COLOR.put(Type.O, "\u001B[43m");
		TYPE_COLOR.put(Type.I, "\u001B[44m");
		TYPE_COLOR.put(Type.T, "\033[0;105m");
		TYPE_COLOR.put(Type.L, "\u001B[46m");
		TYPE_COLOR.put(Type.J, "\u001B[45m");
		TYPE_COLOR.put(Type.S, "\u001B[42m");
		TYPE_COLOR.put(Type.Z, "\u001B[41m");
	}

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
		System.out.println("[M] Move max down");
		System.out.println("[T] Turn");
		System.out.println("[Q] Quit");
		System.out.println();
		System.out.print("Your choice : ");

		return Character.toUpperCase(this.input.next().charAt(0));
	}

	public void showGame()
	{
		StringBuilder game = new StringBuilder();

		char[][] original = this.tetris.getGrid();
		char[][] copy     = new char[original.length][original[0].length];
		for (int l = 0; l < original.length; l++)
			for (int c = 0; c < original[0].length; c++)
			     copy[l][c] = original[l][c];

		boolean[][] structure = this.tetris.getPiece().getStructure();
		char        type      = this.tetris.getPiece().getType();

		// Adding the piece, and it's preview to the grid temporary
		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[0].length; c++)
				if (structure[l][c])
				{
					copy[l + this.tetris.getMaxL()][c + this.tetris.getC()] = Type.PREVIEW;
					copy[l + this.tetris.getL()][c + this.tetris.getC()]    = type;
				}

		// Adding the game display
		game.append('\n');

		StringBuilder lineBuilder = new StringBuilder();
		lineBuilder.append('+');
		for (int c = 0; c < copy[0].length; c++)
		     lineBuilder.append("-+");
		lineBuilder.append("\n|");
		String line = lineBuilder.toString();

		game.append(line);
		for (int l = 0; l < copy.length; l++, game.append('\n'), game.append(line.toString()))
			for (int c = 0; c < copy[l].length; c++)
			     game.append(String.format("%s %s|", TYPE_COLOR.get(copy[l][c]), RESET));

		// Adding the queue
		game.append('\n');
		game.append("Queue : ");
		for (Piece p : this.tetris.getQueue())
		{
			game.append(p.getType());
			game.append(' ');
		}

		// Display
		game.append('\n');
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
