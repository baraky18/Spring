package main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.Shape;

public class DrawingApp {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		context.registerShutdownHook();//this is to call init and destroy methods of bean of type singleton only
		
		System.out.println(context.getMessage("greeting", null, "Default greeting", null));
		
		Shape shape1 = (Shape)context.getBean("triangle");
		shape1.draw();
		
		Shape shape2 = (Shape)context.getBean("inheritedlisttriangle");
		shape2.draw();
		
		Shape shape3 = (Shape)context.getBean("square");
		shape3.draw();
		
		Shape shape4 = (Shape)context.getBean("inheritedsquare");
		shape4.draw();
		
		Shape shape5 = (Shape)context.getBean("circle");
		shape5.draw();
		context.close();
	}

}
