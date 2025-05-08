package com.sky.controller.user;

import com.sky.dto.ReviewDTO;
import com.sky.result.Result;
import com.sky.service.ReviewService;
import com.sky.vo.ReviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/review")
@Api(tags = "C端评论相关接口")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * C端生成评论
     * @param reviewDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("C端生成评论")
    public Result save(ReviewDTO reviewDTO){
        reviewService.save(reviewDTO);
        return Result.success();
    }

    @GetMapping("/selectEmployee")
    @ApiOperation("C端员工评论查询")
    public Result<List<ReviewVO>> selectEmployee(Long staffId){
        List<ReviewVO> list=reviewService.selectByEmployeeId(staffId);
        return Result.success(list);
    }

    @GetMapping("/selectUser")
    @ApiOperation("C端用户评论查询")
    public Result<List<ReviewVO>> selectUser(Long userId){
        List<ReviewVO> list=reviewService.selectByUserId(userId);
        return Result.success(list);
    }
}
