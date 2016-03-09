import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class HashidegaLaud {
	public static void main(String[] args){

		
		HashMap <String,ArrayList<String>> Malelauakatse = new HashMap<>();
		ArrayList<String> Rida_1 = new ArrayList<>();
		for(int i=0; i<8;i++){
			if(i== 1){Rida_1.add(i, "v");}
			else if(i==6){Rida_1.add(i, "m");}
			else{Rida_1.add(i, "-");}
		}
		Malelauakatse.put("A", Rida_1);
		System.out.println(Malelauakatse);
		//ebakorraliku koodi lisamine, et näha dict-tabeli lõppvälimust.
		Malelauakatse.put("B", Rida_1);
		Malelauakatse.put("C", Rida_1);
		Malelauakatse.put("D", Rida_1);
		Malelauakatse.put("E", Rida_1);
		Malelauakatse.put("F", Rida_1);
		Malelauakatse.put("G", Rida_1);
		Malelauakatse.put("H", Rida_1);
		System.out.println(Malelauakatse);
		
		//teeme oletusliku valge liikumise, mis pole kuidagi seotud praegu ülesandega.
		//valge ruudul C2 liigub 2 kohta edasi ruudule C4
		Malelauakatse.get("C").remove(1);
		Malelauakatse.get("C").add(1,"-");
		Malelauakatse.get("C").remove(1+2);
		Malelauakatse.get("C").add(1+2, "v");
		
		ArrayList<String> tahehoius = new ArrayList<>();
		tahehoius.addAll(Arrays.asList("A","B","C","D","E","F","G","H"));
		
		for(int i = 0; i<8;i++){System.out.println(Malelauakatse.get(tahehoius.get(i)));}
		//Nojah, kuna nad kõik jagavad sama rida, siis nad kõik on ka liikumisest mõjutatud.
		
		//Btw, ArrayListi funktsioon - list.contains("m"), seda saaks kasutada
		//äkki liikumise blokeeringuna / ründamise tingimusena?
		//Kus eelnevalt programm kontrollib kõik võimalused läbi, ja siis
		//annab valitud nupule võimalikud käsud, mida mängija saab valida.
	}
}
