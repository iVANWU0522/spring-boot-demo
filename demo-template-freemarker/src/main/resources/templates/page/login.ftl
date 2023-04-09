<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="app" style="margin: 20px 20%">
	<form action="/demo/user/login" method="post">
		name<input type="text" name="name" placeholder="name"/>
		password<input type="password" name="password" placeholder="password"/>
		<input type="submit" value="login">
	</form>
</div>
</body>
</html>