/**
 * 
 */
package com.shankarsan.keypressiterator;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		List<String> argsList = null;
		int keyCombinationTimeAllot = 0;
		KeyPressCapturer keyPressCapturer = null;
		String[] keyCombinationArray = null;
		JFrame frame = null;
		JLabel label = null;
		JLabel timeLabel = null;
		JPanel panel = null;
		try {
			argsList = new ArrayList<String>();
			System.out.print("*********** Welcome to KeyPressIterator Program!!! ***********");
			System.out.print("\nPlease enter the time (in seconds) you need to enter the desired key-combination: ");
			Scanner scanner = new Scanner(System.in);
			keyCombinationTimeAllot = NumberUtils.toInt(scanner.next());
			System.out.print("Now please enter the pause time (in milliseconds) between each key execution. Note that higher pause time will help you to visualize the changes better: ");
			argsList.add(scanner.next());
			System.out.print("Please enter the number of key-combination iterations you desire: ");
			argsList.add(scanner.next());
			System.out.print("WARNING: The next step is for entering the key-combination for your choice. Please note that you have alloted " + keyCombinationTimeAllot +
					" seconds for this purpose. A countdown timer will run showing the time remaining before the iteration starts. Please press any key to begin.");
			System.in.read();
			
			keyPressCapturer = new KeyPressCapturer();
			frame = new JFrame();
			frame.setSize(600, 600);
			frame.addKeyListener(keyPressCapturer);
			frame.setAlwaysOnTop(true);
			panel = new JPanel(new GridLayout(2, 1, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			frame.add(panel);
			label = new JLabel();
			timeLabel = new JLabel();
			timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			timeLabel.setVerticalAlignment(SwingConstants.TOP);
			panel.add(timeLabel);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setVerticalAlignment(SwingConstants.TOP);
			panel.add(label);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			keyCombinationTimeAllot *= 5;
			while(keyCombinationTimeAllot > 0) {
				timeLabel.setText(keyCombinationTimeAllot / 5 + " seconds remaining.");
				label.setText("<html>" + keyPressCapturer.toString() + "</html>");
				keyCombinationTimeAllot--;
				Thread.sleep(200);
			}
			
			keyCombinationArray = ArrayUtils.toStringArray(keyPressCapturer.getKeyCombination().toArray());
			ArrayUtils.reverse(keyCombinationArray);
			argsList.addAll(Arrays.asList(keyCombinationArray));
			
			keyCombinationArray = ArrayUtils.toStringArray(argsList.toArray());
			ArrayUtils.reverse(keyCombinationArray);
			argsList = Arrays.asList(keyCombinationArray);
			
			scanner.close();
			
			System.out.println(argsList.toString());
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			KeyIteratorImpl.getInstance().process(keyCombinationArray);
		} catch (HeadlessException | SecurityException | IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
