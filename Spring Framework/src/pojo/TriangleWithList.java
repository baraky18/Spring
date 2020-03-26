package pojo;

import java.util.List;

public class TriangleWithList implements Shape{

	private List<Point> points;
	
	@Override
	public void draw(){
		for(Point point: points){
			System.out.println("Point is: (" + point.getX() + "," + point.getY() + ")");
		}
		System.out.println("Triangle drawn!");
	}


	public List<Point> getPoints() {
		return points;
	}


	public void setPoints(List<Point> points) {
		this.points = points;
	}

}
