package Implementing_a_simple_IOC;

import org.junit.jupiter.api.Test;

public class SimpleIOCTest {
//    Implementing_a_simple_IOC/ioc.xml  Implementing_a_simple_IOC/ioc.xml

    @Test
    public  void getBean() throws Exception{
        String location = SimpleIOC.class.getClassLoader().getResource("./ioc.xml").getFile();
        System.out.println(location);
        SimpleIOC ioc = new SimpleIOC(location);
        Company company = (Company) ioc.getBean("company");
        People people = (People) ioc.getBean("people");
        System.out.println(company+"====="+people);

    }
}
