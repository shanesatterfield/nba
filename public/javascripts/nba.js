$(document).ready(function(){
	$('#chart-thing').hide();

	$('.command-block').hover(
		function() {
			$(this).children('.jack-in').addClass('lift-up', 400);
		},
		function() {
			$(this).children('.jack-in').removeClass('lift-up', 400, "linear");
		}
	);

	$('.jack-in').click(function(){
		$('#menu-choice').hide();
		var commandValue = $(this).data('command');

		$.getJSON('queryCommand/' + commandValue, function(data){
			$('#result-table').empty();
			$('#code-section').html( data[0][0] );

			htmlString = "<thead><tr>"
			var header;
			for( header in data[1] ) {
				htmlString += "<th>" + data[1][header] +"</th>"
			}
			htmlString += "</tr></thead><tbody>"

			for( var i = 2; i < data.length; ++i ) {
				htmlString += "<tr>"
				var j;
				for( j in data[i] ) {
					htmlString += "<td>" + data[i][j] + "</td>"
				}
				htmlString += "</tr>"
			}
			htmlString += "</tbody>"

			$('#result-table').append( htmlString );
			$('#chart-thing').show();
			Prism.highlightAll();

		}, function() {
			alert("hey")
		});
	});

	$('#return-block').click(function(){
		$('#chart-thing').hide();
		$('#menu-choice').show();
	});
});