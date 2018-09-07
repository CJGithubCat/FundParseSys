package com.zsh.labouCapital.entity;

/**
 * table name: t_station_exam_notice_result create time: 2018-06-25 22:10:48
 */
public class StationExamNoticeResult {
	private int id;
	private int summaryId;
	private int companyId;
	private String noticeNo;
	private String yearMonthDate;
	private String wtgs;// 委托单位
	private String jyz;// 加油站
	private int status;// 状态(1-生成考核通知单未下发;2-已下发部门未确认;3-部门已确认接收通知单;4-已下发加油站,未确认;5-加油站已确认通知单;)
	private int salaryStatus;// 工资通知单状态（1-未提交;2-已提交,未审核;3-部门已审核;4-部门已提交加油站工资明细表,未审核;5-市公司已审核;-1-审核未通过）
	
	private double gasoline92;
	private double gasoline95;
	private double gasoline98;
	private double cng;//天然气
	private double cyxl0;//柴油销量
	private double cyxl10;
	private double rgfy;//人工费用
	private double qycmbxc;//轻油超目标薪酬
	private double dyrgfbz;//吨油人工费标准
	private double sjlxj;//实际量小计
	private double qyzsxc;//轻油折算薪酬
	private double jykllxc;//加油卡联量薪酬
	private double jsjykxc;
	private double jykdzxc;//加油卡调整薪酬 
	private double xcfczkcze;//现场非充值卡充值额         
	private double czkcze;//充值卡充值额        
	private double czkxse;//充值卡销售额
	private double zzfkzdcz;//自助发卡终端充值
	private double jykkhcze;//加油卡考核充值额
	private double fypjcxc;//非油品基础薪酬
	private double fypmbwcqkjc;//非油品目标完成情况奖惩
	private double fdzxy;
	private double ybsp;//一般商品
	private double ydczk;
	private double zyppsxse;
	private double yylm;//异业联盟
	private double qcyp;//汽车用品
	private double tg;//团购
	private double rybhjnjxse;
	private double zyppblsxse;//自有品牌玻璃水销售额                     
	private double olz;//欧露纸
	private double gqxlxse;//枸杞系列销售额
	private double zykzjjddrygz;//临时停业看站工资                 
	private double jbf;
	private double lstykzgz;
	private double qjryxc;
	private double blc;//保留差
	private double gdbc;//过渡补差 
	private double zxjf;
	private double glkf;//管理扣罚    
	private double sjfj;
	private double dyxcbz;
	private double total;
	private String statusStr;// 工资通知单状态（1-未提交;2-已提交,未审核;3-部门已审核;4-部门已提交加油站工资明细表,未审核;5-市公司已审核;-1-审核未通过'）
	private String jyzType;
	private double qyllxc;//轻油联量薪酬
	
	public String getJyzType() {
		return jyzType;
	}

