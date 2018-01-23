package treeCountingProgram;
import java.util.ArrayList;

/**
 * This class represents an ArrayList of Tree objects and it contains methods that help
 * in analyzing the data contained within the tree objects of this ArrayList.
 * 
 * @author Matthew Herman
 * @version 13/02/2017
 */
public class TreeList extends ArrayList<Tree>{
	
	/**
	 * Default constructor for a TreeList object. Creates an
	 * ArrayList<Tree> of size 10.
	 */
	public TreeList() {
		super();
	}
	
	/**
	 * Constructor for a TreeList object that allows size of the new
	 * ArrayList<Tree> object to be specified.
	 * 
	 * @param size intended size of the ArraList<Tree> object to be created.
	 */
	public TreeList(int size) {
		super(size);
	}
	
	/**
	 * This method returns the total number of Tree objects contained in
	 * this ArrayList.
	 * 
	 * @return an integer represent the total number of Tree objects contained
	 * in this ArrayList.
	 */
	public int getTotalNumberOfTrees() {
		int numberOfTrees = 0;
		
		for (int i=0; i<this.size(); i++) {
			numberOfTrees = numberOfTrees + 1;
		}
		
		return numberOfTrees;
	}
	
	/**
	 * This method expects a String argument representing a common species name of a Tree object. 
	 * It iterates through the Trees contained within this list and returns the number of Trees that
	 * have a substring that equals the user inputed string.
	 * 
	 * @param speciesName A string argument representing a common species name of a Tree object.
	 * @return an integer representing the total number of Tree objects contained in this ArrayList
	 * that contain a substring equal to the user inputed string.
	 */
	public int getCountByTreeSpecies(String speciesName) {
		int numberOfTrees = 0;
		
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).getSpcCommon().toLowerCase().equals(speciesName.toLowerCase().trim())) {
				numberOfTrees = numberOfTrees + 1;
			}
		}
		
		return numberOfTrees;
	}
	
	/**
	 * This method expects a String argument representing a borough of NYC and returns and integer 
	 * representing the total number of trees within that borough.
	 * 
	 * @param boroughName A string argument representing a borough of NYC. It should be
	 * "manhattan", "bronx", "brooklyn", "quuens", or "staten island", otherwise it will return 0.
	 * @return an integer representing the total number of Tree objects with the specified borough.
	 */
	public int getCountByBorough (String boroughName) {
		int numberOfTrees = 0;
		
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).getBoroname().toLowerCase().equals(boroughName.toLowerCase().trim())) {
				numberOfTrees = numberOfTrees + 1;
			}
		}
		
		return numberOfTrees;
	}
	
	/**
	 * This method expects two String arguments. The first represents a common species name for a Tree
	 * object. The second represents a borough of NYC. This method returns the number of trees within the
	 * specified borough that have a substring that equals the user inputed string.
	 * 
	 * @param species Name A string representing a common species name of a Tree object.
	 * @param borough Name A string representing a borough of NYC.
	 * @return an integer representing the total number of Tree objects with the specified borough and
	 * that contain a substring equal to the user inputed string.
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroughName) {
		int numberOfTrees = 0;
		
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).getSpcCommon().toLowerCase().equals(speciesName.toLowerCase().trim()) && this.get(i).getBoroname().toLowerCase().equals(boroughName.toLowerCase().trim())) {
				numberOfTrees = numberOfTrees + 1;
			}
		}
		
		return numberOfTrees;
	}
	
	/**
	 * This method expects a String argument representing a common species name of a Tree object.
	 * It then returns an ArrayList<String> which contains all the species in this ArrayList that
	 * contain the string argument as a substring.
	 * 
	 * @param speciesName A string representing a common species name of a Tree object.
	 * @return an ArrayList<String> that contains all the species in this ArrayList that
	 * contain the string argument as a substring.
	 */
	public ArrayList<String> getMatchingSpecies(String speciesName) {
		ArrayList<String> outputList = new ArrayList<String>();
		
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).getSpcCommon().toLowerCase().contains(speciesName.toLowerCase().trim())) {
				
				boolean isNotRepeat = true;
				
				for (int j=0; j<outputList.size(); j++) {
					
					if (this.get(i).getSpcCommon().toLowerCase().equals(outputList.get(j).toLowerCase().trim())) {
						isNotRepeat = false;
					}
					
				}
				
				if (isNotRepeat) {
					outputList.add(this.get(i).getSpcCommon().toLowerCase());
				}
				
			}
		}
		
		return outputList;
	}
	
	/**
	 * This returns a string which displays the contents of this ArrayList in a list
	 * surrounded by square brackets.
	 * 
	 * @return a string which displays the contents of this ArrayList in a list
	 * surrounded by square brackets.
	 */
	@Override
	public String toString() {
		String outputString = "";
		outputString = outputString + "[\n ";
		
		for (int i=0; i<this.size()-1; i++) {
			outputString = outputString + this.get(i) + ", \n ";
		}
		
		if (this.size() > 0) {
			outputString = outputString + this.get(this.size()-1);
		}
		
		outputString = outputString +" \n]";
		return outputString;
	}
	
}
