package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import pojo.Circle;

@Aspect
public class LoggingAspect {
	
	@Before("execution(public String getName())")
	public void LoggingAdvice(){
		System.out.println("Advice run, Get Name Method called");
	}
	
	@Before("execution(public String pojo.Circle.getName())")
	public void LoggingAdviceForCircleOnly(){
		System.out.println("Advice run, Get Method called for Circle");
	}
	
	@Before("allGetters()")
	public void FirstLoggingAdviceForGetMethod(){
		System.out.println("First Advice run, Get Method called");
	}
	
	@Before("allGetters()")
	public void SecondLoggingAdviceForGetMethod(){
		System.out.println("Second Advice run, Get Method called");
	}
	
	@Before("allGetters() && allCircleMethods()")
	public void AdviceForGetMethodAndForAllCircleMethods(){
		System.out.println("Get Method called && all circle methods");
	}
	
	@Before("allCircleMethods()")
	public void AdviceForAllCircleMethodsWithJoinPoint(JoinPoint joinPoint){
		System.out.println(joinPoint.toString());
		Circle circle = (Circle)joinPoint.getTarget();
		System.out.println("Join Point " + circle.getName());
	}
	
	@Before("args(something)")
	public void AdviceForStringArgumentsMethods(String something){
		System.out.println("Value of String is " + something);
	}
	
	@Pointcut("execution(public * get*())")
	public void allGetters(){}
	
	@Pointcut("within(pojo.Circle)")
	public void allCircleMethods(){}
}
