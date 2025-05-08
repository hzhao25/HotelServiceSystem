package com.sky.mapper;

import com.sky.dto.ReviewDTO;
import com.sky.entity.Order;
import com.sky.vo.OrderVO;
import com.sky.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {
    /**
     * 生成评论
     * @param reviewDTO
     */
    void save(ReviewDTO reviewDTO);

    /**
     * 查询员工评论
     * @param staffId
     * @return
     */
    @Select("select * from review where staff_id=#{staffId}")
    List<ReviewVO> selectByEmployeeId(Long staffId);

    /**
     * 查询用户评论
     * @param userId
     * @return
     */
    @Select("select * from review where user_id=#{userId}")
    List<ReviewVO> selectByUserId(Long userId);
}
