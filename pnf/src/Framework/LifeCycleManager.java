/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.AddCourseMiddleFilter;
import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter sourceFilter = new SourceFilter("Students.txt");
            CommonFilter sinkFilter = new SinkFilter("Output.txt");
            CommonFilter middleFilter = new MiddleFilter(true);
            CommonFilter add12345Filter = new AddCourseMiddleFilter("12345");
            CommonFilter add23456Filter = new AddCourseMiddleFilter("23456");

            sourceFilter.connectOutputTo(middleFilter);
            middleFilter.connectOutputTo(add12345Filter);
            add12345Filter.connectOutputTo(add23456Filter);
            add23456Filter.connectOutputTo(sinkFilter);
            
            Thread thread1 = new Thread(sourceFilter);
            Thread thread2 = new Thread(sinkFilter);
            Thread thread3 = new Thread(middleFilter);
            Thread thread4 = new Thread(add12345Filter);
            Thread thread5 = new Thread(add23456Filter);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
