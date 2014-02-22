package model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractPaceMode implements PaceModeInterface {
	private final String paced;
	private final String sensed;
	private final String response;
	private final String modeName;

	/**
	 * Constructor for AbstractPaceMode class.
	 * 
	 * @param P
	 *            what is paced.A - Atrium, V - Ventricle, N - Nothing, D -
	 *            Dual?
	 * @param S
	 *            what is sensed.A,V,N,D?
	 * @param R
	 *            what is response.O - Nothing T - Trigger I - Inhibit D - Dual?
	 */
	AbstractPaceMode(String P, String S, String R) {
		// check P and S
		if ((P == "A" || P == "V" || P == "O" || P == "D")
				&& (S == "A" || S == "V" || S == "O" || S == "D")
				&& (R == "O" || R == "T" || R == "I" || R == "D")) {
			this.paced = P;
			this.sensed = S;
			this.response = R;
		} else {
			throw new IllegalArgumentException("Invalid mode string");
		}

		this.modeName = this.paced + this.sensed + this.response;
	}

	/**
	 * Method for creating mode based on specified input
	 * 
	 * @param modename
	 *            - name of made that needs to be created
	 * @return object of needed mode
	 */
	public static AbstractPaceMode getInstance(String modename) {
		if (modename.equals("AAD")) {
			return new Mode_AAD();
		} else if (modename.equals("DOO")) {
			return new Mode_DOO();
		} else if (modename.equals("VDD")) {
			return new Mode_VDD();
		} else {
			throw new IllegalArgumentException("Invalid mode name");
		}

	}

	// @Override
	// public int bump(int chance) {
	// Random random = new Random();
	// int r = random.nextInt(10)+1;
	// if(r<=chance && r>=0){
	// //System.out.println("bump");
	// return r;
	// }
	// return 0;
	// }
	//
	// @Override
	// public void heart(final int a,final int v, final int rate){
	//
	// new Timer().schedule(new TimerTask() {
	// long tStart2=0;
	// boolean bump_1=false;
	// boolean bump_2=false;
	// public void run() {
	// int p = bump(a);
	// //----------------------------------------/
	// long tStart1 = System.currentTimeMillis();
	// long tEnd2 = System.currentTimeMillis();
	// long tDelta2 = tStart1 - tStart2;
	// double elapsedSeconds2 = tDelta2 / 1000.0;
	// System.out.println(elapsedSeconds2);
	// //----------------------------------------/
	// if(p>0){
	// bump_1=true;
	// System.out.println("bump 1");
	// }else{
	// System.out.println("Skip 1");
	// }
	// //****************************************/
	// try {
	// Thread.sleep((long) (150-(rate*1.5)));
	// } catch(InterruptedException ex) {
	// Thread.currentThread().interrupt();
	// }
	// //****************************************/
	//
	// p=bump(v);
	// if(p>0){
	//
	// //----------------------------------------/
	// long tEnd1 = System.currentTimeMillis();
	// long tDelta = tEnd1 - tStart1;
	// double elapsedSeconds = tDelta / 1000.0;
	// System.out.println("Success: "+elapsedSeconds);
	// //----------------------------------------/
	// bump_2=true;
	// System.out.println("bump 2  \n------------------");
	// }else{
	// //****************************************/
	// try {
	// Thread.sleep((long) 0.50);
	// } catch(InterruptedException ex) {
	// Thread.currentThread().interrupt();
	// }
	// //****************************************/
	// System.out.println("Fail: Pace");
	// //----------------------------------------/
	// long tEnd1 = System.currentTimeMillis();
	// long tDelta = tEnd1 - tStart1;
	// double elapsedSeconds = tDelta / 1000.0+0.010;
	// System.out.println(elapsedSeconds);
	// //----------------------------------------/
	// //System.out.println("Skip  \n------------------");
	// }
	//
	//
	// bump_1=false;
	// bump_2=false;
	// tStart2 = System.currentTimeMillis();
	// //****************************************/
	// try {
	// Thread.sleep(1000-(rate*10));
	// } catch(InterruptedException ex) {
	// Thread.currentThread().interrupt();
	// }
	// //****************************************/
	//
	// }
	// }, 1, 1);
	// }

}
