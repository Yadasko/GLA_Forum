<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>Signin</title>
	
	<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">

		<div id="wrapcentre">
	
			<br style="clear: both;" />
	
				<jsp:include page="layout/topbar.jsp" />
				<br />
	
				<form action="/forum/signin" method="post">

					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<th colspan="2">Login</th>
							</tr>

							<tr>
								<td class="row2">

									<table style="width: 100%;" cellspacing="1" cellpadding="4" align="center">
										<tbody>
											<tr>
												<td valign="top"><b class="gensmall">Nom d'utilisateur:</b></td>
												<td><input class="post" name="username" size="25" tabindex="1" type="text" /></td>
											</tr>
											<tr>
												<td valign="top"><b class="gensmall">Mot de passe:</b></td>
												<td><input class="post" name="password1" size="25" tabindex="2" type="password" /></td>
											</tr>
											<tr>
												<td valign="top"><b class="gensmall">Confirmation du mot de passe:</b></td>
												<td><input class="post" name="password2" size="25" tabindex="2" type="password" /></td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td class="cat" colspan="2" align="center"><input name="login" class="btnmain" value="Login" tabindex="5" type="submit" /></td>
							</tr>
						</tbody>
					</table>

				</form>

				<jsp:include page="layout/footer.jsp" />
		</div>
	</body>
</html>
