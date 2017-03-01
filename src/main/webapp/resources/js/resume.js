$(document).ready(function() {
	// 获取resume中的值
	$("#btnsubmit").click(function() {
		if (!checkRequired()) {
			alert("请完成必填项");
			return;
		}
		var type = "create";
		var headtext = $("#header").text();
		if (headtext != "新建简历") {
			type = "edit";
		}
		submitDataByType(type);
		addReadOnly();
		$(this).addClass("hidden");
		$("#btneditor").removeClass("hidden");
		$("#header").text("我的简历");

	});

	$("#btneditor").click(function() {
		removeReadOnly();
		$(this).addClass("hidden");
		$("#btnsubmit").removeClass("hidden");
	});
	
});

function submitDataByType(type) {

	var gender = $("#gender input[name='gender']:checked").val();
	var name = $("#name").val();

	var native_place = $("#native_place").val();
	var english_lvl = $("#english_lvl option:selected").text();
	var score = $("#score option:selected").val();
	var education = $("#education option:selected").val(); //
	// alert(option);
	var bachelor_school = $("input[name='bachelor_school']").val();
	var bachelor_subject = $("input[name='bachelor_subject']").val();
	// alert(bachelor_subject);
	var doctor_school = $("#doctor_school").val();
	var doctor_subject = $("#doctor_subject").val();
	var master_school = $("#master_school").val();
	var master_subject = $("#master_subject").val();
	var english_score = $("#english_score").val();
	var phone_num = $("#phone_num").val();
	var email = $("#email").val();
	var others = $("#others").val();
	// 通过ajax提交
	$.ajax({
		type : "POST",
		url : "submitResume",
		async : false,
		data : JSON.stringify({
			"name" : name,
			"gender" : gender,
			"native_place" : native_place,
			"education" : education,

			"bachelor_school" : bachelor_school,
			"bachelor_subject" : bachelor_subject,
			"doctor_school" : doctor_school,
			"doctor_subject" : doctor_subject,
			"master_school" : master_school,
			"master_subject" : master_subject,

			"english_lvl" : english_lvl,
			"score" : score,
			"english_score" : english_score,

			"phone_num" : phone_num,
			"email" : email,
			"others" : others,
			"pagetype" : type

		}),
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				addReadOnly();
				$(this).addClass("hidden");
				$("#btneditor").removeClass("hidden");
				$("#header").text("我的简历");
				$("#modal-job").modal('show');
			} else {
				alert(data.message);
			}
		}
	});
}

function checkRequired() {
	var result = true;
	$(".form-group").each(
			function() {
				var temp = true;
				var input = $(this).find("input").val();
				var select = $(this).find("select").val();
				if (typeof (input) != "undefined" && input == '') {
					temp = false;
				} else if (typeof (select) != "undefined" && select == '') {
					temp = false;
				}
				if (typeof (input) != "undefined"
						&& typeof (select) != "undefined"
						&& (select == "" || input == "")) {
					temp = false;
				}
				if (!temp) {
					$(this).addClass("has-error");
				} else {
					$(this).removeClass("has-error");
				}
				if (result) {
					result = temp;
				}
			})
	return result;

}

function addReadOnly() {
	$(":input").attr('readonly', 'true');
	$("#education").attr('disabled', 'true');
	$("#english_lvl").attr('disabled', 'true');
	$("#score").attr('disabled', 'true');
	$("#gender").addClass('hidden');
	$("#lbl_gender").removeClass('hidden');
	var gender = $("#gender input[name='gender']:checked").val();

	$("#lbl_gender").text(gender);

}
function removeReadOnly() {
	$(":input").removeAttr('readonly');
	$("#education").removeAttr('disabled');
	$("#english_lvl").removeAttr('disabled');
	$("#score").removeAttr('disabled');
	$("#gender").removeClass('hidden');
	$("#lbl_gender").addClass('hidden');
	var gender = $("#lbl_gender").text();
	if (gender == '男') {
		$("#man").attr("checked", "checked");
		$("#woman").removeAttr("checked");
	} else {
		$("#woman").attr('checked', 'checked');
		$("#man").removeAttr('checked');
	}
}

function showeducation(selection) {

	$(".bachelor").addClass("hidden");
	if (selection == "bachelor") {
		$(".bachelor.master.doctor").removeClass("hidden");
		$()
	} else if (selection == "master") {
		$(".bachelor.master").removeClass("hidden");
	} else if (selection == "doctor") {
		$(".bachelor").removeClass("hidden");
	}
}

function conn() {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; ++i) {
		if (inputs[i].type.toLowerCase() == 'text') { // 在这里进行input文本框类别判断剩下的input
			// radio自己写吧。复制整个if里面东西将checkbox改成radio或者text
			var mark = false;
			$("input[name='" + inputs[i].name + "']").each(function() {
				if ($(this).prop("")) {
					mark = true;
				}
			});
			if (!mark) {
				alert("请输入必选项");
				return false;
				break;
			}
		}
	}
}