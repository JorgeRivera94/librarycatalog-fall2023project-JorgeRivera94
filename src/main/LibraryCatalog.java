package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;

/**
 * This class represents the library. It manages the library's users and
 * book catalog. It contains the private fields catalog, that acts as the
 * library's book catalog; and users, that is a List of the library's
 * clients.
 * @author jorge
 *
 */
public class LibraryCatalog {
	/**
	 * A list of books that the library owns. It is implemented using an
	 * ArrayList to facilitate direct access to particular books in the
	 * catalog. This adds complexity to the add() method, specially in 
	 * the LibraryCatalog constructor, but it is better because there are
	 * many methods that will be reading the List and giving them direct
	 * access is worth the trade in complexity.
	 */
	private List<Book> catalog;
	/**
	 * A list of users that are clients of the library. It is implemented as
	 * an ArrayList because once created in the LibraryCatalog constructor,
	 * it's size does not change.
	 */
	private List<User> users;
	
	/**
	 * Default constructor for the LibraryCatalog Class.
	 * Uses an ArrayList for the book catalog because it is assumed that
	 * the amount of books will not constantly grow and reduce, and to
	 * facilitate finding a specific book by it's index.
	 * Uses ArrayList for the user List because once created in the 
	 * LibraryCatalog constructor it's size is not affected.
	 * @throws IOException
	 */
	public LibraryCatalog() throws IOException {
		this.catalog = getBooksFromFiles();
		this.users = getUsersFromFiles();
	}
	
