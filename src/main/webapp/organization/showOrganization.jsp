<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/4
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="adddiv"></div>
<div id="upid"></div>
<table id="treegridid" data-options="fit:true"></table>
<script>

     $(function(){
         querytree();
     })

    function querytree(){
    $('#treegridid').treegrid({
        url:'../userController/queryGridTree.jhtml',
        idField:'id',
        treeField:'text',
        toolbar: [{
            iconCls: 'icon-reload',
            text:'刷新',
            handler: function(){
                querytree();
            }
        },'-',{
            iconCls: 'icon-add',
            text:'新增',
            handler: function(){
             saveOrganization();
            }
        },'-',{
            iconCls: 'icon-edit',
            text:'修改',
            handler: function(){
             toupdate();
            }
        },'-',{
            iconCls: 'icon-remove',
            text:'删除',
            handler: function(){
                deOrganization();
            }
        },'-',{
            iconCls: 'icon-redo',
            text:'导出',
            handler: function(){
              exportfile();
            }
        },'-',{
            iconCls: 'icon-undo',
            text:'导入',
            handler: function(){
               importfile();
            }
        }],
        singleSelect:false,
        checkOnSelect:true,
        selectOnCheck:true,
        columns:[[
            {field:'checke',checkbox:true},
            {title:'组织机构名称',field:'text',width:180},
            {field:'ocode',title:'编码',width:60,align:'right'},
            {field:'onickname',title:'简称',width:80},
            {field:'ofunctionary',title:'主负责人',width:80},
            {field:'otelphone',title:'电话',width:80},
            {field:'ofax',title:'传真',width:80},
            {field:'statesss',title:'传真',width:80,
              formatter:function(index,row,value){
                return row.statesss==1?"不可删除":"";
              }
            }
        ]]
    });
    }


    //修改回显
    function toupdate(){
        var array = $("#treegridid").treegrid("getChecked");
        var arr = "";
        if(array.length==1){
            arr=array[0]['id'];


            if(confirm("确实修改这条数据吗?")) {
                $("#upid").dialog({
                    title: '修改组织机构',
                    width: 700,
                    height: 400,
                    modal: true,
                    href: "../userController/queryOrganizationById.jhtml?id=" + arr,
                    buttons: [{
                        text: '保存',
                        handler: function () {
                            $.ajax({
                                url: "../userController/updateOrganization.jhtml",
                                type: "post",
                                data: $("#update_id").serialize(),
                                success: function (result) {
                                    if(result > 0){
                                        querytree();
                                        $("#upid").dialog('close');
                                        $.messager.show({
                                            title: '我的消息',
                                            msg: '修改成功',
                                            timeout: '2000',
                                            showType: 'slide'
                                        });
                                    }
                                }
                            });
                        }
                    }, {

                        text: '关闭',
                        handler: function () {
                            $("#upid").dialog('close');
                        }


                    }]
                });

            }



            }else{
            $.messager.alert('提示','请选择一条要修改的数据!!!','warning');
        }

    }

    //新增
    function saveOrganization(){
        $("#adddiv").dialog({
            title: '添加组织机构',
            width: 700,
            height: 400,
            modal: true ,
            href:'../organization/addOrg.jsp',
            buttons:[{
                text:'保存',
                handler:function(){
                    if(confirm("确定保存么")){
                        $.ajax({
                            url:"<%=request.getContextPath()%>/userController/saveOrganization.jhtml",
                            data:$("#save_id").serialize(),
                            type:"post",
                            success:function(result){
                                querytree();
                                $("#adddiv").dialog('close');
                                $.messager.show({
                                   title:'我的消息',
                                   msg:'添加成功',
                                   timeout:'2000',
                                   showType:'show'
                                });
                            }


                        });

                    }

                }},{
                text:'关闭',
                handler:function(){
                    $("#adddiv").dialog('close');
                }
            }]
        })
    }



//删除
    function deOrganization(){
       var array = $("#treegridid").treegrid("getChecked");
       var starts = "";
       var arr = "";
       var count = 0;
       for(var i=0;i<array.length;i++){
           arr +=",'"+array[i]['id']+"'";
           starts += array[i]['statesss'];
           count++;
       }
        arr = arr.substring(1);
        if(count<1){
            $.messager.alert('提示','请选择要删除的数据!!!','warning');
        }else{
            $.messager.confirm('提示','确定删除这'+count+'条数据吗?',function(r){
                if(r){
                 if(starts.indexOf('1')>-1){
                     $.messager.alert('提示','包含了不能删除的数据！！！','warning');
                 }else{
                     $.ajax({
                         url:'../userController/deleteOrganization.jhtml',
                         data:{id:arr},
                         type:'post',
                         success:function(msg){
                             $.messager.show({
                                 title:'我的消息',
                                 msg:'删除成功',
                                 timeout:'3000',
                                 showType:'show'
                             });
                             querytree();
                         }
                     })
                 }

                }
            })

        }
    }

//导入
     function importfile(){
         $.ajax({
             url:'../userController/importExcel.jhtml',
             type:'post',
             async:true,
             success:function (msg) {
                 $.messager.show({
                     title:'我的消息',
                     msg:'导入成功',
                     timeout:3000,
                     showType:'show'
                 });
             }
         })
     }
//导出
  function exportfile(){
        $.ajax({
            url:'../userController/ExportExcel.jhtml',
            type:'post',
            async:true,
            success:function (msg) {
                $.messager.show({
                    title:'我的消息',
                    msg:'导出成功',
                    timeout:3000,
                    showType:'show'
                });
            }
        })
  }


</script>
</body>
</html>
