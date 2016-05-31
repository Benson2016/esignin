/*
* @appear - jQuery Plugin
* @version 20141104
* @author Chvin
* @website tcwen.com
*/
;(function($){
	$.fn.extend({
		appear:function(option){
			var defaults ={
				normalDelay:200,//基本延迟
				speed:400,//渐变时间
				offsetX:-100,//默认x偏移量
				offsetY:0,//默认y偏移量
				callback:null//出场后的回调函数
			},
				o = $.extend(defaults,option);
			var delays = [];
			
			$(this).each(function(i, e){ //获得最大延迟元素以及判断是否设置IE过滤
				var delay = $(e).attr("data-delay")?parseInt($(e).attr("data-delay")):0;
				delays.push(delay);
			});		
			
			var maxDelayKey = getAyMax(delays);//最大延迟元素序列
						
			$(this).each(function(i, e) {
				/*
					请将目标元素设置CSS	{opacity:0;filter:alpha(opacity=0)}，以便页面无闪烁
					为兼容PNG，和微软雅黑在IE7 IE8的锯齿，请在元素上设置属性 data-notOpacityForIE="7|8"
				*/
				var static = $(e).css("position")=="static",//判断是否为static
					x = $(e).css("right")=="auto"?"left":"right",//设渐变属性
					y = $(e).css("bottom")=="auto"?"top":"bottom",//设渐变属性
					delay = $(e).attr("data-delay")?parseInt($(e).attr("data-delay")):0,//获取延迟时间
					offsetX = $(e).attr("data-offset-x")?parseInt($(e).attr("data-offset-x")):o.offsetX,//设置水平偏移	
					offsetY = $(e).attr("data-offset-y")?parseInt($(e).attr("data-offset-y")):o.offsetY,//设置竖直偏移	
					orgX = $(e).css(x)=="auto"?0:parseInt($(e).css(x)),//获得水平原始值
					orgY = $(e).css(y)=="auto"?0:parseInt($(e).css(y)),//获得竖直原始值
					ov = x=="right"?{"right":orgX}:{"left":orgX};//水平目标

				$.extend(ov,y=="top"?{"top":orgY}:{"bottom":orgY});//竖直目标
				
				var notOpacityForIE = [];//获得数组如[6,7]代表IE6 IE7
				notOpacityForIE = $(e).attr("data-notOpacityForIE")?$(e).attr("data-notOpacityForIE").split("|"):notOpacityForIE;
				if(!is_in_array(notOpacityForIE,ietest()))//检测浏览器和参数是否一致
				$.extend(ov,{"opacity":1});//如果不是IE，合并
				
				if(static)//如果position初始值为static，为使滑动生效设置position为relative
				$(e).css("position","relative");
				
				setTimeout(function(){
					if(!is_in_array(notOpacityForIE,ietest()))//检测浏览器和参数是否一致
					$(e).css("filter","none");//IE不使用滤镜
					
					$(e).css(x,orgX+offsetX).css(y,orgY+offsetY).animate(ov,o.speed,cBack);
				},delay+o.normalDelay);
				
				
				function cBack(){
					if(static)	//还原position
					$(e).css("position","static");
					if(i==maxDelayKey&&o.callback)//判断是否为最大延迟以及是否有回调函数
					o.callback();
				}
            });			

			return this;

			function getAyMax(arr){//求数组的最大值的键值
				var len = arr.length,
					maxNum = 0;
				
				for(var i=0;i<len;i++){
					if(arr[maxNum]<arr[i])
					maxNum = i;					
				}
				return maxNum;
			}

			function is_in_array(arr,ele){//检测数组是否包含元素
				var contains = false;
				for(var i=0,len=arr.length;i<len;i++){
					if(ele==arr[i]){
						contains = true;
						break;	
					}
				}
				return contains;
			}
		
			function ietest(){ //如果浏览器为IE，返回整数版本号，客户端如果为非IE，函数返回0
				var ver = 0;
				if(/MSIE ([^;]+)/.test(navigator.userAgent))
					ver = parseFloat(RegExp["$1"]);
				return ver;
			}
		
		}		
	})	
})(jQuery);