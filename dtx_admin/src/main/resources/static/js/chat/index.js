var socket = new WebSocket("ws://10.10.30.39:8080/websocket");

$(function() {
    //初始化加载listen方法
    listen();
})

//向客户端发送消息，这里定义了一些参数用来设置消息的颜色字体，不过暂时没用到有兴趣的可以自己实现
function emit() {

    //encodeScript方法用来转义<>标签，防止脚本输入，方法内容在core.js里面
    var text = encodeScript($("#msg").val());
    var msg = {
        "msgBody" : text,
       
    };
    msg = JSON.stringify(msg);
    //向服务端发送消息
    socket.send(msg);

    //将自己发送的消息内容静态加载到html上，服务端实现自己发送的消息不会推送给自己
    $("#content").append("<kbd style='color: #" + "CECECE" + ";float: right; font-size: " + 12 + ";'>" + text +  "</kbd><br/>");
    //将消息文本框清空
    $("#msg").val("");
}

function listen() {
    //打开连接时触发
    socket.onopen = function() {
        $("#content").append("<kbd>Welcome!</kbd></br>");
    };
    //收到消息时触发
    socket.onmessage = function(evt) {
        var data = JSON.parse(evt.data);
		 $("#content").append("<kbd >" + " Say: " + data.msgBody + "</kbd></br>");
         $("#content").append("<kbd style='color: #" + data.color + ";font-size: " + data.fontSize + ";margin-top: 10px;'>" + data.userName + " Say: " + data.msgBody + "</kbd></br>");
    };
    //关闭连接时触发
    socket.onclose = function(evt) {
        $("#content").append("<kbd>" + "Close!" + "</kbd></br>");
    }
    //连接错误时触发
    socket.onerror = function(evt) {
        $("#content").append("<kbd>" + "ERROR!" + "</kbd></br>");
    }
}
//按下回车键时触发发送消息方法
document.onkeydown = function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode == 13){ // enter 键
        emit();
    }
}; 