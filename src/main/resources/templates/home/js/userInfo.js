//修改密码
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