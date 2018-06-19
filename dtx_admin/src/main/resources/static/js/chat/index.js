var socket = new WebSocket("ws://10.10.30.39:8080/websocket");

$(function() {
    listen();
})

function emit() {

    var text = encodeScript($("#msg").val());
    var msg = {
        "msgBody" : text,
       
    };
    msg = JSON.stringify(msg);
    socket.send(msg);

    $("#content").append("<kbd style='color: #" + "CECECE" + ";float: right; font-size: " + 12 + ";'>" + text +  "</kbd><br/>");
    $("#msg").val("");
}

function listen() {
    socket.onopen = function() {
        $("#content").append("<kbd>Welcome!</kbd></br>");
    };
    socket.onmessage = function(evt) {
        var data = JSON.parse(evt.data);
         $("#content").append("<kbd style='color: #" + data.color + ";font-size: " + data.fontSize + ";margin-top: 10px;'>" + data.fromName + " Say: " + data.msgBody + "</kbd></br>");
    };
    socket.onclose = function(evt) {
        $("#content").append("<kbd>" + "Close!" + "</kbd></br>");
    }
    socket.onerror = function(evt) {
        $("#content").append("<kbd>" + "ERROR!" + "</kbd></br>");
    }
}
document.onkeydown = function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode == 13){ // 
        emit();
    }
}; 