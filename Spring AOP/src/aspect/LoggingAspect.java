package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import pojo.Circle;

@Aspect
public class LoggingAspect {
	
	@Before("execution(public String getName())")
	public void LoggingAdvice(){
		System.out.println("<LoggingAdvice> Advice run, Get Name Method called");
	}
	
	@Before("execution(public String pojo.Circle.getName())")
	public void LoggingAdviceForCircleOnly(){
		System.out.println("<LoggingAdviceForCircleOnly> Advice run, Get Method called for Circle");
	}
	
	@Before("allGetters()")
	public void FirstLoggingAdviceForGetMethod(){
		System.out.println("<FirstLoggingAdviceForGetMethod> First Advice run, Get Method called");
	}
	
	@Before("allGetters()")
	public void SecondLoggingAdviceForGetMethod(){
		System.out.println("<SecondLoggingAdviceForGetMethod> Second Advice run, Get Method called");
	}
	
	@Before("allGetters() && allCircleMethods()")
	public void AdviceForGetMethodAndForAllCircleMethods(){
		System.out.println("<AdviceForGetMethodAndForAllCircleMethods> Get Method called && all circle methods");
	}
	
	@Before("allCircleMethods()")
	public void AdviceForAllCircleMethodsWithJoinPoint(JoinPoint joinPoint){
		System.out.println("<AdviceForAllCircleMethodsWithJoinPoint> " + joinPoint.toString());
		Circle circle = (Circle)joinPoint.getTarget();
		System.out.println("<AdviceForAllCircleMethodsWithJoinPoint> Join Point " + circle.getName());
	}
	
	@Before("args(something)")
	public void AdviceForStringArgumentsMethods(String something){
		System.out.println("<AdviceForStringArgumentsMethods> Value of String is " + something);
	}
	
	@After("args(something)")
	public void AdviceForAfterStringArgumentsMethods(String something){
		System.out.println("<AdviceForAfterStringArgumentsMethods> Value of String is " + something);
	}
	
	@AfterReturning("args(something)")
	public void AdviceForAfterReturningStringArgumentsMethods(String something){
		System.out.println("<AdviceForAfterReturningStringArgumentsMethods> Value of String is " + something);
	}
	
	@AfterReturning(pointcut="args(something)", returning="returnString")
	public void AdviceForAfterReturningStringArgumentsMethodsWithReturn(String something, String returnString){
		System.out.println("<AdviceForAfterReturningStringArgumentsMethodsWithReturn> Value of String is " + something + " value of return is " + returnString);
	}
	
	@AfterThrowing("args(something)")
	public void AdviceForAfterThrowingStringArgumentsMethods(String something){
		System.out.println("<AdviceForAfterThrowingStringArgumentsMethods> Value of String is " + something);
	}
	
	@AfterThrowing(pointcut="args(something)", throwing="ex")
	public void AdviceForAfterThrowingStringArgumentsMethodsWithExceptionHandle(String something, Exception ex){
		System.out.println("<AdviceForAfterThrowingStringArgumentsMethodsWithExceptionHandle> Value of String is " + something + " exception is " + ex);
	}
	
	@Around("allGetters()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		Object returnValue = null;
		try {
			System.out.println("<aroundAdvice> before Advice");
			returnValue = proceedingJoinPoint.proceed();//this is the getter method that we put code around it
			System.out.println("<aroundAdvice> after Advice");
		} catch (Throwable e) {
			System.out.println("<aroundAdvice> after throwing Advice");
		}
		System.out.println("<aroundAdvice> after finally Advice");
		return returnValue;
	}
	
	@Pointcut("execution(public * get*())")
	public void allGetters(){}
	
	@Pointcut("within(pojo.Circle)")
	public void allCircleMethods(){}
}
