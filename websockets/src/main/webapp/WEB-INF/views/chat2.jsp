<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Socket Clock</title>
        <script language="javascript" type="text/javascript">
            var websocket;
            var last_time;
    
            function startChat() {
                var wsUri = "ws://localhost:8080/helloworld/chat";
                websocket = new WebSocket(wsUri);
                websocket.onmessage = function (data) {
                    last_time = data.data;
                    writeToScreen(last_time); 
                };
                websocket.onerror = function (data) {
                    writeToScreen(data.data);
                    websocket.close();
                }; 
            }
                 function writeToScreen(message) {
                var divChat = document.getElementById("chat");
            
                divChat.innerHTML += message;
               
            }
           // window.addEventListener("load", init, false);
            
        </script>
    </head>
    <body>
        <p> Enter your Name </p>
        <input type=text id="name"/>
        <input type=button onclick="startChat()"/>
        <div id="chat"></div>
    </body>
</html>
