//import traffic_system.*;
/**
 *This class will run the simulation, i.e. creating the trafficsystem
 *and run its <strong>step()</strong>-method an input number of times. The number
 *of times the loop is supposed to run is taken as an input from the 
 *command line. If we use the <strong>TrafficSystem.print()</strong>-method in the
 *loop, the graphic representation of the traffic system will be printed
 *out for each step, giving a presentation of how the system is evolving for 
 *each step.
 *
 *@author frwa3695
 *@date 2015-01-14
 */

public class Simulation {
    /**
     *Will start a traffic system and run a simulation over it args number of times.
     *@param args the number of times the loop should be ran.
     */
    public static void main(String [] args) {
	TrafficSystem ts = new TrafficSystem();
	int simulationLength = Integer.parseInt(args[0]);
	for(int i = 0; i < simulationLength; i++){
	    ts.step();
	    ts.print();
	}
	ts.printStatistics();
    }
}
