package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Config;
import com.example.webshixun.entity.Holiday;
import com.example.webshixun.service.ConfigService;
import com.example.webshixun.service.HolidayService;
import com.example.webshixun.vo.req.AddHolidayReq;
import com.example.webshixun.vo.req.UpdateHolidayReq;
import com.example.webshixun.vo.resp.HolidayResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("holiday")
public class HolidayController {

    /**
     * 服务对象
     */
    @Resource
    private HolidayService holidayService;

    @Resource
    private ConfigService configService;

    /**
     * 获取假期列表
     */
    @GetMapping("/list")
    public Result<List<Holiday>> listHoliday() {
        List<Holiday> list = holidayService.list();
        return Result.success(list, "");
    }

    /**
     * 新增请假
     *
     * @param req 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<String> insert(@RequestBody AddHolidayReq req) throws ParseException {

        Holiday holiday = new Holiday();
        holiday.setNo(req.getNo());
        holiday.setUserNo(req.getUserNo());
        Integer typeId = Integer.valueOf(req.getTypeId());
        holiday.setTypeId(typeId);
        holiday.setBz(req.getBz());
        holiday.setStartTime(req.getStartTime());
        holiday.setEndTime(req.getEndTime());
        holiday.setStatus(req.getStatus());
        holiday.setNoAgree(req.getNoAgree());
        holiday.setCreateTime(LocalDateTime.now());
        holidayService.save(holiday);
        return Result.success("添加成功");
    }


    /**
     * 获取请假基本信息
     */
    @GetMapping("/{id}")
    public Result<HolidayResp> getHolidayDetail(@PathVariable("id") Long id) {
        Holiday holiday = holidayService.getById(id);

        HolidayResp holidayResp = new HolidayResp();
        holidayResp.setId(holiday.getId());
        holidayResp.setNo(holiday.getNo());
        holidayResp.setUserNo(holiday.getUserNo());
        holidayResp.setTypeId(holiday.getTypeId());
        holidayResp.setBz(holiday.getBz());
        holidayResp.setStartTime(holiday.getStartTime());
        holidayResp.setEndTime(holiday.getEndTime());
        holidayResp.setStatus(holiday.getStatus());
        holidayResp.setNoAgree(holiday.getNoAgree());

        return Result.success(holidayResp,"");
    }

    /**
     * 修改请假信息
     */
    @PutMapping
    public Result<String> update(@RequestBody UpdateHolidayReq updateHoliday) {
        Holiday holiday = holidayService.getById(updateHoliday.getId());
        holiday.setNo(updateHoliday.getNo());
        holiday.setUserNo(updateHoliday.getUserNo());
        holiday.setTypeId(updateHoliday.getTypeId());
        holiday.setBz(updateHoliday.getBz());
        holiday.setStartTime(updateHoliday.getStartTime());
        holiday.setEndTime(updateHoliday.getEndTime());
        holiday.setStatus(updateHoliday.getStatus());
        holidayService.updateById(holiday);
        return Result.success("修改成功");
    }

    /**
     * 删除假期
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        holidayService.removeById(id);
        return Result.success("删除成功");
    }

}

