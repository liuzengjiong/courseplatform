/**
 * Created by yangxiagntian on 2016/12/9.
 */
window.onload = function () {


    var oUl = document.getElementById('list');//课程列表
    var oUser = document.getElementById('user');
    //刷新列表
    //refleshList();

    //JQuery ajax()请求数据
    $.ajax({
        url: "/teacher/getCourses",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: {},    //参数值
        type: "GET",   //请求方式
        beforeSend: function () {
            //请求前的处理
        },
        success: function (data) {
            //请求成功时处理
            if (data.code === '1') {
                oUl.innerHTML = '';//清除列表
                oUser.innerHTML = data.username;
                for (var i = 0; i < data.size; i++) {
                    var li = document.createElement('li');
                    var oA = document.createElement('a');//课程名
                    var oEm = document.createElement('em');//存放courseID
                    var oSpan = document.createElement('span');//删除
                    oA.innerHTML = data.list[i].courseName;
                    oEm.innerHTML = data.list[i].courseId;
                    oSpan.innerHTML = '删除';
                    oA.href = 'javascript:;';
                    li.appendChild(oA);
                    li.appendChild(oEm);
                    li.appendChild(oSpan);
                    oUl.appendChild(li);
                    //oEm.style.visibility="hidden";

                    oSpan.onclick = function () {
                        var _this = this;
                        var id = oEm.innerHTML;
                        if (confirm('是否要删除这门课程？')) {
                            $.ajax({
                                url: "/teacher/deleteCourse",    //请求的url地址
                                dataType: "json",   //返回格式为json
                                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                                data: {courseId: id},    //参数值
                                type: "GET",   //请求方式
                                success: function (data) {
                                    //请求成功时处理
                                    if (data.code === '1') {
                                        oUl.removeChild(_this.parentNode);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    });

    var oAdd = document.getElementById('add');//添加按钮
    var oCancel = document.getElementById('cancel');//取消按钮
    var oAddWrap = document.getElementById('wrap');//添加外层
    var oAddPanel = document.getElementById('addPanel');//添加层
    var aInput = oAddPanel.getElementsByTagName('input');//添加课程时输入的名称与教师
    var oIntroduction = document.getElementById('introduction');//添加的课程的简介
    var oSure = document.getElementById('sure');//确定添加按钮
    var aSpan = oUl.getElementsByTagName('span');//课程列表中的删除按钮
    var oUploadFile = document.getElementById('uploadFile');//上传文档
    var oUpload = document.getElementById('upload');//显示上传文档层
    var oUploadUl = oUpload.getElementsByTagName('ul')[0];//显示上传的文档列表
    var oAddfile = document.getElementById('addfile');


    //对课程的删除操作
    /* for (var i = 0; i < aSpan.length; i++) {
     aSpan[i].index = i;
     aSpan[i].onclick = function () {
     var id = parseInt(aSpan[this.index].parentNode.getElementsByTagName('em')[0].innerHTML);
     alert(id)
     if (confirm('是否要删除这门课程？')) {
     $.ajax({
     url: "/teacher/deleteCourse",    //请求的url地址
     dataType: "json",   //返回格式为json
     async: true, //请求是否异步，默认为异步，这也是ajax重要特性
     data: {courseId: id},    //参数值
     type: "DELETE",   //请求方式
     success: function (data) {
     //请求成功时处理
     if (data.code === '1') {
     oUl.removeChild(this.parentNode);
     }
     }
     });
     oUl.removeChild(this.parentNode);
     }
     }
     }*/
    //添加操作
    oAdd.onclick = function () {
        oAddWrap.style.display = 'block';
        aInput[0].focus();
    }
    //取消添加操作
    oCancel.onclick = function () {
        oAddWrap.style.display = 'none';
        aInput[0].value = '';
        oIntroduction.value = '';
        oUploadUl.innerHTML = '';
    }

    //添加文件：
    oAddfile.onclick = function () {
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
            if (mime != ".pdf" || ".swf") {
                alert("请选择pdf或swf格式的文件上传！");
                oInput.value = null;
            }
        }
        //移除已选择上传的文件
        oSpan.onclick = function () {
            oUploadUl.removeChild(this.parentNode);
        }
    }

    //确定添加操作
    oSure.onclick = function () {
        if (aInput[0].value != '') {
            /* var li = document.createElement('li');
             var oA = document.createElement('a');
             var oSpan = document.createElement('span');
             oA.innerHTML = aInput[0].value;
             oSpan.innerHTML = '删除';
             oA.href = 'javascript:;';
             li.appendChild(oA);
             li.appendChild(oSpan);
             oUl.appendChild(li);

             //对课程的删除操作
             oSpan.onclick = function () {
             if (confirm('是否要删除这门课程？')) {
             oUl.removeChild(this.parentNode);
             }
             }*/
            //提交
            document.getElementById('form').submit();
            //提示添加成功并关闭添加层
            alert("已成功添加课程：" + aInput[0].value);
            aInput[0].value = '';
            oIntroduction.value = '';
            oAddWrap.style.display = 'none';

            //刷新列表
            refleshList();
        }

        /*var oFileStrong = oUpload.getElementsByTagName('ul')[0].getElementsByTagName('strong');
         var oFilesEm = oUpload.getElementsByTagName('ul')[0].getElementsByTagName('em');
         var filesURL = [];
         var filesName = [];
         for (var i = 0; i < oFilesEm.length; i++) {
         filesURL.push(oFilesEm[i].innerHTML);
         filesName.push(oFileStrong[i].innerHTML);
         }*/
        //alert(filesURL);
        //ajax 提交数据(废了）
        /*$.ajax({
         url: "",    //请求的url地址
         dataType: "json",   //返回格式为json
         async: true, //请求是否异步，默认为异步，这也是ajax重要特性
         data: {
         coursename: encodeURI(aInput[0].value),
         teacher: encodeURI(aInput[1].value),
         introduction: encodeURI(oIntroduction.value),
         filenames: filesName,
         fileurls: filesURL
         },    //参数值
         type: "POST",   //请求方式
         enctype: 'multipart/form-data',
         beforeSend: function () {
         //请求前的处理
         },
         success: function (data) {
         //请求成功时处理
         if (data.code == '1') {
         window.open('studentIndex.html');
         }
         else {
         oMessage.style.display = 'block';
         }
         },
         complete: function () {
         //请求完成的处理
         },
         error: function () {
         //请求出错处理
         }
         });*/


    }
    //上传文档的显示处理(废了)
    /*oUploadFile.onchange = function () {
     var files = oUploadFile.files;
     if (files.length > 0) {
     for (var i = 0; i < files.length; i++) {
     var oLi = document.createElement('li');
     var oStrong = document.createElement('strong');
     var oEm = document.createElement('em');
     oEm.innerHTML = oUploadFile.value;
     //oEm.innerHTML = $('#uploadFile').val();
     var oSpan = document.createElement('span');
     oStrong.innerHTML = files[i].name;
     oSpan.innerHTML = '×';

     oLi.appendChild(oStrong);
     oLi.appendChild(oEm);
     oLi.appendChild(oSpan);
     oUploadUl.appendChild(oLi);

     oSpan.onclick = function () {
     oUploadUl.removeChild(this.parentNode);
     }
     }
     }
     }*/

    /* function refleshList() {
     //JQuery ajax()请求数据
     $.ajax({
     url: "/teacher/getCourses",    //请求的url地址
     dataType: "json",   //返回格式为json
     async: true, //请求是否异步，默认为异步，这也是ajax重要特性
     data: {},    //参数值
     type: "GET",   //请求方式
     beforeSend: function () {
     //请求前的处理
     },
     success: function (data) {
     //请求成功时处理
     if (data.code === '1') {
     oUl.innerHTML = '';//清除列表
     oUser.innerHTML = data.username;
     for (var i = 0; i < data.size; i++) {
     var li = document.createElement('li');
     var oA = document.createElement('a');//课程名
     var oEm = document.createElement('em');//存放courseID
     var oSpan = document.createElement('span');//删除
     oA.innerHTML = data.list[i].courseName;
     oEm.innerHTML = data.list[i].courseId;
     oSpan.innerHTML = '删除';
     oA.href = 'javascript:;';
     li.appendChild(oA);
     li.appendChild(oEm);
     li.appendChild(oSpan);
     oUl.appendChild(li);
     //oEm.style.visibility="hidden";

     //对课程的删除操作
     /!*oSpan.onclick = function () {
     //alert(1);
     var courseId = this.parentNode;
     if (confirm('是否要删除这门课程？')) {
     $.ajax({
     url: "/teacher/deleteCourse",    //请求的url地址
     dataType: "json",   //返回格式为json
     async: true, //请求是否异步，默认为异步，这也是ajax重要特性
     data: { courseId: parseInt(courseId)  },    //参数值
     type: "DELETE",   //请求方式
     success: function (data) {
     //请求成功时处理
     if( data.code === '1' ){
     oUl.removeChild(this.parentNode);
     }
     }
     });
     }
     }*!/
     }
     }
     },
     complete: function () {
     //请求完成的处理
     },
     error: function () {
     //请求出错处理
     }
     });
     }*/
}
