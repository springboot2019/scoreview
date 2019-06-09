
function removeUsers(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#delete').dialog('open').dialog('setTitle','删除学生');
        //显示未修改前的学生信息
        $('#fimd').form('load',row);
    }
}
function fromExcel(){
        $('#excel_in').dialog('open').dialog('setTitle','通过excel导入（请将xls文件放在电脑桌面然后输入文件名:xxx.xls）');

        $('#ei').form('clear');
}

function toExcel(){
    $('#excel_out').dialog('open').dialog('setTitle','通过excel导出(将生成template.xls文件到桌面)');
    $('#eo').form('clear');
}
//添加新用户
function newUsers(){
    //添加用户对话框
    $('#adddg').dialog('open').dialog('setTitle','添加学生信息');
    //数据清空
    $('#fam').form('clear');

}

//用户信息修改
function editUsers(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改信息');
        //显示未修改前的学生信息
        $('#fim').form('load',row);
    }
}


//信息保存按钮事件
function saveUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    var add="/students/add";

    $('#fim').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//用户信息添加按钮事件
function addUsers(){
    var add="/students/add";
    $('#fam').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

/*

//用户删除按钮事件
function removeUsers(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该学生?',function(r){
            if (r){

                $.post('/students/delete',{stu_id:row.stu_id},function(result){
                    if (result.success){

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }
}*/
