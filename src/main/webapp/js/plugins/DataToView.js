/**
 * 该文件定义 数据库到视图的字段映射
 */
~function(){
	var DataToView = {};

	// Example:
	/**
	 * @param {String}
	 *            id 列ID
	 * @param {Object}
	 *            totalSummary 汇总
	 * @param {String}
	 *            field 数据库字段名称
	 * @param {String}
	 *            show 前端显示的列名称
	 * @param {String}
	 *            align 缩进方式
	 * @param {String}
	 *            render 单元格渲染方法名
	 * @param {Boolean}
	 *            isSort 是否允许排序
	 * @param {Number}
	 *            span 详情表格-占据几行
	 */
	void	{	
	 			id:'id',
	 			field:'database_field',
	 			show:'对应列的标题名称',
	 			align:'right',
	 			render:'cellRenderFunction',
	 			isSort:false,
	 			span: 2,
	 			func: '详情表格方法名'
			}

	// 示例代码
	DataToView.grid = [
		{field:'CustomerID', show:'主键', align:'right'},
		{field:'CompanyName', show:'公司名', width:140}
	];
	DataToView.gridAll = [
		{show: '主键', field: 'CustomerID', align: 'left', width: 120 } ,
        { show: '公司名', field: 'CompanyName', minWidth: 60 },
        { show: '联系名', field: 'ContactName', width: 50, align: 'left' },
        { show: '联系名', field: 'ContactName', minWidth: 140 }, 
        { show: '城市', field: 'City', span: 2, func:'infoDialogColumnRender' }
	];
	
   //报表相关URL
	DataToView.RptURL={
        findLoanExpiredVehicle:'../loan/expired', //贷款到期车辆 统计,查询URL		
        exportLoanExpiredVehicle:'../loan/exportexpired', //贷款到期车辆 统计,查询URL
        findLogCmdSended:'../cmd/log/findsendcmd',//日志查询，下发指令统计
        exportLogCmdSended:'../cmd/log/exportsendcmd',//日志查询，导出下发指令
        findLogUserLoginHistory:'../log/finduserloginhistory',//日志查询，用户登录退出日志查询
        exportLogUserLoginHistory:'../log/exportuserloginhistory',//日志查询，导出用户登录退出日志
		queryOperationRecords:'../logger/queryOperationRecordsInfoPage',//操作记录查询
		exportOperationRecords:'../logger/exportOperationRecordsInfo',//导出操作记录
	};
	
	/***************************考核结果管理**********************************/
	DataToView.examSummaryURL={////////////////////////////////////////////
			query:'../examSummary/queryExamSummarysPage',			
			uploadExcel:'../examSummary/uploadExcel',
			genderExamNotice:'../examSummary/genderExamNotice',
			exportExamSummary:'../examSummary/exportExamSummary'
			
	};
	DataToView.examSummaryHead= [
		{show:'是否生成通知单',field:'yearMonthDate',width:150,align:'center',render:function(rowdata,index,value){
    		var status=rowdata['status'];
    		if(parseInt(status) ==0){
    			return '未生成';
    		}else{
    			return '已生成';
    		}
    	}},
		{show:'汇总年月',field:'yearMonthDate',width:100,align:'center'},
		{show:'分版块',field:'fbkName',width:100,align:'center'},
		{show:'单位',field:'dwName',width:120,align:'center'},
		{show:'BW代码',field:'bwCode',width:120,align:'center'},
		{show:'部门',field:'bnName',width:140,align:'center'},
		{show:'加油站',field:'jyzName',width:100,align:'center'},
		{show:'加油站分类',field:'jyzType',width:100,align:'center'},
		{show:'92#汽油',field:'gasoline92',width:100,align:'center'},
		{show:'95#汽油',field:'gasoline95',width:100,align:'center'},
		{show:'98#汽油',field:'gasoline98',width:100,align:'center'},
		{show:'0#柴油',field:'diesel0',width:100,align:'center'},
		{show:'10#号柴油',field:'diesel10',width:100,align:'center'},
		{show:'天然气',field:'trq',width:100,align:'center'},
		{show:'实际量小计',field:'sjlxj',width:100,align:'center'},
		{show:'吨油薪酬标准',field:'dyxcbz',width:120,align:'center'},
		{show:'薪酬',field:'xc',width:100,align:'center'},
		{show:'吨油附加标准',field:'dyfjbz',width:120,align:'center'},
		{show:'附加',field:'fj',width:100,align:'center'},
		{show:'吨油人工费标准',field:'dyrgfbz',width:120,align:'center'},
		{show:'人工费用',field:'rgfy',width:100,align:'center'},
		{show:'实际附加',field:'sjfj',width:100,align:'center'},
		{show:'单位社保',field:'dwsb',width:100,align:'center'},
		{show:'节日福利',field:'jrfl',width:100,align:'center'},
		{show:'日常劳保',field:'rclb',width:100,align:'center'},
		{show:'用餐补助',field:'ycbz',width:100,align:'center'},
		{show:'人事代理机构管理费',field:'rsdljgglf',width:140,align:'center'},
		{show:'轻油薪酬(含加班费及在站补助)',field:'qyxc',width:210,align:'center'},
		{show:'现场非充值卡充值额',field:'xcfczkcze',width:140,align:'center'},
		{show:'充值卡充值额',field:'czkcze',width:140,align:'center'},
		{show:'充值卡销售额',field:'czkxse',width:140,align:'center'},
		{show:'网厅充值额',field:'wtcze',width:140,align:'center'},
		{show:'自助发卡终端充值',field:'zzfkzdcz',width:140,align:'center'},
		{show:'加油卡考核充值额',field:'jykkhcze',width:140,align:'center'},
		{show:'计算加油卡薪酬',field:'jsjykxc',width:140,align:'center'},
		{show:'非定制香烟',field:'fdzxy',width:140,align:'center'},
//		{show:'一般商品（含酒类、饮料、食品、百货、汽车用品、音像图书、润滑油等）销售额（元）',field:'ybspxse',width:250,align:'center'},
		{show:'一般商品',field:'ybspxse',width:120,align:'center'},
		{show:'燃油宝和降凝剂销售额',field:'rybhjnjxse',width:160,align:'center'},
		{show:'自有品牌水销售额',field:'zyppsxse',width:140,align:'center'},
		{show:'尾气处理液销售额',field:'wqclyxse',width:140,align:'center'},
		{show:'赖茅酒销售额',field:'lmjxse',width:140,align:'center'},
		{show:'自有品牌玻璃水销售额',field:'zyppblsxse',width:180,align:'center'},
		{show:'欧露纸销售额',field:'olzxse',width:130,align:'center'},
		{show:'枸杞系列销售额',field:'gqxlxse',width:140,align:'center'},
		{show:'充值卡销售额',field:'ydczk',width:130,align:'center'},
		{show:'彩票销售额',field:'cpxse',width:130,align:'center'},
		{show:'异业联盟销售额',field:'yylmxse',width:140,align:'center'},
		{show:'汽车养护销售额',field:'qcyhxse',width:140,align:'center'},
		{show:'团购销售额',field:'tgxse',width:130,align:'center'},
		{show:'非油品基础薪酬',field:'fypjcxc',width:140,align:'center'},
		{show:'非油品目标完成奖惩金额',field:'fypmbwcjcje',width:200,align:'center'},
		{show:'轻油折算薪酬',field:'qyzsxc',width:140,align:'center'},
		{show:'加油卡调整薪酬',field:'jykdzxc',width:140,align:'center'},
		{show:'零售量当月任务',field:'lsldyrw',width:140,align:'center'},
		{show:'零售量当月完成量',field:'lsldywcl',width:140,align:'center'},
		{show:'零售量月度任务完成率',field:'lslydrwwcl',width:220,align:'center'},
		{show:'零售量月度任务完成率占比',field:'lslydrwwclzb',width:220,align:'center'},
		{show:'天然气当月任务',field:'trqdyrw',width:140,align:'center'},
		{show:'天然气当月完成量',field:'trqdywcl',width:140,align:'center'},
		{show:'天然气月度任务完成率',field:'trqydrwwcl',width:180,align:'center'},
		{show:'天然气月度任务完成率占比',field:'trqydrwwclzb',width:180,align:'center'},
		{show:'零售月度毛利额当月任务',field:'lsydmledyrw',width:180,align:'center'},
		{show:'零售月度毛利额当月完成量',field:'lsydmledywcl',width:220,align:'center'},
		{show:'零售月度毛利额月度任务完成率',field:'lsydmleydrwwcl',width:220,align:'center'},
		{show:'零售月度毛利额月度任务完成率占比',field:'lsydmleydrwwclzb',width:230,align:'center'},
		{show:'非油品销售额当月任务',field:'fypxsedyrw',width:180,align:'center'},
		{show:'非油品销售额当月完成量',field:'fypxsedywcl',width:180,align:'center'},
		{show:'非油品销售额月度任务完成率',field:'fypxseydrwwcl',width:220,align:'center'},
		{show:'非油品销售额月度任务完成率占比',field:'fypxseydrwwclzb',width:230,align:'center'},
		{show:'商业客户部非油品销售额当月任务',field:'sykhbfypxsedyrw',width:230,align:'center'},
		{show:'商业客户部非油品销售额当月完成量',field:'sykhbfypxsedywcl',width:240,align:'center'},
		{show:'商业客户部非油品销售额月度任务完成率',field:'sykhbfypxseydrwwcl',width:270,align:'center'},
		{show:'商业客户部非油品销售额月度任务完成率占比',field:'sykhbfypxseydrwwclzb',width:290,align:'center'},
		{show:'非油品月度毛利额当月任务',field:'fypydmledyrw',width:180,align:'center'},
		{show:'非油品月度毛利额当月完成量',field:'fypydmledywcl',width:190,align:'center'},
		{show:'非油品月度毛利额月度任务完成率',field:'fypydmleydrwwcl',width:220,align:'center'},
		{show:'非油品月度毛利额月度任务完成率占比',field:'fypydmleydrwwclzb',width:250,align:'center'},
		{show:'直批量当月任务',field:'zpldyrw',width:140,align:'center'},
		{show:'直批量当月完成量',field:'zpldywcl',width:140,align:'center'},
		{show:'直批量月度任务完成率',field:'zplydrwwcl',width:160,align:'center'},
		{show:'直批量月度任务完成率占比',field:'zplydrwwclzb',width:200,align:'center'},
		{show:'直批月度毛利额当月任务',field:'zpydmledyrw',width:180,align:'center'},
		{show:'直批月度毛利额当月完成量',field:'zpydmledywcl',width:200,align:'center'},
		{show:'直批月度毛利额月度任务完成率',field:'zpydmleydrwwcl',width:210,align:'center'},
		{show:'直批月度毛利额月度任务完成率占比',field:'zpydmleydrwwclzb',width:230,align:'center'},
		{show:'月度利润当月任务',field:'ydlrdyrw',width:145,align:'center'},
		{show:'月度利润当月完成量',field:'ydlrdywcl',width:160,align:'center'},
		{show:'月度利润月度任务完成率',field:'ydlrydrwwcl',width:180,align:'center'},
		{show:'月度利润月度任务完成率占比',field:'ydlrydrwwclzb',width:200,align:'center'},
		{show:'上年月均出库量',field:'snyjckl',width:140,align:'center'},
		{show:'总出库量不含寄外库发油',field:'zcklbhjwkfy',width:180,align:'center'},
		{show:'其中跨区出库吨数',field:'qzkqckds',width:140,align:'center'},
		{show:'出库量控制系数下限',field:'cklkzxsxx',width:140,align:'center'},
		{show:'出库量控制系数上限',field:'cklkzxssx',width:140,align:'center'},
		{show:'管理得分',field:'gldf',width:100,align:'center'},
		{show:'管理指标占比',field:'glzbzb',width:120,align:'center'},
		{show:'县公司经营部班子管理贡献系数',field:'xgsjybbzglgxxs',width:220,align:'center'},
		{show:'综合得分率',field:'zhdfl',width:100,align:'center'},
		{show:'联量挂钩系数',field:'llggxs',width:110,align:'center'},
		{show:'基本薪酬',field:'jbxc',width:100,align:'center'},
		{show:'联量薪酬',field:'llxc',width:140,align:'center'},
		{show:'整月看站及借调等人员工资',field:'zykzjjddrygz',width:200,align:'center'},
		{show:'加班费(看站及其他特定非专项奖励加班费)',field:'jbf',width:280,align:'center'},
		{show:'请假人员薪酬',field:'qjryxc',width:140,align:'center'},
		{show:'过渡补差',field:'gdbc',width:100,align:'center'},
		{show:'保留差',field:'blc',width:100,align:'center'},
		{show:'其他补贴',field:'qtbt',width:100,align:'center'},
		{show:'临时停业看站工资',field:'lstykzgz',width:140,align:'center'},
		{show:'专项奖罚',field:'zxjf',width:100,align:'center'},
		{show:'管理扣罚',field:'glkf',width:100,align:'center'},
		{show:'劳效薪酬',field:'lxxc',width:100,align:'center'},
		{show:'合计:薪酬性费用',field:'hj',width:140,align:'center'}/**/
	];
	
	/***************************生成县部门考核通知单管理**********************************/
	DataToView.genderXBMExamSummaryURL={////////////////////////////////////////////
			query:'../examSummary/queryExamSummarysPage',			
			genderXbmExamSummary:'../departExamNotice/genderDepartExamNotice',
			deleteexamSummary:'../departExamNotice/deleteDepartExamNotice',
			updateexamSummary:'../departExamNotice/updateDepartExamNotice'
	};
	DataToView.genderXBMExamSummaryHead= [
		{show:'日期',field:'year_month_date',width:140,align:'center'},
		{show:'部门名称',field:'bn_name',width:140,align:'center'},
		{show:'零售量当月任务',field:'lsldyrw',width:140,align:'center'},
		{show:'零售量当月完成量',field:'lsldywcl',width:140,align:'center'},
		{show:'零售量月度任务完成率',field:'lslydrwwcl',width:200,align:'center'},
		{show:'零售量月度任务完成率占比',field:'lslydrwwclzb',width:220,align:'center'},
		{show:'天然气当月任务',field:'trqdyrw',width:140,align:'center'},
		{show:'天然气当月完成量',field:'trqdywcl',width:140,align:'center'},
		{show:'天然气月度任务完成率',field:'trqydrwwcl',width:140,align:'center'},
		{show:'天然气月度任务完成率占比',field:'trqydrwwclzb',width:200,align:'center'},
		{show:'零售月度毛利额当月任务',field:'lsydmledyrw',width:200,align:'center'},
		{show:'零售月度毛利额当月完成量',field:'lsydmledywcl',width:140,align:'center'},
		{show:'零售月度毛利额月度任务完成率',field:'lsydmleydrwwcl',width:200,align:'center'},
		{show:'零售月度毛利额月度任务完成率占比',field:'lsydmleydrwwclzb',width:220,align:'center'},
		{show:'非油品销售额当月任务',field:'fypxsedyrw',width:140,align:'center'},
		{show:'非油品销售额当月完成量',field:'fypxsedywcl',width:180,align:'center'},
		{show:'非油品销售额月度任务完成率',field:'fypxseydrwwcl',width:180,align:'center'},
		{show:'非油品销售额月度任务完成率占比',field:'fypxseydrwwclzb',width:220,align:'center'},
		{show:'商业客户部非油品销售额当月任务',field:'sykhbfypxsedyrw',width:220,align:'center'},
		{show:'商业客户部非油品销售额当月完成量',field:'sykhbfypxsedywcl',width:220,align:'center'},
		{show:'商业客户部非油品销售额月度任务完成率',field:'sykhbfypxseydrwwcl',width:220,align:'center'},
		{show:'商业客户部非油品销售额月度任务完成率占比',field:'sykhbfypxseydrwwclzb',width:250,align:'center'},
		{show:'非油品月度毛利额当月任务',field:'fypydmledyrw',width:160,align:'center'},
		{show:'非油品月度毛利额当月完成量',field:'fypydmledywcl',width:180,align:'center'},
		{show:'非油品月度毛利额月度任务完成率',field:'fypydmleydrwwcl',width:200,align:'center'},
		{show:'非油品月度毛利额月度任务完成率占比',field:'fypydmleydrwwclzb',width:220,align:'center'},
		{show:'直批量当月任务',field:'zpldyrw',width:140,align:'center'},
		{show:'直批量当月完成量',field:'zpldywcl',width:140,align:'center'},
		{show:'直批量月度任务完成率',field:'zplydrwwcl',width:140,align:'center'},
		{show:'直批量月度任务完成率占比',field:'zplydrwwclzb',width:160,align:'center'},
		{show:'直批月度毛利额当月任务',field:'zpydmledyrw',width:160,align:'center'},
		{show:'直批月度毛利额当月完成量',field:'zpydmledywcl',width:140,align:'center'},
		{show:'直批月度毛利额月度任务完成率',field:'zpydmleydrwwcl',width:180,align:'center'},
		{show:'直批月度毛利额月度任务完成率占比',field:'zpydmleydrwwclzb',width:220,align:'center'},
		{show:'月度利润当月任务',field:'ydlrdyrw',width:140,align:'center'},
		{show:'月度利润当月完成量',field:'ydlrdywcl',width:140,align:'center'},
		{show:'月度利润月度任务完成率',field:'ydlrydrwwcl',width:180,align:'center'},
		{show:'月度利润月度任务完成率占比',field:'ydlrydrwwclzb',width:200,align:'center'},
		{show:'脱销考核得分率',field:'',width:140,align:'center'},
		{show:'脱销考核得分率占比',field:'',width:140,align:'center'},
		{show:'吞吐量目标',field:'',width:140,align:'center'},
		{show:'进库量目标',field:'',width:140,align:'center'},
		{show:'出库量目标',field:'',width:140,align:'center'},
		{show:'实际吞吐量',field:'',width:140,align:'center'},
		{show:'总进库量',field:'',width:140,align:'center'},
		{show:'其中管输接卸吨数',field:'',width:140,align:'center'},
		{show:'其中铁路接卸吨数',field:'',width:140,align:'center'},
		{show:'其中公路接卸吨数',field:'',width:140,align:'center'},
		{show:'总出库量不含寄外库发油',field:'',width:140,align:'center'},
		{show:'其中跨区出库吨数',field:'',width:140,align:'center'},
		{show:'进库量目标完成率',field:'',width:140,align:'center'},
		{show:'出库量目标完成率',field:'',width:140,align:'center'},
		{show:'公司月度常态检查百分制站均扣分',field:'',width:140,align:'center'},
		{show:'各相关部门专项检查扣分',field:'',width:140,align:'center'},
		{show:'管理得分',field:'gldf',width:140,align:'center'},
		{show:'管理指标占比',field:'glzbzb',width:140,align:'center'},
		{show:'综合得分率',field:'zhdfl',width:140,align:'center'},
		{show:'联量挂钩系数',field:'llggxs',width:140,align:'center'},
		{show:'基本薪酬',field:'jbxc',width:140,align:'center'},
		{show:'联量挂钩后的联量薪酬',field:'fbk_name',width:140,align:'center'},
		{show:'加班费（看站及其他特定非专项奖励加班费）',field:'jbf',width:220,align:'center'},
		{show:'正式工过渡补差',field:'',width:140,align:'center'},
		{show:'专项奖金',field:'',width:140,align:'center'},
		{show:'专项扣罚',field:'',width:140,align:'center'},
		{show:'保留的津补贴合计',field:'',width:140,align:'center'},
		{show:'薪酬合计',field:'',width:140,align:'center'}
	];
	
	/***************************生成加油站考核通知单管理**********************************/
	DataToView.genderJYZExamSummaryURL={////////////////////////////////////////////
			query:'../examSummary/queryExamSummarysPage',			
			uploadExcel:'../examSummary/uploadExcel',
			deleteexamSummary:'../examSummary/deleteexamSummary',
			updateexamSummary:'../examSummary/updateexamSummary'
	};
	DataToView.genderJYZExamSummaryHead= [
		{show:'日期',field:'year_month_date',width:140,align:'center'},
		{show:'委托公司名称',field:'wtgs',width:140,align:'center'},
		{show:'加油站',field:'jyz',width:140,align:'center'},
		{show:'E92#销量',field:'gasoline92',width:140,align:'center'},
		{show:'E95#销量',field:'gasoline95',width:140,align:'center'},
		{show:'E98#销量',field:'gasoline98',width:140,align:'center'},
		{show:'吨油联量兑现费用',field:'dylldxfy',width:140,align:'center'},
		{show:'吨油人工费用标准',field:'dyrgfybz',width:140,align:'center'},
		{show:'考核销量',field:'khxl',width:140,align:'center'},
		{show:'CNG',field:'CNG',width:140,align:'center'},
		{show:'柴油销量',field:'cyxl',width:140,align:'center'},
		{show:'轻油代理手续费合计',field:'qydlsxfhj',width:140,align:'center'},
		{show:'薪酬',field:'xc',width:140,align:'center'},
		{show:'附加',field:'fj',width:140,align:'center'},
		{show:'本区降价因素调增减薪酬费用',field:'bqjjysdzjxcfy',width:140,align:'center'},
		{show:'实际产生吨油附加',field:'sjcsdyfj',width:140,align:'center'},
		{show:'员工社保单位部分',field:'ygsbdwbf',width:140,align:'center'},
		{show:'人事代理机构管理费',field:'rsdljgglf',width:140,align:'center'},
		{show:'节日福利',field:'jrfl',width:140,align:'center'},
		{show:'日常劳保',field:'rclb',width:140,align:'center'},
		{show:'用餐补助',field:'ycbz',width:140,align:'center'},
		{show:'社保结余',field:'sbjy',width:140,align:'center'},
		{show:'实际产生薪酬性费用',field:'sjcsxcxfy',width:140,align:'center'},
		{show:'加油卡联量薪酬',field:'jykllxc',width:140,align:'center'},
		{show:'其中现场充值',field:'qzxccz',width:140,align:'center'},
		{show:'充值卡充值额',field:'czkcze',width:140,align:'center'},
		{show:'充值卡销售额',field:'czkxse',width:140,align:'center'},
		{show:'网厅充值额',field:'wtcze',width:140,align:'center'},
		{show:'自助发卡终端充值',field:'zzfkzdcz',width:140,align:'center'},
		{show:'加油卡考核充值额',field:'jykkhcze',width:140,align:'center'},
		{show:'加油卡调整薪酬',field:'jykdzxc',width:140,align:'center'},
		{show:'非油品联量薪酬',field:'fypllxc',width:140,align:'center'},
		{show:'非定制香烟零售',field:'fdzxyls',width:140,align:'center'},
		{show:'一般商品',field:'ybsp',width:140,align:'center'},
		{show:'移动充值卡',field:'ydczk',width:140,align:'center'},
		{show:'彩票销售额',field:'cpxse',width:140,align:'center'},
		{show:'异业联盟ETC销售额',field:'yylmETCxse',width:140,align:'center'},
		{show:'汽车养护销售额',field:'qcyhxse',width:140,align:'center'},
		{show:'团购所有商品销售额',field:'tgsyspxse',width:140,align:'center'},
		{show:'燃油宝和降凝剂销售额',field:'rybhjnjxse',width:140,align:'center'},
		{show:'自有品牌玻璃水销售额',field:'zyppblsxse',width:140,align:'center'},
		{show:'欧露纸销售额',field:'olzxse',width:140,align:'center'},
		{show:'枸杞系列销售额',field:'gqxlxse',width:140,align:'center'},
		{show:'非油品目标完成奖惩金额',field:'fypmbwcjcje',width:140,align:'center'},
		{show:'其他薪酬',field:'qtxc',width:140,align:'center'},
		{show:'其他费用',field:'qtfy',width:140,align:'center'},
		{show:'状态(1-生成考核通知单未下发;2-已下发部门未确认;3-部门已确认接收通知单;4-已下发加油站,未确认;5-加油站已确认通知单;)',field:'status',width:140,align:'center'}		
	];
		
		/*********************************** 机构信息管理***********************************/
		//机构信息管理
		DataToView.jgglURL={
			queryQRcodePicture:'../twgAgency/queryQRcodePicture',//查看图片
			findQRcodeExists:'../twgAgency/findQRcodeExists',//判断机构二维码是否存在
			insertQRcode:'../twgAgency/insertQRcode',//上传二维码图片
			updateQRcode:'../twgAgency/updateQRcode'	
		};
		DataToView.CompanyManageGrid = [
			{show:'机构名称', field:'companyName', width:250,align:'center'},
			{show:'机构地址', field:'companyAddress',width:150,align:'center'},		
			{show:'邮编', field:'companyPost', width:120,align:'center'},
			{show:'工作电话', field:'workPhone',width:250,align:'center'},	
			{show:'传真', field:'fax',width:150,align:'center'},
			{show:'负责人', field:'leader',width:150,align:'center'},
			{show:'负责人电话', field:'leaderPhone',width:150,align:'center'},
			{show:'负责人邮箱', field:'leaderEmail',width:150,align:'center'}
		];
		/*********************************** 用户管理 ***********************************/
		//liujie 
		//用户管理相关
		DataToView.UserManageURL={
			 exportusermanage:'../usermanage/manager/exportUser',//用户信息管理报表导出
			 queryUser:'../usermanage/manager/finduser', //用户管理，查找用户URL
			 addUser:'../usermanage/manager/adduser',//用户管理添加用户
		     deleteUser:'../usermanage/manager/deleteuser',//删除用户
		     updateUser:'../usermanage/manager/updateuser',//修改用户
		     validatePassword:'../usermanage/user/updatepassword/validate',//用户修改密码，验证旧密码
		     updatePassword:'../usermanage/user/updatepassword/update'//用户修改密码
	    }
	   //用户管理，主表格参数
		DataToView.UserManageGrid = [
			{show:'登录名', field:'loginName', width:120,align:'center'},
			{show:'用户姓名', field:'userName',width:150,align:'center'},		
			{show:'角色', field:'roleName', width:120,align:'center'},
			{show:'机构', field:'companyName',width:250,align:'center'},	
			{show:'账户状态', field:'status',width:150,align:'center'}/*,
			{show:'电话', field:'mobile',width:150,align:'center'},
			{show:'邮箱', field:'email',width:150,align:'center'}*/		
		];
		
		/*********************************** 角色管理 ***********************************/
		DataToView.RoleMangeGird=[
	        {show:'角色名称', field:'roleName', width:130,align:'center'},
	  		{show:'是否删除', field:'isdeleted',width:120,align:'center',render:function(rowData){
	  			if(rowData['isdeleted'] == 0){
	  				return '未删除';
	  			}
	  			if(rowData['isdeleted'] == 1){
	  				return '已删除';
	  			}
	  		}},
	  		{show:'备注', field:'remark',width:150,align:'center'},
	  		{show:'最后更新时间', field:'updatestamp', width:180,align:'center'}
	  	];
		DataToView.RoleMangeURL={
			addRoles:'../role/addRoles',
			queryRoles:'../role/queryRolesPage',
			updateRoles:'../role/updateRole',
			deleteRoles:'../role/deleteRoles',
	        changeRoles:'../role/queryChangeRolesByUid'
		};
		

		DataToView.PermissionMangeURL = {
				distRolePermissions : '../module/distRolePermissions'// 分配权限
		};
		
		/*********************************** 工资管理 ***********************************/
		DataToView.salaryURL = {
				query:'../salary/findAllSalaryDetailByPage',
				uploadSalaryExcel:'../salary/uploadSalaryExcel',
				exportSalary:'../salary/exportSalary'
		}
		DataToView.salaryGird= [
		        {show:'通知单号',field:'noticeNo',width:140,align:'center'},
				/*{show:'状态',field:'status',width:160,align:'center',render:function(rowdata, index, value){
					if(parseInt(rowdata['salaryStatus']) == 1){//未提交
						return '加油站未提交';
					}else if(parseInt(rowdata['salaryStatus']) == 2){//已提交，未审核
						return '加油站提交,部门未审核';
					}else if(parseInt(rowdata['salaryStatus']) == 3){//部门已审核
						return '加油站提交,部门未审核';
					}else if(parseInt(rowdata['salaryStatus']) == 4){//部门已提交加油站工资明细表,未审核
						return '部门已提交,市公司未审核';
					}else if(parseInt(rowdata['salaryStatus']) == 5){//市公司已审核;
						return '市公司审核通过';
					}else if(parseInt(rowdata['salaryStatus']) == -1){//审核未通过
						return '市公司审核未通过';
					}
				}},*/
				{show:'单位',field:'dw',width:130,align:'center'},
				{show:'姓名',field:'xm',width:80,align:'center'},
				{show:'身份证号',field:'sfzh',width:150,align:'center'},
				{show:'岗位',field:'gw',width:100,align:'center'},
				{show:'基本薪酬',field:'jbxc',width:100,align:'center'},
				{show:'联量薪酬',field:'llxc',width:100,align:'center'},
				{show:'加油卡薪酬',field:'jykxc',width:100,align:'center'},
				{show:'非油品薪酬',field:'fypxc',width:100,align:'center'},
				{show:'加班费',field:'jbf',width:60,align:'center'},
				{show:'过渡补差',field:'gdbc',width:70,align:'center'},
				{show:'其他工资',field:'qtgz',width:70,align:'center'},
				{show:'专项奖金',field:'zxjj',width:70,align:'center'},
				{show:'专项扣罚',field:'zxkf',width:70,align:'center'},
				{show:'其他奖罚',field:'qtjf',width:70,align:'center'},
				{show:'其他津贴补助等',field:'qtjtbzd',width:120,align:'center'},
				{show:'应发合计',field:'yfhj',width:100,align:'center'},
				{show:'公积金',field:'gjj',width:60,align:'center'},
				{show:'养老金',field:'ylj',width:60,align:'center'},
				{show:'失业金',field:'syj',width:60,align:'center'},
				{show:'医保金',field:'ybj',width:60,align:'center'},
				{show:'年金',field:'nj',width:60,align:'center'},
				{show:'个人所得税',field:'grsds',width:110,align:'center'},
				{show:'实发工资',field:'sfgz',width:80,align:'center'},
				{show:'帐号',field:'zh',width:150,align:'center'},
				{show:'签名',field:'qm',width:80,align:'center'},
				{show:'新开户日期',field:'xkhrq',width:140,align:'center'},
				{show:'借支',field:'jz',width:60,align:'center'},
				{show:'卫生费',field:'wsf',width:60,align:'center'},
				{show:'房费',field:'ff',width:60,align:'center'},
				{show:'电费',field:'df',width:60,align:'center'},
				{show:'暖气费',field:'nqf',width:60,align:'center'},
				{show:'打卡金额',field:'dkje',width:80,align:'center'},
				{show:'已发需要合并扣税项合计',field:'yfxyhbksxhj',width:180,align:'center'},
				{show:'公积金单位每月缴费',field:'gjjdwmyjf',width:150,align:'center'},
				{show:'养老单位每月缴费',field:'yldwmyjf',width:140,align:'center'},
				{show:'失业单位每月缴费',field:'sydwmyjf',width:140,align:'center'},
				{show:'工伤单位每月缴费',field:'gsdwmyjf',width:140,align:'center'},
				{show:'医保单位每月缴费',field:'ybdwmyjf',width:140,align:'center'},
				{show:'生育保险单位每月缴费',field:'sybxdwmyjf',width:160,align:'center'},
				{show:'大病保险单位每月缴费',field:'dbbxdwmyjf',width:160,align:'center'},
				{show:'年金企业缴费每月划入个人账户金额',field:'njqyjfmyhrgrzhje',width:235,align:'center'},
				{show:'年金企业缴费',field:'njqyjf',width:120,align:'center'},
				{show:'管理费',field:'glf',width:60,align:'center'},
				{show:'委托公司名称',field:'wtgsmc',width:120,align:'center'},
				{show:'社保参保地',field:'sbcbd',width:120,align:'center'}
		     ];
		/** 工资考核通知单号 **/
		DataToView.salaryNoticeGird= [
			{show:'年月',field:'yearMonthDate',width:100,align:'center'},	
			{show:'加油站名称',field:'jyz',width:105,align:'center'},
			{show:'通知单号',field:'noticeNo',width:140,align:'center'},
	        {show:'考核通知单状态',field:'status',width:140,align:'center',render:function(rowdata, index, value){
				if(parseInt(rowdata['status']) == 1){
					return '通知单未下发';
				}
				if(parseInt(rowdata['status']) == 2){
					return '县级部门未确认';
				}
				if(parseInt(rowdata['status']) == 3){
					return '县级部门已确认';
				}
				if(parseInt(rowdata['status']) == 4){
					return '下发至加油站';
				}
				if(parseInt(rowdata['status']) == 5){
					return '加油站已接收';
				}				
			}},
			{show:'工资通知单状态',field:'salaryStatus',width:120,align:'center',render:function(rowdata, index, value) {
				if(parseInt(rowdata['salaryStatus']) == 0){
					return '未导入';
				}
				if(parseInt(rowdata['salaryStatus']) == 1){
					return '未提交';
				}
				if(parseInt(rowdata['salaryStatus']) == 2){
					return '已提交,未审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 3){
					return '部门已审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 4){
					return '部门提交，未审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 5){
					return '市公司已审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 6){
					return '市公司已导入';
				}
				if(parseInt(rowdata['salaryStatus']) == -1){
					return '市公司审核未通过';
				}
			}}
			/*,
			{show:'应发合计',field:'yfhj',width:90,align:'center'},
			{show:'工资单合计',field:'total',width:90,align:'center'}*/
	     ];
		//部门工资通知单表头
		DataToView.BmSalaryNoticeGird= [
			{show:'年月',field:'yearMonthDate',width:100,align:'center'},	
			{show:'部门名称',field:'bmmc',width:105,align:'center'},
			{show:'通知单号',field:'noticeNo',width:140,align:'center'},
	        {show:'考核通知单状态',field:'status',width:140,align:'center',render:function(rowdata, index, value){
				if(parseInt(rowdata['status']) == 1){
					return '通知单未下发';
				}
				if(parseInt(rowdata['status']) == 2){
					return '县级部门未确认';
				}
				if(parseInt(rowdata['status']) == 3){
					return '县级部门已确认';
				}
				if(parseInt(rowdata['status']) == 4){
					return '下发至加油站';
				}
				if(parseInt(rowdata['status']) == 5){
					return '加油站已接收';
				}				
			}},
			{show:'工资通知单状态',field:'salaryStatus',width:120,align:'center',render:function(rowdata, index, value) {
				if(parseInt(rowdata['salaryStatus']) == 0){
					return '未导入';
				}
				if(parseInt(rowdata['salaryStatus']) == 1){
					return '未提交';
				}
				if(parseInt(rowdata['salaryStatus']) == 2){
					return '已提交,未审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 3){
					return '部门已审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 4){
					return '部门提交，未审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 5){
					return '市公司已审核';
				}
				if(parseInt(rowdata['salaryStatus']) == 6){
					return '市公司已导入';
				}
				if(parseInt(rowdata['salaryStatus']) == -1){
					return '市公司审核未通过';
				}
			}}
			/*,
			{show:'应发合计',field:'yfhj',width:90,align:'center'},
			{show:'工资单合计',field:'total',width:90,align:'center'}*/
	     ];
		/*********************************** 部门考核通知单管理 ***********************************/
		//liujie 
		//部门考核通知单管理相关
		DataToView.BmkhtzdManageURL={
			 queryBmkhtzd:'../bmkhtzdmanage/queryBmkhtzd', //查询部门考核通知单
			 operateBmkhtzd:'../bmkhtzdmanage/operateBmkhtzd',//操作部门考核通知单
			 batchOperateBmkhtzd:'../bmkhtzdmanage/batchOperateBmkhtzd',//批量操作部门考核通知单
			 exportBmkhtzdmanage:'../bmkhtzdmanage/exportBmkhtzd',  //导出部门考核通知单
			 /*************** 部门工资通知单管理 ***************/
			 queryBmGzNoticePage:'../bmkhtzdmanage/queryBmGzNoticePage',
			 opBmNoticeSalaryStatus:'../bmkhtzdmanage/opBmNoticeSalaryStatus',
			 batchopBmNoticeSalaryStatus:'../bmkhtzdmanage/batchopBmNoticeSalaryStatus'
	    }
	   //部门考核通知单管理，主表格参数
		DataToView.BmkhtzdManageGrid = [
		    {show:'日期', field:'yearMonthDate', width:90,align:'center'},
			{show:'通知单号', field:'noticeNo', width:200,align:'center'},
			{show:'状态', field:'status',width:140,align:'center',render:function(rowdata,index,value){
				if(parseInt(rowdata['status']) == 1){
					return '未下发';
				}else if(parseInt(rowdata['status']) == 2){
					return '已下发县级部门,未确认';
				}else if(parseInt(rowdata['status']) == 3){
					return '县级部门已确认接收';
				}else if(parseInt(rowdata['status']) == 4){
					return '已下发加油站,未确认';
				}else if(parseInt(rowdata['status']) == 5){
					return '加油站已确认接收';
				}else if(parseInt(rowdata['status']) == -1){
					return '-1';
				}
			}},	
			{show:'部门', field:'bmmc',width:140,align:'center'},		
			{show:'薪酬性费用', field:'xcxfy',width:110,align:'center'},
			{show:'联量挂钩系数', field:'llggxs',width:100,align:'center'},	
			{show:'基本薪酬', field:'jbxc',width:90,align:'center'},
			{show:'联量薪酬', field:'llxc',width:90,align:'center'},	
			{show:'请假人员工资', field:'qjrygz',width:100,align:'center'}	,	
			{show:'过渡补差', field:'gdbc',width:90,align:'center'},		
			{show:'保留差', field:'blc',width:90,align:'center'},		
			{show:'其他补贴', field:'qtbt',width:90,align:'center'},		
			{show:'加班费', field:'jbf',width:90,align:'center'},	
			{show:'其他工资', field:'qtgz',width:90,align:'center'},
			{show:'专项奖罚', field:'zxjf',width:90,align:'center'},		
			{show:'管理扣罚', field:'glkf',width:90,align:'center'},		
			{show:'劳效薪酬', field:'lxxc',width:90,align:'center'},
		];
		
		/*********************************** 加油站考核通知单管理 ***********************************/
		//liujie 
		//加油站考核通知单管理相关
		DataToView.JyzkhtzdManageURL={
				queryJyzkhtzd:'../jyzkhtzdmanage/queryJyzkhtzd', //查询加油站考核通知单
				operateJyzkhtzd:'../jyzkhtzdmanage/operateJyzkhtzd',//操作加油站考核通知单
				batchOperateJyzkhtzd:'../jyzkhtzdmanage/batchOperateJyzkhtzd',//操作加油站考核通知单
				updateJyzkhtzd:'../jyzkhtzdmanage/updateJyzkhtzd',//修改加油站考核通知单
				exportJyzkhtzdmanage:'../jyzkhtzdmanage/exportJyzkhtzdmanage',//导出加油站考核通知单
				queryCanAllocation:'../jyzkhtzdmanage/queryCanAllocation',//查询几个可二次分配的参数值分别是多少
				/********加油站工资通知单********/
				queryJyzGzNoticePage:'../jyzkhtzdmanage/queryJyzGzNoticePage',
				opJyzNoticeSalaryStatus:'../jyzkhtzdmanage/opJyzNoticeSalaryStatus',
				batchOpJyzNoticeSalaryStatus:'../jyzkhtzdmanage/batchOpJyzNoticeSalaryStatus'
	    }
	   //加油站考核通知单管理，主表格参数
		DataToView.JyzkhtzdManageGrid = [
            {show:'日期', field:'yearMonthDate', width:80,align:'center'},
			{show:'通知单号',field:'noticeNo',width:180,align:'center'},
			{show:'单位',field:'wtgs',width:120,align:'center'},
			{show:'加油站',field:'jyz',width:100,align:'center'},
			{show:'状态', field:'status',width:140,align:'center',render:function(rowdata,index,value){
				if(parseInt(rowdata['status']) == 1){
					return '未下发';
				}else if(parseInt(rowdata['status']) == 2){
					return '已下发县级部门,未确认';
				}else if(parseInt(rowdata['status']) == 3){
					return '县级部门已确认接收';
				}else if(parseInt(rowdata['status']) == 4){
					return '已下发加油站,未确认';
				}else if(parseInt(rowdata['status']) == 5){
					return '加油站已确认接收';
				}else if(parseInt(rowdata['status']) == 6){
					return '部门修改后,还未下发';
				}else if(parseInt(rowdata['status']) == -1){
					return '-1';
				}
			}},
			{show:'92汽油#销量',field:'gasoline92',width:120,align:'center'},
			{show:'95汽油#销量',field:'gasoline95',width:120,align:'center'},
			{show:'98汽油#销量',field:'gasoline98',width:120,align:'center'},
			{show:'天然气',field:'cng',width:100,align:'center'},
			{show:'0#柴油销量',field:'cyxl0',width:100,align:'center'},
			{show:'10#柴油销量',field:'cyxl10',width:100,align:'center'},
			{show:'人工费用',field:'rgfy',width:100,align:'center'},
			{show:'轻油超目标薪酬',field:'qycmbxc',width:120,align:'center'},
			{show:'吨油人工费标准',field:'dyrgfbz',width:120,align:'center'},
			{show:'实际量小计',field:'sjlxj',width:110,align:'center'},
			{show:'轻油折算薪酬',field:'qyzsxc',width:120,align:'center'},
			{show:'计算加油卡薪酬',field:'jsjykxc',width:120,align:'center'},
			{show:'加油卡调整薪酬',field:'jykdzxc',width:120,align:'center'},
			{show:'现场非充值卡充值额',field:'xcfczkcze',width:130,align:'center'},
			{show:'充值卡充值额',field:'czkcze',width:120,align:'center'},
			{show:'充值卡销售额',field:'czkxse',width:120,align:'center'},
			{show:'自助发卡终端充值',field:'zzfkzdcz',width:130,align:'center'},
			{show:'加油卡考核充值额',field:'jykkhcze',width:130,align:'center'},
			{show:'非油品基础薪酬',field:'fypjcxc',width:130,align:'center'},
			{show:'非油品目标完成情况奖惩',field:'fypmbwcqkjc',width:160,align:'center'},
			{show:'非定制香烟',field:'fdzxy',width:100,align:'center'},
			{show:'一般商品',field:'ybsp',width:100,align:'center'},
			{show:'移动充值卡',field:'ydczk',width:100,align:'center'},
			{show:'自有品牌水销售额',field:'zyppsxse',width:120,align:'center'},
			{show:'异业联盟',field:'yylm',width:100,align:'center'},
			{show:'汽车用品',field:'qcyp',width:100,align:'center'},
			{show:'团购',field:'tg',width:100,align:'center'},
			{show:'燃油宝和降凝剂销售额',field:'rybhjnjxse',width:160,align:'center'},
			{show:'自有品牌玻璃水销售额',field:'zyppblsxse',width:160,align:'center'},
			{show:'欧露纸',field:'olz',width:100,align:'center'},
			{show:'枸杞系列销售额',field:'gqxlxse',width:100,align:'center'},
			{show:'整月看站及借调等人员工资',field:'zykzjjddrygz',width:170,align:'center'},
			{show:'加班费',field:'jbf',width:100,align:'center'},
			{show:'临时停业看站工资',field:'lstykzgz',width:140,align:'center'},
			{show:'请假人员薪酬',field:'qjryxc',width:120,align:'center'},
			{show:'保留差',field:'blc',width:100,align:'center'},
			{show:'过渡补差',field:'gdbc',width:100,align:'center'},
			{show:'专项奖罚',field:'zxjf',width:100,align:'center'},
			{show:'管理扣罚',field:'glkf',width:100,align:'center'},
			{show:'实际附加',field:'sjfj',width:100,align:'center'},
			{show:'吨油薪酬标准',field:'dyxcbz',width:100,align:'center'},
			{show:'合计',field:'total',width:100,align:'center'}
		];
		
		/******************************加油站工资通知单*********************************/
		DataToView.JyzSalaryDetailGrid = [
			{show:'日期', field:'yearMonthDate', width:80,align:'center'},
			{show:'通知单号',field:'noticeNo',width:200,align:'center'},
			{show:'委托公司名称',field:'dw',width:140,align:'center'},
			{show:'姓名',field:'xm',width:100,align:'center'},
			{show:'身份证号',field:'sfzh',width:160,align:'center'},
			{show:'岗位',field:'gw',width:100,align:'center'},
			{show:'基本薪酬',field:'jbxc',width:100,align:'center'},
			{show:'联量薪酬',field:'llxc',width:100,align:'center'},
			{show:'非油品薪酬',field:'fypxc',width:100,align:'center'},
			{show:'加油卡薪酬',field:'jykxc',width:100,align:'center'},
			{show:'加班费',field:'jbf',width:100,align:'center'},
			{show:'过渡补差',field:'gdbc',width:100,align:'center'},
			{show:'其他工资',field:'qtgz',width:100,align:'center'},
			{show:'专项奖金',field:'zxjj',width:100,align:'center'},
			{show:'专项扣罚',field:'zxkf',width:100,align:'center'},
			{show:'其他奖罚',field:'qtjf',width:100,align:'center'},
			{show:'其他津贴补助等',field:'qtjtbzd',width:100,align:'center',render:function(rowdata,index,value){
				console.log(rowdata['qtjtbzd'] == 'null',rowdata['qtjtbzd'] == null , rowdata['qtjtbzd'] == '');
				if(rowdata['qtjtbzd'] == 'null' || rowdata['qtjtbzd'] == null || rowdata['qtjtbzd'] == ''){
					return '0';
				}
			}},
			{show:'应发合计',field:'yfhj',width:100,align:'center'},
			{show:'公积金',field:'gjj',width:60,align:'center'},
			{show:'养老金',field:'ylj',width:60,align:'center'},
			{show:'失业金',field:'syj',width:60,align:'center'},
			{show:'医保金',field:'ybj',width:60,align:'center'},
			{show:'年金',field:'nj',width:60,align:'center'},
			{show:'个人所得税',field:'grsds',width:110,align:'center'},
			{show:'实发工资',field:'sfgz',width:80,align:'center'},
			{show:'帐号',field:'zh',width:150,align:'center'},
			{show:'签名',field:'qm',width:80,align:'center'},
			{show:'新开户日期',field:'xkhrq',width:140,align:'center'},
			{show:'借支',field:'jz',width:60,align:'center'},
			{show:'卫生费',field:'wsf',width:60,align:'center'},
			{show:'房费',field:'ff',width:60,align:'center'},
			{show:'电费',field:'df',width:60,align:'center'},
			{show:'暖气费',field:'nqf',width:60,align:'center'},
			{show:'打卡金额',field:'dkje',width:80,align:'center'},
			{show:'已发需要合并扣税项合计',field:'yfxyhbksxhj',width:180,align:'center'},
			{show:'公积金单位每月缴费',field:'gjjdwmyjf',width:150,align:'center'},
			{show:'养老单位每月缴费',field:'yldwmyjf',width:140,align:'center'},
			{show:'失业单位每月缴费',field:'sydwmyjf',width:140,align:'center'},
			{show:'工伤单位每月缴费',field:'gsdwmyjf',width:140,align:'center'},
			{show:'医保单位每月缴费',field:'ybdwmyjf',width:140,align:'center'},
			{show:'生育保险单位每月缴费',field:'sybxdwmyjf',width:160,align:'center'},
			{show:'大病保险单位每月缴费',field:'dbbxdwmyjf',width:160,align:'center'},
			{show:'年金企业缴费每月划入个人账户金额',field:'njqyjfmyhrgrzhje',width:235,align:'center'},
			{show:'年金企业缴费',field:'njqyjf',width:120,align:'center'},
			{show:'管理费',field:'glf',width:60,align:'center'},
			{show:'委托公司名称',field:'wtgsmc',width:120,align:'center'},
			{show:'社保参保地',field:'sbcbd',width:120,align:'center'}
			];
		/******************************部门工资通知单*********************************/
		DataToView.BmSalaryDetailGrid = [
			{show:'日期', field:'yearMonthDate', width:80,align:'center'},
			{show:'通知单号',field:'noticeNo',width:200,align:'center'},
			{show:'委托公司名称',field:'dw',width:140,align:'center'},
			{show:'姓名',field:'xm',width:100,align:'center'},
			{show:'身份证号',field:'sfzh',width:160,align:'center'},
			{show:'岗位',field:'gw',width:100,align:'center'},
			{show:'基本薪酬',field:'jbxc',width:100,align:'center'},
			{show:'联量薪酬',field:'llxc',width:100,align:'center'},
			{show:'非油品薪酬',field:'fypxc',width:100,align:'center'},
			{show:'加油卡薪酬',field:'jykxc',width:100,align:'center'},
			{show:'加班费',field:'jbf',width:100,align:'center'},
			{show:'过渡补差',field:'gdbc',width:100,align:'center'},
			{show:'其他工资',field:'qtgz',width:100,align:'center'},
			{show:'专项奖金',field:'zxjj',width:100,align:'center'},
			{show:'专项扣罚',field:'zxkf',width:100,align:'center'},
			{show:'其他奖罚',field:'qtjf',width:100,align:'center'},
			{show:'其他津贴补助等',field:'qtjtbzd',width:100,align:'center',render:function(rowdata,index,value){
				console.log(rowdata['qtjtbzd'] == 'null',rowdata['qtjtbzd'] == null , rowdata['qtjtbzd'] == '');
				if(rowdata['qtjtbzd'] == 'null' || rowdata['qtjtbzd'] == null || rowdata['qtjtbzd'] == ''){
					return '0';
				}
			}},
			{show:'应发合计',field:'yfhj',width:100,align:'center'},
			{show:'公积金',field:'gjj',width:60,align:'center'},
			{show:'养老金',field:'ylj',width:60,align:'center'},
			{show:'失业金',field:'syj',width:60,align:'center'},
			{show:'医保金',field:'ybj',width:60,align:'center'},
			{show:'年金',field:'nj',width:60,align:'center'},
			{show:'个人所得税',field:'grsds',width:110,align:'center'},
			{show:'实发工资',field:'sfgz',width:80,align:'center'},
			{show:'帐号',field:'zh',width:150,align:'center'},
			{show:'签名',field:'qm',width:80,align:'center'},
			{show:'新开户日期',field:'xkhrq',width:140,align:'center'},
			{show:'借支',field:'jz',width:60,align:'center'},
			{show:'卫生费',field:'wsf',width:60,align:'center'},
			{show:'房费',field:'ff',width:60,align:'center'},
			{show:'电费',field:'df',width:60,align:'center'},
			{show:'暖气费',field:'nqf',width:60,align:'center'},
			{show:'打卡金额',field:'dkje',width:80,align:'center'},
			{show:'已发需要合并扣税项合计',field:'yfxyhbksxhj',width:180,align:'center'},
			{show:'公积金单位每月缴费',field:'gjjdwmyjf',width:150,align:'center'},
			{show:'养老单位每月缴费',field:'yldwmyjf',width:140,align:'center'},
			{show:'失业单位每月缴费',field:'sydwmyjf',width:140,align:'center'},
			{show:'工伤单位每月缴费',field:'gsdwmyjf',width:140,align:'center'},
			{show:'医保单位每月缴费',field:'ybdwmyjf',width:140,align:'center'},
			{show:'生育保险单位每月缴费',field:'sybxdwmyjf',width:160,align:'center'},
			{show:'大病保险单位每月缴费',field:'dbbxdwmyjf',width:160,align:'center'},
			{show:'年金企业缴费每月划入个人账户金额',field:'njqyjfmyhrgrzhje',width:235,align:'center'},
			{show:'年金企业缴费',field:'njqyjf',width:120,align:'center'},
			{show:'管理费',field:'glf',width:60,align:'center'},
			{show:'委托公司名称',field:'wtgsmc',width:120,align:'center'},
			{show:'社保参保地',field:'sbcbd',width:120,align:'center'}
			];
		//用户操作记录
		DataToView.operationRecordsGrid=[
			{show:'登录名',field:'login_name',width:150,align:'center'},
			{show:'用户名',field:'user_name',width:150,align:'center'},
			{show:'功能描述',field:'description',width:150,align:'center'},
			{show:'操作描述',field:'permissionsdesc',width:150,align:'center'},
			{show:'操作时间',field:'op_time',width:150,align:'center'},
			{show:'访问者ip地址',field:'remote_ip',width:150,align:'center'}
		];
		DataToView.operationRecordsURL={
			queryOperationRecords:'../logger/queryOperationRecordsInfoPage',//操作记录查询
			exportOperationRecords:'../logger/exportOperationRecordsInfo'//导出操作记录
	    }
		//数据库备份管理
		DataToView.databaseBakGrid=[
			{show:'登录名',field:'loginName',width:150,align:'center'},
			{show:'用户名',field:'userName',width:150,align:'center'},
			{show:'备份时间',field:'date',width:150,align:'center'},
			{show:'备份路径',field:'path',width:500,align:'center'}
		];
		DataToView.databaseBakURL={
			querydatabaseBak:'../databaseBak/querydatabaseBak',//数据库备份记录查询
			bakDatabase:'../databaseBak/bakDatabase',//备份数据库
	    }
	window.D2V = DataToView;	
}();
$(function(){
	$(".data-list").click(function(){
		var warm_box =$(".warm_box",parent.document);
		if (warm_box.is(':visible')) {  
			warm_box.slideUp();//隐藏   
     	}
		return;
	})
});
