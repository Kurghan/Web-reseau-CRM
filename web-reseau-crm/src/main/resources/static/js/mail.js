function openMessage(ID){
	document.getElementById("ID").value = ID;
	window.document.action.submit();
}

function openReplyform(){
	if(document.getElementById("formReply").style.visibility == "visible"){
	document.getElementById("formReply").style.visibility = "hidden";
	}
	else document.getElementById("formReply").style.visibility = "visible";
}

function viewTab(){
	var tab	= document.getElementById("messageTable");
	var val;
	try {
	val=tab.rows[1].cells[0].innerHTML;
	document.getElementById("listReply").style.display="block";
	}
	catch(error) {
		//document.getElementById("listReply").style.display="none";
	}
	
	
}

