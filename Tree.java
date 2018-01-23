package treeCountingProgram;

/**
 * This class represents a tree in NYC and it contains information about the tree such as its
 * ID number, species, and health
 * 
 * @author Matthew Herman
 * @version 13/02/2017
 */
public class Tree implements Comparable<Tree> {

	//DATA FIELDS
	private int treeId; //must be non-negative
	private int treeDbh; //must be non-negative
	
	private String status; // must be "Alive", "Dead", "Stump", "", or null
	private String health; // must be "Good", "Fair", "Poor", "", or null
	
	private String spcCommon; // must be "" or "any possible String"
	
	private int zipcode; //must represent a 5 digit integer, eg. 27 should be 00027
	
	private String boroname; // must be "Manhattan", "Bronx", "Brooklyn", "Queens", or "Staten Island"
	
	private double xSp;
	private double ySp;
	
	//CONSTRUCTOR
	/**
	 * Constructor for Tree object
	 * @param treeId A tree object's unique ID number
	 * @param treeDbh A tree object's diameter measured at breast height
	 * @param status A tree object's status
	 * @param health A tree object's health
	 * @param spcCommon A tree object's common species name
	 * @param zipcode A tree object's zipcode
	 * @param boroname A tree object's borough
	 * @param xSp A tree objects's x-coordinate
	 * @param ySp A tree object's y-coordinate
	 * @throws IllegalArgumentException if an invalid argument
	 * is passed to a setter for one of the Tree object's nine fields.
	 */
	public Tree( 
			int treeId, 
			int treeDbh, 
			String status, 
			String health, 
			String spcCommon, 
			int zipcode, 
			String boroname, 
			double xSp, 
			double ySp 
				) throws IllegalArgumentException{
		
		this.setTreeId(treeId);
		this.setTreeDbh(treeDbh);
		this.setStatus(status);
		this.setHealth(health);
		this.setSpcCommon(spcCommon);
		this.setZipcode(zipcode);
		this.setBoroname(boroname);
		this.setXSp(xSp);
		this.setYSp(ySp);

	}
	
	//SETTERS
	/**
	 * Setter for a Tree object's tree_id field. 
	 * 
	 * @param treeId Tree object's unique ID number. It must be a non-negative
	 * integer.
	 * @throws IllegalArgumentException if anything other
	 * than a non-negative integer argument is passed to this method
	 */
	private void setTreeId(int treeId) throws IllegalArgumentException{
		if (treeId >= 0) {
			this.treeId = treeId;
		}
		else {
			throw new IllegalArgumentException("Invalid treeID: treeId must be a non-negative integer");
		}
	}
	
	/**
	 * Setter for a Tree object's treeDbh field.
	 * 
	 * @param treeDbh Tree object's diameter measured at breast height.
	 * It must be a non-negative integer.
	 * @throws IllegalArgumentException if anything other than a non-negative integer
	 * argument is passed to this method.
	 */
	private void setTreeDbh(int treeDbh) throws IllegalArgumentException {
		if (treeDbh >= 0) {
			this.treeDbh = treeDbh;
		}
		else {
			throw new IllegalArgumentException("Invalid treeDbh: treeDbh must be a non-negative integer");
		}
	}
	
	/**
	 * Setter for a Tree object's status field.
	 * 
	 * @param status Tree object's status. Indicates whether the tree is
	 * alive, dead, or a stump Must be null or a string "alive", "dead", "stump", or "".
	 * @throws IllegalArgumentException if anything other than a null or string argument
	 * "alive", "dead", "stump", or "" are passed to this method.
	 */
	private void setStatus(String status) throws IllegalArgumentException {
		String statusNoCase = status.toLowerCase();
		if (statusNoCase.equals("") || statusNoCase.equals("alive") || statusNoCase.equals("dead") || statusNoCase.equals("stump") || statusNoCase == null) {
			this.status = status;
		}
		else {
			throw new IllegalArgumentException("Invalid status: status must be case-insensitively \"alive\", \"dead\", \"stump\", \"\", or null");
		}
	}
	
	/**
	 * Setter for a Tree object's health field.
	 * 
	 * @param health Tree object's health. Indicates the tree's health.
	 * Must be null or a string "good", "fair", "poor", or "".
	 * @throws IllegalArgumentException if anything other than a null or string argument
	 * "good", fair", "poor", or "" are passed to this method.
	 */
	private void setHealth(String health) throws IllegalArgumentException {
		String healthNoCase = health.toLowerCase();
		if (healthNoCase.equals("") || healthNoCase.equals("good") || healthNoCase.equals("fair") || healthNoCase.equals("poor") || healthNoCase == null) {
			this.health = health;
		}
		else {
			throw new IllegalArgumentException("Invalid health: health must be case-insensitively \"good\", \"fair\", \"poor\", \"\", or null");
		}	
	}
	
