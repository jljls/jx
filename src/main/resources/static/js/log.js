//点击菜单执行的查询
function doQueryLog(){
    	//1.初始化当前页码数据
    	$("#fy-3").data("pageCurrent",1);
    	//2.根据条件查询数据
    	doFindLog();
    }

//日志搜索
    function doFindLog() {
    	debugger;
        var url = "selectLog";
        var param = {};
        var pageCurrent = $("#fy-3").data("pageCurrent");
    	if(!pageCurrent){
    		pageCurrent = 1;
    	}
    	var starYear =  $("#star-year").val();
    	var starMouth = $("#star-mouth").val();
    	var starDay = $("#star-day").val()
    	if(starYear && starMouth && starDay){
    		var startTime = starYear+"/"+starMouth+"/"+starDay;
    		param.startTime = startTime;
    	}
		var endYear =  $("#end-year").val();
    	var endMouth = $("#end-mouth").val();
    	var endDay = $("#end-day").val();
    	if(endYear && endMouth && endDay){
    		var endTime = endYear+"/"+endMouth+"/"+endDay;
    		param.endTime = endTime;
    	}
    	param.pageCurrent = pageCurrent;
        $.post(url, param, function (result) {
            if (result.code == 0) {
                setLogBody(result.data.list);
                setPagination("#fy-3",result.data.pageObject);
                var data=new Date();
            	$("#star-year,#end-year").val(data.getFullYear());
            	$("#star-mouth,#end-mouth").val(data.getMonth());
            	$("#star-day,#end-day").val(data.getDate());
            } else {
                alert(result.msg);
                var data=new Date();
            	$("#star-year,#end-year").val(data.getFullYear());
            	$("#star-mouth,#end-mouth").val(data.getMonth());
            	$("#star-day,#end-day").val(data.getDate());
            }
        });
    }

    //删除日志
    function delll(a) {
    	var logid = $(a).parent().data("id");
        var url = "deleteLogById";
        var param = {id: logid};
        $.post(url, param, function (result) {
            if (result.code == 0) {
                doFindLog();
                logNum();
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
            
            var tds =  "<th>"+result[i].createTime+"</th>"+
             "<th>"+result[i].time+"</th>"+
             "<th>"+result[i].userId+"</th>"+
             "<th>"+result[i].type+"</th>"+
             "<th>"+result[i].logContent+"</th>"+
             "<th class='click' onclick='delll(this)'>删除</th>";
            //2.4将th添加到tr对象中(一行要放多个)
            tr.append(tds);
            //2.5将tr追加到tbody中
            tBody.append(tr);

        }
    }