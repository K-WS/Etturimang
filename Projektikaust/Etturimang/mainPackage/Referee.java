package mainPackage;
import java.util.Random;

public class Referee {

	// Kohtunikul on viited malelauale ja m�ngijatele
	Malelaud_2 gameBoard;
	Player whitePlayer, blackPlayer;
	byte gameNr, turn;
	String color, th1, th2, lastMove;
	int nr1, nr2;
	boolean endReached = false, enPassant;
	
	//Konstruktor
	public Referee(Malelaud_2 board, Player white, Player black ){
		gameBoard = board;
		whitePlayer = white;
		blackPlayer = black;
		gameNr = 0;
		turn = 1;//(byte)new Random().nextInt(2);
		switch (turn)
		{
			case 0: color = "m"; break;
			case 1: color = "v"; break;
		}
	}
	
	
	////M�ned l�hendatud teisendusmeetodid
	//
	//char-2-String teisendus
	private String chstr(String str, int i){
		return Character.toString(str.charAt(i));
	}
	
	//char-2-int teisendus
	private int chint(String str, int i){
		return Character.getNumericValue(str.charAt(i));
	}
	
	//T�he indeks reas "ABCDEFGH"
	private int letterVal(String s){
		return gameBoard.tahehoius.indexOf(s);
	}
	
	
	
	////J�rgnevad 7 meetodit on k�igu �igsusekontrolli sammud nende rakendamise j�rjekorras. 
	//
	//
	//Abikontroll, mis teeb kindlaks, kas tegemist on erandliku olukorraga. 
	private void checkEnPassant() 
	{
		if ( 	Math.abs(chint(lastMove, 1) - chint(lastMove, 3)) == 2 
				 &&	Math.abs(letterVal(chstr(lastMove, 2)) - letterVal(th1)) == 1)
			{
				if ((turn==1 && nr1 - chint(lastMove, 3) == 1) ||
					(turn==0 && chint(lastMove, 3) - nr1 == 1))		enPassant = true;
			}
	}
	//
	//K�igepealt kontrollitakse sisendi s�ntaktilist korrektsust
	private boolean isCorrectFormat(String input){
		if (input.length() != 4) return false; 
		else if (!input.matches("[a-hA-H][1-8][a-hA-H][1-8]")) {say("Sisend ebakorrektne!");return false;}
		else if (input.charAt(0) == input.charAt(2) && input.charAt(1) == input.charAt(3)) {say("Ei tohi k�ia samale ruudule!");return false;}
		return true;
	}
	//
	//Seej�rel kontrollitakse, kas vaadeldav ettur on k�iva m�ngija oma. 
	private boolean isMyPawn()
	{				
		if (gameBoard.getPawn(th1, nr1-1) != color) {say("K�ia tohib ainult oma etturiga. Teie omad on t�histatud "+color+"-t�hega.");return false;}
		return true;
	}
	//
	//Siis kontrollitakse, kas sihtruudul juba on sama v�rvi ettur.
	private boolean isSquareOccupied(){
		String square = gameBoard.getPawn(th2, nr2-1);
		if (square == color) {say("Sihtruudul juba on teie ettur.");return true;} 
		return false;
	}
	//
	//Tehakse kindlaks, kas tegemist on r�nnaku v�i tavalise liikumisega. 
	private boolean isAttackMove(){
		String square = gameBoard.getPawn(th2, nr2-1);		
		if (square != " ") return true;
		return false;
	}
	//
	//R�nnaku korral kontrollitakse selle legaalsust. 
	private boolean isLegalAttack(){
		say("R�nnak");
		if (Math.abs(letterVal(th2)-letterVal(th1)) != 1) {say("R�ndamata saab ainult 1 ruudu v�rra diagonaalis.");return false;}
		
		
		if (turn == 0 && 
				((!enPassant && nr2 > nr1) && nr1-nr2 == 1))
		{
			if (whitePlayer.getPawnsLeft()==0) {endReached = true; blackPlayer.wonTheMatch();}
			return true;
		}
		if (turn == 1 && 
				((!enPassant && nr2 > nr1) && nr2-nr1 == 1)) 
		{
			if (blackPlayer.getPawnsLeft()==0) {endReached = true; whitePlayer.wonTheMatch();}
			return true;
		}
		say("Sinna ei ole lubatud r�nnata!");
		return false;
	}
	//
	//Tavaliikumise korral samuti. �htlasi teeb kindlaks, kas ettur on lipuks saanud. 
	private boolean isLegalMove(){
		say("Tavak�ik");
		if (letterVal(th1)!=letterVal(th2)) {say("R�ndamata k�ia saab ainult sirgjooneliselt.");return false;}
		
		if (turn == 0 && (
				nr1 > nr2 && (
						(nr2 != 1 && nr1-nr2 == 1) || nr1-nr2 <= 2) )) 
		{
			if (nr2 == 0) endReached = true;
			blackPlayer.wonTheMatch();
			return true;
		}
		
		if (turn == 1 && (
				nr2 > nr1 && (
						(nr1 != 1 && nr2-nr1 == 1) || nr2-nr1 <= 2) )) 
		{
			if (nr2 == 7) endReached = true;
			whitePlayer.wonTheMatch();
			return true;
		}
		
		say("Sinna ei ole lubatud k�ia!");
		return false;
	}

	
	//Peamine meetod, mille kaudu kohtunik kontrollib, kas kasutaja poolt
	//sisestatud k�ik on lubatud. Tegevus koosneb mitmest sammust. 
	//Kui kontrolli k�igus �ks samm nurjub, siis ei ole k�ik v�imalik. 
	public boolean Assess(String input) 
	{
		th1 = chstr(input, 0);
		nr1 = chint(input, 1);
		th2 = chstr(input, 2);
		nr2 = chint(input, 3);
		
		if (lastMove != null) checkEnPassant();
		
		if (!isCorrectFormat(input) || 
			!isMyPawn() || 
			isSquareOccupied()) return false;
		
		if (isAttackMove() && isLegalAttack()) 
		{
			//TODO!
			//gameBoard.liigutus(input)
			switch(turn)
			{
				case 0: whitePlayer.pawnKilled(); break;
				case 1: blackPlayer.pawnKilled(); break;
			}
			lastMove = input;
			return true;
		} 
		if (!isAttackMove() && isLegalMove()) 
		{
			//TODO!
			//gameBoard.liigutus(input)
			lastMove = input;
			return true;
		}
		return false;
	}
	
	public void nextTurn(){
		turn = (byte)((turn + 1) % 2);
		switch (turn)
		{
			case 0: color = "m"; break;
			case 1: color = "v"; break;
		}
	}

	public boolean gameOver()
	{
		if (endReached) return true;
		
		return false;
	}

	public void say(String sentence) 
	{
		System.out.println(sentence);
	}
}