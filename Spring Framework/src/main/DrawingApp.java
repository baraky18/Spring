package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.Square;
import pojo.Triangle;
import pojo.TriangleWithList;

public class DrawingApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Triangle triangle = (Triangle)context.getBean("triangle");
		triangle.draw();
		
		TriangleWithList inheritedListTriangle = (TriangleWithList)context.getBean("inheritedlisttriangle");
		inheritedListTriangle.draw();
		
		Square square = (Square)context.getBean("square");
		square.draw();
		
		Square inheritedSquare = (Square)context.getBean("inheritedsquare");
		inheritedSquare.draw();
	}

}
