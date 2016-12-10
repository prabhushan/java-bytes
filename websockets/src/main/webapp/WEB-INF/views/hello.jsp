<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Socket Clock</title>
        <script language="javascript" type="text/javascript">
            var websocket;
            var last_time;
            
            function init() {
                output = document.getElementById("output");
            }

            function start_clock() {
                var wsUri = "ws://localhost:8080/helloworld/clock";
                websocket = new WebSocket(wsUri);
                websocket.onmessage = function (evt) {
                    last_time = evt.data;
                    writeToScreen("<span style='color: blue;'>" + last_time + "</span>"); 
                };
                websocket.onerror = function (evt) {
                    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
                    websocket.close();
                }; 
            }
            
            function stop_clock() {
                websocket.send("stop");
            }

            function writeToScreen(message) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                oldChild = output.firstChild;
                if ( oldChild == null) {
                    output.appendChild(pre);
                } else {
                    output.removeChild(oldChild);
                    output.appendChild(pre);
                }
            }
            window.addEventListener("load", init, false);
            
        </script>
    </head>
    <body>
        <div style="text-align: center;font-family: Arial; font-size: large">
            WebSocket Clock
            <br></br>
            <form action="">
                <input 
                    onclick="start_clock()" 
                    title="Press to start the clock on the server" 
                    value="Start" 
                    type="button">
                <input 
                    onclick="stop_clock()" 
                    title="Press to stop the clock on the server" 
                    value="Stop" 
                    type="button">
            </form>
            <div id="output"></div>
        </div>
        
    </body>
</html>
