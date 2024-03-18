package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course")
public class Course implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id", length = 255)
    private String id;

	@Column(name = "student_id")
    private String student_id;
	
	@Column(name = "teacherID")
    private String teacherID;
	
	   @Column(name = "coursedefinitionid")
	    private String coursedefinitionid;
	
    @Column(name = "course_name", length = 255)
    private String course_name;
    
    @Column(name = "course_code", length = 255)
    private String course_code;
    
    @Column(name = "academic_unit_id", length = 255)
    private String academic_unit_id;
    
    @Column(name = "semester", length = 255)
    private String semester;

 
    
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "semester", referencedColumnName = "semester_id", insertable = false, updatable = false)
    private Semester semestermodel;

    @ManyToOne
    @JoinColumn(name = "teacherID", referencedColumnName = "teacher_id", insertable = false, updatable = false)
    private Teacher teacher;
    
    @OneToOne
    @JoinColumn(name = "coursedefinitionid", referencedColumnName = "course_definition_id", insertable = false, updatable = false)
    private Course_definition courseDefinition;
    
    @ManyToOne
    @JoinColumn(name = "academic_unit_id", referencedColumnName = "code", insertable = false, updatable = false)
    private AcademicUnit academicunit;

	public Course() {
		super();
	}



	






	public Course(String id, String student_id, String teacherID, String coursedefinitionid, String course_name,
			String course_code, String academic_unit_id, String semester, Student student, Semester semestermodel,
			Teacher teacher, Course_definition courseDefinition, AcademicUnit academicunit) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.teacherID = teacherID;
		this.coursedefinitionid = coursedefinitionid;
		this.course_name = course_name;
		this.course_code = course_code;
		this.academic_unit_id = academic_unit_id;
		this.semester = semester;
		this.student = student;
		this.semestermodel = semestermodel;
		this.teacher = teacher;
		this.courseDefinition = courseDefinition;
		this.academicunit = academicunit;
	}










	public Course(String course_code) {
		super();
		this.course_code = course_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getAcademic_unit_id() {
		return academic_unit_id;
	}

	public void setAcademic_unit_id(String academic_unit_id) {
		this.academic_unit_id = academic_unit_id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}



	
	public Semester getSemestermodel() {
		return semestermodel;
	}

	public void setSemestermodel(Semester semestermodel) {
		this.semestermodel = semestermodel;
	}

	public AcademicUnit getAcademicunit() {
		return academicunit;
	}

	public void setAcademicunit(AcademicUnit academicunit) {
		this.academicunit = academicunit;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}


	public String getTeacherID() {
		return teacherID;
	}






	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}






	public String getCoursedefinitionid() {
		return coursedefinitionid;
	}






	public void setCoursedefinitionid(String coursedefinitionid) {
		this.coursedefinitionid = coursedefinitionid;
	}



	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public Course_definition getCourseDefinition() {
		return courseDefinition;
	}



	public void setCourseDefinition(Course_definition courseDefinition) {
		this.courseDefinition = courseDefinition;
	}










	public String getStudent_id() {
		return student_id;
	}










	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}










	public Student getStudent() {
		return student;
	}










	public void setStudent(Student student) {
		this.student = student;
	}



		
   
}