	public void setJyzType(String jyzType) {
		this.jyzType = jyzType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(int summaryId) {
		this.summaryId = summaryId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getYearMonthDate() {
		return yearMonthDate;
	}

	public void setYearMonthDate(String yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}
	
	public double getJykllxc() {
		return jykllxc;
	}

	public void setJykllxc(double jykllxc) {
		this.jykllxc = jykllxc;
	}

	public String getWtgs() {
		return wtgs;
	}

	public void setWtgs(String wtgs) {
		this.wtgs = wtgs;
	}

	public String getJyz() {
		return jyz;
	}

	public void setJyz(String jyz) {
		this.jyz = jyz;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSalaryStatus() {
		return salaryStatus;
	}

	public void setSalaryStatus(int salaryStatus) {
		this.salaryStatus = salaryStatus;
	}

	public double getGasoline92() {
		return gasoline92;
	}

	public void setGasoline92(double gasoline92) {
		this.gasoline92 = gasoline92;
	}

	public double getGasoline95() {
		return gasoline95;
	}

	public void setGasoline95(double gasoline95) {
		this.gasoline95 = gasoline95;
	}

	public double getGasoline98() {
		return gasoline98;
	}

	public void setGasoline98(double gasoline98) {
		this.gasoline98 = gasoline98;
	}

	public double getCng() {
		return cng;
	}

	public void setCng(double cng) {
		this.cng = cng;
	}

	public double getCyxl0() {
		return cyxl0;
	}

	public void setCyxl0(double cyxl0) {
		this.cyxl0 = cyxl0;
	}

	public double getCyxl10() {
		return cyxl10;
	}

	public void setCyxl10(double cyxl10) {
		this.cyxl10 = cyxl10;
	}

	public double getRgfy() {
		return rgfy;
	}

	public void setRgfy(double rgfy) {
		this.rgfy = rgfy;
	}

	public double getQycmbxc() {
		return qycmbxc;
	}

	public void setQycmbxc(double qycmbxc) {
		this.qycmbxc = qycmbxc;
	}

	public double getDyrgfbz() {
		return dyrgfbz;
	}

	public void setDyrgfbz(double dyrgfbz) {
		this.dyrgfbz = dyrgfbz;
	}

	public double getSjlxj() {
		return sjlxj;
	}

	public void setSjlxj(double sjlxj) {
		this.sjlxj = sjlxj;
	}

	public double getQyzsxc() {
		return qyzsxc;
	}

	public void setQyzsxc(double qyzsxc) {
		this.qyzsxc = qyzsxc;
	}

	public double getJsjykxc() {
		return jsjykxc;
	}

	public void setJsjykxc(double jsjykxc) {
		this.jsjykxc = jsjykxc;
	}

	public double getJykdzxc() {
		return jykdzxc;
	}

	public void setJykdzxc(double jykdzxc) {
		this.jykdzxc = jykdzxc;
	}

	public double getXcfczkcze() {
		return xcfczkcze;
	}

	public void setXcfczkcze(double xcfczkcze) {
		this.xcfczkcze = xcfczkcze;
	}

	public double getCzkcze() {
		return czkcze;
	}

	public void setCzkcze(double czkcze) {
		this.czkcze = czkcze;
	}

	public double getCzkxse() {
		return czkxse;
	}

	public void setCzkxse(double czkxse) {
		this.czkxse = czkxse;
	}

	public double getZzfkzdcz() {
		return zzfkzdcz;
	}

	public void setZzfkzdcz(double zzfkzdcz) {
		this.zzfkzdcz = zzfkzdcz;
	}

	public double getJykkhcze() {
		return jykkhcze;
	}

	public void setJykkhcze(double jykkhcze) {
		this.jykkhcze = jykkhcze;
	}

	public double getFypjcxc() {
		return fypjcxc;
	}

	public void setFypjcxc(double fypjcxc) {
		this.fypjcxc = fypjcxc;
	}

	public double getFypmbwcqkjc() {
		return fypmbwcqkjc;
	}

	public void setFypmbwcqkjc(double fypmbwcqkjc) {
		this.fypmbwcqkjc = fypmbwcqkjc;
	}

	public double getFdzxy() {
		return fdzxy;
	}

	public void setFdzxy(double fdzxy) {
		this.fdzxy = fdzxy;
	}

	public double getYbsp() {
		return ybsp;
	}

	public void setYbsp(double ybsp) {
		this.ybsp = ybsp;
	}

	public double getYdczk() {
		return ydczk;
	}

	public void setYdczk(double ydczk) {
		this.ydczk = ydczk;
	}

	public double getZyppsxse() {
		return zyppsxse;
	}

	public void setZyppsxse(double zyppsxse) {
		this.zyppsxse = zyppsxse;
	}

	public double getYylm() {
		return yylm;
	}

	public void setYylm(double yylm) {
		this.yylm = yylm;
	}

	public double getQcyp() {
		return qcyp;
	}

	public void setQcyp(double qcyp) {
		this.qcyp = qcyp;
	}

	public double getTg() {
		return tg;
	}

	public void setTg(double tg) {
		this.tg = tg;
	}

	public double getRybhjnjxse() {
		return rybhjnjxse;
	}

	public void setRybhjnjxse(double rybhjnjxse) {
		this.rybhjnjxse = rybhjnjxse;
	}

	public double getZyppblsxse() {
		return zyppblsxse;
	}

	public void setZyppblsxse(double zyppblsxse) {
		this.zyppblsxse = zyppblsxse;
	}

	public double getOlz() {
		return olz;
	}

	public void setOlz(double olz) {
		this.olz = olz;
	}

	public double getGqxlxse() {
		return gqxlxse;
	}

	public void setGqxlxse(double gqxlxse) {
		this.gqxlxse = gqxlxse;
	}

	public double getZykzjjddrygz() {
		return zykzjjddrygz;
	}

	public void setZykzjjddrygz(double zykzjjddrygz) {
		this.zykzjjddrygz = zykzjjddrygz;
	}

	public double getJbf() {
		return jbf;
	}

	public void setJbf(double jbf) {
		this.jbf = jbf;
	}

	public double getLstykzgz() {
		return lstykzgz;
	}

	public void setLstykzgz(double lstykzgz) {
		this.lstykzgz = lstykzgz;
	}

	public double getQjryxc() {
		return qjryxc;
	}

	public void setQjryxc(double qjryxc) {
		this.qjryxc = qjryxc;
	}

	public double getBlc() {
		return blc;
	}

	public void setBlc(double blc) {
		this.blc = blc;
	}

	public double getGdbc() {
		return gdbc;
	}

	public void setGdbc(double gdbc) {
		this.gdbc = gdbc;
	}

	public double getZxjf() {
		return zxjf;
	}

	public void setZxjf(double zxjf) {
		this.zxjf = zxjf;
	}

	public double getGlkf() {
		return glkf;
	}

	public void setGlkf(double glkf) {
		this.glkf = glkf;
	}

	public double getSjfj() {
		return sjfj;
	}

	public void setSjfj(double sjfj) {
		this.sjfj = sjfj;
	}

	public double getDyxcbz() {
		return dyxcbz;
	}

	public void setDyxcbz(double dyxcbz) {
		this.dyxcbz = dyxcbz;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public double getQyllxc() {
		return qyllxc;
	}

	public void setQyllxc(double qyllxc) {
		this.qyllxc = qyllxc;
	}

	@Override
	public String toString() {
		return "StationExamNoticeResult [id=" + id + ", summaryId=" + summaryId + ", companyId=" + companyId
				+ ", noticeNo=" + noticeNo + ", yearMonthDate=" + yearMonthDate + ", wtgs=" + wtgs + ", jyz=" + jyz
				+ ", status=" + status + ", salaryStatus=" + salaryStatus + ", gasoline92=" + gasoline92
				+ ", gasoline95=" + gasoline95 + ", gasoline98=" + gasoline98 + ", cng=" + cng + ", cyxl0=" + cyxl0
				+ ", cyxl10=" + cyxl10 + ", rgfy=" + rgfy + ", qycmbxc=" + qycmbxc + ", dyrgfbz=" + dyrgfbz + ", sjlxj="
				+ sjlxj + ", qyzsxc=" + qyzsxc + ", jsjykxc=" + jsjykxc + ", jykdzxc=" + jykdzxc + ", xcfczkcze="
				+ xcfczkcze + ", czkcze=" + czkcze + ", czkxse=" + czkxse + ", zzfkzdcz=" + zzfkzdcz + ", jykkhcze="
				+ jykkhcze + ", fypjcxc=" + fypjcxc + ", fypmbwcqkjc=" + fypmbwcqkjc + ", fdzxy=" + fdzxy + ", ybsp="
				+ ybsp + ", ydczk=" + ydczk + ", zyppsxse=" + zyppsxse + ", yylm=" + yylm + ", qcyp=" + qcyp + ", tg="
				+ tg + ", rybhjnjxse=" + rybhjnjxse + ", zyppblsxse=" + zyppblsxse + ", olz=" + olz + ", gqxlxse="
				+ gqxlxse + ", zykzjjddrygz=" + zykzjjddrygz + ", jbf=" + jbf + ", lstykzgz=" + lstykzgz + ", qjryxc="
				+ qjryxc + ", blc=" + blc + ", gdbc=" + gdbc + ", zxjf=" + zxjf + ", glkf=" + glkf + ", sjfj=" + sjfj
				+ ", dyxcbz=" + dyxcbz + ", total=" + total + ", statusStr=" + statusStr + ", jyzType=" + jyzType
				+ ", qyllxc=" + qyllxc + "]";
	}
}