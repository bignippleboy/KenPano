<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="java.util.Date"%>
<% String formId = "form_" + new Date().getTime();
   String gridId = "grid_" + new Date().getTime();
   String path = request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
%>

<script type="text/javascript">
var grid;
var form;
var _dialog;
$(function (){
    grid = $("#<%=gridId%>");
    form = $("#<%=formId%>");
	PageLoader = {
	   //
	    initSearchPanel:function(){},
	    initGridPanel: function(){
	         var self = this;
	         var width = 280;
	         return grid.grid({
	                identity:"id",
	                buttons: [
	                        {content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>', action: 'add'},
	                        {content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>', action: 'modify'},
	                        {content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>', action: 'delete'}
	                    ],
	                url:"${pageContext.request.contextPath}/pano/pageJson.koala",
	                columns: [
	                     	                         	                         { title: 'vTour uuid', name: 'uuid', width: width},
	                         	                         	                             { title: '操作', width: 120, render: function (rowdata, name, index)
	                                 {
	                                     var param = '{"id":"' + rowdata.id + '","uuid":"' + rowdata.uuid + '"}';
	                                     var h = "<a href='javascript:generateVtour("+param+")'>生成vTour</a> | <a href='javascript:showVtourEditor("+param+")'>编辑vTour</a> | <a href='javascript:showVtour("+param+")'>查看vTour</a> | <a href='javascript:vtourSetting("+param+")'>设置vTour</a> | <a href='javascript:viewQR("+param+")'>查看二维码</a>";
	                                     /* alert(h); */
	                                     return h;
	                                 }
	                             }
	                ]
	         }).on({
	                   'add': function(){
	                       self.add($(this));
	                   },
	                   'modify': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length == 0){
	                            $this.message({
	                                type: 'warning',
	                                content: '请选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                        if(indexs.length > 1){
	                            $this.message({
	                                type: 'warning',
	                                content: '只能选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                       self.modify(indexs[0], $this);
	                    },
	                   'delete': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length == 0){
	                            $this.message({
	                                   type: 'warning',
	                                   content: '请选择要删除的记录'
	                            });
	                            return;
	                        }
	                        var remove = function(){
	                            self.remove(indexs, $this);
	                        };
	                        $this.confirm({
	                            content: '确定要删除所选记录吗?',
	                            callBack: remove
	                        });
	                   }
	         });
	    },
	    add: function(grid){
	    	var dialog = $("#panoAddModal");
	            dialog.modal({
	                keyboard:false
	            }).on({
					'hidden.bs.modal' : function() {
                        dialog.modal('hide');
                        grid.data('koala.grid').refresh();
					}
	            });
	    },
	    modify: function(id, grid){
	        var self = this;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">修改</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button><button type="button" class="btn btn-success" id="save">保存</button></div></div></div></div>');
	        $.get('<%=path%>/PanoInfo-update.jsp').done(function(html){
	               dialog.find('.modal-body').html(html);
	               self.initPage(dialog.find('form'));
	               $.get( '${pageContext.request.contextPath}/PanoInfo/get/' + id + '.koala').done(function(json){
	                       json = json.data;
	                        var elm;
	                        for(var index in json){
	                            elm = dialog.find('#'+ index + 'ID');
	                            if(elm.hasClass('select')){
	                                elm.setValue(json[index]);
	                            }else{
	                                elm.val(json[index]);
	                            }
	                        }
	                });
	                dialog.modal({
	                    keyboard:false
	                }).on({
	                    'hidden.bs.modal': function(){
	                        $(this).remove();
	                    }
	                });
	                dialog.find('#save').on('click',{grid: grid}, function(e){
	                    if(!Validator.Validate(dialog.find('form')[0],3))return;
	                    $.post('${pageContext.request.contextPath}/PanoInfo/update.koala?id='+id, dialog.find('form').serialize()).done(function(result){
	                        if(result.success){
	                            dialog.modal('hide');
	                            e.data.grid.data('koala.grid').refresh();
	                            e.data.grid.message({
	                            type: 'success',
	                            content: '保存成功'
	                            });
	                        }else{
	                            dialog.find('.modal-content').message({
	                            type: 'error',
	                            content: result.actionError
	                            });
	                        }
	                    });
	                });
	        });
	    },
	    initPage: function(form){
	              form.find('.form_datetime').datetimepicker({
	                   language: 'zh-CN',
	                   format: "yyyy-mm-dd",
	                   autoclose: true,
	                   todayBtn: true,
	                   minView: 2,
	                   pickerPosition: 'bottom-left'
	               }).datetimepicker('setDate', new Date());//加载日期选择器
	               form.find('.select').each(function(){
	                    var select = $(this);
	                    var idAttr = select.attr('id');
	                    select.select({
	                        title: '请选择',
	                        contents: selectItems[idAttr]
	                    }).on('change', function(){
	                        form.find('#'+ idAttr + '_').val($(this).getValue());
	                    });
	               });
	    },
	    remove: function(ids, grid){
	    	var data = [{ name: 'ids', value: ids.join(',') }];
	    	$.post('${pageContext.request.contextPath}/PanoInfo/delete.koala', data).done(function(result){
	                        if(result.success){
	                            grid.data('koala.grid').refresh();
	                            grid.message({
	                                type: 'success',
	                                content: '删除成功'
	                            });
	                        }else{
	                            grid.message({
	                                type: 'error',
	                                content: result.result
	                            });
	                        }
	    	});
	    }
	}
	PageLoader.initSearchPanel();
	PageLoader.initGridPanel();
	form.find('#search').on('click', function(){
            if(!Validator.Validate(document.getElementById("<%=formId%>"),3))return;
            var params = {};
            form.find('input').each(function(){
                var $this = $(this);
                var name = $this.attr('name');
                if(name){
                    params[name] = $this.val();
                }
            });
            grid.getGrid().search(params);
        });
});

var generateVtour = function(param) {
	$.post('${pageContext.request.contextPath}/pano/generateVtour.koala', {vtourUUID:param.uuid,isMac:"Y"}).done(function(result){
		grid.message({
            type: 'success',
            content: '生成vTour成功'
        });
	});
};

var showVtourEditor = function(param) {
	/* var vTourPath = "${pageContext.request.contextPath}/uploads/pano/"+uuid+"/vtour/tour.html"; */
	//openTab(vTourPath, "查看vTour", "查看vTour");//openTab不行，krpano引入的资源文件的相对路径不对
	/* window.open("${pageContext.request.contextPath}/uploads/"+uuid+"/vtour/tour_editor.html"); */
	openTab("/pages/pano/Pano-editor.jsp?uuid="+param.uuid, "vtour_editor_tab", "vtour_editor_tab","vtour_editor_tab");
}

var dialog2 = $('#panoSettingDiv');
dialog2.find('#saveSetting').on('click', function(e){
	$("#isGyroscope").val($("[name='my-checkbox']").bootstrapSwitch("state"));
	$("#isMapOpen").val($("[name='map-checkbox']").bootstrapSwitch("state"));
	$("#isSkinOpen").val($("[name='skin-checkbox']").bootstrapSwitch("state"));
    $.post('${pageContext.request.contextPath}/pano/update.koala', dialog2.find('form').serialize()).done(function(result){
        if(result.success){
            dialog2.modal('hide');
        }else{
            dialog2.find('.modal-content').message({
            type: 'error',
            content: result.actionError
            });
        }
    });
});

var viewQR = function(param) {
    var self = this;
    var dialog = $('<div class="modal fade"><div class="modal-dialog">'
    	+'<div class="modal-content"><div class="modal-header"><button type="button" class="close" '
    	+'data-dismiss="modal" aria-hidden="true">&times;</button>'
    	+'<h4 class="modal-title">新增</h4></div><div class="modal-body">'
    	+'<p>One fine body&hellip;</p></div><div class="modal-footer">'
    	+'<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
    	+'<button type="button" class="btn btn-success" id="addMapspot">添加Mapspot</button>'
    	+'<button type="button" class="btn btn-success" id="save">保存</button></div></div>'
    	+'</div></div>');
    $.get('<%=path%>Pano-qrimg.jsp').done(function(html){
        dialog.find('.modal-body').html(html);
        
            dialog.find('#imgContainer').append('<img src="${pageContext.request.contextPath}/uploads/qrimgs/'+param.uuid+'/qr.jpg" />');
              
	        dialog.modal({
                keyboard:false
            }).on({
                'hidden.bs.modal': function(){
                    $(this).remove();
                }
            })
    });
};

var panoDropStr = "";
var vtourSetting = function(param) {
        //self.initPage(dialog.find('form'));
        $.get( '${pageContext.request.contextPath}/pano/get/' + param.id + '.koala').done(function(json){
            json = json.data;
             var elm;
             for(var index in json){
                 /* elm = dialog.find('#'+ index + 'ID');
                 if(elm.hasClass('select')){
                     elm.setValue(json[index]);
                 }else{
                     elm.val(json[index]);
                 } */
                 if(index == "isGyroscope"){
                	 if(json[index] == "true")
                	 	$("[name='my-checkbox']").bootstrapSwitch("state", true);
                	 else
                	 $("[name='my-checkbox']").bootstrapSwitch("state", false);
                 }
                 if(index == "isMapOpen"){
                	 if(json[index] == "true")
                	 	$("[name='map-checkbox']").bootstrapSwitch("state", true);
                	 else
                	 $("[name='map-checkbox']").bootstrapSwitch("state", false);
                 }
                 if(index == "isSkinOpen"){
                	 if(json[index] == "true")
                	 	$("[name='skin-checkbox']").bootstrapSwitch("state", true);
                	 else
                	 $("[name='skin-checkbox']").bootstrapSwitch("state", false);
                 }
             }
         });
         dialog2.modal({
             keyboard:false
         }).on({
             'hidden.bs.modal': function(){
                 /* $(this).remove(); */
             }
         });
         $('#id').val(param.id);
         $('#uuid').val(param.uuid);
}

var showVtour = function(param) {
	/* var uuid = param.substr(0,param.indexOf(".")); */
	/* var vTourPath = "${pageContext.request.contextPath}/uploads/pano/"+uuid+"/vtour/tour.html"; */
	openTab("/pages/pano/Pano-view.jsp?uuid="+param.uuid, "vtour_tab", "vtour_tab","vtour_tab");//openTab不行，krpano引入的资源文件的相对路径不对；但是用iframe是可以的
	/* window.open("${pageContext.request.contextPath}/uploads/"+vtourUUID+"/vtour/tour.html"); */
}

var openDetailsPage = function(id){
        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">查看</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-info" data-dismiss="modal">返回</button></div></div></div></div>');
        $.get('<%=path%>/PanoInfo-view.jsp').done(function(html){
               dialog.find('.modal-body').html(html);
               $.get( '${pageContext.request.contextPath}/PanoInfo/get/' + id + '.koala').done(function(json){
                       json = json.data;
                        var elm;
                        for(var index in json){
                        if(json[index]+"" == "false"){
                        		dialog.find('#'+ index + 'ID').html("<span style='color:#d2322d' class='glyphicon glyphicon-remove'></span>");
                        	}else if(json[index]+"" == "true"){
                        		dialog.find('#'+ index + 'ID').html("<span style='color:#47a447' class='glyphicon glyphicon-ok'></span>");
                        	}else{
                          		 dialog.find('#'+ index + 'ID').html(json[index]+"");
                        	}
                        }
               });
                dialog.modal({
                    keyboard:false
                }).on({
                    'hidden.bs.modal': function(){
                        $(this).remove();
                    }
                });
        });
}
</script>
</head>
<body>
<div style="width:98%;margin-right:auto; margin-left:auto; padding-top: 15px;">
<!-- search form -->
<form name=<%=formId%> id=<%=formId%> target="_self" class="form-horizontal">
<input type="hidden" name="page" value="0">
<input type="hidden" name="pagesize" value="10">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
          <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">name:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="name" class="form-control" type="text" style="width:180px;" id="nameID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">img:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="img" class="form-control" type="text" style="width:180px;" id="imgID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">path:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="path" class="form-control" type="text" style="width:180px;" id="pathID"  />
        </div>
                </td>
       <td style="vertical-align: bottom;"><button id="search" type="button" style="position:relative; margin-left:35px; top: -15px" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button></td>
  </tr>
</table>	
</form>
<!-- grid -->
<div id=<%=gridId%>></div>
</div>
<!-- 模态框（Modal） -->
	<form id="fileupload" action='pano/upload.koala' method="POST"
		enctype="multipart/form-data">
		<div class="modal fade" id="panoAddModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
					</div>
					<div class="modal-body" id="modal-body">
						<!-- <input id="pano" type="file" name="pano" multiple> -->
						<!-- <div class="container"> -->
						<div class="row">
							<button type="button" id="addDiv" class="btn btn-default">添加全景图</button>
							<button type="submit" id="addPano" class="btn btn-default">开始上传</button>
						</div>
						<div id="fileList">
							<p>
							<div class="row">
								<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
									<input type="file" name="files" multiple>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
									<button type="button" class="btn btn-primary disabled">删除</button>
								</div>
							</div>
							</p>
						</div>
						<div id="myResultsDiv"></div>
						<!-- </div> -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>


	<div class="modal fade" id="panoSettingDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel2" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
				</div>
				<div class="modal-body" id="modal-body">
					<!-- <input id="pano" type="file" name="pano" multiple> -->
					<!-- <div class="container"> -->
					<form class="form-horizontal">
						<input type="hidden" id="id" name="id" />
						<input type="hidden" id="uuid" name="uuid" />
						<input type="hidden" id="isGyroscope" name="isGyroscope" />
						<input type="hidden" id="isMapOpen" name="isMapOpen" />
						<input type="hidden" id="isSkinOpen" name="isSkinOpen" />
					</form>

						<div class="form-group">
							<label class="col-sm-3 control-label">陀螺仪:</label>
							<div class="col-sm-9">
								<input type="checkbox" name="my-checkbox" data-size="mini">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">地图:</label>
							<div class="col-sm-9">
								<input type="checkbox" name="map-checkbox" data-size="mini">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">默认皮肤:</label>
							<div class="col-sm-9">
								<input type="checkbox" name="skin-checkbox" data-size="mini">
							</div>
						</div>
					<!-- </div> -->
				</div>
				<div class="modal-footer">
					<button id="saveSetting" type="button" class="btn btn-default" data-dismiss="modal">保存</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal -->
	</div>
<script>
    $(function(){
        $("[name='my-checkbox']").bootstrapSwitch();
        $('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch', function(event, state) {
        	  console.log(state); // true | false
        	});
        $("[name='map-checkbox']").bootstrapSwitch();
        $("[name='skin-checkbox']").bootstrapSwitch();
        
		$('form').ajaxForm({
		    target: '#myResultsDiv2',
		    data: "post",
		    dataType: "json",
		    beforeSubmit: function(formData, jqForm, options) { },
		    success: function(data, textStatus, jqXHR) {
		    },
		    error: function() {
		    },
		    complete: function(){}
		});

		$("#addDiv").click(function(){
			$("#fileList").append('<p><div class="row"><div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"><input type="file" name="files" multiple></div><div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"><button type="button" class="btn btn-primary disabled">删除</button></div></div></p>')
		});
    	
    })
    
    
</script>
</body>
</html>
