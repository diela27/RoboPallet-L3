
public class Robot {
	Mouvement mv = new Mouvement();
	public Robot() {
		
	}
	 public void avancer() {
	        mv.avancer();
	    }
	 public void reculer() {
	        mv.reculer();
	    }

	    public void tournerDroite() {
	        mv.tournerDroite();
	    }

	    public void tournerGauche() {
	        mv.tournerGauche();
	    }
	    public void ouvrirBras(int i) {
	    	mv.ourvirBras(i);
	    }
	    public void fermerBras(int i) {
	    	mv.fermerBras(i);
	    }

	    public void stop() {
	        mv.stoproue();
	    }
	   public void  tourComplet() {
		   mv.tourComplet();
	   }
	}
//izj
	

