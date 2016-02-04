package ph.com.alliance.model;


public class AirconModel {
	private int id;
	private String name;
	private String description;
	private boolean deleted_flag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDeleted_flag() {
		return deleted_flag;
	}
	public void setDeleted_flag(boolean deleted_flag) {
		this.deleted_flag = deleted_flag;
	}
	@Override
	public String toString() {
		return "AirconModel [id=" + id + ", name=" + name + ", description=" + description + ", deleted_flag="
				+ deleted_flag + "]";
	}
	
}
