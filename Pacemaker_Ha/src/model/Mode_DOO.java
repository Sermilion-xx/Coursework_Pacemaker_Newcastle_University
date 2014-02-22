package model;

import java.util.Timer;
import java.util.TimerTask;

import main.Heart;
import main.Simulation;

public class Mode_DOO extends AbstractPaceMode {

	Mode_DOO() {
		super("D", "O", "O");
	}

	@Override
	public void pacemaker(final Heart heart) {
		
		new Timer().schedule(new TimerTask() {
			public void run() {
				try {
					// this mode will not sense anything, but only signal the paces to atrium and ventricle
					String a = Simulation.syncQueue.take();
//					if (a.equals("A") || a.equals("AO")) {
//						heart.bump(10);
//						System.out.println("Extra-Pace Atrium ");
//					} else if (a.equals("V") || a.equals("VO")){
//						heart.bump(10);
//						System.out.println("Extra-Pace Vetricle ");
//					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1, 1);

	}

}
