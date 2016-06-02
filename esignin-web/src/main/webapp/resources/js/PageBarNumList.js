﻿var PageBarNumList = {
    /**
     * 分页
     * @param {int} pageno 当前页
     * @param {int} pagecount 总页数
     * @param {int} nocount 分页条显示的页码数量
     * @param {String} func 分页方法
     * @param {boolean} control 是否显示每页显示多少条
     * @param {int} pagesize 每页显示条数
     * 自动生成的页码在点击后会自动调用func方法,并将当前页码作为第一个参数传入该方法,getPageBar第5个参数开始,将被作为func的第2个参数开始传入;func的第1个参数是用户所点击的pageNo
     */
    getPageBar : function(pageno, pagecount, nocount, func, pagesize, control) {
        var pagebar = [];
        pagebar.push('<p class="pagebar_numlist">');
        if(control){
        	pagebar.push('<span><em>每页显示</em><input class="_showPageNum" type="text" value="' + (pagesize || 8) + '" /><em>条</em><button class="_showPageNumBtn" onclick="PageBarNumList.__showPageCount(' + pagecount*(pagesize || 8) + ',' + func + ',this)">确定</button></span>');
        }
        pagebar.push('<a class="pagebar_numlist_first" title="首页" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, 1, arguments)) + '">&nbsp;首页&nbsp;</a>');
        pagebar.push('<a title="上一页" class="g-pageprev" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, Math.max(pageno - 1, 1), arguments)) + '">&nbsp;</a>');

        var leftno = 1;
        if(pageno <= nocount) {
            leftno = 1;
        } else if(pageno % nocount == 0) {
            leftno = pageno - nocount + 1;
        } else {
            leftno = ~~(pageno / nocount) * nocount + 1;
        }

        for(var i = leftno, len = leftno + nocount; i < len; i++) {

            if(i <= pagecount) {
                if(pageno == i) {
                    pagebar.push('<span class="pagebar_numlist_current">' + i + '</span>');
                } else {
                    pagebar.push('<a title="第' + i + '页" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, i, arguments)) + '">' + i + '</a>');
                }
            } else {
                break;
            }

        }

        if(len <= pagecount) {
            var stepno = Math.min(len + nocount - 1, pagecount);
            pagebar.push('<a title="第' + stepno + '页" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, stepno, arguments)) + '">...</a>');
            pagebar.push('<a title="第' + stepno + '页" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, stepno, arguments)) + '">' + stepno + '</a>');
        }

        pagebar.push('<a title="下一页" class="g-pagenext" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, Math.min(pageno + 1, pagecount), arguments)) + '">&nbsp;</a>');
        pagebar.push('<a class="pagebar_numlist_last" title="尾页" href="javascript:void(0)" onclick="' + this.__getPageBarFuncString.apply(this, this.__getPageBarFuncParams(func, pagecount, arguments)) + '">&nbsp;尾页&nbsp;</a>');
        pagebar.push('</p>');
        return pagebar.join('');
    },
    /**
     * @private
     * 获取分页方法参数
     * @param {Object} func
     * @param {Object} no
     * @param {Object} arguments
     */
    __getPageBarFuncParams : function(func, no, args) {
        var params = [func, no];
        if(args.length > 4) {
            for(var i = 4, len = args.length; i < len; i++) {
                params.push(args[i]);
            }
        }
		//console.log(params);
        return params;
    },
    /**
     * @private
     * 获取分页方法序列化字符串
     * @param {String} func
     */
    __getPageBarFuncString : function(func) {
        var funcStr = [func + '('];
        if(arguments.length > 1) {
            var isString = false;

            for(var i = 1, len = arguments.length; i < len; i++) {
                isString = ('string' === typeof arguments[i]);
                if(isString) {
                    funcStr.push('\'');
                }
                funcStr.push(arguments[i]);
                if(isString) {
                    funcStr.push('\'');
                }
                if(i < len - 1) {
                    funcStr.push(',');
                }
            }
        }
        funcStr.push(')');
        //console.log(funcStr);
        return funcStr.join('');
    },
    __showPageCount: function(max,func,el){
    	var val = '';
    	if(el.previousSibling.previousSibling.tagName.toLowerCase() == 'input'){
    		val = el.previousSibling.previousSibling.value;
    		if(!val.length){
				alert('请输入显示页数');
			}else if(!val.match(/^[123456789]\d*$/g)){
				alert('请输入非零数字');
			}else if(val > max){
                alert('每页最多显示' + max + '条');
            }else{
				func(1,val);
			}
    	}
    	
    }
}