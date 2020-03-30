package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.ShapeService;

public class AopMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService = context.getBean("shapeService", ShapeService.class);
		System.out.println(shapeService.getCircle().getName());
		System.out.println("");
		System.out.println(shapeService.getTriangle().getName());
		System.out.println("");
		shapeService.getCircle().setName("dummy name");
		System.out.println("");
		shapeService.getCircle().exampleMethodWithArgs("success");
		System.out.println("");
		shapeService.getCircle().exampleMethodWithArgs("don't throw exception");
		System.out.println("");
		shapeService.getCircle().exampleMethodWithArgsReturningString("input");
		System.out.println("");
		shapeService.getCircle().getMethodExample();
		System.out.println("");
		shapeService.getCircle().exampleMethodWithArgs("throw");
	}

}
