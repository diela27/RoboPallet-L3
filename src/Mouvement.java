import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Mouvement {
	

	    
	    public Mouvement() {
	        
	    }

	   
	    public void avancer() {
	    	 Motor.A.setSpeed(1000);
	         Motor.D.setSpeed(1000);
	         Motor.A.forward();
	         Motor.D.forward();
	        

	         Delay.msDelay(3000); 

	         Motor.A.stop();
	         Motor.D.stop();
	    }

	   
	    public void reculer() {
	    	 Motor.A.setSpeed(1000);
	         Motor.D.setSpeed(1000);
	         Motor.A.backward();
	         Motor.D.backward();
	        

	         Delay.msDelay(3000); 

	         Motor.A.stop();
	         Motor.D.stop();
	    }

	   
	    public void tournerDroite() {
	    	
	    	    Motor.A.setSpeed(200);
	    	    Motor.D.setSpeed(200);

	    	    Motor.A.forward();
	    	    Motor.D.backward();

	    	    Delay.msDelay(760);

	    	   stoproue();
	    	
	    }

	   
	    public void tournerGauche() {
	        Motor.A.setSpeed(200);
    	    Motor.D.setSpeed(200);

    	    Motor.A.backward();
    	    Motor.D.forward();

    	    Delay.msDelay(760);

    	    stoproue();
	    }
		public void ourvirBras(int i ) {
		 	Motor.B.setSpeed(500);

	        Motor.B.forward();  

	        Delay.msDelay(i); 

	        Motor.B.stop(); 
	}
		public void fermerBras(int i ) {
		 	Motor.B.setSpeed(500);

	        Motor.B.backward();  

	        Delay.msDelay(i); 

	        Motor.B.stop(); 
	}
	   
	    public void stoproue() {
	    	Motor.A.stop();
    	    Motor.D.stop();
	    }
	    public void  tourComplet() {
	    	/*Motor.A.setSpeed(1000);
    	    Motor.D.setSpeed(1000);

    	    Motor.A.backward();
    	    Motor.D.forward();

    	    Delay.msDelay(100);

    	    stoproue();  */
	    	Motor.A.rotate(360);//cvhjf
	    	
	    }
	    
	
}
