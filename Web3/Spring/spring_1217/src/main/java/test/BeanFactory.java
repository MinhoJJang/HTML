package test;

public class BeanFactory {
	public Object getBean(String beanName) {
		if(beanName.equals("iPhone")) {
			return new IPhone();
		}
		else if (beanName.equals("galaxyPhone")) {
			return new GalaxyPhone();
		}
		return null;
	}
}
