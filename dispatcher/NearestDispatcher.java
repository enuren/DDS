package dispatcher;

import instance.Call;
import instance.Site;

import java.util.ArrayList;

public class NearestDispatcher implements Dispatcher{

	public NearestDispatcher(){
		
	}
	
	/**
	 * A simple dispatcher sending the first available ambulance
	 */
	public double dispatch(ArrayList<Call> calls, ArrayList<Site> sites, double time){
		
		for(Call c : calls){
			double firstAvailable = Double.MAX_VALUE;
			int index = -1;
			for(int i=0; i<sites.size(); i++){
				Site s = sites.get(i);
				if(s.distanceTo(c)<firstAvailable){
					firstAvailable = s.distanceTo(c);
					index = i;
				}
			}
			sites.get(index).getFirstAvailable().serviceCall(c, time);;
		}
		
		// Remove all calls from the list to signify that they are serviced
		calls.clear();
		
		// Request to be recalled... Never
		return Double.MAX_VALUE;
	}
}
