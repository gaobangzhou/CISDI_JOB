function onSelectChange(){
	var CompanyName = $("#CompanyName").val();
	var JobStatus = $("#JobStatus").val();
	var jobType = $("#JobType").val();
	var pagePerNumber =$("#pagePerNumber").val();
	var pageNum =1;
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum,
			company:CompanyName,
			status:JobStatus,
			jobType:jobType
			
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}

function pageTurn(pagePerNumber,pageNum){
	var CompanyName = $("#CompanyName").val();
	var JobStatus = $("#JobStatus").val();
	var jobType = $("#JobType").val();
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum,
			company:CompanyName,
			status:JobStatus,
			jobType:jobType			
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}
