
function newRecruitment(){
	var myTemplate = Handlebars.compile($('#infoTpl').html());

	$('#rectForm').html(myTemplate(null));
	$('#recStartTime').datetimepicker({
		format : "yyyy-mm-dd hh:ii",
		language : 'zh-CN',
		autoclose : true,
		todayBtn : true,
		minuteStep : 10
	});
	
	
	$("#modal-rect").modal('show');
}

function editRecruitment(guid){
	
	var myTemplate = Handlebars.compile($('#infoTpl').html());

	$('#rectForm').html(myTemplate(null));
	$('#recStartTime').datetimepicker({
		format : "yyyy-mm-dd hh:ii",
		language : 'zh-CN',
		autoclose : true,
		todayBtn : true,
		minuteStep : 10
	});
	
	
	$("#modal-rect").modal('show');
}

function removeDatetime() {
	$('#recStartTime').val("");
}

function saveRecruitment(){
	$('#rectForm').ajaxSubmit(){
		
	}
}