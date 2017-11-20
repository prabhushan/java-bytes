<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edifact Parser</title>
</head>
<body>
<form action="/parse" method="post">
<select name="type">
<option value="5010">5010</option>
<option value="10.6">10.6</option>

</select>
<textarea rows="10" cols="100" id="request" name="request"></textarea>
<input type="submit"/>
</form>

</body>
</html>