	/**
	 * Private method called by the constructor in order to fill the book
	 * catalog with Book instances described by information in files.
	 * @return An ArrayList containing Book objects that serves as the library's 
	 * catalog
	 * @throws IOException If there is no catalog.csv in data/catalog.csv
	 */
	private List<Book> getBooksFromFiles() throws IOException {
		
		List<Book> bookList = new ArrayList<Book>();
		String fileName = "data/catalog.csv";
		String line;
		Integer id;
		String title;
		String author;
		String genre;
		String lastCheckOut;
		Boolean checkedOut;
		int index;
		
		try {
			var reader = new BufferedReader(new FileReader(fileName));
		
			//discard first line
			reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				//Get book ID
				index = line.indexOf(',');
				id = Integer.valueOf(line.substring(0, index));
				line = line.substring(index +1);
				
				//Get book title
				index = line.indexOf(',');
				title = line.substring(0, index);
				line = line.substring(index +1);
				
				//Get book author
				index = line.indexOf(',');
				author = line.substring(0, index);
				line = line.substring(index +1);
				
				//Get book genre
				index = line.indexOf(',');
				genre = line.substring(0, index);
				line = line.substring(index +1);
				
				//Get last check out
				index = line.indexOf(',');
				lastCheckOut = line.substring(0, index);
				line = line.substring(index +1);
				
				//check if book is checked out
				checkedOut = Boolean.valueOf(line);
				
				//instantiate book and add it to the list
				bookList.add(new Book(id, title, author, genre, lastCheckOut, checkedOut));
			}
			reader.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return bookList;
	}
	/**
	 * Private method called by the constructor in order to fill the user
	 * list with User instances described by information in files.
	 * @return An ArrayList containing User objects that represent the library's
	 * clients
	 * @throws IOException if there is no user.csv in data/user.csv
	 */
	private List<User> getUsersFromFiles() throws IOException {
		
		List<User> userList = new ArrayList<User>();
		String fileName = "data/user.csv";
		String line;
		Integer id;
		String name;
		List<Book> checkedOutList;
		
		int index;
		String[] bookIDs;
		
		var reader = new BufferedReader(new FileReader(fileName));
		
		//discard first line
		reader.readLine();
		
		while ((line = reader.readLine()) != null) {
			//Get user ID
			index = line.indexOf(',');
			id = Integer.valueOf(line.substring(0, index));
			line = line.substring(index +1);
			
			//Get user's full name
			index = line.indexOf(',');
			name = line.substring(0, index);
			line = line.substring(index);
			
			//Get user's checked out book's IDs
			checkedOutList = new SinglyLinkedList<Book>();
			if (line.length() > 1) {
				line = line.substring(2, line.length() -1);
				bookIDs = line.split(" ");
				
				for (String e : bookIDs) {
					checkedOutList.add(0, this.catalog.get(Integer.valueOf(e) -1));
				}
				
			}
			userList.add(new User(id, name, checkedOutList));
		}
		reader.close();
		
		return userList;
	}
	/**
	 * Gets this library's book catalog.
	 * @return A List of books the library owns
	 */
	public List<Book> getBookCatalog() {
		return this.catalog;
	}
	/**
	 * Gets this library's user list
	 * @return A List of users that the library has
	 */
	public List<User> getUsers() {
		return this.users;
	}
	/**
	 * Adds a new book the the library's catalog. 
	 * @param title The book's title
	 * @param author The book's author
	 * @param genre The book's genre
	 */
	public void addBook(String title, String author, String genre) {
		catalog.add(new Book(this.catalog.size() +1, title, author, genre, "2023-09-15", false));
		return;
	}
	/**
	 * Searches for the book in the catalog that has
	 * the given ID and removes it.
	 * @param id ID of the book to be removed from the
	 * catalog
	 */
	public void removeBook(int id) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getId().equals(id)) {
				catalog.remove(i);
				return;
			}
		}
	}	
	
	/**
	 * Checks out a book from the library if it is not
	 * already checked out. If it checks out a book, it
	 * changes the book's check-out status to true and 
	 * check out date to today, September 15, 2023.
	 * @param id ID of the book to be checked out
	 * @return true if it manages to check out the book
	 */
	public boolean checkOutBook(int id) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getId().equals(id)) {
				Book toCheckOut = catalog.get(i);
				if (!toCheckOut.isCheckedOut()) {
					toCheckOut.setCheckedOut(true);
					toCheckOut.setLastCheckOut(LocalDate.parse("2023-09-15"));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns a book form the library if it is not already
	 * returned. If it manages to return a book, it changes 
	 * the book's check-out status to false.
	 * @param id ID of the book to be returned
	 * @return true if it manages to return the book
	 */
	public boolean returnBook(int id) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getId().equals(id)) {
				Book toReturn = catalog.get(i);
				if (toReturn.isCheckedOut()) {
					toReturn.setCheckedOut(false);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the book with the given ID is available 
	 * for check out.
	 * @param id The book's ID
	 * @return true if the book is available for check out
	 */
	public boolean getBookAvailability(int id) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getId().equals(id)) {
				return !catalog.get(i).isCheckedOut();
			}
		}
		return false;
	}
	
	/**
	 * Returns the amount of of books of the same title are
	 * present in the library's catalog.
	 * @param title The title of the books to be counted
	 * @return The amount of books of the parameter title
	 * in the catalog
	 */
	public int bookCount(String title) {
		List<Book> toCount = searchForBook(x -> x.getTitle().equals(title));
		return toCount.size();
	}
	/**
	 * Generates a .txt file with a report that includes
	 * the number of books per genre, the total amount of
	 * books in the library, the list of books that are
	 * currently checked out, the list of users that owe
	 * money from books that have been checked out for too
	 * long along with the money they owe, and the total
	 * fees owed to the library.
	 * @throws IOException if the output directory is invalid
	 */
	public void generateReport() throws IOException {
		
		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";
		/*
		 * In this section you will print the amount of books per category.
		 * 
		 * Place in each parenthesis the specified count. 
		 * 
		 * Note this is NOT a fixed number, you have to calculate it because depending on the 
		 * input data we use the numbers will differ.
		 * 
		 * How you do the count is up to you. You can make a method, use the searchForBooks()
		 * function or just do the count right here.
		 */
		int totalBooks = searchForBook(x -> x.getGenre().equals("Adventure")).size() +
				searchForBook(x -> x.getGenre().equals("Fiction")).size() +
				searchForBook(x -> x.getGenre().equals("Classics")).size() +
				searchForBook(x -> x.getGenre().equals("Mystery")).size() +
				searchForBook(x -> x.getGenre().equals("Science Fiction")).size();
		output += "Adventure\t\t\t\t\t" + (searchForBook(x -> x.getGenre().equals("Adventure")).size()) + "\n";
		output += "Fiction\t\t\t\t\t\t" + (searchForBook(x -> x.getGenre().equals("Fiction")).size()) + "\n";
		output += "Classics\t\t\t\t\t" + (searchForBook(x -> x.getGenre().equals("Classics")).size()) + "\n";
		output += "Mystery\t\t\t\t\t\t" + (searchForBook(x -> x.getGenre().equals("Mystery")).size()) + "\n";
		output += "Science Fiction\t\t\t\t\t" + (searchForBook(x -> x.getGenre().equals("Science Fiction")).size()) + "\n";
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (totalBooks) + "\n\n";
		
		/*
		 * This part prints the books that are currently checked out
		 */
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		/*
		 * Here you will print each individual book that is checked out.
		 * 
		 * Remember that the book has a toString() method. 
		 * Notice if it was implemented correctly it should print the books in the 
		 * expected format.
		 * 
		 * PLACE CODE HERE
		 */
		List<Book> checkedOutBooks = searchForBook(x -> x.isCheckedOut());
		for (Book e : checkedOutBooks) {
			output += e.toString() + "\n";
		}
		
		
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (checkedOutBooks.size()) + "\n\n";
		
		
		/*
		 * Here we will print the users the owe money.
		 */
		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";
		/*
		 * Here you will print all the users that owe money.
		 * The amount will be calculating taking into account 
		 * all the books that have late fees.
		 * 
		 * For example if user Jane Doe has 3 books and 2 of them have late fees.
		 * Say book 1 has $10 in fees and book 2 has $78 in fees.
		 * 
		 * You would print: Jane Doe\t\t\t\t\t$88.00
		 * 
		 * Notice that we place 5 tabs between the name and fee and 
		 * the fee should have 2 decimal places.
		 * 
		 * PLACE CODE HERE!
		 */
		List<User> owingUsers = searchForUsers(x -> x.getCheckedOutList().size() > 0);
		float totalFees = 0;
		for (User e : owingUsers) {
			float fees = 0;
			for (Book b : e.getCheckedOutList()){
				fees += b.calculateFees();
			}
			totalFees += fees;
			
			output += e.getName() + "\t\t\t\t\t$" + String.format("%.2f", fees) + "\n";
		}

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + (String.format("%.2f", totalFees)) + "\n\n\n";
		output += "\n\n";
		//System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		/*
		 * Here we will write to the file.
		 * 
		 * The variable output has all the content we need to write to the report file.
		 * 
		 * PLACE CODE HERE!!
		 */
		var writer = new BufferedWriter(new FileWriter("report/report.txt"));
		writer.write(output);
		writer.close();
	}
	
	/*
	 * BONUS Methods
	 * 
	 * You are not required to implement these, but they can be useful for
	 * other parts of the project.
	 */
	/**
	 * Functional method to filter the catalog's books to match
	 * a parameter lambda function
	 * @param func Lambda function to evaluate the books in the library's
	 * catalog
	 * @return A List that contains the books that comply with the given
	 * lambda function
	 */
	public List<Book> searchForBook(FilterFunction<Book> func) {
		List<Book> toReturn = new SinglyLinkedList<Book>();
		for (Book e : catalog) {
			if (func.filter(e)) {
				//Here I am adding at the end so that the generated report matches
				//the expected report
				toReturn.add(e);
			}
		}
		return toReturn;
	}
	/**
	 * Functional method to filter the library's users to match
	 * a parameter lambda function
	 * @param func Lambda function to evaluate the clients in the library's
	 * user list
	 * @return A List that contains the users that comply with the given
	 * lambda function
	 */
	public List<User> searchForUsers(FilterFunction<User> func) {
		List<User> toReturn = new SinglyLinkedList<User>();
		for (User e : users) {
			if (func.filter(e)) {
				//Here I am adding at the end so that the generated report matches
				//the expected report
				toReturn.add(e);
			}
		}
		return toReturn;
	}
	
}
