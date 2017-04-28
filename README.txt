-------------------------------------------------------
INTRODUCTION
-------------------------------------------------------
Class: CS 421 - Theory of Computing
Assignment: Programming #2
Language: Java
Executable file: castillo.jar
Test file: sequence.txt

-------------------------------------------------------
DESCRIPTION
-------------------------------------------------------
Develop a program that simulates a PDA that parses a
sequence, verifying if it complies with the if-else
C++ language paring. The following are valid paring:
1) if
2) ifelse
3) if{}
4) if{}else
5) if{}else{}

These parings can also be nested within brackets or
concatenated together.

-------------------------------------------------------
RUNNING EXECUTABLE FILE ONLY
-------------------------------------------------------
Refer to Step 4 and/or Step 5 
in the "COMPILING & RUNNING" section below.

-------------------------------------------------------
COMPILING & RUNNING
-------------------------------------------------------
Step 1: Put the following files into the same directory:
1) all *.java files specified below:
 a) Alphabet.java
 b) AlphabetExtractor.java
 c) ElseUpdater.java (Main program)
 d) EmptyUpdater.java
 e) IfUpdater.java
 f) LeftBracketUpdater.java
 g) Program.java
 h) RightBracketUpdater.java
 i) Transition.java
 j) StackItem.java
 k) StateStackUpdater.java
 l) StateStackUpdaterException.java
 m) StateStackUpdaterFactory.java
 n) State.java
 
2) sequence.txt (or any other file to parse)

Step 2: Compile java files into class files
Example-> javac *.java

Step 3: Generate .jar file
Example-> jar cvfe castillo.jar Program *.class

Step 4: Run jar
Example-> java -jar castillo.jar

Step 5 (optional): Run jar with specified file
Example-> java -jar castillo.jar sequence.txt

Note: sequence.txt can be any other file and must reside 
in the same directory as the jar file.