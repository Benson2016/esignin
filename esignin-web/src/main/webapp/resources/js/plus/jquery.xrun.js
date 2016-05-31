;(function($){
	$.fn.extend({
		xRun:function(option){
			var defaults = {
				mLeftEv:"ul",//移动元素
				viewNum:4, // 可视元素个数
				scrollNum:1,// 每滚一次移动的元素个数，当scrollNum>viewNum，将强制等于viewNum
				btnL:".left",//左按钮
				btnR:".right",//右按钮
				speed:300,//移动速度
				autoRun:false,//是否自动滚动
				delayTime:3500,//自动滚动间隔时间
				loop:false,//是否循环滚动，若length%scrollNum!=0，loop强制为false
				fastClick:false,//连续点击是否有效，到最后，当loop判定为true，fastClick将强制为false
				isReturn:true,//最后是否可切回第一，第一是否可切到最后，当loop判定为true或autoRun为true，isReturn将强制为true，当isReturn为false时，按钮状态在首末两端将对应隐藏
				pageScroll:false,//是否生产切换按钮
				inStop:false,//当autoRun为true，控制鼠标进入是否停止自动切换
				mobileTouch:false//是否支持移动端滑动，设为true，同时需载入mobileTouch插件生效
			};
			option = $.extend(defaults,option);
			var $mLeftEv = $(this).find(option.mLeftEv),
				$li = $mLeftEv.children(),
				viewNum = option.viewNum,
				scrollNum = option.scrollNum>viewNum?viewNum:option.scrollNum,
				$btnL = $(this).find(option.btnL),
	 			$btnR = $(this).find(option.btnR),
				speed = option.speed,
				autoRun = option.autoRun,
				delayTime = option.delayTime,
				length = $li.length,		
				loop = option.loop&&length%scrollNum==0,
				fastClick = loop?false:option.fastClick,
				isReturn = (autoRun||loop)?true:option.isReturn,
				pageScroll = option.pageScroll,
				inStop = option.inStop,
				mobileTouch = option.mobileTouch,
				level = 0,
				fx = Math.ceil((viewNum - scrollNum)/scrollNum),
				maxLevel = Math.ceil(length / scrollNum)-fx,
				run,
				runBool = false,
				status = false;
			if(loop)
				maxLevel += fx;
			var n = length-maxLevel*scrollNum,
				m = viewNum - n;
			if(n+scrollNum<=viewNum)
				maxLevel--
				
			if (maxLevel <= 0) {
				$btnR.hide();
				$btnL.hide();
				return this;
			}
			
			function setRun(){
				clearInterval(run);
				run = setInterval(function(){method(add());},delayTime);
				runBool = true;
			}
			
			function cancelRun(){
				clearInterval(run);
				runBool = false;
			}
			
			function returnLevel(){
				this.maxLevel = maxLevel;
				this.level = level;
			}
			returnLevel();
			
			var liWidth = returnInt($li.css("width"))+returnInt($li.css("padding-left"))+returnInt($li.css("padding-right"))+returnInt($li.css("margin-left"))+returnInt($li.css("margin-right"))+returnInt($li.css("border-left-width"))+returnInt($li.css("border-right-width")),
				mLeftVal = liWidth*scrollNum,
				wrapDiv = '<div style="width:'+(liWidth*viewNum-returnInt($li.css("margin-left"))-returnInt($li.css("margin-right")))+'px;position:relative;overflow:hidden;"></div>'
			$mLeftEv.width(99999).wrap(wrapDiv);
			
			if(loop){
				var liTagName = $li[0].tagName;
				var $gtEv = $mLeftEv.children(liTagName+":gt("+(length-1-scrollNum)+")").clone(true);
				var $ltEv = $mLeftEv.children(liTagName+":lt("+(scrollNum*2+fx)+")").clone(true);
				$mLeftEv.prepend($gtEv).append($ltEv).css("margin-left",-mLeftVal);
			}
			
			if(pageScroll){
				var pageScrollH = "";
				for(var i=0;i<=maxLevel;i++){
					if(i==0)
					pageScrollH += "<span class=\"current\"></span>";
					else
					pageScrollH += "<span></span>";
				}
				pageScrollH = "<div class=\"pagescroll\">"+pageScrollH+"</div>";
				$(this).append(pageScrollH);
				pageScroll = $(this).find(".pagescroll");
				pageScroll.find("span").unbind("click.xRun").bind({
					"click.xRun": function() {
						if(!fastClick){
							if(status)
							return;
						}
						level = $(this).index();
						method();
						if(autoRun){
							setRun();
						}
					}
				});
			}
			
			if(!isReturn)
			returnDecide();
			
			function returnDecide(){
				if(level==0)
				$btnL.hide();
				else
				$btnL.show();
				if(level==maxLevel)
				$btnR.hide();
				else
				$btnR.show();
			}
			
			function add() {
				level = ++level > maxLevel ? 0 : level;
				return 1;
			}
			function minus() {
				level = --level == -1 ? maxLevel : level;
				return -1;
			}
			function returnInt(str){
				return parseInt("0"+str,10)
			}
			
			$btnR.unbind("click.xRun").bind({
				"click.xRun": function() {
					if(!fastClick){
						if(status)
						return;
					}
					method(add());
					if(autoRun){
						setRun();
					}
				}
			});
			$btnL.unbind("click.xRun").bind({
				"click.xRun": function() {
					if(!fastClick){
						if(status)
						return;
					}
					method(minus());
					if(autoRun){
						setRun();
					}
					
				}
			});
			
			if($.fn.mobileTouch&&mobileTouch){
			$(this).mobileTouch({
					isPageScroll:false,
					methods:[
						["left",1],
						["right",1]
					],
					leftCallBack:function(){
						$btnR.trigger("click");
					},
					rightCallBack:function(){
						$btnL.trigger("click");
					}
			});
			}
			
			
			function method(e) {
				status = true;
				returnLevel();
				if(pageScroll)
				pageScroll.find("span").eq(level).addClass("current").siblings().removeClass("current")
				if(option.beforeRun)
				option.beforeRun();
				var levels = level;
				if(loop)
				levels++;
				if(loop&&level==maxLevel&&e==-1){
					$mLeftEv.animate({ "margin-left": 0 }, speed ,function(){
						status = false;
						$mLeftEv.css("margin-left",-levels * mLeftVal);
						if(option.afterRun)
						option.afterRun();
					});
				}
				else if(loop&&level==0&&e==1){
					$mLeftEv.animate({ "margin-left": -(maxLevel+2) * mLeftVal }, speed ,function(){
						status = false;
						$mLeftEv.css("margin-left",-levels * mLeftVal);
						if(option.afterRun)
						option.afterRun();
					});
				}
				else{
					if(!isReturn)
					returnDecide();
					$mLeftEv.animate({ "margin-left": -levels * mLeftVal }, speed ,function(){
						status = false;
						if(option.afterRun)
						option.afterRun();
					});
				}
			}
			
			if(option.beforeStart){
				option.beforeStart();
			}
			
			if(autoRun){
				run = setInterval(function(){method(add());},option.delayTime);
				if(inStop)
				$(this).hover(
					function(){
						cancelRun();
					},
					function(){
						if(!runBool)
						setRun();
					}
				)
				
			}
			return this;
		}
	})
	
})(jQuery);