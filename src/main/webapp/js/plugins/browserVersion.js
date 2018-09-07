var b_name = navigator.appName;
var b_version = navigator.appVersion;
var version = b_version.split(";");
var trim_version = version[0].replace(/[ ]/g, "");
if (b_name == "Microsoft Internet Explorer") {
    /*如果是IE10以下*/
    if (trim_version == "MSIE6.0" || trim_version == "MSIE7.0" || trim_version == "MSIE8.0" || trim_version == "MSIE9.0") {
        alert("您的IE浏览器版本过低,请下载IE10及以上版本!");
        window.location.href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie";
    }
}