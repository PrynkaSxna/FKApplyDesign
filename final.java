package ttt;

import java.util.*;

//interface board for board_sq and board_hex
//ttt_board class to abstract the details of board structure
//class player_move for player's current move
//class game to simulate the current status of game

interface board
{
	//m indicates depth with 3x3 having level 1, i and j are indexing parameters
	public boolean win(int i, int j, int m, char symbol);	
	public boolean is_full(int i, int j, int m);
	public boolean insert(int x, int y, char symbol);
	public void view();
}  

class board_sq implements board
{
	//i and j are starting index of square block
	int n;
	char[][] board;

	board_sq(int n)
	{
		this.n = n;
		board = new char[n][n];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				board[i][j]='_';
			}
		}
	}

	public boolean is_full(int i, int j, int m)
	{
		for(int a=i; a<i+3*m && a<n; a++)
		{
			for(int b=j; b<j+3*m && b<n; b++)
			{
				if(board[a][b]=='_')
					return true;
			}
		}
		return false;
	}

	
	public boolean insert(int x, int y, char symbol)
	{
		if(x<1 || x>n || y<1 || y>n)
		{
			System.out.println("Invalid index.");
			return false;
		}
		if(board[x][y] != '_')
		{
			System.out.println("Non-empty cell.");
			return false;
		}
		board[x][y]=symbol;
		return true;
	}

	public void view()
	{
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
				System.out.print(board[i][j]);
			System.out.println(' ');
		}
	}

	private boolean row(int i, int j, int m, char symbol)
	{
		int a;
		int b;
		for(a=i; a<i+3*m && a<n; a+=n)
		{
			for(b=j; b<j+3*m && b<n; b+=n)
			{
				if(!win(a,b,m-1,symbol))
					break;
			}
			if(b==j+3*m || b==n)
				return true;
		}
		return false;
	}
	
	private boolean col(int i, int j, int m, char symbol)
	{
		int a;
		int b;
		for(a=j; a<j+3*m && a<n; a+=n)
		{
			for(b=i; b<i+3*m && b<n; b+=n)
			{
				if(!win(b,a,m-1,symbol)
					break;
			}
			if(b==i+m || b==n)
				return true;
		}
		return false;
	}

	private boolean diag1(int i, int j, int m, char symbol)
	{
		int a;
		for(a=0; a<3*m && a<n; a+=n)
		{
			if(!win(i+a,j+a,m-1,symbol))
				return false;		
		}
		return true;
	}

	private boolean diag2(int i, int j, int m, char symbol)
	{
		int a;
		for(a=0; a<3*m && a<n; a++)
		{
			if(!win(i+a,j-a,m-1,symbol))
				return false;		
		}
		return true;
	}

	public boolean win(int i, int j, int m, char symbol)
	{
		if (m==0)
			return board[i][j] == symbol;
		return row(i,j,m,symbol) | col(i,j,m,symbol) | diag1(i,j,m,symbol) | diag2(i,j,m,symbol);
	}
}

class board_hex implements board
{
	//i and j are centre of hex block
	int n;
	int m;
	char[][] board;

	board_state(int n1, int n2)
	{
		this.n1 = n;
		this.n2 = m;
		board = new char[n][m];
		for(int i=0; i<n1; i++)
		{
			for(int j=0; j<; j++)
			{
				board[i][j]='_';
				//tiles which do not exist are marked by '*' to indicate illegal moves
			}
		}
	}

	public boolean is_full(int i, int j, int m)
	{
		for(int a=Math.max(0,i-m); a<Math.min(n1,i+m); a++)
		{
			for(int b=Math.max(0,j-m); b<Math.min(n2,j+m); b++)
			{
				if(board[a][b]=='_')
					return true;
			}
		}
		return false;
	}

	
	public boolean insert(int x, int y, char symbol)
	{
		if(x<1 || x>n || y<1 || y>n)
		{
			System.out.println("Invalid index.");
			return false;
		}
		if(board[x][y] != '_')
		{
			System.out.println("Non-empty cell.");
			return false;
		}
		if(board[x][y] == '*')
		{
			System.out.println("Illegal move");
			return false;
		}
		board[x][y]=symbol;
		return true;
	}

	public void view()
	{
		for(int i=0; i<n; i++)
		{
			for(int j=1; j<n; j+=2)
			{
				System.out.print(' ');
				System.out.print(board[i][j]);	
			}
			System.out.println(' ');
			for(int j=0; j<n; j+=2)
			{
				System.out.print(board[I][j]);
				System.out.print(' ');
			}
			System.out.println(' ');
		}
	}
	
	//to do: implement a private helper to populate hex neighbours for win()

	private boolean col(int i, int j, int m, char symbol)
	{
		int a;
		for(a=Math.max(0,j-m); a<Math.min(j+m,n2); a++)
		{
			if(board[i][a]!=symbol)
				return false;
		}
		return true;
		//to do: extend for general case
	}

	private boolean diag1(int i, int j, int m, char symbol)
	{
		return board[i][j] == symbol & board[i][j-1] == symbol & board[i-1][j+1] == symbol;  
		//to do: extend for general case
	}

	private boolean diag2(int i, int j, int m, char symbol)
	{
		return board[i-1][j-1] == symbol & board[i][j] == symbol & board[I][j+1] == symbol;  
		//to do: extend for general case	
	}

	public boolean win(int i, int j, int m, char symbol)
	{
		if (m==0)
			return board[i][j] == symbol;
		return col(i,j,m,symbol) | diag1(i,j,m,symbol) | diag2(i,j,m,symbol);
	}
}


class ttt_board
{
	board bb; //member of interface type
	int depth;
	ttt_board(int depth, board bb)
	{
		this.depth=depth;
		this.bb=bb;
	}
	public board get_obj()
	{
		return bb;
	}
}


class player_move
{
	int x;
	int y;
	
	public void set_input(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int get_x()
	{
		return x;
	}

	public int get_y()
	{
		return y;
	}
}

class game
{
	player_move p1;
	player_move p2;
	ttt_board bb;
	int depth;

	game(board tb, int depth)
	{
		bb = new ttt_board(depth, tb);
		this.depth = depth;
		p1 = new player_move();
		p2 = new player_move();
	}

}
