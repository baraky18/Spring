package pojo;

public class Circle {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void exampleMethodWithArgs(String s){
		System.out.println("the arg is " + s);
		if("throw".equalsIgnoreCase(s)){
			throw(new RuntimeException());
		}
	}
	
	public String exampleMethodWithArgsReturningString(String s){
		System.out.println("the arg is " + s);
		return "return value";
	}
	
	public String getMethodExample(){
		throw(new RuntimeException());
	}
}
