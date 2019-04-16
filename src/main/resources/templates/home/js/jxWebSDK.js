var BASE64_MARKER = ';base64,';

function jxBase64ToUint8(b64Data) {
  var raw = atob(b64Data);
  var rawLength = raw.length;
  var array = new Uint8Array(new ArrayBuffer(rawLength));

  for(i = 0; i < rawLength; i++) {
    array[i] = raw.charCodeAt(i);
  }
  return array;
}

//检测插件是否启动
function jxIsServerExsit() {
	var res = -101;
	$.ajax({
		url: "http://127.0.0.1:65533/",
		type: "post",
		dataType: "text",
		async: false,
		data: 	{command:"jxIsServerExsit"},
		success: function(data, status){res = 0;}
	});
	
	return res;
}

//检测设备连接
function jxIsFVDConnected() {
	var res = -101;
		$.ajax({
		url: "http://127.0.0.1:65533/",
		type: "post",
		dataType: "text",
		async: false,
		data: 	{command:"jxIsFVDConnected"},
		success: function(data, status){res = parseInt(data.split(':')[1]);}
	});
	return res;
}


//检测手指
function jxIsFingerDetected() {
	var res = -101;
	$.ajax({
		url: "http://127.0.0.1:65533/",
		type: "post",
		dataType: "text",
		async: false,
		data: 	{command:"jxIsFingerDetected"},
		success: function(data, status){res = parseInt(data.split(':')[1]);}
	});
	return res;
}


var jxCapRes;
var jxCapFeat;
var jxInCapProcess;
//采集指静脉特征
function jxCapVeinFeat() {
	
	jxCapRes = 0;
	jxCapFeat = null;
	jxInCapProcess = true;
	
	$.ajax({
		url: "http://127.0.0.1:65533/",
		type: "post",
		dataType: "text",
		async: true,
		data: 	{command:"jxCapVeinFeat"},
		success: function(data, status){
			var index = data.indexOf('IsVeinImgOK');
			var tmp = data.substring(0, index);
			jxCapFeat = data.substring(index+'IsVeinImgOK'.length);
			jxCapRes = parseInt(tmp.split(':')[1]);
			jxInCapProcess = false;},
		error:function(result){jxCapRes = -101; jxInCapProcess = false;}
	});
}


//比对是否是同一个手指
function jxVerifyVeinFeat(feat1, feat2) {
	var res = -101;
	$.ajax({
		url: "http://127.0.0.1:65533/",
		type: "post",
		dataType: "text",
		async: false,
		data: 	{command:"jxVerifyVeinFeat", feat1: feat1, feat2: feat2},
		success: function(data, status){res = parseInt(data.split(':')[1]);}
	});
	return res;
	
}
