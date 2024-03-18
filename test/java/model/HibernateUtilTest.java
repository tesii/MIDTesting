package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HibernateUtilTest {
	
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
	        System.out.println("Running testCreateStudent...");
	        session.beginTransaction();

	        // Creating a new student
	        Student student = new Student("Regno_04", "ella", "thompson", java.sql.Date.valueOf(LocalDate.parse("1992-09-10")));

	        String studentId = (String) session.save(student);

	        session.getTransaction().commit();

	        // Verifying that the student is created successfully
	        assertNotNull(studentId, "Student creation failed");
	        System.out.println("Student created successfully");
	    }

}
