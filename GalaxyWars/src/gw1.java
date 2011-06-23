import splash.Splash;
import Galacticcore.*;

public class gw1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Splash splash = new Splash("Galaxy Wars", "1.0", "04/08/2011");
		splash.Splashout();
		gwview gv = new gwview();
		gv.galaxyform();
	}

}
