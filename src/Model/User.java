package Model;

import java.util.Date;

public class User {
	private String userName;
	private Date userDate;
	private String userPhone;
	private String userRole;
	private String userPassword;
	
	public User(String userName, Date userDate, String userPhone, String userRole, String userPassword) {
		super();
		this.userName = userName;
		this.userDate = userDate;
		this.userPhone = userPhone;
		this.userRole = userRole;
		this.userPassword = userPassword;
	}
	
	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
}
