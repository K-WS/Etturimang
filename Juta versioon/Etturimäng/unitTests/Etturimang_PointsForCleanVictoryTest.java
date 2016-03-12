package unitTests;
import mainPackage.Malelaud;
import mainPackage.Player;
import mainPackage.Referee;

public class Etturimang_PointsForCleanVictoryTest {
	
	static String[] kaigud = {"A7B6"};

	public static void main(String[] args) 
	{		
		Malelaud malelaud = new Malelaud();
		Player valge = new Player();
		Player must = new Player();
		Referee ref = new Referee(malelaud, valge, must);
	

		for(int repeat = 2; repeat > 0; repeat--)
		{
			malelaud.hackSweep();
			malelaud.hackSquare("A7","v");
			malelaud.hackSquare("B6", "m");
			valge.hackPawnsLeft(1);
			must.hackPawnsLeft(1);
			System.out.println(malelaud);
			boolean praegune_mang = true;
		
			while(praegune_mang)
			{	
				for(String pseudoInput : kaigud)
				{
					ref.Assess(pseudoInput);
					malelaud.liigutus(pseudoInput);
					praegune_mang = !ref.isGameOver();
				}
			}
			System.out.println("Valge lõpp-punktid: "+valge.getPoints()+", Musta lõpp-punktid: "+must.getPoints());
			if (repeat>1) 
			{	malelaud = new Malelaud();
				ref = new Referee(malelaud, valge, must);
				valge.resetPawnsLeft();
				must.resetPawnsLeft();
			}
		}
	}
}


