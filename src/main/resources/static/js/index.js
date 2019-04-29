
//定义一个函数,通过此函数实现页面的跳转
    function jumpToPage(a) {
    	//获得点击对象上class属性对应的值,根据此值
        //判定具体点击的是哪个对象(例如上一页,下一页)
        var clazz = $(a).attr("class");
    	var id = $(a).parent().attr("id");
    	if(id=="fy-1"){
            //获得fy-1对象上绑定的pageCurrent对应的值
            var pageCurrent = $('#fy-1').data("pageCurrent");
            //获得fy-1对象上绑定的pageCount对应的值
            var pageCount = $('#fy-1').data("pageCount")
            //根据class属性的值判断点击的是否是上一页
            if (clazz == 'pre' && pageCurrent > 1) {
                pageCurrent--;
            }
            //判断点击的是否是下一页
            if (clazz == "next" && pageCurrent < pageCount) {
                pageCurrent++;
            }
            //判断点击的对象是否是首页
            if (clazz == "first") {
                pageCurrent = 1;
            }
            //判定点击的对象是否是尾页
            if (clazz == "last") {
                pageCurrent = pageCount;
            }
            //重写绑定pageCurrent的值,
            $('#fy-1').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFind();
    	}
		if(id=="fy-2"){
            //获得fy-2对象上绑定的pageCurrent对应的值
            var pageCurrent = $('#fy-2').data("pageCurrent");
            //获得fy-2对象上绑定的pageCount对应的值
            var pageCount = $('#fy-2').data("pageCount")
            //根据class属性的值判断点击的是否是上一页
            if (clazz == 'pre' && pageCurrent > 1) {
                pageCurrent--;
            }
            //判断点击的是否是下一页
            if (clazz == "next" && pageCurrent < pageCount) {
                pageCurrent++;
            }
            //判断点击的对象是否是首页
            if (clazz == "first") {
                pageCurrent = 1;
            }
            //判定点击的对象是否是尾页
            if (clazz == "last") {
                pageCurrent = pageCount;
            }
            //重写绑定pageCurrent的值,
            $('#fy-2').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFindeUserInfo();		
		}
		if(id=="fy-3"){
            //获得fy-3对象上绑定的pageCurrent对应的值
            var pageCurrent = $('#fy-3').data("pageCurrent");
            //获得fy-3对象上绑定的pageCount对应的值
            var pageCount = $('#fy-3').data("pageCount")
            //根据class属性的值判断点击的是否是上一页
            if (clazz == 'pre' && pageCurrent > 1) {
                pageCurrent--;
            }
            //判断点击的是否是下一页
            if (clazz == "next" && pageCurrent < pageCount) {
                pageCurrent++;
            }
            //判断点击的对象是否是首页
            if (clazz == "first") {
                pageCurrent = 1;
            }
            //判定点击的对象是否是尾页
            if (clazz == "last") {
                pageCurrent = pageCount;
            }
            //重写绑定pageCurrent的值,
            $('#fy-3').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFindLog();
		}
    	
    }
    
    
    //定义一个函数,通过此函数实现具体页面的跳转
    function jumpToPagedetail(a) {
    	debugger;
        //判定具体点击的是哪个对象(例如上一页,下一页)
    	var id = $(a).parent().attr("id");
    	if(id=="fy-1"){
    		//获得用户输入的值，设置为当前页
    		var pageCurrent = $("#page1").val();
            var pageCount = $('#fy-1').data("pageCount")
            //判断当前页是否大于总页数,大于的话，则当前页等于总页数
            if(pageCurrent > pageCount){
            	pageCurrent = pageCount;
            }
            //判断当前页是否小于等于1,小于的话，则当前页等于1
            if(pageCurrent <= 1){
            	pageCurrent = 1;
            }
            
            //重写绑定pageCurrent的值,
            $('#fy-1').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFind();
    	}
    	if(id=="fy-2"){
    		//获得用户输入的值，设置为当前页
    		var pageCurrent = $("#page2").val();
            var pageCount = $('#fy-2').data("pageCount")
            //判断当前页是否大于总页数,大于的话，则当前页等于总页数
            if(pageCurrent > pageCount){
            	pageCurrent = pageCount;
            }
            //判断当前页是否小于等于1,小于的话，则当前页等于1
            if(pageCurrent <= 1){
            	pageCurrent = 1;
            }
            //重写绑定pageCurrent的值,
            $('#fy-2').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFindeUserInfo();
    	}
    	if(id=="fy-3"){
    		//获得用户输入的值，设置为当前页
    		var pageCurrent = $("#page3").val();
    		
            var pageCount = $('#fy-3').data("pageCount")
            //判断当前页是否大于总页数,大于的话，则当前页等于总页数
            if(pageCurrent > pageCount){
            	pageCurrent = pageCount;
            }
            //判断当前页是否小于等于1,小于的话，则当前页等于1
            if(pageCurrent <= 1){
            	pageCurrent = 1;
            }
            //重写绑定pageCurrent的值,
            $('#fy-3').data("pageCurrent", pageCurrent);
            //重新执行查询操作(根据pageCurrent的值)
            doFindLog();
    	}
    }
    
    
    
    //添加指静脉
	  var regTimer;
	  var regNum;
	  var feat_list;
	  var regState;
	  var ERRMSG = new Array("采集成功", "设备未连接", "设备打开出错", "用户操作超时", "输入参数有误", "手指检测出错", "图像采集出错","手指过早移开", "图像质量低", "静脉特征损坏", "其他错误");
	  
	  
	  function regVein()
	  
	  {
		if(regState == 0)
		{
			debugger;
			document.getElementById("msg").innerHTML = "第" + (regNum + 1) + "次采集，请放入手指";
			jxCapVeinFeat();
			regState = 1;
			return;
		}
		else if(regState == 1)
		{
			if(jxInCapProcess == true)
				return;
			if(jxCapRes == 0)
			{
				document.getElementById("msg").innerHTML = "第" + (regNum + 1) + "次采集完成，请移开手指";
				feat_list.push(jxCapFeat);
				regNum = regNum + 1;
				if(regNum == 3)
					regState = 3;
				else
					regState = 2;
				return;
			}
			else if(jxCapRes == -101)
			{
			$("#msg").html( "指静脉本地服务已断开").css("color","#d33");
				regError(jxCapRes);
				return;
			}
			else
			{
		document.getElementById("msg").innerHTML = ERRMSG[-jxCapRes];
				regError(jxCapRes);
				return;
			}
		}
		else if(regState == 2)
		{
			res = jxIsFingerDetected();
			if(res == 1)
			{
				return;
			}
			else if(res == 0)
			{
				regState = 0;
				return;
			}
			else if(res == -101)
			{
				$("#msg").html("指静脉本地服务已断开").css("color","#d33");
				regError(res);
				return;
			}
			else
			{
				document.getElementById("msg").innerHTML = ERRMSG[-res];
				regError(res);
				return;
			}
		}
		else if(regState == 3)
		{
			/*
			if(jxVerifyVeinFeat(feat_list[0], feat_list[1]) == 1)
				document.getElementById("spanRegState").innerHTML = "指静脉注册成功";
			else
				document.getElementById("spanRegState").innerHTML = "指静脉不一致";
			*/
			
			window.clearInterval(regTimer);
			if(createUser()){
				$("#msg").html( "指静脉注册成功").css("color", "#3d3");
				search();
				people();
			}else{
				$("#msg").html( "指静脉注册失败").css("color", "#d33");
 			}
 			return;
 		}
 	  }
 	  
 	  //注册人员
 	  var createUser = function(){
 		  debugger;
 		  var flag = false;
 		  var userId = document.getElementById("uid").value;
 		  var param = {
 				  userId:userId,
 				  veinFeats:feat_list.join(",")
 		  }
 		  var url="registe1";
 		  $.ajax({
 				url : url,
 				data : param,
 				dataType : 'json',
 				contentType: "application/json;charset=utf-8",
 				async : false,
 				success : function(result) {
 					debugger;
 					if(result.code!=0){
 						alert(result.msg);
 						flag = false;
 					}else{
 						
 						flag = true;
 					}
 				}
 				
 			});
 		  return flag;
 		  
 	  }
 	  
       function regError(val)
       {
         window.clearInterval(regTimer);
       }

       function startReg()
       {
    	   
    	   
    	   
 		regNum = 0;
 		regState = 0;
 		feat_list = [];
 		regTimer = setInterval("regVein()", 10);
       } 
 	  var capTimer;
       var capState; // 0: no task  1:wait finger  2:capping 3:wait leave
       function capImg()
       {
         if(capState == 0)
 		{
 			document.getElementById("msg").innerHTML = "请放入手指";
 			jxCapVeinFeat();
 			capState = 1;
 			return;
 		}
 		else if(capState == 1)
 		{
 			if(jxInCapProcess == true){
 				return;
 			}			
 			if(jxCapRes == 0){
 				var res = false;
 				var i=0;
 				while(true){
 					if(i==2){
 						break;
 					}
 					if(jxVerifyVeinFeat(jxCapFeat, feat_list[i]) == 1){
 						res = true;
 					}
 					i++;
 				}
 					
 						
 				
 				
 				if(res == false)
 					$("#msg").html("指静脉验证失败").css("color","#d33");
 				else
 					$("#msg").html( "指静脉验证通过").css("color","#3d3");
 				window.clearInterval(capTimer);
 				return;
 			}
 			else if(jxCapRes == -101)
 			{
 				$("#msg").html( "指静脉本地服务已断开").css("color","#d33");
 				capError(jxCapRes);
 				return;
 			}
 			else
 			{
 				$("#msg").html( ERRMSG[-jxCapRes]).css("color","#d33");
 				capError(jxCapRes);
 				return;
 			}
 		}
       }

       function capError(val)
       {
         window.clearInterval(capTimer);
       }

       function startAuth()
       {
         capState = 0;
         capTimer = setInterval("capImg()", 10);
       }

       function stopAuth()
       {
         window.clearInterval(capTimer);
         //document.getElementById("spanCapState").innerHTML = "";
       }