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
	  
/********************************************************* 数据管理 ***********************************/
		/******************************* 资源基本信息 ***********************************/
		DataToView.SRCURL={
			queryFundList:'../fund/queryFundList' //查询部门考核通知单
	    }
	   
		DataToView.SRCGrid = [[{type: 'checkbox', fixed: 'left'},
			{title:'资源名称', field:'itemName', width:200,align:'center'},
			{title:'指数链接', field:'itemUrl', width:600,align:'center'},
			{title:'类型', field:'type', width:100,align:'center'}
		]];
		
		/******************************* 指数基本信息 ***********************************/
		DataToView.INDEXURL={
			queryFundList:'../index/queryIndexList' //查询部门考核通知单
	    }
	   
		DataToView.INDEXGrid = [[{type: 'checkbox', fixed: 'left'},
			{title:'资源编号', field:'indexCode', width:100,align:'center'},
			{title:'指数名称', field:'indexEname', width:100,align:'center'},
			{title:'起始点数', field:'basePoint', width:100,align:'center'},
			{title:'起始日期', field:'baseDate', width:120,align:'center'},
			{title:'上线日期', field:'onlineDate', width:120,align:'center'},
			{title:'类型', field:'indexCIntro', width:100,align:'center'},
			{title:'行情走势URL', field:'hangqingzoushi', width:400,align:'center'},
			{title:'编制方法URL', field:'makeMethod', width:400,align:'center'},
			{title:'维护细则URL', field:'weihuxize', width:400,align:'center'},
			{title:'成分股列表URL', field:'chengfenguliebiao', width:400,align:'center'},
			{title:'机构类型', field:'agencyType', width:100,align:'center'},
			{title:'创建日期', field:'dateCreate', width:100,align:'center'},
			{title:'行情文件路径', field:'hangqingfilepath', width:360,align:'center'},
			{title:'详情URL', field:'detailUrl', width:380,align:'center'}
		]];
	
		/******************************* 基金基本信息 ***********************************/
		DataToView.JJXXURL={
			queryFundList:'../fund/queryFundList'
	    }
	   
		DataToView.JJXXGrid = [[{type: 'checkbox', fixed: 'left'},
			{title:'基金编码', field:'fundCode', width:100,align:'center'},
			{title:'基金名称', field:'fundName', width:200,align:'center'},
			{title:'指数编码', field:'indexCode', width:100,align:'center'},
			{title:'申购状态', field:'buyStatus', width:100,align:'center',render:function(rowdata,index,value){
				console.info(rowdata,index,value);
			}},
			{title:'基金类型', field:'fundType', width:100,align:'center'},
			{title:'手续费', field:'poundage', width:100,align:'center'},
			{title:'基金公司', field:'companyName',width:140,align:'center'},		
			{title:'基金评级', field:'grade',width:100,align:'center'},
			,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
		]];
		
		/******************************* 跑批基本信息 ***********************************/
		DataToView.JOBURL={
				queryFundList:'../job/queryJobList' 
		    }
		   
		DataToView.JOBGrid = [[{type: 'checkbox', fixed: 'left'},
			{title:'任务名称', field:'jobName', width:200,align:'center'},
			{title:'任务键值', field:'jobKey', width:100,align:'center'},
			{title:'任务描述', field:'jobDesc', width:100,align:'center',render:function(rowdata,index,value){
				console.info(rowdata,index,value);
			}},
			{title:'表达式', field:'cron', width:100,align:'center'},
			{title:'是否可用', field:'enable',width:140,align:'center'},		
			{title:'创建时间', field:'dateCreate',width:100,align:'center'},
			{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
		]];
		
		/******************************* 历史净值信息 ***********************************/
		DataToView.JZHISTORYURL={
				queryNetWorthHistoryList:'../netWorthHistory/queryNetWorthHistoryList'
	    }
	   
		DataToView.JZHISTORYGrid = [[
			{title:'基金编码', field:'fundCode', width:100,align:'center'},
			{title:'基金名称', field:'fundName', width:200,align:'center'},
			{title:'日期', field:'dateInfoStr', width:200,align:'center'},
			{title:'净值', field:'netWorth', width:100,align:'center'},
			{title:'30天均线', field:'avgLine30', width:100,align:'center'},
			{title:'60天均线', field:'avgLine60', width:100,align:'center'},
			{title:'120天均线', field:'avgLine120', width:100,align:'center'},
			{title:'星期几', field:'weekInfo', width:100,align:'center'},
			{title:'日增长率', field:'networthDaygrowRate', width:100,align:'center'},
			{title:'累计净值', field:'addupWorth',width:140,align:'center'}
		]];
		
		/******************************* 最新净值信息 ***********************************/
		
		DataToView.JZLASTURL={
				queryNetWorthLastList:'../netWorthLast/queryNetWorthLastList',
				parseNewestGuZhiInfo:'../netWorthLast/parseNewestGuZhiInfo'   // 解析最新的估值信息
	    }
	   
		DataToView.JZLASTGrid = [[
			{title:'基金编码', field:'fundCode', width:100,align:'center'},
			{title:'基金名称', field:'fundName', width:200,align:'center'},
			{title:'更新时间', field:'dateUpdateStr', width:200,align:'center'},
			{title:'当前估值', field:'nowNetWorth', width:100,align:'center'},
			{title:'昨天净值', field:'yesterdayNetWorth', width:100,align:'center'},
			{title:'估值涨幅', field:'guzhiAddRate', width:100,align:'center'},
			{title:'30天均线', field:'avgLine30', width:100,align:'center'},
			{title:'60天均线', field:'avgLine60', width:100,align:'center'},
			{title:'120天均线', field:'avgLine120', width:100,align:'center'},
			{title:'日期', field:'dateInfoStr', width:200,align:'center'},
			{title:'星期几', field:'weekInfo', width:100,align:'center'},
			,{title:'操作',  fixed: 'right',  toolbar: '#barDemo', width:150}
		]];
		
		/******************************* 持有基金信息 ***********************************/
		DataToView.FundHoldURL={
				queryFundHoldList:'../fundHold/queryFundHoldList',
				uploadFundHoldSummary:'../fundHold/uploadFundHoldSummary'
	    }
	   
		DataToView.FundHoldGrid = [[
			{title:'操作',  toolbar: '#barDemo', width:120,align:'center'},
			{title:'基金编码', field:'fundCode', width:100,align:'center'},
			{title:'基金名称', field:'fundName', width:200,align:'center'},
			{title:'实际总投入', field:'holdCost', width:100,align:'center', style: 'background-color: #5FB878;'},
			{title:'资产总金额', field:'marketValue', width:100,align:'center', style: 'background-color: #5FB878;'},
			/*{title:'总收益', field:'totalGain', width:100,align:'center'},
			{title:'总收益率', field:'totalGainRate', width:100,align:'center'},*/
			{title:'持有收益', field:'holdGain', width:100,align:'center', style: 'background-color: #FFAD86;'},
			{title:'持有收益率', field:'holdGainRate', width:100,align:'center', style: 'background-color: #FFAD86;'},
			{title:'资产总份额', field:'volume', width:100,align:'center'},
			{title:'最新净值', field:'nav', width:100,align:'center'},
			{title:'最新估值', field:'navGuzhi', width:100,align:'center'},
			{title:'7天均线', field:'avgLine7', width:100,align:'center'},
			{title:'15天均线', field:'avgLine15', width:100,align:'center'},
			{title:'30天均线', field:'avgLine30', width:100,align:'center'},
			{title:'60天均线', field:'avgLine60', width:100,align:'center'},
			{title:'90天均线', field:'avgLine90', width:100,align:'center'},
			{title:'120天均线', field:'avgLine120', width:100,align:'center'},
			{title:'240天均线', field:'avgLine120', width:100,align:'center'},
			{title:'历史最高净值', field:'historyMax', width:100,align:'center'},
			{title:'历史最低净值', field:'weekInfo', width:100,align:'center'},
			{title:'待确认金额', field:'unconfirmedAmount', width:100,align:'center'},			
			{title:'待确认份额', field:'unconfirmedVolume', width:100,align:'center'},			
			{title:'待确认订单数', field:'unconfirmedCount', width:100,align:'center'},
			{title:'可用份额', field:'usableRemainShare', width:100,align:'center'},
			{title:'类型', field:'type', width:100,align:'center'}
		]];
	
/***************************************************** 数据管理 ***********************************/
		DataToView.JZTJURL={
			 queryBmkhtzd:'../bmkhtzdmanage/queryBmkhtzd' //查询部门考核通知单
	    }
	   
		DataToView.JZTJGrid = [[
		    {title:'id', field:'id', width:90,align:'center'},
			{title:'基金Code', field:'fundCode', width:200,align:'center'},			
			{title:'基金净值', field:'netWorth',width:140,align:'center'},		
			{title:'星期', field:'weekInfo',width:110,align:'center',render:function(rowdata,index,value){
				return '星期'+ value;
			}},
			{title:'日期', field:'dateInfo',width:110,align:'center'}			
		]];
		
		
		
		/*****************基金分析系统*********************/
		DataToView.NetWorttHistoryURL={
			calDayGrothRate:'../netWorth/calDayGrothRate',//备份数据库
			historyHuice:'../netWorth/analyseIntervalBuyModule'
	    }
		
	window.D2V = DataToView;	
}();
