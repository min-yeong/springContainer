package springcontainer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class App {
	public static void main(String[] args) {
		/*
		// User 객체를 만들어낸 객체는 App, User 객체의 주체는 App 
		User u = new User(1L, "홍길동");
		System.out.println(u);
		*/
		// testBeanFactory();
		testApplicationContext();
	}
	
	public static void testApplicationContext() {
		// ApplicationContext 이용
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		//User user1 = ac.getBean(User.class);
		// -> Error : 클래스로 만들어진 Bean이 많을 경우 type(Class)로 getBean을 할 수 없다.
	
		User user2 = (User)ac.getBean("user2");
		System.out.println("user2 : "+user2);
		User user3 = (User)ac.getBean("user3");
		System.out.println("user3 : "+user3);
		User user4 = (User)ac.getBean("user4");
		System.out.println("user4 : "+user4);
		
		// 외부 객체가 주입된 Bean의 확인
		User user5 = (User)ac.getBean("user5");
		System.out.println("user5 :"+user5);
		
		// 컬렉션을 주입한 Bean의 확인
		User user6 = (User)ac.getBean("user6");
		System.out.println("user6 :"+user6);
	}
	
	public static void testBeanFactory() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContext.xml"));
		
		// Factory로부터 Bean을 얻어오기
		User user = bf.getBean(User.class); // 클래스(Type)명으로 받아오기
		System.out.println("user : "+user);
		
		// id로 객체를 얻어오기
		User userById = (User)bf.getBean("user");
		System.out.println("userById : "+userById);
		
		// name으로 객체를 얻어오기
		User userByName = (User)bf.getBean("member");
		System.out.println("userByName : "+userByName);
		
		/*
		// 만약 존재하지 않는 id 혹은 name으로 객체를 불러온 경우 -> NoSuchBeanDefinitionException 발생 
		User userUnknown = (User)bf.getBean("unknown");
		System.out.println("userUnknown : "+userUnknown);
		*/
		
		// 기본적으로 Spring Container는 단일 객체를 유지
		System.out.println("user가 userById와 동일 객체인가? "+ (user == userById));
		System.out.println("userById는 userByName과 동일 객체인가? "+(userById == userByName));
	}
}
