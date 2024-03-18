package model;

import javax.persistence.*;
import java.util.Date; // Import Date class for registration date

@Entity
@Table(name = "registration")
public class StudentRegistration {

    @Id
    private String id;

    @Column(name = "studentId")
    private String studentId;

    @Column(name = "academic_id")
    private String academic_id;

    @Column(name = "semesterId")
    private String semesterId;
    
    @Column(name = "course_id")
    private String course_id;


    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate; // New field for registration date

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_id", referencedColumnName = "code", insertable = false, updatable = false)
    private AcademicUnit academicunit;

    @ManyToOne
    @JoinColumn(name = "semesterId", referencedColumnName = "semester_id", insertable = false, updatable = false)
    private Semester semester;
    
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;


    // Constructors

    public StudentRegistration() {
    }

	
	



	public StudentRegistration(String id, String studentId, String academic_id, String semesterId, String course_id,
			Date registrationDate, Student student, AcademicUnit academicunit, Semester semester, Course course) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.academic_id = academic_id;
		this.semesterId = semesterId;
		this.course_id = course_id;
		this.registrationDate = registrationDate;
		this.student = student;
		this.academicunit = academicunit;
		this.semester = semester;
		this.course = course;
	}






	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAcademic_id() {
		return academic_id;
	}

	public void setAcademic_id(String academic_id) {
		this.academic_id = academic_id;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public AcademicUnit getAcademicunit() {
		return academicunit;
	}

	public void setAcademicunit(AcademicUnit academicunit) {
		this.academicunit = academicunit;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}






	public String getCourse_id() {
		return course_id;
	}






	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}






	public Course getCourse() {
		return course;
	}






	public void setCourse(Course course) {
		this.course = course;
	}





	

   }
