# Colored Tile Puzzle AI

![Puzzle_tile](https://user-images.githubusercontent.com/44750316/86928663-85ca1280-c13d-11ea-809b-58097764e4e2.png)
#### (credit for the picture: https://bit.ly/2ZRH9fl)

This game implements a search engine that supports several search algorithms to solve the Puzzle Tile.
In this game given a NxM board containing:
1. NxM-1 tiles numbered from 1 to NxM-1.
2. Blank tile.
3. Some of the numbered tiles  are painted in black, some in green, and some in red.

The tiles are arranged in some given starting order.

## The Goal is:
Find the cheapest number of operations from the strat state to the goal state. In the goal state, all tiles are arranged from left to right and top down (regardless of their color), with the empty tile in the bottom right corner

![Screenshot_3](https://user-images.githubusercontent.com/44750316/86930582-db072380-c13f-11ea-898e-a2870a21335b.png)

## Roles:
Unlike the usual puzzle-tile game, where every move is considered one step, this game has different rules and different costs dependencies
the tiles color.
 1. A black painted block cannot be moved at all. Any other block that is near the empty block can be moved. 
 2. Sliding of a green block for the empty block costs 1.
 3. Sliding a red block for the empty block costs 30.
This Game gets as input a .txt file that invoke the game with custom Scenario.

### For example:

![image](https://user-images.githubusercontent.com/44750316/86932148-a4caa380-c141-11ea-85c7-e8808111af0a.png)


![detective-1424831_640](https://user-images.githubusercontent.com/44750316/86933022-c2e4d380-c142-11ea-8c7d-9061b931f491.png)

### For searching the chepest solution we used several known algorithms like:
 1. BFS.
 2. A*.
 3. DFID.
 4. IDA*.
 5. DFBnB.

### Enjoy!!


