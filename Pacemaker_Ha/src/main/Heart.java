package main;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public final class Heart implements Runnable {

	// failure percentage, 10 = 100%
	private final int atriumFailureRate;
	private final int ventricleFailureRate;
	// extra from normal heart rate: 0-100
	private int heartRate = 0;

	// add for the case we need to skip the heart beat
	private int bump = 0;

	public Heart(int aFailure, int vFailure, int rate) {
		// check parameters
		if (aFailure > 10 || aFailure < 0 || vFailure > 10 || vFailure < 0
				|| rate > 100 || rate < 0) {
			throw new IllegalArgumentException("Invalid heart argument!");
		}

		this.atriumFailureRate = aFailure;
		this.heartRate = rate;
		this.ventricleFailureRate = vFailure;
	}

	public int getAtriumRate() {
		return this.atriumFailureRate;
	}

	public int getVentricleRate() {
		return this.ventricleFailureRate;
	}

	public int getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(int rate) {
		this.heartRate = rate;
	}

	public int getBump() {
		return this.bump;
	}

	public void setBump(int b) {
		this.bump = b;
	}

	// bump(2) - 20% chance that beat will occur
	// return: 1 - bump
	// 0 - does not bump
	public int bump(int chance) {
		Random random = new Random();
		int r = random.nextInt(11);
		if (r <= chance) {
			return 1;
		}
		return 0;
	}

	// //----------------------------------------/
	// long tStart1 = System.currentTimeMillis();
	// long tEnd1 = System.currentTimeMillis();
	// long tDelta2 = tStart1 - tStart2;
	// double elapsedSeconds2 = tDelta2 / 1000.0;
	// System.out.println(elapsedSeconds2);
	// //----------------------------------------/
	@Override
	public void run() {
		final Heart heart = this;

		new Timer().schedule(new TimerTask() {
			public void run() {
				try {
					heart.setBump(bump(heart.getAtriumRate()));
					// Atrium beats
					if (heart.getBump() > 0) {
						// put A to the queue, signal that Atrium has beat
						Simulation.syncQueue.put("A");
						
						try {
						    Thread.sleep(50);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						
						if(heart.getBump()!=-1)
						System.out.println("Natural Bump Atrium");

						// Atrium does not beat
					} else {
						// put AO to the queue, signal that Atrium has not beat
						Simulation.syncQueue.put("AO");
						System.out.println("Skip Atrium");
					}

					// time beetween Atrium and Ventricle beats
					Thread.sleep((long) (200 - (heart.getHeartRate() * 1.5)));

					// ****************************************/

					heart.setBump(bump(heart.getVentricleRate()));
					// Ventricle beats
					if (heart.getBump() > 0) {
						// put V to the queue, signal that Ventricle has beat
						Simulation.syncQueue.put("V");
						System.out.println("Natural Bump Ventricle");
						System.out.println("-------------------");

						// Ventricle does not beat
					} else {
						// put VO to queue, signal that Ventricle hasn't beat
						Simulation.syncQueue.put("VO");
						System.out.println("Skip Ventricle");
						System.out.println("-------------------");
					}

					// sleep time after two beats
					// ****************************************/

					Thread.sleep(1000 - (heart.getHeartRate() * 10));

					// ****************************************/
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1, 1);

	}
}
