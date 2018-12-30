<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>首页导航图管理</title>
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
    <li class="active"><a href="${ctx}/drh/banner/">首页导航图列表</a></li>
    <li><a href="${ctx}/drh/banner/add">首页导航图添加</a></li>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>次序编号</th>
        <th>图片链接</th>
        <th>跳转链接</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="banner">
        <tr>
            <td><a href="${ctx}/drh/banner/form?id=${banner.id}">
                    ${banner.orderNo}
            </a></td>
            <td>
                    ${banner.imageUrl}
            </td>
            <td>
                    ${banner.skipUrl}
            </td>
            <td>
                    ${banner.status}
            </td>

            <td>
                <a href="${ctx}/drh/banner/form?id=${banner.id}">修改</a>
                <a href="${ctx}/drh/banner/delete?id=${banner.id}"
                   onclick="return confirmx('确认要删除该封面图信息吗？', this.href)">删除
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>