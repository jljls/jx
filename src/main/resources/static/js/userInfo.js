//修改管理员密码
function upDatepws() {
    var url = "upDatapws";
    var pws = $("#new-pw").val();
    var pwsa = $("#new-pw-agin").val();
    if(pws == pwsa){
	    if (pws && pws) {
	        var param = {pws:pws};
	        $.post(url, param, function (result) {
	            if (result.code == 0) {
	                $("#password-edit").html("密码修改成功!").css("color", "#3d3");
	                $("#new-pw").val("");
	                $("#new-pw-agin").val("");
	            } else {
	                $("#password-edit").html(result.msg);
	            }
	        });
	    }
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
            $("#admin-tip").html(result.msg).css("color", "#d33");
        }
    });
}



function doQueryUserInfo(){
	//1.初始化当前页码数据
	$("#fy-2").data("pageCurrent",1);
	//2.根据条件查询数据
	doFindeUserInfo();
}

//管理员搜索
function doFindeUserInfo() {
	debugger;
    var url = "selectUInfoAll";
    var userId = $("#kw").val();
    var pageCurrent = $("#fy-2").data("pageCurrent");
	if(!pageCurrent){
		pageCurrent = 1;
	}
    var param = {userId:userId}
    param.pageCurrent = pageCurrent;
    $.post(url,param,function (result) {
        if (result.code == 0) {
            setUserInfo(result.data.list);
            setPagination("#fy-2",result.data.pageObject);
        } else {
            alert(result.msg);
        }
    });
}
//设置管理员表格
function setUserInfo(result) {
    var tBody = $("#userInfotBody");
    tBody.empty();
    for (var i in result) {
        var tr = $("<tr></tr>");
        tr.data("id", result[i].userId);
        var tds = "<th><input type='checkbox' name='checkId' value='"+result[i].userId+"'/></th>"+
         "<th>"+result[i].userId+"</th>"+
         "<th class='click' onclick='dell(this)'>删除</th>";
        //2.4将th添加到tr对象中(一行要放多个)
        tr.append(tds);
        //2.5将tr追加到tbody中
        tBody.append(tr);
    }
}

//删除
function dell(a) {
	debugger;
    if (confirm("您是否删除本条数据！！！")) {
    	debugger;
        var url = "deleteUInfoById";
        var userId = $(a).parent().data("id");
        var param = {userId:userId};
        $.post(url, param, function (result) {
            if (result.code == 0) {
            	doFindeUserInfo();
            } else {
                alert(result.msg);
            }
        });
    }
}