package model;

import java.util.Timer;
import java.util.TimerTask;

import main.Heart;
import main.Simulation;

public class Mode_AAD extends AbstractPaceMode {

	Mode_AAD() {
		super("A", "A", "D");
	}
/**
 * Method that monitors signals from heart and either adds or inhibits paces.
 * @param heart - heart object
 * @return void
 **/
	@Override
	public void pacemaker(final Heart heart) {
		
		new Timer().schedule(new TimerTask() {
			public void run()  {
				try {
					String a =  Simulation.syncQueue.take();
					if(a.equals("A")){
						//if A -> natural beat happened, replace with artificial.
						// why do we need to set the bump?
						heart.setBump(-1);
						//initiating beat by pacemaker
						
						heart.bump(10);
						System.out.println("Suppressed-Pace Atrium ");
					}else if(a.equals("AO")){
						heart.bump(10);
						System.out.println("Extra-Pace Atrium ");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			}, 1, 1);	
	}
}
