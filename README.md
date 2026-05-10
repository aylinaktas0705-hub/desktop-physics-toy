# Desktop Physics Toy:
A transparent desktop overlay built in Java. A physics ball bounces around the screen and open windows.

- Basic physics simulation with friction, bounce, and gravity
- Drag and throw ball with your mouse
- Use Desktop Windows as platforms (may not work well if windows runs too many background operations)

## How to run:
- JDK 21 must be installed
- Compile: javac -cp .;jna-5.14.0.jar;jna-platform-5.14.0.jar *.java
- Then run: java --enable-native-access=ALL-UNNAMED -cp .;jna-5.14.0.jar;jna-platform-5.14.0.jar Main

Built with Java 21 and JNA :)
