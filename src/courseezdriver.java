import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class courseezdriver {

	/**
	 * @param args
	 */
	static HashMap<String, ArrayList<UniqClass>> coursecatalog;
	public static void main(String[] args) {
		coursecatalog=new HashMap<String, ArrayList<UniqClass>>();
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
		listing.add(new UniqClass(17040,"130 to 300p", "MW","WAG 214", "ZHOU, Y", "open"));
		coursecatalog.put("EE361Q", (ArrayList<UniqClass>) listing.clone());
		listing.clear();
		listing.add(new UniqClass(16960,"200 to 330p", "TTH","UTC 3.134", "JULIEN, C", "open"));
		coursecatalog.put("EE360C", (ArrayList<UniqClass>) listing.clone());
		listing.clear();
		listing.add(new UniqClass(5380,"1100 to 1230p", "TTH","UTC 2.102A", "MILLER, H", "open"));
		listing.add(new UniqClass(5385,"200 to 330p", "TTH","UTC 2.102A", "MILLER, H", "open"));
		coursecatalog.put("MKT320F", (ArrayList<UniqClass>) listing.clone());
		listing.clear();
		/*for(String course : coursecatalog.keySet()){
			System.out.println(course+":");
			for(UniqClass uclass: coursecatalog.get(course)){
				System.out.println("\t"+uclass.id);
			}
		}*/
		
		
		String[] choices = {"EE364D","EE361Q","EE461L","MKT320F","EE360C"};
		ArrayList<Schedule> perms = allPermutations(choices,null,0);
		
		
		for(Schedule sc : perms){
			System.out.println(sc.getNames()+","+sc.getIndexes());
		}
		System.out.println("Number of permutations: "+perms.size());
		
	}
	public static ArrayList<Schedule> allPermutations(String[] courses, ArrayList<Schedule> allPossible, int index){
		if(courses.length == index)
			return allPossible;
		int size = coursecatalog.get(courses[index]).size();
		Schedule tmp = new Schedule();
		//if no schedules have been added, add a permutation for each class
		if(allPossible==null){
			allPossible = new ArrayList<Schedule>(); 	
			for(int i =0; i<size;i++){
				if(tmp.addClass(courses[0], i,coursecatalog))
					allPossible.add(new Schedule(tmp.time,tmp.coursenames,tmp.courseindexes));
				tmp.clear();
			}
			return allPermutations(courses,allPossible,1);
		}
		ArrayList<Schedule> temp =new ArrayList<Schedule>();
		//for each already valid schedule, add a new permutation that includes the new class
		for(Schedule sc : allPossible){
			for(int j=0;j<size;j++){
					tmp = new Schedule(sc.time,sc.coursenames,sc.courseindexes);
					if(tmp.addClass(courses[index], j,coursecatalog))
						temp.add(new Schedule(tmp.time,tmp.coursenames,tmp.courseindexes));
					tmp.clear();
			}
		}
		return allPermutations(courses,temp,index+1);
	}
}

