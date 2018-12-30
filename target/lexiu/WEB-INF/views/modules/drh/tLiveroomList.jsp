<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播间管理</title>
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
		<li class="active"><a href="${ctx}/drh/tLiveroom/">直播间列表</a></li>
		<shiro:hasPermission name="drh:tLiveroom:edit"><li><a href="${ctx}/drh/tLiveroom/form">直播间添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tLiveroom" action="${ctx}/drh/tLiveroom/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>title：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>type：</label>
				<form:input path="type" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li><label>isprivate：</label>
				<form:input path="isprivate" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li><label>baseurl：</label>
				<form:input path="baseurl" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>username：</label>
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>type</th>
				<th>isprivate</th>
				<th>baseurl</th>
				<th>userid</th>
				<th>username</th>
				<shiro:hasPermission name="drh:tLiveroom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tLiveroom">
			<tr>
				<td><a href="${ctx}/drh/tLiveroom/form?id=${tLiveroom.id}">
					${tLiveroom.title}
				</a></td>
				<td>
					${tLiveroom.type}
				</td>
				<td>
					${tLiveroom.isprivate}
				</td>
				<td>
					${tLiveroom.baseurl}
				</td>
				<td>
					${tLiveroom.userid}
				</td>
				<td>
					${tLiveroom.username}
				</td>
				<shiro:hasPermission name="drh:tLiveroom:edit"><td>
    				<a href="${ctx}/drh/tLiveroom/form?id=${tLiveroom.id}">修改</a>
					<a href="${ctx}/drh/tLiveroom/delete?id=${tLiveroom.id}" onclick="return confirmx('确认要删除该直播间吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>