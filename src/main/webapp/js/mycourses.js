/**yangxiangtian
 * Created by Administrator on 2016/12/12.
 */
function getContextPath(){   
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
}  

var oUl = $('#list');
$(function () {
//JQuery ajax()请求数据
    $.ajax({
        url: getContextPath()+"/student/getCourses",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: {},    //参数值
        type: "GET",   //请求方式
        beforeSend: function () {
            //请求前的处理
        },
        success: function (data) {
            //请求成功时处理
            if(data.code === '1'){
                oUser.innerHTML = data.username;
                for(var i=0;i<data.size;i++){
                    var li = document.createElement('li');
                    var oA = document.createElement('a');//课程名
                    var oEm = document.createElement('em');//存放courseID
                    oA.innerHTML = data.list[i].courseName;
                    oEm.innerHTML = data.list[i].courseId;
                    oA.href = 'javascript:;';
                    li.appendChild(oA);
                    oUl.appendChild(li);

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
});