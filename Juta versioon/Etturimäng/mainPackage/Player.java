package mainPackage;


/**
 * Klass, mis hoiab endas m�ngija andmeid. 
 */
public class Player {
	
	/** Mitu etturit on m�ngijal j�rel. */
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
		 * Pawn killed - k�ivitatakse, kui vastane v�tab m�ne etturi maha.  
		 */
		public void pawnKilled() 
		{
			pawnsLeft--;
		}
		
		/**
		 * Reset pawns left - k�ivitatakse uue m�ngu alustamisel. 
		 */
		public void resetPawnsLeft()
		{
			pawnsLeft = 8;
		}
		
	/** The points - mitu m�ngu on v�idetud. Viikide eest meie programm punkte ei anna. */
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
		 * Won the match - k�ivitatakse, kui m�ni ettur on j�udnud laua serva v�i kui k�ik vastase etturid on h�vitatud.
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
	 * Hack pawns left - meetod, mida kasutatakse m�ngu eri seisude kiireks testimiseks. 
	 *
	 * @param i - mitu etturit on m�ngijale kunstlikult tekitatud
	 */
	public void hackPawnsLeft(int i)
	{
		while(pawnsLeft>i)
		{
			pawnKilled();
		}
	}
}
