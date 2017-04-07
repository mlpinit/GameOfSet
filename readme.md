# Introduction - Game Of Set
This is a basic implementation of the popular game of [Set](https://goo.gl/uyBVMl) using java and the swing GUI.

If you want to play the game you can download it [here](https://github.com/mlpinit/GameOfSet/blob/master/dist/GameOfSet-20170407.jar).

![Game Of Set Screenshot](/screenshot.png)

Outline:

* [Project Dependencies](#project-dependencies)
* [Pull Requests](#pull-requests)
* [Scripts](#scripts)
* [TODO](#todo)

## Project Dependencies
 
I've used **[ant](http://ant.apache.org/)** for the build process. If you don't have ant and you're on a mac you can install it with homebrew ``brew install ant``. Testing is done using **[JUnit](http://junit.org/junit4/)**. If you have ant installed, it will take care of this dependency for you durring the build/test phases.

## Pull Requests

Pull requests are welcome. If you'd like to make a pull requests please add tests and run ``./scripts/test.sh`` to make sure nothing is failing. If something is not clear just ping me and I'd be happy to work with you on getting your feature in.

## Scripts

There are only two scripts at this point: 

* ``./scripts/test.sh`` - this cleans, compiles and tests.
* ``./scripts/dist.sh`` - this cleans, compiles and creates an executable under '/dist'.

Check the build.xml file for more details.

## TODO

List of some potentially useful todos:

* Handle the unlikely scenario where there is no set in 15 cards.
* At the end of the game, add a button on the message dialog to allow for a
  restart.
* ~~Create better looking cards for the game.~~
* Display the time it took to finish the game at the end of the game.
* Pause button
* Allow different players to play the game and collect statistics.
* ~~Introduce a hint feature that highlights a set or parts (one or two cards) of a set.~~
* Display how many cards there are left in the Info Panel
* Introduce a description for the rules of the game
