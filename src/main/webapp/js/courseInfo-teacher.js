/**
 * Created by yangxiangtian on 2016/12/13.
 */
window.onload = function () {
    var oContainer = document.getElementById('container');
    var oContent = document.getElementById('content');
    var oAdd = document.getElementById('add');//添加
    var oWrap = document.getElementById('wrap');//添加层
    var oCancel = document.getElementById('cancel');//取消添加
    var oAddFile = document.getElementById('addfile');//添加文件
    var oUploadUl = document.getElementById('uploadList');//添加上传的列表
    var oUser = document.getElementById('user');//用户
    var oCourseName = oContent.getElementsByTagName('h2')[0];//课程名
    var oIntroduction = document.getElementById('introduction');//简介
    var oMainInfo = document.getElementById('mainInfo');//大纲
    var fileList = document.getElementById('filesList');//课件列表
    var aInputLi = oUploadUl.getElementsByTagName('input');//已选择的文件列表
    var oSure = document.getElementById('sure');//确定添加按钮

    //请求数据
    //alert(id);
    //教师信息
    $.ajax({
        url: "/teacher/info",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: { },    //参数值
        type: "GET",   //请求方式
        success: function (data) {
            //请求成功时处理
            if (data.code === '1') {
                oUser.innerHTML = data.user["name"];
            }
        }
    });
    //课程信息
    /*var href = window.location.href;
    var courseId = href.substring(href.indexOf('?')+1);//课程ID
    $.ajax({
        url: "/teacher/getCourse",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: { courseId: courseId},    //参数值
        type: "GET",   //请求方式
        success: function (data) {
            //请求成功时处理
            if (data.code === '1') {
                oCourseName.innerHTML = data.course["courseName"];
                oIntroduction.innerHTML = data.course["courseIntroduction"];
                oMainInfo.innerHTML = data.course["courseSyllabus"];
                var courseware = data.courseware;
                for(var i=0;i<courseware.length;i++){
                    var oLi = document.createElement('li');
                    var oA = document.createElement('a');
                    var oEm = document.createElement('em')
                    var oSpan = document.createElement('span');
                    oA.innerHTML = courseware[i].coursewareName;
                    oA.href = courseware[i].coursewareURL;
                    oEm.innerHTML = courseware[i].coursewareId;
                    oSpan.innerHTML = "删除";
                    //添加到课件列表
                    oLi.appendChild(oA);
                    oLi.appendChild(oEm);
                    oLi.appendChild(oSpan);
                    fileList.appendChild(oLi);

                    //删除操作
                    oSpan.onclick = function () {
                        var _this = this;
                        var coursewarwId = oEm.innerHTML;
                        if (confirm('是否要删除这门课程？')) {
                            $.ajax({
                                url: "/teacher/deleteCourseware",    //请求的url地址
                                dataType: "json",   //返回格式为json
                                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                                data: {courseId: coursewarwId},    //参数值
                                type: "DELETE",   //请求方式
                                success: function (data) {
                                    //请求成功时处理
                                    if (data.code === '1') {
                                        fileList.removeChild(_this.parentNode);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    });*/
    refleshCourse();

    oContainer.style.height = 200+oContent.offsetHeight+50+'px';

    oAdd.onclick = function () {
        oWrap.style.display = 'block';
    }
    oCancel.onclick = function () {
        oWrap.style.display = 'none';
    }

    //添加文件：
    oAddFile.onclick = function () {
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
            var mime = filename.toLowerCase().substring(filename.lastIndexOf("."));
            if (!(mime == ".pdf"||mime ==".swf")){
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
        if (aInputLi[0].value != "") {
            //提交
            document.getElementById('form').submit();
            //刷新课程信息
            refleshCourse();
            //提示添加成功并关闭添加层
            alert("已成功添加课件");
            oUploadUl.innerHTML = '';
        }
    }

function refleshCourse() {
    //课程信息
    var href = window.location.href;
    var courseId = href.substring(href.indexOf('?')+1);//课程ID
    $.ajax({
        url: "/teacher/getCourse",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: { courseId: courseId},    //参数值
        type: "GET",   //请求方式
        success: function (data) {
            //请求成功时处理
            if (data.code === '1') {
                oCourseName.innerHTML = data.course["courseName"];
                oIntroduction.innerHTML = data.course["courseIntroduction"];
                oMainInfo.innerHTML = data.course["courseSyllabus"];
                var courseware = data.coursewares;
                for(var i=0;i<courseware.length;i++){
                    var oLi = document.createElement('li');
                    var oA = document.createElement('a');
                    var oEm = document.createElement('em')
                    var oSpan = document.createElement('span');
                    oA.innerHTML = courseware[i].coursewareName;
                    oA.href = courseware[i].filePath;
                    oEm.innerHTML = courseware[i].coursewareId;
                    oSpan.innerHTML = "删除";
                    //添加到课件列表
                    oLi.appendChild(oA);
                    oLi.appendChild(oEm);
                    oLi.appendChild(oSpan);
                    fileList.appendChild(oLi);

                    //删除操作
                    oSpan.onclick = function () {
                        var _this = this;
                        var coursewarwId = oEm.innerHTML;
                        if (confirm('是否要删除这个课件？')) {
                            $.ajax({
                                url: "/teacher/deleteCourseware",    //请求的url地址
                                dataType: "json",   //返回格式为json
                                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                                data: {courseId: coursewarwId},    //参数值
                                type: "DELETE",   //请求方式
                                success: function (data) {
                                    //请求成功时处理
                                    if (data.code === '1') {
                                        fileList.removeChild(_this.parentNode);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    });

}
}