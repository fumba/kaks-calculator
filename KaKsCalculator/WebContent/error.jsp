<%@ include file="include/taglibs.jsp"%>
<html>

<head>
<c:set var="pageTitle" value="Error - KaKs Calculation Online Tool"
	scope="request" />
<%@ include file="include/header.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body>

	<%@ include file="include/navigation_bar.jsp"%>

	<div class="container">

		<div class="jumbotron">
			<div>
				<s:property value="errorMessage" escapeHtml="false" />
			</div>

			<br> <br> <a class="btn btn-primary" href="kaksform.jsp">Go
				back to Calculator </a>
		</div>
		
		<%@ include file="include/footer.jsp"%>
		
	</div>
	
	
</body>
</html>