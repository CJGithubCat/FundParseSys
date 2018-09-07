package test.controller;
/**
 * 测试日期：2015-05-19 下午2:50
 * 测试结果：无需启动服务，模拟服务启动测试整体流程；
 *         问题：拦截请求失效，比如session过滤失效;
 *         原因：用这样的方式模拟启动服务，web.xml里面的filter无效，不会拦截。
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//XML风格  
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration(value = "src/main/webapp")  
@ContextHierarchy({  
      @ContextConfiguration(name = "parent", locations = "classpath:config/spring/applicationContext.xml"), 
      @ContextConfiguration(name = "child1", locations = "classpath:config/spring/applicationContext-security.xml"),
      @ContextConfiguration(name = "child", locations = "classpath:config/spring/spring-mvc.xml")  
})  

//注解风格  
//@RunWith(SpringJUnit4ClassRunner.class)  
//@WebAppConfiguration(value = "src/main/webapp")  
//@ContextHierarchy({  
//      @ContextConfiguration(name = "parent", classes = AppConfig.class),  
//      @ContextConfiguration(name = "child", classes = MvcConfig.class)  
//})
public class MonitorControllerWebAppContextSetupTest {
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
    
    @Before  
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
    } 
    
    @Test
    public void testView() throws Exception {
    	String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
    	MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post("/monitor/data/vehicleinfos")
                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                    .accept(MediaType.APPLICATION_JSON)).andReturn(); //执行请求
    	if(result!=null){
    		System.out.println("******************" + result.getResponse().getContentAsString()+"***********************");
    	}
//        Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体
    }
}
