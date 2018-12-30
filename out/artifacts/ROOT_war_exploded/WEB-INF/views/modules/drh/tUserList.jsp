<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
		<c:if test="${tUser.type==0}" >
			<li class="active"><a href="${ctx}/drh/tUser/">用户信息列表</a></li>
			<li><a href="${ctx}/drh/tUser/form">用户信息添加</a></li>
		</c:if>
		<c:if test="${tUser.type==1}" >
			<li class="active"><a href="${ctx}/drh/tUser/?type=1">主播信息列表</a></li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="tUser" action="${ctx}/drh/tUser/" method="post" class="breadcrumb form-search">
		<input id="status" name="status" type="hidden" value="${tUser.status}">
		<input id="type" name="type" type="hidden" value="${tUser.type}">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属机构id</th>
				<th>用户名</th>
				<th>用户类型</th>
				<th>昵称</th>
				<th>个人签名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>省</th>
				<th>市</th>
				<th>area</th>
				<th>地址</th>
				<th>喜好</th>
				<th>余额</th>
				<th>省份证号</th>
				<th>银行卡</th>
				<th>审核</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUser">
				<td><a href="${ctx}/drh/tUser/form?id=${tUser.id}">
					${tUser.sid}
				</a></td>
				<td>
					${tUser.username}
				</td>
				<td>
					<c:if test="${tUser.type==0}" >
						普通用户
					</c:if>
					<c:if test="${tUser.type==1}" >
						主播
					</c:if>
				</td>
				<td>
					${tUser.nickname}
				</td>
				<td>
					${tUser.signature}
				</td>
				<td>
					<c:if test="${tUser.sex=='M'}" >
						女
					</c:if>
					<c:if test="${tUser.sex=='F'}" >
						男
					</c:if>
				</td>
				<td>
					${tUser.age}
				</td>
				<td>
					${tUser.province}
				</td>
				<td>
					${tUser.city}
				</td>
				<td>
					${tUser.area}
				</td>
				<td>
					${tUser.address}
				</td>

				<td>
					${tUser.interest}
				</td>
				<td>
					${tUser.umoney}
				</td>
				<td>
					${tUser.idcard}
				</td>
				<td>
					${tUser.bankcard}
				</td>
			<td>
				<a href="${ctx}/drh/tUser/save?id=${tUser.id}&status=1">通过</a>
				<a href="${ctx}/drh/tUser/save?id=${tUser.id}$status=2">不通过</a>
			</td>
				<td>
    				<a href="${ctx}/drh/tUser/form?id=${tUser.id}">修改</a>
					<a href="${ctx}/drh/tUser/delete?id=${tUser.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
					<a href="${ctx}/drh/gift/record/list?userid=${tUser.id}">礼物</a>

				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>