package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.ConsumeDTO;
import com.sky.dto.ConsumePageQueryDTO;
import com.sky.entity.Consume;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.ConsumeMapper;
import com.sky.result.PageResult;
import com.sky.service.ConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsumeServiceimpl implements ConsumeService {
    @Autowired
    private ConsumeMapper consumeMapper;

    /**
     * 添加消耗品信息
     * @param consumeDTO
     */
    public void save(ConsumeDTO consumeDTO) {
        Consume consume=new Consume();
        BeanUtils.copyProperties(consumeDTO,consume);

        consumeMapper.insert(consume);
    }

    /**
     * 消耗品分页查询
     * @param consumePageQueryDTO
     * @return
     */
    public PageResult pageQuery(ConsumePageQueryDTO consumePageQueryDTO) {
        PageHelper.startPage(consumePageQueryDTO.getPage(), consumePageQueryDTO.getPageSize());

        Page<Consume> page=consumeMapper.pageQuery(consumePageQueryDTO);

        Long total=page.getTotal();

        List<Consume> records=page.getResult();

        return new PageResult(total,records);
    }

    /**
     * 根据id查询消耗品信息
     * @param id
     * @return
     */
    public Consume getById(Integer id) {
        Consume consume = consumeMapper.getById(id);
        return consume;
    }

    /**
     * 修改消耗品信息
     * @param consumeDTO
     */
    public void update(ConsumeDTO consumeDTO) {
        Consume consume=new Consume();
        BeanUtils.copyProperties(consumeDTO,consume);
        consumeMapper.update(consume);
    }

    /**
     * 售卖停售消耗品
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Integer id) {
        Consume consume=Consume.builder()
                .id(id)
                .status(status)
                .build();
        consumeMapper.update(consume);
    }

    /**
     * 删除消耗品
     * @param id
     */
    public void deleteById(Integer id) {
        Consume consume=consumeMapper.getById(id);
        if(consume.getStatus()== StatusConstant.ENABLE){
            throw new DeletionNotAllowedException(MessageConstant.CONSUME_ON_SALE);
        }
        consumeMapper.deleteById(id);
    }
}
