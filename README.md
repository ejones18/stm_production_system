# A short-term memory, production system for packing grocery bags
Built upon provided sample code as part of a first year assignment, this repository contains the code for an improved short-term memory, rule-based production system.

## The Problem
 As shopping items are passed through the checkout, they must be put into bags in the most efficient way possible. The aim is to minimise the number of bags that have been used once all the items have been packed.
 
### Simplifications
* All bags have a space capacity of 100.
* All items are stacked one on top of the other.
* We don’t care about item fragility or overall weight of the bags or items - we just care about using the space in the bags most effectively.

### Restrictions
* The ‘trolley’ will contain a number of items in a set order. You cannot pre-sort the items or put them to one side to maximise efficiency - you must bag them in the order they appear.

## Repository details
* To run the Bagging system, compile and run RunProductionSystem.java (note: it is compatible with Java 1.8+; it’s also ready to build in IntelliJ).
* The group of files with names beginning `Basic` implement a basic algorithm for Bagging whereas any with `Improved` refer to my improved production rules.
* Each of the files is a separate Production rule (note: they all extend Production.java).

## Special mention
Huge thanks to the folks teaching at The UoSheffield, specifically the Machines&Intellgience course, for a super interesting assignment!
