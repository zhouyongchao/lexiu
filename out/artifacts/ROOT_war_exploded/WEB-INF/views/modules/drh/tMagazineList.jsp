<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>杂志管理</title>
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
		<li class="active"><a href="${ctx}/drh/tMagazine/">杂志列表</a></li>
		<shiro:hasPermission name="drh:tMagazine:edit"><li><a href="${ctx}/drh/tMagazine/form">杂志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tMagazine" action="${ctx}/drh/tMagazine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>imageurl：</label>
				<form:input path="imageurl" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>title：</label>
				<form:input path="title" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>content：</label>
				<form:input path="content" htmlEscape="false" maxlength="6000" class="input-medium"/>
			</li>
			<li><label>updatedate：</label>
				<form:input path="updatedate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>title</th>
				<shiro:hasPermission name="drh:tMagazine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tMagazine">
			<tr>
				<td><a href="${ctx}/drh/tMagazine/form?id=${tMagazine.id}">
					${tMagazine.title}
				</a></td>
				<shiro:hasPermission name="drh:tMagazine:edit"><td>
    				<a href="${ctx}/drh/tMagazine/form?id=${tMagazine.id}">修改</a>
					<a href="${ctx}/drh/tMagazine/delete?id=${tMagazine.id}" onclick="return confirmx('确认要删除该杂志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>