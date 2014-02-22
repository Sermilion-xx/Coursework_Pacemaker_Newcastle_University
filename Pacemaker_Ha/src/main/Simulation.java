package main;

import java.util.concurrent.SynchronousQueue;

import model.*;

public class Simulation {
	static boolean pacemaker_on=true;
	public static SynchronousQueue<String> syncQueue = new SynchronousQueue<String>(true);
	static AbstractPaceMode mode;
	
	public static void main(String[] args) {
		
		
		//Atrium failure 80%, Ventrice - 0%, heart rate exceeding 0.
		Heart heart = new Heart(2,10,0);
		new Thread(heart).start();
		
		
		if(pacemaker_on==false){
		//Simulation of heart alone
		for(int i=0;i<100;i++){
			try {
				Simulation.syncQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		}else{	
			mode = (Mode_DOO)AbstractPaceMode.getInstance("DOO");
			mode.pacemaker(heart);
		}
	}

	public void setMode(String modeString){
		// todo
		// only authorized user can set mode
	}
	
	public void sendRecord(){
		// todo
		// send the record to doctor in a secure way
	}
}
