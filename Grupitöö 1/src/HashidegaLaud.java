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
		//ebakorraliku koodi lisamine, et n�ha dict-tabeli l�ppv�limust.
		Malelauakatse.put("B", Rida_1);
		Malelauakatse.put("C", Rida_1);
		Malelauakatse.put("D", Rida_1);
		Malelauakatse.put("E", Rida_1);
		Malelauakatse.put("F", Rida_1);
		Malelauakatse.put("G", Rida_1);
		Malelauakatse.put("H", Rida_1);
		System.out.println(Malelauakatse);
		
		//teeme oletusliku valge liikumise, mis pole kuidagi seotud praegu �lesandega.
		//valge ruudul C2 liigub 2 kohta edasi ruudule C4
		Malelauakatse.get("C").remove(1);
		Malelauakatse.get("C").add(1,"-");
		Malelauakatse.get("C").remove(1+2);
		Malelauakatse.get("C").add(1+2, "v");
		
		ArrayList<String> tahehoius = new ArrayList<>();
		tahehoius.addAll(Arrays.asList("A","B","C","D","E","F","G","H"));
		
		for(int i = 0; i<8;i++){System.out.println(Malelauakatse.get(tahehoius.get(i)));}
		//Nojah, kuna nad k�ik jagavad sama rida, siis nad k�ik on ka liikumisest m�jutatud.
		
		//Btw, ArrayListi funktsioon - list.contains("m"), seda saaks kasutada
		//�kki liikumise blokeeringuna / r�ndamise tingimusena?
		//Kus eelnevalt programm kontrollib k�ik v�imalused l�bi, ja siis
		//annab valitud nupule v�imalikud k�sud, mida m�ngija saab valida.
	}
}
