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
	public Schedule(boolean[][] time,ArrayList<String> coursenames, ArrayList<Integer> courseindexes){
		this.time=time;
		this.coursenames=(ArrayList<String>) coursenames.clone();
		this.courseindexes=(ArrayList<Integer>) courseindexes.clone();
		
	}
	public boolean addClass(String name, int index, HashMap<String, ArrayList<UniqClass>> coursecatalog){
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
	}
	public ArrayList<Integer> getIndexes(){
		return courseindexes;
	}
	public ArrayList<String> getNames(){
		return coursenames;
	}
	private boolean updateTime(String days, String tim){
		String[] ind_days = days.split("");
		String[] times = tim.split(" to ");
		int day;
		int time_ind=0;
		for(int i =0;i<ind_days.length;i++){
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
				int startTime=Integer.parseInt(times[time_ind]);
				char aorp=times[time_ind+1].charAt(times[time_ind+1].length()-1);
				int stopTime=Integer.parseInt(times[time_ind+1].substring(0, times[time_ind+1].length()-1));
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
				for(int j=startTime;j<stopTime-1;j++){
					if(this.time[day][j]==true){
						return false;
					}
					this.time[day][j]=true;
				}
			}
			if(ind_days[i].equals(" "))
				time_ind+=2;
		}
		return true;
	}
}
