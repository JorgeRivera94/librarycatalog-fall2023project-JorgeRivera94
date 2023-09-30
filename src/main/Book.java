package main;

import java.time.LocalDate;
/*TODO description of class */
public class Book {
	/**
	 * Unique number that identifies the book 
	 */
	private int id;
	
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
	private String genre; //TODO maybe use an enum with all 5 posible genres?
	
	/**
	 * The last date where someone borrowed the book
	 */
	private LocalDate lastCheckOutDate;
	
	/**
	 * Indicates whether the book is currently checked out
	 * of the library
	 */
	private Boolean checkOut;
	
	public int getId() {
		return -10;
	}
	public void setId(int id) {
		
	}
	public String getTitle() {
		return "";
	}
	public void setTitle(String title) {
		
	}
	public String getAuthor() {
		return "";
	}
	public void setAuthor(String author) {
		
	}
	public String getGenre() {
		return "";
	}
	public void setGenre(String genre) {
		
	}
	public LocalDate getLastCheckOut() {
		return null;
	}
	public void setLastCheckOut(LocalDate lastCheckOut) {
		
	}
	public boolean isCheckedOut() {
		return false;
	}
	public void setCheckedOut(boolean checkedOut) {
		
	}
	
	@Override
	public String toString() {
		/*
		 * This is supposed to follow the format
		 * 
		 * {TITLE} By {AUTHOR}
		 * 
		 * Both the title and author are in uppercase.
		 */
		return "";
	}
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		return -1000;
	}
}
