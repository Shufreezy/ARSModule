package ph.com.alliance.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ph.com.alliance.dao.AirconScheduleDao;
import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.entity.WeeklySchedule;

@Repository("airconScheduleDao")
public class AirconScheduleDaoImpl implements AirconScheduleDao {

	@Override
	public boolean addAircon(EntityManager manager, Aircon aircon) {
		boolean result = false; 
		try{
			manager.persist(aircon);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editAircon(EntityManager manager, Aircon aircon) {
		boolean result = false; 
		try{
			manager.merge(aircon);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Aircon getAircon(EntityManager manager, Aircon aircon) {
		Aircon result = null; 
		try{
			String hql;
			Query query = null;
			if(aircon.getId()>0){
				hql = "Select ac FROM Aircon ac where ac.id = :id AND deleted_flag= :deleted";
				query= manager.createQuery(hql);
				query.setParameter("id", aircon.getId()).setParameter("deleted", false);
			}
			else if(aircon.getName() !=null){
				hql = "Select ac FROM Aircon ac where ac.name = :airconName AND deleted_flag= :deleted";
				query= manager.createQuery(hql);
				query.setParameter("airconName", aircon.getName()).setParameter("deleted", false);
			}
			result = (Aircon)query.getSingleResult();
			
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Aircon> getAllAircon(EntityManager manager) {
		String hql = "Select ac from Aircon ac where deleted_flag= :deleted";
		return manager.createQuery(hql).setParameter("deleted", false).getResultList();
	}
	@Override
	public boolean existsAircon(EntityManager manager, Aircon aircon) {
		return getAircon(manager,aircon)!=null;
	}

	@Override
	public boolean addSpecificSchedule(EntityManager manager, SpecificSchedule schedule) {
		boolean result = false; 
		try{
			manager.persist(schedule);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editSpecificSchedule(EntityManager manager, SpecificSchedule schedule) {
		
		boolean result = false; 
		try{
			manager.merge(schedule);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public SpecificSchedule getSpecificSchedule(EntityManager manager, SpecificSchedule schedule) {
		SpecificSchedule result = null; 
		try{
			if(schedule.getId()>0){
				String hql = "Select ss FROM SpecificSchedule ss where ss.id = :ID";
				Query query = manager.createQuery(hql);
				query.setParameter("ID", schedule.getId());
				result = (SpecificSchedule)query.getSingleResult();
			}
			else{
				String hql = "Select ss FROM SpecificSchedule ss where ss.aircon_id = :airconId AND ss.date = :date AND ss.time_start = :start AND ss.time_end = :end";
				Query query = manager.createQuery(hql);
				query.setParameter("airconId", schedule.getAircon_id())
					 .setParameter("date", schedule.getDate())
					 .setParameter("start", schedule.getTime_start())
					 .setParameter("end", schedule.getTime_end());
				result = (SpecificSchedule)query.getSingleResult();
			}
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager,int aircon_id){
		List<SpecificSchedule> result = null; 
		try{
			String hql = "Select ss FROM SpecificSchedule ss where ss.aircon_id = :airconId";
			Query query = manager.createQuery(hql);
			result = query.setParameter("airconId", aircon_id).getResultList();
			//result = manager.find(SpecificSchedule.class, schedule.getId());
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager, Date date) {
		List<SpecificSchedule> result = null; 
		try{
			String hql = "Select ss FROM SpecificSchedule ss where ss.date = :date";
			Query query = manager.createQuery(hql);
			result = query.setParameter("date", date).getResultList();
			//result = manager.find(SpecificSchedule.class, schedule.getId());
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager, Time start, Time end) {
		List<SpecificSchedule> result = null; 
		try{
			String hql = "Select ss FROM SpecificSchedule ss where ss.time_start = :start AND ss.time_end = :end";
			Query query = manager.createQuery(hql);
			query.setParameter("start", start)
				 .setParameter("end", end);
			result = query.getResultList();
			//result = manager.find(SpecificSchedule.class, schedule.getId());
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean existsSpecificSchedule(EntityManager manager, SpecificSchedule schedule) {
		
		return getSpecificSchedule(manager,schedule)!=null;
	}

	@Override
	public boolean addWeeklySchedule(EntityManager manager, WeeklySchedule schedule) {
		
		boolean result = false; 
		try{
			manager.persist(schedule);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editWeeklySchedule(EntityManager manager, WeeklySchedule schedule) {
		
		boolean result = false; 
		try{
			manager.merge(schedule);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public WeeklySchedule getWeeklySchedule(EntityManager manager, WeeklySchedule schedule) {
		WeeklySchedule result = null; 
		try{
			result = manager.find(WeeklySchedule.class, schedule.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean existsWeeklySchedule(EntityManager manager, WeeklySchedule schedule) {
		
		return getWeeklySchedule(manager,schedule)!=null;
	}

	@Override
	public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager) {
		List<SpecificSchedule> result = null; 
		try{
			String hql = "Select ss FROM SpecificSchedule ss";
			Query query = manager.createQuery(hql);
			result = query.getResultList();
			//result = manager.find(SpecificSchedule.class, schedule.getId());
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<SpecificSchedule> getAllSpecificSchedule(EntityManager manager, int aircon_id, Date date) {
		List<SpecificSchedule> result = null; 
		try{
			String hql = "Select ss FROM SpecificSchedule ss where ss.date = :date AND ss.aircon_id = :airconId";
			Query query = manager.createQuery(hql);
			result = query.setParameter("date", date).setParameter("airconId", aircon_id).getResultList();
			//result = manager.find(SpecificSchedule.class, schedule.getId());
		}catch(NoResultException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	
}
