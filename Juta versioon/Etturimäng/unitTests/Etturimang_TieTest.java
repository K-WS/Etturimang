package unitTests;

import mainPackage.Malelaud;
import mainPackage.Player;
import mainPackage.Referee;

public class Etturimang_TieTest 
{
	static String[] kaigud = {"A5A4"};
	
	public static void main(String[] args) 
	{
		Malelaud malelaud = new Malelaud();
		Player valge = new Player();
		Player must = new Player();
		Referee ref = new Referee(malelaud, valge, must);
	

		for(int repeat = 1; repeat > 0; repeat--)
		{
			malelaud.hackSweep();
			malelaud.hackSquare("A5","v");
			malelaud.hackSquare("A3","m");
			malelaud.hackSquare("C5","v");
			malelaud.hackSquare("C4","m");
			//malelaud.hackSquare("B3","m");
			valge.hackPawnsLeft(2);
			must.hackPawnsLeft(2);
			System.out.println(malelaud);
			boolean praegune_mang = true;
		
			while(praegune_mang)
			{	
				for(String pseudoInput : kaigud)
				{
					ref.Assess(pseudoInput);
					malelaud.liigutus(pseudoInput);
					ref.tieCheck();
					praegune_mang = !ref.isGameOver();
					//System.out.println(praegune_mang);
				}
			}
			System.out.println("Valge l�pp-punktid: "+valge.getPoints()+", Musta l�pp-punktid: "+must.getPoints());
			if (repeat>1) 
			{	malelaud = new Malelaud();
				ref = new Referee(malelaud, valge, must);
				valge.resetPawnsLeft();
				must.resetPawnsLeft();
			}
		}
	}
}


