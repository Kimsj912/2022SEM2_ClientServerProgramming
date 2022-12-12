/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.SinkFilter;
import Components.SourceFilter;
import Components.checkSatisfiedFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter srcFilter1 = new SourceFilter("Courses.txt");
            CommonFilter srcFilter2 = new SourceFilter("Students.txt");
            CommonFilter snkFilter1 = new SinkFilter("Output-1.txt");
            CommonFilter snkFilter2 = new SinkFilter("Output-2.txt");
            CommonFilter filter3 = new checkSatisfiedFilter();

            // A2 (1-3-6-2)
            srcFilter1.connectOutputTo(1,1,filter3);
            srcFilter2.connectOutputTo(1,2,filter3);
            filter3.connectOutputTo(1,1,snkFilter1);
            filter3.connectOutputTo(2,1,snkFilter2);

            Thread srcthread1 = new Thread(srcFilter1);
            Thread srcthread2 = new Thread(srcFilter2);
            Thread snkthread1 = new Thread(snkFilter1);
            Thread snkthread2 = new Thread(snkFilter2);
            Thread thread3 = new Thread(filter3);

            srcthread1.start();
            srcthread2.start();
            snkthread1.start();
            snkthread2.start();
            thread3.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
