import java.util.Scanner;
public class TicTacToe 
{
	private Player player1;
	private Player player2;
	private Board board;

	public void startGame()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		// Input Players
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		while(player1.getSymbol() == player2.getSymbol())
		{
			System.out.println("Symbol already taken!");
			System.out.println("Pick another symbol.");
			char symbol = sc.next().charAt(0);
			player2.setSymbol(symbol);
		}

		// Create Board
		board = new Board(player1.getSymbol(), player2.getSymbol());

		// Conduct the Game
		boolean player1Turn = true;
		int status = Board.GAME_INCOMPLETE;
		while(status == Board.GAME_INCOMPLETE || status == Board.INVALID_MOVE)
		{
			if(player1Turn)
			{
				System.out.println("Player 1 - " + player1.getName() + "'s turn : ");
				System.out.println("Enter x coordinate : ");
				int x = sc.nextInt();
				System.out.println("Enter y coordinate : ");
				int y = sc.nextInt();

				status = board.move(player1.getSymbol(), x, y);
				if(status == Board.INVALID_MOVE)
				{
					System.out.println("Invalid move!");
					System.out.println("Try again...");
				}
				else
				{
					player1Turn = false;
					board.print();
				}
			}
			else
			{
				// player2 turn
				System.out.println("Player 2 - " + player2.getName() + "'s turn : ");
				System.out.println("Enter x coordinate : ");
				int x = sc.nextInt();
				System.out.println("Enter y coordinate : ");
				int y = sc.nextInt();

				status = board.move(player2.getSymbol(), x, y);
				if(status == Board.INVALID_MOVE)
				{
					System.out.println("Invalid move!");
					System.out.println("Try again...");
				}
				else
				{
					player1Turn = true;
					board.print();
				}
			}
		}
		if(status == Board.PLAYER_1_WINS)
		{
			System.out.println("Player 1 - " + player1.getName() + " wins.");
		}
		else if(status == Board.PLAYER_2_WINS)
		{
			System.out.println("Player 2 - " + player2.getName() + " wins.");
		}
		else
		{
			System.out.println("Game drawn!!!");
		}
	}

	private Player takePlayerInput(int n)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Player " + n + " name : ");
		String name = sc.nextLine();
		System.out.println("Enter Player " + n + " symbol : ");
		char symbol = sc.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}
}