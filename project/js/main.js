// 로드되면 바텀 창 바뀜
window.onload = function () {
    // 바텀 바 로드 // 타이틀 버튼
    let btn = document.getElementById('bottom_bar');
    let mbt = document.getElementById('main_button');
    btn.style.width = '100%';
    btn.style.height = '60px';
    btn.style.backgroundColor = 'rgb(0, 50, 110)';
    mbt.style.right = '38px';

    changeOpacity('main_button', 1);
    changeOpacity('title_name', 1);
    changeOpacity('main_gifs', 1);

    // 이미지 로드
    // 함수 포문과 재귀함수 등등 써서 최대한 줄이려 했지만 실패함
    setTimeout("imgRotateId('main_title_img',-20,0.8,240);", 3500); setTimeout("imgRotateId('main_title_img',90,0.8,70);", 4200); setTimeout("imgRotateId('main_title_img',-10,1.5,200);", 4800); setTimeout("imgRotateId('main_title_img',15,0.8,170);", 6000); setTimeout("imgRotateId('main_title_img',0,1,190);", 6500);
    setTimeout('imgRotateId("gif1",-15,3,500);', 1000); setTimeout('imgRotateId("gif1",0,2,450)', 2800); setTimeout('imgRotateId("gif1",0,2,500)', 4600);
    setTimeout('imgRotateId("gif2",-10,2,500);', 1000); setTimeout('imgRotateId("gif2",30,2,300)', 2800); setTimeout('imgRotateId("gif2",0,2,500)', 4600);
    setTimeout('imgRotateId("gif3",-26,1,500);', 1000); setTimeout('imgRotateId("gif3",15,1,110)', 1800); setTimeout('imgRotateId("gif3",0,1,500)', 2600);
    setTimeout('imgRotateId("gif4",24,3,500);', 1000); setTimeout('imgRotateId("gif4",0,2,500)', 3800); setTimeout('imgRotateId("gif4",0,1,500)', 5600);
    setTimeout('imgRotateId("gif5",20,2,500);', 1000); setTimeout('imgRotateId("gif5",-20,1,330)', 2800); setTimeout('imgRotateId("gif5",0,2,500)', 3600);
    setTimeout('imgRotateId("gif6",-20,2,500);', 1000); setTimeout('imgRotateId("gif6",15,2,400)', 2800); setTimeout('imgRotateId("gif6",0,1,500)', 3600);
    setTimeout('imgRotateId("gif7",-15,1,500);', 1000); setTimeout('imgRotateId("gif7",30,1,140)', 1800); setTimeout('imgRotateId("gif7",0,2,500)', 2600);
    setTimeout('imgRotateId("gif8",-30,4,500);', 1000); setTimeout('imgRotateId("gif8",0,2,500)', 4800); setTimeout('imgRotateId("gif8",0,1,500)', 6600);
    setTimeout('imgRotateId("gif9",-25,2,500);', 1000); setTimeout('imgRotateId("gif9",25,1,220)', 2800); setTimeout('imgRotateId("gif9",0,2,500)', 3600);
}
//  =========================================== 메인의 gif 파일들 동작
let mainImg = document.getElementsByClassName('main_img');
let marginTop = [-200, -420, -280, -520, -500, -220, -270, -530, -600, -220, -800, -400];
// function imgMarginTop(){
//     for(let i = 0; i < mainImg.length; i++){
//         mainImg[i].style["margin-top"] = marginTop[i] + "px";
//         marginTop[i] ++;

//         if(marginTop[i] == 400){
//             clearInterval(timeId);
//         }
//     }
// }
// let timeId = setInterval('imgMarginTop()', 2);

// gif 파일들 떨어지게 동작
// function imgMarginTop(){
//     for(let i = 0; i < mainImg.length; i++){
//         console.log(marginTop[i]);
//         if(marginTop[i] < 300){
//             let randInt = Math.ceil(Math.random()* 10); 
//             marginTop[i] = marginTop[i] + randInt;
//             mainImg[i].style["margin-top"] = marginTop[i] + "px";
//             marginNum = parseInt(mainImg[i].style["margin-top"])
//         }else if(marginTop[i] >= 298 && marginTop[i] <= 303){
//             mainImg[i].style["margin-top"] = marginTop[i] + "px";
//         }else if(marginTop[i] > 300){
//             for(let j = 0; j < 250; j ++){
//                 let randInt = 1; 
//                 marginTop[i] = marginTop[i] - randInt;
//                 mainImg[i].style["margin-top"] = marginTop[i] + "px";
//                 marginNum = parseInt(mainImg[i].style["margin-top"])
//             }
//         }
//     }
// }

