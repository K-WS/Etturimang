package unitTests;

import mainPackage.Malelaud;
import mainPackage.Player;
import mainPackage.Referee;


/**
 * Klass viigiseisude kiireks l�biproovimiseks.
 */
public class Etturimang_TieTest 
{
	
	/** Massiiv, mis sisaldab k�ike, mida teostatakse automaatselt (paarisindeksitel valge k�igud, paaritutel musta k�igud) */
	static String[] kaigud = {"A5A4"};

	public static void main(String[] args) 
	{
		Malelaud malelaud = new Malelaud();
		Player valge = new Player();
		Player must = new Player();
		Referee ref = new Referee(malelaud, valge, must);
	
		//Suurendades repeat v��rtust saab vaadata, kuidas muutub musta v�i valge punktisumma vastavalt v�itude arvule
		for(int repeat = 1; repeat > 0; repeat--)
		{
			
			//.hackSweep() asetab soovitud malendi etten�idatud ruudule
			malelaud.hackSweep();
			malelaud.hackSquare("A5","v");
			malelaud.hackSquare("A3","m");
			malelaud.hackSquare("C5","v");
			malelaud.hackSquare("C4","m");
			//malelaud.hackSquare("B3","m");
			
			// Arvestada tuleb sellega, et m�ngu toimimiseks peab malelaual olevate malendite arv vastama kummagi m�ngija isendisv�ljale pawnsLeft,
			// malelauda h�kkimise j�rel (�lalpool) tuleb manuaalselt m��rata allesj��nud malendite arv meetodiga .hackPawnsLeft()
			valge.hackPawnsLeft(2);
			must.hackPawnsLeft(2);
			System.out.println(malelaud);
			boolean praegune_mang = true;
			
			//Nagu teisteski testiklassides kujutab peats�kkel lihtsuse m�ttes l�hendatud m�nguts�klit. 
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
			
			//Juhul kui soovite teha mitu identset m�ngu j�rjest, tuleb luua uued isendid (v�i taastada isendiv�ljade algseis)
			if (repeat>1) 
			{	malelaud = new Malelaud();
				ref = new Referee(malelaud, valge, must);
				valge.resetPawnsLeft();
				must.resetPawnsLeft();
			}
		}
	}
}


