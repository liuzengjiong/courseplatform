/**
 * Created by yangxiagntian on 2016/12/9.
 */
function getContextPath(){   
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
}  
window.onload = function () {

    var oUl = document.getElementById('list');//课程列表

    var oUser = document.getElementById('user');
    var oContainer = document.getElementById('container');
    var oContent = document.getElementById('content');
    var oAdd = document.getElementById('add');//添加按钮
    
    var oCancel = document.getElementById('cancel');//取消按钮
    var oAddWrap = document.getElementById('wrap');//添加外层
    var oAddPanel = document.getElementById('addPanel');//添加层
    var aInput = oAddPanel.getElementsByTagName('input');//添加课程时输入的名称与教师
    var oIntroduction = document.getElementById('introduction');//添加的课程的简介

    var oSure = document.getElementById('sure');//确定添加按钮
 
    var oSpan = document.getElementsByTagName('span');//课程列表中的删除按钮
   
  //  var oUploadFile = document.getElementById('uploadFile');//上传文档
   
    var oUpload = document.getElementById('upload');//显示上传文档层
  
    var oUploadUl = oUpload.getElementsByTagName('ul')[0];//显示上传的文档列表
  //  var oAddfile = document.getElementById('addfile');
  
    //课程信息
    var href = window.location.href;
    var courseId = href.substring(href.indexOf('?')+1,href.index);//课程ID
    if(href.indexOf("&")!=-1){
    	courseId = courseId.substring(0, courseId.indexOf("&"));
    	oAdd.hidden = true;
    }
    
    //刷新列表
    refleshList();
    //oContainer.style.height = 200+oContent.offsetHeight+50+'px';

    
    //添加操作
    oAdd.onclick = function () {
        oAddWrap.style.display = 'block';
        aInput[0].focus();
    }
    //取消添加操作
    oCancel.onclick = function () {
        oAddWrap.style.display = 'none';
       // aInput[0].value = '';
        //oIntroduction.value = '';
       // oUploadUl.innerHTML = '';
    }

    //添加文件：
   /* oAddfile.onclick = function () {
        var oLi = document.createElement('li');
        var oInput = document.createElement('input');
        var oSpan = document.createElement('span');
        oInput.type = 'file';
        oInput.name = 'files';
        oSpan.innerHTML = '×';

        oLi.appendChild(oInput);
        oLi.appendChild(oSpan);
        oUploadUl.appendChild(oLi);

        oInput.onchange = function () {
            var filename = oInput.value;
            var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
            if (!(mime == ".pdf"||mime ==".swf")){
                alert("请选择pdf或swf格式的文件上传！");
                oInput.value = null;
            }
        }
        //移除已选择上传的文件
        oSpan.onclick = function () {
            oUploadUl.removeChild(this.parentNode);
        }
    }*/

    //确定添加操作
    oSure.onclick = function () {
    		var fileName = document.getElementById('file').value;
        	if(fileName){
        		var mime = fileName.toLowerCase().substr(fileName.lastIndexOf("."));
                if (!(mime == ".doc"||mime ==".docx")){
                    alert("请选择doc或docx格式的文件上传！");
                    document.getElementById('file').value = null;
                    return;
                }
        		if(document.getElementById('courseName').value){
        			var oForm = document.getElementById('form');
        			var oInputId = document.createElement('input');
                    oInputId.name = 'courseId';
                    oInputId.value = courseId;
                    oForm.appendChild(oInputId);
        			//提交
                    oForm.action = getContextPath()+"/experiment/addExperiment";
                    oForm.submit();
                    //提示添加成功并关闭添加层
                    alert("已成功添加实验报告：" + document.getElementById('courseName').value);
                    document.getElementById('courseName').value = '';
                    document.getElementById('file').value = '';
                    oAddWrap.style.display = 'none';
                    
                    //刷新列表
                    //refleshList();	
        		}else{
        			alert("请输入实验报告名称");
        		}
        	}else{
        		alert("请选择文件");
        	}
            

    }
    function getFileUrl(id){
    	  var result;
    	  $.ajax({
              url: getContextPath()+"/experiment/createCopyment",    //请求的url地址
              dataType: "json",   //返回格式为json
              async: false, //请求是否异步，默认为异步，这也是ajax重要特性
              data: {"id":id},    //参数值
              type: "GET",   //请求方式
              beforeSend: function () {
                  //请求前的处理
              },
              success: function (data) {
            	  if(data.code==1){
            		  result = data.path;
            		 // alert(data.path);
            	  }else{
            		  alert("未能创建文件副本");
            	  }
              }
    	  });
    	  
    	  return result;
    }
    
    function refleshList() {
        //JQuery ajax()请求数据
        $.ajax({
            url: getContextPath()+"/experiment/getExperiments",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {"courseId":courseId},    //参数值
            type: "GET",   //请求方式
            beforeSend: function () {
                //请求前的处理
            },
            success: function (data) {
                //请求成功时处理
                if (data.code === '1') {
                	
                    oUl.innerHTML = '';//清除列表
                    oUser.innerHTML = data.username;
                    if(data.size==0){
                    	alert("目前还没有实验报告");
                    }
                    for (var i = 0; i < data.size; i++) {
                        var li = document.createElement('li');
                        var oA = document.createElement('a');//课程名
                        var oEm = document.createElement('em');//存放courseID
                        //var oSpan = document.createElement('span');//删除
                        oA.innerHTML = data.list[i].experimentName;
                        oEm.innerHTML = data.list[i].id;
                       // oSpan.innerHTML = '删除';
                        var fileUrl = getFileUrl(data.list[i].id)//data.list[i].fileUrl;
                        fileUrl=encodeURI(fileUrl); 
                        fileUrl=encodeURI(fileUrl); 
                        var url=getContextPath()+"/editfile.jsp?fileId="+fileUrl;
                        //window.open(url,'newWin','modal=yes,width=560,height=420,resizable=no,scrollbars=no'); 
                        oA.href = url;
                        oA.target = "blank";
                        li.appendChild(oA);
                        li.appendChild(oEm);
                        //li.appendChild(oSpan);
                        oUl.appendChild(li);
                        oEm.style.visibility="hidden";
                        oContainer.style.height = 200+oContent.offsetHeight+50+'px';
                            //删除
                        oSpan.onclick = function () {
                            var _this = this;
                            var id = this.previousElementSibling.innerHTML;//获取上一个兄弟节点的内容
                            if (confirm('是否要删除这门课程？')) {
                                $.ajax({
                                    url: getContextPath()+"/teacher/deleteCourse",    //请求的url地址
                                    dataType: "json",   //返回格式为json
                                    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                                    data: {courseId: id},    //参数值
                                    type: "GET",   //请求方式
                                    success: function (data) {
                                        //请求成功时处理
                                        if (data.code === '1') {
                                            //oUl.removeChild(_this.parentNode);
                                            refleshList();
                                        }
                                    }
                                });
                            }
                        }

                        //跳转
                        oA.onclick = function () {
                            var id = this.nextElementSibling.innerHTML;
                            $.ajax({
                                url: getContextPath()+"/teacher/getCourse",    //请求的url地址
                                dataType: "json",   //返回格式为json
                                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                                data: {courseId: id},    //参数值
                                type: "GET",   //请求方式
                                success: function (data) {
                                    //请求成功时处理
                                    if (data.code === '1') {
                                        window.location.href = 'courseInfo-teacher.html'+'?'+id;
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}
