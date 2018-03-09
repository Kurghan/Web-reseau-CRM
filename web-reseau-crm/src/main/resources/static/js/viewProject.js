/**
 * 
 */

	//recuperation des information du tableau des taches
function getTask(ID){
	var IdRow = ID.parentNode.parentNode.rowIndex;
	document.getElementById("editTaskName").value = tasksTable.rows[IdRow].cells[0].innerHTML;
	document.getElementById("editTaskProgress").value = tasksTable.rows[IdRow].cells[1].innerHTML;
	document.getElementById("editTaskComment").value = tasksTable.rows[IdRow].cells[2].innerHTML;
	document.getElementById("editTaskID").value = tasksTable.rows[IdRow].cells[5].innerHTML;
}

	//recuperation de l'ID d'une tache pour la supprimer
function getTaskToDelete(ID){
	document.getElementById("IDTaskToDelete").value = ID;
}

function getIDClient(){
	var IDCustomer = document.getElementById("IDCustomerForMessage").value;
	var IDProject = document.getElementById("IDProjectForMessage").value;
	document.getElementById("IDcustomerMessage").value = IDCustomer;
	document.getElementById("IDProjectMessage").value = IDProject;
}

	//recuperation d'un message pour la page message
function getIdMessage(tab){
	var ID = tab.getElementsByTagName("tr")[0].cells[5];
	document.getElementById("IDmessageForm").value = ID.innerHTML;
	document.getElementById("formMessage").submit(ID);
}

var IDProject = document.getElementById("IDProject").innerHTML;
document.getElementById("IDMessageProject").value = IDProject;