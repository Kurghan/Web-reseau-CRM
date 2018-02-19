/**
 * 
 */
function getTask(ID){
	var IdRow = ID.parentNode.parentNode.rowIndex;
	//alert(tasksTable.rows[IdRow].cells[0].innerHTML + " " + tasksTable.rows[IdRow].cells[1].innerHTML + " " + tasksTable.rows[IdRow].cells[2].innerHTML);
	document.getElementById("editTaskName").value = tasksTable.rows[IdRow].cells[0].innerHTML;
	document.getElementById("editTaskProgress").value = tasksTable.rows[IdRow].cells[1].innerHTML;
	document.getElementById("editTaskComment").value = tasksTable.rows[IdRow].cells[2].innerHTML;
	document.getElementById("editTaskID").value = tasksTable.rows[IdRow].cells[5].innerHTML;
}

function getTaskToDelete(ID){
	
	document.getElementById("IDTaskToDelete").value = ID;
	
}

