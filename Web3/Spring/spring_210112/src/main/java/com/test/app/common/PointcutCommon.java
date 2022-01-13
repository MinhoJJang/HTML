package com.test.app.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // 횡단관심과 "결합"될때 불리는 포인트컷클래스
public class PointcutCommon {
	@Pointcut("execution(* com.test.app..*Impl.*(..))")
	public void aPointcut() {}
	
	@Pointcut("execution(* com.test.app..*Impl.select*(..))")
	public void bPointcut() {}
}
