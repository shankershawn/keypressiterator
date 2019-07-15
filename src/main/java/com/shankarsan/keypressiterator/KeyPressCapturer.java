/**
 * 
 */
package com.shankarsan.keypressiterator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SHANKARSAN
 *
 */
public class KeyPressCapturer implements KeyListener {

	@Override
	public String toString() {
		return keyCombination.stream().collect(Collectors.joining(","));
	}

	private volatile List<String> keyCombination = new ArrayList<String>();
	/**
	 * @return the keyCombination
	 */
	public List<String> getKeyCombination() {
		return keyCombination;
	}

	private int keyCombinationTimeAllot = 0;
	/**
	 * @return the keyCombinationTimeAllot
	 */
	public int getKeyCombinationTimeAllot() {
		return keyCombinationTimeAllot;
	}

	/**
	 * @param keyCombinationTimeAllot the keyCombinationTimeAllot to set
	 */
	public void setKeyCombinationTimeAllot(int keyCombinationTimeAllot) {
		this.keyCombinationTimeAllot = keyCombinationTimeAllot;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyCombination.add(e.getKeyCode() + Constants.KEY_PRESS_FLAG);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyCombination.add(e.getKeyCode() + Constants.KEY_RELEASE_FLAG);
	}
}
