package noFilter;

import java.util.Vector;

public class Course {
	
	private String cNum, profName, cName;
	private Vector<String> preNums;

	// Get
	public String getcNum() {return this.cNum;}
	public String getProfName() {return this.profName;}
	public String getcName() {return this.cName;}
	public Vector<String> getPreNums() {return this.preNums;}
	// Set
	public void setcNum(String cNum) {this.cNum = cNum;}
	public void setProfName(String profName) {this.profName = profName;}
	public void setcName(String cName) {this.cName = cName;}
	public void setPreNums(Vector<String> preNums) {this.preNums = preNums;}

	// Constructor
	public Course() {
		this.preNums = new Vector<String>();
	}

	// Methods
	public void addData(String cNum, String profName, String cName, Vector<String> preNums) {
		this.cNum = cNum;
		this.profName = profName;
		this.cName = cName;
		this.preNums = preNums;
	}
	
	public String toString() {
		String returnval = "";
		returnval=getcNum()+' '+getProfName()+' '+getcName();
		for(String s:getPreNums()) {
			returnval+=' '+s;
		}
		return returnval+'\n';
		
	}
}
