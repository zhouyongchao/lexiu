<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>杂志评论管理</title>
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
		<li class="active"><a href="${ctx}/drh/tMagazineComment/">杂志评论列表</a></li>
		<shiro:hasPermission name="drh:tMagazineComment:edit"><li><a href="${ctx}/drh/tMagazineComment/form">杂志评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tMagazineComment" action="${ctx}/drh/tMagazineComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>content：</label>
				<form:input path="content" htmlEscape="false" maxlength="6000" class="input-medium"/>
			</li>
			<li><label>statute：</label>
				<form:input path="statute" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>commentdate：</label>
				<form:input path="commentdate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>magazineid</th>
				<th>content</th>
				<th>statute</th>
				<th>commentdate</th>
				<shiro:hasPermission name="drh:tMagazineComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tMagazineComment">
			<tr>
				<td><a href="${ctx}/drh/tMagazineComment/form?id=${tMagazineComment.id}">
					${tMagazineComment.magazineid}
				</a></td>
				<td>
					${tMagazineComment.content}
				</td>
				<td>
					${tMagazineComment.statute}
				</td>
				<td>
					${tMagazineComment.commentdate}
				</td>
				<shiro:hasPermission name="drh:tMagazineComment:edit"><td>
    				<a href="${ctx}/drh/tMagazineComment/form?id=${tMagazineComment.id}">修改</a>
					<a href="${ctx}/drh/tMagazineComment/delete?id=${tMagazineComment.id}" onclick="return confirmx('确认要删除该杂志评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>