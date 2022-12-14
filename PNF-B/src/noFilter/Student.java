package noFilter;

import java.util.Vector;

public class Student {
	
	// Attribute
	private String sNum, fName, lName, major;
	private Vector<String> cNums;
	private boolean isSatisfied = true;
	
	// Get
	public String getsNum() {return this.sNum;}
	public String getFName() {return this.fName;}
	public String getLName() {return this.lName;}
	public String getMajor() {return major;}
	public Vector<String> getCNums() {return this.cNums;}
	public boolean isSatisfied() {return isSatisfied;}
	
	// Set
	public void setsNum(String sNum) {this.sNum = sNum;}
	public void setFName(String fName) {this.fName = fName;}
	public void setLName(String lName) {this.lName = lName;}
	public void setMajor(String major) {this.major = major;}
	public void setCNums(Vector<String> cNums) {this.cNums = cNums;}
	public void setSatisfied(boolean isSatisfied) {this.isSatisfied = isSatisfied;}

	// Constructor
	public Student() {
		this.cNums = new Vector<String>();
	}

	// Methods
	public void addData(String sNum, String fName, String lName, String major, Vector<String> cNums) {
		this.sNum = sNum;
		this.fName = fName;
		this.lName = lName;
		this.major = major;
		this.cNums = cNums;
	}
	public String toString() {
		String returnval = "";
		returnval=getsNum()+' '+getFName()+' '+getLName()+' '+getMajor();
		for(String s:getCNums()) {
			returnval+=' '+s;
		}
		return returnval+'\n';
		
	}
}
