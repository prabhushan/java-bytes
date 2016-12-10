<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Byte Tweets</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Byte Tweets</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->

			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="Enter your name" id="name" /> <span
					class="input-group-btn">
					<button class="btn btn-secondary" type="button" id='enter'
						onclick="startChat()">Join the room!</button>
				</span>
			</div>
		<!-- 	<div>
				<span class="label label-default">Default Label</span> <span
					class="label label-primary">Primary Label</span> <span
					class="label label-success">Success Label</span> <span
					class="label label-info">Info Label</span> <span
					class="label label-warning">Warning Label</span> <span
					class="label label-danger">Danger Label</span>
			</div> -->
			<br />
			<div class="form-group">
				<label for="comment">Byte Tweets</label>
				<textarea class="form-control" rows="3" id="comment"></textarea>
				<br />
				<span
					class="input-group-btn">
					<button class="btn btn-secondary" type="button" id='send'
						onclick="sendChat()">Push Tweets</button>
				</span>
			</div>
			<div class="list-group" id="user-list"></div>
			<br />

		</div>

		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- jQuery Version 1.11.1 -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script language="javascript" type="text/javascript">
		var websocket;
		var last_time;
		$("#send").prop("disabled", true);
		function startChat() {
			var name = $('#name').val();
			$("#enter").prop("disabled", true);
			$("#send").prop("disabled", false);

			var wsUri = "ws://localhost:8080/helloworld/chat/" + name + "";
			websocket = new WebSocket(wsUri);
			websocket.onmessage = function(data) {
				last_time = data.data;
				writeToScreen(last_time);
			};
			websocket.onerror = function(data) {
				writeToScreen(data.data);
				websocket.close();
			};
			
		}

		var sendChat = function(data){
			websocket.send($("#comment").val());
			$("#comment").val("");
		}
		var writeToScreen = function(message) {
			$("#user-list").append(
					$("<a href='#' class='list-group-item'>").text(message));
		}
	</script>
</body>

</html>