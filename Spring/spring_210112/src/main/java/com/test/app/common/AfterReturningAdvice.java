package com.test.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.test.app.board.BoardVO;
import com.test.app.member.MemberVO;

@Service
@Aspect
public class AfterReturningAdvice {
		
	@AfterReturning(pointcut="PointcutCommon.bPointcut()",returning="obj")
	public void afterLog(JoinPoint jp,Object obj) { // "바인드 변수"☆
		String coreMethod=jp.getSignature().getName();
		System.out.println("현재 수행중인 "+coreMethod+"()이후에 출력됨");
		if(obj instanceof MemberVO) {
			MemberVO vo=(MemberVO)obj; // 다운 캐스팅
			System.out.println("리턴된 객체는 "+vo+"입니다.");
		}
		else if(obj instanceof BoardVO) {
			BoardVO vo=(BoardVO)obj;
			System.out.println("리턴된 객체는 "+vo+"입니다.");
		}
		else {
			System.out.println("instanceof체크 false나옴!");
		}
	}
}
