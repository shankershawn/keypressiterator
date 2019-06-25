/**
 * 
 */
package com.shankarsan.keypressiterator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author SHANKARSAN
 *
 */
public class MainController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> argList = null;
		Robot keyPressRobot = null;
		int sleepInterval = 0, iterations = 0;
//		System.out.println(KeyEvent.VK_SPACE + " " + KeyEvent.VK_TAB);
		try {
			if (ArrayUtils.isNotEmpty(args) && args.length > 1 && (argList = Arrays.asList(args)).stream().allMatch(arg -> NumberUtils.isParsable(arg))) {
				sleepInterval = NumberUtils.toInt(argList.get(argList.size() - 1));
				iterations = NumberUtils.toInt(argList.get(argList.size() - 2));
				keyPressRobot = new Robot();
				Thread.sleep(5000);
				while(iterations-- > 0) {
					for(int index = 0; index < argList.size() - 2; index++) {
						keyPressRobot.keyPress(NumberUtils.toInt(argList.get(index)));
						keyPressRobot.keyRelease(NumberUtils.toInt(argList.get(index)));
						Thread.sleep(sleepInterval);
					}
				}
			} else {
				System.out.println("Invalid input!!! Please enter the list of key codes as integer, the iteration count as second last param and the interval in seconds as the last pararm!");
			}
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
