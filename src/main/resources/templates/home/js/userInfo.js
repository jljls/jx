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