<%@ include file="include/taglibs.jsp"%>
<html>

<head>
<c:set var="pageTitle" value="Welcome - KaKs Calculation Online Tool"
	scope="request" />
<%@ include file="include/header.jsp"%>
</head>

<body>

	<%@ include file="include/navigation_bar.jsp"%>

	<div class="container">
		<div class="jumbotron">
			<div>
				<p>KaKs Online Calculation Tool</p>
				<br> Calculates the ratio of nonsynonymous (Ka) to synonymous
				(Ks) nucleotide substitution rates inorder to indicate selective
				pressures on genes, and can also be used to identify pairwise
				combinations of genes or branches of gene phylogenetic trees, where
				encoded proteins may have changed function. Algorithms Implemented:
				NG (Jukes Cantor Models), LWL (JC-K2P-K2P) and MLWL (K2P-K2P-K2P). <br>
				<br> <br>
				<p style="font-size: 13px">
					Reference:<br> Zhang Zhang, Jun Li, Xiao-Qian Zhao, Jun Wang,
					Gane Ka-Shu Wong, Jun Yu, KaKs_Calculator: Calculating Ka and Ks
					Through Model Selection and Model Averaging, Genomics, Proteomics &
					Bioinformatics, Volume 4, Issue 4, 2006, Pages 259-263, ISSN
					1672-0229, DOI: 10.1016/S1672-0229(07)60007-2. <br> <a
						href="https://s3.amazonaws.com/fumba.me/share+files/1-s2.0-S1672022907600072-main.pdf"
						target="_blank"> Full Paper [PDF Format] </a>
				</p>
			</div>

			<a class="btn btn-primary" href="kaksform.jsp">Access Tool </a>

		</div>

		<div></div>
		
		<%@ include file="include/footer.jsp"%>
		
	</div>

	
</body>
</html>