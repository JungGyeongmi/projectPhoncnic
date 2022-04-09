<%@ page contentType="text/html; charset=utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>웹소켓 테스트 페이지</title>
        <script type="text/javascript">
            var g_webSocket = null;
            window.onload = function () {
                g_webSocket = new WebSocket("ws://localhost:8080/websocket");


                /**
                 * 웹소켓 사용자 연결 성립하는 경우 호출
                 */
                g_webSocket.onopen = function (message) {
                    addLineToChatBox("Server is connected.");
                };


                /**
                 * 웹소켓 메시지(From Server) 수신하는 경우 호출
                 */
                g_webSocket.onmessage = function (message) {
                    addLineToChatBox(message.data);
                };

                /**
                 * 웹소켓 사용자 연결 해제하는 경우 호출
                 */
                g_webSocket.onclose = function (message) {
                    addLineToChatBox("Server is disconnected.");
                };

                /**
                 * 웹소켓 에러 발생하는 경우 호출
                 */
                g_webSocket.onerror = function (message) {
                    addLineToChatBox("Error!");
                };
            }

            /**
            * 채팅 박스영역에 내용 한 줄 추가
            */
            function addLineToChatBox(_line) {
                if (_line == null) {
                    _line = "";
                }

                var chatBoxArea = document.getElementById("chatBoxArea");
                chatBoxArea.value += _line + "\n";
                chatBoxArea.scrollTop = chatBoxArea.scrollHeight;
            }

            /**
            * Send 버튼 클릭하는 경우 호출 (서버로 메시지 전송)
            */
            function sendButton_onclick() {
                var inputMsgBox = document.getElementById("inputMsgBox");
                if (inputMsgBox == null || inputMsgBox.value == null || inputMsgBox.value.length == 0) {
                    return false;
                }

                var chatBoxArea = document.getElementById("chatBoxArea");

                if (g_webSocket == null || g_webSocket.readyState == 3) {
                    chatBoxArea.value += "Server is disconnected.\n";
                    return false;
                }

                // 서버로 메시지 전송
                g_webSocket.send(inputMsgBox.value);
                inputMsgBox.value = "";
                inputMsgBox.focus();

                return true;
            }

            /**
            * Disconnect 버튼 클릭하는 경우 호출
            */
            function disconnectButton_onclick() {
                if (g_webSocket != null) {
                    g_webSocket.close();
                }
            }

            /**
            * inputMsgBox 키입력하는 경우 호출
            */
            function inputMsgBox_onkeypress() {
                if (event == null) {
                    return false;
                }

                // 엔터키 누를 경우 서버로 메시지 전송
                var keyCode = event.keyCode || event.which;
                if (keyCode == 13) {
                    sendButton_onclick();
                }
            }
        </script>
    </head>

    <body>
        <input id="inputMsgBox" style="width: 250px;" type="text" onkeypress="inputMsgBox_onkeypress()">
        <input id="sendButton" value="Send" type="button" onclick="sendButton_onclick()">
        <input id="disconnectButton" value="Disconnect" type="button" onclick="disconnectButton_onclick()">
        <br />
        <textarea id="chatBoxArea" style="width: 100%;" rows="10" cols="50" readonly="readonly"></textarea>
    </body>

    </html>