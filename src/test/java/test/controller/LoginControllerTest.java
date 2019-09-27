package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zsh.labouCapital.controller.LoginController;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/*.xml"})
public class LoginControllerTest {
	
    @Autowired
    private LoginController loginController;
    
    @Autowired
    private IUserService userService;
	/***
	 * @Title: analyseExceptFund   
	 * @Description: TODO  
	 * @param:解析出低估值的基金信息
	 * @return: void      
	 * @throws
	 */
	@Test
	public void login(){
	    User user= new User();
	    user.setLoginName("admin");
	    user.setLoginPassword("e10adc3949ba59abbe56e057f20f883e");
	    try {
            userService.validatePwd(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
}
