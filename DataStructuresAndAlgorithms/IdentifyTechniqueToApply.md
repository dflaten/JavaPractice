# Overview
This document outlines a methodology of identifying what type of technique you
should apply to a given Programming Problem. This for now focuses on the
current list of most common Leetcode problems and the flowchart on [this
site](https://sebinsua.com/algorithmic-bathwater) was used as the base. 

## Technique Decision Tree 
First we must identify what type of general problem are we trying to solve. 

1. Optimization Problem? Are we trying to find the best possible solution from
   a list of possible solutions?
2. Recover Order of Elements?
3. Interconnected Points?
4. Efficient Lookups of data?
5. Linear/sequential data structure?

### Optimization Problems

1. Do the local optimmum decisions lead to a global optimum? If so use a [Greedy
   Algorithm](GreedyTechniques/Greedy.md). 
2. Do solutions to smaller problems help us solve the bigger one (optimal
   subsctructure of problem)? Do some of these smaller problems have overlap
   with each other or do we find ourselves solving the same problem over and
   over? If so use [Dynamic
   Programming](RecursionAndDynamicProgramming/RecursionDynamicProgramminmg.md)
   to solve the problem.
