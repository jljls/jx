//修改管理员密码
function upDatepws() {
    var url = "upDatapws";
    var pws = $("#new-pw").val();
    var pwsa = $("#new-pw-agin").val();
    if (!pws && !pws && pws == pwsa) {
        var param = {pws: pws};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                $("#password-edit").html("密码修改成功!").css("color", "#3d3");
            } else {
                $("#password-edit").html(result.msg);
            }
        });
    }
}

//添加管理员
function uifadd() {
    // $("#errmsg").html("");
    var userId = $("#new-gu").val();
    var password = $("#new-gg").val();
    if (!userId) {
        $("#admin-tip").html("管理员账号不能为空");
        return;
    }
    if (!password) {
        $("#admin-tip").html("管理员密码不能为空");
        return;
    }
    var url = "insertUserInfo";
    var param = {userId: userId, name: "普通管理员", password: password};

    $.post(url, param, function (result) {
        if (result.code == 0) {
            $("#admin-tip").html("管理员添加成功").css("color", "#3d3");
        } else {
            $("#admin-tip").html(result.msg);
        }
    });
}

//查询当前管理员总数
function selectUInfoNum() {
    var url = "selectUInfoNum";
    $.getJSON(url, function (result) {
        if (result.code == 0) {
            $("#sysNum").html("管理员总数:" + result.data);
        }
    });
}

//管理员搜索
function doFindeUserInfo() {
    var url = "selectUInfoByUserId";
    var userId = $("#kw").val();
    if (!userId) {
        $.post(url, param, function (result) {
            if (result.code == 0) {
                setUserInfo(result.data);
            } else {
                alert(result.msg);
            }
        });
    }
}
//设置管理员表格
function setUserInfo(result) {
    var tBody = $("#userInfotBody");
    tBody.empty();
    for (var i in result) {
        var tr = $("<tr></tr>");
        tr.data("id", result[i].id);
        var tds = "";
        /* "<th><input type='checkbox' name='checkId' value='"+result[i].id+"'/></th>"+
         "<th>"+result[i].userId+"</th>"+
         "<th>"+result[i].groupId+"</th>"+
         "<th class='lick' onclick='del(this)'>删除</th>"; */
        //2.4将th添加到tr对象中(一行要放多个)
        tr.append(tds);
        //2.5将tr追加到tbody中
        tBody.append(tr);
    }
}