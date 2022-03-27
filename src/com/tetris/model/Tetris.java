package com.tetris.model;

import com.tetris.constant.Type;

import java.util.LinkedList;
import java.util.Queue;

import static com.tetris.constant.Parameter.*;

public class Tetris
{
	// Attributes
	private char[][]     grid;
	private Queue<Piece> queue;

	private Piece piece;
	private int   l;
	private int   c;
	private int   previewL;
	private int   previewC;


	// Constructor
	public Tetris()
	{
		// Init the grid
		this.grid = new char[HEIGHT][WIDTH];

		for (int l = 0; l < this.grid.length; l++)
			for (int c = 0; c < this.grid[0].length; c++)
			     this.grid[l][c] = Type.VOID;

		for (int l = 0; l < this.grid.length; l++)
		     this.grid[l][0] = this.grid[l][WIDTH - 1] = Type.WALL;

		for (int c = 0; c < this.grid[0].length; c++)
		     this.grid[HEIGHT - 1][c] = Type.WALL;

		this.grid[0][0] = this.grid[0][WIDTH - 1] = Type.WALL_INV;
		this.grid[1][0] = this.grid[1][WIDTH - 1] = Type.WALL_INV;
		this.grid[2][0] = this.grid[2][WIDTH - 1] = Type.WALL_INV;

		// Init the piece queue
		this.queue = new LinkedList<>();
		this.queue.addAll(Piece.createBag());
		this.queue.addAll(Piece.createBag());

		// Pull the first piece
		this.pullPiece();
	}


	// Getters
	public char[][] getGrid() { return this.grid; }
	public Queue<Piece> getQueue() { return this.queue; }
	public Piece getPiece()        { return this.piece; }
	public int getL()              { return this.l; }
	public int getC()              { return this.c; }
	public int getPreviewL()       { return this.previewL; }
	public int getPreviewC()       { return this.previewC; }


	// Public methods
	public boolean moveD()
	{
		boolean[][] structure = this.piece.getStructure();

		for (int c = 0; c < structure[0].length; c++)
			for (int l = structure.length - 1; l >= 0; l--)
				if (structure[l][c])
					if (this.grid[this.l + l + 1][this.c + c] != Type.VOID)
						return this.place(l);
					else
						break;

		this.l++;
		return true;
	}

	public boolean moveL()
	{
		boolean[][] structure = this.piece.getStructure();

		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[l].length; c++)
				if (structure[l][c])
					if (this.grid[this.l + l][this.c + c - 1] != Type.VOID)
						return false;
					else
						break;
		this.c--;
		return true;
	}

	public boolean moveR()
	{
		boolean[][] structure = this.piece.getStructure();

		for (int l = 0; l < structure.length; l++)
			for (int c = structure[l].length - 1; c >= 0; c--)
				if (structure[l][c])
					if (this.grid[this.l + l][this.c + c + 1] != Type.VOID)
						return false;
					else
						break;
		this.c++;
		return true;
	}

	public boolean turn()
	{
		// TODO
		return false;
	}


	// Private methods
	private void pullPiece()
	{
		this.piece = this.queue.poll();

		this.l = SPAWN_L;
		this.c = SPAWN_C;

		if (this.piece.getType() == Type.O)
			this.c++;

		if (this.queue.size() < 8)
			this.queue.addAll(Piece.createBag());
	}

	private void calcPreview()
	{
		// TODO
	}

	private boolean place(int collisionL)
	{
		boolean[][] structure = this.piece.getStructure();
		char        type      = this.piece.getType();

		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[l].length; c++)
				if (structure[l][c])
					this.grid[this.l + l][this.c + c] = type;

		for (int l = SPAWN_L; l < SPAWN_L + 2; l++)
			for (int c = SPAWN_C; c < SPAWN_C + 4; c++)
				if (this.grid[l][c] != Type.VOID)
					return false;

		if (this.l + collisionL < 3)
			return false;

		this.pullPiece();
		return true;
	}
}
