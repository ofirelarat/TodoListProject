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
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}
}