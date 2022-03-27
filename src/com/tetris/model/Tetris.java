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

	private int maxL;


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
	public int getMaxL()           { return this.maxL; }


	// Public methods
	public boolean moveD()
	{
		if (this.l == this.maxL)
			return this.place();

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
		this.calcMaxL();
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
		this.calcMaxL();
		return true;
	}

	public boolean turn()
	{
		this.piece.turnR();
		boolean[][] structure = this.piece.getStructure();

		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[l].length; c++)
				if (structure[l][c] && this.grid[this.l + l][this.c + c] != Type.VOID)
				{
					this.piece.turnL();
					return false;
				}

		this.calcMaxL();
		return true;
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

		this.calcMaxL();
	}

	private void calcMaxL()
	{
		boolean[][] structure = this.piece.getStructure();

		this.maxL = Integer.MAX_VALUE;
		int lTmp, cTmp;

		for (int c = 0; c < structure[0].length; c++)
			for (int l = structure.length - 1; l >= 0; l--)
				if (structure[l][c])
				{
					lTmp = this.l + l;
					cTmp = this.c + c;
					while (this.grid[lTmp][cTmp] == Type.VOID)
						lTmp++;
					System.out.println(lTmp);
					this.maxL = Math.min(this.maxL, lTmp - l - 1);
					break;
				}
	}

	private boolean place()
	{
		boolean[][] structure  = this.piece.getStructure();
		char        type       = this.piece.getType();
		int         collisionL = 0;

		// Search the line of collision
		collisionFound:
		for (int l = structure.length - 1; l >= 0; l--)
			for (int c = 0; c < structure[l].length; c++)
				if (structure[l][c])
				{
					collisionL = l;
					break collisionFound;
				}

		// "Printing" the piece on the grid
		for (int l = 0; l < structure.length; l++)
			for (int c = 0; c < structure[l].length; c++)
				if (structure[l][c])
					this.grid[this.l + l][this.c + c] = type;

		// Loosing conditions
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
