

public class Referee_UnitTest {

	public static void main(String[] args) {
		Malelaud brd = new Malelaud();
		Player w = new Player("v");
		Player b = new Player("m");
		
		Referee ref = new Referee(brd, w, b);
		System.out.println(ref.Assess("A2A3"));
	}

}