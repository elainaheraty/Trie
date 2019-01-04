package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {

	// prevent instantiation
	private Trie() { }

	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		
		//create root node
		Indexes idx = null;
		TrieNode rootTn = new TrieNode(idx, null, null);	
		
		if(allWords==null) {
			return rootTn;
		}
		
		if(allWords.length>0) {
			//create child node
			idx = new Indexes(0, (short)0, (short)(allWords[0].length()-1));
			TrieNode tn = new TrieNode(idx, null, null);
			rootTn.firstChild=tn;
			TrieNode prvTn=null;
			
			int i=0;
			for(i=1; i<allWords.length;i++) { 
				//create child or sibling trieNode
				prvTn=tn;
				idx = new Indexes(i, (short)0, (short)(allWords[i].length()-1));
				tn = new TrieNode(idx, null, null);
				prvTn.sibling=tn;
				
				int j=0;
				int ind=0;
				for(j=i-1; j>=0; j++) {
					ind = matchChars(allWords[i], allWords[j]);
					if(ind>0) {
						//create common node
						//create children
						break;
					}
				}
				if(j==0&&ind==0) {
					//make it a sibling
					TrieNode siblingTn = rootTn.firstChild;
					while(siblingTn.sibling!=null) {
						siblingTn=siblingTn.sibling;
					}
					siblingTn.sibling = tn; 
				}
			}
					
		}
		return rootTn;
	
	}
	

	private static int matchChars (String str1, String str2) {
		int length = str1.length()>str2.length()?str2.length():str1.length();
		int i=0;
		for(i=0; i<length; i++) {
			if(str1.charAt(i)!=str2.charAt(i)) {
				break;
			}
		}
		return i;
	}

	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
			String[] allWords, String prefix) {
		/** COMPLETE THIS METHOD **/

		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		return null;
	}

	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}

	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}

		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
					.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}

		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}

		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
}
