package Lists;

import Exceptions.NullDataException;
import Objects.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentList extends CommonList<Student> {

	public StudentList(String fileName) throws IOException {
		super(fileName);
		while (objFile.ready()) {
			String info = objFile.readLine();
			if (!info.equals("")) {
				this.add(new Student(info));
			}
		}
		objFile.close();
	}


	public ArrayList<Student> getStudentList(){
		return new ArrayList<>(this);
	}

	public Student getStudentById(String id){
		for (Student student : this) {
			if (student.getStudentId().equals(id)) return student;
		}
		return null;
	}

	public ArrayList<Student> getStudentsByName(String name){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		String upperCaseName = name.toUpperCase();
		for(Student student: this){
			if(student.getName().toUpperCase().contains(upperCaseName)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public ArrayList<Student> getStudentsByMajor(String major){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		for(Student student: this){
			if(student.getMajor().equals(major)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public ArrayList<Student> getStudentsByCompletedCourse(String courseId){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		for(Student student: this){
			if(student.getCompletedCourses().contains(courseId)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public boolean deleteStudentById(String studentId){
		boolean result = false;
		for (int i = 0; i < this.size(); i++) {
			Student student = this.get(i);
			if (student.getStudentId().equals(studentId)) {
				result = this.remove(student);
				break;
			}
		}
		return result;
	}

	public boolean updateStudentById(String studentId, Student newStudent){
		boolean result = false;
		for (int i = 0; i < this.size(); i++) {
			Student student = this.get(i);
			if (student.getStudentId().equals(studentId)) {
				result = this.set(i, newStudent) != null;
				break;
			}
		}
		return result;
	}

}