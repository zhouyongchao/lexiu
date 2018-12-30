<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>折扣信息管理</title>
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
		<li class="active"><a href="${ctx}/drh/tSecurities/">折扣信息列表</a></li>
		<shiro:hasPermission name="drh:tSecurities:edit"><li><a href="${ctx}/drh/tSecurities/form">折扣信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tSecurities" action="${ctx}/drh/tSecurities/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>type：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>discount：</label>
				<form:input path="discount" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>mechanismid：</label>
				<form:input path="mechanismid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>endtime：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tSecurities.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>status：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>type</th>
				<th>discount</th>
				<th>mechanismid</th>
				<th>endtime</th>
				<th>status</th>
				<shiro:hasPermission name="drh:tSecurities:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tSecurities">
			<tr>
				<td><a href="${ctx}/drh/tSecurities/form?id=${tSecurities.id}">
					${fns:getDictLabel(tSecurities.type, '', '')}
				</a></td>
				<td>
					${tSecurities.discount}
				</td>
				<td>
					${tSecurities.mechanismid}
				</td>
				<td>
					<fmt:formatDate value="${tSecurities.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(tSecurities.status, '', '')}
				</td>
				<shiro:hasPermission name="drh:tSecurities:edit"><td>
    				<a href="${ctx}/drh/tSecurities/form?id=${tSecurities.id}">修改</a>
					<a href="${ctx}/drh/tSecurities/delete?id=${tSecurities.id}" onclick="return confirmx('确认要删除该折扣信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>