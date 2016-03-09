package mainPackage;

public class Referee {

	// Kohtunikul on viited malelauale ja mängijatele, kuid ta ei muuda kunagi nende olekut (vahetult); 
	static Malelaud gameBoard;
	Player whitePlayer, blackPlayer;
	
	// Samuti haldab see klass muutuvaid mänguparameetreid (v.a laua seisund)
	byte turn;
	String color, th1, th2, lastMove;
	int nr1, nr2;
	boolean endReached, enPassant;
	
	//Konstruktor
	public Referee(Malelaud board, Player white, Player black ){
		gameBoard = board;
		whitePlayer = white;
		blackPlayer = black;
		turn = 1;
		color = "v";
		endReached = false; 
		enPassant = false;
	}
	
	
	////Mõned lühendatud nimetusega teisendusmeetodid
	//
	//char-2-String teisendus
	public static String c2s(String str, int i)
	{
		return Character.toString(str.charAt(i));
	}
	
	//char-2-int teisendus
	public static int c2i(String str, int i)
	{
		return Character.getNumericValue(str.charAt(i));
	}
	
	//Tähe "väärtus" ehk indeks reas "ABCDEFGH" (vajalik vahetult kõrvuti olevate ridade kindlakstegemisel)
	public static int letterVal(String s)
	{
		return gameBoard.tahehoius.indexOf(s);
	}
	
	
	
	////Järgnevad 7 meetodit on käigu õigsusekontrolli sammud nende rakendamise järjekorras. 
	//
	//
	//Abikontroll, mis teeb kindlaks, kas tegemist on erandliku olukorraga. 
	private void checkEnPassant() 
	{
		if ( 	Math.abs(c2i(lastMove, 1) - c2i(lastMove, 3)) == 2 
				 &&	Math.abs(letterVal(c2s(lastMove, 2)) - letterVal(th1)) == 1
				 && nr1 == c2i(lastMove, 3)		)				
		{
			enPassant = true;
			gameBoard.enPassant(turn==1? true : false);
		}
	}
	//
	//Kõigepealt kontrollitakse sisendi süntaktilist korrektsust
	private boolean isCorrectFormat(String input)
	{
		if (input.length() != 4) 
		{
			say("Sisendi pikkus peab olema täpselt 4 märki! Sisestage midagi muud."); 
			return false; 
		}
		else if (!input.matches("[a-hA-H][1-8][a-hA-H][1-8]")) 
		{
			say("Selline sisend ei tähista käiku. Sisestage midagi muud.");
			return false;
		}
		else if (input.charAt(0) == input.charAt(2) && input.charAt(1) == input.charAt(3)) 
		{
			say("Ei tohi käia samale ruudule! Sisestage muu käik. ");
			return false;
		}
		return true;
	}
	//
	//Seejärel kontrollitakse, kas vaadeldav ettur on käiva mängija oma. 
	private boolean isMyPawn()
	{				
		if (gameBoard.getPawn(th1, nr1) != color) 
		{
			say("Käia tohib ainult oma etturiga. Teie omad on tähistatud "+color+"-tähega. Sisestage muu käik. ");
			return false;
		}
		return true;
	}
	//
	//Siis kontrollitakse, kas sihtruudul juba on sama värvi ettur.
	private boolean isSquareOccupied()
	{
		String square = gameBoard.getPawn(th2, nr2);
		if (square == color) 
		{
			say("Sihtruudul juba on teie ettur. Sisestage muu käik.");
			return true;
		} 
		return false;
	}
	//
	//Tehakse kindlaks, kas tegemist on rünnaku või tavalise liikumisega. 
	private boolean isAttackMove()
	{
		String square = gameBoard.getPawn(th2, nr2);		
		if (square != " ") return true;
		return false;
	}
	//
	//Rünnaku korral kontrollitakse selle legaalsust. 
	private boolean isLegalAttack()
	{	
		if ( Math.abs(letterVal(th2)-letterVal(th1)) != 1) 
		{
			say("Rünnata saab ainult 1 ruudu võrra diagonaalis. Sisestage muu käik."); 
			return false;
		}
		
		if (turn == 0 && 
				((!enPassant && nr1 < nr2 && nr2-nr1 == 1) || (enPassant && nr2 == nr1)))
		{
			whitePlayer.pawnKilled();
			if (whitePlayer.getPawnsLeft()==0) 
			{
				say("Must võitis!"); 
				endReached = true; 
				blackPlayer.wonTheMatch();
			}
			return true;
		}
		if (turn == 1 && 
				((!enPassant && nr2 < nr1 && nr1-nr2 == 1) || (enPassant && nr1 == nr2)))
		{
			blackPlayer.pawnKilled();
			if (blackPlayer.getPawnsLeft()==0) 
			{
				say("Valge võitis!");
				endReached = true; 
				whitePlayer.wonTheMatch();
			}
			return true;
		}
		say("Sinna ei ole lubatud rünnata! Sisestage muu käik.");
		return false;
	}
	//
	//Tavaliikumise korral samuti. Ühtlasi teeb kindlaks, kas ettur on lipuks saanud. 
	private boolean isLegalMove()
	{
		if (letterVal(th1)!=letterVal(th2))
		{
			say("Ründamata saab käia ainult sirgjooneliselt. Sisestage muu käik.");
			return false; 
		}
		
		if (turn == 0 && (
				nr2 > nr1 && (
						(nr2 != 1 && nr2-nr1 == 1) || nr2-nr1 <= 2) )) 
		{
			if (nr2 == 8) {
				endReached = true;
				say("Must võitis!");
				blackPlayer.wonTheMatch();
			}
			return true;
		}
		
		if (turn == 1 && (
				nr1 > nr2 && (
						(nr1 != 1 && nr1-nr2 == 1) || nr1-nr2 <= 2) )) 
		{
			if (nr2 == 1) 
			{
				endReached = true;
				say("Valge võitis!");
				whitePlayer.wonTheMatch();
			}
			return true;
		}
		
		say("Sinna ei ole lubatud käia! Sisestage muu käik.");
		return false;
	}

	
	//Peamine meetod, mille kaudu kohtunik kontrollib, kas kasutaja poolt
	//sisestatud käik on lubatud. Tegevus koosneb mitmest sammust. 
	//Kui kontrolli käigus üks samm nurjub, siis ei ole käik võimalik. 
	public boolean Assess(String input) 
	{
		
		//Lollusevastane kontroll
		if (!isCorrectFormat(input)) return false; 
		
		//Sisendi lahtimõtestamine
		th1 = c2s(input, 0);
		nr1 = c2i(input, 1);
		th2 = c2s(input, 2);
		nr2 = c2i(input, 3);
		
		//Näpuvigade-vastane kontroll
		if(!isMyPawn() || isSquareOccupied()) return false;
		
		//Erandikontroll
		if (lastMove != null) checkEnPassant();
		
		//Reeglitundmise kontroll
		if (isAttackMove() && isLegalAttack()) 
		{
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
			lastMove = input;
			return true;
		}
		return false;
	}
	
	public void nextTurn()
	{
		turn = (byte)((turn + 1) % 2);
		enPassant = false;
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