$(function(){
    $("#tab-switch li").click(function(){
        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
    })
});
//提交回复
function postComment() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2Target(questionId, 1, content);
}

function comment2Target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容！");
        return;
    }

    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        dataType: "json",
        success: function (res) {
            if (res.code == 200) {
                window.location.reload();
            } else {
                if (res.code == 2003) {
                    var isAccepted = confirm(res.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f242da64c8b4e8828c85&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(res.message);
                }
            }
        }
    })
}

function comment(commentId) {
    var content = $("#input-" + commentId).val();
    comment2Target(commentId, 2, content);
}

//展开二级评论
function collapseComments(e) {
    var id = $(e).attr("data");
    var comments = $("#comment-" + id);
    if (comments.hasClass("in")) {
        comments.removeClass("in");
        $(e).removeClass("active");
    } else {
        if (comments.children().length != 0) {
            $(e).addClass("active");
            comments.addClass("in");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                var items = '';
                $.each(data.data, function (index, comment) {
                    var dateTime = moment(comment.gmtCreate).format("YYYY-MM-DD");
                    items += '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comments">' +
                        '<div class="media">' +
                        '<div class="media-left">' +
                        '<a href="#">' +
                        '<img class="media-object img-rounded" src="' + comment.user.avatarUrl + '">' +
                        '</a>' +
                        '</div>' +
                        '<div class="media-body">' +
                        '<h5 class="media-heading">' +
                        '<span>' + comment.user.name + '</span>' +
                        '</h5>' +
                        '<div >' + comment.content + '</div>' +
                        '<div class="menu">' +
                        '<span class="pull-right">' + dateTime + '</span>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                });

                items += '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                    '<input type="text" class="form-control" placeholder="评论一下......" id="input-' + id + '"/>' +
                    '<button type="button" class="btn btn-success pull-right" onclick="comment(' + id + ')">评论</button>' +
                    '</div>';
                comments.append(items);
                $(e).addClass("active");
                comments.addClass("in");
            });
        }
    }
}

function selectTag(value) {
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}