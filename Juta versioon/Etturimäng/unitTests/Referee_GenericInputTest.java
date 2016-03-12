package unitTests;
import mainPackage.Referee;
import mainPackage.Malelaud;
import mainPackage.Player;

public class Referee_GenericInputTest {

	public static void main(String[] args) {
		Malelaud brd = new Malelaud();
		Player w = new Player();
		Player b = new Player();
		
		Referee ref = new Referee(brd, w, b);
		System.out.println(ref.Assess("F8F7"));
	}
}