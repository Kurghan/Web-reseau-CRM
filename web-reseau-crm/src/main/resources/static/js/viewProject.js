/**
 * 
 */
function getTask(ID){
	var IdRow = ID.parentNode.parentNode.rowIndex;
	document.getElementById("editTaskName").value = tasksTable.rows[IdRow].cells[0].innerHTML;
	document.getElementById("editTaskProgress").value = tasksTable.rows[IdRow].cells[1].innerHTML;
	document.getElementById("editTaskComment").value = tasksTable.rows[IdRow].cells[2].innerHTML;
	document.getElementById("editTaskID").value = tasksTable.rows[IdRow].cells[5].innerHTML;
}

function getTaskToDelete(ID){
	
	document.getElementById("IDTaskToDelete").value = ID;
	
}

function getIDClient(){
	var IDCustomer = document.getElementById("IDCustomerForMessage").value;
	var IDProject = document.getElementById("IDProjectForMessage").value;
	document.getElementById("IDcustomerMessage").value = IDCustomer;
	document.getElementById("IDProjectMessage").value = IDProject;
}

function getIdMessage(tab){
	var ID = tab.getElementsByTagName("tr")[0].cells[3];
	var message = tab.getElementsByTagName("tr")[0].cells[2];
	var date = tab.getElementsByTagName("tr")[0].cells[1];
	var title = tab.getElementsByTagName("tr")[0].cells[0];
	document.getElementById("titleRead").innerHTML = title.innerHTML;
	document.getElementById("messageRead").innerHTML = message.innerHTML;
	document.getElementById("dateRead").innerHTML = date.innerHTML;
	$('#myModalReadMessage').modal('show');
	//alert(title.innerHTML);
}

function openReply(){
	document.getElementById("divReply").style.display = "block";
}