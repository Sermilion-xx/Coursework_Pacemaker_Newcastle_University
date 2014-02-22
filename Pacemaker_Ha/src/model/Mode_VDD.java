package model;

import java.util.Timer;
import java.util.TimerTask;

import main.Heart;
import main.Simulation;

public class Mode_VDD extends AbstractPaceMode {

	Mode_VDD() {
		super("V", "D", "D");
	}

	@Override
	public void pacemaker(final Heart heart ) {
		new Timer().schedule(new TimerTask() {
			public void run() {
				try {
					// this mode will sense both, but only signal the paces to ventricle
					String a = Simulation.syncQueue.take();
					if (a.equals("A") || a.equals("AO")) {
						// do nothing
					} else if(a.equals("V")){
						//if V -> natural beat happened, replace with artificial.
						// why do we need to set the bump?
						heart.setBump(10);
						//initiating beat by pacemaker
						heart.bump(10);
						System.out.println("Supressed-Pace Ventricle ");
					}else if(a.equals("VO")){
						heart.bump(10);
						System.out.println("Extra-Pace Ventricle ");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1, 1);
	}

}
