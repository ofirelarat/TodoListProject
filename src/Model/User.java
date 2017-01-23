package Model;

/**
 * 
 * @author Ofir & Chen
 * This class holds the USER parameters and his methods.
 *
 */
public class User
{
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	/**
	 * Default constructor
	 */
	public User()
	{
		
	}
	
	/**
	 * Constructor using fields
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param password
	 */
	public User(String email, String firstName, String lastName, String password)
	{
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	/**
	 * 
	 * @return id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * 
	 * @return the email of the user
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * 
	 * @return first name of the user
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * 
	 * @param firstName
	 */
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return last name of the user
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	/**
	 * 
	 * @return the password of the user
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * to string method
	 */
	@Override
	public String toString()
	{
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}
}
