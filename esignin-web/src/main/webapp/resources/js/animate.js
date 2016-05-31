/*
 *	http://tcwen.com/
 *	update 2014/11/14
 *
 *	Demo:
 	var o = animate({
 		obj:document.getElementsByTagName("img")[0],
 		change:[
 			{
 				css:"webkitTransform",
 				start:"rotate(0deg) scale(1)",
 				end:"rotate(3600deg) scale(1.5)"
 			},
 			{
 				css:"backgroundColor",
 				int:true,
 				start:"rgb(0,0,0)",
 				end:"rgb(105,17,115)"
 			}
 		],
 		time:3000,
 		easing:function(x, t, b, c, d){
 			return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;//easeInOutSine
 		},
 		callback:function(){
 			console.log("done");
 		}
 	});
 	o.stop();
 */

function animate(o){
	if(!o.obj) return;
	var count = 0,//改变次数
		numReg = /-?\d+(?:\.\d+)?/g,//提取数字
		starts = [],//开始状态值组
		ends = [],//目标状态值组
		strArr = [],//字符分组
		FPS = o.FPS || 40,//帧率
		times = o.time || 0,//时间
		level = Math.ceil(times/parseInt(1000/FPS)),//所需改变次数
		level = level==0?1:level,
		timeout = null,//延迟变量
		prevStop = o.stop === undefined ? true : o.stop,//是否清除原有动画
		easing = o.easing || function (x, t, b, c, d) {// t：time, b:begin, c:change, d:duration
			return -c *(t/=d)*(t-2) + b;
			// 参考 http://gsgd.co.uk/sandbox/jquery/easing/
			// 默认 easeOutQuad
			// return c * t/d + b;//匀速方法
		};
	

	//遍历保存 starts,ends,strArr 为后提供服务
	for(var i in o.change){
		if(!o.change.hasOwnProperty(i)) continue;
		var css = o.change[i];
		css.start = css.start.toString();
		css.end = css.end.toString();
		getFloatAr(css.start);//兼容IE
		starts[i] = getFloatAr(css.start);
		ends[i] = getFloatAr(css.end);
		strArr[i] = css.start.split(numReg);
		if(css.start.search(numReg) == 0 && strArr[i][0] !== ""){//兼容IE，当a = str.split(n),返回值若a[0]==""，IE将忽略
			strArr[i].unshift("");
		}
		if(strArr[i][strArr[i].length-1] === "")
			strArr[i].pop();
	}

	var rt = {},
		stop = false;
	rt.stop = function(){
		if(count != level && level != 0){
			stop = true;
			clearTimeout(timeout);
		}
	}
	rt.play = function(){
		if(stop == false)
			return;
		if(count != level && level != 0){
			stop = false;
			method();
		}
	}

	if(o.obj.ANIMATE){//储存方法到dom元素，当dom元素存在animate方法未完成时清除动画序列
		if(typeof o.obj.ANIMATE.stop == "function" && prevStop)
			o.obj.ANIMATE.stop();
	}
	o.obj.ANIMATE = rt;
	method();

	return rt;//返回stop,play外部方法

	function merge(strs,nums){//交叉合并数组，返回样式字符串
		var style = "";
		for(var i in strs){
			if(!strs.hasOwnProperty(i)) continue;
			style += strs[i];
			if(nums[i] !== undefined)
			style += nums[i];
		}
		return style;
	}
	

	function getFloatAr(str){//取出字符串数字，返回数组
		var arr = [],num;
		while(num = numReg.exec(str)){
			arr.push(parseFloat(num[0]));
		}
		return arr;
	}

	function r(){
		count++;//统计次数增加
		var time = count*parseInt(1000/FPS);
		for(var i in o.change){//遍历设值
			if(!o.change.hasOwnProperty(i)) continue;
			var live = [];
			for(var n in starts[i]){
				if(!starts[i].hasOwnProperty(n)) continue;
				if(ends[i][n]-starts[i][n] != 0){
					if(times == 0){
						live.push(ends[i][n])
					}
					else{//easing times不能为0
						live.push(easing(null,time,starts[i][n],ends[i][n]-starts[i][n],times));
					}
				}
				else
					live.push(starts[i][n]);
			}
			if(o.change[i].int === true){//转为整数
				for(var m in live){
					if(!live.hasOwnProperty(m)) continue;
					live[m] = Math.round(live[m]);
				}
			}

			o.obj.style[o.change[i].css] = merge(strArr[i],live);
			if(typeof o.change[i].speed == "function"){//速度输出
				if(!o.change[i].prev)
					o.change[i].prev = starts[i];
				o.change[i].speed(subtraction(o.change[i].prev,live),count);
				o.change[i].prev = live;
			}
		}
	}


	function subtraction(arr1,arr2){//计算数组元素差，返回新数组
		var arr = [];
		for(var i=0,len=arr1.length;i<len;i++){
			arr.push(arr2[i]-arr1[i]);
		}
		return arr;
	}


	function method(){
		if(stop == true)
			return;
		if(count != level-1 && level != 0){//如果未达到最后一次
			r();
			timeout = setTimeout(function(){//回调
				method();
			},parseInt(1000/FPS));
		}else{//最后一次
			timeout = setTimeout(function(){
				r();
				try{//删除dom上储存的方法
					delete o.obj.ANIMATE;
				}catch(e){
					o.obj.ANIMATE = undefined;
				}
				if(o.callback)//函数回调
					o.callback(o.obj);
			},times - (level-1)*parseInt(1000/FPS));
		}
	}
}