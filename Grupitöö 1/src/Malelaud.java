

public class Malelaud {
	//8-elemendiline rida
	private char a1;
	private char a2;
	private char a3;
	private char a4;
	private char a5;
	private char a6;
	private char a7;
	private char a8;
	
	//Get ja Set
	public char getA1() {return a1;}
	public void setA1(char a1) {this.a1 = a1;}
	public char getA2() {return a2;}
	public void setA2(char a2) {this.a2 = a2;}
	public char getA3() {return a3;}
	public void setA3(char a3) {this.a3 = a3;}
	public char getA4() {return a4;}
	public void setA4(char a4) {this.a4 = a4;}
	public char getA5() {return a5;}
	public void setA5(char a5) {this.a5 = a5;}
	public char getA6() {return a6;}
	public void setA6(char a6) {this.a6 = a6;}
	public char getA7() {return a7;}
	public void setA7(char a7) {this.a7 = a7;}
	public char getA8() {return a8;}
	public void setA8(char a8) {this.a8 = a8;}
	
	//Konstruktor
	public Malelaud(char a1, char a2, char a3, char a4, char a5, char a6, char a7, char a8) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a5 = a5;
		this.a6 = a6;
		this.a7 = a7;
		this.a8 = a8;
		
	//To-string on vist ebavajalik
	}
	//Kõikide 8 Listi hoidmiseks peaks siis saama äkki:
	//ArrayList<Malelaud> Laud = new ArrayList<Malelaud>();
	
	//ja idee põhjal siis nt tühja rea loomine peaklassis oleks:
	//Malelaud Rida_1 = new Malelaud("-","-","-","-","-","-","-","-");
	//Laud.add(Rida_1);
	
	//Teine ja seitmes rida oleksid erandlikud, musta (m) ja valge (v) nuppude jaoks.
	//kuid kas siis eraldi klassis tuleb anda nendele funktsioonid... või äkki peaklassis?


}
