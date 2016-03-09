import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class Malelaud {
	
	HashMap<String, ArrayList<String>> laud;
	public ArrayList<String> tahehoius = new ArrayList<>();
	
	
	//Malelaua loomise meetod
	public Malelaud() {
		tahehoius.addAll(Arrays.asList("A","B","C","D","E","F","G","H"));
		laud = create();
	}
	//Abistav meetod Malelaua loomisele, et 
	HashMap<String, ArrayList<String>> create(){
	ArrayList<String> malerida = new ArrayList<>();
	malerida.addAll(Arrays.asList(" ", "v", " ", " ", " ", " ", "m", " "));
	
	HashMap <String,ArrayList<String>> Malelauakatse = new HashMap<>();

		for (int i = 0; i<8; i++){
			Malelauakatse.put(tahehoius.get(i), new ArrayList<>(malerida));
		}
		return Malelauakatse;
	}
	
	//Tavapärane toString, mis tagastab malelaua praeguse seisu konsoolile
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
	
	
	//Meetod, mis lõikab korrektse stringi tükkideks, ning selle põhjal
	//muudab malelaua välimust.
	public void liigutus(String kask){
		String th1 = Character.toString(kask.charAt(0));
		int nr1 = Math.abs(Character.getNumericValue(kask.charAt(1)) -8);
		String th2 = Character.toString(kask.charAt(2));
		int nr2 = Math.abs(Character.getNumericValue(kask.charAt(3))-8);
		
		
		String p = laud.get(th1).get(nr1);
		laud.get(th1).set(nr1," ");
		laud.get(th2).set(nr2, p);
		System.out.println(toString());
	}
	
	//Meetod, mis tagastab malendi asukoha
	public String getPawn(String th, int nr) 
	{ 
		return laud.get(th).get(Math.abs(nr-8)); 
	}
	
}
