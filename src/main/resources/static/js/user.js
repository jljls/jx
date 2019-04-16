

//添加员工
    function save() {
        //获取值
        $("#errmsg").html("");
        var userId = $("#new-pu").val();
        var groupId = $("#new-pg").val();
        //表单验证
        if (!userId) {
            $("#user-tip").html("用户ID不能为空!");
            return
        } else {
            //添加
            doAdd(userId, groupId);
        }
    }
    
  //新增普通用户
    function doAdd(userId, groupId) {
        var url = "insertEmp";
        var param = {userId: userId, groupId: groupId};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                $("#user-tip").html("注册成功!").css("color", "#3d3");
            } else {
                $("#user-tip").html(result.msg).css("color", "#d33");
            }
        });
    }
    
  //删除
    function del(a) {
        if (confirm("您是否删除本条数据！！！")) {
            var url = "deleteById";
            var userId = $(a).parent().data("id");
            var param = {userId:userId};
            $.post(url, param, function (result) {
                if (result.code == 0) {
                	doFind();
                } else {
                    alert(result.msg);
                }
            });
        }
    }

    //批量删除
    function dellist(ind) {
        if (ind == 1) {
            var ids = $(".body-table-1 :checked");
        }
        if (ind == 2) {
            var ids = $(".body-table-2 :checked");
        }
        if (ids.length == 0) {
            alert("请选择一条数据");
        }

        var userIds;
        ids.each(function () {
            if (userIds == "") {
                userIds += $(this).val();
            } else {
                userIds += "," + $(this).val();
            }
        })
        var url = "deleteUsers";
        var param = {ids: userIds};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                ids.each(function () {
                    $(this).parent().parent().remove();
                })
            } else {
                alert(result.msg);
            }
        });
    }
    
    //查询用户
    function doFind() {
    	debugger;
        var url = "selectUser";
        var param = {pageCurrent:1};
        $.post(url,param, function (result) {
            if (result.code == 0)
                setTableBodyRows(result.data);
        });
        
    }
    
  //普通用户ID查找
    function doFindUserId() {
        var url = "selectUserByuserId";
        var userId = $("#userkw").val();
        if(!userId){
        	doFind();
        }else{
        	var param = {userId: userId};
            $.post(url, param, function (result) {
                if (result.code == 0) {
                    setTableBodyRows(result.data);
                }
            });
        }
    }
    
  //表格数据拼接
    function setTableBodyRows(result) {
        var tBody = $("#tbodyId");
        tBody.empty();
        for (var i in result) {
            var tr = $("<tr></tr>");
            tr.data("id", result[i].userId);
            var tds = "<th><input type='checkbox' name='checkId' value='" + result[i].id + "'/></th>" +
             "<th>" + result[i].userId + "</th>" +
             "<th>" + result[i].groupId + "</th>" +
             "<th class='click' onclick='del(this)'>删除</th>";
            tr.append(tds);
            tBody.append(tr);

        }
    }