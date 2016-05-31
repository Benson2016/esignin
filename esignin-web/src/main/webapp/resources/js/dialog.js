/**
 *
 * @param msg
 * @param title
 * @param btn
 */
function showMsg (msg,title,btn) {
    hyxt.dialog.show({ prompt: msg, btn: {yes: (!btn ? "确定" : btn)}, name: "iframe_Name_" + Math.random(), title: (!title ? "提示" : title), width: 370, height: 180});
}

/**
 *
 * @param msg
 * @param yFun
 * @param title
 * @param yBtn
 * @param nBtn
 */
function showConfirm(msg,yFun,title,yBtn,nBtn) {
    hyxt.dialog.show({
        prompt : msg, name : "iframe_Name_"+Math.random(),title : (!title ? "提示" : title),width : 370, height : 180,btn:{
            yes:(!yBtn ? "确定" : yBtn),no:(!nBtn ? "取消" : nBtn),yes_after_close : function(){ yFun(); }
        }
    });
}