<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/jquery-file-upload/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/jquery-file-upload/css/jquery.fileupload.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/jquery-file-upload/css/jquery.fileupload-ui.css"> --%>
</head>
<body>
	<form class="form-horizontal" action="PanoInfo/fileupload.koala" method="post" enctype="multipart/form-data">

		<div class="form-group">
			<label class="col-lg-3 control-label">name:</label>
			<div class="col-lg-9">
				<input name="name" style="display: inline; width: 94%;"
					class="form-control" type="text" id="nameID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">path:</label>
			<div class="col-lg-9">
				<input name="path" style="display: inline; width: 94%;"
					class="form-control" type="text" id="pathID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">img:</label>
			<div class="col-lg-9">
				<input name="img" style="display: inline; width: 94%;"
					class="form-control" type="text" id="imgID" />
			</div>
		</div>
		<div class="form-group">
			<span class="btn btn-success fileinput-button"> <i
				class="glyphicon glyphicon-plus"></i> <span>Add files...</span> 
<!-- 				<input id="fileupload" type="file" name="attchss" multiple> -->
				<input type="file" name="attchss">
				<input type="submit">
			</span>
		</div>

		<!-- The global progress state -->
		<div class="col-lg-5 fileupload-progress fade">
			<!-- The global progress bar -->
			<div class="progress progress-striped active" role="progressbar"
				aria-valuemin="0" aria-valuemax="100">
				<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
			</div>
			<!-- The extended global progress state -->
			<div class="progress-extended">&nbsp;</div>
		</div>
		</div>
		<!-- The table listing the files available for upload/download -->
		<table role="presentation" class="table table-striped">
			<tbody class="files"></tbody>
		</table>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.fileupload.js"></script>
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script> --%>
	<script type="text/javascript">
		/* $(function() {
			$('#fileupload').fileupload({
		    	url: "${pageContext.request.contextPath}/PanoInfo/fileupload.koala",
				dataType : 'json',
				done : function(e, data) {
					alert('a')
					$.each(data.result.files, function(index,file){
						$('<p/>').text(file.name).appendTo(document.body);
					});
				}
			});
		}) */
	</script>
</body>
</html>