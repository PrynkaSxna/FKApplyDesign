package ttt;

import java.util.*;

interface board
{
	public boolean insert(int x, int y, char symbol);
	public boolean win();
	public boolean is_Full();
	public void view();
}

class hex_board implements board
{
	char[][] hboard;
	int m;
	int n;
	hex_board(int m, int n)
	{
		this.m = m;
		this.n = n;
		board = new char[m][n];
		int a=0;
		char[] pos = new char[2];
		pos[0] = '*';
		pos[1] = '_';
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				board[i][j]=pos[a];
				a = (a+1)%2;
			}
		}
	}
	public boolean insert(int x, int y, char symbol)
	{
		if(board[i][j]=='*')
		{
			System.out.println("Invalid index.\n");
			return false;
		}
		if(board[i][j]!='_')
		{
			System.out.println("Non-empty cell.\n");
			return false;
		}	
		board[i][j]=symbol;
		return true;
	}
	public boolean is_Full()
	{
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(board[i][j]=='_')
					return false;
			}
		}	
		return true;
	}
	public void view()
	{
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.print(board[i][j]);			}
		}	
		System.out.println(' ');
	}
	private boolean col()
	{
		int start;
		char c;
		int i;
		int j;
		for(i=0; i<n; i++)
		{
			start = (i+1)%2;
			c = board[start][i];
			if(c == '_')
				continue;
			for(j=start+2; j<m; j=j+2)
			{
				if(board[j][i] != c)
					break;
			}
			if(j>=m)
				return true;
		} 
		return false;
	}
	private boolean diag1()
	{
		int i;
		int j;
		char c;
		for(int i=0; i<m; i=i+2)
		{
			c=board[i][0];
			if(c=='_')
				continue;
			for(int j=0; j<n; j++)
			{
				if(board[i][j]!=c)
					break;
			}
			if(j==n)
				return true;
		}
		return false;
	}
	private boolean diag2()
	{
		int i;
		int j;
		char c;
		for(int i=0; i<m; i=i+2)
		{
			c=board[i][n-1];
			if(c=='_')
				continue;
			for(int j=n-1; j>=0; j--)
			{
				if(board[i][j]!=c)
					break;
			}
			if(j<0)
				return true;
		}
		return false;
	}
	public boolean win()
	{
		return (col() | diag1() | diag2());
	}
}


/**To incorporate irregular boards, the constructor is changed such that the char board has a * to indicate all cells which don't exist on the board and therefore correspond to illegal moves**/