package model;

import main.Heart;

public interface PaceModeInterface {
	
	/**
	 * Method that produces heart beats. It is possible to specify
	 * atrium and ventricle failure rate,as well as heart rate. 
	 * @param a - failure rate(1=10%) of atrium
	 * @param v - failure rate(1=10%) of ventricle
	 * @param rate - heart rate
	 */
	//public void heart(int a, int v, int rate);

	/**
	 * Not implemented yet
	 */
	public void pacemaker(Heart heart);
	/**
	 * Method that produces single heart beat with some chance
	 * @param chance - the chance of producing a beat
	 * @return the number that occur 
	 */
	//public int bump(int chance);
}
