/**
 * This class represent a traffic light. It contains a periodTime, which will loop
 * over and over i.e.[0, 1, ... , periodTime-1, 0, 1]. The lights green time is set to a 
 * number in the range of periodTime so that the traffic light car show a green light 0 steps 
 * in a cycle, the full cycle, or anything in between.
 *
 *@author Fredrik Wärngård
 *@date 2015-01-14
 */

public class Light {
    private int period;
    private int time;
    private int greenLight;

    public Light(int period, int green) throws IllegalArgumentException {
    	this.period = period;
    	this.time = 0;
    	if(green <= period){
	    this.greenLight = green;
    	} else {
	    throw new IllegalArgumentException("green time for traffic light has to be of lower or equal value of variable period");
	}
    }

    /**
     * Increments the internal clock one step, or resets it, if maximum is reached
     */    
    public void step() { 
	if(time < period - 1)
	    ++this.time;
	else
	    this.time = 0;
    }

    /**
     *Gives you the internal time of a traffic light
     *@return int internal time of a traffic light
     */    
    public int getTime() { 
	return this.time;
    }
    
    /**
     * Returns true if the traffic light is currently green, else false.
     * @return Boolean value whether or not  the traffic light is green or not.
     */    
    public boolean isGreen()   {
    	return this.time < this.greenLight;
    }

    /**
     * Represent a green light as a "O", and a red light as "X"
     * @return O if it is currently green time for the traffic light. X if it is not. 
     */    
    public String toString()  {
    	return (this.isGreen()) ? "O" : "X";
    }
    
}
