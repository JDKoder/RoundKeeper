RoundKeeper
===========
Essentially this is really a practice project for TDD/BDD.  So far so good.

For sanity's sake, all NPC/PC's (Non/Player-Characters) are henceforth referred to as Actors.
A round keeper for tabletop games where there is an initiative order. The interface provides controls to
- Add/Remove Actors to the intiative order on the fly
- Modify an Actors initiative on the fly.
- Track who's turn it is
- Add other statistics to be tracked
- Modify statistics of any Actors


Currently Working On: 
Implementing Spring for IOC

Dependencies
----
Ant
Ivy
Java 19

To build:
-----
set environment variable IVY2_HOME to your .ivy2 directory
`export IVY2_HOME=/home/youruser/.ivy2`
From Roundkeeper directory run
`ant build`

To Run:
------
navigate to _target_ directory
`java -jar Roundkeeper-1.0.0.1.jar`

note: be aware if you need to build in a 32 bit environment you'll have to update the build.xml and ivy.xml files to pull the correct swt files or this will not run.


