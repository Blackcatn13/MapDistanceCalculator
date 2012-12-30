/**
 * 
 */
package outputs;

/**
 * @author User
 *
 */
public class Time {

	long start;
	int scaleF;
	
	public Time(){
		start = System.currentTimeMillis();
		//set scale to miliseconds;
		scaleF = 1;
	}
	
	public void setScale (String scale){
		String scalelc = scale.toLowerCase();
		if (scalelc.equals("seconds")){
			scaleF = 1000;
		}
		else if (scalelc.equals("miliseconds")){
			scaleF = 1000/60;
		}
	}
	
	public void resetTime(){
		start = System.currentTimeMillis();
	}
	
	public long elapsedTime(){
		return (System.currentTimeMillis()-start)/scaleF;
	}
}
