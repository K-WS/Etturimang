import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Etturimang {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int kaik = 1;
		
		Malelaud malelaud = new Malelaud();
		Player valge = new Player("v");
		Player must = new Player("m");
		Referee ref = new Referee(malelaud, valge, must);
		
		System.out.println(malelaud);
		boolean kas_mangid_edasi = true;
		//While-tsükkel, mis kestab niikaua, kuni mängija enam ei taha mängida.
		while(kas_mangid_edasi){
			boolean praegune_mang = true;
			//While tsükkel, mis kestab niikaua, kuni mäng saab läbi
			while(praegune_mang){
				

				//While tsükkel, mis kestab niikaua, kui mängija pole oma käiku sisestanud.
				while(kaik == 1){System.out.println("Sisesta käik, valge ");
				String a = sc.nextLine().toUpperCase();
				if(a == "Restart"){malelaud.restart();break;}
				boolean b = ref.Assess(a);
				if (b==true){
					malelaud.liigutus(a);
					kaik = 0;
					ref.nextTurn();

				}
				}
				//Teine While tsükkel, mis kestab niikaua, kui mängija pole oma käiku sisestanud.
				while(kaik == 0){System.out.println("Sisesta käik, must ");
				String a = sc.nextLine().toUpperCase();
				boolean b = ref.Assess(a);
				if (b==true){
					malelaud.liigutus(a);
					kaik = 1;
					ref.nextTurn();

				}
				praegune_mang = !ref.gameOver();
				
				}
				
				System.out.println(malelaud);
			}
			System.out.println("Valge lõpp-punktid: "+valge.getPoints()+", Musta lõpp-punktid: "+must.getPoints());
			System.out.println("Kas soovid edasi mängida? (jah/ei)");
			boolean onVastus = true;
			while(onVastus){
				String arg = sc.nextLine();
				if(arg.equals("jah")){onVastus = false;}
				//tee klassi Malelaud uus meetod, mis "restardib" malendite ja laua algseisu, ja kasuta seda siin arg.equals("jah") korral.
				else if(arg.equals("ei")){onVastus = false; kas_mangid_edasi = false;}}
		}		
	}
}
