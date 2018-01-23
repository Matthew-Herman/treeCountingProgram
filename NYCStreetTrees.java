package treeCountingProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the main method for the NYCStreeTrees program,
 * which takes OpenNYC data on trees in NYC's boroughs and outputs
 * information on a tree species popularity in the boroughs.
 * 
 * @author Matthew Herman
 * @version 13/02/2017
 */
public class NYCStreetTrees {
	
	
	/**
	 * Main method for this NYC Street Trees program
	 * 
	 * @param args String array of command line arguments
	 */
	public static void main(String[] args) {
		String fileName = null;
		File inputFile = null;
		Scanner fileInput = null;
		try {
			if (args.length != 1) {
				throw new IllegalArgumentException("NYCStreetTrees must take a file name as a sole argument.");
			}
			fileName = args[0];
		}
		catch (NullPointerException e) {
			System.err.println("Usage Error: the file expects one file name as an argument. ");
			System.exit(1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Usage Error: the file expects one file name as an argument. ");
			System.exit(1);
		}
		catch (IllegalArgumentException e) {
			System.err.println("Usage Error: the file expects one file name as an argument. ");
			System.exit(1);
		}
		catch (Exception e) {
			System.err.println("Exception caught: " + e.getMessage());
			System.exit(1);
		}
		
		/*
		 * This try catch block ensures the command line argument is a valid file path
		 * If the file path is not valid, an exception is thrown and the program is terminated
		 */
		try {
			inputFile = new File(fileName);
			fileInput = new Scanner(inputFile);
		} 
		catch (NullPointerException e) {
			System.err.println("Usage Error: the file expects one file name as an argument. ");
			System.exit(1);
		}
		catch (FileNotFoundException e) {
			System.err.println("Error: file not found. ");
			System.exit(1);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}

		
		TreeList listOfTrees = new TreeList();
		
		/*
		 * This while loop goes through each line in the input csv file and creates a tree object
		 * which is then added to the listOfTrees, if certain conditions are met.
		 */
		while (fileInput.hasNextLine()) {
			String line = fileInput.nextLine();
			ArrayList<String> inputList = splitCSVLine(line);
			
			/*
			 * This try catch block ensures the line from the input csv file is valid by 
			 * ensuring the number of columns is 41 and the data within the line is valid
			 * according to the conditions for the variables of the Tree class. If a line
			 * of data is valid, a tree object is created with it and added to the listOfTrees,
			 * otherwise it is skipped and no tree containing its data is added to the listOfTrees.
			 */
			try {
				if (inputList.size() != 41) {
					throw new IllegalArgumentException("Invalid ArrayList size. ArrayList.size() must return 41");
				}
				boolean isDuplicate = false;
				int treeId = Integer.parseInt(inputList.get(0)); 
				int treeDbh = Integer.parseInt(inputList.get(3)); 
				String status = inputList.get(6);
				String health = inputList.get(7);
				String spcCommon = inputList.get(9);
				int zipcode = Integer.parseInt(inputList.get(25)); 
				String boroname = inputList.get(29);
				double xSp = Double.parseDouble(inputList.get(39));
				double ySp = Double.parseDouble(inputList.get(40));
				Tree newTree = new Tree(treeId, treeDbh, status, health, spcCommon, zipcode, boroname, xSp, ySp);
				
				//checks listOfTrees if a duplicate Tree object is already there, if it is duplicate tree not added to listOfTrees
				for (int i=0; i<listOfTrees.size(); i++) {
					if (listOfTrees.get(i).equals(newTree)) {
						isDuplicate = true;
					}
				}
				if (isDuplicate) {
					continue;
				}
				else {
					listOfTrees.add(newTree);
				}
			}
			catch (NumberFormatException e) {
				//System.err.println("Data line invalid: attempted to convert a string with inappropriate format to an integer:\n" + e.getMessage() +  ": line not added to data");
				continue;
			}
			catch (IllegalArgumentException e) {
				//System.err.println("Data line invalid: an illegal argument was passed to a method:\n" + e.getMessage() + " line skipped and not added to data");
				continue;
			}
			catch (IndexOutOfBoundsException e) {
				//System.err.println("Data line Invalid: Number of Columns must equal 41:\n" + e.getMessage() + ": line not added to data");
				continue;
			}
			
		}
		
		Scanner userInput = new Scanner(System.in);
		boolean isKeepGoing = true;
		String[] bouroughList = {
				"Manhattan",
				"Bronx",
				"Brooklyn",
				"Queens",
				"Staten Island"
		};
		
		/*
		 * This is the main while loop for user input and it checks the popularity of
		 * tree species based upon user inputs and then outputs information. The while
		 * loop terminates when the user enters "quit".
		 */
		while (isKeepGoing) {
			System.out.println("\nPlease enter a species name in order to check its popularity, or enter quit in order to terminate the program: ");
			String userResponse = userInput.nextLine().trim();
			
			//This ends the while loop if the user enters "quit"
			if (userResponse.toLowerCase().trim().equals("quit")) {
				isKeepGoing = false;
			}
			
			/*
			 * This ends the current iteration and begins a new one if the user enters
			 * and empty string or only whitespace
			 */
			else if (userResponse.equals("".trim())) {
				continue;
			}
			
			/*
			 * If a user enters a string, then this else block checks if there are trees in
			 * the listOfTrees which have a spcCommon field which contains as a substring 
			 * the user inputed string. If such trees are found, then information regarding
			 * the popularity of that species in NYC and the boroughs is output.
			 */
			else {
				
				ArrayList<String> matchingSpecies = listOfTrees.getMatchingSpecies(userResponse);
				
				if (matchingSpecies.size() == 0) {
					System.out.println("There are no records of " + userResponse + " on NYC streets.");
					continue;
				}
				
				//This prints a list of the species found in the listOfTrees that match the user input
				System.out.println("All matching species: ");
				for (int i=0; i<matchingSpecies.size(); i++) {
					System.out.println("  " + matchingSpecies.get(i));
				}
				
				//prints popularity information for NYC
				System.out.println("\nPopularity in New York City: ");
				int totalNumberMatchingSpeciesInNYC = 0;
				int totalNumberTreesInNYC = 0;
				double totalPercentage = 0.0;
				
				//sums matching trees for each borough to get the total in NYC
				for (int i=0; i<bouroughList.length; i++) {
					for (int j=0; j<matchingSpecies.size(); j++) {
						totalNumberMatchingSpeciesInNYC += listOfTrees.getCountByTreeSpeciesBorough(matchingSpecies.get(j), bouroughList[i]);
					}
					
					totalNumberTreesInNYC += listOfTrees.getCountByBorough(bouroughList[i]);
				}
				
				/*
				 * If no matching species are found begins a new iteration of main while loop,
				 * otherwise this calculates the total percentage of matching trees to total trees in NYC.
				 */
				if (totalNumberMatchingSpeciesInNYC == 0) {
					continue;
				}
				else {
					totalPercentage = ((double) totalNumberMatchingSpeciesInNYC / (double) totalNumberTreesInNYC * 100);
					totalPercentage = Math.round(totalPercentage * 100);
					totalPercentage = totalPercentage / 100;
				}
				
				//This prints out the popularity information formatted according to specifications
				//This line specifically formats totalNumberTreesInNYC into a string surrounded by parentheses
				String totalTrees = String.format("(%,d)", totalNumberTreesInNYC);
				System.out.printf("  %-15s%,10d%-10s  %6.2f%s\n", "NYC:", totalNumberMatchingSpeciesInNYC, totalTrees,totalPercentage, "%");
				
				//This prints popularity information for each borough in boroughList
				for (int i=0; i<bouroughList.length; i++) {
					
					//calculate total number of trees in the borough that matches all matching species
					int numberMatchingSpeciesInBourough = 0;
					for (int j=0; j<matchingSpecies.size(); j++) {
						numberMatchingSpeciesInBourough += listOfTrees.getCountByTreeSpeciesBorough(matchingSpecies.get(j), bouroughList[i]);
					}
					
					//calculate percentage of matching species in the borough rounded to 2 decimals
					double percentage = 0.0;
					if (listOfTrees.getCountByBorough(bouroughList[i]) == 0) {
						percentage = 0.0;
					}
					else {
						percentage = ((double) numberMatchingSpeciesInBourough / (double) listOfTrees.getCountByBorough(bouroughList[i]) * 100);
						percentage = Math.round(percentage * 100);
						percentage = percentage / 100;
					}
					//This prints out the popularity information formatted according to specifications
					//This line specifically formats countByBourough into a string surrounded by parentheses
					String countByBorough = String.format("(%,d)", listOfTrees.getCountByBorough(bouroughList[i]));
					System.out.printf("  %-15s%,10d%-10s  %6.2f%s\n", bouroughList[i] + ":", numberMatchingSpeciesInBourough,  countByBorough, percentage, "%"); 
				}
				
			}
			
		}//primary while loop for input and output
		
		//System.out.println(listOfTrees.get(0).compareTo(listOfTrees.get(1)));
		
		fileInput.close();
		userInput.close();
		System.out.println("NYCStreetTrees Program Terminated.");
		System.exit(1);
		
	} //main
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries that may contain commas ).
	 * 
	 * @param text Line line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 * found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				}
				else {
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
					 //add it to the current entry
					nextWord.append( nextChar );
				}
				else { //skip all spaces between entries
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry
					nextWord.append(nextChar);
				else { //end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				 //add all other characters to the next Word
				nextWord.append(nextChar);
				insideEntry = true;
			}
			
		}
		
		//add the last word (assuming not empty)
		//trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}
	
}
