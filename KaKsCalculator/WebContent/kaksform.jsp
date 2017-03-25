<%@ include file="include/taglibs.jsp"%>
<html>

<head>
<c:set var="pageTitle" value="Access - KaKs Calculation Online Tool"
	scope="request" />
<%@ include file="include/header.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body>

	<%@ include file="include/navigation_bar.jsp"%>

	<div class="container">
		<div class="jumbotron">
			<div class="row">
				Enter two sequences to be compared below in the fields below. Input
				must be in FASTA format.<br> Examples:<br>
				CCCACUAUCGUUAACGAUAGCUGGUCCUAC<br>CCAACAAUGGUUAACGACAGAUCGUCCUAU

			</div>
			<br> <br>

			<div class="row">

				<s:form id="myForm" action="calculate" theme="bootstrap"
					validate="true" cssClass="bs-example form-horizontal" method="post">

					<fieldset>
						<s:textarea label="Original Sequence" name="originalSequence"
							cssClass="col-lg-12" rows="4" placeholder="" />

						<s:textarea label="Substituted Sequence" name="mutatedSequence"
							cssClass="col-lg-12" rows="4" placeholder="" />

						<br>
						<br>
						<div class="g-recaptcha"
							data-sitekey="6Lej_RkUAAAAACPXKrd8JoBIKSU6yHZTZU0n8AMF"></div>

						<br>
						<br>

						<s:submit cssClass="btn btn-primary" value="Calculate" />


						<s:hidden name="pageName" value="kaksform" />

					</fieldset>

				</s:form>

			</div>
		</div>

		<%@ include file="include/footer.jsp"%>

	</div>



</body>
</html>