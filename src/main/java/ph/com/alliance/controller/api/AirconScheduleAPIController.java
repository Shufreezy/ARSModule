package ph.com.alliance.controller.api;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.entity.User;
import ph.com.alliance.exception.ConflictException;
import ph.com.alliance.model.AirconModel;
import ph.com.alliance.model.SpecificScheduleModel;
import ph.com.alliance.model.UserModel;
import ph.com.alliance.service.impl.AirconScheduleServiceImpl;

@Controller
@RequestMapping("/aircon_schedule")
public class AirconScheduleAPIController {
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	@Autowired
	AirconScheduleServiceImpl airconScheduleService;
	
	private static final String timeFormat = "HH:mm";
	private static final String dateFormat = "yyyy-mm-dd";
	
	@RequestMapping(value = "/createNewSchedule", method = RequestMethod.POST)
	public void createSchedule(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException, ParseException{
		int aircon_id =  Integer.parseInt(req.getParameter("aircon_id"));
		System.out.println(req.getParameter("date"));
		Date date = parseDate(req.getParameter("date"));
		Time start =parseTime(req.getParameter("start"));
		Time end = parseTime(req.getParameter("end"));
		//int created_by = Integer.parseInt(req.getParameter("created_by"));
		SpecificScheduleModel model = new SpecificScheduleModel();
		model.setAircon_id(aircon_id);
		model.setDate(date);
		model.setTime_start(start);
		model.setTime_end(end);
		//model.setCreated_by(created_by);
		//response.getWriter().write(model.toString() + " RECEIVED"+"\n RESULT="+);
		boolean success = false;
		try {
			if(model.getTime_start().getTime() >=  model.getTime_end().getTime())
				throw new Exception("Invalid time input");
			  success = airconScheduleService.createAirconSchedule(this.convertToEntity(model));
			  response.sendRedirect(req.getContextPath()+"/AirconSchedule");
		} catch (Exception e) {
			String message = e.getMessage();
			message = message.replace(" ","%20");
			response.sendRedirect(req.getContextPath()+"/AirconSchedule?error="+message);
		}
	}
	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
	public void updateSchedule(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException, ParseException{
		int schedule_id = Integer.parseInt(req.getParameter("id"));
		int aircon_id =  Integer.parseInt(req.getParameter("aircon_id"));
		System.out.println(req.getParameter("date"));
		Date date = parseDate(req.getParameter("date"));
		Time start =parseTime(req.getParameter("start"));
		Time end = parseTime(req.getParameter("end"));
		//int created_by = Integer.parseInt(req.getParameter("created_by"));
		SpecificScheduleModel model = new SpecificScheduleModel();
		model.setId(schedule_id);
		model.setAircon_id(aircon_id);
		model.setDate(date);
		model.setTime_start(start);
		model.setTime_end(end);
		//model.setCreated_by(created_by);
		//response.getWriter().write(model.toString() + " RECEIVED"+"\n RESULT="+);
		boolean success = false;
		try {
			if(model.getTime_start().getTime() >=  model.getTime_end().getTime())
				throw new Exception("Invalid time input");
			success = airconScheduleService.updateAirconSchedule(this.convertToEntity(model));
			response.sendRedirect(req.getContextPath()+"/AirconSchedule");
		} catch (Exception e) {
			String message = e.getMessage();
			message = message.replace(" ","%20");
			response.sendRedirect(req.getContextPath()+"/AirconSchedule?error="+message);
		}
		
		
	}
	@RequestMapping(value = "/deleteSchedule", method = RequestMethod.GET)
	public void deleteSchedule(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException, ParseException{
		int schedule_id = Integer.parseInt(req.getParameter("id"));
		SpecificScheduleModel model = new SpecificScheduleModel();
		model.setId(schedule_id);
		model = this.convertToModel(airconScheduleService.getAirconSchedule(this.convertToEntity(model)));
		boolean success = airconScheduleService.deleteAirconSchedule(this.convertToEntity(model));
		response.sendRedirect(req.getContextPath()+"/AirconSchedule");
		
	}
	
	@RequestMapping(value = "/getAllSchedules", method = RequestMethod.POST)
	public void getAllSchedules(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException{
		List<SpecificSchedule> all = airconScheduleService.getAllAirconScheduleByAirconName("Test");
		for(SpecificSchedule s:all){
			response.getWriter().write(this.convertToModel(s).toString()+"\n");
		}
	}
	
	/*
	 * Aircon CRUD Controllers
	 * 
	 * */
	@RequestMapping(value = "/addNewAircon", method = RequestMethod.POST)
	public void createAircon(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException{
		AirconModel model = new AirconModel();
		model.setName(req.getParameter("name"));
		model.setDescription(req.getParameter("description"));
		airconScheduleService.createAircon(this.convertToEntity(model));
		response.sendRedirect(req.getContextPath()+"/AirconSchedule");
		//response.getWriter().write("SUCCESSFUL:" + airconScheduleService.createAircon(this.convertToEntity(model)));
	}
	@RequestMapping(value = "/updateAircon", method = RequestMethod.POST)
	public void updateAircon(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException{
		AirconModel model = new AirconModel();
		String s = req.getParameter("id");
		model.setId(Integer.parseInt(s));
		model.setName(req.getParameter("name"));
		model.setDescription(req.getParameter("description"));
		airconScheduleService.updateAircon(this.convertToEntity(model));
		response.sendRedirect(req.getContextPath()+"/AirconSchedule");
		//response.getWriter().write("SUCCESSFUL:" + airconScheduleService.updateAircon(this.convertToEntity(model)));
	}
	@RequestMapping(value = "/deleteAircon", method = RequestMethod.GET)
	public void deleteAircon(HttpServletRequest req, HttpServletResponse response,ModelMap map) throws IOException{
		AirconModel model = new AirconModel();
		model.setId(Integer.parseInt(req.getParameter("id")));
		airconScheduleService.deleteAircon(this.convertToEntity(model));
		response.sendRedirect(req.getContextPath()+"/AirconSchedule");
	}
	
	/*
	 * 
	 * Helper Methods
	 * 
	 * 
	 * */
	private java.sql.Date parseDate(String str) throws ParseException{
		SimpleDateFormat dt = new SimpleDateFormat(dateFormat);
		return new java.sql.Date(dt.parse(str).getTime());
	}
	
	private java.sql.Time parseTime(String str) throws ParseException{
		SimpleDateFormat dt = new SimpleDateFormat(timeFormat);
		return new java.sql.Time(dt.parse(str).getTime());
	}
	
	private SpecificScheduleModel convertToModel (SpecificSchedule schedule) { 	
		SpecificScheduleModel userModel = null;
    	
    	if (schedule != null) {
    		userModel = dozerBeanMapper.map(schedule, SpecificScheduleModel.class);
    	} 
    	
    	return userModel;
    }
    
    /**
     * This is a sample object mapper.
     * Model to entity mapping can be explicitly done via setters, or
     * see convertToModel function for mapping using constructor
     * 
     * @param schedule
     * @return
     */
    private SpecificSchedule convertToEntity (SpecificScheduleModel schedule) {
    	SpecificSchedule u = null;
    	
    	if (schedule != null) {
    		u = dozerBeanMapper.map(schedule, SpecificSchedule.class);
    	}
    	
    	return u;
    }
    private AirconModel convertToModel (Aircon aircon) { 	
    	AirconModel userModel = null;
    	
    	if (aircon != null) {
    		userModel = dozerBeanMapper.map(aircon, AirconModel.class);
    	} 
    	
    	return userModel;
    }
    
    /**
     * This is a sample object mapper.
     * Model to entity mapping can be explicitly done via setters, or
     * see convertToModel function for mapping using constructor
     * 
     * @param schedule
     * @return
     */
    private Aircon convertToEntity (AirconModel aircon) {
    	Aircon u = null;
    	
    	if (aircon != null) {
    		u = dozerBeanMapper.map(aircon, Aircon.class);
    	}
    	
    	return u;
    }
	
    
}
