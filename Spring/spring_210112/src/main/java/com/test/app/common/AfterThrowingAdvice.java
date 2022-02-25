package com.test.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {	
	@AfterThrowing(pointcut="PointcutCommon.aPointcut()",throwing="excep")
	public void exceptionLog(JoinPoint jp,Exception excep) { // 바인드 변수
		String coreMethod=jp.getSignature().getName();
		System.out.println("비즈니스 메서드 "+coreMethod+"() 수행중에 문제,예외 발생시 출력");
		System.out.println("예외메세지: "+excep.getMessage());
		
		if(excep instanceof IllegalArgumentException) {
			System.out.println("사용하지않는 매개변수입니다.");
		}
		else if(excep instanceof NullPointerException) {
			System.out.println("객체생성 실패했습니다.");
		}
		else {
			System.out.println("현재 예외는 파악되지않은 예외입니다! 분석이 필요합니다!");
		}
	}
}
