package main;

import interfaces.List;

/**
 * This class represents a library user. It contains private
 * fields representing the client's ID, full name and the list
 * of books they have currently checked out.
 * @author jorge
 */
public class User {
	/**
	 * Unique number that identifies the user
	 */
	private Integer id;
	/**
	 * Full name of the user
	 */
	private String name;
	/**
	 * A List of books that the user has currently checked out
	 */
	private List<Book> checkedOutList;
	
	/**
	 * Main constructor for the User class. Instantiates a library
	 * user with an ID, their full name, and a list of currently
	 * checked out books.
	 * @param id Unique number that identifies the user
	 * @param name Full name of the user
	 * @param checkedOutList A List of books that the user has currently
	 * checked out
	 */
	public User(Integer id, String name, List<Book> checkedOutList) {
		this.id = id;
		this.name = name;
		this.checkedOutList = checkedOutList;
	}
	/**
	 * Constructor for an instance of User without a list of
	 * currently checked out books
	 * @param id Unique number that identifies the user
	 * @param name Full name of the user
	 */
	public User(Integer id, String name) {
		this(id, name, null);
	}
	/**
	 * Constructor for an instance of User using only the user's
	 * full name
	 * @param name Full name of the user
	 */
	public User(String name) {
		this(null, name, null);
	}
	/**
	 * Constructor for an instance of User using only the List of
	 * books that the user has currently checked out
	 * @param checkedOutList A List of books that the user has 
	 * currently checked out
	 */
	public User(List<Book> checkedOutList) {
		this(null, null, checkedOutList);
	}
	
	/**
	 * Gets the user's ID
	 * @return The users ID, a unique number that identifies the 
	 * user
	 */
	public int getId() {
		return id;
	}
	/**
	 * Assigns an unique number that identifies the user
	 * @param id Unique number that identifies the user
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Gets the user's full name
	 * @return The user's full name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Assigns a name to the user
	 * @param name The user's full name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets a List of books that the user has currently 
	 * checked out
	 * @return A List of books that the user has currently 
	 * checked out
	 */
	public List<Book> getCheckedOutList() {
		return checkedOutList;
	}
	/**
	 * Assigns a List of books that the user has currently 
	 * checked out
	 * @param checkedOutList A List of books that the user has 
	 * currently checked out
	 */
	public void setCheckedOutList(List<Book> checkedOutList) {
	this.checkedOutList = checkedOutList;
	}
	
}
