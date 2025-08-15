Disclaimer:   If you want something functional for the sake of keeping track of your D&D game, just go use excel.  I may never fix the simple things keeping this project from being actually useful (though I have opened issues for some of them).

This project is mostly the product of curiosity around whether it was feasible to build an offline application in java with swt, ivy, and guice.  The goal was to test implementation for different build technologies, IoC implementations and GUI stuff that no one even uses anymore simply out of curiosity.  It became an overengineered frankenstein so it will probably never get the love it might, at this point, deserve.  There are far better languages (and ways) to implement this, but at the time, I was using Java for my job and so this project is written in Java.

This project is more of a "what if I..." experiment rather than something meant to be useful.

RoundKeeper
===========
This is a java application that uses SWT to render game keeper tools for tabletop games (mainly D&D).

For sanity's sake, all NPC/PC's (Non/Player-Characters) are henceforth referred to as Actors.

Currently Features:
A round keeper for tabletop games where there is mutable turn order. The interface provides controls to
- Add/Remove Actors to the intiative order on the fly
- Modify an Actors initiative on the fly.
- Track who's turn it is

Currently Working On: 
--View structure strategy (code scalability)--


Distribution
----
Currently there is no formal distribution.  If you would like to try the program without warranty or support, there is a runnable jar inside of the dist folder.

Roundkeeper-{version}-{system}.jar

Skip to "To Run" section for instructions on executing the jar.

Dependencies
----
Ant
Ivy
Java 17 (necessitated by the latest version of Guice)

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


