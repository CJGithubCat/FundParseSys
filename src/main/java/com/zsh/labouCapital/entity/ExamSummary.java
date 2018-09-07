package com.zsh.labouCapital.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Title: ExamSummary
 * </p>
 * <p>
 * Description:考核汇总
 * </p>
 * 
 * @author cj
 * @date 2018年6月21日
 */
public class ExamSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int companyId;// 机构id
	private String yearMonthDate;
	private String fbkName;
	private String dwName;//单位名称                
	private String bnName;//部门名称 
	private String jyzName;//加油站名称  
	private String fbkType;//分版块类型
	private String bwCode;
	private String jyzType;//加油站分类
	private double gasoline92;//92#汽油   
	private double gasoline95;//95#汽油 
	private double gasoline98;//98#汽油 
	private double diesel0;//0#柴油      
	private double diesel10;//10#柴油     
	private double trq;//天然气          
	private double sjlxj;//实际量小计          
	private double dyxcbz;//吨油薪酬标准   
	private double xc;//薪酬                
	private double dyfjbz;//吨油附加标准       
	private double fj;//附加               
	private double dyrgfbz;//吨油人工费标准  
	private double rgfy;//人工费用   
	private double sjfj;//实际附加      
	private double dwsb;//单位社保       
	private double jrfl;//节日福利     
	private double rclb;//日常劳保           
	private double ycbz;//用餐补助                
	private double rsdljgglf;//人事代理机构管理费                 
	private double qyxc;//轻油薪酬           
	private double xcfczkcze;//现场非充值卡充值额            
	private double czkcze;//充值卡充值额        
	private double czkxse;//充值卡销售额             
	private double wtcze;//网厅充值额                               
	private double zzfkzdcz;//自助发卡终端充值       
	private double jykkhcze;//加油卡考核充值额          
	private double jsjykxc;//计算加油卡薪酬                    
	private double fdzxy;//非定制香烟（零售）销售额（元）            
	private double ybspxse;//一般商品销售额                 
	private double rybhjnjxse;//燃油宝和降凝剂销售额       
	private double zyppsxse;//自有品牌水销售额           
	private double wqclyxse;//尾气处理液销售额               
	private double lmjxse;//赖茅酒销售额         
	private double zyppblsxse;//自有品牌玻璃水销售额                     
	private double olzxse;//欧露纸/定制香烟销售额（元）                     
	private double gqxlxse;//枸杞系列销售额   
	private double ydczk;//移动充值卡
	private double cpxse;//彩票销售额              
	private double yylmxse;//异业联盟销售额  
	private double qcyhxse;//汽车养护销售额                
	private double tgxse;//团购销售额                 
	private double fypjcxc;//非油品基础薪酬        
	private double fypmbwcjcje;//非油品目标完成奖惩金额            
	private double qyzsxc;//轻油折算薪酬                  
	private double jykdzxc;//加油卡调整薪酬 
	private double lsldyrw;//零售量当月任务         
	private double lsldywcl;//零售量当月完成量        
	private double lslydrwwcl;//零售量月度任务完成率    
	private double lslydrwwclzb;//零售量月度任务完成率占比      
	private double trqdyrw;//天然气当月任务                   
	private double trqdywcl;//天然气当月完成量   
	private double trqydrwwcl;//天然气月度任务完成率            
	private double trqydrwwclzb;//天然气月度任务完成率占比 
	private double lsydmledyrw;//零售月度毛利额当月任务                   
	private double lsydmledywcl;//零售月度毛利额当月完成量          
	private double lsydmleydrwwcl;//零售月度毛利额月度任务完成率        
	private double lsydmleydrwwclzb;//零售月度毛利额月度任务完成率占比         
	private double fypxsedyrw;//非油品销售额当月任务     
	private double fypxsedywcl;//非油品销售额当月完成量                  
	private double fypxseydrwwcl;//非油品销售额月度任务完成率     
	private double fypxseydrwwclzb;//非油品销售额月度任务完成率占比         
	private double sykhbfypxsedyrw;//商业客户部非油品销售额当月任务          
	private double sykhbfypxsedywcl;//商业客户部非油品销售额当月完成量 
	private double sykhbfypxseydrwwcl;//商业客户部非油品销售额月度任务完成率     
	private double sykhbfypxseydrwwclzb;//商业客户部非油品销售额月度任务完成率占比 
	private double fypydmledyrw;//非油品月度毛利额当月任务     
	private double fypydmledywcl;//非油品月度毛利额当月完成量         
	private double fypydmleydrwwcl;//非油品月度毛利额月度任务完成率           
	private double fypydmleydrwwclzb;//非油品月度毛利额月度任务完成率占比       
	private double zpldyrw;//直批量当月任务   
	private double zpldywcl;//直批量当月完成量             
	private double zplydrwwcl;//直批量月度任务完成率                 
	private double zplydrwwclzb;//直批量月度任务完成率占比      
	private double zpydmledyrw;	//直批月度毛利额当月任务                   
	private double zpydmledywcl;//直批月度毛利额当月完成量                 
	private double zpydmleydrwwcl;//直批月度毛利额月度任务完成率          
	private double zpydmleydrwwclzb;//直批月度毛利额月度任务完成率占比 
	private double ydlrdyrw;//月度利润当月任务                         
	private double ydlrdywcl;//月度利润当月完成量                       
	private double ydlrydrwwcl;//月度利润月度任务完成率                
	private double ydlrydrwwclzb;//月度利润月度任务完成率占比               
	private double snyjckl;//上年月均出库量            
	private double zcklbhjwkfy;//总出库量不含寄外库发油  
	private double qzkqckds;//其中跨区出库吨数      
	private double cklkzxsxx;//出库量控制系数下限  
	private double cklkzxssx;//出库量控制系数上限    
	private double gldf;//管理得分       
	private double glzbzb;//管理指标占比       
	private double xgsjybbzglgxxs;//县公司经营部班子管理贡献系数       
	private double zhdfl;//综合得分率      
	private double llggxs;//联量挂钩系数                
	private double jbxc;//基本薪酬         
	private double llxc;//联量薪酬            
	private double zykzjjddrygz;//整月看站及借调等人员工资         
	private double jbf;//加班费             
	private double qjryxc;//请假人员薪酬            
	private double gdbc;//过渡补差                 
	private double blc;//保留差                  
	private double qtbt;//其他补贴          
	private double lstykzgz;//临时停业看站工资                 
	private double zxjf;//专项奖罚            
	private double glkf;//管理扣罚               
	private double lxxc;//劳效薪酬    
	private double hj;//合计       
	private String bz;//备注    
	private int status;
	private double qycmbxc;//轻油朝目标薪酬
	private double beginfypmbwcjcje;//初始导入时的：非油品目标完成情况奖惩
	private double  beginqyzsxc;//初始导入时的：轻油折算薪酬
	private double  beginjykdzxc;//初始导入时的：加油卡调整薪
	private double  beginglkf;//初始导入时的：管理扣罚
	private double  beginzxjf;//初始导入时的：专项奖罚

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFbkName(String fbkName) {
		this.fbkName = fbkName;
	}

	public String getFbkName() {
		return fbkName;
	}

	public void setDwName(String dwName) {
		this.dwName = dwName;
	}

	public String getDwName() {
		return dwName;
	}

	public void setBnName(String bnName) {
		this.bnName = bnName;
	}

	public String getBnName() {
		return bnName;
	}

	public void setJyzName(String jyzName) {
		this.jyzName = jyzName;
	}

	public String getJyzName() {
		return jyzName;
	}

	public void setBwCode(String bwCode) {
		this.bwCode = bwCode;
	}

	public String getBwCode() {
		return bwCode;
	}

	public void setJyzType(String jyzType) {
		this.jyzType = jyzType;
	}

	public String getJyzType() {
		return jyzType;
	}

	public void setGasoline92(double gasoline92) {
		this.gasoline92 = gasoline92;
	}

	public double getGasoline92() {
		return gasoline92;
	}

	public void setGasoline95(double gasoline95) {
		this.gasoline95 = gasoline95;
	}

	public double getGasoline95() {
		return gasoline95;
	}

	public void setGasoline98(double gasoline98) {
		this.gasoline98 = gasoline98;
	}

	public double getGasoline98() {
		return gasoline98;
	}

	public void setDiesel0(double diesel0) {
		this.diesel0 = diesel0;
	}

	public double getDiesel0() {
		return diesel0;
	}

	public void setDiesel10(double diesel10) {
		this.diesel10 = diesel10;
	}

	public double getDiesel10() {
		return diesel10;
	}

	public void setTrq(double trq) {
		this.trq = trq;
	}

	public double getTrq() {
		return trq;
	}

	public void setSjlxj(double sjlxj) {
		this.sjlxj = sjlxj;
	}

	public double getSjlxj() {
		return sjlxj;
	}

	public void setDyxcbz(double dyxcbz) {
		this.dyxcbz = dyxcbz;
	}

	public double getDyxcbz() {
		return dyxcbz;
	}

	public void setXc(double xc) {
		this.xc = xc;
	}

	public double getXc() {
		return xc;
	}

	public void setDyfjbz(double dyfjbz) {
		this.dyfjbz = dyfjbz;
	}

	public double getDyfjbz() {
		return dyfjbz;
	}

	public void setFj(double fj) {
		this.fj = fj;
	}

	public double getFj() {
		return fj;
	}

	public void setDyrgfbz(double dyrgfbz) {
		this.dyrgfbz = dyrgfbz;
	}

	public double getDyrgfbz() {
		return dyrgfbz;
	}

	public void setRgfy(double rgfy) {
		this.rgfy = rgfy;
	}

	public double getRgfy() {
		return rgfy;
	}

	public void setSjfj(double sjfj) {
		this.sjfj = sjfj;
	}

	public double getSjfj() {
		return sjfj;
	}

	public void setDwsb(double dwsb) {
		this.dwsb = dwsb;
	}

	public double getDwsb() {
		return dwsb;
	}

	public void setJrfl(double jrfl) {
		this.jrfl = jrfl;
	}

	public double getJrfl() {
		return jrfl;
	}

	public void setRclb(double rclb) {
		this.rclb = rclb;
	}

	public double getRclb() {
		return rclb;
	}

	public void setYcbz(double ycbz) {
		this.ycbz = ycbz;
	}

	public double getYcbz() {
		return ycbz;
	}

	public void setRsdljgglf(double rsdljgglf) {
		this.rsdljgglf = rsdljgglf;
	}

	public double getRsdljgglf() {
		return rsdljgglf;
	}

	public void setQyxc(double qyxc) {
		this.qyxc = qyxc;
	}

	public double getQyxc() {
		return qyxc;
	}

	public void setXcfczkcze(double xcfczkcze) {
		this.xcfczkcze = xcfczkcze;
	}

	public double getXcfczkcze() {
		return xcfczkcze;
	}

	public void setCzkcze(double czkcze) {
		this.czkcze = czkcze;
	}

	public double getCzkcze() {
		return czkcze;
	}

	public void setCzkxse(double czkxse) {
		this.czkxse = czkxse;
	}

	public double getCzkxse() {
		return czkxse;
	}

	public void setWtcze(double wtcze) {
		this.wtcze = wtcze;
	}

	public double getWtcze() {
		return wtcze;
	}

	public void setZzfkzdcz(double zzfkzdcz) {
		this.zzfkzdcz = zzfkzdcz;
	}

	public double getZzfkzdcz() {
		return zzfkzdcz;
	}

	public void setJykkhcze(double jykkhcze) {
		this.jykkhcze = jykkhcze;
	}

	public double getJykkhcze() {
		return jykkhcze;
	}

	public void setJsjykxc(double jsjykxc) {
		this.jsjykxc = jsjykxc;
	}

	public double getJsjykxc() {
		return jsjykxc;
	}


	public double getFdzxy() {
		return fdzxy;
	}

	public void setFdzxy(double fdzxy) {
		this.fdzxy = fdzxy;
	}

	public void setYbspxse(double ybspxse) {
		this.ybspxse = ybspxse;
	}

	public double getYbspxse() {
		return ybspxse;
	}

	public void setRybhjnjxse(double rybhjnjxse) {
		this.rybhjnjxse = rybhjnjxse;
	}

	public double getRybhjnjxse() {
		return rybhjnjxse;
	}

	public double getZyppsxse() {
		return zyppsxse;
	}

	public void setZyppsxse(double zyppsxse) {
		this.zyppsxse = zyppsxse;
	}

	public void setWqclyxse(double wqclyxse) {
		this.wqclyxse = wqclyxse;
	}

	public double getWqclyxse() {
		return wqclyxse;
	}

	public void setLmjxse(double lmjxse) {
		this.lmjxse = lmjxse;
	}

	public double getLmjxse() {
		return lmjxse;
	}

	public void setZyppblsxse(double zyppblsxse) {
		this.zyppblsxse = zyppblsxse;
	}

	public double getZyppblsxse() {
		return zyppblsxse;
	}

	public void setOlzxse(double olzxse) {
		this.olzxse = olzxse;
	}

	public double getOlzxse() {
		return olzxse;
	}

	public void setGqxlxse(double gqxlxse) {
		this.gqxlxse = gqxlxse;
	}

	public double getGqxlxse() {
		return gqxlxse;
	}

	public void setYdczk(double ydczk) {
		this.ydczk = ydczk;
	}

	public double getYdczk() {
		return ydczk;
	}

	public void setCpxse(double cpxse) {
		this.cpxse = cpxse;
	}

	public double getCpxse() {
		return cpxse;
	}

	public void setYylmxse(double yylmxse) {
		this.yylmxse = yylmxse;
	}

	public double getYylmxse() {
		return yylmxse;
	}

	public void setQcyhxse(double qcyhxse) {
		this.qcyhxse = qcyhxse;
	}

	public double getQcyhxse() {
		return qcyhxse;
	}

	public void setTgxse(double tgxse) {
		this.tgxse = tgxse;
	}

	public double getTgxse() {
		return tgxse;
	}

	public double getFypjcxc() {
		return fypjcxc;
	}

	public void setFypjcxc(double fypjcxc) {
		this.fypjcxc = fypjcxc;
	}

	public void setFypmbwcjcje(double fypmbwcjcje) {
		this.fypmbwcjcje = fypmbwcjcje;
	}

	public double getFypmbwcjcje() {
		return fypmbwcjcje;
	}

	public void setQyzsxc(double qyzsxc) {
		this.qyzsxc = qyzsxc;
	}

	public double getQyzsxc() {
		return qyzsxc;
	}

	public void setJykdzxc(double jykdzxc) {
		this.jykdzxc = jykdzxc;
	}

	public double getJykdzxc() {
		return jykdzxc;
	}

	public void setLsldyrw(double lsldyrw) {
		this.lsldyrw = lsldyrw;
	}

	public double getLsldyrw() {
		return lsldyrw;
	}

	public void setLsldywcl(double lsldywcl) {
		this.lsldywcl = lsldywcl;
	}

	public double getLsldywcl() {
		return lsldywcl;
	}

	public void setLslydrwwcl(double lslydrwwcl) {
		this.lslydrwwcl = lslydrwwcl;
	}

	public double getLslydrwwcl() {
		return lslydrwwcl;
	}

	public void setLslydrwwclzb(double lslydrwwclzb) {
		this.lslydrwwclzb = lslydrwwclzb;
	}

	public double getLslydrwwclzb() {
		return lslydrwwclzb;
	}

	public void setTrqdyrw(double trqdyrw) {
		this.trqdyrw = trqdyrw;
	}

	public double getTrqdyrw() {
		return trqdyrw;
	}

	public void setTrqdywcl(double trqdywcl) {
		this.trqdywcl = trqdywcl;
	}

	public double getTrqdywcl() {
		return trqdywcl;
	}

	public void setTrqydrwwcl(double trqydrwwcl) {
		this.trqydrwwcl = trqydrwwcl;
	}

	public double getTrqydrwwcl() {
		return trqydrwwcl;
	}

	public void setTrqydrwwclzb(double trqydrwwclzb) {
		this.trqydrwwclzb = trqydrwwclzb;
	}

	public double getTrqydrwwclzb() {
		return trqydrwwclzb;
	}

	public void setLsydmledyrw(double lsydmledyrw) {
		this.lsydmledyrw = lsydmledyrw;
	}

	public double getLsydmledyrw() {
		return lsydmledyrw;
	}

	public void setLsydmledywcl(double lsydmledywcl) {
		this.lsydmledywcl = lsydmledywcl;
	}

	public double getLsydmledywcl() {
		return lsydmledywcl;
	}

	public void setLsydmleydrwwcl(double lsydmleydrwwcl) {
		this.lsydmleydrwwcl = lsydmleydrwwcl;
	}

	public double getLsydmleydrwwcl() {
		return lsydmleydrwwcl;
	}

	public void setLsydmleydrwwclzb(double lsydmleydrwwclzb) {
		this.lsydmleydrwwclzb = lsydmleydrwwclzb;
	}

	public double getLsydmleydrwwclzb() {
		return lsydmleydrwwclzb;
	}

	public void setFypxsedyrw(double fypxsedyrw) {
		this.fypxsedyrw = fypxsedyrw;
	}

	public double getFypxsedyrw() {
		return fypxsedyrw;
	}

	public void setFypxsedywcl(double fypxsedywcl) {
		this.fypxsedywcl = fypxsedywcl;
	}

	public double getFypxsedywcl() {
		return fypxsedywcl;
	}

	public void setFypxseydrwwcl(double fypxseydrwwcl) {
		this.fypxseydrwwcl = fypxseydrwwcl;
	}

	public double getFypxseydrwwcl() {
		return fypxseydrwwcl;
	}

	public void setFypxseydrwwclzb(double fypxseydrwwclzb) {
		this.fypxseydrwwclzb = fypxseydrwwclzb;
	}

	public double getFypxseydrwwclzb() {
		return fypxseydrwwclzb;
	}

	public void setSykhbfypxsedyrw(double sykhbfypxsedyrw) {
		this.sykhbfypxsedyrw = sykhbfypxsedyrw;
	}

	public double getSykhbfypxsedyrw() {
		return sykhbfypxsedyrw;
	}

	public void setSykhbfypxsedywcl(double sykhbfypxsedywcl) {
		this.sykhbfypxsedywcl = sykhbfypxsedywcl;
	}

	public double getSykhbfypxsedywcl() {
		return sykhbfypxsedywcl;
	}

	public void setSykhbfypxseydrwwcl(double sykhbfypxseydrwwcl) {
		this.sykhbfypxseydrwwcl = sykhbfypxseydrwwcl;
	}

	public double getSykhbfypxseydrwwcl() {
		return sykhbfypxseydrwwcl;
	}

	public void setSykhbfypxseydrwwclzb(double sykhbfypxseydrwwclzb) {
		this.sykhbfypxseydrwwclzb = sykhbfypxseydrwwclzb;
	}

	public double getSykhbfypxseydrwwclzb() {
		return sykhbfypxseydrwwclzb;
	}

	public void setFypydmledyrw(double fypydmledyrw) {
		this.fypydmledyrw = fypydmledyrw;
	}

	public double getFypydmledyrw() {
		return fypydmledyrw;
	}

	public void setFypydmledywcl(double fypydmledywcl) {
		this.fypydmledywcl = fypydmledywcl;
	}

	public double getFypydmledywcl() {
		return fypydmledywcl;
	}

	public void setFypydmleydrwwcl(double fypydmleydrwwcl) {
		this.fypydmleydrwwcl = fypydmleydrwwcl;
	}

	public double getFypydmleydrwwcl() {
		return fypydmleydrwwcl;
	}

	public void setFypydmleydrwwclzb(double fypydmleydrwwclzb) {
		this.fypydmleydrwwclzb = fypydmleydrwwclzb;
	}

	public double getFypydmleydrwwclzb() {
		return fypydmleydrwwclzb;
	}

	public void setZpldyrw(double zpldyrw) {
		this.zpldyrw = zpldyrw;
	}

	public double getZpldyrw() {
		return zpldyrw;
	}

	public void setZpldywcl(double zpldywcl) {
		this.zpldywcl = zpldywcl;
	}

	public double getZpldywcl() {
		return zpldywcl;
	}

	public void setZplydrwwcl(double zplydrwwcl) {
		this.zplydrwwcl = zplydrwwcl;
	}

	public double getZplydrwwcl() {
		return zplydrwwcl;
	}

	public void setZplydrwwclzb(double zplydrwwclzb) {
		this.zplydrwwclzb = zplydrwwclzb;
	}

	public double getZplydrwwclzb() {
		return zplydrwwclzb;
	}

	public void setZpydmledyrw(double zpydmledyrw) {
		this.zpydmledyrw = zpydmledyrw;
	}

	public double getZpydmledyrw() {
		return zpydmledyrw;
	}

	public void setZpydmledywcl(double zpydmledywcl) {
		this.zpydmledywcl = zpydmledywcl;
	}

	public double getZpydmledywcl() {
		return zpydmledywcl;
	}

	public void setZpydmleydrwwcl(double zpydmleydrwwcl) {
		this.zpydmleydrwwcl = zpydmleydrwwcl;
	}

	public double getZpydmleydrwwcl() {
		return zpydmleydrwwcl;
	}

	public void setZpydmleydrwwclzb(double zpydmleydrwwclzb) {
		this.zpydmleydrwwclzb = zpydmleydrwwclzb;
	}

	public double getZpydmleydrwwclzb() {
		return zpydmleydrwwclzb;
	}

	public void setYdlrdyrw(double ydlrdyrw) {
		this.ydlrdyrw = ydlrdyrw;
	}

	public double getYdlrdyrw() {
		return ydlrdyrw;
	}

	public void setYdlrdywcl(double ydlrdywcl) {
		this.ydlrdywcl = ydlrdywcl;
	}

	public double getYdlrdywcl() {
		return ydlrdywcl;
	}

	public void setYdlrydrwwcl(double ydlrydrwwcl) {
		this.ydlrydrwwcl = ydlrydrwwcl;
	}

	public double getYdlrydrwwcl() {
		return ydlrydrwwcl;
	}

	public void setYdlrydrwwclzb(double ydlrydrwwclzb) {
		this.ydlrydrwwclzb = ydlrydrwwclzb;
	}

	public double getYdlrydrwwclzb() {
		return ydlrydrwwclzb;
	}

	public void setSnyjckl(double snyjckl) {
		this.snyjckl = snyjckl;
	}

	public double getSnyjckl() {
		return snyjckl;
	}

	public void setZcklbhjwkfy(double zcklbhjwkfy) {
		this.zcklbhjwkfy = zcklbhjwkfy;
	}

	public double getZcklbhjwkfy() {
		return zcklbhjwkfy;
	}

	public void setQzkqckds(double qzkqckds) {
		this.qzkqckds = qzkqckds;
	}

	public double getQzkqckds() {
		return qzkqckds;
	}

	public void setCklkzxsxx(double cklkzxsxx) {
		this.cklkzxsxx = cklkzxsxx;
	}

	public double getCklkzxsxx() {
		return cklkzxsxx;
	}

	public void setCklkzxssx(double cklkzxssx) {
		this.cklkzxssx = cklkzxssx;
	}

	public double getCklkzxssx() {
		return cklkzxssx;
	}

	public void setGldf(double gldf) {
		this.gldf = gldf;
	}

	public double getGldf() {
		return gldf;
	}

	public void setGlzbzb(double glzbzb) {
		this.glzbzb = glzbzb;
	}

	public double getGlzbzb() {
		return glzbzb;
	}

	public void setXgsjybbzglgxxs(double xgsjybbzglgxxs) {
		this.xgsjybbzglgxxs = xgsjybbzglgxxs;
	}

	public double getXgsjybbzglgxxs() {
		return xgsjybbzglgxxs;
	}

	public void setZhdfl(double zhdfl) {
		this.zhdfl = zhdfl;
	}

	public double getZhdfl() {
		return zhdfl;
	}

	public void setLlggxs(double llggxs) {
		this.llggxs = llggxs;
	}

	public double getLlggxs() {
		return llggxs;
	}

	public void setJbxc(double jbxc) {
		this.jbxc = jbxc;
	}

	public double getJbxc() {
		return jbxc;
	}

	public void setLlxc(double llxc) {
		this.llxc = llxc;
	}

	public double getLlxc() {
		return llxc;
	}

	public void setZykzjjddrygz(double zykzjjddrygz) {
		this.zykzjjddrygz = zykzjjddrygz;
	}

	public double getZykzjjddrygz() {
		return zykzjjddrygz;
	}

	public void setJbf(double jbf) {
		this.jbf = jbf;
	}

	public double getJbf() {
		return jbf;
	}

	public void setQjryxc(double qjryxc) {
		this.qjryxc = qjryxc;
	}

	public double getQjryxc() {
		return qjryxc;
	}

	public void setGdbc(double gdbc) {
		this.gdbc = gdbc;
	}

	public double getGdbc() {
		return gdbc;
	}

	public double getBlc() {
		return blc;
	}

	public void setBlc(double blc) {
		this.blc = blc;
	}

	public void setQtbt(double qtbt) {
		this.qtbt = qtbt;
	}

	public double getQtbt() {
		return qtbt;
	}

	public void setLstykzgz(double lstykzgz) {
		this.lstykzgz = lstykzgz;
	}

	public double getLstykzgz() {
		return lstykzgz;
	}

	public void setZxjf(double zxjf) {
		this.zxjf = zxjf;
	}

	public double getZxjf() {
		return zxjf;
	}

	public void setGlkf(double glkf) {
		this.glkf = glkf;
	}

	public double getGlkf() {
		return glkf;
	}

	public void setLxxc(double lxxc) {
		this.lxxc = lxxc;
	}

	public double getLxxc() {
		return lxxc;
	}

	public void setHj(double hj) {
		this.hj = hj;
	}

	public double getHj() {
		return hj;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBz() {
		return bz;
	}

	public void setFbkType(String fbkType) {
		this.fbkType = fbkType;
	}

	public String getFbkType() {
		return fbkType;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public String getYearMonthDate() {
		return yearMonthDate;
	}

	public void setYearMonthDate(String yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	
	public double getQycmbxc() {
		return qycmbxc;
	}

	public void setQycmbxc(double qycmbxc) {
		this.qycmbxc = qycmbxc;
	}

	public double getBeginfypmbwcjcje() {
		return beginfypmbwcjcje;
	}

	public void setBeginfypmbwcjcje(double beginfypmbwcjcje) {
		this.beginfypmbwcjcje = beginfypmbwcjcje;
	}

	public double getBeginqyzsxc() {
		return beginqyzsxc;
	}

	public void setBeginqyzsxc(double beginqyzsxc) {
		this.beginqyzsxc = beginqyzsxc;
	}

	public double getBeginjykdzxc() {
		return beginjykdzxc;
	}

	public void setBeginjykdzxc(double beginjykdzxc) {
		this.beginjykdzxc = beginjykdzxc;
	}

	public double getBeginglkf() {
		return beginglkf;
	}

	public void setBeginglkf(double beginglkf) {
		this.beginglkf = beginglkf;
	}

	public double getBeginzxjf() {
		return beginzxjf;
	}

	public void setBeginzxjf(double beginzxjf) {
		this.beginzxjf = beginzxjf;
	}

	@Override
	public String toString() {
		return "ExamSummary [id=" + id + ", companyId=" + companyId + ", yearMonthDate=" + yearMonthDate + ", fbkName="
				+ fbkName + ", dwName=" + dwName + ", bnName=" + bnName + ", jyzName=" + jyzName + ", fbkType="
				+ fbkType + ", bwCode=" + bwCode + ", jyzType=" + jyzType + ", gasoline92=" + gasoline92
				+ ", gasoline95=" + gasoline95 + ", gasoline98=" + gasoline98 + ", diesel0=" + diesel0 + ", diesel10="
				+ diesel10 + ", trq=" + trq + ", sjlxj=" + sjlxj + ", dyxcbz=" + dyxcbz + ", xc=" + xc + ", dyfjbz="
				+ dyfjbz + ", fj=" + fj + ", dyrgfbz=" + dyrgfbz + ", rgfy=" + rgfy + ", sjfj=" + sjfj + ", dwsb="
				+ dwsb + ", jrfl=" + jrfl + ", rclb=" + rclb + ", ycbz=" + ycbz + ", rsdljgglf=" + rsdljgglf + ", qyxc="
				+ qyxc + ", xcfczkcze=" + xcfczkcze + ", czkcze=" + czkcze + ", czkxse=" + czkxse + ", wtcze=" + wtcze
				+ ", zzfkzdcz=" + zzfkzdcz + ", jykkhcze=" + jykkhcze + ", jsjykxc=" + jsjykxc + ", fdzxy=" + fdzxy
				+ ", ybspxse=" + ybspxse + ", rybhjnjxse=" + rybhjnjxse + ", zyppsxse=" + zyppsxse + ", wqclyxse="
				+ wqclyxse + ", lmjxse=" + lmjxse + ", zyppblsxse=" + zyppblsxse + ", olzxse=" + olzxse + ", gqxlxse="
				+ gqxlxse + ", ydczk=" + ydczk + ", cpxse=" + cpxse + ", yylmxse=" + yylmxse + ", qcyhxse=" + qcyhxse
				+ ", tgxse=" + tgxse + ", fypjcxc=" + fypjcxc + ", fypmbwcjcje=" + fypmbwcjcje + ", qyzsxc=" + qyzsxc
				+ ", jykdzxc=" + jykdzxc + ", lsldyrw=" + lsldyrw + ", lsldywcl=" + lsldywcl + ", lslydrwwcl="
				+ lslydrwwcl + ", lslydrwwclzb=" + lslydrwwclzb + ", trqdyrw=" + trqdyrw + ", trqdywcl=" + trqdywcl
				+ ", trqydrwwcl=" + trqydrwwcl + ", trqydrwwclzb=" + trqydrwwclzb + ", lsydmledyrw=" + lsydmledyrw
				+ ", lsydmledywcl=" + lsydmledywcl + ", lsydmleydrwwcl=" + lsydmleydrwwcl + ", lsydmleydrwwclzb="
				+ lsydmleydrwwclzb + ", fypxsedyrw=" + fypxsedyrw + ", fypxsedywcl=" + fypxsedywcl + ", fypxseydrwwcl="
				+ fypxseydrwwcl + ", fypxseydrwwclzb=" + fypxseydrwwclzb + ", sykhbfypxsedyrw=" + sykhbfypxsedyrw
				+ ", sykhbfypxsedywcl=" + sykhbfypxsedywcl + ", sykhbfypxseydrwwcl=" + sykhbfypxseydrwwcl
				+ ", sykhbfypxseydrwwclzb=" + sykhbfypxseydrwwclzb + ", fypydmledyrw=" + fypydmledyrw
				+ ", fypydmledywcl=" + fypydmledywcl + ", fypydmleydrwwcl=" + fypydmleydrwwcl + ", fypydmleydrwwclzb="
				+ fypydmleydrwwclzb + ", zpldyrw=" + zpldyrw + ", zpldywcl=" + zpldywcl + ", zplydrwwcl=" + zplydrwwcl
				+ ", zplydrwwclzb=" + zplydrwwclzb + ", zpydmledyrw=" + zpydmledyrw + ", zpydmledywcl=" + zpydmledywcl
				+ ", zpydmleydrwwcl=" + zpydmleydrwwcl + ", zpydmleydrwwclzb=" + zpydmleydrwwclzb + ", ydlrdyrw="
				+ ydlrdyrw + ", ydlrdywcl=" + ydlrdywcl + ", ydlrydrwwcl=" + ydlrydrwwcl + ", ydlrydrwwclzb="
				+ ydlrydrwwclzb + ", snyjckl=" + snyjckl + ", zcklbhjwkfy=" + zcklbhjwkfy + ", qzkqckds=" + qzkqckds
				+ ", cklkzxsxx=" + cklkzxsxx + ", cklkzxssx=" + cklkzxssx + ", gldf=" + gldf + ", glzbzb=" + glzbzb
				+ ", xgsjybbzglgxxs=" + xgsjybbzglgxxs + ", zhdfl=" + zhdfl + ", llggxs=" + llggxs + ", jbxc=" + jbxc
				+ ", llxc=" + llxc + ", zykzjjddrygz=" + zykzjjddrygz + ", jbf=" + jbf + ", qjryxc=" + qjryxc
				+ ", gdbc=" + gdbc + ", blc=" + blc + ", qtbt=" + qtbt + ", lstykzgz=" + lstykzgz + ", zxjf=" + zxjf
				+ ", glkf=" + glkf + ", lxxc=" + lxxc + ", hj=" + hj + ", bz=" + bz + ", status=" + status
				+ ", qycmbxc=" + qycmbxc + ", beginfypmbwcjcje=" + beginfypmbwcjcje + ", beginqyzsxc=" + beginqyzsxc
				+ ", beginjykdzxc=" + beginjykdzxc + ", beginglkf=" + beginglkf + ", beginzxjf=" + beginzxjf + "]";
	}
}
