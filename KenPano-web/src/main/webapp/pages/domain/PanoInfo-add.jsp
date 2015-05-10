<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload Example</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.fileupload-process.js"></script> --%>
</head>
<body>
<input id="fileupload123" type="file" name="attchss" multiple>
<script>
function a(){alert('fuck')}
$(function () {
    $('#fileupload123').fileupload({
    	url: "${pageContext.request.contextPath}/PanoInfo/fileupload.koala",
        dataType: 'json',
        done: function (e, data) {alert('a')
            /* $.each(data.result, function (index, file) {
                $('<p/>').text(file.filename).appendTo(document.body);
            }); */
        }
    });
});
</script>
<button onclick="a()">abc</button>
</body> 
</html>