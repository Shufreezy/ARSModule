package ph.com.alliance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Aircon")
public class Aircon {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	private String name;
	private String description;
	private boolean deleted_flag;
	
	public Aircon() {
		super();
	}
	public Aircon(String name) {
		super();
		this.name = name;
	}
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
	
	
	
	

}
