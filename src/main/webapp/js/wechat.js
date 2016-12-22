document
    .write("<script type='text/javascript' src='js/jquery.min.js'></script>");

// 二维码显示模板
var QRModel = '<div id="QR_div">'
    + '<img id="QR_img" alt="扫描二维码" src="https://login.weixin.qq.com/qrcode/@uuid"/>'
    + '<p id="QR_p">扫描二维码登录</p>' + '</div>'
    + '<script type="text/javascript">waitLogin();</script>';
// 登录按钮模板
var loginModel = '<div id="login">'
    + '<a href="#" id="login_btn" class="weui_btn weui_btn_primary">登录微信</a>'
    + '</div>';

login = function () {
    $.ajax({
        url: "wechat/login",
        type: "GET",
        dataType: 'text',
        success: function (data) {
            if (data.length > 0) {
                var html = '<div id="QR_div">'
                    + '<img id="QR_img" alt="扫描二维码" src="https://login.weixin.qq.com/qrcode/'
                    + data
                    + '">'
                    + '<p id="QR_p">扫描二维码登录</p>'
                    + '</div>'
                    + ''
                    + '<script type="text/javascript">waitLogin();</script>';
                $("body").html(html);
            } else {
                alert("网络错误，请重试");
            }
        }
    });

}

/**
 * 登录失败
 *
 * @type
 */
var login_fail = -1;
/**
 * 登录成功
 *
 * @type Number
 */
var login_success = 1;
/**
 * 等待扫描二维码
 *
 * @type Number
 */
var login_scanning = 0;
/**
 * 等待确认登录
 *
 * @type Number
 */
var login_comfirm = 2;
/**
 * 登录超时
 *
 * @type Number
 */
var login_overtime = 3;
/**
 * 获取登录状态
 *
 */
loginState = function () {
    $.ajax({
        url: "loginState.do",
        type: "GET",
        dataType: 'text',
        timeout: 40 * 1000,
        success: function (data) {
            state = data;
            if (state == login_comfirm) {
                // 已经扫描二维码，等待确认
                waitConfirm();
            } else if (state == login_overtime) {
                // 超时
                loginOverTime();
            } else {
                alert("可能失败");
                alert(state + "aaa");
            }
        }
    });
}

/**
 * 等待登录
 */
waitLogin = function () {
    loginState();
}
/**
 * 等待确认登录
 */
waitConfirm = function () {
    $("#QR_p").html("扫描成功,请在手机微信上确认登录");
    $.ajax({
        url: "loginState.do",
        type: "GET",
        dataType: 'text',
        timeout: 40 * 1000,
        success: function (data) {
            state = data;
            if (state == login_success) {
                // 登录成功 TODO 页面跳转
                init();
            } else if (state == login_overtime) {
                // 超时
                loginOverTime();
            } else {
                alert(state);
            }
        }
    });
}

/**
 * 登录超时
 */
loginOverTime = function () {
    $("body").html(loginModel);
}

init = function () {
    // simulates similar behavior as an HTTP redirect
    window.location.replace("weChat/init.do");
    // $.ajax({
    // url : "weChat/init.do",
    // type : "GET",
    // dataType : 'text',
    // timeout : 40 * 1000,
    // success : function(data) {
    // }
    // });
}

mailList = function () {
    window.location.replace("weChat/mailList.do");
}