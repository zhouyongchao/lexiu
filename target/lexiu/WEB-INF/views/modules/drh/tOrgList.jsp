<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构信息管理</title>
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
		<li class="active"><a href="${ctx}/drh/tOrg/">机构信息列表</a></li>
		<%--<li><a href="${ctx}/drh/tOrg/add">机构信息添加</a></li>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="tOrg" action="${ctx}/drh/tOrg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>登录账户：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>机构名：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>省：</label>
				<form:input path="province" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录账户</th>
				<th>机构名</th>
				<th>用户名</th>
				<th>标签</th>
				<th>省</th>
				<th>市</th>
				<th>区</th>
				<th>街道</th>
				<th>街道号</th>
				<th>经度</th>
				<th>纬度</th>
				<th>星级</th>
				<th>联系方式</th>
				<th>城市id</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tOrg">
			<tr>
				<td><a href="${ctx}/drh/tOrg/form?id=${tOrg.id}">
					${tOrg.loginName}
				</a></td>
				<td>
						${tOrg.title}
				</td>
				<td>
						${tOrg.name}
				</td>
				<td>
					${tOrg.tags}
				</td>
				<td>
					${tOrg.province}
				</td>
				<td>
					${tOrg.city}
				</td>
				<td>
					${tOrg.distrinct}
				</td>
				<td>
					${tOrg.street}
				</td>
				<td>
					${tOrg.streetnumber}
				</td>
				<td>
					${tOrg.longitude}
				</td>
				<td>
					${tOrg.latitude}
				</td>
				<td>
					${tOrg.startlevel}
				</td>
				<td>
					${tOrg.phone}
				</td>
				<td>
					${tOrg.cityid}
				</td>
				<td>
    				<a href="${ctx}/drh/tOrg/form?id=${tOrg.id}">修改</a>
					<a href="${ctx}/drh/tOrg/delete?id=${tOrg.id}" onclick="return confirmx('确认要删除该机构信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>