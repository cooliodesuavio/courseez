import java.util.ArrayList;


public class Schedule {
	ArrayList<String> coursenames;
	ArrayList<Integer> courseindexes;
	int time[][];
	public Schedule(){
		time=new int[5][96];//5 days a week; 24 hours at 15 minute intervals
		coursenames=new ArrayList<String>();
		courseindexes=new ArrayList<Integer>();
	}
	public void addClass(String name, int index){
		coursenames.add(name);
		courseindexes.add(index);
	}
	public ArrayList<Integer> getIndexes(){
		return courseindexes;
	}
	public ArrayList<String> getNames(){
		return coursenames;
	}
}
