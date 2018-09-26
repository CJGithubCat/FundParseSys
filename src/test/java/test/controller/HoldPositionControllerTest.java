package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zsh.labouCapital.controller.HoldPositionController;

/**
 *<p> Title: HoldPositionControllerTest </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月26日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/*.xml"})
public class HoldPositionControllerTest {
    
    @Autowired
    private HoldPositionController holdPositionController;
    
    @Test
    public void analyBuyStrategy(){
        holdPositionController.analyBuyStrategy(null);
    }
}
