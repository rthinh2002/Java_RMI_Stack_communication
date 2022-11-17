Student id: a1807507
Name: Xuan Thinh Le
Assignment name: Distributed System Assignment 1 - Semester 2, 2022

Overview of the assignment:
Using Java RMI to perform a stack on the server with the connection of multiple clients.
I have successfully created the program with just simple Java RMI, in my opinion, the hard part of this assignment should be the testing
since we have to think of a way to demonstrate how did we test the program

===TESTING===
Quick note: For all testing, please make sure that there is not java SorterClient running in the background of your machine
To check if there are any java SorterClient running in the background, please use the command (ps -ax) and kill it with (kill <pid>)

---Testing Single Client Instruction---
Nagivate to the assignment folder, and run ./testingSingleClient
Note that this script will terminate all of your rmiregistry and Server running in the background
Output will be in output.txt

---Testing Multiple Clients Instruction---
Nagivate to the assignment folder, and run ./testMultiClient
This script will also terminate all of the rmiregistry and Server running in the background
Due to the reason of testing multi-clients as multi-processes running behind the background, so the output will be not deterministic and 
different each time, so there is no expected output file
Instead, the script will display the process running for each client to demonstrate that each client has the ability to access to the stack
Note that each Client will have a different test file with different length so the final stack display for each client will be different since final
stack will display when the clients finish running their last command.
The output for clients will be in outputClient1.txt, outputClient2.txt, outputClient3.txt, outputClient4.txt

--Error case--
If you got error while running the script, use ps -ax command and kill all process with rmiregistry, SorterServer, and SorterClient and run the script again

Thank you!