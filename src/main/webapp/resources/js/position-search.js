		function select(type) {
			var keyWord = $("#keyWord").val();
			var companyCode = $("#COMPANY option:selected").val();
			var typeCode = $("#TYPE option:selected").val();
			var objectCode = $("#OBJECT option:selected").val();//学历
			var recGuid = $("#recGuid").val();
			var searchmap={
					"keyWord" : keyWord,
					"companyCode" : companyCode,
					"typeCode":typeCode,
					"education":objectCode,
					"rguid":recGuid,
					"RecType":type
			};
			reflashTable(searchmap);
		
		}
		function keyselect(type){
			var keyWord = $("#keyWord").val();
			if(keyWord==""){
				alert("请输入关键字，然后搜索");
			}else{
				select(type);
			}
		}
		/*var url = "${request.getContextPath()}/jobinfo/searchPosition.html?companyCode="
		+ companyCode
		+ "&typeCode="
		+ typeCode
		+ "&objectCode="
		+ objectCode
		+ "&recruitmentType="
		+ RecType
		+ "&keyWord="
		+ encodeURIComponent(keyWord);
window.location.href = encodeURI(url);*/
		
		function detailInfo(object,reGuid,type){
			object.style.cssText= "background-color: #EEEEE0;border-left: solid 5px sandybrown; margin-top: 5px;margin-bottom:5px"; 
			if(type=='campus'){
				location.href='position-detail/'+reGuid+'.html';
			}else{
				location.href='position-detail/'+reGuid+'.html';
			}
			
		}
		$(document).ready(function() {	
			$("#alertBtn").click(function(){
				$(".container.alert-success").addClass('hidden');
			});
		
		});