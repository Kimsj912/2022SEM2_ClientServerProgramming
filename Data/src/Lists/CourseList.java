package Lists;

import Exceptions.NullDataException;
import Objects.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseList extends CommonList<Course>{

	public CourseList(String fileName) throws IOException {
		super(fileName);
		while (objFile.ready()) {
			String info = objFile.readLine();
			if (!info.equals("")) {
				this.add(new Course(info));
			}
		}
		objFile.close();
	}

	public Course getCourseById(String courseId) {
		for (Course course : this) {
			if (course.getCourseId().equals(courseId)) return course;
		}
		return null;
	}
	public ArrayList<Course> getCourseByCourseName(String courseName) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		String upperCaseCourseName = courseName.toUpperCase();
		for (Course course : this) {
			if (course.getName().toUpperCase().contains(upperCaseCourseName)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}
	public ArrayList<Course> getCourseByProfessor(String professorName) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		String upperCaseProfessorName = professorName.toUpperCase();
		for (Course course : this) {
			if (course.getProfessor().toUpperCase().contains(upperCaseProfessorName)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}

	public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		for (Course course : this) {
			if (course.getPreCourse().contains(preCourseId)) courseArrayList.add(course);
		}
		return courseArrayList;
	}

	public ArrayList<Course> getCourseBySemester(String semester) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		for (Course course : this) {
			if (course.getSemester().equals(semester)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}

	public boolean deleteCourseById(String courseId){
		boolean result = false;
		for (int i = 0; i < this.size(); i++) {
			Course course = (Course) this.get(i);
			if (course.getCourseId().equals(courseId)) {
				result = this.remove(course);
				break;
			}
		}
		return result;
	}

	public boolean updateCourseById(String courseId, Course newCourse){
		boolean result = false;
		for (int i = 0; i < this.size(); i++) {
			Course course = (Course) this.get(i);
			if (course.getCourseId().equals(courseId)) {
				result = this.set(i, newCourse) != null;
				break;
			}
		}
		return result;
	}
}