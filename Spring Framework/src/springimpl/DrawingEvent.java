package springimpl;

import org.springframework.context.ApplicationEvent;

public class DrawingEvent extends ApplicationEvent {

	public DrawingEvent(Object source) {
		super(source);
	}
	
	public String toString(){
		return "Draw event occured";
	}
}
