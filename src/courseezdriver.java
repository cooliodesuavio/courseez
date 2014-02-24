import java.util.ArrayList;
import java.util.HashMap;


public class courseezdriver {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		HashMap<String, ArrayList<UniqClass>> coursecatalog=new HashMap<String, ArrayList<UniqClass>>();
		ArrayList<UniqClass> listing = new ArrayList<UniqClass>();
		//Example of an addition of class to the HashMap
		listing.add(new UniqClass(17020,"900 to 1000a 930 to 1230p", "MWF T","CPE 2.212 ENS 307", "JULIEN, C", "open"));
		listing.add(new UniqClass(17025,"900 to 1000a 1230 to 330p", "MWF T","CPE 2.212 ENS 307", "JULIEN, C", "open"));
		listing.add(new UniqClass(17030,"900 to 1000a 330 to 630p", "MWF T","CPE 2.212 ENS 307", "JULIEN, C", "open"));
		listing.add(new UniqClass(17035,"900 to 1000a 630 to 930p", "MWF T","CPE 2.212 ENS 307", "JULIEN, C", "open"));
		coursecatalog.put("EE461L", (ArrayList<UniqClass>) listing.clone());
		listing.clear();
		listing.add(new UniqClass(17075,"1200 to 100p 1200 to 300p", "MW F","RLM 5.104 ENS 212", "HALLOCK, G", "open"));
		listing.add(new UniqClass(17080,"1200 to 100p 300 to 600p", "MW W","RLM 5.104 ENS 212", "HALLOCK, G", "open"));
		listing.add(new UniqClass(17085,"1200 to 100p 630 to 930p", "MW W","RLM 5.104 ENS 212", "HALLOCK, G", "open"));
		coursecatalog.put("EE364D", (ArrayList<UniqClass>) listing.clone());
		listing.clear();
	}

}
