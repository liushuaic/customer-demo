<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/4
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="save_id">
    <input type="hidden" name="oorder" value="1">
    <input type="hidden" name="statesss" value="0">
<table border="1">
    <tr>
        <th>编码:</th>
        <td><input class="easyui-textbox" name="ocode"></td>
        <th>名称:</th>
        <td><input class="easyui-textbox" name="text"></td>
    </tr>
    <tr>
        <th>简称:</th>
        <td><input class="easyui-textbox" name="onickname"></td>
        <th>父节点:</th>
        <td><input class="easyui-combobox" name="pid" id="ppid"></td>
    </tr>
    <tr>
        <th>分类:</th>
        <td><input class="easyui-textbox" name="otype"></td>
        <th>负责人:</th>
        <td><input class="easyui-textbox" name="ofunctionary"></td>
    </tr>
    <tr>
        <th>副主管:</th>
        <td><input class="easyui-textbox" name="ocommand"></td>
        <th>电话:</th>
        <td><input class="easyui-textbox" name="otelphone"></td>
    </tr>
    <tr>
        <th>内线:</th>
        <td><input class="easyui-textbox" name="otel"></td>
        <th>传真:</th>
        <td><input class="easyui-textbox" name="ofax"></td>
    </tr>
    <tr>
        <th>邮编:</th>
        <td><input class="easyui-textbox" name="opostcode"></td>
        <th>网址:</th>
        <td><input class="easyui-textbox" name="ourl"></td>
    </tr>
    <tr>
        <th>地址:</th>
        <td colspan="3"><input class="easyui-textbox" name="oaddress"></td>
    </tr>
    <tr>
        <th>选项:</th>
        <td colspan="3"><input type="radio" name="ooptions" value="1">有效
            <input type="radio" name="ooptions" value="2">内部组织
        </td>
    </tr>
    <tr>
        <th>描述:</th>
        <td colspan="3"><textarea cols="80px" rows="5px" name="oinfor"></textarea></td>
    </tr>
</table>
</form>
<script>
    $(function(){
     $('#ppid').combotree({
        url: '../userController/queryGridTree.jhtml',
        required: true
    });
    })
</script>
</body>
</html>
