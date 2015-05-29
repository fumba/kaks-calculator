<%@ include file="include/taglibs.jsp" %>
<html>

<head>
<<<<<<< HEAD
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


=======
	<c:set var="pageTitle" value="Results - KaKs Calculation Online Tool" scope="request" />
	<%@ include file="include/header.jsp" %>
</head>

<body>

<%@ include file="include/navigation_bar.jsp" %>

<div class="col-lg-6 col-lg-offset-3">
	<div class="well">
		<div class="container">
		
		<div class="jumbotron">
		<div> <s:property value="errorMessage"/> </div>
		</div>
		
		</div>
	</div>
</div>
>>>>>>> branch 'master' of https://github.com/fumbaa/kaks-calculator.git

</body>
</html>
