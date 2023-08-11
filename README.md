# Algorithms-sandbox

A java application created to experiment with standard algorithms. 

The pipeline/concept is pretty straightforward : 
- An algorithm for the maze generation
- Another algorithm for solving it

Here are samples using Depth-first search for generation and A* for solving the maze:

| ![](media/13.gif) | ![](media/14.gif) |
|------------|----------------|

| ![](media/1.gif) | ![](media/2.gif) | ![](media/3.gif) |
|----------------------------------------------|---------------------------------------------------|------------------------------------------------------|
| ![](media/4.gif) | ![](media/5.gif) | ![](media/6.gif) |
| ![](media/7.gif) | ![](media/8.gif) | ![](media/9.gif) |
| ![](media/10.gif) | ![](media/11.gif) | ![](media/12.gif) |

As both steps are animated (and can take a long time depending on the size of the maze), one can decide to skip the cool animation of the generation by setting the fast forward flag in the settings.java file. It is also possible to mess with the node/tile size or with the maze dimensions by modifying parameters in that same file.

Note that the start and end nodes are on the upper left corner and lower right corner respectively. Walls are pictured as black tiles, floor as gray, potential paths during the exploration are blue and final solution is green.
