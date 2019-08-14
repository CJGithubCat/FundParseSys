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
	 *            title 前端显示的列名称
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
	 			title:'对应列的标题名称',
	 			align:'right',
	 			render:'cellRenderFunction',
	 			isSort:false,
	 			span: 2,
	 			func: '详情表格方法名'
			}

	// 示例代码
	DataToView.grid = [
		{field:'CustomerID', title:'主键', align:'right'},
		{field:'CompanyName', title:'公司名', width:140}
	];
	DataToView.gridAll = [
		{title: '主键', field: 'CustomerID', align: 'left', width: 120 } ,
        { title: '公司名', field: 'CompanyName', minWidth: 60 },
        { title: '联系名', field: 'ContactName', width: 50, align: 'left' },
        { title: '联系名', field: 'ContactName', minWidth: 140 }, 
        { title: '城市', field: 'City', span: 2, func:'infoDialogColumnRender' }
	];
	  
		/*********************************** 部门考核通知单管理 ***********************************/
		//liujie 
		//部门考核通知单管理相关
		DataToView.JZTJURL={
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
		DataToView.JZTJGrid = [[
		    {title:'id', field:'id', width:90,align:'center'},
			{title:'基金Code', field:'fundCode', width:200,align:'center'},			
			{title:'基金净值', field:'netWorth',width:140,align:'center'},		
			{title:'星期', field:'weekInfo',width:110,align:'center',render:function(rowdata,index,value){
				return '星期'+ value;
			}},
			{title:'日期', field:'dateInfo',width:110,align:'center'}			
		]];
		
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
            {title:'日期', field:'yearMonthDate', width:80,align:'center'},
			{title:'通知单号',field:'noticeNo',width:180,align:'center'},
			{title:'单位',field:'wtgs',width:120,align:'center'},
			{title:'加油站',field:'jyz',width:100,align:'center'},
			{title:'状态', field:'status',width:140,align:'center',render:function(rowdata,index,value){
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
			{title:'92汽油#销量',field:'gasoline92',width:120,align:'center'},
			{title:'95汽油#销量',field:'gasoline95',width:120,align:'center'},
			{title:'98汽油#销量',field:'gasoline98',width:120,align:'center'},
			{title:'天然气',field:'cng',width:100,align:'center'},
			{title:'0#柴油销量',field:'cyxl0',width:100,align:'center'},
			{title:'10#柴油销量',field:'cyxl10',width:100,align:'center'},
			{title:'人工费用',field:'rgfy',width:100,align:'center'},
			{title:'轻油超目标薪酬',field:'qycmbxc',width:120,align:'center'},
			{title:'吨油人工费标准',field:'dyrgfbz',width:120,align:'center'},
			{title:'实际量小计',field:'sjlxj',width:110,align:'center'},
			{title:'轻油折算薪酬',field:'qyzsxc',width:120,align:'center'},
			{title:'计算加油卡薪酬',field:'jsjykxc',width:120,align:'center'},
			{title:'加油卡调整薪酬',field:'jykdzxc',width:120,align:'center'},
			{title:'现场非充值卡充值额',field:'xcfczkcze',width:130,align:'center'},
			{title:'充值卡充值额',field:'czkcze',width:120,align:'center'},
			{title:'充值卡销售额',field:'czkxse',width:120,align:'center'},
			{title:'自助发卡终端充值',field:'zzfkzdcz',width:130,align:'center'},
			{title:'加油卡考核充值额',field:'jykkhcze',width:130,align:'center'},
			{title:'非油品基础薪酬',field:'fypjcxc',width:130,align:'center'},
			{title:'非油品目标完成情况奖惩',field:'fypmbwcqkjc',width:160,align:'center'},
			{title:'非定制香烟',field:'fdzxy',width:100,align:'center'},
			{title:'一般商品',field:'ybsp',width:100,align:'center'},
			{title:'移动充值卡',field:'ydczk',width:100,align:'center'},
			{title:'自有品牌水销售额',field:'zyppsxse',width:120,align:'center'},
			{title:'异业联盟',field:'yylm',width:100,align:'center'},
			{title:'汽车用品',field:'qcyp',width:100,align:'center'},
			{title:'团购',field:'tg',width:100,align:'center'},
			{title:'燃油宝和降凝剂销售额',field:'rybhjnjxse',width:160,align:'center'},
			{title:'自有品牌玻璃水销售额',field:'zyppblsxse',width:160,align:'center'},
			{title:'欧露纸',field:'olz',width:100,align:'center'},
			{title:'枸杞系列销售额',field:'gqxlxse',width:100,align:'center'},
			{title:'整月看站及借调等人员工资',field:'zykzjjddrygz',width:170,align:'center'},
			{title:'加班费',field:'jbf',width:100,align:'center'},
			{title:'临时停业看站工资',field:'lstykzgz',width:140,align:'center'},
			{title:'请假人员薪酬',field:'qjryxc',width:120,align:'center'},
			{title:'保留差',field:'blc',width:100,align:'center'},
			{title:'过渡补差',field:'gdbc',width:100,align:'center'},
			{title:'专项奖罚',field:'zxjf',width:100,align:'center'},
			{title:'管理扣罚',field:'glkf',width:100,align:'center'},
			{title:'实际附加',field:'sjfj',width:100,align:'center'},
			{title:'吨油薪酬标准',field:'dyxcbz',width:100,align:'center'},
			{title:'合计',field:'total',width:100,align:'center'}
		];
		
		/******************************加油站工资通知单*********************************/
		DataToView.JyzSalaryDetailGrid = [
			{title:'日期', field:'yearMonthDate', width:80,align:'center'},
			{title:'通知单号',field:'noticeNo',width:200,align:'center'},
			{title:'委托公司名称',field:'dw',width:140,align:'center'},
			{title:'姓名',field:'xm',width:100,align:'center'},
			{title:'身份证号',field:'sfzh',width:160,align:'center'},
			{title:'岗位',field:'gw',width:100,align:'center'},
			{title:'基本薪酬',field:'jbxc',width:100,align:'center'},
			{title:'联量薪酬',field:'llxc',width:100,align:'center'},
			{title:'非油品薪酬',field:'fypxc',width:100,align:'center'},
			{title:'加油卡薪酬',field:'jykxc',width:100,align:'center'},
			{title:'加班费',field:'jbf',width:100,align:'center'},
			{title:'过渡补差',field:'gdbc',width:100,align:'center'},
			{title:'其他工资',field:'qtgz',width:100,align:'center'},
			{title:'专项奖金',field:'zxjj',width:100,align:'center'},
			{title:'专项扣罚',field:'zxkf',width:100,align:'center'},
			{title:'其他奖罚',field:'qtjf',width:100,align:'center'},
			{title:'其他津贴补助等',field:'qtjtbzd',width:100,align:'center',render:function(rowdata,index,value){
				console.log(rowdata['qtjtbzd'] == 'null',rowdata['qtjtbzd'] == null , rowdata['qtjtbzd'] == '');
				if(rowdata['qtjtbzd'] == 'null' || rowdata['qtjtbzd'] == null || rowdata['qtjtbzd'] == ''){
					return '0';
				}
			}},
			{title:'应发合计',field:'yfhj',width:100,align:'center'},
			{title:'公积金',field:'gjj',width:60,align:'center'},
			{title:'养老金',field:'ylj',width:60,align:'center'},
			{title:'失业金',field:'syj',width:60,align:'center'},
			{title:'医保金',field:'ybj',width:60,align:'center'},
			{title:'年金',field:'nj',width:60,align:'center'},
			{title:'个人所得税',field:'grsds',width:110,align:'center'},
			{title:'实发工资',field:'sfgz',width:80,align:'center'},
			{title:'帐号',field:'zh',width:150,align:'center'},
			{title:'签名',field:'qm',width:80,align:'center'},
			{title:'新开户日期',field:'xkhrq',width:140,align:'center'},
			{title:'借支',field:'jz',width:60,align:'center'},
			{title:'卫生费',field:'wsf',width:60,align:'center'},
			{title:'房费',field:'ff',width:60,align:'center'},
			{title:'电费',field:'df',width:60,align:'center'},
			{title:'暖气费',field:'nqf',width:60,align:'center'},
			{title:'打卡金额',field:'dkje',width:80,align:'center'},
			{title:'已发需要合并扣税项合计',field:'yfxyhbksxhj',width:180,align:'center'},
			{title:'公积金单位每月缴费',field:'gjjdwmyjf',width:150,align:'center'},
			{title:'养老单位每月缴费',field:'yldwmyjf',width:140,align:'center'},
			{title:'失业单位每月缴费',field:'sydwmyjf',width:140,align:'center'},
			{title:'工伤单位每月缴费',field:'gsdwmyjf',width:140,align:'center'},
			{title:'医保单位每月缴费',field:'ybdwmyjf',width:140,align:'center'},
			{title:'生育保险单位每月缴费',field:'sybxdwmyjf',width:160,align:'center'},
			{title:'大病保险单位每月缴费',field:'dbbxdwmyjf',width:160,align:'center'},
			{title:'年金企业缴费每月划入个人账户金额',field:'njqyjfmyhrgrzhje',width:235,align:'center'},
			{title:'年金企业缴费',field:'njqyjf',width:120,align:'center'},
			{title:'管理费',field:'glf',width:60,align:'center'},
			{title:'委托公司名称',field:'wtgsmc',width:120,align:'center'},
			{title:'社保参保地',field:'sbcbd',width:120,align:'center'}
			];
		/******************************部门工资通知单*********************************/
		DataToView.BmSalaryDetailGrid = [
			{title:'日期', field:'yearMonthDate', width:80,align:'center'},
			{title:'通知单号',field:'noticeNo',width:200,align:'center'},
			{title:'委托公司名称',field:'dw',width:140,align:'center'},
			{title:'姓名',field:'xm',width:100,align:'center'},
			{title:'身份证号',field:'sfzh',width:160,align:'center'},
			{title:'岗位',field:'gw',width:100,align:'center'},
			{title:'基本薪酬',field:'jbxc',width:100,align:'center'},
			{title:'联量薪酬',field:'llxc',width:100,align:'center'},
			{title:'非油品薪酬',field:'fypxc',width:100,align:'center'},
			{title:'加油卡薪酬',field:'jykxc',width:100,align:'center'},
			{title:'加班费',field:'jbf',width:100,align:'center'},
			{title:'过渡补差',field:'gdbc',width:100,align:'center'},
			{title:'其他工资',field:'qtgz',width:100,align:'center'},
			{title:'专项奖金',field:'zxjj',width:100,align:'center'},
			{title:'专项扣罚',field:'zxkf',width:100,align:'center'},
			{title:'其他奖罚',field:'qtjf',width:100,align:'center'},
			{title:'其他津贴补助等',field:'qtjtbzd',width:100,align:'center',render:function(rowdata,index,value){
				console.log(rowdata['qtjtbzd'] == 'null',rowdata['qtjtbzd'] == null , rowdata['qtjtbzd'] == '');
				if(rowdata['qtjtbzd'] == 'null' || rowdata['qtjtbzd'] == null || rowdata['qtjtbzd'] == ''){
					return '0';
				}
			}},
			{title:'应发合计',field:'yfhj',width:100,align:'center'},
			{title:'公积金',field:'gjj',width:60,align:'center'},
			{title:'养老金',field:'ylj',width:60,align:'center'},
			{title:'失业金',field:'syj',width:60,align:'center'},
			{title:'医保金',field:'ybj',width:60,align:'center'},
			{title:'年金',field:'nj',width:60,align:'center'},
			{title:'个人所得税',field:'grsds',width:110,align:'center'},
			{title:'实发工资',field:'sfgz',width:80,align:'center'},
			{title:'帐号',field:'zh',width:150,align:'center'},
			{title:'签名',field:'qm',width:80,align:'center'},
			{title:'新开户日期',field:'xkhrq',width:140,align:'center'},
			{title:'借支',field:'jz',width:60,align:'center'},
			{title:'卫生费',field:'wsf',width:60,align:'center'},
			{title:'房费',field:'ff',width:60,align:'center'},
			{title:'电费',field:'df',width:60,align:'center'},
			{title:'暖气费',field:'nqf',width:60,align:'center'},
			{title:'打卡金额',field:'dkje',width:80,align:'center'},
			{title:'已发需要合并扣税项合计',field:'yfxyhbksxhj',width:180,align:'center'},
			{title:'公积金单位每月缴费',field:'gjjdwmyjf',width:150,align:'center'},
			{title:'养老单位每月缴费',field:'yldwmyjf',width:140,align:'center'},
			{title:'失业单位每月缴费',field:'sydwmyjf',width:140,align:'center'},
			{title:'工伤单位每月缴费',field:'gsdwmyjf',width:140,align:'center'},
			{title:'医保单位每月缴费',field:'ybdwmyjf',width:140,align:'center'},
			{title:'生育保险单位每月缴费',field:'sybxdwmyjf',width:160,align:'center'},
			{title:'大病保险单位每月缴费',field:'dbbxdwmyjf',width:160,align:'center'},
			{title:'年金企业缴费每月划入个人账户金额',field:'njqyjfmyhrgrzhje',width:235,align:'center'},
			{title:'年金企业缴费',field:'njqyjf',width:120,align:'center'},
			{title:'管理费',field:'glf',width:60,align:'center'},
			{title:'委托公司名称',field:'wtgsmc',width:120,align:'center'},
			{title:'社保参保地',field:'sbcbd',width:120,align:'center'}
			];
		//用户操作记录
		DataToView.operationRecordsGrid=[
			{title:'登录名',field:'login_name',width:150,align:'center'},
			{title:'用户名',field:'user_name',width:150,align:'center'},
			{title:'功能描述',field:'description',width:150,align:'center'},
			{title:'操作描述',field:'permissionsdesc',width:150,align:'center'},
			{title:'操作时间',field:'op_time',width:150,align:'center'},
			{title:'访问者ip地址',field:'remote_ip',width:150,align:'center'}
		];
		DataToView.operationRecordsURL={
			queryOperationRecords:'../logger/queryOperationRecordsInfoPage',//操作记录查询
			exportOperationRecords:'../logger/exportOperationRecordsInfo'//导出操作记录
	    }
		//数据库备份管理
		DataToView.databaseBakGrid=[
			{title:'登录名',field:'loginName',width:150,align:'center'},
			{title:'用户名',field:'userName',width:150,align:'center'},
			{title:'备份时间',field:'date',width:150,align:'center'},
			{title:'备份路径',field:'path',width:500,align:'center'}
		];
		DataToView.databaseBakURL={
			querydatabaseBak:'../databaseBak/querydatabaseBak',//数据库备份记录查询
			bakDatabase:'../databaseBak/bakDatabase',//备份数据库
	    }
		
		/*****************基金分析系统*********************/
		DataToView.NetWorttHistoryURL={
			calDayGrothRate:'../netWorth/calDayGrothRate',//备份数据库
			historyHuice:'../netWorth/analyseIntervalBuyModule'
	    }
		
	window.D2V = DataToView;	
}();
