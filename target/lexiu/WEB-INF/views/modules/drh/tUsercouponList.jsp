<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>usercoupon管理</title>
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
		<li class="active"><a href="${ctx}/drh/tUsercoupon/">usercoupon列表</a></li>
		<shiro:hasPermission name="drh:tUsercoupon:edit"><li><a href="${ctx}/drh/tUsercoupon/form">usercoupon添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tUsercoupon" action="${ctx}/drh/tUsercoupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>userid：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>couponid：</label>
				<form:input path="couponid" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>couponid</th>
				<shiro:hasPermission name="drh:tUsercoupon:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUsercoupon">
			<tr>
				<td><a href="${ctx}/drh/tUsercoupon/form?id=${tUsercoupon.id}">
					${tUsercoupon.userid}
				</a></td>
				<td>
					${tUsercoupon.couponid}
				</td>
				<shiro:hasPermission name="drh:tUsercoupon:edit"><td>
    				<a href="${ctx}/drh/tUsercoupon/form?id=${tUsercoupon.id}">修改</a>
					<a href="${ctx}/drh/tUsercoupon/delete?id=${tUsercoupon.id}" onclick="return confirmx('确认要删除该usercoupon吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>