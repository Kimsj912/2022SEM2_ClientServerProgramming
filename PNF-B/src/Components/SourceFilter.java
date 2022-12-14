/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class SourceFilter extends CommonFilterImpl {
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;
        try { 
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(sourceFile)));
            while(true) {
                byte_read = br.read();
                if (byte_read == -1) return false;
                out.write(byte_read);
            }
        } catch (IOException e) {
            if (e instanceof EOFException) return false;
            else System.out.println(e);
        } finally {
        	try {out.close();} catch (IOException e) {e.printStackTrace();}
        } return false;
    }

}
