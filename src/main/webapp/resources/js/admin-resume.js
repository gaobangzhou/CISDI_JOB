function onSelectChange(){
	var CompanyName = $("#CompanyName").val();
	var ResumeStatus = $("#ResumeStatus").val();
	var ChoiceType = $("#ChoiceType").val();
	var Recruitment =$("#Recruitment").val();
	var pagePerNumber =$("#pagePerNumber").val();
	var pageNum =1;
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum,
			company:CompanyName,
			recruitment:Recruitment,
			status:ResumeStatus,
			choiceType:ChoiceType
			
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}

function pageTurn(pagePerNumber,pageNum){
	var CompanyName = $("#CompanyName").val();
	var ResumeStatus = $("#ResumeStatus").val();
	var ChoiceType = $("#ChoiceType").val();
	var Recruitment =$("#Recruitment").val();
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum,
			company:CompanyName,
			recruitment:Recruitment,
			status:ResumeStatus,
			choiceType:ChoiceType			
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}

function manageApply(guid,status){

	pageTurn($("#pagePerNumber").val(),$("#active a").text());
	$.ajax({
		url : "manageApply",
		type : "POST",
		data : JSON.stringify({
			"applyGuid" : guid,
			"applyStatus":status
		}),
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				pageTurn($("#pagePerNumber").val(),$("#active a").text());
			}
		},
		error : function(msg) {

		}
	});
	
}

function ShowResume(guid){
	$.ajax({
		url : "resumeinfo",
		type : "POST",
		data : JSON.stringify({
			"OpenId" : guid
		}),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			
				$("#ResumeInfo").html(data)
			
		},
		error : function(msg) {

		}
	});
	
}
function downloadResult(){
	var CompanyName = $("#CompanyName").val();
	var ResumeStatus = $("#ResumeStatus").val();
	var ChoiceType = $("#ChoiceType").val();
	var Recruitment =$("#Recruitment").val();
	window.location.href="downloadResult?company="+CompanyName+"&&recruitment="+Recruitment+"&&status="+ResumeStatus+"&&choiceType="+ChoiceType;
}
