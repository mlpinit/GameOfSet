Work in progress...


This is a basic implementation of the popular game of [Set](https://en.wikipedia.org/wiki/Set_(game\)) using java and leveragin swing for the GUI.

If you want to play the game you can find it under dist 'dist/GameOfSet-\*'.

Pull requests are welcome. Here are some conventions used and a general
overview of the project: 

Conventions:

The image location of a given card is located under resources and it has the
following format: "<Color><Shape><Filling><Count><Type>".

Color: R-Red, G-Green, M-Mouve
Shape: S-Squiggle, D-Diamond, O-Oval
Filling: E-Empty, S-Stripped, F-Full
Count: 1, 2, 3
Type: Dark, Light

Example: "RSE2L" - This means it's a red card with 2 empty squiggles on it.

# TODO:

* Handle the unlikely scenario where there is no set in 15 cards.
* At the end of the game, add a button on the message dialog to allow for a
  restart.
* Create better looking cards for the game.
* Display time it took to finish the game at the end of the game.
* Allow different players to play the game and collect statistics.
