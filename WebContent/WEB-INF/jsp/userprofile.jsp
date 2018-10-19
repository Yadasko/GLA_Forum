<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
<head>

<title>Profile</title>

<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
</head>
<body class="ltr">

	<div id="wrapcentre">

		<br style="clear: both;" />

		<jsp:include page="layout/topbar.jsp" />
		<br />


		<table class="tablebg" cellspacing="1" width="100%">
			<tbody>
				<tr>
					<th colspan="2">User Profile</th>
				</tr>
			
				<tr>
					<td class="row2">

						<table style="width: 15%;" cellspacing="1" cellpadding="4"
							>
						
								<tr>
									<td valign="top"><b class="gensmall">Login :</b> </td>
									<td><c:out value="${login}"/></td>
									
								</tr>
								<tr>
									<td valign="top"><b class="gensmall">Messages posted:</b></td>
									<td><c:out value="${posts}"/></td>
								</tr>
	

							
						</table>
					</td>
				</tr>

			</tbody>
		</table>

		<c:if test="${info_msg != null}">
			<tr>
				<td valign="top">${info_msg}</td>
			</tr>
		</c:if>

		<jsp:include page="layout/footer.jsp" />
	</div>
</body>
</html>