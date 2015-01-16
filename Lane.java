/**
 *@author Fredrik Wärngård
 *@date 2015-01-14
 *
 *Lane creates a traffic lane of a certain length <strong>length</strong>
 *and has among its methods <strong>step()</strong> to move all cars in the 
 *lane one step forward if the spot ahead is not taken. It also has methods
 *<strong>firstCar()</strong> that returns if the is a car in the lanes'
 *first spot, <strong>getFirst()</strong> that returns that same car, 
 *<strong>lastFree()</strong> that checks the last spot of the lane for the
 *same thing, and <strong>putLast(Car newCar)</strong> that puts a car in the
 *last spot. The class also contains a <strong>toString()</strong>-method.
 */

public class Lane {
    // Undantag som kastas när det inte gick att lägga 
    // in en ny bil på vägen
    public static class OverflowException extends RuntimeException {
	public OverflowException(String message) {
	    super(message);
	}
    }
    
    private Car[] theLane;

    // Konstruerar ett Lane-objekt med plats för n fordon
    public Lane(int length) {
    	theLane = new Car[length];
    }

    /**
     *The step function starts checking the first element in the lane. If that 
     *element is null, then the second element is moved over from spot 2 to 
     *spot 1, followed by setting spot 2s' element to null. Because of its 
     *iterative loop, this is followed with doing the same operation with spot 2
     *and its next spot, and so on until the lanes' second last element that 
     *will do its copy from the last element.
     */
    public void step() {
	for(int i = 0; i < theLane.length - 1; ++i){
	    if(theLane[i] == null){
		theLane[i] = theLane[i+1];
		theLane[i+1] = null;
	    }
    	}
    }

    /**
     *It will return the car in the first spot of the lane and set the lanes'
     *first spot to null
     *@return Car the first car in the lane
     */
    public Car getFirst() {
    	Car first = theLane[0];
    	theLane[0] = null;
    	return first;
    }

    /**
     *Returning the car in the first spot of the lane without removing it from 
     *the lane
     *@return Car the first car in the lane
     */
    public Car firstCar() {
    	return theLane[0];
    }

    /**
     *Returns true if the last spot of the lane is free, Otherwise false, a 
     *case that will only occur if there is a queue on the lane all up there.
     *@return boolean true if last spot is free, otherwise false
     */
    public boolean lastFree() {
    	return theLane[theLane.length - 1] == null;
    }

    /**
     *Will insert a car on the last spot of the lane.
     *@param inputCar
     *@throws OverflowException in case the lane is full
     */
    public void putLast(Car inputCar) throws OverflowException {
    	if(lastFree()){
	    theLane[theLane.length - 1] = inputCar;
    	} else {
	    throw new OverflowException("The lane is full, stop sending cars!");
    	}
    }

    /**
     *Will print the lane with empty spots represented as "-" and elements as
     *their defined toString()-methods-
     *@return String the representation of the lane
     */    
    public String toString() {
	String printLane = "";
	for (int i = 0; i < theLane.length; i++) {
	    if (theLane[i] != null) {
		printLane += theLane[i].toString();
	    } else {
		printLane += "-";
	    }
	}
	return printLane;
    }
}
