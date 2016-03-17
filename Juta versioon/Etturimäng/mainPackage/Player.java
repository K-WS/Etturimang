package mainPackage;


/**
 * Klass, mis hoiab endas mängija andmeid. 
 */
public class Player {
	
	/** Mitu etturit on mängijal järel. */
	private int pawnsLeft;
		
		/**
		 * Gets the pawns left.
		 *
		 * @return the pawns left
		 */
		public int getPawnsLeft() 
		{ 
			return pawnsLeft; 
		}
		
		/**
		 * Pawn killed - käivitatakse, kui vastane võtab mõne etturi maha.  
		 */
		public void pawnKilled() 
		{
			pawnsLeft--;
		}
		
		/**
		 * Reset pawns left - käivitatakse uue mängu alustamisel. 
		 */
		public void resetPawnsLeft()
		{
			pawnsLeft = 8;
		}
		
	/** The points - mitu mängu on võidetud. Viikide eest meie programm punkte ei anna. */
	private int points;
		
		/**
		 * Gets the points.
		 *
		 * @return the points
		 */
		public int getPoints() 
		{ 
			return points; 
		}
		
		/**
		 * Won the match - käivitatakse, kui mõni ettur on jõudnud laua serva või kui kõik vastase etturid on hävitatud.
		 */
		public void wonTheMatch()
		{
			points++;
		}
	
	/**
	 * Instantiates a new player.
	 */
	public Player() 
	{
		pawnsLeft = 8;
		points = 0;
	}

	/**
	 * Hack pawns left - meetod, mida kasutatakse mängu eri seisude kiireks testimiseks. 
	 *
	 * @param i - mitu etturit on mängijale kunstlikult tekitatud
	 */
	public void hackPawnsLeft(int i)
	{
		while(pawnsLeft>i)
		{
			pawnKilled();
		}
	}
}
