public class Board 
{
	private char board[][];
	private int boardSize = 3;
	private char p1Symbol;
	@SuppressWarnings("unused")
	private char p2Symbol;
	// number of moves played
	private int count;  

	/*
	 * Status :
	 *  1 -> Player 1 won
	 *  2 -> Player 2 won
	 *  3 -> Draw
	 *  4 -> Game incomplete 
	 *  5 -> Invalid move
	 */
	public final static int PLAYER_1_WINS = 1;
	public final static int PLAYER_2_WINS = 2;
	public final static int DRAW = 3;
	public final static int GAME_INCOMPLETE = 4;
	public final static int INVALID_MOVE = 5;

	public Board(char p1Symbol, char p2Symbol)
	{
		board = new char[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
				board[i][j] = ' ';
			}
		}
		this.p1Symbol = p1Symbol;
		this.p2Symbol = p2Symbol;
	}

	public int move(char symbol, int x, int y)
	{
		if(x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != ' ')
		{
			return INVALID_MOVE;
		}
		board[x][y] = symbol;
		count++;

		// check horizontally
		if(board[x][0] == board[x][1] && board[x][0] == board[x][2])
		{
			return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
		}
		// check vertically
		if(board[0][y] == board[1][y] && board[0][y] == board[2][y])
		{
			return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
		}
		// check left diagonal
		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2])
		{
			return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
		}
		// check right diagonal
		if(board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0])
		{
			return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
		}
		// check for draw condition
		if(count == boardSize*boardSize)
		{
			return DRAW;
		}
		return GAME_INCOMPLETE;
	}

	public void print()
	{
		System.out.println("-----------------");
		for(int i = 0; i < boardSize; i++)
		{
			System.out.print(" ");
			for(int j = 0; j < boardSize; j++)
			{
				System.out.print("| " + board[i][j] + " |");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}
}
