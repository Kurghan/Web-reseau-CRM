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

	//recuperation d'un message pour l'afficher dans un modal
function getIdMessage(tab){
	var ID = tab.getElementsByTagName("tr")[0].cells[5];
	var message = tab.getElementsByTagName("tr")[0].cells[2];
	var date = tab.getElementsByTagName("tr")[0].cells[1];
	var title = tab.getElementsByTagName("tr")[0].cells[0];
	document.getElementById("titleRead").innerHTML = title.innerHTML;
	document.getElementById("messageRead").innerHTML = message.innerHTML;
	document.getElementById("dateRead").innerHTML = date.innerHTML;
	document.getElementById("IDmessageRead").value = ID.innerHTML;
	$('#myModalReadMessage').modal('show');
	//alert(title.innerHTML);
}
	
	//ouverture de la fenetre de reponse message
function openReply(){
	document.getElementById("divReply").style.display = "block";
	var ID = document.getElementById("IDmessageRead").value;
	//alert(ID);
	document.getElementById("IDmessageReply").value = ID;
}

	//fermeture de la fenetre de reponse message
function closeReply(){
	document.getElementById("divReply").style.display = "none";
}
