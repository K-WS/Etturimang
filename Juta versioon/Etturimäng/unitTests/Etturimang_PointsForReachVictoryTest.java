package unitTests;

import mainPackage.Malelaud;
import mainPackage.Player;
import mainPackage.Referee;


/**
 * The Class Etturimang_PointsForReachVictoryTest - klass sellise v�iduolukorra testimiseks, kus malend on j�udnud laua teise otsa
 */
public class Etturimang_PointsForReachVictoryTest 
{
		/** Massiiv teostatavate automaatk�ikudega */
		static String[] kaigud = {"A2A1"};

		
		public static void main(String[] args) 
		{		
			Malelaud malelaud = new Malelaud();
			Player valge = new Player();
			Player must = new Player();
			Referee ref = new Referee(malelaud, valge, must);
		

			for(int repeat = 3; repeat > 0; repeat--)
			{
				
				//Antud olukorras asetame valge etturi ��re l�hedale ja teostame tema viimase k�igu
				//Vajalik on ka v�hemalt �he musta etturi olemasolu, muidu oleks valge juba v�itnud. 
				malelaud.hackSweep();
				malelaud.hackSquare("A2","v");
				malelaud.hackSquare("B6","m");
				valge.hackPawnsLeft(1);
				must.hackPawnsLeft(1);
				System.out.println(malelaud);
				boolean praegune_mang = true;
			
				//L�hendatud versioon p�hiklassi m�nguts�klist
				while(praegune_mang)
				{	
					for(String pseudoInput : kaigud)
					{
						ref.Assess(pseudoInput);
						malelaud.liigutus(pseudoInput);
						praegune_mang = !ref.isGameOver();
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


