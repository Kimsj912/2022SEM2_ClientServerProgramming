
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourse;

	public CourseList(String sStudentFileName) throws IOException {
		BufferedReader fileObj = new BufferedReader(new FileReader(sStudentFileName));
		this.vCourse = new ArrayList<>();
		while (fileObj.ready()) {
			String stuInfo = fileObj.readLine();
			if (!stuInfo.equals("")) {
				this.vCourse.add(new Course(stuInfo));
			}
		}
		fileObj.close();
	}

	public ArrayList<Course> getAllCoursesRecords() {
		return this.vCourse;
	}
	public void addCourseRecord(Course course) {
		this.vCourse.add(course);
	}
	public void deleteCourseRecord(String cCID) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(cCID)) {
				this.vCourse.remove(i);
				break;
			}
		}
	}

	public Course getCourseRecord(String cCID) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(cCID)) {
				return course;
			}
		}
		return null;
	}

	public boolean isRegisteredStudent(String cCID) {
		for (Course value : this.vCourse) {
			if (((Course) value).match(cCID)) return true;
		}
		return false;
	}

	public Course getCourse(String cCID) {
		for (Course value : this.vCourse) {
			if (((Course) value).match(cCID)) return value;
		}
		return null;
	}
}
