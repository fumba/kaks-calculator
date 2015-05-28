<%@ include file="include/taglibs.jsp" %>
<html>

<head>
	<c:set var="pageTitle" value="Access - KaKs Calculation Online Tool" scope="request" />
	<%@ include file="include/header.jsp" %>
</head>

<body>

<%@ include file="include/navigation_bar.jsp" %>

<div class="col-lg-6 col-lg-offset-3">
	<div class="well">
		<div class="container">
		
			<div class="row">
			<p>Enter two sequences to be compared below in the fields below.</p>
			</div>
		
			<div class="row">
				<div class="col-lg-6">
				
					<s:form id="myForm" action="calculate" theme="bootstrap"
					validate="true" cssClass="bs-example form-horizontal"
					method="post">
					
					<fieldset>
					
					<s:textfield label="Original Sequence" name="userName"
					cssClass="col-lg-12" placeholder="ATCGCC" />
					
					<s:textfield label="Substituted Sequence" name="userName"
					cssClass="col-lg-12" placeholder="CCCATG" />
					
					<div class="col-lg-9 col-lg-offset-3">
						<s:submit cssClass="btn btn-primary" value="Calculate" />
					</div>
					
					<s:hidden name="pageName" value="kaksform" />
					
					</fieldset>
					
					</s:form>
					
				</div>
			</div>
		</div>
	</div>
</div>


</body>
</html>