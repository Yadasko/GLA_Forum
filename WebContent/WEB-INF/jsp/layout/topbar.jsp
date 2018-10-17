<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
	cellpadding="0" width="100%">
	<tbody>
		<tr>
			<td class="row1"><a href="/forum/home"><p
						class="breadcrumbs">Accueil</p></a> <c:if test="${ info_msg != null}">
					<td class="row1">
						<p class="breadcrumbs">
							<c:out value="${info_msg != null ? info_msg : ''}" />
						</p>
					</td>
				</c:if>

				<p class="navbar_element">
					<!-- We could have used this one-liner if we didn't care about the link being displayed only when not connected -->
					<!-- <c:out value="${username != null ? username : '<a> Non connecté !</a>'}"/> -->
					<c:set var="user" value="${sessionScope.user}" />
					<c:if test="${user == null }">
						<a href="/forum/login"> <c:out value="Se connecter !" />
						</a>
					</c:if>

					<c:if test="${ user != null }">
											${user.login}
											<a style="padding-left: 10px;" href="/forum/logout"> Se
							déconnecter</a>
					</c:if>
				</p></td>
		</tr>
	</tbody>
</table>