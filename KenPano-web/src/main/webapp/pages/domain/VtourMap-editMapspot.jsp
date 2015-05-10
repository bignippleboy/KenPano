<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	#map_container {
		position: relative;
		width: 500px;
		background-size: 100% auto;
		background-repeat: no-repeat;
		/* background-image: url();要程序赋值 */
		border: 1px solid;
	}
	/* #map_container:before {
		content: "";
		display: block;
		/* padding-top: 65%;要程序计算 */
	} */
</style>
</head>
<body>
	<form class="form-horizontal">
		
		<div id="map_container"></div>
		<label class="col-sm-3 control-label">选择要链接的场景：</label>
		<div class="col-lg-9">
			<!-- <div class="dropdown" style="display:block">
		      <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu_pano" data-toggle="dropdown" aria-expanded="true">
		        Dropdown
		        <span class="caret"></span>
		      </button>
		      <ul class="dropdown-menu" id="pano_dropdown_ul" role="menu" aria-labelledby="dropdownMenu_vtour">
		      </ul>
		    </div> -->
			<select class="form-control" id="panoSel">
			</select>
		</div>
		
	</form>
	<script type="text/javascript">
	$(function(){
		
		var selectItems = {};
		
	})

	</script>
</body>
</html>