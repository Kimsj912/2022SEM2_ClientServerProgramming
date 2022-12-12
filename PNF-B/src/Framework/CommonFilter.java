/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */
package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface CommonFilter extends Runnable{
    public void connectOutputTo(int outnum, int innum, CommonFilter filter) throws IOException;
    public void connectInputTo(int outnum, int innum, CommonFilter filter) throws IOException;
    public PipedInputStream getPipedInputStream();
    public PipedOutputStream getPipedOutputStream();
    public PipedInputStream getPipedInput2Stream();
    public PipedOutputStream getPipedOutput2Stream();
}
