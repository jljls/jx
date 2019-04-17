

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
                veinnum();
                logNum();
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
                	veinnum();
                	logNum();
                } else {
                    alert(result.msg);
                }
            });
        }
    }

    //批量删除
    function dellist(ind) {
    	if (ind == 1) {
            var ids;
        	$("#tbodyId input[name='checkId']")//迭代input对象
        	.each(function (){//each函数用于迭代一个数组
        		if($(this).prop("checked")){//判断此input对象是否被选中
        			if(ids==""){
        				ids += $(this).val();
        			}else{
        				ids += ","+$(this).val();
        			}
        		}
        	})
        	if (ids.length == 0) {
        		return;
        	}
        	var url = "deleteUsers";
            var param = {ids:ids};
            $.post(url, param, function (result) {
                if (result.code == 0) {
                	doFind();
                	veinnum();
                	logNum();
                } else {
                    alert(result.msg);
                }
            });
        }
        if (ind == 2) {
        	debugger;
            var ids;
        	$("#userInfotBody input[name='checkId']")//迭代input对象
        	.each(function (){//each函数用于迭代一个数组
        		if($(this).prop("checked")){//判断此input对象是否被选中
        			if(ids==""){
        				ids += $(this).val();
        			}else{
        				ids += ","+$(this).val();
        			}
        		}
        	})
        	if (ids.length == 0) {
        		return;
        	}
        	var url = "deleteUInfoByIds";
            var param = {ids:ids};
            $.post(url, param, function (result) {
                if (result.code == 0) {
                	doFindeUserInfo()
                	selectUInfoNum();
                	logNum();
                } else {
                    alert(result.msg);
                }
            });
        }
    }
    
    function doQuery(){
    	//1.初始化当前页码数据
    	$("#fy-1").data("pageCurrent",1);
    	//2.根据条件查询数据
    	doFind();
    }
    
    //查询用户
    function doFind() {
        var url = "selectUser";
        var userId = $("#userkw").val();
        var pageCurrent = $("#fy-1").data("pageCurrent");
    	if(!pageCurrent){
    		pageCurrent = 1;
    	}
        var param = {pageCurrent:pageCurrent};
        param.userId = userId;
        $.post(url,param, function (result) {
            if (result.code == 0)
                setTableBodyRows(result.data.list);
            	setPagination("#fy-1",result.data.pageObject);
        });
    }
    
  //表格数据拼接
    function setTableBodyRows(result) {
        var tBody = $("#tbodyId");
        tBody.empty();
        for (var i in result) {
            var tr = $("<tr></tr>");
            tr.data("id", result[i].userId);
            var tds = "<th><input type='checkbox' name='checkId' value='" + result[i].userId + "'/></th>" +
             "<th>" + result[i].userId + "</th>" +
             "<th>" + result[i].groupId + "</th>" +
             "<th class='click' onclick='del(this)'>删除</th>";
            tr.append(tds);
            tBody.append(tr);

        }
    }