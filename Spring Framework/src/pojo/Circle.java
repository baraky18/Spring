package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Component;

import springimpl.DrawingEvent;

//@Component - this annotation is replacing the definition in spring.xml
public class Circle implements Shape, ApplicationEventPublisherAware {

	private Point center;
	
	@Autowired
	private MessageSource messageSource;

	private ApplicationEventPublisher publisher;
	
	@Override
	public void draw() {
		System.out.println(messageSource.getMessage("circle.point", new Object[] {center.getX(), center.getY()}, "Default point message", null));
		System.out.println("Circle drawn!");
		DrawingEvent drawingEvent = new DrawingEvent(this);
		publisher.publishEvent(drawingEvent);
	}

	public Point getCenter() {
		return center;
	}

	@Autowired //connects circle bean with its member of center point (without adding the property to the circle bean in spring.xml)
	@Qualifier("center") //when there are multiple points in spring.xml, it qualifies the correct point to circle
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}
