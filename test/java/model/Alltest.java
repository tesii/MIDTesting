package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.SSLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;





public class Alltest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @BeforeEach
    void setUp() throws Exception {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    void tearDown() throws Exception {
        session.close();
        System.out.println("Session closed\n");
    }


	 
    @Test
    void testCreateStudent() {
        System.out.println("Running testCreateStudentWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for student details
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter date of birth (YYYY-MM-DD): ");
            String dobString = scanner.nextLine();
            Date dob = java.sql.Date.valueOf(LocalDate.parse(dobString));

            // Your existing code...
            Student student = new Student(studentId, lastName, firstName, dob);

            // Save the student to the database
            String savedStudentId = (String) session.save(student);

            // Verifying that the student is created successfully
            assertNotNull(savedStudentId, "Student creation failed");
            System.out.println("Student created successfully");
            System.out.println("------------------------------");

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    void testCreateSemester() throws SSLException {
        System.out.println("Running testCreateSemesterWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for semester details
            System.out.print("Enter semester ID: ");
            String semesterId = scanner.nextLine();

            System.out.print("Enter semester name: ");
            String semesterName = scanner.nextLine();

            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDateString = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateString);

            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDateString = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(endDateString);

            // Creating a new semester
            Semester semester = new Semester(semesterId, semesterName, startDate, endDate);

            // Save the semester to the database
            String savedSemesterId = (String) session.save(semester);

            // Verifying that the semester is created successfully
            assertNotNull(savedSemesterId, "Semester creation failed");
            System.out.println("Semester created successfully");
            System.out.println("------------------------------");
            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            // Print a generic error message for other exceptions
            System.out.println("An unexpected exception occurred: " + e.getMessage());
            
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    void testCreateAcademicUnit() {
        System.out.println("Running testCreateAcademicUnitWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for academic unit details
            System.out.print("Enter academic unit code: ");
            String code = scanner.nextLine();

            System.out.print("Enter academic unit name: ");
            String name = scanner.nextLine();

            String parentId = null; // Initially set to null

            // Check if this is the first entry
            if (!isFirstEntry()) {
                // If not the first entry, prompt for parent academic unit ID
                System.out.print("Enter parent academic unit ID: ");
                parentId = scanner.nextLine();

                // Additional validation to ensure the parent exists
                if (!parentExists(parentId)) {
                    System.out.println("Error: Parent academic unit does not exist. Aborting operation.");
                    return;
                }
            }

            System.out.print("Enter academic unit type (PROGRAME or any other type): ");
            String unitTypeString = scanner.nextLine();
            EAcademicUnit unitType = EAcademicUnit.valueOf(unitTypeString);

            // Creating a new academic unit
            AcademicUnit academicUnit = new AcademicUnit();
            academicUnit.setCode(code);
            academicUnit.setName(name);
            academicUnit.setParentId(parentId);
            academicUnit.setAcademicUnitType(unitType);

            // Save the academic unit to the database
            String savedAcademicId = (String) session.save(academicUnit);

            // Verifying that the academic unit is created successfully
            assertNotNull(savedAcademicId, "AcademicUnit creation failed");
            System.out.println("AcademicUnit created successfully");
            System.out.println("------------------------------");

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    // Mock implementation for parentExists
    private boolean parentExists(String parentId) {
    	   Query query = session.createQuery("SELECT COUNT(a) FROM AcademicUnit a WHERE a.code = :parentId");
    	    query.setParameter("parentId", parentId);
    	    Long count = (Long) query.uniqueResult();

    	    return count > 0; 
    }

    // Mock implementation for isFirstEntry
    private boolean isFirstEntry() {
    	  Query query = session.createQuery("SELECT COUNT(a) FROM AcademicUnit a");
    	    Long count = (Long) query.uniqueResult();

    	    return count == 0;
    }

    @Test
    void testCreateCourse() {
        System.out.println("Running testCreateCourseWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for course details
            System.out.print("Enter course ID: ");
            String courseId = scanner.nextLine();

            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine();

            System.out.print("Enter academic unit ID: ");
            String academicUnitId = scanner.nextLine();

            System.out.print("Enter semester: ");
            String semester = scanner.nextLine();
            
            System.out.print("Enter teacher: ");
            String teacher = scanner.nextLine();
            
            System.out.print("Enter course definition: ");
            String coursedefinition = scanner.nextLine();

            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            
         

            // Creating a new course
            Course course = new Course();
            course.setId(courseId);
            course.setCourse_name(courseName);
            course.setAcademic_unit_id(academicUnitId);
            course.setSemester(semester);
            course.setCourse_code(courseCode);
            course.setTeacherID(teacher);
            course.setCoursedefinitionid(coursedefinition);
           
            // Save the course to the database
            String savedCourseId = (String) session.save(course);

            // Verifying that the course is created successfully
            assertNotNull(savedCourseId, "Course creation failed");
            System.out.println("Course created successfully");
            System.out.println("------------------------------");

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testCreateTeacher() {
        System.out.println("Running testCreateTeacherWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for teacher details
            System.out.print("Enter teacher ID: ");
            String teacherId = scanner.nextLine();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter qualification (e.g., MASTERS, PHD): ");
            EQualification qualification = EQualification.valueOf(scanner.nextLine().toUpperCase());

           

            // Creating a new teacher
            Teacher teacher = new Teacher();
            teacher.setTeacher_id(teacherId);
            teacher.setFirst_name(firstName);
            teacher.setLast_name(lastName);
            teacher.setQualification(qualification);
           

            // Save the teacher to the database
            String savedTeacherId = (String) session.save(teacher);

            // Verifying that the teacher is created successfully
            assertNotNull(savedTeacherId, "Teacher creation failed");
            System.out.println("Teacher created successfully");
            System.out.println("------------------------------");
            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testCreateCourseDefinition() {
        System.out.println("Running testCreateCourseDefinitionWithScannerInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for course definition details
            System.out.print("Enter course definition ID: ");
            String courseDefinitionId = scanner.nextLine();

            System.out.print("Enter course definition code: ");
            String courseDefinitionCode = scanner.nextLine();

            System.out.print("Enter course definition description: ");
            String courseDefinitionDescription = scanner.nextLine();

        

            // Create a Course_definition entity
            Course_definition courseDefinition = new Course_definition(null,null,null);
            courseDefinition.setCourse_definition_id(courseDefinitionId);
            courseDefinition.setCourseDefinitionCode(courseDefinitionCode);
            courseDefinition.setCourseDefinitionDescription(courseDefinitionDescription);
         

            // Save the Course_definition to the database
            String savedCourseDefinitionId = (String) session.save(courseDefinition);

            // Verifying that the Course_definition is created successfully
            assertNotNull(savedCourseDefinitionId, "CourseDefinition creation failed");
            System.out.println("CourseDefinition created successfully");
            System.out.println("------------------------------");

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testCreateStudentRegistration() {
        System.out.println("Running testCreateStudentRegistrationWithUserInput...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            // Collect Registration ID from the user
            System.out.print("Enter Registration ID: ");
            String studentRegistrationId = scanner.nextLine();

            // Collect student details from the user
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();

            System.out.print("Enter semester ID: ");
            String semesterId = scanner.nextLine();

            System.out.print("Enter academic ID: ");
            String academicId = scanner.nextLine();

            System.out.print("Enter course ID: ");
            String courseId = scanner.nextLine();
            
            // Create a StudentRegistration entity
            StudentRegistration studentRegistration = new StudentRegistration();
            studentRegistration.setId(studentRegistrationId);
            studentRegistration.setSemesterId(semesterId);
            studentRegistration.setStudentId(studentId);
            studentRegistration.setAcademic_id(academicId);
            studentRegistration.setCourse_id(courseId);
          
            studentRegistration.setRegistrationDate(new Date());

            String savedStudentRegistrationId = (String) session.save(studentRegistration);

            // Verifying that the StudentRegistration is created successfully
            assertNotNull(savedStudentRegistrationId, "StudentRegistration creation failed");
            System.out.println("StudentRegistration created successfully");
            System.out.println("------------------------------");

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testCreateStudentCourse() {
        // Assuming you have session and transaction variables initialized
        System.out.println("Running testCreateStudentCourse...");
        session.beginTransaction();

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter student course details:");

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Registration ID: ");
            String regID = scanner.nextLine();

            System.out.print("Course ID: ");
            String courseID = scanner.nextLine();

            System.out.print("Credits: ");
            int credits = Integer.parseInt(scanner.nextLine());

            System.out.print("Results: ");
            double results = Double.parseDouble(scanner.nextLine());

            System.out.print("Teacher ID: ");
            String teacherID = scanner.nextLine();

            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setID(id);
            studentCourse.setRegID(regID);
            studentCourse.setCourseID(courseID);
            studentCourse.setCredits(credits);
            studentCourse.setResults(results);
            studentCourse.setTeacherID(teacherID);

            // Assuming you have Teacher and StudentRegistration objects available
            Teacher teacher = session.get(Teacher.class, teacherID);
            StudentRegistration registration = session.get(StudentRegistration.class, regID);

            studentCourse.setTeacher(teacher);
            studentCourse.setRegistration(registration);

            session.save(studentCourse);

            // Commit the transaction if everything is successful
            session.getTransaction().commit();

            System.out.println("StudentCourse created successfully");
            System.out.println("------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testGetStudentsPerSemester() {
        // Create a real session object
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Prompt the user to enter the semester ID
            String targetSemesterId = "Sem_04";

            // Call the method to retrieve students per semester
            List<Student> students = getStudentsPerSemester(session, targetSemesterId);

            // Assert that the list of students is not null
            assertNotNull(students, "List of students should not be null");

            // Assert that the list of students is not empty
            assertFalse(students.isEmpty(), "No students found for semester " + targetSemesterId);

            // Print the details of students
            System.out.println("Students for semester " + targetSemesterId + ":");
            for (Student student : students) {
                System.out.println("ID: " + student.getStudent_id() + ", FirstName: " + student.getFirstName() + ", LastName: " + student.getLastName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("An unexpected exception occurred: " + e.getMessage());
        } finally {
            // Close the session
            session.close();
        }
    }

    // Method to retrieve students per semester
    private List<Student> getStudentsPerSemester(Session session, String semesterId) {
        Query<Student> query = session.createQuery(
                "SELECT s FROM StudentRegistration sr JOIN sr.student s WHERE sr.semesterId = :semesterId",
                Student.class);
        query.setParameter("semesterId", semesterId);
        return query.getResultList();
    }

    @Test
    void testListStudentsPerAcademicUnitAndSemester() {
        System.out.println("Running testListStudentsPerAcademicUnitAndSemesterWithUserInput...");
        // Create the session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Begin the transaction
            session.beginTransaction();

            // Simulate input for academic unit and semester
            String targetAcademicUnit = "AC-01"; // Or you can prompt the user to enter the academic unit ID
            String targetSemesterId = "Sem_01"; // Or you can prompt the user to enter the semester ID

            // Query to retrieve students per academic unit and semester
            List<Object[]> studentDetails = session.createQuery(
                    "SELECT s.student_id, s.lastName, s.firstName FROM StudentRegistration sr " +
                            "JOIN sr.student s " +
                            "WHERE sr.semesterId = :semesterId AND sr.academic_id = :academic_id", Object[].class)
                    .setParameter("semesterId", targetSemesterId)
                    .setParameter("academic_id", targetAcademicUnit)
                    .getResultList();

            // Display the list of students
            System.out.println("Students for Academic Unit " + targetAcademicUnit + " and Semester " + targetSemesterId + ":");

            // Assert that the list of student details is not null
            assertNotNull(studentDetails, "List of student details is null");

            // If the list is empty, assert that it should be empty
            if (studentDetails.isEmpty()) {
                assertEquals(0, studentDetails.size(), "No students found for academic unit: " + targetAcademicUnit +
                        " and semester: " + targetSemesterId);
            } else {
                // Assert the student details for each student in the list
                for (Object[] studentDetail : studentDetails) {
                    String student_id = (String) studentDetail[0];
                    String lastName = (String) studentDetail[1];
                    String firstName = (String) studentDetail[2];

                    assertNotNull(student_id, "Student ID should not be null");
                    assertNotNull(lastName, "Last name should not be null");
                    assertNotNull(firstName, "First name should not be null");

                    // Print student details
                    System.out.println("ID: " + student_id + ", Last Name: " + lastName + ", First Name: " + firstName);
                }
            }

            // Assert that the list of students is not empty
            assertFalse(studentDetails.isEmpty(), "No students found for academic unit: " + targetAcademicUnit +
                    " and semester: " + targetSemesterId);

            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        } finally {
            // Close the session
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    
    @Test
    void testListStudentsPerCourseAndSemester() {
        System.out.println("Running testListStudentsPerCourseAndSemester...");

        try {
            // Set course ID and semester ID
            String targetCourseId = "C_03";
            String targetSemesterId = "Sem_01";

            // Begin transaction
            session.beginTransaction();

            List<Object[]> registrations = session.createQuery(
                    "SELECT s.student_id, s.firstName, s.lastName " +
                            "FROM StudentRegistration sr " +
                            "JOIN sr.student s " +
                            "WHERE sr.course_id = :courseId AND sr.semesterId = :semesterId", Object[].class)
                    .setParameter("courseId", targetCourseId)
                    .setParameter("semesterId", targetSemesterId)
                    .getResultList();

            // Commit transaction
            session.getTransaction().commit();

            // Assert that the list of registrations is not null
            assertNotNull(registrations, "List of registrations is null");

            // Assert that the list of registrations is not empty
            assertFalse(registrations.isEmpty(), "No students found for course: " + targetCourseId +
                    " and semester: " + targetSemesterId);

            // Display the list of students
            System.out.println("Student details from course " + targetCourseId +
                    " and semester " + targetSemesterId + " are:");
            System.out.println("------------------------------");

            // Assert each student detail in the list
            for (Object[] registration : registrations) {
                String studentId = (String) registration[0];
                String firstName = (String) registration[1];
                String lastName = (String) registration[2];

                assertNotNull(studentId, "Student ID should not be null");
                assertNotNull(firstName, "First Name should not be null");
                assertNotNull(lastName, "Last Name should not be null");

                System.out.println("Student ID: " + studentId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }



    @Test
    void testListCoursesPerAcademicUnitAndSemester() {
        System.out.println("Running testListCoursesPerAcademicUnitAndSemesterWithUserInput...");
        session.beginTransaction();

        try {
            // Set academic unit ID and semester ID
            String targetAcademicUnit = "AC_05";
            String targetSemesterId = "Sem_01";

            List<Object[]> courseDetails = session.createQuery(
                    "SELECT c.id, c.course_name, t.first_name, t.last_name, cd.courseDefinitionCode, cd.courseDefinitionDescription " +
                            "FROM Course c " +
                            "JOIN c.teacher t " +
                            "JOIN c.courseDefinition cd " +
                            "WHERE c.academic_unit_id = :academicUnitId AND c.semester = :semesterId", Object[].class)
                    .setParameter("academicUnitId", targetAcademicUnit)
                    .setParameter("semesterId", targetSemesterId)
                    .getResultList();

            // Assert that the list of course details is not null
            assertNotNull(courseDetails, "List of course details is null");

            // Assert that at least one course is found
            assertFalse(courseDetails.isEmpty(), "No courses found for academic unit: " + targetAcademicUnit +
                    " and semester: " + targetSemesterId);

            // Display the list of courses
            if (!courseDetails.isEmpty()) {
                System.out.println("Courses offered in academic unit " + targetAcademicUnit +
                        " and semester " + targetSemesterId + " are:");
                System.out.println("------------------------------");

                // Assert each course detail in the list
                for (Object[] courseDetail : courseDetails) {
                    String courseId = (String) courseDetail[0];
                    String courseName = (String) courseDetail[1];
                    String teacher_firstname = (String) courseDetail[2];
                    String teacher_lastname = (String) courseDetail[3];
                    String courseDefinition_code = (String) courseDetail[4];
                    String courseDefinition_description = (String) courseDetail[5];

                    assertNotNull(courseId, "Course ID should not be null");
                    assertNotNull(courseName, "Course Name should not be null");
                    assertNotNull(teacher_firstname, "Teacher First Name should not be null");
                    assertNotNull(teacher_lastname, "Teacher Last Name should not be null");
                    assertNotNull(courseDefinition_code, "Course Definition Code should not be null");
                    assertNotNull(courseDefinition_description, "Course Definition Description should not be null");

                    System.out.println("Course ID: " + courseId);
                    System.out.println("Course Name: " + courseName);
                    System.out.println("Teacher firstname: " + teacher_firstname);
                    System.out.println("Teacher lastname: " + teacher_lastname);
                    System.out.println("Course Definition code: " + courseDefinition_code);
                    System.out.println("Course Definition Description: " + courseDefinition_description);
                    System.out.println("------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

  
    @Test
    void testListCoursePerStudent() {
        System.out.println("Running testListCoursesPerStudent...");
        session.beginTransaction();

        try {
            String targetStudentId = "24004"; // Set the target student ID

            List<Object[]> courseDetails = session.createQuery(
                     "SELECT c.id, c.course_name, t.first_name, t.last_name, cd.courseDefinitionCode, cd.courseDefinitionDescription " +
                         	    "FROM Course c " +
                         	    "JOIN c.teacher t " +  
                         	    "JOIN c.courseDefinition cd " + 
                         	    "WHERE c.student_id = :studentId ", Object[].class)
                	    .setParameter("studentId", targetStudentId)
                	    .getResultList();

            // Assert that the list of course details is not null
            assertNotNull(courseDetails, "List of course details is null");

            // Assert that at least one course is found for the student
            assertFalse(courseDetails.isEmpty(), "No courses found for student ID: " + targetStudentId);

            // Display the list of courses
            if (!courseDetails.isEmpty()) {
                System.out.println("Courses details for student ID " + targetStudentId + " are:");
                System.out.println("------------------------------");

                for (Object[] courseDetail : courseDetails) {
                    String courseCode = (String) courseDetail[0];
                    String courseName = (String) courseDetail[1];
                    String teacher_firstname = (String) courseDetail[2];
                    String teacher_lastname = (String) courseDetail[3];
                    
                    System.out.println("Course Code: " + courseCode);
                    System.out.println("Course Name: " + courseName);
                    System.out.println("Teacher FirstName: " + teacher_firstname);
                    System.out.println("Teacher LastName: " + teacher_lastname);
                    
                    System.out.println("------------------------------");
                }
            }

            // Commit the transaction if everything is successful
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback the transaction in case of an exception
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

}


