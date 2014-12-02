
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
import java.util.ArrayList
/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,ArrayList[ArrayList[String]],ArrayList[String],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(title: String)(rows: ArrayList[ArrayList[String]])(headers: ArrayList[String])(query: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*3.96*/("""

"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
	<title>"""),_display_(/*8.10*/title),format.raw/*8.15*/("""</title>
	
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	<!--
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css">
	<link rel="stylesheet" type="text/css" href="http://bootswatch.com/readable/bootstrap.min.css">
	-->
	<link rel="stylesheet" type="text/css" href=""""),_display_(/*21.48*/routes/*21.54*/.Assets.at("stylesheets/readable.bootstrap.min.css")),format.raw/*21.106*/("""">
	<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
	<script>hljs.initHighlightingOnLoad();</script>

	<link rel="stylesheet" type="text/css" href=""""),_display_(/*25.48*/routes/*25.54*/.Assets.at("stylesheets/style.css")),format.raw/*25.89*/("""">

	<script type="text/javascript" src=""""),_display_(/*27.39*/routes/*27.45*/.Assets.at("javascripts/nba.js")),format.raw/*27.77*/(""""></script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="navbar-brand">CECS 323 NBA Team</span>
			</div>
		</div>
	</nav>
	<div id="menu-choice" class="container">
		<div class="row">
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="1">
					<h2>Insert</h2>
					<hr />
					<p>Insert a new team into the Team table.</p>
				</div>
			</div>
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="2">
					<h2>Update</h2>
					<hr />
					<p>Alter the data-command of an attribute of all rows that fill the given condition.</p>
				</div>
			</div>
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="3">
					<h2>Delete</h2>
					<hr />
					<p></p>
				</div>
			</div>
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="4">
					<h2>Query 1</h2>
					<hr />
					<p></p>
				</div>
			</div>
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="5">
					<h2>Query 2</h2>
					<hr />
					<p></p>
				</div>
			</div>
			<div class="command-block col-md-4">
				<div class="jack-in" data-command="6">
					<h2>Query 3</h2>
					<hr />
					<p></p>
				</div>
			</div>
		</div>
	</div>
	<div id="chart-thing" class="container">
		<button id="return-block" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-arrow-left"></span>
		</button>
		<h1>Result Data</h1>
		<pre><code id="code-section" class="sql">"""),_display_(/*88.45*/query),format.raw/*88.50*/("""</code></pre>
		<table id="result-table" class="table table-striped">
			<tr>
			"""),_display_(/*91.5*/for( header <- headers ) yield /*91.29*/ {_display_(Seq[Any](format.raw/*91.31*/("""
				"""),format.raw/*92.5*/("""<th>"""),_display_(/*92.10*/header),format.raw/*92.16*/("""</th>
			""")))}),format.raw/*93.5*/("""
			"""),format.raw/*94.4*/("""</tr>
			
			"""),_display_(/*96.5*/for( row <- rows ) yield /*96.23*/ {_display_(Seq[Any](format.raw/*96.25*/("""
				"""),format.raw/*97.5*/("""<tr>
				"""),_display_(/*98.6*/for( curr <- row ) yield /*98.24*/ {_display_(Seq[Any](format.raw/*98.26*/("""
					"""),format.raw/*99.6*/("""<td>"""),_display_(/*99.11*/curr),format.raw/*99.15*/("""</td>				
				""")))}),format.raw/*100.6*/("""
				"""),format.raw/*101.5*/("""</tr>
			""")))}),format.raw/*102.5*/("""
		"""),format.raw/*103.3*/("""</table>
	</div>
</body>
</html>"""))}
  }

  def render(title:String,rows:ArrayList[ArrayList[String]],headers:ArrayList[String],query:String): play.twirl.api.HtmlFormat.Appendable = apply(title)(rows)(headers)(query)

  def f:((String) => (ArrayList[ArrayList[String]]) => (ArrayList[String]) => (String) => play.twirl.api.HtmlFormat.Appendable) = (title) => (rows) => (headers) => (query) => apply(title)(rows)(headers)(query)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Dec 01 18:20:59 PST 2014
                  SOURCE: /home/shane/Dropbox/jdbc-test/app/views/index.scala.html
                  HASH: 37fbfc32d0e4bda6cdb262cb6512665fa83c277d
                  MATRIX: 803->30|985->124|1013->126|1088->175|1113->180|1852->892|1867->898|1941->950|2160->1142|2175->1148|2231->1183|2300->1225|2315->1231|2368->1263|3961->2829|3987->2834|4095->2916|4135->2940|4175->2942|4207->2947|4239->2952|4266->2958|4306->2968|4337->2972|4377->2986|4411->3004|4451->3006|4483->3011|4519->3021|4553->3039|4593->3041|4626->3047|4658->3052|4683->3056|4729->3071|4762->3076|4803->3086|4834->3089
                  LINES: 26->3|29->3|31->5|34->8|34->8|47->21|47->21|47->21|51->25|51->25|51->25|53->27|53->27|53->27|114->88|114->88|117->91|117->91|117->91|118->92|118->92|118->92|119->93|120->94|122->96|122->96|122->96|123->97|124->98|124->98|124->98|125->99|125->99|125->99|126->100|127->101|128->102|129->103
                  -- GENERATED --
              */
          