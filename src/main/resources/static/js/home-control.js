
function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs480/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}

function deleteUser(userId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/cs480/codeSubmit/" + userId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the photo.");
				}
			});
}
function submitCode()
{
	var userId = $('#input_id').val();
	var userprob = $('#input_prob').val();
	var userfile = $('#input_file').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/codeSubmit/" + userId,
					data : {
						"User" : userId,
						"Problem" : userprob,
						"file" : userfile
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}


}
function addUser() {

	var userId = $('#input_id').val();
	var userprob = $('#input_prob').val();
	var userfile = $('#input_file').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/codeSubmit/",
					data : {
						"User" : userId,
						"Problem" : userprob,
						"file" : userfile
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}

function getUser(UserId) {
	var UserId = $('#query_id').val();
	if (UserId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs480/user/" ,
					data : {
					},
					success : function(result) {
						$('#result_id').text(result.id);
						$('#result_prob').text(result.prob);
						$('#result_fileName').text(result.fileName);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the user.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
	

}