<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pano upload</title>
</head>
<body>
	<input id="pano" type="file" name="pano" multiple>
	<script type="text/javascript">
		$(function(){
			$("#pano").fileupload({
				url: "${pageContext.request.contextPath}/Pano/upload.koala",
		        dataType: 'json',
		        done: function (e, data) {alert('a')
		            /* $.each(data.result, function (index, file) {
		                $('<p/>').text(file.filename).appendTo(document.body);
		            }); */
		        }
			});
		});
	</script>
</body>
</html>