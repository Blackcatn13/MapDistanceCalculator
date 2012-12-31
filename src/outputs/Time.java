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
	private float scaleF;
	
	public Time(){
		start = System.nanoTime();
		//set scale to nanosecond;
		scaleF = 1F;
	}
	
	public void setScale (String scale){
		String scalelc = scale.toLowerCase();
		if (scalelc.equals("millisecond")){
			scaleF = 1000*1000F;
		}
		else if (scalelc.equals("second")){
			scaleF = 1000*1000*1000F;
		}
		else if (scalelc.equals("minute")){
			scaleF = 1000*1000*1000*60F;
		}
	}
	
	public void resetTime(){
		start = System.nanoTime();
	}
	
	public float elapsedTime(){
		return  ((System.nanoTime()-start)/scaleF);
	}
}