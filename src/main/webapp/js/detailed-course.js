/**
 * Created by yangxiangtian on 2016/12/13.
 */
window.onload = function () {
    var oContainer = document.getElementById('container');
    var oContent = document.getElementById('content');
    var oUser = document.getElementById('user');//用户
    var oCourseName = oContent.getElementsByTagName('h2')[0];//课程名
    var oTeacherName = document.getElementById('teacherName');//科任教师
    var oIntroduction = document.getElementById('introduction');//简介
    var oMainInfo = document.getElementById('mainInfo');//大纲
    var fileList = document.getElementById('fileslist');//课件列表
    var oReportOL = document.getElementById('reportOL');//实验报告在线编辑
    var oComment = document.getElementById('comment');//评论
    var oSend = document.getElementById('send');//评论发送

    //课程信息
    var href = window.location.href;
    var courseId = href.substring(href.indexOf('?')+1);//课程ID
    // 请求数据
    //学生信息
    $.ajax({
        url: "/student/info",    //请求的url地址
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
    oContainer.style.height = 200 + oContent.offsetHeight + 50 + 'px';
    //刷新数据
    refleshCourse();

    //在线编辑转接
    oReportOL.onclick = function () {
        alert("转到报告在线编辑");
    }
    //发送评论
    oSend.onclick = function () {
        alert('暂时没有完善');
    }
    function refleshCourse() {

        $.ajax({
            url: "/student/getCourse",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {courseId: courseId},    //参数值
            type: "GET",   //请求方式
            success: function (data) {
                //请求成功时处理
                if (data.code === '1') {
                    oCourseName.innerHTML = data.course["courseName"];
                    oIntroduction.innerHTML = data.course["courseIntroduction"];
                    oMainInfo.innerHTML = data.course["courseSyllabus"];
                    var courseware = data.coursewares;
                    //清空课件列表
                    fileList.innerHTML = '';
                    for (var i = 0; i < courseware.length; i++) {
                        var oLi = document.createElement('li');
                        var oA = document.createElement('a');
                        var oEm = document.createElement('em');
                        oA.innerHTML = courseware[i].coursewareName;
                        oA.href = courseware[i].filePath;
                        oA.target = 'blank';
                        oEm.innerHTML = courseware[i].coursewareId;
                        //添加到课件列表
                        oLi.appendChild(oA);
                        oLi.appendChild(oEm);
                        fileList.appendChild(oLi);
                        oEm.style.display = 'none';
                        oContainer.style.height = 200 + oContent.offsetHeight + 50 + 'px';

                    }
                }
            }
        });
    }
}