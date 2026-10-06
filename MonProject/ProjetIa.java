package MonProject;
import lejos.hardware.BrickFinder;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
public class ProjetIa {

    
 
    private static Action a;
	private static ToucherCapteur touch;
	private static Pince pince;
	private static DistanceCapteur distance;
	private static CouleurCapteur color;
	private int angle; //représente l'angle vers lequel le robot est tourné

	public void ProjetIa (Action action, Pince pince, ToucherCapteur t, DistanceCapteur d,CouleurCapteur c) {
		this.a=action;
		this.pince=pince;
		this.touch=t;
		this.distance=d;
		this.color=c;
		this.angle=0;
	}

//-----------------------------------------------Début du jeu-------------------------------------------------
	
	public void case345(boolean droite) {
    	if(droite) { //si on veut décaler à droite
    		avancerPlusPrendrePaletAsyncEtOuvre(300);
			a.seDecalerD();
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerG(90);
			avancerJusqueLigne();
			a.avancer(100, 200);//réavancer pour que le centre de notre robot soit sur la ligne, et pas le capteur couleur
			avancerJusqueLigne();
			a.avancer(100, 200);//réavancer pour que le centre de notre robot soit sur la ligne, et pas le capteur couleur
			a.tournerG(90);
		}else {
			avancerPlusPrendrePaletAsyncEtOuvre(300);
			a.seDecalerG();
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerD(90);
			avancerJusqueLigne();
			a.avancer(100, 200);//réavancer pour que le centre de notre robot soit sur la ligne, et pas le capteur couleur
			avancerJusqueLigne();
			a.avancer(100, 200);//réavancer pour que le centre de notre robot soit sur la ligne, et pas le capteur couleur
			a.tournerD(90);
		}
    }

	public void test3palets1(boolean droite) {
    	if(droite) {//si on veut passer par la droite
    		avancerPlusPrendrePaletAsyncEtOuvre(300);
			a.seDecalerD();
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerG(90);
			avancerJusqueLigne();
			a.avancer(100, 200);//réavancer pour que le centre de notre robot soit sur la ligne, et pas le capteur couleur
			a.tournerG(90);
			avancerPlusPrendrePaletAsync(300);
			a.tournerG(180);
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerG(180);
			avancerPlusPrendrePaletAsync(300);
			a.tournerG(180);
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			pince.fermer();	
		}else {//si on veut passer par la gauche
			avancerPlusPrendrePaletAsyncEtOuvre(300);
			a.seDecalerG();
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerD(90);
			avancerJusqueLigne();
			a.avancer(100, 200);
			a.tournerD(90);
			avancerPlusPrendrePaletAsync(300);
			a.tournerG(180);
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			a.tournerG(180);
			avancerPlusPrendrePaletAsync(300);
			a.tournerG(180);
			avancerJusqueCouleur("blanc");
			pince.ouvrir();
			a.reculer(100, 300);
			pince.fermer();		
		}
    }

//-----------------------------------------------Méthode recherche-------------------------------------------------
	
	public void recherche() { // permet de se tourner vers le palet le plus proche et d'aller le récupérer 
		float[] tab = new float[0];
		int j=0;
		int t=0;
		boolean b;//TODO peut etre enlever
		float min = (float)0.80;
		long start = System.currentTimeMillis();
		a.asyncTournerD(360);
		while(a.isMoving()) {
			tab=distance.recherche(tab);
			if(tab[tab.length-1]!=0 && tab[tab.length-1]+0.1<min){
				j=tab.length-1;
			}
			if(tab[tab.length-1]!=0 && tab[tab.length-1]<min) {
				min=tab[tab.length-1];
			}if((tab.length>1) && (j!=0) && (tab[tab.length-1]!=0) && (tab[tab.length-2]-0.1<min) && (tab[tab.length-1]-0.1>min) ){
				t=tab.length-1;
				if (min>0.15 && min<0.35 && (t-j)<300 && (t-j)>10) {
					a.stop();
					long end = System.currentTimeMillis();
					long dist = end-start;
					angle+=(360/5200)*dist-52;
					a.tournerD(40);
					b=false;
					if (!avancerPlusPrendrePalet(200,(int)((min+0.3)*1000))){
						a.reculer((int)(min+0.1)*1000, 250);
						recherche();
					}
				}else if (min>0.35 && min<0.45 && (t-j)<250 && (t-j)>10) {
					a.stop();
					long end = System.currentTimeMillis();
					long dist = end-start;
					angle+=(360/5200)*dist-54;
					a.tournerD(40);
					b=false;
					if (!avancerPlusPrendrePalet(200,(int)(min+0.3)*1000)){
						a.reculer((int)(min+0.1)*1000, 250);
						recherche();
					}
				}else if (min>0.45 && min<0.55 && (t-j)<200 && (t-j)>10) {
					a.stop();
					long end = System.currentTimeMillis();
					long dist = end-start;
					angle+=(360/5200)*dist-54;
					a.tournerD(40);
					b=false;
					if (!avancerPlusPrendrePalet(200,(int)(min+0.3)*1000)){
						a.reculer((int)(min+0.1)*1000, 250);
						recherche();
					}
				}else if (min>0.55 && min<0.75 && (t-j)<100 && (t-j)>10) {
					a.stop();
					long end = System.currentTimeMillis();
					long dist = end-start;
					angle+=(360/5200)*dist-54;
					a.tournerD(40);
					b=false;
					if (!avancerPlusPrendrePalet(200,(int)(min+0.3)*1000)){
						a.reculer((int)(min+0.1)*1000, 250);
						recherche();
					}
				}
				min=(float)0.80;
				j=0;
				t=0;
			}
		}	
	}
	
