$(document).ready(function() {
	$("#onlogin").on("click", function() {
		var username = $.trim($("#username").val());
		var password = $("#password").val();
		if (username == "" && password == "") {
			alert("请填写用户名和密码");
			return false;
		} else if (username == "") {
			alert("请填写用户名");
			return false;
		} else if (password == "") {
			alert("请填写密码");
			return false;
		} else {
		}
		$.ajax({
			type : "POST",
			url : "innerLogin",
			data : JSON.stringify({
				"userName" : username,
				"password" : password
			}),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					// alert("登录成功！");
					// location.href =
					// "/CISDI_AR/archives/archives-search.html";
					location.href = "/CISDI_JOB/admin/admin-home.html";
				} else {
					alert(data.message);
				}
			},
			error : function(textStatus) {
				alert("error");
			}
		});
	});
});

$(document).keydown(function(e) {
	if (e.keyCode == 13) {
		$('#onlogin').trigger("click");
	}
});