<style type="text/css">
  body{min-width:1000px;}
  a:hover { color:#f74e37; }
  .screen .headBox { padding-bottom:25px; background:#e3e3e3; height:84px; position:fixed; width:100%; left:0; top:0; z-index:8;}
  .screen .headBox .topBox { overflow:hidden; height:84px; background:#fff; padding:0 40px 0 20px;}
  .screen .headBox .childicon { float:left; width:45px; height:45px; overflow:hidden; margin-top:20px;}
  .screen .headBox .childicon img { float:left;  width:45px; height:45px;}
  .screen .headBox .childtetle{ float:left; height:45px; line-height:45px; overflow:hidden; padding:20px 0 0 24px; font-size:24px; color:#666;}
  .screen .headBox .childclose { float:right; overflow:hidden; width:34px; height:34px; margin:25px 0 0 28px; background:url(${root}/commons/img/close.png);}
  .screen .headBox .childclose:hover{ background:url(${root}/commons/img/closehover.png);}
  .screen .headBox .childbut{ float:right;overflow:hidden; margin-top:20px;margin-right:19px}
  .screen .contentBox{ overflow:hidden; margin-left:20px; min-height:500px; background:#fff; padding: 129px 20px 20px 20px;}

  .g-tableList{font-size:14px;table-layout:auto;}
  .g-tableList thead th{font-size:14px;color:#666;border-bottom:1px solid #e5e5e5;}
  .g-tableList tbody td{font-size:14px;border-bottom:1px solid #e5e5e5;color:#666;line-height:20px;}
  .g-tableList tbody tr:nth-child(2n-1) td { background:#f7f7f7; }
  .g-tableList tbody img { border:1px solid #cecece; }
  .g-tableList .thSort { cursor:ns-resize; }
  .g-tableList .thSort span {background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALCAMAAACah1cpAAAAM1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4Ovn3wBAAAAEHRSTlMAJC02P0JOUVpgeIfq8Pb57Ter4AAAAD9JREFUeF5Fy1sKgDAQQ9GMWh9ja+/+VysZBM9XCIns0CdJlR04HdYHmE2Kjo3QbxlYD6lN7za3V33LTepTzQuA3AJblVbHMwAAAABJRU5ErkJggg==) /*${root}/commons/img/toggle-ico.png*/ right 5px no-repeat;_background:url(${root}/commons/img/toggle-ico.png) right 5px no-repeat;display:inline-block;padding-right:16px;}
  .g-tableList .thSort.desc span{background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALBAMAAABfd7ooAAAAJ1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4PIqRqDAAAADHRSTlMA+VrwJEItP2Dqh3i2e/DgAAAAM0lEQVR4XnWKsQ0AIAzDsrDwQJcOPYVf+g6PIXwUFRsDGSJZtn5LWEPNsYJJ1Pd9TbzhAdMxBnYi4KGKAAAAAElFTkSuQmCC);_background-image: url(${root}/commons/img/toggle-ico1.png);}
  .g-tableList .thSort.asc span{background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALBAMAAABfd7ooAAAAJ1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4PIqRqDAAAADHRSTlMAJHhRWjb2+UJO6vCS7BriAAAAL0lEQVR4Xm3EsQ0AEBRAwReJXm0KIxjIAIYxjdYbSn7NFcdPi9KOlx3ycELVU3hcwU8G34G0RpYAAAAASUVORK5CYII=);_background-image: url(${root}/commons/img/toggle-ico2.png);}

  .g-dialog .g-dialog_tit{background: #f74e37;}
  input[type=text]:focus, input[type=email]:focus, input[type=password]:focus, textarea:focus, input[type=number]:focus, input[type=tel]:focus {border: 1px solid #f74e37;}
  .pagebar_numlist .pagebar_numlist_current,.pagebar_numlist a:hover{background-color:#f74e37;border:1px solid #f74e37}
  .pagebar_numlist button:hover {background: #f74e37;color: #FFF;}
  .tableTopBtn{text-align:right;padding:10px 0;}
  .exportDataBtn{display:inline-block;text-align:left;background:url(${root}/commons/img/export.png) left 0 no-repeat;padding-left:18px;}
  #loading { display: none; position: fixed; top:0; left:0; width:100%; height:100%; z-index:9999; background:rgba(0, 0, 0, .5) url(${root}/commons/img/loading.gif) no-repeat center; }
</style>