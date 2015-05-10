
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
	           	<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
	           		<input type="file" name="xml_file" multiple>
	           		<input type="hidden" name="vtour_uuid" value="${param.uuid }" />
	           	</div>
	           	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
	           		<button type="submit" class="btn btn-primary">上传xml</button>
	        	</div>
			</div>
		</p>
	</form>
	<iframe src="${pageContext.request.contextPath}/uploads/${param.uuid }/vtour/tour_editor.html" style="width:100%;height:500px"></iframe>
	<script type="text/javascript">
		$(function(){
    		$('form').ajaxForm({
    		    target: '#myResultsDiv'
    		});
		});
	</script>
</body>
</html>