package com.sky.controller.admin;

import com.sky.dto.RoomDTO;
import com.sky.dto.RoomPageQueryDTO;
import com.sky.entity.Room;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/room")
@Api(tags = "客房相关接口")
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 添加客房
     * @param roomDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加客房")
    public Result save(@RequestBody RoomDTO roomDTO){
        log.info("添加的客房信息：{}",roomDTO);
        roomService.save(roomDTO);
        return Result.success();
    }

    /**
     * 客房分页查询
     * @param roomPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("客房分页查询")
    public Result<PageResult> pageQuery(RoomPageQueryDTO roomPageQueryDTO){
        log.info("客房分页查询：{}",roomPageQueryDTO);

        PageResult pageResult=roomService.pageQuery(roomPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 根据id查询客房信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询客房信息")
    public Result<Room> getById(@PathVariable Integer id){
        Room room=roomService.getById(id);
        return Result.success(room);
    }

    /**
     * 修改客房信息
     * @param roomDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改客房信息")
    public Result update(@RequestBody RoomDTO roomDTO){
        log.info("修改客房信息：{}",roomDTO);

        roomService.update(roomDTO);

        return Result.success();
    }

    /**
     * 售卖停售客房
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("售卖停售客房")
    public Result startOrStop(@PathVariable Integer status,Integer id){
        log.info("售卖停售客房：{},{}",status,id);
        roomService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据id删除客房
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除客房")
    public Result<String> deleteById(Integer id){
        log.info("删除客房：{}",id);
        roomService.deleteById(id);
        return Result.success();
    }
}
