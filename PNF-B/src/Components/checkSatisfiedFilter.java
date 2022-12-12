package Components;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.Vector;

import Framework.CommonFilterImpl;
import noFilter.Course;
import noFilter.Student;

public class checkSatisfiedFilter extends CommonFilterImpl {

    private final Vector<Course> cVect;
    private final Vector<Student> sVect;

    public checkSatisfiedFilter(){
        this.cVect = new Vector<Course>();
        this.sVect = new Vector<Student>();
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
    	// make Vector
        makecVect(in);	
        makesVect(in2);
        
        // filtering
        filtering();
        
        // outStream setting
        String satisfiedstr = "";
        String nonSatisfiedstr = "";
        
        for(Student s: this.sVect) {
        	if(s.isSatisfied()) satisfiedstr+=s.toString();
        	else nonSatisfiedstr+=s.toString();
        }
        for(char c:satisfiedstr.toCharArray()) out.write(c);
        for(char c:nonSatisfiedstr.toCharArray()) out2.write(c);
        return false;
    }

    private void filtering() {
    	for(Student s:this.sVect) {
    		Vector<String> precs = new Vector<String>();
    		for(Course c:this.cVect) {
    			if(s.getCNums().contains(c.getcNum())){
    				for (String num:c.getPreNums()) precs.add(num);
    			}
    		}
    		for(String ps:precs) {
    			if (!s.getCNums().contains(ps)) {
    				s.setSatisfied(false);
    			}
    		}
    	}
	}

	public void makecVect(PipedInputStream cin) throws IOException {
        int databyte = '\0';
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];

        int cNumIdx = 0;
        int profNameIdx = 0;
        int cNameIdx = 0;
        Vector<Integer> preCourseIdx = new Vector<>();

        boolean minusOneTime=false;
        
        // all line loop
        while (true){
        	// one line loop
	        while(databyte != '\n' && databyte != -1) {
	        	// common filter
                databyte = cin.read();
				if (databyte == -1) {//managing last line 
					if(minusOneTime) return;
					minusOneTime= true;
				}
                if (databyte == ' ') numOfBlank++;
                if (databyte != -1) buffer[idx++] = (byte) databyte;

                // special filtering
                if (numOfBlank == 1 && databyte == ' ') cNumIdx = idx;
                if (numOfBlank == 2 && databyte == ' ') profNameIdx = idx;
                if (numOfBlank == 3 && databyte == ' ') cNameIdx = idx;
                if (numOfBlank >= 4 && databyte == ' ') preCourseIdx.add(idx);

            }
            // managing when \n & -1 , it doesn't contain last index problem 
	        if(cNumIdx==6) { 
	        	if (cNameIdx==0) cNameIdx = idx;
	        	else preCourseIdx.add(idx);
	        }

	        
	        // make String || Vector<String>
	        StringBuilder cnum = new StringBuilder();
            StringBuilder profName= new StringBuilder();
            StringBuilder cName = new StringBuilder();
            Vector<String> preCourseVect = new Vector<>();

            // append attribute
            for(int i = 0; i<cNumIdx;i++) if (buffer[i]!='\n' && buffer[i] != '\r' && buffer[i]!=' ') cnum.append((char)buffer[i]);
            for(int i = cNumIdx; i<profNameIdx;i++) profName.append((char)buffer[i]);
            for(int i = profNameIdx; i<cNameIdx;i++) cName.append((char)buffer[i]);
            if (!preCourseIdx.isEmpty()){
                StringBuilder preCourse = new StringBuilder();
                int start = cNameIdx;
                for(int k = 0; k<preCourseIdx.size();k++){
                    for(int i = start; i< preCourseIdx.get(k); i++) {
                    	if (buffer[i]!='\n' && buffer[i] != '\r' && buffer[i]!=' ') preCourse.append((char)buffer[i]);
                    }
                    preCourseVect.add(preCourse.toString());
                    start = preCourseIdx.get(k);
                    preCourse = new StringBuilder();
                }
            }
            
            // add Course
            if(cnum.length()!=0) { 
            	Course c= new Course();
            	c.addData(cnum.toString(),profName.toString(),cName.toString(),preCourseVect);
            	this.cVect.add(c);
            }
            
            // initializing
            idx = 0;
            cNumIdx = 0;
            profNameIdx = 0;
            cNameIdx = 0;
            numOfBlank = 0;
            
            databyte = '\0';
            preCourseIdx.clear();

        }
    }

    private void makesVect(PipedInputStream sin) throws IOException {
        int databyte = 0;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];

        int sNumIdx = 0;
        int fNameIdx=0;
        int lNameIdx = 0;
        int majorIdx = 0;
        Vector<Integer> cNumsIdx = new Vector<>();

        boolean minusOneTime=false;

        while (true){
            while (databyte != '\n' && databyte != -1){
            	// common filter
                databyte = sin.read();
				if (databyte == -1) {//managing last line 
					if(minusOneTime) return;
					minusOneTime= true;
				}
                if (databyte == ' ') numOfBlank++;
                if (databyte != -1) buffer[idx++] = (byte) databyte;

                // special filtering
                if (numOfBlank == 1 && databyte == ' ') sNumIdx = idx;
                if (numOfBlank == 2 && databyte == ' ') fNameIdx = idx;
                if (numOfBlank == 3 && databyte == ' ') lNameIdx = idx;
                if (numOfBlank == 4 && databyte == ' ') majorIdx = idx;
                if (numOfBlank >= 5 && databyte == ' ') cNumsIdx.add(idx);
            }
            // managing when \n & -1 , it doesn't contain last index problem 
	        if(sNumIdx==9) cNumsIdx.add(idx);

	        // make String || Vector<String>
            StringBuilder sNum = new StringBuilder();
            StringBuilder fName= new StringBuilder();
            StringBuilder lName = new StringBuilder();
            StringBuilder major = new StringBuilder();
            Vector<String> cNumsVect = new Vector<>();

            // append attribute
            for(int i = 0; i<sNumIdx;i++) sNum.append((char)buffer[i]);
            for(int i = sNumIdx; i<fNameIdx;i++) fName.append((char)buffer[i]);
            for(int i = fNameIdx; i<lNameIdx;i++) lName.append((char)buffer[i]);
            for(int i = lNameIdx; i<majorIdx;i++) major.append((char)buffer[i]);
            if (!cNumsIdx.isEmpty()){
                StringBuilder myCourse = new StringBuilder();
                int start = majorIdx;
                for(int k = 0; k<cNumsIdx.size();k++){
                    for(int i = start; i< cNumsIdx.get(k); i++){
                    	if (buffer[i]!='\n' && buffer[i] != '\r' && buffer[i]!=' ') myCourse.append((char)buffer[i]);
                    }
                    cNumsVect.add(myCourse.toString());
                    start = cNumsIdx.get(k);
                    myCourse = new StringBuilder();
                }
            }
            
            // add Student
            if(sNum.length()!=0) { 
            	 Student s= new Student();
                 s.addData(sNum.toString(),fName.toString(),lName.toString(), major.toString(), cNumsVect);
                 this.sVect.add(s);
            }
           
            // initializing
            idx = 0;
            sNumIdx = 0;
            fNameIdx = 0;
            lNameIdx = 0;
            sNumIdx = 0;
            
            numOfBlank = 0;
            databyte = '\0';
            cNumsIdx.clear();
        }
    }
    
}
