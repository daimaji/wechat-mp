package com.agjiapp.wechat.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class Logs {

	private static final Logger log = Logger.getLogger(Logs.class);

	@Pointcut("execution(* com.agjiapp.wechat.controller.*.*(..))")
	public void logs() {
	}

	@Around("logs()")
	public Object recordLogs(ProceedingJoinPoint proceedingJoinPoint) {
		String className = proceedingJoinPoint.getTarget().getClass()
				.getSimpleName();
		String methodName = proceedingJoinPoint.getSignature().getName();
		try {
			log.info("类:" + className + ",方法:" + methodName + " 开始执行.");
			Object retValue = proceedingJoinPoint.proceed();
			log.info("类:" + className + ",方法:" + methodName + " 执行结束.");
			return retValue;
		} catch (Throwable t) {
			log.error("类:" + className + ",方法:" + methodName + " 发生错误.", t);
			return "error";
		}
	}

}
