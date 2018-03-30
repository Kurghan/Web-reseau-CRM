/*if(document.location.href.substring(document.location.href.lastIndexOf( "/" )+1).includes ("projects")){
	document.getElementById("divLang").style.visibility="visible";
}*/




$(document).ready(function() {
	/*var CheminComplet = document.location.href;
	var NomDuFichier = CheminComplet.substring(CheminComplet.lastIndexOf( "/" )+1 );
	var page = NomDuFichier.split("?");*/
	$("#locales").change(function () {
        var selectedOption = $("#locales").val();
            window.location.replace('projects?lang=' + selectedOption);
    });
});

