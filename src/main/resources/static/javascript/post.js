window.onload = ()=>{

    //자신의 글일 경우 수정, 삭제 항목 보이기
    let myMenu = document.getElementById("user-menu");
    let myMenuBtn = document.getElementById("update-delete-btn");
    let menuCheck = document.getElementById("menu-check");
    if(myMenu) {
        myMenu.addEventListener("click", () => {
            if (menuCheck.checked == false) {
                myMenuBtn.style.display = "flex";
                menuCheck.checked = true;
            } else {
                myMenuBtn.style.display = "none";
                menuCheck.checked = false;
            }
        })
    }


    // 댓글 모두 숨기기 버튼 클릭 시, 해당 댓글의 대댓글이 닫힘
    let hideCommentsButton = document.getElementsByClassName("comment-reply-hidden");
    let comments = document.getElementsByClassName("comment-reply-list");
    let hiddenCheck = document.getElementsByClassName("hidden-check");

    for(let i = 0; i < hideCommentsButton.length; i++){
        hideCommentsButton[i].addEventListener("click", ()=>{
            if(hiddenCheck[i].checked === false) {
                hiddenCheck[i].checked = true;
                comments[i].style.display = "none";
                hideCommentsButton[i].innerHTML = "<svg style='transform: rotateX(180deg)' xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"currentColor\" aria-hidden=\"true\" class=\"rotate-180 transform h-4 w-4\"><path fill-rule=\"evenodd\" d=\"M11.47 7.72a.75.75 0 011.06 0l7.5 7.5a.75.75 0 11-1.06 1.06L12 9.31l-6.97 6.97a.75.75 0 01-1.06-1.06l7.5-7.5z\" clip-rule=\"evenodd\"></path></svg>댓글 모두 보기";
            } else {
                hiddenCheck[i].checked = false;
                comments[i].style.display = "block";
                hideCommentsButton[i].innerHTML = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"currentColor\" aria-hidden=\"true\" class=\"h-4 w-4\"><path fill-rule=\"evenodd\" d=\"M11.47 7.72a.75.75 0 011.06 0l7.5 7.5a.75.75 0 11-1.06 1.06L12 9.31l-6.97 6.97a.75.75 0 01-1.06-1.06l7.5-7.5z\" clip-rule=\"evenodd\"></path></svg>댓글 모두 숨기기";
            }
        })
    }

    // 댓글 쓰기 버튼 클릭 시, 해당 댓글의 답글 작성란 보여주기
    let writeCommentButton = document.getElementsByClassName("comment-reply-add");
    let replyForm = document.getElementsByClassName("comment-reply-write");
    let replyCheck = document.getElementsByClassName("reply-check")

    for(let i = 0; i < writeCommentButton.length; i++){
        writeCommentButton[i].addEventListener("click", ()=>{
            if(replyCheck[i].checked === false) {
                replyForm[i].style.display = "block";
                replyCheck[i].checked = true;
                writeCommentButton[i].innerHTML = "댓글 취소"
            } else {
                replyForm[i].style.display = "none"
                replyCheck[i].checked = false;
                writeCommentButton[i].innerHTML = "댓글 쓰기"
            }
        })
    }

    // 댓글 박스
    $('#summernote').summernote({
        placeholder: '내용을 작성하세요',
        height: 100,
        width: 800,
        maxHeight: 100,
        minHeight: 100
    });
    $('.summernote').summernote({
        placeholder: '내용을 작성하세요',
        height: 100,
        maxHeight: 100,
        minHeight: 100
    });

    // 마이페이지
    let myPage = document.getElementsByClassName("mypage");
    let myPageMenu = document.getElementById("mypage-menu");
    let myPageCheck = document.getElementById("mypage-check");

    myPage[0].addEventListener("click", ()=>{
        if(myPageCheck.checked === false) {
            myPageMenu.style.visibility = "visible";
            myPageCheck.checked = true;
        }
        else {
            myPageMenu.style.visibility = "hidden";
            myPageCheck.checked = false;
        }
    })

    $(".user-menu").each(function (i, btn) {
        btn.addEventListener("click", function () {
            console.log(i);
            if($($(".menu-check")[i]).is(":checked") === false) {
                $($(".update-delete-btn")[i]).css("display", "flex");
                $($(".menu-check")[i]).prop("checked", true);
            } else {
                $($(".update-delete-btn")[i]).css("display", "none");
                $($(".menu-check")[i]).prop("checked", false);
            }
        })
    })


    $(".comment-update").each(function (i, btn) {
        btn.addEventListener("click", function () {
            const text = $($(".comment-content-value")[i]).val();
            if($($(".change-edit-mode-check")[i]).is(":checked")) {
                $($(".comment-edit-mode")[i]).css("display", "none");
                $($(".comment-normal-mode")[i]).css("display", "block");
                $($(".change-edit-mode-check")[i]).prop("checked", false);
            } else {
                $($(".comment-edit-mode")[i]).css("display", "block");
                $($(".comment-normal-mode")[i]).css("display", "none");
                $($(".change-edit-mode-check")[i]).prop("checked", true);
                $($(".edit-mode-content")[i]).summernote("code", text);
            }
        })
    })
    $(".cancel-button").each(function (i, btn) {
        btn.addEventListener("click", function () {
            if($($(".change-edit-mode-check")[i]).is(":checked")) {
                $($(".comment-edit-mode")[i]).css("display", "none");
                $($(".comment-normal-mode")[i]).css("display", "block");
                $($(".change-edit-mode-check")[i]).prop("checked", false);
            } else {
                $($(".comment-edit-mode")[i]).css("display", "block");
                $($(".comment-normal-mode")[i]).css("display", "none");
                $($(".change-edit-mode-check")[i]).prop("checked", true);
            }
        })
    })


}