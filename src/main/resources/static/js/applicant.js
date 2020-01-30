function deleteRow(element){
	$(element).closest('tr').remove();
}

$(document).ready(function() {
	var exp_tbody = $("#exp-table > tbody");

	$("#add-exp").click(function() {
		var row = "<tr><td>" + $( "#exp-area option:selected" ).text() + "</td><td>" + $("#exp-years").val() + "</td><td><button onclick = deleteRow(this) class='button is-danger is-small'>Delete</button></td></tr>";
		exp_tbody.append($(row));
	});
	
	$("#avail-form").submit(function(e){
	    e.preventDefault();
	    var avail_tbody = $("#avail-table > tbody");
		var row = "<tr><td>" + $("#avail-from").val() + "</td><td>" + $("#avail-to").val() + "</td><td><button onclick = deleteRow(this) class='button is-danger is-small'>Delete</button></td></tr>";
		avail_tbody.append($(row));
	  });
});
