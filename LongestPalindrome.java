/*
* The code here is just work on understanding how a Trie works with a focus on solving
* the longest palindromic substring. The code is not efficient as the runtime to build
* the tree is O(N^2) and runtime of finding the palindrome is O(N^2). 
* The solution here first finds all common substring between S (original string) and
* R (the same string in reverse order). We then use a simple brute force palindrome
* finding algorithm on all common substrings to find the palindromes. Again not very
* efficient.
*/
class LongestPalindrome {
    private ArrayList<String> listAllWords = new ArrayList<>(); //Collects all common 
    private ArrayList<Character> listCharSS = new ArrayList<>();
    private ArrayList<String> listPalindromes = new ArrayList<>(); //Collects all palindromes
    public String longestPalindrome(String s) {
    
        Trie triePalindrome = new Trie();
        String strReturn = "";
        
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        for(int i = 0; i < s.length(); i++){
            triePalindrome.insertNode(s.substring(i));
        }
        
        findPalindromes(triePalindrome,s);
        System.out.println(listAllWords);
        for(String i: listAllWords){
            if(isPalindrome(i)) listPalindromes.add(i);
        }
        for(String i : listPalindromes){
            if (i.length() > strReturn.length()) strReturn = i;
        }
        return strReturn;
    }
    
    private void findPalindromes(Trie tP, String s){
        String reverseS = "";
        String strWord = "";
        Node current;
        
        for(int i = s.length()-1; i >= 0; i--) reverseS = reverseS + s.charAt(i);
        current = tP.getRoot();
        
        for(int i = 0; i < reverseS.length(); i++){
            strWord = "";
            findCSS(current, reverseS.substring(i));
            for(Character j : listCharSS){
                strWord += j;
            }
            listAllWords.add(strWord);
            listCharSS = new ArrayList<>();
        }
        
        return;
    }
    
    private void findCSS(Node n, String s){
        Node current;
        if(s.length() == 0) return;
        current = n.getChild(s.charAt(0));
        if(current == null){
            return;
        }else{
            listCharSS.add(current.getElement());
            findCSS(current, s.substring(1));
        }
        return;
    }
    
    private boolean isPalindrome(String s){
        if (s.length() == 1) return true;
        if (s.length() == 0) return true;
        
        if(s.charAt(0) != s.charAt(s.length()-1)) return false;
        
        if(s.length()-1 > 0) return isPalindrome(s.substring(1,s.length()-1));
        
        return true;
    }
    
}

/*
* Code to create the tree. The root is created and the tree just holds the location of the root node.
* That is why we only have getRoot()
* All other nodes will be found through a walk of the tree starting at the root.
* We implement a getChild which is simply a call to the node object's get child.
* This is done simply to allow access to nodes through a single object making it less confusing.
* See node description below to see what is stored in the node.
*/
class Trie{
    private Node root;
    private boolean boolEnd = false;
    public Trie(){
        root = new Node();
        //System.out.println("root = " + root);
    }
    
    public void insertNode(String s){
        //System.out.println("\n" + s + " " + " root: " + root);
        insertNode(root, s);
    }
    
    public Node getRoot(){
        return this.root;
    }
   
    public void insertNode(Node n, String s){
        
        if(s.length() == 0) return;
        boolEnd = false;
        //System.out.println(n + " " + s);
        char charS[] = s.toCharArray();
        
        if (s.length() == 1) boolEnd = true;
        
        Node current = n.getChild(charS[0]);
        
        if(current == null){
            current = new Node(charS[0], boolEnd, s.length());
            //System.out.println(current.getElement() + " " + current + " if");
            n.getChildren().put(charS[0], current);
            charS = null;
            if(boolEnd == true) return;
            insertNode(current, s.substring(1));
        }else{
            //System.out.println(current.getElement() + " " + current + " else");
            charS = null;
            insertNode(current, s.substring(1));
        }
        
        if(boolEnd) return;
        
    }
    
    public Node getLetter(Node n, char c){
        return n.getChild(c);
    }
    
}

/*
* The Node object stores all the important information. Including the letter we are viewing (element),
* if this node represents end of a suffix (boolEnd), the location in the suffix that node belongs to
* (index) and all children/letters that follow it.
*/
class Node{
    private char element;
    private boolean boolEnd;
    private int index;
    private HashMap<Character,Node> children;
    public Node(char c, boolean x, int i){
        this.element = c;
        this.boolEnd = x;
        this.index = i;
        this.children = new HashMap<>();
    }
    
    public Node(){
        this.children = new HashMap<>();
        this.boolEnd = false;
    }
    
    public char getElement(){
        return this.element;
    }
    
    public Node getChild(char c){
        return this.children.get(c);
    }
    
    public HashMap<Character, Node> getChildren(){
        return this.children;
    }
    
    public boolean getEndofWord(){
        return this.boolEnd;
    }
    
    public int getIndex(){
        return this.index;
    }

}


