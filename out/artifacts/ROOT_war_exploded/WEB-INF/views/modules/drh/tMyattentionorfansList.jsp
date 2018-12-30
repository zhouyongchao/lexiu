<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关注信息管理</title>
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
		<li class="active"><a href="${ctx}/drh/tMyattentionorfans/">关注信息列表</a></li>
		<shiro:hasPermission name="drh:tMyattentionorfans:edit"><li><a href="${ctx}/drh/tMyattentionorfans/form">关注信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tMyattentionorfans" action="${ctx}/drh/tMyattentionorfans/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关注者id：</label>
				<form:input path="attentionid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>粉丝id：</label>
				<form:input path="fansid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>status：</label>
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
				<th>关注者id</th>
				<th>粉丝id</th>
				<th>status</th>
				<shiro:hasPermission name="drh:tMyattentionorfans:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tMyattentionorfans">
			<tr>
				<td><a href="${ctx}/drh/tMyattentionorfans/form?id=${tMyattentionorfans.id}">
					${tMyattentionorfans.attentionid}
				</a></td>
				<td>
					${tMyattentionorfans.fansid}
				</td>
				<td>
					${tMyattentionorfans.status}
				</td>
				<shiro:hasPermission name="drh:tMyattentionorfans:edit"><td>
    				<a href="${ctx}/drh/tMyattentionorfans/form?id=${tMyattentionorfans.id}">修改</a>
					<a href="${ctx}/drh/tMyattentionorfans/delete?id=${tMyattentionorfans.id}" onclick="return confirmx('确认要删除该关注信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>