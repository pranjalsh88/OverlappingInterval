# OverlappingInterval

###Approach:

I am using an interval tree approach to solve this problem
Interval tree will store all intervals in a BST manner and will store
an interval (an int array of size 2) into every node along with the max value of end time in the corresponding subtree

For every interval, the algorithm will check for overlapping interval and will insert the interval into the tree and 
will add the conflicting intervals to the output. 

Alternatively, I can use recursion to solve the same problem as inserting into the tree.



###Complexity:
List of size n

Each insertion will be O(logn)
finding Overlaps will be O(logn)
and I am iterating through the input list

Effectively, the complexity will be O(nlogn)

