package Lists;

import Exceptions.NullDataException;
import Objects.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentList {
	protected ArrayList<Student> vStudent;
	
	public StudentList(String sStudentFileName) throws IOException, FileNotFoundException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.vStudent.add(new Student(stuInfo));
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Student> getAllStudentRecords() throws NullDataException{
		if(this.vStudent.size() == 0) throw new NullDataException("---------No student records found-------------");
		return this.vStudent;
	}

	public Student getStudentRecord(String id){
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.match(id)) {
				return student;
			}
		}
		return null;
	}

	public boolean deleteStudentRecord(String student){
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student stu = (Student) this.vStudent.get(i);
			if (stu.match(student)) {
				this.vStudent.remove(i);
				return true;
			}
		}
		return false;
	}

	public void addStudentRecord(Student student){
		this.vStudent.add(student);
	}
}