	/**
	 * Setter for a Tree object's spcCommon (common species name) field. 
	 * 
	 * @param spcCommon Tree object's common species name. Must be any string.
	 * @throws IllegalArgumentException if a non-String argument is passed to this method.
	 */
	private void setSpcCommon(String spcCommon) throws IllegalArgumentException {
		if (spcCommon != null) {
			this.spcCommon = spcCommon;
		}
		else {
			throw new IllegalArgumentException("Invalid spcCommon: spcCommon must be a String or empty String");
		}
	}
	
	/**
	 * Setter for a Tree object's zipcode field.
	 * 
	 * @param zipcode Tree object's zipcode. Must be an integer
	 * inclusively between 0 and 99999.
	 * @throws IllegalArgumentException if anything other than an integer argument
	 * inclusively between 0 and 99999 is passed to this method.
	 */
	private void setZipcode(int zipcode) throws IllegalArgumentException {
		if (zipcode >= 0 && zipcode <= 99999) {
			this.zipcode = zipcode;
		}
		else {
			throw new IllegalArgumentException("Invalid zipcode: zipcode must be a non-negative integer with 5 digits");
		}
	}
	
	/**
	 * Setter for a Tree object's boroname (borough) field.
	 * 
	 * @param boroname Tree object's borough. Must be "manhattan",
	 * "bronx", "brooklyn", "queens", or "staten island".
	 * @throws IllegalArgumentException if anything other than a string argument
	 * "manhattan", "bronx", "brooklyn", "queens" or "staten island" is passed to this method.
	 */
	private void setBoroname(String boroname) throws IllegalArgumentException {
		String boronameNoCase = boroname.toLowerCase();
		if (boronameNoCase.equals("manhattan") | boronameNoCase.equals("bronx") | boronameNoCase.equals("brooklyn") | boronameNoCase.equals("queens") | boronameNoCase.equals("staten island")) {
			this.boroname = boroname;
		}
		else {
			throw new IllegalArgumentException("Invalid boroname: boroname must be case-insensitively \"manhattan\", \"bronx\", \"brooklyn\", \"queens\", \"staten island\" ");
		}
	}
	
	/**
	 * Setter for a Tree object's x_sp (x-coordinate) field.
	 * 
	 * @param xSp Tree object's x-coordinate based upon 
	 * an OpenNYC coordinate system. More info can be found at
	 * http://www.ngs.noaa.gov/TOOLS/spc.shtml.
	 * Must be a double.
	 */
	private void setXSp(double xSp) {
		this.xSp = xSp;
	}
	
	/**
	 * Setter for a Tree object's y_sp (y-coordinate) field.
	 * 
	 * @param xSp Tree object's y-coordinate based upon 
	 * an OpenNYC coordinate system. More info can be found at
	 * http://www.ngs.noaa.gov/TOOLS/spc.shtml.
	 * Must be a double.
	 */
	private void setYSp(double ySp) {
		this.ySp = ySp;
	}
	
	//GETTERS
	/**
	 * Getter for a Tree object's tree_id field.
	 * 
	 * @return an integer representing a tree object's ID number.
	 */
	public int getTreeId() {
		return this.treeId;
	}

	/**
	 * Getter for a Tree object's tree_dbh field.
	 * 
	 * @return an integer representing a tree object's diameter
	 * measured at breast height.
	 */
	public int getTreeDbh() {
		return this.treeDbh;
	}
	
	/**
	 * Getter for a Tree object's status field.
	 * 
	 * @return a String representing a Tree object's status.
	 * It must be "alive", "dead", "stump", "", or null.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Getter for a Tree object's health field.
	 * 
	 * @return a String representing a Tree object's health.
	 * It must be "good", "fair", "poor", "", or null.
	 */
	public String getHealth() {
		return this.health;
	}

	/**
	 * Getter for a Tree object's spcCommon field.
	 * 
	 * @return a String representing a Tree object's common
	 * species name.
	 */
	public String getSpcCommon() {
		return this.spcCommon;
	}

	/**
	 * Getter for a Tree object's zipcode field.
	 * 
	 * @return an integer representing a Tree object's zipcode.
	 * The integer if less than 10000 must be formatted if converted to a string
	 * so that enough 0s lead the integer in order for the zipcode to have 5 digits.
	 */
	public int getZipcode() {
		return this.zipcode;
	}

