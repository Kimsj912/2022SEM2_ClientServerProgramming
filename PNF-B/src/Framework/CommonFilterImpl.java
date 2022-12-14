/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class CommonFilterImpl implements CommonFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();
	protected PipedInputStream in2 = new PipedInputStream();
	protected PipedOutputStream out2 = new PipedOutputStream();

	public void connectOutputTo(int outnum, int innum, CommonFilter nextFilter) throws IOException {
		if(innum==1) {
			if(outnum==1) {
				System.out.println("connectOutputTo");
				out.connect(nextFilter.getPipedInputStream());
			}
			else if(outnum==2) {
				System.out.println("Connect outnum=2");
				out2.connect(nextFilter.getPipedInputStream());
			}
		}else if(innum==2) {
			if(outnum==1) {
				out.connect(nextFilter.getPipedInput2Stream());
				System.out.println("connectOutputTo innum==2 / outnum==1");
			}
			else if(outnum==2) {
				out2.connect(nextFilter.getPipedInput2Stream());
				System.out.println("connectOutputTo innum==2 / outnum==2");
			}
		}
	}
	public void connectInputTo(int outnum, int innum, CommonFilter filter) throws IOException{
		in.connect(filter.getPipedOutputStream());
	}
	public PipedInputStream getPipedInputStream() {
		return in;
	}
	public PipedInputStream getPipedInput2Stream() {
		return in2;
	}
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}
	public PipedOutputStream getPipedOutput2Stream() {
		return out2;
	}


	abstract public boolean specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally {
			closePorts();
		}
	}
	protected void closePorts() {
		try {
			out.close();
			in.close();
			out2.close();
			in2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