	public static double tempsVersAngle(long t) { // renvoie l'angle associé au temps de rotation (avec une fonction polynome)
		return (-1.95*(long)Math.pow(10, -6)*t*t+0.0906*t-58.36);
	}
	
	public void recalibrage() { //permet de se recalibrer vers le but adverse après une méthode "recherche"
		int ang= angle%360;
		this.angle=0;
		a.tournerD(ang);
	}
	
//------------------------------------------AvancerPlusPrendrePalet--------------------------------------------
	
	public boolean avancerPlusPrendrePalet(int vitesse, int dis) {
		a.setVitesse(vitesse);
		a.avancerAsync(dis);
		while(a.isMoving()) { //s'arrête que quand le capteur touche le palet
			pince.ouvrir();
			if(touch.estToucher()) {
				a.stop();
				pince.fermer();
				return true;
			}
		}
		pince.fermer();
		return false;
	}

	public void avancerPlusPrendrePaletAsync(int vitesse) {// avancer jusuqu'au palet de maniere asynchrone puis ferme les pinces
		a.setVitesse(vitesse);
		a.avancerAsync(2000);
		while(a.isMoving()) { //s'arrête que quand le capteur touche le palet
			RGB rgb = color.getCouleur();
			if (distance.getDistance() <= 0.20) {
				a.setVitesse(150);
			}

			if (touch.estToucher()) {
				a.stop();
				pince.fermer();
			}

			pince.fermer();
		}
		}
 public void avancerPlusPrendrePaletAsyncEtOuvre(int vitesse) {
	

		    a.setVitesse(vitesse);
		    a.avancerAsync(2000);

		    while (a.isMoving()) {

		        pince.ouvrir();

		        if (touch.estToucher()) {
		            a.stop();
		            pince.fermer();
		            return;
		        }
		    }

		    pince.fermer();
		}
 public void avancerJusqueCouleur(String c) {

	    a.setVitesse(300);
	    a.avancer();

	    RGB rgb = color.getCouleur();

	    while (!color.nomCouleur(
	            rgb.getRed(),
	            rgb.getGreen(),
	            rgb.getBlue()).equals(c)) {

	        rgb = color.getCouleur();
	    }

	    a.stop();
	}
	public void avancerJusqueLigne() {// avance jusqu'a une ligne noire, rouge ou jaune (pour se décaler d'une case)
		a.avancerAsync(300);
		RGB rgb = color.getCouleur();
		while(color.nomCouleur(rgb.getRed(), rgb.getGreen(), rgb.getBlue()) != "rouge" || color.nomCouleur(rgb.getRed(), rgb.getGreen(), rgb.getBlue()) != "jaune"|| color.nomCouleur(rgb.getRed(), rgb.getGreen(), rgb.getBlue()) != "noir") {
			rgb = color.getCouleur();
		}a.stop();

	}
//----------------------------------------------------Autres------------------------------------------------------

	public void eviterRobot() { // permet d'éviter les robots adverses lorsqu'ils sont en face
		if (!pince.estOuvert()&&touch.estToucher()&&distance.getDistance()<270) {
			float distanceProvisoire=distance.getDistance();
			Delay.msDelay(100);
			if(distance.getDistance()<distanceProvisoire-5)
				a.tournerD(100);
			a.avancer(150,100);
			a.tournerG(100);
		}
	}
	
//----------------------------------------------------Main------------------------------------------------------

	public static void main(String[] args) {
		Action a = new Action();
		Pince p = new Pince();
		int strategie = 6;//choix de la stratégie
		int dMid = 500; //distance pour retourner au milieu. Augmenter si l'on souhaite que le robot aille plus loin
		switch (strategie) {
		case 9 ://pour lancer un code normal sans lancer toute une stratégie
			//pince.fermerObligatoirement();
			//a.avancer(700);
			break;
		//case 0, 1, 2 : si le robot adverse veut être en face on se décale d'une ligne pour prendre nos 3 palets
		case 0: //---------------------------------------en face à gauche---------------------------------------
		

		    a.avancer(100);
		    a.tournerD(90);

		    a.avancerJusqueLigne();

		    a.avancer(100, 200);
		    a.tournerG(90);

		   // test3palets1(true);

		    a.tournerG(180);
		    a.avancer(dMid);

		    for (int i = 0; i < 100; i++) {

		        //recherche();
		        //recalibrage();

		        a.avancerJusqueCouleur("blanc");

		        pince.ouvrir();

		        a.reculer(100, 300);

		        pince.fermer();

		        a.tournerG(180);
		        a.avancer(dMid);
		    }
		}
	}
}
