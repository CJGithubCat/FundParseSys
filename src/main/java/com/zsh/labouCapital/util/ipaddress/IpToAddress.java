package com.zsh.labouCapital.util.ipaddress;

import com.zsh.labouCapital.pojo.IpMappedAddress;

public interface IpToAddress {
    /**
     *   	
     * @param interfaceUrl:淘宝，新浪，qq等提供的接口地址
     * @param ip 
     * @param encode :向 interfaceUrl 发送请求的编码
     * @return
     * @throws Exception
     */
     public  IpMappedAddress getAddress(String interfaceUrl,String ip,String encode)throws Exception;
}
