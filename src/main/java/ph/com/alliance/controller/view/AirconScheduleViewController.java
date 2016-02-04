package ph.com.alliance.controller.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import ph.com.alliance.model.AirconModel;
import ph.com.alliance.model.SpecificScheduleModel;
import ph.com.alliance.service.impl.AirconScheduleServiceImpl;

@Controller
@RequestMapping("/AirconSchedule")
public class AirconScheduleViewController {
	public static final String folderView = "/AirconScheduling_View";
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	@Autowired
	AirconScheduleServiceImpl airconScheduleService;
	
	public static final String SEARCH_TYPE_DEFAULT = "Aircon Name";
	public static final String SEARCH_TYPE_DATE = "Date";
	public static final String SEARCH_TYPE_TIME = "Time";
	private static final String timeFormat = "HH:mm";
	private static final String dateFormat = "yyyy-mm-dd";
	@RequestMapping(method = RequestMethod.GET)
	public String indexPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		
		List<AirconModel> allAircon = new ArrayList<AirconModel>();
		for(Aircon ac : airconScheduleService.getAlleAircon()){
			allAircon.add(this.convertToModel(ac));
		}
		map.addAttribute("airconList", allAircon);
		String searchType = request.getParameter("searchType");
		List<SpecificScheduleModel> allSchedules = new ArrayList<SpecificScheduleModel>();
		if(searchType!=null ){
				if(searchType.equals(SEARCH_TYPE_DEFAULT)){
					String name = request.getParameter("airconName");
					if(name!=null && name.length()>0){
						for(SpecificSchedule ss : airconScheduleService.getAllAirconScheduleByAirconName(name)){
							allSchedules.add(this.convertToModel(ss));
						}
					}
					else for(SpecificSchedule ss : airconScheduleService.getAllAirconSchedule()){ allSchedules.add(this.convertToModel(ss));}
				}
				else if(searchType.equals(SEARCH_TYPE_DATE)){
					String date  = request.getParameter("date");
					if(date!=null && date.length()>0){
						try {
							for(SpecificSchedule ss : airconScheduleService.getAllAirconScheduleByDate(parseDate(date))){
								allSchedules.add(this.convertToModel(ss));
							}
						} catch (ParseException e) {
						}
					}
					else for(SpecificSchedule ss : airconScheduleService.getAllAirconSchedule()){ allSchedules.add(this.convertToModel(ss));}
				}
				else if(searchType.equals(SEARCH_TYPE_TIME)){
					String time_start = request.getParameter("time_start");
					String time_end = request.getParameter("time_end");
					if(time_start!=null && time_end!=null && time_start.length()>0 && time_end.length()>0){
						try {
							for(SpecificSchedule ss : airconScheduleService.getAllAirconScheduleByTime(parseTime(time_start), parseTime(time_end))){
								allSchedules.add(this.convertToModel(ss));
							}
						} catch (ParseException e) {
						}
					}
					else for(SpecificSchedule ss : airconScheduleService.getAllAirconSchedule()){ allSchedules.add(this.convertToModel(ss));}
				}
		}
		
		if(searchType==null){
			searchType = SEARCH_TYPE_DEFAULT;
			for(SpecificSchedule ss : airconScheduleService.getAllAirconSchedule()){ allSchedules.add(this.convertToModel(ss));}
		}
		map.addAttribute("searchType",searchType);
		for(SpecificScheduleModel model: allSchedules){
			Aircon ac = new Aircon();
			ac.setId(model.getAircon_id());
			ac = airconScheduleService.getAircon(ac);
			if(ac!=null)
				model.setAircon_name(ac.getName());
		}
		map.addAttribute("scheduleList", allSchedules);
		String error = request.getParameter("error");
		if(error!=null){
			map.addAttribute("error",error);
		}
		return folderView+"/index";
	}
	
	
	
	@RequestMapping(value="edit",method = RequestMethod.GET)
	public String editPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		List<AirconModel> allAircon = new ArrayList<AirconModel>();
		for(Aircon ac : airconScheduleService.getAlleAircon()){
			allAircon.add(this.convertToModel(ac));
		}
		map.addAttribute("airconList", allAircon);
		SpecificSchedule schedule = new SpecificSchedule();
		schedule.setId(Integer.parseInt(request.getParameter("id")));
		SpecificScheduleModel model = this.convertToModel(airconScheduleService.getAirconSchedule(schedule));
		
		Aircon ac = new Aircon();
		ac.setId(model.getAircon_id());
		model.setAircon_name(airconScheduleService.getAircon(ac).getName());
		map.addAttribute("model",model);
		return folderView+"/edit";
		
	}
	
	@RequestMapping(value="addNewSchedule",method = RequestMethod.GET)
	public String registerPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){

		
		List<AirconModel> allAircon = new ArrayList<AirconModel>();
		for(Aircon ac : airconScheduleService.getAlleAircon()){
			allAircon.add(this.convertToModel(ac));
		}
		map.addAttribute("airconList", allAircon);
		return folderView+"/register";
		
	}
	
	
	@RequestMapping("/editAirConditioner")
	public String editAirconPage(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException{
		String id = request.getParameter("id");
		if(id==null){
			response.sendRedirect(request.getContextPath()+"/AirconSchedule");
		}
		Aircon aircon = new Aircon();
		AirconModel model = new AirconModel();
		aircon.setId(Integer.parseInt(id));
		model = this.convertToModel(airconScheduleService.getAircon(aircon));
		map.addAttribute("model",model);
		return folderView+"/edit_aircon";
	}
	
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
	 private AirconModel convertToModel (Aircon aircon) { 	
	    	AirconModel userModel = null;
	    	
	    	if (aircon != null) {
	    		userModel = dozerBeanMapper.map(aircon, AirconModel.class);
	    	} 
	    	
	    	return userModel;
	    }
	
}
