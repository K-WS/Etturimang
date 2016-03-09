package mainPackage;
import java.util.Scanner;

public class Etturimang {
	
	private static void tutvustaMangu() 
	{
		System.out.println("Etturim�ng: ");
		System.out.println("1.K�igu sisestamine");
		System.out.println("    Vastavalt sellele, kelle k�ik on praegu, tuleb kirjutada neljas�mboline string");
		System.out.println("    n�iteks A7A6, kus esimesed 2 s�mbolit t�histavad malendi esialgset asukohta");
		System.out.println("    ja viimased 2 asukohta, kuhu soovid liigutada oma etturit.");
		System.out.println();
		System.out.println("2.M�ngu �lesanne");
		System.out.println("    j�uda �he oma etturitega l�puni v�lja, v�i v�tta maha k�ik vastase etturid.");
		System.out.println("K�ikide muude reeglite poolest k�ib see samamoodi nagu tavaline male.");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		tutvustaMangu();
		
		Scanner sc = new Scanner(System.in);
		int kaik = 1;
		String sisend;
		
		Malelaud malelaud = new Malelaud();
		Player valge = new Player();
		Player must = new Player();
		Referee ref = new Referee(malelaud, valge, must);
		
		System.out.println(malelaud);
		boolean kas_mangid_edasi = true;
		
		//While-ts�kkel, mis kestab niikaua, kuni m�ngija enam ei taha m�ngida.
		while(kas_mangid_edasi)
		{
			boolean praegune_mang = true;
			
			//While ts�kkel, mis kestab niikaua, kuni m�ng saab l�bi
			while(praegune_mang)
			{
				System.out.println("Sisestage k�ik, "+(kaik==1? "valge" : "must"));
				boolean refLubab = false;
				
				do
				{
					sisend = sc.nextLine().toUpperCase();
					refLubab = ref.Assess(sisend);
					if(refLubab) 
					{ 
						malelaud.liigutus(sisend);
						praegune_mang = !ref.gameOver();	//m�ng j�tkub, kui kohtunik ei �tle vastupidist
						if(praegune_mang) 
						{	
							kaik = (kaik+1)%2; 				//m�ngu j�tkudes on m�tet k�iku vahetada
							ref.nextTurn(); 
						}		
					}
				}
				while(!refLubab); 		//ts�klit korratakse, kuni kohtunik sisendi heaks kiidab
			}
			
			System.out.println("Valge l�pp-punktid: "+valge.getPoints()+", Musta l�pp-punktid: "+must.getPoints());
			System.out.println("Kas soovid edasi m�ngida? (jah/ei)");
			boolean onVastus = true;
			while(onVastus)
			{
				String arg = sc.nextLine();
				if(arg.equals("jah"))
				{
					onVastus = false;
					malelaud = new Malelaud();
					ref = new Referee(malelaud, valge, must);
					praegune_mang = true;
					System.out.println(malelaud);
					valge.resetPawnsLeft();
					must.resetPawnsLeft();
				}
				else if(arg.equals("ei"))
				{
					onVastus = false; 
					kas_mangid_edasi = false;
					sc.close();
				}
			}
		}		
	}
}
