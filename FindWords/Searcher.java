import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains methods that finds the words.
 * @author Dongzheng (Elizabeth) Xu
 */
public class Searcher {

	/**
	 * Reference to table of binary search trees that implements the lexicon data structure.
	 */
	private HashTable table; 

	/**
	 * Name of input file storing all words that will be searching for in lexicon data structure 
	 */
	private String inputFile;

	public Searcher(HashTable wordHashTable, String theTestFile) {//opens test file and checks binary search tree
		inputFile= theTestFile;
		table = wordHashTable; //allow access to hashtable with binary search tree
	}
	//INSERT WORD IGF THERES NO NEW NODE HAVE TO INSERT WORKD+

	/**
	 * This method reads the input file and for each word w in it 
	 * invokes below method findWord to look for the word w in the lexicon 
	 * and print information about where the word appears in the
	 * collection of text files that compose the lexicon.
	 */
	public void findAllWords() {
		try {// need 2 methods
			//findAll words only starts the recursion,
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		
			
			String line;
			while ((line = br.readLine()) != null) {//while not end of line

				// The following just splits on white space
				String[] words = line.split("[\\s]+");// split sentence of words to singular words we want to look for
				for (String word : words) { // for each word search for word in text
					findWord(word);
				}
			}
		} catch (IOException e) {
			System.out.println("Error opening file "+inputFile);
		}


	}


	/**
	 * This method looks for searchWord in the lexicon.
	 */
	public void findWord(String searchWord) {
		int index=table.computeIndex(searchWord); //compute index of search word

		//use get table (table is a instance variable in hashtable)
		if(table.getTable()[index].getWord(searchWord)==null){//word not found in table
			CustomPrinter.wordNotFound(searchWord, inputFile) ;//print word not found
		}
		else {//word is found in table
			LinkedList list = table.getTable()[index].getWord(searchWord).getFiles();// get the files where the word appears (this is linked list stored in node)
			FileNode file = list.getHead(); // start at beginning of list
			CustomPrinter.wordFound(searchWord, inputFile);
			while(file!=null) {//while not at end of files
				ArrayList<Integer> lq = file.getPositions();// get list of positions
				//file.addPosition();
				CustomPrinter.printPositionsPerFileFound(file.getFilename(), lq, inputFile);
				file = file.getNext();
			}
			
		}
	}	

}
