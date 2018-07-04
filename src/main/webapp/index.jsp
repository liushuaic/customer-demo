<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/4
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <!-- 引入EasyUI的样式文件-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/EasyUI/themes/default/easyui.css" type="text/css"/>

    <!-- 引入EasyUI的图标样式文件-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/EasyUI/themes/icon.css" type="text/css"/>
</head>
<body>
<!-- 引入JQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/EasyUI/jquery.min.js"></script>

<!-- 引入EasyUI -->
<script type="text/javascript"src="<%=request.getContextPath()%>/EasyUI/jquery.easyui.min.js"></script>

<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
<script type="text/javascript"src="<%=request.getContextPath()%>/EasyUI/locale/easyui-lang-zh_CN.js"></script>

<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'分享成功。创造卓越',split:true" style="height:150px;"></div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
        <ul id="orgtree"></ul>
    </div>
    <div data-options="region:'center'"  style="padding:5px;background:#eee;">
        <div id="orgtabs" class="easyui-tabs" data-options="fit:true">

        </div>

    </div>
</div>

<script>
     $("#orgtree").tree({
         url:'<%=request.getContextPath()%>/userController/queryOrgTree.jhtml',
         lines:true,
         onClick:function(node){
             query(node.text,node.href);
         }
     })

    function query(title,href){
         var flag = $("#orgtabs").tabs("exists",title);
         if(flag){
            $("#orgtabs").tabs("select",title);
         }else{
             $('#orgtabs').tabs('add',{
                 title:title,
                 content:'Tab Body',
                 closable:true,
                 href:'../'+href,
                 tools:[{
                     iconCls:'icon-mini-refresh',
                     handler:function(){
                         alert('refresh');
                     }
                 }]
             });
         }
    }

</script>

</body>
</html>
