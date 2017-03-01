$(document).ready(function(){
	//添加地点
	$("#addPlace").click(function(){
		var placeName = $("#placeName").val();
		var placeCode = $("#placeCode").val();
		var type = "WORK_PALCE";
		if (placeName == "" || placeCode == "") {
			alert("请填写每一项");
			return false;
		} 
		$.ajax({
			type : "POST",
			url : "insertConfiguration",
			data : JSON.stringify({
				"lupname":placeName,
				"code":placeCode,
				"type":type
			}),
			dataType : "html",
			contentType : "application/json",
			success : function(data) {
				$("#placeForm form").empty();
				$("#placeForm form").html(data);			
			}
			
		});
	});
	//添加专业
	$("#workProfessional").click(function(){
		var professionalName = $("#professionalName").val();
		var professionalCode = $("#professionalCode").val();
		var type = "SUBJECT";
		if (professionalName == "" || professionalCode == "") {
			alert("请填写每一项");
			return;
		} 
		$.ajax({
			type : "POST",
			url : "insertProfessional",
			data : JSON.stringify({
				"lupname":professionalName,
				"code":professionalCode,
				"type":type
			}),
			dataType : "html",
			contentType : "application/json",
			success : function(data) {
				$("#professionalForm form").empty();
				$("#professionalForm form").html(data);			
			}
			
		});
	});
	//类别
	$("#workType").click(function(){
		var workTypeName = $("#workTypeName").val();
		var workTypeCode = $("#workTypeCode").val();
		var type = "JOB_TYPE";
		if (workTypeName == "" || workTypeCode == "") {
			alert("请填写每一项");
			return;
		} 
		$.ajax({
			type : "POST",
			url : "insertWorkType",
			data : JSON.stringify({
				"lupname":workTypeName,
				"code":workTypeCode,
				"type":type
			}),
			dataType : "html",
			contentType : "application/json",
			success : function(data) {
				$("#workTypeForm form").empty();
				$("#workTypeForm form").html(data);			
			}
			
		});
	});
	//英语水平
	$("#englishLeveBtn").click(function(){
		var englishLeveName = $("#englishLeveName").val();
		var englishLeveCode = $("#englishLeveCode").val();
		var type = "ENGLISHLVL";
		if (englishLeveName == "" || englishLeveCode == "") {
			alert("请填写每一项");
			return;
		} 
		$.ajax({
			type : "POST",
			url : "insertEnglishLevel",
			data : JSON.stringify({
				"lupname":englishLeveName,
				"code":englishLeveCode,
				"type":type
			}),
			dataType : "html",
			contentType : "application/json",
			success : function(data) {
				$("#englishLevelForm form").empty();
				$("#englishLevelForm form").html(data);			
			}
			
		});
	});
	
});