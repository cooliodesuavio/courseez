
import java.util.ArrayList;
import java.util.HashMap;


public class Schedule {
	ArrayList<String> coursenames;
	ArrayList<Integer> courseindexes;
	boolean time[][];
	public Schedule(){
		time=new boolean[5][96];//5 days a week; 24 hours at 15 minute intervals
		coursenames=new ArrayList<String>();
		courseindexes=new ArrayList<Integer>();
	}
	//copy constructor
	public Schedule(boolean[][] tim,ArrayList<String> coursenames, ArrayList<Integer> courseindexes){
		time=new boolean[5][96];
		for(int i=0;i<5;i++){
			for(int j=0;j<96;j++){
				this.time[i][j]=tim[i][j];
			}
		}
		this.coursenames=new ArrayList<String>(coursenames);
		this.courseindexes=new ArrayList<Integer>(courseindexes);	
	}
	
	public boolean addClass(String name, int index, HashMap<String, ArrayList<UniqClass>> coursecatalog){
		//if the schedule is still valid when the new class is added return true, otherwise return false
		coursenames.add(name);
		courseindexes.add(index);
		UniqClass u = coursecatalog.get(name).get(index);
		if(updateTime(u.days,u.times))
			return true;
		return false;
	}
	public void clear(){
		coursenames.clear();
		courseindexes.clear();
		for(int i=0;i<5;i++)
			for(int j=0;j<96;j++)
				time[i][j]=false;
	}
	public ArrayList<Integer> getIndexes(){
		return courseindexes;
	}
	public ArrayList<String> getNames(){
		return coursenames;
	}
	private boolean updateTime(String days, String tim){
		String[] ind_days = days.split("");
		String[] times = tim.split(" ");
		int day;
		int time_ind=0;
		//for each day that the class is scheduled
		for(int i =0;i<ind_days.length;i++){
			//find out the correct index for the day is
			if(i<ind_days.length-1 && ind_days[i+1].equals("H"))
				day=3;
			else{
				switch(ind_days[i]){
				case "M": day=0;
					break;
				case "T": day=1;
					break;
				case "W": day=2;
					break;
				case "F": day=4;
					break;
				default: day=-1;
					break;
				}
			}
			if(day!=-1){
				//grab the start time and end time and correctly alter them for 24 hour time and convert that into an index
				//NOTE:: the conversion to 24 time will need to be done more correctly somehow
				int startTime=Integer.parseInt(times[time_ind]);
				char aorp=times[time_ind+2].charAt(times[time_ind+2].length()-1);
				int stopTime=Integer.parseInt(times[time_ind+2].substring(0, times[time_ind+2].length()-1));
				if(aorp=='p'){
					if(startTime<=700){
						startTime+=1200;
						stopTime+=1200;
					}
					else if(stopTime<=500)
						stopTime+=1200;
				}
				startTime=((startTime/100) * 4)+((startTime%100)/15);
				stopTime=((stopTime/100) * 4)+((stopTime%100)/15);
				//go through time matrix to see if a time slot that the class occupies is already occupied
				for(int j=startTime;j<stopTime;j++){
					if(this.time[day][j]==true){
						return false;
					}
					this.time[day][j]=true;
				}
			}
			//move onto the next time listing for class
			if(ind_days[i].equals(" "))
				time_ind+=3;
		}
		return true;
	}
}
