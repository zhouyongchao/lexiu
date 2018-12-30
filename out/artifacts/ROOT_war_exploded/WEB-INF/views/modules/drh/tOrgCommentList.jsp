<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构评论管理</title>
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
		<li class="active"><a href="${ctx}/drh/tOrgComment/">机构评论列表</a></li>
		<shiro:hasPermission name="drh:tOrgComment:edit"><li><a href="${ctx}/drh/tOrgComment/form">机构评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tOrgComment" action="${ctx}/drh/tOrgComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>orgid：</label>
				<form:input path="orgid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>userid：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>content：</label>
				<form:input path="content" htmlEscape="false" maxlength="6000" class="input-medium"/>
			</li>
			<li><label>imageurl：</label>
				<form:input path="imageurl" htmlEscape="false" maxlength="3000" class="input-medium"/>
			</li>
			<li><label>statute：</label>
				<form:input path="statute" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>orgid</th>
				<th>userid</th>
				<th>content</th>
				<th>imageurl</th>
				<th>statute</th>
				<shiro:hasPermission name="drh:tOrgComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tOrgComment">
			<tr>
				<td><a href="${ctx}/drh/tOrgComment/form?id=${tOrgComment.id}">
					${tOrgComment.orgid}
				</a></td>
				<td>
					${tOrgComment.userid}
				</td>
				<td>
					${tOrgComment.content}
				</td>
				<td>
					${tOrgComment.imageurl}
				</td>
				<td>
					${tOrgComment.statute}
				</td>
				<shiro:hasPermission name="drh:tOrgComment:edit"><td>
    				<a href="${ctx}/drh/tOrgComment/form?id=${tOrgComment.id}">修改</a>
					<a href="${ctx}/drh/tOrgComment/delete?id=${tOrgComment.id}" onclick="return confirmx('确认要删除该机构评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>