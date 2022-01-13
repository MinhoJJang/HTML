package com.test.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service // 1. @ 스캔범위에 추가
@Aspect // 4. 애스팩트 설정 -> 실시간 위빙처리
public class LogAdvice {
	
	// 2. 포인트컷 설정
	@Pointcut("execution(* com.test.app..*Impl.select*(..))")
	public void bPointcut() {}
	
	// JP: 스프링 컨테이너가 new JP() (==jp객체생성)
	// 현재 수행되는 비즈니스메서드 정보를 셋팅!
	
	// 3. 어드바이스 설정 + 동작시점
	@Before("bPointcut()")
	public void printLog(JoinPoint jp) {
		System.out.println("JP실습중...");
		String coreMethod=jp.getSignature().getName(); // 메서드 시그니쳐
		System.out.println("처리중인 비즈니스 메서드명: "+coreMethod);
		Object[] args=jp.getArgs();
		System.out.println("args들: ");
		for(Object v:args) {
			System.out.print(v+" ");
		}
		System.out.println();
	}
}
