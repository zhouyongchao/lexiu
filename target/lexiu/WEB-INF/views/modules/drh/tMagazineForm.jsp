<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>杂志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/drh/tMagazine/">杂志列表</a></li>
		<%--<shiro:hasPermission name="drh:tMagazine:edit">${not empty tMagazine.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="drh:tMagazine:edit">查看</shiro:lacksPermission>--%>
		<li class="active"><a href="${ctx}/drh/tMagazine/form?id=${tMagazine.id}">杂志添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tMagazine" action="${ctx}/drh/tMagazine/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">--%>
			<%--<label class="control-label">跳转路径：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="imageurl" htmlEscape="false" maxlength="1000" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副标题：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">封面图：</label>

			<div class="controls">
				<form:hidden id="imageurl" path="imageurl" htmlEscape="false" maxlength="100" class="input-large "/>
				<sys:ckfinder input="imageurl" type="images" uploadPath="/magazine" selectMultiple="true"
							  maxWidth="100"
							  maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正文：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="6000" class="input-xxlarge "/>
				<sys:ckeditor replace="content" uploadPath="/manazine/content" />
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>