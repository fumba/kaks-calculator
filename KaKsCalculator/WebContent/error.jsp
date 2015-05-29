<%@ include file="include/taglibs.jsp" %>
<html>

<head>
	<c:set var="pageTitle" value="Error - KaKs Calculation Online Tool" scope="request" />
	<%@ include file="include/header.jsp" %>
</head>

<body>

<%@ include file="include/navigation_bar.jsp" %>

<div class="col-lg-6 col-lg-offset-3">
	<div class="well">
		<div class="container">
		
		<div class="jumbotron">
		<div> <s:property value="errorMessage" escapeHtml="false"/> </div>
		</div>
		
		<a class="btn btn-primary" href="kaksform.jsp">Go back to Calculator </a>
		
		</div>
	</div>
	
	
</div>



</body>
</html>