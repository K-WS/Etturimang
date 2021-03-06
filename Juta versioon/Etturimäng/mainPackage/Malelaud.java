package mainPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Malelaud {
	
	//T�ev��rtustega v�li, mis osaleb en passant olukorra lahendamises
	private boolean[] enPassant = {false, false};
		public void enPassant(boolean leftToRight){
			System.out.println("Malelaud.enPassant()");
			enPassant[0] = true;
			enPassant[1] = leftToRight;
		}
	
	//S�nastik, mis esindab malendite asukohti malelaual
	HashMap<String, ArrayList<String>> laud;
	
	//Loend, mis sisaldab t�hti A-H (vajalik s�nastikust andmete k�ttesaamiseks)
	public ArrayList<String> tahehoius = new ArrayList<>();
	
	public Malelaud() 
	{
		tahehoius.addAll(Arrays.asList("A","B","C","D","E","F","G","H"));
		laud = createBoard();
	}
	
	//Abistav meetod Malelaua loomisele
	HashMap<String, ArrayList<String>> createBoard()
	{
		ArrayList<String> malerida = new ArrayList<>();
		malerida.addAll(Arrays.asList(" ", "v", " ", " ", " ", " ", "m", " "));
		
		HashMap <String,ArrayList<String>> Malelauakatse = new HashMap<>();

		for (int i = 0; i<8; i++)
		{
			Malelauakatse.put(tahehoius.get(i), new ArrayList<>(malerida));
		}
		return Malelauakatse;
	}
	
	//Tavap�rane toString, mis tagastab malelaua praeguse seisu konsoolile
	public String toString(){
		
		String nr = "   8|7|6|5|4|3|2|1\n";
		for(String x : tahehoius){
			for(int i = 0; i<8; i++){
				if (i==0) nr += x+" |";
				if (laud.get(x).get(i) != " ") nr += laud.get(x).get(i)+"|";
				else nr += "-|";
				if (i==7) nr += "\n";
			}
		}		
		return nr;
	}
	
	//Meetod, mis l�ikab korrektse (eelnevalt kohtuniku klassist l�bi k�inud) stringi t�kkideks, ning selle p�hjal muudab malelaua v�limust.
	public void liigutus(String kask){
		String th1 = Character.toString(kask.charAt(0));
		int nr1 = Math.abs(Character.getNumericValue(kask.charAt(1)) -8);
		String th2 = Character.toString(kask.charAt(2));
		int nr2 = Math.abs(Character.getNumericValue(kask.charAt(3))-8);
		
		String pawn = laud.get(th1).get(nr1);
		laud.get(th1).set(nr1," ");
		laud.get(th2).set(nr2, pawn);
		
		if (enPassant[0]) 
		{
			boolean leftToRight = enPassant[1];
			pawn = laud.get(th2).get(nr2);
			laud.get(th2).set(nr2," ");
			
					
			laud.get(th2).set(nr2 + (leftToRight? 1 : (-1)), pawn);
			enPassant[0] = false;
		}
		
		System.out.println(toString());		
	}
	
	//Meetod, mis tagastab malendi asukoha
	public String getPawn(String th, int nr) 
	{ 
		return laud.get(th).get(Math.abs(nr-8)); 
	}

	
	////H�kkimismeetodid kiireks testimiseks
	//
	//meetod, mis asetab soovitud malendi soovitud ruudule
	public void hackSquare(String input, String color)
	{
		String th1 = Character.toString(input.charAt(0));
		int nr1 = Math.abs(Character.getNumericValue(input.charAt(1))-8);
		laud.get(th1).set(nr1, color);
	}
	//
	//meetod, mis teeb malelaua t�iesti t�hjaks
	public void hackSweep()
	{
		for(String ltr : tahehoius)
		{
			for(int i = 0; i<8; i++)
			{
				laud.get(ltr).set(i, " ");
			}
		}
	}

	
}	