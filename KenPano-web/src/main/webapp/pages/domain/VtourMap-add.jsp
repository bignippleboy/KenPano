<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="form-horizontal" action="VtourMap/upload.koala" method="POST" enctype="multipart/form-data">

		<div class="form-group">
			<label class="col-lg-3 control-label">name:</label>
			<div class="col-lg-9">
				<input name="name" style="display: inline; width: 94%;"
					class="form-control" type="text" id="nameID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">mapImg:</label>
			<div class="col-lg-9">
				<input name="mapFile" style="display: inline; width: 94%;"
					class="form-control" type="file" id="mapImgID" multiple />
				<button type="submit" id="fileupload" class="btn btn-default">开始上传</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">position:</label>
			<div class="col-lg-9">
				<input name="position" style="display: inline; width: 94%;"
					class="form-control" type="text" id="positionID" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var selectItems = {};
		$(function(){
			$('form').ajaxForm({
			    target: '#myResultsDiv',
			    beforeSubmit: function(formData, jqForm, options) { alert('f') },
			    success: function(data, textStatus, jqXHR) {
			    	alert('success')
			    }
			});
		});
	</script>
</body>
</html>