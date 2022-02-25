package com.test.app.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	
	// pjp=new PJP("현재 실행하는 비즈니스 메서드 정보");
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("[비즈니스 메서드 수행전]");
		
		StopWatch sw=new StopWatch();
		sw.start();
		Object obj=pjp.proceed(); // 비즈니스 메서드를 탈취
		sw.stop();
		
		System.out.println("[비즈니스 메서드 수행후]");
		
		System.out.println(pjp.getSignature().getName()+"() 비즈니스메서드 실행에 소요된 시간은");
		System.out.println(sw.getTotalTimeMillis()+"입니다.");
		
		return obj;
	}
}
