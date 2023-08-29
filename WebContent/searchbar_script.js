$("#search-button").click(function(){
	console.log($("#search-bar"));
	const searchValue = $("#search-bar").val();
	console.log(searchValue);
});

$("#search-bar").keyup(function(e){
	const searchValue = e.target.value;
	console.log(searchValue);
	
	$.get('search', {"param" : searchValue.trim()}, 
		function(resp){
			const name = resp.suggestions;			
			const suggestion = $("div.result");
			const list = suggestion.children('ul');
			list.empty();
			suggestion.css("opacity", 1);
			
			$.each(name, function(key, value){
				list.append('<li><a href="byName?param='+value+'">' + value + '</a></li>');
			});
			
		}
	).fail(function(request, error){
		console.log(error);
		console.log(request);
	});
});

$("#search-bar").blur(function(){
	const suggestion = $("div.result");
	suggestion.css("opacity", 0);
});