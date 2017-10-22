package com.ofir.model;

public class User
{
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	public User()
	{
		
	}
	
	public User(String email, String firstName, String lastName, String password)
	{
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public int getId()
	{
		return id;
	}

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
 
	public boolean xssProofing(){
		String[] specialText = {"<", ">","\'","&","\""};
		for (String txt : specialText) {
			if(this.firstName.contains(txt) || this.lastName.contains(txt) || this.email.contains(txt) || this.password.contains(txt)){
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}
}
