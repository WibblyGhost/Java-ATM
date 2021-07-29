# Java-ATM
___
###Purpose
This project started as coding challenge that was given to me. From this challenge I have created a simple virtual ATM. This machine should be able to store different types and quantities of notes. Users should be able to withdraw notes and check the machines balance. Given the case where the withdrawal amount cannot be satisfied the ATM should be able to signify how much cash it can divvy. 

###Thought Processes used
When looking at the problem, I identified the main classes/objects that could be used, their attributes, and main methods.  

When I was thinking about how to dispense the notes for the withdrawal amount I wrote some pseudocode to check the logic worked for a few test cases.

I thought about whether this was a console application or a forms' application. I chose to implement it using forms layout due to the better layout control.

I considered using modulo and remainders to calculate the number of notes from each drawer which would have been more efficient and require more complex code/conditional tests. However, I decided to use a simpler coding approach.

I kept the user prompts and displays in the AtmProgram so that the ATM Program was the only class dealing with presentation.

###Possible Improvements 
Currently, the ATM drawers don't currently persist to the disk, a future enhancement could serialize the object to JSON and write the changed state to a file on the disk. On re-entry to the ATM the last known state could be read and used to initialize the drawers.
  
Initialization of the drawers currently is defined in the source code. However, it could be put in a config file and read in on first run.

Styling of the user interface could be improved by removing dialog popups and changing colors and fonts