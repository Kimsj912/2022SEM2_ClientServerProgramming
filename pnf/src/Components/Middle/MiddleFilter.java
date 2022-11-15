/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{

    private final boolean isCSMustContain;
    public MiddleFilter(boolean isCSMustContain){
        this.isCSMustContain = isCSMustContain;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 4;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isPass = false;
        int byte_read = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                // CS�� �ʿ��ϴٰ� ������ CS�� �ְų�, CS�� �ʿ���ٰ� ���� �� CS�� ���ٸ� isPass�� true�� �ٲ��ش�.
                if(this.isCSMustContain && numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S') isPass = true;
                if(!this.isCSMustContain && numOfBlank == checkBlank && buffer[idx-3] != 'C' && buffer[idx-2] != 'S'
                        && Character.isLetter((char)buffer[idx-3]) && Character.isLetter((char)buffer[idx-2])) isPass = true;
            }
            if(isPass) {
                for(int i = 0; i<idx; i++){
                    out.write((char)buffer[i]);
                }
                isPass = false; // reset
            }
            if (byte_read == -1)  return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }  
}
