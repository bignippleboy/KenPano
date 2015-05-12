
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.form.js"></script>
</head>
<body>
    <form id="fileupload" action='pano/upload_xml.koala' method="POST" enctype="multipart/form-data">
		<p>
			<div class="row">
	           	<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	           		<input type="file" name="xml_file" multiple>
	           		<input type="hidden" name="vtour_uuid" value="${param.uuid }" />
	           	</div>
	           	<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	           		<button type="submit" class="btn btn-primary">上传xml</button>
	        	</div>
	           	<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					<select class="form-control" id="reelSel">
					</select>
	        	</div>
	           	<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	           		<button id="getit" type="button" class="btn btn-primary">保存reel</button>
	        	</div>
			</div>
		</p>
	</form>
	<iframe id="myframe" src="${pageContext.request.contextPath}/uploads/${param.uuid }/vtour/tour_editor.html" style="width:100%;height:500px"></iframe>
	<script type="text/javascript">
		$(function(){
    		$('form').ajaxForm({
    		    target: '#myResultsDiv'
    		});


			var params = {};
			params.pagesize = 10;
			params.page = 0;
			
			$.ajax({
				type : "post",
				url : '${pageContext.request.contextPath}/Reel/pageJson.koala',
				data : params,
				dataType : 'json',
				success : function(result){
					json = result.data;
					for(var i=0;i<json.length;i++) {
	            		$('#reelSel').append('<option reelId="'+json[i].id+'">'+json[i].uuid+'</option>');
					}
				}
			});

    		$("#getit").on("click", function(){
/*     			var krpano = document.getElementById("krpanoSWFObject");
    			var ath = Number( krpano.get("print_ath") );
    			var atv = Number( krpano.get("print_atv") );

    			console.log(ath+","+atv); */
    			var pos = $(window.parent.document).contents().find("#myframe")[0].contentWindow.getVar();
    			console.log(pos.ath+","+pos.atv);
    			
    			//提交reelspot
    			var reelParams = {};
    			reelParams.atv = pos.atv;
    			reelParams.ath = pos.ath;
    			reelParams.vtour_uuid = "${param.uuid }";//vtour uuid
    			var reelDTO = {};
    			reelDTO.id=$("#reelSel").find("option:selected").attr("reelId");
    			reelParams.reelDTO = reelDTO;alert(JSON.stringify(reelParams));
    			$.ajax({
    				type : "post",
    				url : '${pageContext.request.contextPath}/Reelspot/add.koala',
    				data : JSON.stringify(reelParams),
    				dataType : 'json',
	  				contentType:"application/json",
    				success : function(result){
    				}
    			});
    		});
		});
	</script>
</body>
</html>