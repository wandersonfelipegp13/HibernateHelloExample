package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {
	protected SessionFactory sessionFactory;

	protected void setup() {
		// code to load Hibernate Session factory
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build(); // configures settings from hibernate.cfg.xml
				
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

	protected void exit() {
		// code to close Hibernate Session factory
		sessionFactory.close();
	}

	protected void create() {
		// code to save a book
		Book book = new Book();
		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setPrice(32.59f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void read() {
		// code to get a book
		Session session = sessionFactory.openSession();

		long bookId = 1;
		Book book = session.get(Book.class, bookId);

		System.out.println("Title: " + book.getTitle());
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Price: " + book.getPrice());

		session.close();
	}

	protected void update() {
		// code to modify a book
		Book book = new Book();
		book.setId(1);
		book.setTitle("Ultimate Java Programming");
		book.setAuthor("Nam Ha Minh");
		book.setPrice(19.99f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		// code to remove a book
		Book book = new Book();
		book.setId(1);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(book);

		session.getTransaction().commit();
		session.close();
	}

	public static void main(String[] args) {
		// code to run the program

		BookManager manager = new BookManager();
		manager.setup();

		manager.create();
		// manager.read();
		// manager.update();
		// manager.delete();

		manager.exit();
	}
}
