<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/4
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="update_id">
    <input type="hidden" name="oorder" value="1">
    <input type="hidden" name="statesss" value="0">
    <input type="hidden" name="id" value="${organization.id}">
    <table border="1">
        <tr>
            <th>编码:</th>
            <td><input class="easyui-textbox" name="ocode" value="${organization.ocode}"></td>
            <th>名称:</th>
            <td><input class="easyui-textbox" name="text" value="${organization.text}"></td>
        </tr>
        <tr>
            <th>简称:</th>
            <td><input class="easyui-textbox" name="onickname" value="${organization.onickname}"></td>
            <th>父节点:</th>
            <td><input class="easyui-combobox" name="pid" id="ppid" value="${organization.pid}"></td>
        </tr>
        <tr>
            <th>分类:</th>
            <td><input class="easyui-textbox" name="otype" value="${organization.otype}"></td>
            <th>负责人:</th>
            <td><input class="easyui-textbox" name="ofunctionary" value="${organization.ofunctionary}"></td>
        </tr>
        <tr>
            <th>副主管:</th>
            <td><input class="easyui-textbox" name="ocommand" value="${organization.ocommand}"></td>
            <th>电话:</th>
            <td><input class="easyui-textbox" name="otelphone" value="${organization.otelphone}"></td>
        </tr>
        <tr>
            <th>内线:</th>
            <td><input class="easyui-textbox" name="otel" value="${organization.otel}"></td>
            <th>传真:</th>
            <td><input class="easyui-textbox" name="ofax" value="${organization.ofax}"></td>
        </tr>
        <tr>
            <th>邮编:</th>
            <td><input class="easyui-textbox" name="opostcode" value="${organization.opostcode}"></td>
            <th>网址:</th>
            <td><input class="easyui-textbox" name="ourl" value="${organization.ourl}"></td>
        </tr>
        <tr>
            <th>地址:</th>
            <td colspan="3"><input class="easyui-textbox" name="oaddress" value="${organization.oaddress}"></td>
        </tr>
        <tr>
            <th>选项:</th>
            <td colspan="3"><input type="radio" name="ooptions" value="1" ${organization.ooptions==1 ? "checked":""}>有效
                <input type="radio" name="ooptions" value="2" ${organization.ooptions==2 ?"checked":""}>内部组织
            </td>
        </tr>
        <tr>
            <th>描述:</th>
            <td colspan="3"><textarea cols="80px" rows="5px" name="oinfor">${organization.oinfor}</textarea></td>
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
