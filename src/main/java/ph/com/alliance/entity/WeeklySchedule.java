package ph.com.alliance.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="WeeklySchedule")
public class WeeklySchedule {

	@Id
	@Column(name="id")
	private int id;
	private int aircon_id;
	private Date date;
	private Time time_start;
	private Time time_end;
	private boolean delete_flag;
	private Date modified_date;
	private int modified_by;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAircon_id() {
		return aircon_id;
	}
	public void setAircon_id(int aircon_id) {
		this.aircon_id = aircon_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime_start() {
		return time_start;
	}
	public void setTime_start(Time time_start) {
		this.time_start = time_start;
	}
	public Time getTime_end() {
		return time_end;
	}
	public void setTime_end(Time time_end) {
		this.time_end = time_end;
	}
	public boolean isDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	
	
}
