package ttt;

import java.util.Scanner; 


interface board
{
    public boolean insert(int x, int y, char symbol);
	 public boolean win();	
	 public boolean is_Full();
	 public void view();
} 


class ttt_board implements board
{
	int n;
	char[][] t_board;

	ttt_board(int n)
	{
		this.n = n;
		t_board = new char[n][n];
	}

	public boolean insert(int x, int y, char symbol)
	{
		if(x<1 || x>this.n || y<1 || y>this.n)
		{
			System.out.println("Invalid index. Retry.");
			return false;
		}
		else if(t_board[x-1][y-1] != '\u0000')	
		{
			System.out.println("Cell not empty. Retry");
			return false;
		}
		t_board[x][y] = symbol;
		return true;
	}

	private boolean row()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=1; j<this.n; j++)
			{
				if(this.t_board[i][j] != this.t_board[i][0])
					break;
			}
			if(j == this.n)
				return true;
		}
		return false;
	}

	private boolean column()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=1; j<this.n; j++)
			{
				if(this.t_board[j][i] != this.t_board[j][0])
					break;
			}
			if(j == this.n)
				return true;
		}
		return false;
	}

	private boolean diagonal1()
	{
		int i;
		for(i=1; i<this.n; i++)
		{
			if(this.t_board[i][i] != this.t_board[0][0])
				return false;
		}
		return true;
	}

	private boolean diagonal2()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=this.n-2; j<=0; j--)
			{
				if(this.t_board[i][j] != this.t_board[0][n-1])
					return false;
			}		
		}
		return true;
	}

	public boolean win()
	{
		 return (row() | column() | diagonal1() | diagonal2());
	}

	public boolean is_Full()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(this.t_board[i][j] == '\u0000')
					return false;
			}
		}
		return true;
	}

	public void view()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				System.out.print(this.t_board[i][j]);	
				System.out.print(' ');
			}
			System.out.println(' ');
		}
	}
	
	public int[] get_empty()
	{
		int i;
		int j;
		int[] position = new int[2];
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(this.t_board[i][j] == '\u0000')
				{
					position[0]=i;
					position[1]=j;
				}
			}
		}
		return position;
	}
}


class player
{
    int x;
    int y;
    public void input()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter x:");
        x = in.nextInt();
        System.out.println("Enter y:");
        y = in.nextInt();
    }
    public void input(int[] position)
    {
        x = position[0];
        y = position[1];
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


public class game
{
	public static void main(String[] args) 
	{
		ttt_board tb = new ttt_board(3);
		player p1 = new player();
		player p2 = new player();
           int p;
           System.out.println("Enter 1 to play with machine and 2 to play with human:");
           Scanner in = new Scanner(System.in);
		p = in.nextInt();
		boolean chance = true;
		while(!tb.is_Full())
		{
		    if(chance)
		    {
		        if(p == 1)
		        {
		            int[] pos = new int[2];
		            pos = tb.get_empty();
		            p1.input(pos);
		        }
		        else
		        {
		            p1.input();
		        }
		        tb.insert(p1.get_x(),p1.get_y(),'X');
		    }
		    else
		    {
		        p2.input();
		        tb.insert(p2.get_x(),p2.get_y(),'O');
		    }
		    tb.view();
		    if(tb.win())
		    {
		        if(chance)
		        {
		            System.out.println("Player1 won!");
		        }
		        else
		        {
		            System.out.println("Player2 won!");
		        }
		        break;
		    }
		}
	    System.out.println("Game Over!");
	}
}