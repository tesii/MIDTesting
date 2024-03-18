package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentcourse")
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
 
    @Column(name = "ID")
    private String ID;

    @Column(name = "regID")
    private String regID;

    @Column(name = "courseID")
    private String courseID;

    @Column(name = "credits")
    private int credits;

    @Column(name = "results")
    private double results;

    @Column(name = "teacherID")
    private String teacherID;
	
    @ManyToOne
    @JoinColumn(name = "teacherID", referencedColumnName = "teacher_id", insertable = false, updatable = false)
    private Teacher teacher;
    
    @ManyToOne
    @JoinColumn(name = "regID", referencedColumnName = "id", insertable = false, updatable = false)
    private StudentRegistration registration;

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

 

	public StudentCourse() {
		super();
	}



	public StudentCourse(String ID, String regID, String courseID, int credits, double results, String teacherID,
			Teacher teacher, StudentRegistration registration, Course course) {
		super();
		this.ID = ID;
		this.regID = regID;
		this.courseID = courseID;
		this.credits = credits;
		this.results = results;
		this.teacherID = teacherID;
		this.teacher = teacher;
		this.registration = registration;
		this.course = course;
	}





	public String getID() {
		return ID;
	}



	public void setID(String ID) {
		this.ID = ID;
	}





	public String getCourseID() {
		return courseID;
	}



	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}



	public int getCredits() {
		return credits;
	}



	public void setCredits(int credits) {
		this.credits = credits;
	}



	public double getResults() {
		return results;
	}



	public void setResults(double results) {
		this.results = results;
	}



	public StudentRegistration getRegistration() {
		return registration;
	}



	public void setRegistration(StudentRegistration registration) {
		this.registration = registration;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}




	public String getTeacherID() {
		return teacherID;
	}



	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}




	public String getRegID() {
		return regID;
	}




	public void setRegID(String regID) {
		this.regID = regID;
	}

	
}