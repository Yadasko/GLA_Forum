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

			<jsp:include page="layout/topbar.jsp" />

			<br clear="all" />


			<c:forEach items="${messages}" var="messages">
				<table class="tablebg" cellspacing="1" width="100%">
					<tbody>
						<tr class="row2">

							<td valign="middle" align="center"><b class="postauthor"><a href="profile?id=${messages.authorId }"><c:out value="${messages.author_name}"/></a></b>
							</td>
							<td width="100%" height="25">
								<table cellspacing="0" width="100%">
									<tbody>
										<tr>

											<td class="gensmall" width="100%">
												<div style="float: left;">
													&nbsp; <b>Fil de discussion :</b> <c:out value="${messages.associated_thread_name}"/>
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
							<table cellspacing="4" align="center" width="150">
							<tbody><th>
							<img src="avatar?userId=<c:out value="${messages.authorId}"/>" height="100" width="100"></img></th>
							<br/> <span
							class="postdetails"> <b>Posts: </b>${messages.author_posts_count}
						</span>
						</tbody>
						</table>
						</td>
						<td valign="top">
							<table cellspacing="5" width="100%">
								<tbody>
									<tr>
										<td>
											<div class="postbody"> <c:out value="${messages.content}"/> </div> <br
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
			
			<c:if test="${user != null }">
				<form action="/forum/thread" method="post">
				<input name="thread_id" type="hidden" value="${param.id}"/>
				<table class="tablebg" cellspacing="1" width="100%">
					<tbody>
						<tr>
							<th colspan="2">Répondre</th>
						</tr>

						<tr>
							<td class="row2 wrapcentre">

								<table style="width: 100%;" cellspacing="1" cellpadding="6"
									align="center">
									<tbody>
										<tr>
											<td style="display:flex;justify-content:center;align-items:center;"><textarea name="content" style="width: 430px; height: 130px;"></textarea></td>
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
		<jsp:include page="layout/footer.jsp" />

	</div>
</body>
</html>
