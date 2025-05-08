package com.sky.service.impl;

import com.sky.constant.OrderConstant;
import com.sky.context.BaseContext;
import com.sky.dto.OrderDTO;
import com.sky.entity.Employee;
import com.sky.entity.Order;
import com.sky.mapper.EmployeeMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.RoomMapper;
import com.sky.service.EmployeeService;
import com.sky.service.OrderService;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EmployeeMapper employeeMappr;

    /**
     * 订单生成
     * @param orderDTO
     * @return
     */
    public void save(OrderDTO orderDTO) {
        Order order=new Order();
        BeanUtils.copyProperties(orderDTO,order);

        //拿到用户所在的房间号
        log.info("userId:{}",orderDTO.getUserId());
        Long userId= orderDTO.getUserId();
        Long roomId=roomMapper.selectByUserId(userId);
        order.setUserId(userId);
        order.setRoomId(roomId);

        //拿到分配的员工id
        List<Employee> list=new ArrayList<>();
        Random random=new Random();
        if(order.getType().equals("物品")){
            list=employeeMappr.selectByType(OrderConstant.DELIVER);
            if(list!=null && list.size()>0){
                Long[] emp=new Long[list.size()];
                for(int i=0;i<list.size();i++){
                    emp[i]= list.get(i).getId();
                }
                int index=random.nextInt(list.size());
                Long staffId=emp[index];
                order.setStaffId(staffId);
            }
        }else if(order.getType().equals("清洁")){
            list=employeeMappr.selectByType(OrderConstant.CLEAN);
            if(list!=null && list.size()>0){
                Long[] emp=new Long[list.size()];
                for(int i=0;i<list.size();i++){
                    emp[i]= list.get(i).getId();
                }
                int index=random.nextInt(list.size());
                Long staffId=emp[index];
                order.setStaffId(staffId);
            }
        }else if(order.getType().equals("其他")){
            list=employeeMappr.selectByType(OrderConstant.CLEAN);
            if(list!=null && list.size()>0){
                Long[] emp=new Long[list.size()];
                for(int i=0;i<list.size();i++){
                    emp[i]= list.get(i).getId();
                }
                int index=random.nextInt(list.size());
                Long staffId=emp[index];
                order.setStaffId(staffId);
            }
        }else if(order.getType().equals("维修")){
            list=employeeMappr.selectByType(OrderConstant.FIX);
            if(list!=null && list.size()>0){
                Long[] emp=new Long[list.size()];
                for(int i=0;i<list.size();i++){
                    emp[i]= list.get(i).getId();
                }
                int index=random.nextInt(list.size());
                Long staffId=emp[index];
                order.setStaffId(staffId);
            }
        }
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderMapper.insert(order);
    }

    /**
     * 查询用户的订单
     * @param userId
     * @return
     */
    public List<OrderVO> selectOrderByUserId(Long userId) {
        List<OrderVO> list=orderMapper.selectOrderByUserId(userId);
        return list;
    }

    /**
     * 查询员工订单
     * @param staffId
     * @return
     */
    public List<OrderVO> selectOrderByStaffId(Long staffId) {
        List<OrderVO> list=orderMapper.selectOrderByStaffId(staffId);
        return list;
    }

    /**
     * 取消订单
     * @param id
     * @param status
     */
    public void updateStatus(String status,Long id) {
        orderMapper.updateStatus(status,id);
    }


}
