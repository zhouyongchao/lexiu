<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏信息管理</title>
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
		<li class="active"><a href="${ctx}/drh/tMycollect/">收藏信息列表</a></li>
		<shiro:hasPermission name="drh:tMycollect:edit"><li><a href="${ctx}/drh/tMycollect/form">收藏信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tMycollect" action="${ctx}/drh/tMycollect/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>userid：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>周边信息：</label>
				<form:input path="peripheryids" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>杂志信息：</label>
				<form:input path="magazineids" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>1已收藏0 取消收藏：</label>
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-medium"/>
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
				<th>周边信息</th>
				<th>杂志信息</th>
				<th>1已收藏0 取消收藏</th>
				<shiro:hasPermission name="drh:tMycollect:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tMycollect">
			<tr>
				<td><a href="${ctx}/drh/tMycollect/form?id=${tMycollect.id}">
					${tMycollect.userid}
				</a></td>
				<td>
					${tMycollect.peripheryids}
				</td>
				<td>
					${tMycollect.magazineids}
				</td>
				<td>
					${tMycollect.status}
				</td>
				<shiro:hasPermission name="drh:tMycollect:edit"><td>
    				<a href="${ctx}/drh/tMycollect/form?id=${tMycollect.id}">修改</a>
					<a href="${ctx}/drh/tMycollect/delete?id=${tMycollect.id}" onclick="return confirmx('确认要删除该收藏信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>