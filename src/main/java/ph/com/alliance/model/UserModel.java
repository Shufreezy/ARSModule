package ph.com.alliance.model;

public class UserModel {
	
	private String uid;
	private String fname;
	private String lname;
	private String gender;
	private int age;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
    public String toString() {
		return "User {" + "userID=" + uid + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", age=" + age + '}';
    } 
}
