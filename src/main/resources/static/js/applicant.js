function deleteRow(element){
	$(element).closest('tr').remove();
}

$( document ).ready(function() {
	var tbody = $("#exp-table").find('tbody');
	
	$("#add-exp").click(function() {
		var row = "<tr><td>" + $( "#exp-area option:selected" ).text() + "</td><td>" + $("#exp-years").val() + "</td><td><button onclick = deleteRow(this) class='button is-danger is-small'>Delete</button></td></tr>";
		tbody.append($(row));
	});
});