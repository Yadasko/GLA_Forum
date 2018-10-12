<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
<head>

<title>THREAD</title>



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
										<a href="/forum/login?ref=${requestScope['javax.servlet.forward.request_uri']}&id=${param.id}"> <c:out value="Se connecter !" />
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
						<td valign="middle" align="left" colspan="4" nowrap="nowrap"><a
							href="#"><img src="fichiers/button_topic_new.gif"
								alt="Post new topic" title="Post new topic" /></a>&nbsp;<a href="#"><img
								src="fichiers/button_topic_reply.gif" alt="Reply to topic"
								title="Reply to topic" /></a></td>
					</tr>
				</tbody>
			</table>

			<br clear="all" />


			<c:forEach items="${messages}" var="messages">
				<table class="tablebg" cellspacing="1" width="100%">
					<tbody>
						<tr class="row2">

							<td valign="middle" align="center"><b class="postauthor">${messages.author_name}</b>
							</td>
							<td width="100%" height="25">
								<table cellspacing="0" width="100%">
									<tbody>
										<tr>

											<td class="gensmall" width="100%">
												<div style="float: left;">
													&nbsp; <b>Fil de discussion :</b> Mon fil de discussion
												</div>
												<div style="float: right;">
													<b>Posted:</b> Wed Aug 17, 2016 2:12 pm&nbsp;
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>


					<tr class="row2">
						<td class="profile" valign="top">
							<table cellspacing="4" align="center" width="150"></table> <span
							class="postdetails"> <b>Posts: </b>24
						</span>
						</td>
						<td valign="top">
							<table cellspacing="5" width="100%">
								<tbody>
									<tr>
										<td>
											<div class="postbody">${messages.content}</div> <br
											clear="all" />
										<br />
											<table cellspacing="0" width="100%">
												<tbody>
													<tr valign="middle">
														<td class="gensmall" align="right"></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
						<tr>
							<td class="spacer" colspan="2" height="1"><img
								src="fichiers/spacer.gif" alt="" width="1" height="1" /></td>
						</tr>
				</table>
			</c:forEach>
			
			<c:if test="${username != null }">
				<form action="/forum/login" method="post">

				<table class="tablebg" cellspacing="1" width="100%">
					<tbody>
						<tr>
							<th colspan="2">Répondre</th>
						</tr>

						<tr>
							<td class="row2 wrapcentre">

								<table style="width: 100%;" cellspacing="1" cellpadding="4"
									align="center">
									<tbody>
										<tr>
											<td valign="top"><b class="gensmall">Message :</b></td>
											<td><textarea>Lorem ipsum sit dolor</textarea></td>
										</tr>

									</tbody>
								</table>
							</td>
						</tr>

						<tr>
							<td class="cat" colspan="2" align="center"><input
								name="login" class="btnmain" value="Poster" tabindex="5"
								type="submit" /></td>
						</tr>
					</tbody>
				</table>

			</form>
			</c:if>
			
		</div>


		<br clear="all" />
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
