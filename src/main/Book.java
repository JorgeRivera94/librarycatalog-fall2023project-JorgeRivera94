package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * This class represents a book in a library.
 * @author jorge
 */
public class Book {
	/**
	 * Unique number that identifies the book 
	 */
	private Integer id;
	/**
	 * Name of the book
	 */
	private String title;
	/**
	 * Who authored the book
	 */
	private String author;
	/**
	 * What genre the book belongs to
	 */
	private String genre;
	/**
	 * The last date where someone borrowed the book from
	 * the library
	 */
	private LocalDate lastCheckOut;
	/**
	 * Indicates whether the book is currently checked out
	 * of the library
	 */
	private Boolean checkedOut;
	
	/**
	 * Main constructor for the Book class.
	 * @param id Unique number that identifies the book
	 * @param title Name of the book
	 * @param author Who authored the book
	 * @param genre What genre the book belongs to
	 * @param lastCheckOut The last date where someone borrowed the book from the library
	 * @param checkedOut Indicates whether the book is currently checked out of the library
	 */
	public Book(Integer id, String title, String author, String genre, LocalDate lastCheckOut, Boolean checkedOut) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.lastCheckOut = lastCheckOut;
		this.checkedOut = checkedOut;
	}
	/**
	 * Constructor for an instance of Book, without having been checked out.
	 * Missing parameters are initialized to null.
	 * @param id Unique number that identifies the book
	 * @param title Name of the book
	 * @param author Who authored the book
	 * @param genre What genre the book belongs to
	 */
	public Book(Integer id, String title, String author, String genre) {
		this(id, title, author, genre, null, null);
	}
	/**
	 * Constructor for an instance of Book without an assigned ID.
	 * Missing fields are initialized to null.
	 * @param title Name of the book
	 * @param author Who authored the book
	 * @param genre What genre the book belongs to
	 */
	public Book(String title, String author, String genre) {
		this(null, title, author, genre, null, null);
	}
	/**
	 * Constructor for an instance of Book without an assigned ID or genre.
	 * Missing fields are initialized to null.
	 * @param title Name of the book
	 * @param author Who authored the book
	 */
	public Book(String title, String author) {
		this(null, title, author, null, null, null);
	}
	/**
	 * Constructor for an instance of Book while only knowing it's title.
	 * Missing fields are initialized to null.
	 * @param title Name of the book
	 */
	public Book(String title) {
		this(null, title, null, null, null, null);
	}
	
	/**
	 * Gets this book's ID.
	 * @return	The book ID
	 */
	public int getId() {
		return id;
	}
	/**
	 * Assigns an ID to the book in the library.
	 * @param id The ID the book will have in the library
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Gets this book's title.
	 * @return The book title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Assigns a title to the book.
	 * @param title The title to be assigned to the book
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Gets this book's author.
	 * @return The book author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Assigns an author to the book.
	 * @param author The author's name to be assigned to the book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * Gets this book's genre.
	 * @return The book genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * Assigns a book genre.
	 * @param genre The genre of the book
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * Gets this book's last check out date.
	 * @return The book's last check out date
	 */
	public LocalDate getLastCheckOut() {
		return lastCheckOut;
	}
	/**
	 * Assigns a check out date.
	 * @param lastCheckOut Date the book was checked out form the library
	 */
	public void setLastCheckOut(LocalDate lastCheckOut) {
		this.lastCheckOut = lastCheckOut;
	}
	/**
	 * Checks if the book is currently checked out of the library.
	 * @return True if the book is currently checked out
	 */
	public boolean isCheckedOut() {
		return checkedOut;
	}
	/**
	 * Assigns a check out status.
	 * @param checkedOut Whether the book is currently checked out
	 */
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	/**
	 * Describes the book by returning it's title and author.
	 * @return A string representing the book by giving it's title and author
	 */
	@Override
	public String toString() {
		/*
		 * This is supposed to follow the format
		 * 
		 * {TITLE} By {AUTHOR}
		 * 
		 * Both the title and author are in uppercase.
		 */
		return title.toUpperCase() + " BY " + author.toUpperCase();
	}
	
	/**
	 * Method to calculate the fees on a book based on if the book is currently
	 * checked out and the last check out date.
	 * @return A float value representing the fees, in dollars, on a book
	 */
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		
		//Book has not been checked out
		if (!this.isCheckedOut()) {
			return 0;
		}
		
		//Specifications say to take September 15, 2023 as "today's date"
		LocalDate currentDate = LocalDate.parse("2023-09-15");
		LocalDate cutOffDate = this.lastCheckOut.plusDays(31);
		
		long daysOverdue = cutOffDate.until(currentDate, ChronoUnit.DAYS);
		
		if (daysOverdue < 0) {
			return 10;
		}
		else {
			double fees = 10 + 1.5 * daysOverdue;
			return (float) fees;
		}
		
	}
}
