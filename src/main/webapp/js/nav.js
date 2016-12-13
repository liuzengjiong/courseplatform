/**
 * Created by Administrator on 2016/12/6.
 */
var oUl = document.getElementById('nav').getElementsByTagName('ul')[0];
var aLi = oUl.getElementsByTagName('li');

var speed = 0;
var left = 0;

for (var i=0;i<aLi.length-1;i++){
    aLi[i].onmouseover = function () {
        aLi[aLi.length-1].style.width = this.offsetWidth+'px';
        startMove(aLi[aLi.length-1],this.offsetLeft);
    }
}

function startMove(obj, iTarget) {
    clearInterval(obj.timer);
    obj.timer = setInterval(function(){

        speed +=(iTarget - obj.offsetLeft)/8;
        speed *= 0.7;
        left += speed;      //定义一个变量left存放，可以解决由speed计算造成的小数带来的误差
        if(Math.abs(left-iTarget) <1 && Math.abs(speed) < 1){ //当位置与目标位置的差值小于1，和速度小于1
            //即，它们足够小时，关闭定时器，以免浪费资源，这是让left等于
            //iTarget,因为speed计算造成的小数带来的误差
            clearInterval(obj.timer);
            obj.style.left = iTarget + 'px';
        }
        else {
            obj.style.left = left + 'px';
            //obj.style.left = obj.offsetLeft + speed + 'px';
        }
    },30);

}