package ph.com.alliance.exception;

import java.text.SimpleDateFormat;

import ph.com.alliance.entity.SpecificSchedule;

public class ConflictException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpecificSchedule sched1;
	private SpecificSchedule sched2;

	public ConflictException(SpecificSchedule sched1, SpecificSchedule sched2) {
		super();
		this.sched1 = sched1;
		this.sched2 = sched2;
		
	}

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		SimpleDateFormat dtformat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm aa");
		return "Conflict to: "+dtformat.format(sched1.getDate())+ "[" +
				timeFormat.format(sched1.getTime_start())+"-"+ timeFormat.format(sched1.getTime_end())
				+" to "+
				timeFormat.format(sched2.getTime_start())+"-"+ timeFormat.format(sched2.getTime_end()) + "]";
	}
	
	
	
	
	

}
