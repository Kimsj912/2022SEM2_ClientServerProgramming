package Lists;

import Exceptions.NullDataException;
import Objects.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentList {
	protected ArrayList<Student> studentArrayList;
	protected HashMap<String, Student> studentHashMap;

	public StudentList(String sStudentFileName) throws IOException, FileNotFoundException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		// for get Index
		this.studentArrayList = new ArrayList<>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.studentArrayList.add(new Student(stuInfo));
			}
		}
		objStudentFile.close();

		// for fast search
		this.studentHashMap = new HashMap<>();
		for(Student student: studentArrayList){
			studentHashMap.put(student.getStudentId(), student);
		}
	}

	public ArrayList<Student> getAllStudentRecords() throws NullDataException{
		if(this.studentArrayList.size() == 0) throw new NullDataException();
		return this.studentArrayList;
	}

	public Student getStudentById(String id){
		return studentHashMap.get(id);
	}

	public ArrayList<Student> getStudentsByName(String name){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		for(Student student: this.studentArrayList){
			if(student.getName().equals(name)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public ArrayList<Student> getStudentByMajor(String major){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		for(Student student: this.studentArrayList){
			if(student.getMajor().equals(major)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public ArrayList<Student> getStudentsByCompletedCourse(String courseId){
		ArrayList<Student> studentArrayList = new ArrayList<>();
		for(Student student: this.studentArrayList){
			if(student.getCompletedCourses().contains(courseId)){
				studentArrayList.add(student);
			}
		}
		return studentArrayList;
	}

	public boolean addStudent(Student student){
		this.studentArrayList.add(student);
		this.studentHashMap.put(student.getStudentId(), student);
		return true;
	}

	public boolean deleteStudentById(String studentId){
		for (int i = 0; i < this.studentArrayList.size(); i++) {
			Student stu = (Student) this.studentArrayList.get(i);
			if (stu.getStudentId().equals(studentId)) {
				this.studentArrayList.remove(i);
				this.studentHashMap.remove(studentId);
				return true;
			}
		}
		return false;
	}
	public boolean updateStudentById(String studentId, Student newStudent){
		for (int i = 0; i < this.studentArrayList.size(); i++) {
			Student student = this.studentArrayList.get(i);
			if (student.getStudentId().equals(studentId)) {
				this.studentArrayList.set(i, newStudent);
				this.studentHashMap.put(studentId, newStudent);
				return true;
			}
		}
		return false;
	}
}