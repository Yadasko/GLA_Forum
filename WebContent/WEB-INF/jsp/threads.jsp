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

			<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
				cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="row1">
							<a href="/forum/home"><p class="breadcrumbs">Accueil</p></a>
							 <c:if test="${ info_msg != null}">
								<td class="row1">
									<p class="breadcrumbs">
										<c:out value="${info_msg != null ? info_msg : ''}" />
									</p>
								</td>
							</c:if>
		
								<p class="navbar_element">
									<!-- We could have used this one-liner if we didn't care about the link being displayed only when not connected -->
									<!-- <c:out value="${username != null ? username : '<a> Non connecté !</a>'}"/> -->
									<c:set var="username" value="${sessionScope.username}" />
									<c:if test="${username == null }">
										<a href="/forum/login?ref=${requestScope['javax.servlet.forward.request_uri']}"> <c:out value="Se connecter !" />
										</a>
									</c:if>

									<c:if test="${ username != null }">
											${username}
											<a style="padding-left: 10px;" href="/forum/logout"> Se déconnecter</a>
									</c:if>
								</p>
	
						</td>
					</tr>
				</tbody>
			</table>
			<br clear="all" />

			<table cellspacing="1" width="100%">
				<tbody>
					<tr>
						<td valign="middle" align="left"><img
							src="fichiers/button_topic_new.gif" alt="Post new topic"
							title="Post new topic" /></td>
					</tr>
				</tbody>
			</table>

			<br clear="all" />

			<table class="tablebg" cellspacing="1" width="100%">
				<tbody>
					<tr>
						<td class="cat" colspan="4">
							<table cellspacing="0" width="100%">
								<tbody>
									<tr class="nav">
										<td valign="middle">&nbsp;</td>
										<td valign="middle" align="right">&nbsp;</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>

					<tr>

						<th>&nbsp;Topics&nbsp;</th>
						<th>&nbsp;Auteur&nbsp;</th>
						<th>&nbsp;R&eacute;ponses&nbsp;</th>
						<th>&nbsp;Vues&nbsp;</th>
					</tr>


					<c:forEach items="${threads}" var="threads">
						<tr>
							<td class="row1"><a class="topictitle" href="/forum/thread?id=${threads.id}">${threads.name}</a></td>
							<td class="row2" align="center" width="130"><p class="topicauthor">
								<a class="username-coloured" href="#">${threads.author_name}</a></p></td>
							<td class="row1" align="center" width="50"><p class="topicdetails">${threads.responses_count}</p></td>
					       <td class="row2" align="center" width="50"><p class="topicdetails">${threads.views}</p></td>
					    </tr>
					</c:forEach>
					

				</tbody>
			</table>
			<br clear="all" />
		</div>

		<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
			cellpadding="0" width="100%">
			<tbody>
				<tr>
					<td class="row1">
						<p class="breadcrumbs">Index du forum</p>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>
