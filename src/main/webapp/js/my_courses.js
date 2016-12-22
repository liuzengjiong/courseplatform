/**
 * Created by yangxiangtian on 2016/12/13.
 */
window.onload = function () {
    var oCourseList = document.getElementById('list');

    //获取列表
    $.ajax({
        url: "/student/getCourses",    //请求的url地址
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
                oCourseList.innerHTML = '';//清除列表
                //oUser.innerHTML = data.username;
                for (var i = 0; i < data.list.length; i++) {
                    var li = document.createElement('li');
                    var oH3 = document.createElement('h3');//
                    var oA = document.createElement('a');//课程名
                    var oEm = document.createElement('em');//存放courseID
                    var oH5 = document.createElement('h5');//科任老师
                    var oP = document.createElement('p');//简介
                    oA.innerHTML = data.list[i].courseName;
                    oEm.innerHTML = data.list[i].courseId;
                    oH5.innerHTML = data.list[i].teacherName;
                    oP.innerHTML = data.list[i].courseIntroduction;
                    oA.href = 'javascript:;';
                    oH3.appendChild(oA);
                    oH3.appendChild(oEm);
                    li.appendChild(oH3);
                    // li.appendChild(oEm);
                    li.appendChild(oH5);
                    li.appendChild(oP);
                    oCourseList.appendChild(li);
                    //oEm.style.visibility="hidden";

                    //跳转详细页面
                    oA.onclick = function () {
                        var id = this.nextElementSibling.innerHTML;
                        $.ajax({
                            url: "/student/getCourse",    //请求的url地址
                            dataType: "json",   //返回格式为json
                            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                            data: {courseId: id},    //参数值
                            type: "GET",   //请求方式
                            success: function (data) {
                                //请求成功时处理
                                if (data.code === '1') {
                                    window.parent.location.href = 'detailed-course.html'+'?'+id;
                                }
                            }
                        });
                    }
                }
            }
        }
    });
}