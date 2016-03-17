package unitTests;
import mainPackage.Referee;
import mainPackage.Malelaud;
import mainPackage.Player;

public class Referee_GenericInputTest {
	//Klass, kus saab testida vigaseid sisendeid ilma et m�ng k�iks

	public static void main(String[] args) {
		Malelaud brd = new Malelaud();
		Player w = new Player();
		Player b = new Player();
		
		Referee ref = new Referee(brd, w, b);
		
		//Selleks tuleb kirjutada oma sisend siia (stringi kohale): 
		System.out.println(ref.Assess("F8F7"));
		
		// V�ljund on true, kui k�ik on lubatud. 
		// Enamikel juhtudel antakse ka verbaalset tagasisidet vea olemuse kohta.
	}
}