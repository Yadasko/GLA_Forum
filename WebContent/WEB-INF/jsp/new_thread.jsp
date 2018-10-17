<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>New Thread</title>
	
	<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">

		<div id="wrapcentre">
	
			<br style="clear: both;" />
	
				<jsp:include page="layout/topbar.jsp" />
				<br />
	
				<form action="/forum/new_thread" method="post">

					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<th colspan="2">Create a new topic</th>
							</tr>

							<tr>
								<td class="row2">

									<table style="width: 100%;" cellspacing="1" cellpadding="4" align="center">
										<tbody>
											<tr>
												<td valign="top"><b class="gensmall">Sujet:</b></td>
												<td><input class="post" name="thread_name" size="25" tabindex="1" type="text" /></td>
											</tr>
											<tr>
												<td valign="top"><b class="gensmall">Message:</b></td>
												<td><textarea name="content" style="width: 430px; height: 130px;"></textarea></td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td class="cat" colspan="2" align="center"><input name="post" class="btnmain" value="Post!" tabindex="5" type="submit" /></td>
							</tr>
						</tbody>
					</table>

				</form>

				<c:if test="${info_msg != null}">
				<tr>
				    <td valign="top">${info_msg}</td>
				</tr>        
				</c:if>

				<jsp:include page="layout/footer.jsp" />
		</div>
	</body>
</html>
