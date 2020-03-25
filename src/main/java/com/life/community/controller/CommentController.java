package com.life.community.controller;

import com.life.community.dto.CommentCreateDTO;
import com.life.community.dto.CommentDTO;
import com.life.community.dto.ResultDTO;
import com.life.community.enums.CommentTypeEnum;
import com.life.community.exception.CustomizeErrorCode;
import com.life.community.model.Comment;
import com.life.community.model.User;
import com.life.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * Description: 回复和评论问题
 * </p>
 *
 * @author zp
 * @version v1.0.0
 * @date 2020/3/21
 * @see com.life.community.controller
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO postComment(@RequestBody CommentCreateDTO commentCreateDTO,
                                 HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        comment.setCommentator(user.getId());
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    /**
     * 二级评论
     *
     * @param id 回复ID
     */
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
