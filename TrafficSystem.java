import java.util.Properties;
import java.io.*;

/**
 *Put the other classes together and make a general step through the 
 *trafficsystem with changing lights, creating new cars and lanes, and steps
 *through the said lanes with the said cars as time goes by. It keeps track
 *of the systems "global" time as it set that along with the other parameters 
 *for each class after reading them from input. It has also a method for 
 *printing statistics as well as printing a visual representation of the lanes.
 *
 *@author Fredrik Wärngård
 *@date 2015-01-14
 */

public class TrafficSystem {
    private Lane  startLane;
    private Lane  laneToDest1;
    private Lane  laneToDest2;
    private Light light1;
    private Light light2;

    private int arrivalIntensity;
    private int laneChoice;
    
    private int totalWaitingTime = 0;
    private int laneToDest1WaitingTime = 0;
    private int laneToDest2WaitingTime = 0;
    private int carsEntered = 0;
    private int carsExited = 0;
    private int carsLane1Exited = 0;
    private int carsLane2Exited = 0;
    
    private int time = 0; 
    
    public TrafficSystem() {
    	readParameters();
    }

    /**
     *Reads from file with the method <strong>getProperty</strong> from the 
     *<strong>Properties</strong>-class. Set the variables that are needed for
     *setting up the traffic system. The variables for statistics and time are
     *by default supposed to be set to 0, due to how I chose to construct it.
     *But this can of course be changed by hand.
     *@throws FileNotFoundException 
     *@throws IOException 
     *@throws IllegalArgumentException 
     */
    public void readParameters() {
	try {
	    Properties parameters = new Properties();
	    parameters.load(new FileInputStream("simulation.properties"));
	    startLane = new Lane(Integer.parseInt(parameters.getProperty("startLane")));
	    laneToDest1 = new Lane(Integer.parseInt(parameters.getProperty("turnLanes")));
	    laneToDest2 = new Lane(Integer.parseInt(parameters.getProperty("turnLanes")));
	    int period = Integer.parseInt(parameters.getProperty("period"));
	    int green1  = Integer.parseInt(parameters.getProperty("greenTime1"));
	    light1 = new Light(period, green1);
	    int green2 = Integer.parseInt(parameters.getProperty("greenTime2"));
	    light2 = new Light(period, green2);
	    arrivalIntensity = Integer.parseInt(parameters.getProperty("arrivalIntensity"));
	    laneChoice = Integer.parseInt(parameters.getProperty("laneChoice"));;
	} catch(FileNotFoundException e) {
	    System.out.println("Finding the file went wrong : " + e.toString());
	} catch(IOException e) {
	    System.out.println("Something with the IO went wrong : " + e.toString());
	} catch(IllegalArgumentException e) {
	    System.out.println("Light parameters (green > period == not cool) : " + e.toString());
	}
    }
    
    /**
     *For statistics, this will save data from a car. with the method
     *<strong>Car.timeInSystem(time)</strong> we will increment the total runtime
     *variable for all cars through the system with the current cars runtime, and 
     *also we will increment the same function for the lane that the car just
     *ran through. Along with that we will also increment the <strong>carsExited</strong> and 
     *<strong>carsLane1Exited</strong> or <string> carsLane2Exited</strong> variables, as this methods will
     *be ran when we remove cars from the lane.
     *@param car the car which data we're trying to extract
     */
    private void extractCarData(Car car) {
	float timeInSystem = car.timeInSystem(time);
	totalWaitingTime += timeInSystem;
	if (car.getDestination() == 1) {
	    laneToDest1WaitingTime += timeInSystem;
	    ++carsLane1Exited;
	    
	}
	if (car.getDestination() == 2) {
	    laneToDest2WaitingTime += timeInSystem;
	    ++carsLane2Exited;
	}
	++carsExited;
	
    }

    /**
     *Connects the parts for simulating a step through the traffic system. At first we
     *pick out the first cars of the forked lanes, if there are any cars there, and we
     *extract their data for our statistic variables. Then we update the lanes and create
     *new cars if fate, and our arrivalIntensity variable has told us that it is time to
     *do so. If we want to input a new car when the last spot is taken, an exception is 
     *thrown. If it is not taken all is alright and well and we also increment our
     *<strong>carsEntered</strong> variable (for statistics). At last all clocks are incremented.
     *@throws OverflowException
     */
    public void step() {
	if (laneToDest1.firstCar() != null) {
	    if (light1.isGreen()) {
		Car exitCar = laneToDest1.getFirst();
		extractCarData(exitCar);
	    }
	}
	laneToDest1.step();
	
	if (laneToDest2.firstCar() != null) {
	    if (light2.isGreen()) {
		Car exitCar = laneToDest2.getFirst();
		extractCarData(exitCar);
	    }
	}		
	laneToDest2.step();

	if (startLane.firstCar() != null) {
	    int dest = startLane.firstCar().getDestination();
	    if (dest == 1) {
		if (laneToDest1.lastFree()) {
		    try { 
			laneToDest1.putLast(startLane.getFirst());
		    } catch(Lane.OverflowException e) {
			System.out.println("left lane is full : " + e.toString());
		    }
		}
	    }
	    if (dest == 2) {
		if (laneToDest2.lastFree()) {
		    try { 
			laneToDest2.putLast(startLane.getFirst());
		    } catch(Lane.OverflowException e) {
			System.out.println("right lane is full : " + e.toString());
		    }
		}
	    }
	}
	startLane.step();
	if (Math.random() * 100 < arrivalIntensity) {
	    int whichLane = (Math.random() * 100 < laneChoice) ? 1 : 2;
	    try {
		startLane.putLast(new Car(this.time, whichLane));
		++carsEntered;
	    } catch (Lane.OverflowException e) {
		System.out.println("start lane is full : " + e.toString());
	    } 
	}
	time++;
	light1.step();
	light2.step();	
    }

    /**
     *Prints statistic as described in the method below.
     */
    public void printStatistics() {
	System.out.println("Time that the simulation ran    : " + time);
	System.out.println("Steps without a traffic jam     : " + time);
	System.out.println("Cars that entered the system    : " + carsEntered);
	System.out.println("Cars that exited the system     : " + carsExited);
	System.out.println("Cars that took lane number 1    : " + carsLane1Exited);
	System.out.println("Cars that took lane number 2    : " + carsLane2Exited);
	System.out.println("Average time from enter to exit           : " + (totalWaitingTime/(float)carsExited));
	System.out.println("Average time from enter to exiting lane 1 : " + (laneToDest1WaitingTime/(float)carsLane1Exited));
	System.out.println("Average time from enter to exiting lane 2 : " + (laneToDest2WaitingTime/(float)carsLane2Exited));    
    }

    /**
     *prints the traffic lane as it looks at the moment with help from the respective classes
     *<strong>toString</strong>-methods.
     */

    // Skriv ut en grafisk representation av kösituationen med hjälp av klassernas toString-metoder
    public void print() {
	System.out.println("LIGHT        LANE         STARTINGLANE");
	System.out.print("  " + light1.toString() + "   ");
	System.out.print(laneToDest1.toString());
	System.out.println(startLane.toString());
	System.out.print("  " + light2.toString() + "   ");
	System.out.println(laneToDest2.toString());
	System.out.println("\n");
    }
}
