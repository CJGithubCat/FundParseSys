
/**
 * 函数功能：打开首页左侧工具栏，其他工具栏会自动收起
 * 用法：openThisArea("AreaControl");//打开区域监控
 * openThisArea("CarControl");//打开车辆监控
 * @param LableID 
 * 
 */
function openThisArea(LableID) {
	var item = $("#" + LableID);
	item.addClass('open');
	item.next().slideDown(500);

	item.parent().siblings().find("a.item-ht2").removeClass('open');
	item.parent().siblings().find("a.item-ht2").next().slideUp(500);
}