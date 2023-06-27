# Greedy Techniques
The Greedy technique builds a solution piece by piece choosing the next most
obvious and immediate benefit. It always makes the choice that seems the best
at the time, making a *locally-optimal choice* in the hope that it will make a
globally optimal solution. Therefore the greedy technique is used to solve
optimization problems. 

Greedy algorithms work by recursively constructing a solution from the smallest
possible constituent parts. As a reminder recursion is an approach where the
problem is solved by splitting the problem down into smaller instances of the
problem, solving those problems and then combining the results to get an
overall solution. Usually this approach does not produce the globally best
solution since it is optimizing decisions locally.

See example below where the greedy solution (shown in red choices) produces a
not globally optimal solution.

![NotGloballyOptimal](NotGloballyOptimal.png "Greedy solution, not globally optimal.")

## How to know if a problem matches this pattern?
* If selecting a series of local optima allows us to construct or identify the
  globally optimal solution. 
* Our analysis shows that making local greedy choices to not lead to a
  sub-optimal solution. 
* The problem has a local optima.
* The problem is an optimization problem. 

## Real-world Examples
* **CPU Scheduling** - Many algorithms using the greedy approach help in CPU
  scheduling. 
* **LAN Networks** - In a large LAN with many switches, finding a minimum
  spanning tree is important to ensure that only a minimum number of packets
  will be transmitted across the network. 
* **Social Networking Websites**: - Recommending a list of people a user may
  know through the Dijkstra algorithm which finds the shortest path between
  users measured through connections among them.


