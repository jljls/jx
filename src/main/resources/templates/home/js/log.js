//日志搜索(有问题)
    function doFindLog() {
        var url = "selectLog";
        var param = {};
        param.startTime = $("#startTime").val();
        param.endTime = $("#endTime").val();
        $.post(url, param, function (result) {
            if (result.code == 0) {
                setLogBody(result.data);
            } else {
                alert(result.msg);
            }
        });
    }

    //删除日志
    function deleteLog(a) {
        var logid = $(a).prev().prev().prev().prev().prev().prev();
        var url = "deleteLog";
        var param = {id: logid};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                doFindLog();
            }
        });
    }

    //显示当前日志数
    function logNum() {
        var url = "logNum";
        $.getJSON(url, function (result) {
            if (result.code == 0) {
                $("#logNum").html("日志总数:" + result.data);
            }
        });
    }
    //设置日志表格
    function setLogBody(result) {
        var tBody = $("#logtBody");
        tBody.empty();
        for (var i in result) {
            var tr = $("<tr></tr>");
            tr.data("id", result[i].id);
            //2.2构建每行th对象(一行有多个)
            //var td0=$("<th></th>");
            //....
            //2.3在th对象内容填充具体数据
            //th0.append(result[id].id);
            //....
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