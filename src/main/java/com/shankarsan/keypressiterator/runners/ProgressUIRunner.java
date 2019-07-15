/**
 * 
 */
package com.shankarsan.keypressiterator.runners;

/**
 * @author SHANKARSAN
 *
 */
public class ProgressUIRunner implements Runnable {
	private volatile boolean exit = false;
	@Override
	public void run() {
		try {
			String animElements = "/|\\-";
			for(int index = 0; index < animElements.length() && !exit; index++) {
				System.out.print("\b");
				System.out.print(animElements.charAt(index));
				System.out.flush();
				if(index == animElements.length() - 1) {
					index = -1;
				}
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		this.exit = true;
	}
}
