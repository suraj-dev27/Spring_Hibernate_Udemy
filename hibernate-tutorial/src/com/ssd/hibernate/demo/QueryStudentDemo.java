package com.ssd.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ssd.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students (from hibernate 5.2 onwards replace list() with
			// getResultList() method)
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(theStudents);

			// query students: lastName='Duck'
			theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();

			// display the students
			System.out.println("\n\nStudents who have last name of Duck");
			displayStudents(theStudents);

			// query students: lastName='Duck' OR firstName='Suraj'
			theStudents = session.createQuery("from Student s where s.lastName='Duck' OR firstName='Abhi'")
					.getResultList();

			// display the students
			System.out.println("\n\nStudents who have last name of Duck OR first name of Abhi");
			displayStudents(theStudents);

			// query students: where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%yahoo.com'").getResultList();

			// display the students
			System.out.println("\n\nStudents who's email ends with 'yahoo.com'");
			displayStudents(theStudents);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
