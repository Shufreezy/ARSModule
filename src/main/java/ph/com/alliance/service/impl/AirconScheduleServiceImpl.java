package ph.com.alliance.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import ph.com.alliance.dao.AirconScheduleDao;
import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.exception.ConflictException;
import ph.com.alliance.service.AirconScheduleService;

@Service("airconScheduleService")
public class AirconScheduleServiceImpl implements AirconScheduleService {

	@Autowired 
	private AirconScheduleDao airconScheduleDao;
	@Autowired
	private JpaTransactionManager transactionManager;
	
	
	
	public boolean createAirconSchedule(SpecificSchedule schedule) throws ConflictException {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			boolean exists = airconScheduleDao.existsSpecificSchedule(manager, schedule);
			if(!exists){
				hasConflict(schedule);
			 	java.util.Date today = new java.util.Date();
				schedule.setCreated_date(new java.sql.Date(today.getTime()));
				result = airconScheduleDao.addSpecificSchedule(manager, schedule);
				manager.getTransaction().commit();
			}
			
		
		} catch (Exception e) {
			if(e instanceof ConflictException){
				throw e;
			}
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public boolean updateAirconSchedule(SpecificSchedule schedule) throws ConflictException {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			if(airconScheduleDao.existsSpecificSchedule(manager, schedule)){
				hasConflict(schedule);
			 	java.util.Date today = new java.util.Date();
				schedule.setModified_date(new java.sql.Date(today.getTime()));
				result = airconScheduleDao.editSpecificSchedule(manager, schedule);
				manager.getTransaction().commit();
			}
			
			
		} catch (Exception e) {
			if(e instanceof ConflictException){
				throw e;
			}
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public boolean deleteAirconSchedule(SpecificSchedule schedule) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			if(airconScheduleDao.existsSpecificSchedule(manager, schedule)){
			 	java.util.Date today = new java.util.Date();
				schedule.setDelete_flag(true);
				result = airconScheduleDao.editSpecificSchedule(manager, schedule);
				manager.getTransaction().commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public SpecificSchedule getAirconSchedule(SpecificSchedule schedule) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getSpecificSchedule(manager, schedule);
	}

	@Override
	public List<SpecificSchedule> getAllAirconScheduleByAirconName(String name) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		Aircon result = airconScheduleDao.getAircon(manager,new Aircon(name));
		if(result!=null)
			return airconScheduleDao.getAllSpecificSchedule(manager,result.getId());
		else
			return null;
	}

	@Override
	public List<SpecificSchedule> getAllAirconScheduleByDate(Date date) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getAllSpecificSchedule(manager, date);
	}

	@Override
	public List<SpecificSchedule> getAllAirconScheduleByTime(Time start, Time end) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getAllSpecificSchedule(manager,start,end);
	}

	@Override
	public void hasConflict(SpecificSchedule schedule1) throws ConflictException {
		boolean ok = false;
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		List<SpecificSchedule> result = airconScheduleDao.getAllSpecificSchedule(manager, schedule1.getAircon_id(),schedule1.getDate());
		if(result!=null)
			for(SpecificSchedule schd : result){
				long s1 = schedule1.getTime_start().getTime();
				long e1 = schedule1.getTime_end().getTime();
				long s2 = schd.getTime_start().getTime();
				long e2 = schd.getTime_end().getTime();
				boolean cond1 = (s1 < e2);
				boolean cond2 = (e1 > s2);
				boolean cond3 = !schd.isDelete_flag();
				boolean cond4 = s1 == s2 || e1 == e2;
				ok =  ((cond1 && cond2) || cond4) && cond3;
				if(ok == true){
					throw new ConflictException(schedule1,schd);
				}	
				
			}
	}

	@Override
	public boolean createAircon(Aircon aircon) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			if(!airconScheduleDao.existsAircon(manager, aircon)){
			 	result = airconScheduleDao.addAircon(manager, aircon);
				manager.getTransaction().commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public boolean updateAircon(Aircon aircon) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			if(airconScheduleDao.existsAircon(manager, aircon)){
			 	result = airconScheduleDao.editAircon(manager, aircon);
				manager.getTransaction().commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public boolean deleteAircon(Aircon aircon) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		boolean result = false;
		manager.getTransaction().begin();
		try {
			if(airconScheduleDao.existsAircon(manager, aircon)){
				aircon = airconScheduleDao.getAircon(manager, aircon);
				if(!aircon.isDeleted_flag()){
					aircon.setDeleted_flag(true);
				 	result = airconScheduleDao.editAircon(manager, aircon);
					manager.getTransaction().commit();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
		return result;
	}

	@Override
	public Aircon getAircon(Aircon aircon) {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getAircon(manager, aircon);
	}

	@Override
	public List<Aircon> getAlleAircon() {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getAllAircon(manager);
	}
	@Override
	public List<SpecificSchedule> getAllAirconSchedule() {
		EntityManager manager = transactionManager.getEntityManagerFactory().createEntityManager();
		return airconScheduleDao.getAllSpecificSchedule(manager);
	}

}
