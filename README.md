# Algorithms-sandbox

A java application created to experiment with standard algorithms. 

The pipeline/concept is pretty straightforward : 
- An algorithm for the maze generation
- Another algorithm for solving it

As both steps are animated (and can take a long time depending on the size of the maze), one can decide to skip the cool animation of the generation by setting the fast forward flag in the settings.java file. It is also possible to mess with the node/tile size or with the maze dimensions by modifying parameters in that same file.

Here's a picture of what the final result looks like using the Depth-first search for generation and A* for solving the maze:

Note that walls are pictured as black, floor as gray, potential solutions found by the A* algorithm are blue and final solution is green.


![alt tag](https://raw.githubusercontent.com/frtru/MazeSandbox/master/gen_dfs_sol_astar.jpg)
