const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInputs = document.querySelectorAll(".account-input");

    let user = {
        lastName: accountInputs[0].value,
        firstName: accountInputs[1].value,
        email: accountInputs[2].value,
        password: accountInputs[3].value
    }

    //JSON.stinrgify() -> js객체를 JSON문자열로 변환 (텍스트인데 JSON이다)
    //JSON.parse()     -> JSON문자열을 js객체로 변환

    let ajaxOption = {
        async: false,/* 동기 비동기 처리 */                                               //필수
        type: "post", /* 메소드 타입은 포스트. 회원가입이니까 */                              //필수
        url: "/api/account/register", /* 여기로 요청 날려라 */                             //필수
        contentType: "application/json",                                                //전송 데이터가 json인경우
        data: JSON.stringify(user),                                                     //전송할 데이터가 있으면
        dataType: "json", /* 응답받을 데이터 타입(리턴타입). 오브젝트 객체니까 제이슨 */          //json외 text 등을 사용할 수 있지만 json 사용
        success: (response) => {                                                        //성공시에 실행될 메소드
            alert("회원가입 요청 성공");
        },
        error: (error) => {                                                                //실패시에 실행될 메소드
            alert("회원가입 요청 실패");
            console.log(error.responseJSON);
        }
    }

    $.ajax(ajaxOption);
}