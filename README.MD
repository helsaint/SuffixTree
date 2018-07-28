# SuffixTree


The code here is just work on understanding how a Trie works with a focus on solving
the longest palindromic substring. The code is not efficient as the runtime to build
the tree is O(N^2) and runtime of finding the palindrome is O(N^2). 
The solution here first finds all common substring between S (original string) and
R (the same string in reverse order). We then use a simple brute force palindrome
finding algorithm on all common substrings to find the palindromes. Again not very
efficient.



Code to create the tree. The root is created and the tree just holds the location of the root node.
That is why we only have getRoot()
All other nodes will be found through a walk of the tree starting at the root.
We implement a getChild which is simply a call to the node object's get child.
This is done simply to allow access to nodes through a single object making it less confusing.
See node description below to see what is stored in the node.



The Node object stores all the important information. Including the letter we are viewing (element),
if this node represents end of a suffix (boolEnd), the location in the suffix that node belongs to
(index) and all children/letters that follow it.

