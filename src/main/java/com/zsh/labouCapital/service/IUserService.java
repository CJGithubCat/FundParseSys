package com.zsh.labouCapital.service;
import com.zsh.labouCapital.dao.dto.TUser;
import com.zsh.labouCapital.entity.User;

public interface IUserService{
   /**
    * @Title: validatePwd   
    * @Description: 登录验证  
    * @param: @param user
    * @param: @return
    * @param: @throws Exception      
    * @return: int      
    * @throws
    */
   public int validatePwd(User user)throws Exception;

   public TUser getUserByLoginName(String loginName);
    
}
