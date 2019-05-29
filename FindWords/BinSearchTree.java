/**
 * This class uses a linked structure to implement a binary search tree. 
 * @author Dongzheng (Elizabeth) Xu
 */
public class BinSearchTree {

	/**
	 *  This is reference to the root node of the binary search tree
	 */
	private BinSearchTreeNode root;

	/**
	 * Initializes recursion for the search in the binary search tree for the node containing the searchword.
	 * @param searchWord word to be looked for
	 * @return node of binary tree storing searchWord
	 * @return null if no node stores it
	 *///HOW DOES SEARCH TREE KNOW WHAT THE ROOT IS???
	public BinSearchTreeNode getWord(String searchWord) {

		BinSearchTreeNode nodeWithWord= search(root,searchWord);// starts search at root node
		return nodeWithWord;

	}

	/**SELF ADDED METHOD
	 * This searches a binary search tree for the node with searchword.
	 * 
	 * @param root node
	 * @param searchWord word to be looked for
	 * @return node of binary tree storing searchWord
	 * @return null if no node stores it
	 */

	//new method to ensure root of binary search tree remains the same
	private BinSearchTreeNode search(BinSearchTreeNode node, String searchWord) {

		// case1: binary search tree is empty, make node the root 
		if (node==null) return node; 

		//case2: binary search tree is not empty, find correct place by comparison and add node
		else if (node.getWord().equals(searchWord)) //the current node is the searchword
			return node; 
		else
			if(searchWord.compareTo(node.getWord())<0) {//searchWord be in left subtree (bc left is smaller in binary search tree) 
				node= node.getLeft();// go to left child

			} else //search word be in right subtree (bc right is larger in binary search tree) 
				node= node.getRight();// go to right child

		node= search(node,searchWord);// recursion to return root with correct word;
		return node; // returns the correct room
	}


	/**
	 * This method helps build a binary search word by inserting a node containing the word into the tree if it doesn't exist, or updating info if it exists.
	 * @param theWord to be added
	 * @param theFileName what book its from
	 * @param thePosition position in the book
	 */
	public void insertWord(String theWord,String theFileName,int thePosition) {
		BinSearchTreeNode nodeWithWord= new BinSearchTreeNode(theWord,theFileName,thePosition);
		BinSearchTreeNode wordInTree=getWord(theWord);// gets position of word in binary tree that stores all locations of that word in the text files
		if(wordInTree==null)
			insert(root,theWord,theFileName,thePosition);
		else
			wordInTree.getFiles().insertWord(theFileName,thePosition); //if node exists update it

	}

	/**
	 * SELF ADDED METHOD
	 * This method finds where the word in the added node should be inserted and inserts it.
	 * @param theWord to be added
	 * @param theFileName what book its from
	 * @param thePosition position in the book
	 */
	public void insert(BinSearchTreeNode node,String theWord,String theFileName,int thePosition) {

		BinSearchTreeNode nodeWithWord= new BinSearchTreeNode(theWord,theFileName,thePosition);

		//This only goes in the loop if there is no parent, for other cases, the parent exists, but the child doesn't.
		if (node==null) {
			root= nodeWithWord;
		}

		else if(theWord.compareTo(node.getWord())<0) {//searchWord be in left subtree (bc left is smaller in binary search tree) 
			if(node.getLeft()==null)// if left child is empty, add
				node.setLeft(nodeWithWord);

			else //search word be in right subtree (bc right is larger in binary search tree) 
				insert (node.getLeft(),theWord,theFileName,thePosition);
		}
		else { 
			if(node.getRight()==null)
				node.setRight(nodeWithWord);
			else //search word be in right subtree (bc right is larger in binary search tree) 
				insert (node.getRight(),theWord,theFileName,thePosition);
		}

	}
}
