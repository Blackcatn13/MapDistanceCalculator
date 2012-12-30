/**
 * 
 */
package outputs;

/**
 * @author xyz0k
 *
 */
public class Time {

	private long start;
	private int scaleF;
	
	public Time(){
		start = System.currentTimeMillis();
		//set scale to millisecond;
		scaleF = 1;
	}
	
	public void setScale (String scale){
		String scalelc = scale.toLowerCase();
		if (scalelc.equals("second")){
			scaleF = 1000;
		}
		else if (scalelc.equals("minute")){
			scaleF = 1000*60;
		}
	}
	
	public void resetTime(){
		start = System.currentTimeMillis();
	}
	
	public long elapsedTime(){
		return (System.currentTimeMillis()-start)/scaleF;
	}
}
