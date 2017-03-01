function check(o){
	    if(o.value=="campus"){
	    	$("#screen").removeClass("hidden");    
	    }else{
	    	$("#screen").addClass("hidden");
	    }
}

function checkRequred(){
	var result = true;
	$(".form-cell").each(
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
	var btnedit =$("#btnedit").text();
	if ($("#recruitment_type").val() == "campus"&&typeof (btnedit) == "undefined") {
	     var recTemp = false;
	     $('#recruitment-lable').removeClass("red");
		$('#recruitment :selected').each(function(i, selected) {
			recTemp = true;
			
		});
		
		if (!recTemp) {
			$('#recruitment-lable').addClass("red");
			result = recTemp;
		}
	}
	return result;
}
$(document).ready(function() {	

	$("#btnsubmit").click(function() {
		var flag = false;
		try {
			if(!checkRequred())
			{
				alert("请填写信息");
			  return;	
			}
				
		   submitDataByType();
					
		} catch (e) {
			// TODO: handle exception
			alert("提交失败");
		}	
	
	});
	

		$("#modal-wizard").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
		
	});
});

function submitDataByType() {
	var b = false;
	var name = $("#name").val();
	var create_com = $("#create_com option:selected").val();
	var type = $("#type option:selected").val();
	var professional = $("#professional").val();
	var place = $("#place option:selected").val();
	var priority = $("#priority option:selected").val();
	 var education =$("#education option:selected").val();
	var screen = $("#screen option:selected").val();
	var recruitment_type = $("#recruitment_type option:selected").val();
	
	
	var recruitment="";
	$('#recruitment :selected').each(function(i, selected) {
	    recruitment +=$(selected).val()+";";
	}); 
	var create_person = $("#create_person").val();
	var description = $("#description").val();
	var capacity = $("#capacity").val();
	// 通过ajax提交
	$.ajax({
		type : "POST",
		url : "insertJob",
		data : JSON.stringify({
			"name" : name,
			"type" : type,
			"place" : place,
			"create_com" : create_com,
			"priority" : priority,
			"education" : education,
			"description" : description,
			"capacity" : capacity,
			"recruitment_type" : recruitment_type,
			"professional" : professional,
			"recruitment":recruitment		
		}),
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {   
				$("#modal-wizard").modal('hide');
				 onSelectChange();	
			} else {
				alert(data.failureMessage);
			}
		}
	});
}

function addReadOnly() {
	$(".form-cell-edit").each(function() {
		$(this).find("input").attr("readOnly","readOnly");
		$(this).find("select").attr("disabled","disabled");
	});	
	$("#descriptionEdit").attr("readOnly","readOnly");
	$("#capacityEdit").attr("readOnly","readOnly");
	$("#professionalEdit").attr("readOnly","readOnly");

}

function removeReadOnly() {
	$(".form-cell-edit").each(function() {
		$(this).find("input").removeAttr('readonly');
		$(this).find("select").removeAttr('disabled');
	});	
	$("#descriptionEdit").removeAttr("readOnly");
	$("#capacityEdit").removeAttr("readOnly");
	$("#professionalEdit").removeAttr("readOnly");
}
/*
 * 查看和编辑
 */
function lookAndEdit(jobGuid){
	$.ajax({
		type : "POST",
		url : "jobModal",
		data : JSON.stringify({
			"jobGuid":jobGuid
		}),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			 //alert("成功！"+msg);
				$("#jobmodal").empty();
				$("#jobmodal").html(data);	
				/*addReadOnly();
				$("#Editor").click(function() {				
					removeReadOnly();	
				});
				$("#btnsubmit").click(function() {
					alert("hi");
					submitDataByType();
					
				});*/
				$("#btnedit").click(function() {
					$(this).addClass("hidden");
					$("#btnfinish").removeClass("hidden");
					
					removeReadOnly();
					
					
				});
				$("#btnfinish").click(function() {
					$(this).addClass("hidden");   
					$("#btnedit").removeClass("hidden");
					
				});
				
		},
		error : function(msg) {
			 alert("数据错误！"+msg);
		}
		
	});
}
/**
 * 
 * @param jobGuid
 * 删除
 */
function editJobStatus(jobGuid,status){
	$.ajax({
		type:"POST",
		url:"editJobStatus",
		data : JSON.stringify({
			"jobGuid":jobGuid,
			"status":status
		}),
		dataType : "json",
		contentType : "application/json",
		success:function(data){
			if(data.success){
				onSelectChange();
				
			}else{
				alert(data.failureMessage);
			}
			
		}
	});
}
function EditSubmit(jobGuid){
	if (!checkEdit()) {
		alert("请填写信息");
		return;
	}
	addReadOnly();
	$("#btnfinish").addClass("hidden");
	$("#btnedit").removeClass("hidden");
	
	var name = $("#nameEdit").val();
	var create_com = $("#create_comEdit option:selected").val();
	var type = $("#typeEdit option:selected").val();
	var professional = $("#professionalEdit").val();
	var place = $("#placeEdit option:selected").val();
	var priority = $("#priorityEdit option:selected").val();
	 var education =$("#educationEdit option:selected").val();

	var description = $("#descriptionEdit").val();
	var capacity = $("#capacityEdit").val();
	
	 $.ajax({
     type:"POST",
		url:"updateJob",
		data : JSON.stringify({
			"jobGuid":jobGuid,
			"name" : name,
			"type" : type,
			"place" : place,
			"create_com" : create_com,
			"priority" : priority,
			"education" : education,
			"description" : description,
			"capacity" : capacity,
			"professional" : professional,
		}),
		dataType : "json",
		contentType : "application/json",
		success:function(data){
			if(data.success){
				$("#modal-job").modal('hide');
				onSelectChange();	
			    alert(data.Message);
			}else{
				alert(data.Message);
			}
			
		}
	});
}

function checkEdit(){
	
	var result = true;
	$(".form-cell-edit").each(
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
			});
	
	return result;
}