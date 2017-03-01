$(document).ready(function() {
	$("#RETURN").click(function() {
		window.history.back(-1);
	});
	 
	$("#bootbox-options").on(ace.click_event,
					function() {
						var jobGuid = $("#jobGuid").val();
						var jrGuid = $("#jrGuid").val();
						$.ajax({
							type : "POST",
							url : "../../resumeVerification",
							data : JSON.stringify({
							"jobGuid" : jobGuid
							
							}),
							dataType : "json",
							contentType : "application/json",
							success : function(data) {
								if (data.success) {
									bootbox.dialog({
										title : "请选择你的志愿",
										message : "<div class='no-padding-left' style='margin-top: 8px;' id='choice'>"
																+ "<input  type='radio' name='choice'  checked='checked'  value='FC'/>第一志愿 <input  type='radio' name='choice'  style='margin-left: 30px;' value='SC'/>第二志愿</div>",
										buttons : {
												"cancel" : {
												"label" : "<i class='ace-icon fa fa-check'></i> 取消",
												"className" : "btn-sm btn-warning",
												"callback" : function() {
													}
												},
												"success" : {
												"label" : "<i class='ace-icon fa fa-check'></i> 确定",
												"className" : "btn-sm btn-success",
												"callback" : function() {
													var choice = $("#choice input[name='choice']:checked").val();
													$.ajax({
														type : "POST",
														url : "../../volunter",
														data : JSON.stringify({
															"jobGuid" : jobGuid,
															"choice":choice,
															"jrGuid" : jrGuid
															}),
														dataType : "json",
														contentType : "application/json",
														success : function(data) {
															 if (data.success) {
															  window.location.href = "/CISDI_JOB/jobinfo/apply-record.html";
															 }
															},
														error : function(data) {
																			 alert("失败！"+data);
																}
														});
																	
													}
											}

										}
								});

							} else {
								if (data.type == "reusumenull") {
									alert(data.message);
									window.location.href = "/CISDI_JOB/resume/micro-resume.html";
								  } else {
									alert(data.message);
								  }
								}
						},
						error : function(msg) {
							 alert("失败！"+msg);
						}
					});

			});

});