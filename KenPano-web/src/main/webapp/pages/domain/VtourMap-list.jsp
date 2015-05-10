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
<style type="text/css">

	.drag_item {
		position: absolute;
		top: 0;
		left: 0;
		border: 0px;
		background: none;
		/*bottom: 0;
		right: 0;
		width: 100%;
		height: 100%;*/
	}
</style>
<script type="text/javascript">
var grid;
var form;
var _dialog;
var idIndex = 0;
$(function (){
    grid = $("#<%=gridId%>");
    form = $("#<%=formId%>");
	var mapDialog = $("#vtourMapModal");
	mapDialog.find('#saveMap').on('click', function(e){
        /* if(!Validator.Validate(dialog.find('form')[0],3))return; */
        $.post('${pageContext.request.contextPath}/VtourMap/add.koala', mapDialog.find('form').serialize()).done(function(result){
            if(result.success){
            	mapDialog.modal('hide');
                e.data.grid.data('koala.grid').refresh();
                e.data.grid.message({
                type: 'success',
                content: '保存成功'
                });
            }else{
            	mapDialog.find('.modal-content').message({
                type: 'error',
                content: result.actionError
                });
            }
        });
    });
	PageLoader = {
	   //
	    initSearchPanel:function(){
	        	            	                	            	                	            	                	            	        	     },
	    initGridPanel: function(){
	         var self = this;
	         var width = 180;
	         return grid.grid({
	                identity:"id",
	                buttons: [
	                        {content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>', action: 'add'},
	                        {content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>', action: 'modify'},
	                        {content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>', action: 'delete'},
	                        {content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>编辑mapspot</button>', action: 'editMapspot'}
	                    ],
	                url:"${pageContext.request.contextPath}/VtourMap/pageJson.koala",
	                columns: [
	                     	                         	                         { title: 'name', name: 'name', width: width},
	                         	                         	                         	                         { title: 'mapUUID', name: 'mapUUID', width: width},
	                         	                         	                         	                         { title: 'vtourDTO.uuid', name: 'vtourDTO.uuid', width: width},
	                         	                         	                             { title: '操作', width: 120, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     var h = "<a href='javascript:openDetailsPage(" + param + ")'>查看</a> ";
	                                     return h;
	                                 }
	                             }
	                ]
	         }).on({
	                   'add': function(){
	                       self.add($(this));
	                   },
	                   'editMapspot': function(event, data){
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
		                    self.editMapspot(indexs[0], $(this));
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
	        $.get('${pageContext.request.contextPath}/pano/getAllVtour.koala').done(function(json){
	        	json = json.data;
	        	$(".dropdown-menu").html("");
	        	for(var i=0;i<json.length;i++) {
	        		$(".dropdown-menu").append('<li role="presentation"><a role="menuitem" tabindex="-1" href="#" vtourid="'+json[i].id+'">'+json[i].uuid+'</a></li>');
	        	}
	        	mapDialog.find('.dropdown-menu li a').on("click", function(){
	        		$("[name='vtourDTO.id']").val( $(this).attr("vtourid") );
	        	});
	        });
	    	mapDialog.modal({
                keyboard:false
            }).on({
				'hidden.bs.modal' : function() {
					mapDialog.modal('hide');
                    grid.data('koala.grid').refresh();
				}
            });
	    },
	    editMapspot: function(id, grid){
	    	idIndex = 0;
	        var self = this;
	        var img_width,img_height;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog">'
	        	+'<div class="modal-content"><div class="modal-header"><button type="button" class="close" '
	        	+'data-dismiss="modal" aria-hidden="true">&times;</button>'
	        	+'<h4 class="modal-title">新增</h4></div><div class="modal-body">'
	        	+'<p>One fine body&hellip;</p></div><div class="modal-footer">'
	        	+'<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
	        	+'<button type="button" class="btn btn-success" id="addMapspot">添加Mapspot</button>'
	        	+'<button type="button" class="btn btn-success" id="save">保存</button></div></div>'
	        	+'</div></div>');
	        $.get('<%=path%>VtourMap-editMapspot.jsp').done(function(html){
	            dialog.find('.modal-body').html(html);
	            self.initPage(dialog.find('form'));
               $.get( '${pageContext.request.contextPath}/VtourMap/get/' + id + '.koala').done(function(json){
                      json = json.data;
                      img_width = json.width;
                      img_height = json.height;
                      dialog.find('#map_container').css("background-image","url(uploads/maps/"+json.mapUUID+")");
                      /* dialog.find('#map_container:before').css("padding-top", json.aspectRadio); */

                      $.each(document.styleSheets[0].rules, function(index){
			    			if(this.cssText.indexOf('#map_container::before') != -1){
			    				document.styleSheets[0].deleteRule(index);
			    			}
			    		})
              		document.styleSheets[0].insertRule('#map_container:before {content:"";display: block;padding-top: '+json.aspectRadio+';}',0);
                      //回显mapspot
                      for(var i=0;i<json.mapspotDTOs.length;i++) {
                      dialog.find('#map_container').append('<img src="${pageContext.request.contextPath}/images/camicon.png" id="drag_reid'+i+'" class="drag_item draggable ui-widget-content">');
                      dialog.find( "#drag_reid"+i ).draggable({ containment: "#map_container", scroll: false });
                      dialog.find("#drag_reid"+i).css({position:"absolute",left:json.mapspotDTOs[i].x_percent,top:json.mapspotDTOs[i].y_percent});
                      }
                      
                      //构造pano dropdown
                      for(var i=0;i<json.vtourDTO.panoDTOs.length;i++) {
                    	  dialog.find('#panoSel').append('<option panoId="'+json.vtourDTO.panoDTOs[i].id+'">'+json.vtourDTO.panoDTOs[i].destPanoImgPath+'</option>');
                      }
                      
              		/* alert(document.styleSheets[0]); */
                       /* var elm;
                       for(var index in json){
                           elm = dialog.find('#'+ index + 'ID');
                           if(elm.hasClass('select')){
                               elm.setValue(json[index]);
                           }else{
                               elm.val(json[index]);
                           }
                       } */
       	            dialog.modal({
    	                keyboard:false
    	            }).on({
    	                'hidden.bs.modal': function(){
    	                    $(this).remove();
    	                }
    	            })
                });
	        });
	        dialog.find('#addMapspot').on('click', function(e){
				$("#map_container").append('<img src="${pageContext.request.contextPath}/images/camicon.png" id="drag_newid'+idIndex+'" class="drag_item draggable ui-widget-content">');
		    	$( "#drag_newid"+idIndex ).draggable({ containment: "#map_container", scroll: false });
		    	idIndex++;
	        });
	        dialog.find('#save').on('click',{grid: grid}, function(e){
	              /* if(!Validator.Validate(dialog.find('form')[0],3))return; */
	              //构造mapspotDTOs
	              var mapspotDTOs = new Array();
	              for(var i=0; i<idIndex; i++) {
	            	  mapspotDTOs.push({x_percent:$("#drag_newid"+i).position().left/5+"%",y_percent:$("#drag_newid"+i).position().top*img_width/5/img_height+"%",sceneName:$("#panoSel").val()});
	              }
	              var map_param = {id:id,mapspotDTOs:mapspotDTOs};
	              $.ajax({
	  				url:"${pageContext.request.contextPath}/VtourMap/editMapspot.koala",
	  				type:"post",
	  				data:JSON.stringify(map_param),
	  				dataType:"json",
	  				contentType:"application/json",
	  				success:function(data){
	  				},error:function(data){
	  				}
	  			});
	              /* $.post('${pageContext.request.contextPath}/Classes/add.koala', dialog.find('form').serialize()).done(function(result){
	                   if(result.success ){
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
	              }); */
	        });
	    },
	    modify: function(id, grid){
	        var self = this;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">修改</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button><button type="button" class="btn btn-success" id="save">保存</button></div></div></div></div>');
	        $.get('<%=path%>/VtourMap-update.jsp').done(function(html){
	               dialog.find('.modal-body').html(html);
	               self.initPage(dialog.find('form'));
	               $.get( '${pageContext.request.contextPath}/VtourMap/get/' + id + '.koala').done(function(json){
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
	                    $.post('${pageContext.request.contextPath}/VtourMap/update.koala?id='+id, dialog.find('form').serialize()).done(function(result){
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
	    	$.post('${pageContext.request.contextPath}/VtourMap/delete.koala', data).done(function(result){
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

var openDetailsPage = function(id){
        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">查看</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-info" data-dismiss="modal">返回</button></div></div></div></div>');
        $.get('<%=path%>/VtourMap-view.jsp').done(function(html){
               dialog.find('.modal-body').html(html);
               $.get( '${pageContext.request.contextPath}/VtourMap/get/' + id + '.koala').done(function(json){
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
                      <label class="control-label" style="width:100px;float:left;">mapImg:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="mapImg" class="form-control" type="text" style="width:180px;" id="mapImgID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">position:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="position" class="form-control" type="text" style="width:180px;" id="positionID"  />
        </div>
                </td>
       <td style="vertical-align: bottom;"><button id="search" type="button" style="position:relative; margin-left:35px; top: -15px" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button></td>
  </tr>
</table>	
</form>
<!-- grid -->
<div id=<%=gridId%>></div>
</div>



	<div class="modal fade" id="vtourMapModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel2" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">给vtour添加地图</h4>
				</div>
				<div class="modal-body" id="modal-body">
					<!-- <input id="pano" type="file" name="pano" multiple> -->
					<!-- <div class="container"> -->
					<form class="form-horizontal" id="mapForm" action="VtourMap/upload.koala" method="POST" enctype="multipart/form-data">

						<div class="form-group">
							<label class="col-lg-3 control-label">name:</label>
							<div class="col-lg-9">
								<input name="name" style="display: inline; width: 94%;"
									class="form-control" type="text" id="nameID" />
							</div>
						</div>
						
						<div class="form-group">
							<input name="mapImg" style="display: inline; width: 94%;"
								class="form-control" type="file" id="mapImgID" multiple />
							<input type="hidden" name="mapUUID" />
							<button type="submit" id="fileupload" class="btn btn-default">开始上传</button>
						</div>
						
						<!-- vtour -->
							<label class="col-lg-3 control-label">vtour:</label>
							<div class="col-lg-9">
								<div class="dropdown" style="display:block">
							      <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
							        Dropdown
							        <span class="caret"></span>
							      </button>
							      <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" mapId="1">Action</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" mapId="2">Another action</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" mapId="3">Something else here</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="#" mapId="4">Separated link</a></li>
							      </ul>
							    </div>
							</div>
						
						<input type="hidden" name="aspectRadio" />
						<input type="hidden" name="width" />
						<input type="hidden" name="height" />
						<input type="hidden" name="vtourDTO.id" />
						
					</form>
					<!-- </div> -->
				</div>
				<div class="modal-footer">
					<button id="saveMap" type="button" class="btn btn-default" data-dismiss="modal">保存</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal -->
	</div>
	<script type="text/javascript">
	
$(function(){
	
	$('form').ajaxForm({
	    target: '#myResultsDiv',
	    type: "post",
	    dataType: "json",
	    success: function(data, textStatus, jqXHR) {
	    	/* alert(data.fileName); */
	    	$("[name='mapUUID']").val(data.fileUUID);
	    	$("[name='aspectRadio']").val(data.aspectRadio);
	    	$("[name='width']").val(data.width);
	    	$("[name='height']").val(data.height);
	    },
	    error: function() {
	    	/* alert('e') */
	    }
	});
	
})	
	
/* 
	$("#fileupload").AjaxFileUpload({
		action:"/VtourMap/upload.koala",
		onComplete: function(obj) {
			alert(obj.filename);
		}
	}); */
	</script>
</body>
</html>
