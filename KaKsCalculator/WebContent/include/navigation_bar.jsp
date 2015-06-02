<div class="navbar navbar-default">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-responsive-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
	</div>

	<div class="navbar-collapse collapse navbar-responsive-collapse">

		<ul class="nav navbar-nav navbar-right">
			<li><a href="/KaKsCalculator/">Home</a></li>
			<li><a href="kaksform.jsp">Access Calculator</a></li>
			<li><a href="https://github.com/fumbaa/kaks-calculator"
				target="_blank">Source Code</a></li>
			<li><a class="focuslink" href="http://www.fumba.me"
				target="_blank">www.fumba.me</a></li>
		</ul>
	</div>

	<script type="text/javascript">
		var url = window.location;

		// Will also work for relative and absolute hrefs
		$('ul.nav a').filter(function() {
			return this.href == url;
		}).parent().addClass('active');
	</script>

</div>