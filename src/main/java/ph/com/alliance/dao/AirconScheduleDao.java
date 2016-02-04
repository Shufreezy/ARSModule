package ph.com.alliance.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;

import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.entity.WeeklySchedule;

public interface AirconScheduleDao {
	// AIRCON CRUD
		public boolean addAircon(EntityManager manager,Aircon aircon);
		public boolean editAircon(EntityManager manager,Aircon aircon);
		public Aircon getAircon(EntityManager manager,Aircon aircon);
		public List<Aircon> getAllAircon(EntityManager manager); 
		public boolean existsAircon(EntityManager manager,Aircon aircon);
		
		// SPECIFIC SCHEDULE CRUD
		public boolean addSpecificSchedule(EntityManager manager,SpecificSchedule schedule);
		public boolean editSpecificSchedule(EntityManager manager,SpecificSchedule aircon);
		public SpecificSchedule getSpecificSchedule(EntityManager manager,SpecificSchedule aircon);
		public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager,int aircon_id);
		public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager,Date date);
		public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager,int aircon_id,Date date);
		public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager,Time start,Time end);
		public boolean existsSpecificSchedule(EntityManager manager,SpecificSchedule aircon);
		
		public boolean addWeeklySchedule(EntityManager manager,WeeklySchedule schedule);
		public boolean editWeeklySchedule(EntityManager manager,WeeklySchedule aircon);
		public WeeklySchedule getWeeklySchedule(EntityManager manager,WeeklySchedule aircon);
		public boolean existsWeeklySchedule(EntityManager manager,WeeklySchedule aircon);
		public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager);
		
}
