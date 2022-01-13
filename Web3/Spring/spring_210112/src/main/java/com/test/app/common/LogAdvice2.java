package com.test.app.common;

import org.aspectj.lang.JoinPoint;

public class LogAdvice2 {
	public void printLog2(JoinPoint jp) {
		Object[] args=jp.getArgs();
		System.out.println("매개변수에 활용된 데이터: "+args[0]);
	}
}
