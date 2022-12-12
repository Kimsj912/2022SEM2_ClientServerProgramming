package Components.CourseRegister;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import Framework.Event;
import Framework.EventId;
import Framework.EventQueue;
import Framework.RMIEventBus;

public class CourseRegisterMain {
	 public static void main(String[] args)throws FileNotFoundException, IOException, NotBoundException {
		 RMIEventBus eventBus = (RMIEventBus) Naming.lookup("EventBus");
		 long componentId = eventBus.register();
		 System.out.println("CourseRegisterMain (ID:" + componentId + ") is successfully registered...");
		 Event event = null;
		 boolean done = false;
		 while(!done) {
			 try {
				 Thread.sleep(1000);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 EventQueue eventQueue = eventBus.getEventQueue(componentId);
			 for (int i = 0; i < eventQueue.getSize(); i++) {
				 event = eventQueue.getEvent();
				 System.out.println("Received an event(ID: " + event.getEventId() + ")...");
				 if (event.getEventId() == EventId.EnrollInCourse) {
					 printLogEvent("EnrollInCourse", event);
					 String[] strs = event.getMessage().split(" ");
					 String sid = strs[0];
					 String cid = strs[1];

					 eventBus.sendEvent(new Event(EventId.ListStudentsRegistered, sid));
					 eventBus.sendEvent(new Event(EventId.ListCourseRegistered, cid));
					 eventBus.sendEvent(new Event(EventId.ListCoursesCompleted,event.getMessage()));

				 } else if (event.getEventId() == EventId.) {

				 } else if (event.getEventId() == EventId.QuitTheSystem) {
					 eventBus.unRegister(componentId);
					 done = true;
				 }
			 }
		 }
	 }
	private static void printLogEvent(String comment, Event event) {
		System.out.println(
				"\n** " + comment + " the event(ID:" + event.getEventId() + ") message: " + event.getMessage());
	}
}

