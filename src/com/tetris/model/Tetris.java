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
		// TODO
		return false;
	}

	public boolean moveR()
	{
		// TODO
		return false;
	}

	public boolean moveL()
	{
		// TODO
		return false;
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
	}

	private void calcPreview()
	{
		// TODO
	}
}
