<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="${ctx}"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/WEB-INF/views/commons/easyui.jsp"%>
		<script type="text/javascript" src="${ctx}/resources/public/scripts/common.js" ></script>
		<script type="text/javascript">
		$(function(){
			checkEditControl('${ctx}/admin/app/catalog');
		});
		function edit() {
			var m = $('#tt').datagrid('getSelected');
			if (m) {
				$('#editWin').window('open');
				// init data
				$('#editForm input[name=name]').val(m.name);
				$('#editForm input[name=displayIndex]').val(m.displayIndex);
				$('#editForm input[name=status]').val(m.status);
				$('#description').val(m.description);
				$('#id').val(m.id);
				$('#editWin').show();
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}

		function del() {
			var m = $('#tt').datagrid('getSelected');
			if (m) {
				$.messager.confirm('警告','删除后该分类的应用将无法关联，您确认要删除吗?',
						function(data) {
					if (data) {
						$.ajax({
							url : '${ctx}/admin/app/catalog/delete/'+ m.id,
							type : 'GET',
							timeout : 1000,
							error : function() {
								$.messager.alert('错误','删除失败!','error');
							},
							success : function(data) {
								if (data == -1) {
									$.messager.alert('错误','删除失败!','error');
								} else if (data > 0) {
									$.messager.alert('成功','删除成功','info');
									// update rows
									$('#tt').datagrid('reload');
								} else {
									$.messager.alert('异常','后台系统异常','error');
								}
							}
						});
					}
				});
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要删除的记录。'
				});
			}
		}
		</script>
		<style>
		#editWin label {width: 115px;}
		#editWin input {width: 180px;}
		#editWin select {width: 185px;}
		#editWin textarea {width: 300px;height: 60px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="appCatalog"
			action="${ctx}/admin/app/catalog/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="name" path="name">邮箱：</form:label>
					</td>
					<td>
						<form:input path="name" cssClass="easyui-validatebox" />
					</td>
					<td>
						<form:label for="displayIndex" path="displayIndex">显示顺序：</form:label>
					</td>
					<td>
						<form:input path="displayIndex" cssClass="easyui-validatebox" />
					</td>
					<td>
						<form:label for="status" path="status">状态：</form:label>
					</td>
					<td>
						<form:select path="status" cssClass="easyui-combobox">
							<form:option value="">请选择</form:option>
							<form:option value="0">停用</form:option>
							<form:option value="1">启用</form:option>
						</form:select>
					</td>
				</tr>
			</table>
		</form:form>
		<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="submit"
					iconCls="icon-search">查 询</a>
				<a href="javascript:void();" class="easyui-linkbutton" id="reset"
					iconCls="icon-undo">重 置</a>
			</div>
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="应用分类列表" align="left" singleSelect="true" 
			idField="id" url="${ctx}/admin/app/catalog/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[ 5, 10]" sortName="displayIndex" sortOrder="asc">
				<thead>
					<tr>
						<th field="name" width="150" align="center">分类名称</th>
						<th field="description" width="200" align="center">描述</th>
						<th field="displayIndex" width="60" align="center" >显示顺序</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="createTime" width="100" align="center">创建时间</th>
						<th field="updateTime" width="100" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="编辑应用分类" closed="true" style="width:400px;height:300px;padding:5px;" modal="true">
			<form:form modelAttribute="appCatalog" id="editForm" action="${ctx}/admin/app/catalog/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="name" path="name"  cssClass="mustInput">分类名称：</form:label></td>
						<td><form:input path="name" cssClass="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td><form:label for="displayIndex" path="displayIndex" cssClass="easyui-validatebox">顺序：</form:label></td>
						<td><form:input path="displayIndex" cssClass="easyui-numberspinner"  value="1"  min="1" max="1000" required="true" validType="number"/></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="easyui-validatebox">状态：</form:label></td>
						<td>
							<form:select path="status" cssClass="easyui-combobox">
								<form:option value="1">启用</form:option>
								<form:option value="0">停用</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label	for="description" path="description" cssClass="easyui-validatebox">描述：</form:label></td>
					</tr>
					<tr>
						<td colspan="2">
							<form:textarea path="description" />
						</td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit"
						iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset"
						iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
	</body>
</html>