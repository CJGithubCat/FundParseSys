package com.zsh.labouCapital.service;
import com.zsh.labouCapital.pojo.TBaCustomer;

public interface ICustomerService extends BaseService<TBaCustomer>{
   /**
    * 根据机构ID 查找客户信息
    * @param agencyId
    * @return TBaCustomer
    * @throws Exception
    * @author gongyu
    */
    public TBaCustomer findCustomerByAgency(Integer agencyId)throws Exception;
}
