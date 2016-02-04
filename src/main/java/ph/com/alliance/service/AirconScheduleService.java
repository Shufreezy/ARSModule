package ph.com.alliance.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.exception.ConflictException;

public interface AirconScheduleService {
	
	public boolean createAircon(Aircon aircon);
	public boolean updateAircon(Aircon aircon);
	public boolean deleteAircon(Aircon aircon);
	public Aircon getAircon(Aircon aircon);
	public List<Aircon> getAlleAircon();
	
	public boolean createAirconSchedule(SpecificSchedule schedule) throws ConflictException;
	public boolean updateAirconSchedule(SpecificSchedule schedule) throws ConflictException;
	public boolean deleteAirconSchedule(SpecificSchedule schedule);
	public SpecificSchedule getAirconSchedule(SpecificSchedule schedule);
	public List<SpecificSchedule> getAllAirconScheduleByAirconName(String name);
	public List<SpecificSchedule> getAllAirconScheduleByDate(Date date);
	public List<SpecificSchedule> getAllAirconScheduleByTime(Time start,Time end);
	public void hasConflict(SpecificSchedule schedule1) throws ConflictException;
	public List<SpecificSchedule> getAllAirconSchedule();
	
}
