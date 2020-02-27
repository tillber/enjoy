$(document).ready(function() {
	$("td[colspan=7]").find(".app").hide();
    $("table").click(function(event) {
        event.stopPropagation();
        var $target = $(event.target);
        if ($target.hasClass(".app") && $target.closest("td").attr("colspan") > 1 ) {
            $target.slideUp();
        } else {
            $target.closest("tr").next().find(".app").slideToggle();
        }                    
    });
});