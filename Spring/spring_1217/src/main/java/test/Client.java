package test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Client {
	
	public static void main(String[] args) {
		// Factory 사용 
		/*BeanFactory factory = new BeanFactory();
		
		Phone phone = (Phone)factory.getBean(args[0]);
		// Run -> Run Configurations - Client - Arguments - Program Arguments 에서 설정
		
		// Phone phone = new IPhone();
		phone.volumeDown();
		phone.msg();*/
		
		// ====================
		
		// SpringContainer 사용 
		// 1. applicationContext.xml 에서 class 및 id 지정 
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		Phone phone = (Phone)factory.getBean("iPhone");
		phone.volumeDown();
		phone.msg();
		
		factory.close(); // 굳이 안써도 되지만 명시적으로 써주자 
		
	}
}
