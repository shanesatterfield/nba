var queryDescriptions = [
	'Find the teams that have a total weight over 3300 pounds and the staff members of those teams.',
	'Find all players and staff members in the Clippers team.',
	'Find all Power Forwards in the database along with their team name.',
	'Find the team playing the most away games in December and the head coach for this team.',
	'Find the conference with the highest total ranked teams.',
	'Find the teams playing 2 or more games at Staples Center that have no games broadcast on NBA TV.',
	'Insert a new team into the Team table.',
	'Alter the value of an attribute of all rows that fill the given condition.',
	'Deletes the Rift division.'
];

function loadQueryDescriptions() {
	$('#menu-choice > .row').children().each(function() {
		var index = parseInt($(this).find('.jack-in').data('command')) - 1;
		$(this).find('p').html( queryDescriptions[ index ] )
		console.log("hey");
	})

}

$(document).ready(function() {
	$('#chart-thing').hide();

	loadQueryDescriptions();

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
			$('#query-description').html( queryDescriptions[ parseInt(commandValue)-1 ] );

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