// -80 <  x  < 400 , x > 400
// 시간에 따라 반복 동작
// 6초후 멈춤


// 필요한 변수들 선언
// true 일때 up / false일때 down
let unDownImg = true;






// 각도를 조정하는 함수를 만들자
// 피라미터로 get요소를 받고, 각도를 받는다
// 한번
// 아이디로 돌리기
// 여기에 마진값도 주자
function imgRotateId(p_string, p_deg, p_second, p_margin) {
    let getEle = document.getElementById(p_string);
    getEle.style["transition-duration"] = p_second + "s";
    getEle.style["rotate"] = p_deg + "deg";
    getEle.style["margin-top"] = p_margin + "px";
}

// 클래스로 돌리기
function imgRotateClass(p_string, p_deg, p_second) {
    let getEle = document.getElementsByClassName(p_string);
    for (let i = 0; i < getEle.length; i++) {
        let randInt = Math.ceil(Math.random() * 20);
        getEle[i].style["transition-duration"] = p_second + "s";
        getEle[i].style["rotate"] = p_deg + "deg";

    }
}

// 불투명도 바꾸기
function changeOpacity(p_id, p_num) {
    let getEle = document.getElementById(p_id);
    getEle.style["opacity"] = p_num;
}
// display  바꾸기
function displayNone(p_id, p_string) {
    let getEle = document.getElementById(p_id);
    getEle.style["display"] = p_string;
}

// 홈 버튼 함수
function homeBut() {
    window.open('main2.html', '_self');
}

// 홈 바텀의 로그인 버튼
function fn_registe() {
    // 메인 이미지들
    changeOpacity('main_contents', 0);
    imgRotateId('main_contents', 0, 1.5, -650);
    setTimeout('displayNone("main_contents", "None");', 1500)
    let c_reg = document.getElementById('c_registe');
    let s_form = document.getElementById('sign_form');
    c_reg.style['position'] = 'static';
    s_form.style['right'] = '25%';
    s_form.style['top'] = '50%';
    s_form.style['bottom'] = '0px';
    s_form.style['opacity'] = '1';
    s_form.style['width'] = '470px';
    s_form.style['height'] = '450px';
}
// 로그인 버튼 눌렀을 시 condition 동적인 움직임
function fn_registeCon(){
    changeOpacity('c_condition', 0);
    setTimeout("fn_closeCon()", 1500);
    setTimeout('displayNone("b_condition", "none");', 2800); 
}

// 로그인 버튼 애니메이션으로 나오기
function fn_registe_ani() {
    displayNone('sign_form', 'block');
    setTimeout("fn_registe()", 2000);
}



// 로그인 화면 sign in/ sing up
function resetClass(element, classname) {
    element.classList.remove(classname);
}

function fn_signUn() {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "signin");
    resetClass(form, "reset");
    form.classList.add("signup");
    document.getElementById("submit-btn").innerText = "Sign Up";
}

function fn_signIn() {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "signup");
    resetClass(form, "reset");
    form.classList.add("signin");
    document.getElementById("submit-btn").innerText = "Sign In";

}


// 로그인 화면 Close
function fn_close() {
    // 로그인 화면 제어
    let c_reg = document.getElementById('c_registe');
    let s_form = document.getElementById('sign_form');
    s_form.style['right'] = '100px';
    s_form.style['top'] = '100%';
    s_form.style['bottom'] = '10%';
    s_form.style['opacity'] = '0';
    s_form.style['width'] = '10px';
    s_form.style['height'] = '70px';
    setTimeout("displayNone('sign_form','none');", 800);

    // 이미지 제어
    displayNone("main_contents", "block");
    if(!unDownImg){
        setTimeout("changeOpacity('main_contents',1);", 2000)
        setTimeout("imgRotateId('main_contents', 0, 1, 0);", 300)
        comeback_Img();
    }else{
        setTimeout("changeOpacity('main_contents',1);", 500)
        setTimeout("imgRotateId('main_contents', 0, 1, 0);", 500)
    }
}

