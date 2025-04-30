package com.sky.controller.admin;

import com.sky.dto.ConsumeDTO;
import com.sky.dto.ConsumePageQueryDTO;
import com.sky.entity.Consume;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ConsumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/consume")
@Api(tags = "消耗品相关接口")
@Slf4j
public class ConsumeController {

    @Autowired
    private ConsumeService consumeService;

    /**
     * 添加消耗品
     * @param consumeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加消耗品")
    public Result save(@RequestBody ConsumeDTO consumeDTO){
        log.info("添加的消耗品信息：{}",consumeDTO);
        consumeService.save(consumeDTO);
        return Result.success();
    }

    /**
     * 消耗品分页查询
     * @param consumePageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("消耗品分页查询")
    public Result<PageResult> pageQuery(ConsumePageQueryDTO consumePageQueryDTO){
        log.info("消耗品分页查询：{}",consumePageQueryDTO);

        PageResult pageResult=consumeService.pageQuery(consumePageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 根据id查询消耗品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询消耗品信息")
    public Result<Consume> getById(@PathVariable Integer id){
        Consume consume=consumeService.getById(id);
        return Result.success(consume);
    }

    /**
     * 修改消耗品信息
     * @param consumeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改消耗品信息")
    public Result update(@RequestBody ConsumeDTO consumeDTO){
        log.info("修改消耗品信息：{}",consumeDTO);

        consumeService.update(consumeDTO);

        return Result.success();
    }

    /**
     * 售卖停售消耗品
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("售卖停售消耗品")
    public Result startOrStop(@PathVariable Integer status,Integer id){
        log.info("售卖停售消耗品：{},{}",status,id);
        consumeService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据id删除消耗品
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除消耗品")
    public Result<String> deleteById(Integer id){
        log.info("删除消耗品：{}",id);
        consumeService.deleteById(id);
        return Result.success();
    }
}
