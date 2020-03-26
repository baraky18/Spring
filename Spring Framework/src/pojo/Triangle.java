package pojo;

import org.springframework.beans.factory.annotation.Autowired;

public class Triangle implements Shape{

	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	@Override
	public void draw(){
		System.out.println("Point A is: (" + pointA.getX() + "," + pointA.getY() + ")");
		System.out.println("Point B is: (" + pointB.getX() + "," + pointB.getY() + ")");
		System.out.println("Point B is: (" + pointC.getX() + "," + pointC.getY() + ")");
		System.out.println("Triangle drawn!");
	}

	public void init(){
		System.out.println("initializing Triangle");
	}
	
	public void destroy(){
		System.out.println("destroying Triangle");
	}

	public Point getPointA() {
		return pointA;
	}

	@Autowired
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}


	public Point getPointB() {
		return pointB;
	}

	@Autowired
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}


	public Point getPointC() {
		return pointC;
	}


	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
}
