<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>hl管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/drh/tSignlnrecord/">hl列表</a></li>
		<shiro:hasPermission name="drh:tSignlnrecord:edit"><li><a href="${ctx}/drh/tSignlnrecord/form">hl添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tSignlnrecord" action="${ctx}/drh/tSignlnrecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>userid：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>signdate：</label>
				<input name="signdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tSignlnrecord.signdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>userid</th>
				<th>signdate</th>
				<shiro:hasPermission name="drh:tSignlnrecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tSignlnrecord">
			<tr>
				<td><a href="${ctx}/drh/tSignlnrecord/form?id=${tSignlnrecord.id}">
					${tSignlnrecord.userid}
				</a></td>
				<td>
					<fmt:formatDate value="${tSignlnrecord.signdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="drh:tSignlnrecord:edit"><td>
    				<a href="${ctx}/drh/tSignlnrecord/form?id=${tSignlnrecord.id}">修改</a>
					<a href="${ctx}/drh/tSignlnrecord/delete?id=${tSignlnrecord.id}" onclick="return confirmx('确认要删除该hl吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>