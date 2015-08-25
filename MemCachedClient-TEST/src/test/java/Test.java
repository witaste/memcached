
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zno.pojo.Person;
import cn.zno.utils.Base64;

import com.danga.MemCached.MemCachedClient;

public class Test {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");

		MemCachedClient client = ctx.getBean(MemCachedClient.class);

		Person person = new Person();
		person.setAge(11);
		person.setName("xiaoming");
		person.setPassword("111112");
		
		// ���л�Ϊ�ַ������뻺�� 
		client.set("xiaoming",Base64.se2base64(person), new Date(60 * 1000));
		
		// ��ȡ�����������л��ַ���
		String s = String.valueOf(client.get("xiaoming"));
		// ����
		Person xiaoming = Base64.deFromBase64(s, Person.class);
		
		// ��ӡ
		System.out.println(xiaoming.toString());

	}

}