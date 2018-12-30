<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:70%;}
		/* p{margin-left:5px; font-size:14px;} */
	</style>
	<style type="text/css">
		.table-item,.table-item-sub,.table-item-abreast{width:500px;padding:0;border-left:solid 1px #d4d5d7;border-right:solid 1px #d4d5d7;border-bottom:solid 1px #d4d5d7;background-color:#fff;border-collapse:collapse}
		.table-item,.table-item-sub{margin:0}
		.table-item-abreast{margin:0}
		.table-item{border-top:solid 1px #b6b7bc}
		.table-item td table td{border:0;height:auto}		
		.table-item-abreast{border-top:solid 1px #d4d5d7}
		.table-item-sub{border-top:solid 3px #d4d5d7}
		.table-item-sub th,.table-item th,.table-item-abreast th,.table-item-sub td,.table-item td,.table-item-abreast td{padding:4px 5px 4px 5px;border-top:solid 0 #d4d5d7;border-left:solid 1px #d4d5d7;border-right:solid 1px #d4d5d7;border-bottom:solid 1px #d4d5d7}
		.table-item-sub th,.table-item th,.table-item-abreast th{text-align:right;background-color:#eaebf0;background-image:none;color:#2e363f;height:30px}
		.table-item-sub td,.table-item td,.table-item-abreast td{text-align:left;height:30px}

		.table-item-abreast .table-item-list{width:100%;padding:0;border-bottom:solid 1px #b9c6d6;background-color:#fff;border-collapse:collapse}
		.table-item-abreast .table-item-list th,.table-item-abreast .table-item-list td{border:1px solid #a1a1a1;padding:0 2px 0 2px;text-align:center}
		.table-item-abreast .table-item-list td{height:20px;background-color:#fff}
		.table-item-abreast .table-item-list th.first,.table-item-abreast .table-item-list td.first{border-left:0;border-right:0}
		.table-item-abreast .table-item-list td.lefttext{padding:0 5px 0 5px;text-align:left}
		.table-item-abreast .table-item-list td.rightnum{padding:0 5px 0 5px;text-align:right}
		.table-item-abreast .table-item-list input{border:1px solid #fff;background-color:#fff}
		.table-item-abreast .table-item-list td.readonlytext{color:#16598d;background-color:#f3f3f3}
		.table-item-abreast .table-item-list input:hover,.table-item-abreast .table-item-list input:focus{border:1px solid #ff6b09;outline:none}
		.table-item-list th{text-align:center}	
		.controls{margin-left: 1px !important;}
		.combtd{width:620px;}
		.control-span{font-weight:normal;float:right;margin-left: 1px !important;}
		.menuContent{

display:none;
 position: absolute; 
 background:white;
 overflow:auto;
 border: 1px solid #ddd; 
z-index:10000;
}
	</style>
	<%--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t3wrfavKyphyLdmG604EnypdXWXr1WFR"></script>--%>
	<title>机构添加</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
</head>
<body>
	<%--<div id="allmap"></div>--%>
  <form:form id="inputForm" modelAttribute="org" action="${ctx}/drh/tOrg/save" method="post" class="form-horizontal">
  <form:hidden path="id"/>
  <table class="table-item">

 			<tr>
                <th >
                    <label class="control-label">机构名：</label>
                </th>
                <td>
					<div class="controls">
						<form:input path="title" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
                  <th>
                    <label class="control-label">标签：</label>
                </th>
                 <td>
					<div class="controls">
						<form:input path="tags" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
                 <th>
                    <label class="control-label">联系方式：</label>
                </th>
                 <td>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
            </tr>
            <tr>
                <th>
                    <label class="control-label">省:</label>
                </th>
                <td>
					<div class="controls">
						<form:input path="province" id="province" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
                  <th>
                    <label class="control-label">市：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:select path="cityid" class="input-large ">  
				             <form:options items="${cityList}" itemLabel="city" itemValue="id"/>  
				         </form:select>  
					</div>
                </td>    
                 <th>
                    <label class="control-label">区：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:input path="distrinct" id="distrinct" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>        
            </tr>
            <tr>
                <th>
                    <label class="control-label">街道：</label>
                </th>
                <td>
					<div class="controls">
						<form:input path="street" id="street" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
                  <th>
                    <label class="control-label">街道号：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:input path="streetnumber" id="streetnumber" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>    
                 <th>
                    <label class="control-label">星级：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:select path="startlevel" class="input-large ">  
				             <form:options items="${fns:getDictList('startlevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				         </form:select>  
					</div>
                </td>        
            </tr>
            <tr>
                <th>
                    <label class="control-label">经度：</label>
                </th>
                <td>
					<div class="controls">
						<form:input path="longitude" id="longitude" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>
                <th>
                    <label class="control-label">纬度：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:input path="latitude" id="latitude" htmlEscape="false" maxlength="100" class="input-large "/>
					</div>
                </td>    
                 <th>
                    <label class="control-label">正文封面图列表：</label>
                </th>                                      
                 <td>
					<div class="controls">
						<form:hidden id="imageurl" path="imageurl" htmlEscape="false" maxlength="100" class="input-large "/>
				        <sys:ckfinder input="imageurl" type="images" uploadPath="/orgphoto" selectMultiple="true" maxWidth="100" maxHeight="100"/>
					</div>
                </td>        
            </tr>
            <tr>
                <th>
                    <label class="control-label">主封面图：</label>
                </th>
                <td>
                    <div class="controls">
                        <form:hidden id="orgimage" path="orgImage" htmlEscape="false" maxlength="100"
                                     class="input-large "/>
                        <sys:ckfinder input="orgimage" type="images" uploadPath="/orgphoto" selectMultiple="true" maxWidth="100" maxHeight="100"/>
                    </div>
                </td>
            </tr>
      <tr>
          <th>
              <label class="control-label">营业开始时间：</label>
          </th>
          <td>
              <div class="controls">
                  <form:input path="startTime" id="startTime" htmlEscape="false" maxlength="100" class="input-large "/>

              </div>
          </td>
          <th>
              <label class="control-label">营业结束时间：</label>
          </th>
          <td>
              <div class="controls">
                  <form:input path="endTime" id="endTime" htmlEscape="false" maxlength="100" class="input-large "/>

              </div>
          </td>
          <th>
              <label class="control-label">状态：</label>
          </th>
          <td>
              <div class="controls">
                  <form:select path="status" class="input-xlarge ">
                      <form:option value="" label=""/>
                      <form:options items="${fns:getDictList('org_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                  </form:select>
                  <%--<form:hidden id="imageurl" path="imageurl" htmlEscape="false" maxlength="100" class="input-large "/>--%>
                  <%--<sys:ckfinder input="imageurl" type="images" uploadPath="/orgphoto" selectMultiple="true" maxWidth="100" maxHeight="100"/>--%>
              </div>
          </td>
      </tr>
		<tr>
			<th>
                  <label class="control-label">机构描述:</label>
             </th>
              <td colspan="5">
			   <div class="controls">
			    <form:textarea id="description" htmlEscape="true" path="description" rows="4" maxlength="200" class="input-large"/>
				<sys:ckeditor replace="description" uploadPath="/org/description" />
				</div>
				</td>
		</tr>
		</table>
		<div style="text-align: center;" >
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</form:form>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");            
	//map.centerAndZoom("北京",12); 
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	var geoc = new BMap.Geocoder();   
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			$("#province").val(addComp.province);
			$("#city").val(addComp.city);
			$("#street").val(addComp.street);
			$("#streetNumber").val(addComp.streetNumber);
			$("#district").val(addComp.district);
			$("#longitude").val(pt.lng);
			$("#latitude").val(pt.lat);
			
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        
		
	});
</script>
