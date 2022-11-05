package Lists;

import Exceptions.NullDataException;
import Objects.Course;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseList {
	protected ArrayList<Course> courseArrayList;
	protected HashMap<String, Course> courseHashMap;

	public CourseList(String sStudentFileName) throws IOException {
		BufferedReader fileObj = new BufferedReader(new FileReader(sStudentFileName));
		// for get Index
		this.courseArrayList = new ArrayList<>();
		while (fileObj.ready()) {
			String stuInfo = fileObj.readLine();
			if (!stuInfo.equals("")) {
				this.courseArrayList.add(new Course(stuInfo));
			}
		}
		fileObj.close();

		// for fast search
		this.courseHashMap = new HashMap<>();
		for(Course course: courseArrayList){
			courseHashMap.put(course.getCourseId(), course);
		}
	}

	public ArrayList<Course> getAllCoursesRecords() throws NullDataException{
		if(this.courseArrayList.size() == 0) throw new NullDataException("---------No Course  records found-------------");
		return this.courseArrayList;
	}
	public boolean addCourse(Course course) {
		this.courseArrayList.add(course);
		this.courseHashMap.put(course.getCourseId(), course);
		return true;
	}

	public Course getCourseById(String courseId) {
		for (Course course : this.courseArrayList) {
			if (course.getCourseId().equals(courseId)) return course;
		}
		return null;
	}
	public ArrayList<Course> getCourseByCourseName(String courseName) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		String upperCaseCourseName = courseName.toUpperCase();
		for (Course course : this.courseArrayList) {
			if (course.getName().toUpperCase().contains(upperCaseCourseName)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}
	public ArrayList<Course> getCourseByProfessor(String professorName) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		String upperCaseProfessorName = professorName.toUpperCase();
		for (Course course : this.courseArrayList) {
			if (course.getProfessor().toUpperCase().contains(upperCaseProfessorName)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}

	public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		for (Course course : this.courseArrayList) {
			if (course.getPreCourse().contains(preCourseId)) courseArrayList.add(course);
		}
		return courseArrayList;
	}

	public ArrayList<Course> getCourseBySemester(String semester) {
		ArrayList<Course> courseArrayList = new ArrayList<>();
		for (Course course : this.courseArrayList) {
			if (course.getSemester().equals(semester)) {
				courseArrayList.add(course);
			}
		}
		return courseArrayList;
	}

	public boolean deleteCourseById(String courseId){
		for (int i = 0; i < this.courseArrayList.size(); i++) {
			Course course = (Course) this.courseArrayList.get(i);
			if (course.getCourseId().equals(courseId)) {
				this.courseArrayList.remove(i);
				this.courseHashMap.remove(courseId);
				return true;
			}
		}
		return false;
	}

	public boolean updateCourseById(String courseId, Course newCourse){
		for (int i = 0; i < this.courseArrayList.size(); i++) {
			Course course = (Course) this.courseArrayList.get(i);
			if (course.getCourseId().equals(courseId)) {
				this.courseArrayList.set(i, newCourse);
				this.courseHashMap.put(courseId, newCourse);
				return true;
			}
		}
		return false;
	}
}