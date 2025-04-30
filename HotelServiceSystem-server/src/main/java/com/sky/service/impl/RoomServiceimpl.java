package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.constant.GenerateUrlConstant;
import com.sky.dto.RoomDTO;
import com.sky.dto.RoomPageQueryDTO;
import com.sky.entity.Room;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.UrlGenerateFailException;
import com.sky.mapper.DoNotDisturbMapper;
import com.sky.mapper.RoomMapper;
import com.sky.result.PageResult;
import com.sky.service.RoomService;
import com.sky.utils.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoomServiceimpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private QrCodeUtil qrCodeUtil;

    /**
     * 添加客房信息
     * @param roomDTO
     */
    public void save(RoomDTO roomDTO) {
        Room room=new Room();
        BeanUtils.copyProperties(roomDTO,room);
        roomMapper.insert(room);

        String qrCodeImgUrl=qrCodeUtil.getminiqrQr(room.getId());
        if(qrCodeImgUrl!=null){
            room.setQrCode(qrCodeImgUrl);
            roomMapper.update(room);
        }else{
            throw new UrlGenerateFailException(GenerateUrlConstant.GENERATE_URL_FALI);
        }
    }

    /**
     * 客房分页查询
     * @param roomPageQueryDTO
     * @return
     */
    public PageResult pageQuery(RoomPageQueryDTO roomPageQueryDTO) {
        PageHelper.startPage(roomPageQueryDTO.getPage(), roomPageQueryDTO.getPageSize());

        Page<Room> page=roomMapper.pageQuery(roomPageQueryDTO);

        Long total=page.getTotal();

        List<Room> records=page.getResult();

        return new PageResult(total,records);
    }

    /**
     * 根据id查询客房信息
     * @param id
     * @return
     */
    public Room getById(Integer id) {
        Room room = roomMapper.getById(id);
        return room;
    }

    /**
     * 修改客房信息
     * @param roomDTO
     */
    public void update(RoomDTO roomDTO) {
        Room room=new Room();
        BeanUtils.copyProperties(roomDTO,room);
        roomMapper.update(room);
    }

    /**
     * 售卖停售客房
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Integer id) {
        if(status==StatusConstant.ENABLE){
            roomMapper.updateUserIdNull(id);
        }else{
            roomMapper.updateUserIdNotNull(id);
        }
        Room room=Room.builder()
                .id(id)
                .status(status)
                .build();
        roomMapper.update(room);
    }

    /**
     * 删除客房
     * @param id
     */
    public void deleteById(Integer id) {
        Room room=roomMapper.getById(id);
        if(room.getStatus()== StatusConstant.DISABLE){
            throw new DeletionNotAllowedException(MessageConstant.ROOM_ON_USE);
        }

        roomMapper.deleteById(id);
    }
}
