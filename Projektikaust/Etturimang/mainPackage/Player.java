package mainPackage;

public class Player {
	
	byte pawnsLeft = 8;
		public byte getPawnsLeft() { return pawnsLeft; }
		
	int points = 0; 
	
	public void pawnKilled()
	{
		pawnsLeft--;
	};
	
	void wonTheMatch() 
	{
		points++;
	}
	
}
