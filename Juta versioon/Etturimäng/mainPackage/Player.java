package mainPackage;


public class Player {
	
	private int pawnsLeft;
		public int getPawnsLeft() 
		{ 
			return pawnsLeft; 
		}
		public void pawnKilled() 
		{
			pawnsLeft--;
		}
		public void resetPawnsLeft()
		{
			pawnsLeft = 8;
		}
		
	private int points;
		public int getPoints() 
		{ 
			return points; 
		}
		public void wonTheMatch()
		{
			points++;
		}
	
	public Player() 
	{
		pawnsLeft = 8;
		points = 0;
	}

	public void hackPawnsLeft(int i)
	{
		while(pawnsLeft>i)
		{
			pawnKilled();
		}
	}
}