	/**
	 * Getter for a Tree object's boroname field.
	 * 
	 * @return a String representing a Tree object's borough. It
	 * must be a string "manhattan", "bronx", "brooklyn", "queens", or "staten island".
	 */
	public String getBoroname() {
		return this.boroname;
	}

	/**
	 * Getter for a Tree object's x-coordinate.
	 * 
	 * @return a double representing a Tree object's x-coordinate according
	 * to the OpenNYC coordinate system. More info at  http://www.ngs.noaa.gov/TOOLS/spc.shtml.
	 */
	public double getXSp() {
		return this.xSp;
	}
	
	/**
	 * Getter for a Tree object's y-coordinate.
	 * 
	 * @return a double representing a Tree object's y-coordinate according
	 * to the OpenNYC coordinate system. More info at  http://www.ngs.noaa.gov/TOOLS/spc.shtml.
	 */
	public double getYSp() {
		return this.ySp;
	}
 
	
	/**
	 * This method compares a tree object to any other object to check if they are equal.
	 * 
	 * @param anotherObj This can be any Java Object.
	 * @return true if the object passed as an argument is a Tree object with the same
	 * spcCommon and treeID as the object calling this method. Otherwise returns false.
	 */
	@Override
	public boolean equals(Object anotherObj) throws IllegalArgumentException {
		if (this.getClass() == anotherObj.getClass()) {
			return this.equals(anotherObj);
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method compares a tree object to another tree object to check if they are equal.
	 * Two trees are equal if they have the same ID and species name.
	 * 
	 * @param anotherTree This can be any tree object.
	 * @return true if the object passed as an argument is a tree object with the same
	 * spcCommon and treeId as the object calling this method. Otherwise returns false.
	 * @throws IllegalArgumentException if two trees have the same ID and different species names.
	 */
	public boolean equals(Tree anotherTree) throws IllegalArgumentException {
		if ( (this.getTreeId() == anotherTree.getTreeId()) && (this.getSpcCommon().toLowerCase().equals(anotherTree.getSpcCommon().toLowerCase())) ) {
			return true;
		}
		else if ( (this.getTreeId() == anotherTree.getTreeId()) && !(this.getSpcCommon().toLowerCase().equals(anotherTree.getSpcCommon().toLowerCase())) ) {
			throw new IllegalArgumentException("Invalid Argument Found: Two tree instances can not have the same values of treeId and different values of spcCommon ");
		}
		else {
			return false;
		}
	}
	
	/**
	 * This compares a tree object to another tree object and returns an integer -1, 0, or 1
	 * that indicates whether the tree object calling this method is respectively less than,
	 * equal to, or greater than the tree object passed as an argument to this method. The
	 * total ordering here is based upon a primary key, species name, and a secondary key, treeID.
	 * 
	 * @param anotherTree This can be any non-null tree object.
	 * @return an integer -1, 0, or 1. -1 means the tree object calling this method is less than the 
	 * tree object passed as an argument to this method. 0 means the tree object calling this method is
	 * equal to the tree object passed as an argument to this method. 1 means the tree object calling
	 * this method is greater than the tree object passed as an argument to this method.
	 * @throws NullPointerException if the tree object passed as an argument is null.
	 * @throws IllegalArgumentException
	 */
	public int compareTo(Tree anotherTree) throws NullPointerException {
		if (this.equals(anotherTree)) {
			return 0;
		}
		else if (anotherTree == null) {
			throw new NullPointerException("compareTo(arg) throws a NullPointerException when arg = null");
		}
		else if (this.getSpcCommon().toLowerCase().compareTo(anotherTree.getSpcCommon().toLowerCase()) > 0) {
			return 1;
		}
		else if (this.getSpcCommon().toLowerCase().compareTo(anotherTree.getSpcCommon().toLowerCase()) < 0) {
			return -1;
		}
		else if (this.getTreeId() > anotherTree.getTreeId()) {
			return 1;
		}
		else if (this.getTreeId() < anotherTree.getTreeId()) {
			return -1;
		}
		else {
			return -1;
		}
		
	}
	
	/**
	 * This returns a string which displays the values of the Tree object's fields in a list
	 * surrounded by square brackets.
	 * 
	 * @return a string which displays the values of the Tree object's 
	 * fields in a list surrounded by square brackets.
	 */
	@Override
	public String toString() {
		String outputString = "";
		outputString =
				"[ " +
				this.getTreeId() + ", " +
				this.getTreeDbh() + ", " +
				this.getStatus() + ", " +
				this.getHealth() + ", " +
				this.getSpcCommon() + ", " +
				String.format("%05d, ", this.getZipcode()) +
				this.getBoroname() + ", " +
				this.getXSp() + ", " +
				this.getYSp() + " ]";
		return outputString;
	}

	
}
