import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Etturimang {

	public static void main(String[] args) {
		Malelaud_2 asi = new Malelaud_2();
			HashMap<String, ArrayList<String>> a = asi.create();
			System.out.println(a);
			
			for (int i = 0; i<8; i++){
				System.out.println(a.get("A").get(1));}
			
			asi.laud.get("C").remove(1);
			asi.laud.get("C").add(1," ");
			asi.laud.get("C").remove(1+2);
			asi.laud.get("C").add(1+2, "v");
			System.out.println(asi.toString());

			
			
	}

}
