package test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {

	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  
	  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("study_context.xml");
	  HelloWorld bean = (HelloWorld)applicationContext.getBean("helloWorld");
	  bean.say();
	 }

	}
