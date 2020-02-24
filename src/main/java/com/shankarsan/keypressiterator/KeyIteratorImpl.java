/**
 * 
 */
package com.shankarsan.keypressiterator;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author SHANKARSAN
 *
 */
public class KeyIteratorImpl implements KeyIterator {
	
	private static KeyIterator keyIterator;
	
	public static KeyIterator getInstance() {
		if(null == keyIterator) {
			keyIterator = new KeyIteratorImpl();
		}
		return keyIterator;
	}
	
	private KeyIteratorImpl() {
		
	}
	
	/**
	 * @param args
	 */
	public void process(String[] args) {
		List<String> argList = null;
		Robot keyPressRobot = null;
		int sleepInterval = 0, iterations = 0;
		String keyCode = null;
		String keyCodeArg = null;
		Set<Integer> pressedKeysList = null;
//		System.out.println(KeyEvent.VK_WINDOWS + " " + KeyEvent.VK_TAB);
		try {
			if (ArrayUtils.isNotEmpty(args) && args.length > 1 &&
					(argList = Arrays.asList(args))
						.stream()
						.allMatch(arg -> 
							NumberUtils.isParsable(arg.split(Constants.KEY_PRESS_FLAG)[0]) || NumberUtils.isParsable(arg.split(Constants.KEY_RELEASE_FLAG)[0])
						)) {
				sleepInterval = NumberUtils.toInt(argList.get(argList.size() - 1));
				iterations = NumberUtils.toInt(argList.get(argList.size() - 2));
				keyPressRobot = new Robot();
				
				Thread.sleep(3000);
				while(iterations-- > 0) {
					for(int index = 0; index < argList.size() - 2; index++) {
						keyCodeArg = argList.get(index);
						keyCode = keyCodeArg.split(Constants.KEY_PRESS_FLAG)[0].split(Constants.KEY_RELEASE_FLAG)[0];
						if(keyCode.equals(keyCodeArg)) {
							keyPressRobot.keyPress(NumberUtils.toInt(keyCode));
							keyPressRobot.keyRelease(NumberUtils.toInt(keyCode));
						}else if(keyCodeArg.contains(Constants.KEY_PRESS_FLAG)) {
							keyPressRobot.keyPress(NumberUtils.toInt(keyCode));
							if(null == pressedKeysList) {
								pressedKeysList = new HashSet<>();
							}
							pressedKeysList.add(NumberUtils.toInt(keyCode));
						}else if(keyCodeArg.contains(Constants.KEY_RELEASE_FLAG)) {
							keyPressRobot.keyRelease(NumberUtils.toInt(keyCode));
						}
						Thread.sleep(sleepInterval);
					}
				}
			} else {
				System.out.println("Invalid input!!! Please enter the list of key codes as integer, each integer followed by '!' if key is to be put on hold. The iteration count as second last param and the interval in seconds as the last pararm!");
			}
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(null != pressedKeysList && null != keyPressRobot) {
				for(Integer pressedKeyCode : pressedKeysList) {
					keyPressRobot.keyRelease(pressedKeyCode);
				}
			}
		}
	}

}
