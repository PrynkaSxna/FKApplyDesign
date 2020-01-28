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
		for(int i=0; i<this.n; i++)
		{
		    for(int j=0; j<this.n; j++)
		        t_board[i][j]='_';
		}
	}

	public boolean insert(int x, int y, char symbol)
	{
		if(x<1 || x>this.n || y<1 || y>this.n)
		{
			System.out.println("Invalid index. Retry.");
			return false;
		}
		else if(t_board[x-1][y-1] != '_')	
		{
			System.out.println("Cell not empty. Retry");
			return false;
		}
		t_board[x-1][y-1] = symbol;
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
				if(this.t_board[i][j] != this.t_board[i][0] || this.t_board[i][0] == '_')
					break;
			}
			if(j == this.n)
			{
			    return true;
			}
		}
		return false;
	}

	private boolean column()
	{
		int i;
		int j;
		for(i=1; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(this.t_board[j][i] != this.t_board[j][0] || this.t_board[j][0] == '_')
					break;
			}
			if(j == this.n)
			{
			    return true;
			}
		}
		return false;
	}

	private boolean diagonal1()
	{
		int i;
		for(i=1; i<this.n; i++)
		{
			if(this.t_board[0][0] == '_')
			    return false;
			if(this.t_board[i][i] != this.t_board[0][0])
				return false;
		}
		return true;
	}

	private boolean diagonal2()
	{
		int i;
		for(i=1; i<this.n; i++)
		{
			if(this.t_board[0][this.n-1] == '_')	
			    return false;
			if(this.t_board[i][n-i-1] != this.t_board[0][this.n-1])
			    return false;
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
				if(this.t_board[i][j] == '_')
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
				if(this.t_board[i][j] == '_')
				{
					position[0]=i;
					position[1]=j;
				}
			}
		}
		return position;
	}
}

class board9
{
	int n;
	ttt_board[][] b9;
	board9(int n)
	{
		this.n = n;
		b9 = new ttt_board[n][n];
		for(int i=0; i<this.n; i++)
		{
		    for(int j=0; j<this.n; j++)
		        b9[i][j] = new ttt_board(this.n);
		}
	}

	public boolean insert(int b1, int b2, int x, int y, char symbol)
	{
		if(b1<1 || b1>this.n || b2<1 || b2>this.n)
		{
			System.out.println("Invalid block index. Retry.");
			return false;
		}
		return b9[b1-1][b2-1].insert(x,y,symbol);
	}

	private boolean row()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(!b9[i][j].win())
					break;
			}
			if(j == this.n)
			{
			    return true;
			}
		}
		return false;
	}

	private boolean column()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(!b9[j][i].win())
					break;
			}
			if(j == this.n)
			{
			    return true;
			}
		}
		return false;
	}

	private boolean diagonal1()
	{
		int i;
		for(i=0; i<this.n; i++)
		{
			if(!b9[i][i].win())
				return false;
		}
		return true;
	}

	private boolean diagonal2()
	{
		int i;
		for(i=0; i<this.n; i++)
		{
			if(!b9[i][n-i-1].win())
			    return false;
		}
		return true;
	}

	public boolean win()
	{
		 return (this.row() | this.column() | this.diagonal1() | this.diagonal2());
	}

	public boolean is_Full()
	{
		int i;
		int j;
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(!b9[i][j].is_Full())
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
				System.out.printf("Block %d %d\n",i,j);
				b9[i][j].view();
			}
		}
	}
	
	public int[] get_empty()
	{
		int i;
		int j;
		int[] position = new int[4];
		for(i=0; i<this.n; i++)
		{
			for(j=0; j<this.n; j++)
			{
				if(!b9[i][j].is_Full())
				{
					int[] pos2 = b9[i][j].get_empty();
					position[0]=i;
					position[1]=j;
					position[2]=pos2[0];
					position[3]=pos2[1];
					break;
				}
			}
		}
		return position;
	}
}

class player
{
    int b1;
    int b2;
    int x;
    int y;
    public void input()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter b1:");
        b1 = in.nextInt();
        System.out.println("Enter b2:");
        b2 = in.nextInt();
        System.out.println("Enter x:");
        x = in.nextInt();
        System.out.println("Enter y:");
        y = in.nextInt();
    }
    public void input(int[] position)
    {
        b1 = position[0];
        b2 = position[1];
        x = position[2];
        y = position[3];
    }
    public int get_b1()
    {
        return b1;
    }
    public int get_b2()
    {
        return b2;
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
		board9 tb = new board9(3);
		player p1 = new player();
		player p2 = new player();
           int p;
           System.out.println("Enter 1 to play with machine and 2 to play with human:");
           Scanner in = new Scanner(System.in);
		p = in.nextInt();
		System.out.println("Enter block indexes in range 1-3, followed by sub-block indexes in range 1-3.");
		boolean chance = true;
		while(!tb.is_Full())
		{
		    if(chance)
		    {
		        System.out.println("Player 1's chance.");
		        if(p == 1)
		        {
		            int[] pos = new int[4];
		            pos = tb.get_empty();
		            p1.input(pos);
		        }
		        else
		        {
		            p1.input();
		        }
		        tb.insert(p1.get_b1(),p1.get_b2(),p1.get_x(),p1.get_y(),'X');
		    }
		    else
		    {
		        System.out.println("Player 2's chance.");
		        p2.input();
		        tb.insert(p2.get_b1(),p2.get_b2(),p2.get_x(),p2.get_y(),'O');
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
			chance = !chance;
		}
	    System.out.println("Game Over!");
	}
}