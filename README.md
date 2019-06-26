# keypressiterator

The input of the program is A B C D ..... Y Z .. where A B C D are the KeyCodes to be executed. X is the number of iterations to be performed for the given KeyCode set. Z is the time interval in milliseconds between each key execution. It is a good practice to keep the time interval within the range of 300-500.

e.g. 9P 9R 9 32 100 400

Here P & R stand for Press and Release respectively. If P is used, the corresponding key is pressed and not released. Similarly, if R is used, the key is only released. If no param is used then the key will be pressed and released consecutively.

This means here Tab key will be pressed, then released and then again pressed and released consecutively followed by the space key press and release. The iteration count is 100 and 400 is the millisecond time gap between each key execution.
