//显示已注册用户 已注册手指
    function veinnum() {
        var url = "selectUAndV";
        $.getJSON(url, function (result) {
            $("#veinnum").html(result.data);
        });
    }

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
                $("#user-tip").html(result.msg);
            }
        });
    }
    
  //删除
    function del(a) {
        if (confirm("您是否删除本条数据！！！")) {
            var url = "deleteById";
            var param = {};
            $.post(url, param, function (result) {
                if (result.code == 0) {
                    $(a).parent().remove();
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
        var url = "selectUser";
        $.post(url, function (result) {
            if (result.code == 0)
                setTableBodyRows(result.data);
        });

    }
    
  //普通用户ID查找
    function doFindUserId() {
        var url = "selectUserByuserId";
        var userId = $("#userkw").val();
        var param = {userId: userId};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                setTableBodyRows(result.data);
            }
        });
    }
    
  //表格数据拼接
    function setTableBodyRows(result) {
        var tBody = $("#tbodyId");
        tBody.empty();
        for (var i in result) {
            var tr = $("<tr></tr>");
            tr.data("id", result[i].userId);
            //2.2构建每行th对象(一行有多个)
            //var td0=$("<th></th>");
            //....
            //2.3在th对象内容填充具体数据
            //th0.append(result[id].id);
            //....
            var tds = "<th><input type='checkbox' name='checkId' value='" + result[i].id + "'/></th>" +
             "<th>" + result[i].userId + "</th>" +
             "<th>" + result[i].groupId + "</th>" +
             "<th class='lick' onclick='del(this)'>删除</th>";
            //2.4将th添加到tr对象中(一行要放多个)
            tr.append(tds);
            //2.5将tr追加到tbody中
            tBody.append(tr);

        }
    }