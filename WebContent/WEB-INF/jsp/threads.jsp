<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
<head>

<title>FORUM</title>



<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
</head>
<body class="ltr">


	<div id="wrapcentre">

		<div id="pagecontent">

			<jsp:include page="layout/topbar.jsp" />

			<br clear="all" />

			<table class="tablebg" cellspacing="1" width="100%">
				<tbody>
					<tr>
						<th class="accueil">&nbsp;Topics&nbsp;</th>
						<th class="accueil">&nbsp;Auteur&nbsp;</th>
						<th class="accueil">&nbsp;R&eacute;ponses&nbsp;</th>
						<th class="accueil">&nbsp;Vues&nbsp;</th>
					</tr>


					<c:forEach items="${threads}" var="threads">
						<tr>
							<td class="row1"><a class="topictitle" href="/forum/thread?id=${threads.id}"><c:out value="${threads.name}" /></a></td>
							<td class="row2" align="center" width="130"><p class="topicauthor">
								<a class="username-coloured" href="#"><c:out value="${threads.author_name}"/></a></p></td>
							<td class="row1" align="center" width="50"><p class="topicdetails">${threads.responses_count}</p></td>
					       <td class="row2" align="center" width="50"><p class="topicdetails">${threads.views}</p></td>
					    </tr>
					</c:forEach>
					

				</tbody>
			</table>
			<br clear="all" />
		</div>
		<jsp:include page="layout/footer.jsp" />
	</div>

</body>
</html>