// 메인의 이미지들 제자리 찾아가는 함수
function comeback_Img(){
    let img_Ele = document.getElementById('main_contents');
    displayNone("main_contents", "block");
    img_Ele.style['transition-duration'] = "2s";
    setTimeout("changeOpacity('main_contents',1);", 2500); 
    setTimeout("imgRotateId('main_title_img',540,1,1200);", 1000);setTimeout("imgRotateId('main_title_img',0,2,190);", 2000);
    setTimeout('imgRotateId("gif1",360,1,1000)', 1000);setTimeout('imgRotateId("gif1",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif2",-180,1,1000)', 1000);setTimeout('imgRotateId("gif2",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif3",720,1,1000)', 1000);setTimeout('imgRotateId("gif3",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif4",-0,1,1000)', 1000);setTimeout('imgRotateId("gif4",0,2,500)', 2000);
    setTimeout('imgRotateId("gif5",420,1,1000)', 1000);setTimeout('imgRotateId("gif5",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif6",-200,1,1000)', 1000);setTimeout('imgRotateId("gif6",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif7",50,1,1000)', 1000);setTimeout('imgRotateId("gif7",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif8",-260,1,1000)', 1000);setTimeout('imgRotateId("gif8",0,2,500)', 2000); 
    setTimeout('imgRotateId("gif9",180,1,1000)', 1000);setTimeout('imgRotateId("gif9",0,2,500)', 2000);
}

// 메인의 이미지들 떨어지는 함수
function mainImgDown(){
    imgRotateId('main_title_img',180,1,190); setTimeout("imgRotateId('main_title_img',200,1,1000);", 1000);
    setTimeout('imgRotateId("gif1",15,2,1000)', 1000); 
    setTimeout('imgRotateId("gif2",30,2,1000)', 1000); 
    setTimeout('imgRotateId("gif3",45,1,1000)', 1000); 
    setTimeout('imgRotateId("gif4",0,2,1000)', 1000);
    setTimeout('imgRotateId("gif5",10,1,1000)', 1000); 
    setTimeout('imgRotateId("gif6",25,2,1000)', 1000); 
    setTimeout('imgRotateId("gif7",35,1,1000)', 1000); 
    setTimeout('imgRotateId("gif8",10,2,1000)', 1000); 
    setTimeout('imgRotateId("gif9",5,1,1000)', 1000);
    setTimeout("changeOpacity('main_contents',0);", 1000); 
    setTimeout('displayNone("main_contents", "none");',1800);
    unDownImg = false;
}

// 상위 고정 버튼들
// condition 버튼!

// condition 창 제어 함수
function fn_showCon(){
    let b_con = document.getElementById('b_condition');
    b_con.style['width'] = '1280px'
    b_con.style['height'] = '640px'
    b_con.style['top'] = '16%'
    b_con.style['right'] = '15%'
    b_con.style['transition-duration'] = '2s'
    changeOpacity('b_condition', 1)
    
}
// condition 창 닫기
function fn_closeCon(){
    let b_con = document.getElementById('b_condition');
    b_con.style['width'] = '10px'
    b_con.style['height'] = '10px'
    b_con.style['top'] = '0%'
    b_con.style['right'] = '25%'
    b_con.style['transition-duration'] = '2s'
    changeOpacity('b_condition', 0)
}
// condition 버튼 클릭
function fn_condition(){
    mainImgDown();
    setTimeout('displayNone("b_condition", "block");', 1700);
    setTimeout('fn_showCon()',1800);
    setTimeout("changeOpacity('c_condition', 1)", 3000);

}

// 상위의 X버튼
function fn_exit(){
    fn_registeCon()
    comeback_Img();
}

// 입장 버튼
function over_entry(){
    let enter_img = document.getElementById('enter_img');
    enter_img.src = "image/search_white.png";
}

function out_entry(){
    let enter_img = document.getElementById('enter_img');
    enter_img.src = "image/search_black.png";
}

// police 버튼 관련 !!
function fn_policy(){
    mainImgDown();
    setTimeout('displayNone("p_policy", "block");', 1700);
}