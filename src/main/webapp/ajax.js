/**
 * Created by Administrator on 2016/12/8.
 */
/*参数说明：
 opts: { '可选参数' },
 method：请求方式post/get，默认get
 url: 发送请求的地址，
 data：发送数据，json
 async: 是否异步true/false，默认true
 cache: 是否缓存true/false ,默认为true
 contentType：HTTP头信息，默认值'application/x-www.form-urlencoded'
 success: 请求成功后的回调函数
 error：请求失败后的回调函数
 */

function ajax(opts) {
    //设置默认值
    var defaults = {
        method: 'get',
        url: '',
        data: '',
        async: true,
        cache: true,
        contentType: 'application/x-www.form-urlencoded',
        success: function(){},
        error: function(){}
    };
    //用户参数覆盖默认参数
    for(var key in opts){
        defaults[key] = opts[key];
    }
    //数据的处理
    if(typeof defaults.data === 'object'){
        //处理data
        var str = '';
        for(var key in defaults.data){
            str += key + '=' + defaults.data[key]+'&';
        }
        defaults.data = str.substring(0,str.length-1);

        //处理method
        defaults.method =defaults.method.toUpperCase();
        //处理cache
        defaults.cache = defaults.cache ? '' : '&'+new Date().getTime();
        //处理url
        if(defaults.method === 'GET' && defaults.data || defaults.cache){
            defaults.url += '?'+defaults.data+defaults.cache;
        }
    }

    //ajax 的编写
    //创建ajax对象
    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    //和服务器建立联系，告诉服务器你要取得什么文件
    xhr.open(defaults.method,defaults.url,defaults.async);
    //发送请求
    if(defaults.method === 'GET'){
        xhr.send(null);
    }else {
        xhr.setRequestHeader('content-type',defaults.contentType);
        xhr.send(defaults.data);
    }
    //等待服务器的响应
    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                defaults.success.call(xhr,xhr.responseText);
            }else {
                defaults.error();
            }
        }
    }
}