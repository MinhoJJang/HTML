package com.test.app.common;

public class AfterAdvice {
	public void finallyLog() {
		System.out.println("비즈니스 메서드 수행후에 무조건 출력됨!");
	}
}
