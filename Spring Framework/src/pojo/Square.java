package pojo;

public class Square implements Shape{

	private Point pointA;
	private Point pointB;
	private Point pointC;
	private Point pointD;
	
	@Override
	public void draw(){
		System.out.println("Point A is: (" + pointA.getX() + "," + pointA.getY() + ")");
		System.out.println("Point B is: (" + pointB.getX() + "," + pointB.getY() + ")");
		System.out.println("Point C is: (" + pointC.getX() + "," + pointC.getY() + ")");
		System.out.println("Point D is: (" + pointD.getX() + "," + pointD.getY() + ")");
		System.out.println("Square drawn!");
	}


	public Point getPointA() {
		return pointA;
	}


	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}


	public Point getPointB() {
		return pointB;
	}


	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}


	public Point getPointC() {
		return pointC;
	}


	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
	
	public Point getPointD() {
		return pointD;
	}


	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}
}
