/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class CommonFilterImpl implements CommonFilter {
	protected PipedInputStream in = new PipedInputStream(); // pipedInputStream은 자바에서 제공해주는, 바이트 단위로 데이터를 읽어들이는 클래스이다.
	protected PipedOutputStream out = new PipedOutputStream(); // pipedOutputStream은 자바에서 제공해주는, 바이트 단위로 데이터를 쓰는 클래스이다.

	public void connectOutputTo(CommonFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}
	public void connectInputTo(CommonFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}
	public PipedInputStream getPipedInputStream() {
		return in;
	}
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}

	// 실행은 갖고있지않고 컴포넌트들이 각각 갖고 있다.
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
	private void closePorts() {
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
