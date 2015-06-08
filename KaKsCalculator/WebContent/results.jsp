<%@ include file="include/taglibs.jsp"%>
<html>

<head>
<c:set var="pageTitle" value="Results - KaKs Calculation Online Tool"
	scope="request" />
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation_bar.jsp"%>


	<div class="container">

		<div class="jumbotron">

			Jukes-Cantor (JC) Method:<br> Ka:
			<s:property value="ngKa" escapeHtml="false" />
			<br> Ks:
			<s:property value="ngKs" escapeHtml="false" />
			<br> Ka/Ks:
			<s:property value="ngKaKs" escapeHtml="false" />

			<br> <br> Kimuras- two parameter (K2P) model:<br> Ka:
			<s:property value="lwlKa" escapeHtml="false" />
			<br> Ka Variance:
			<s:property value="lwlVKa" escapeHtml="false" />
			<br> Ks:
			<s:property value="lwlKs" escapeHtml="false" />
			<br> Ks Variance:
			<s:property value="lwlVKs" escapeHtml="false" />
			<br> Ka/Ks:
			<s:property value="lwlKaKs" escapeHtml="false" />
			<br> <br> <br> JC and K2P Models:<br> Ka:
			<s:property value="mlwlKa" escapeHtml="false" />
			<br> Ks:
			<s:property value="mlwlKs" escapeHtml="false" />
			<br> Ka/Ks:
			<s:property value="mlwlKaKs" escapeHtml="false" />

		</div>


		<div class="jumbotron">
			<a class="btn btn-primary" href="kaksform.jsp">Go back to
				Calculator </a>
		</div>

		<%@ include file="include/footer.jsp"%>

	</div>

</body>
</html>