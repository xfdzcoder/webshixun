package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.req.AddHolidayReq;
import com.example.webshixun.dto.req.UpdateHolidayReq;
import com.example.webshixun.dto.resp.HolidayResp;
import com.example.webshixun.entity.Config;
import com.example.webshixun.entity.Holiday;
import com.example.webshixun.service.ConfigService;
import com.example.webshixun.service.HolidayService;

import javax.annotation.Resource;
import java.text.ParseException;

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
     * @param
     * @return
     */
    @GetMapping("/list")
    public Result<Page<Holiday>> listHoliday(@RequestParam("pagenum") Integer pagenum,
                                             @RequestParam("pagesize") Integer pagesize,
                                             @RequestParam("typeId") String typeId,
                                             @RequestParam("status") String status,
                                             @RequestParam("userNo") String userNo) {

        //分页构造器
        Page<Holiday> pageInfo = new Page<>(pagenum, pagesize);
        LambdaQueryWrapper<Holiday> queryWrapper = new LambdaQueryWrapper<Holiday>()
                .eq(! "".equals(typeId), Holiday::getTypeId,typeId)
                .eq(! "".equals(status), Holiday::getStatus,status)
                .eq(! "".equals(userNo),Holiday::getUserNo,userNo);
        Page<Holiday> page = holidayService.page(pageInfo, queryWrapper);
        return Result.success(page, "", page.getTotal());

    }
    /**
     * 获取假期列表
     * @param
     * @return
     */
    @GetMapping("/list1")
    public Result<Page<Holiday>> list1Holiday(@RequestParam("pagenum") Integer pagenum,
                                             @RequestParam("pagesize") Integer pagesize,
                                             @RequestParam("typeId") String typeId,
                                             @RequestParam("status") String status) {

        //分页构造器
        Page<Holiday> pageInfo = new Page<>(pagenum, pagesize);
        LambdaQueryWrapper<Holiday> queryWrapper = new LambdaQueryWrapper<Holiday>()
                .eq(! "".equals(typeId), Holiday::getTypeId,typeId)
                .eq(! "".equals(status), Holiday::getStatus,status);
        Page<Holiday> page = holidayService.page(pageInfo, queryWrapper);
        return Result.success(page, "", page.getTotal());

    }

    /**
     * 新增请假
     *
     * @param addHolidayReq 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> insert(AddHolidayReq addHolidayReq) throws ParseException {

//        Holiday holiday = new Holiday();
//        holiday.setNo(addHolidayReq.getNo());
//        holiday.setUserNo(addHolidayReq.getUserNo());
//        Integer typeId = Integer.valueOf(addHolidayReq.getTypeId());
//        holiday.setTypeId(typeId);
//        holiday.setBz(addHolidayReq.getBz());
//        String startTime = addHolidayReq.getStartTime();
//        String endTime = addHolidayReq.getEndTime();
//        //需要转成的格式
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat format1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
//        //开始时间 和结束时间将中国标准时间格式化为yyyy-MM-dd HH:mm:ss
//        String metBeginTemp = startTime.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800", "GMT+08:00");
//        startTime=format.format(format1.parse(metBeginTemp));
//        String endTimeTemp = endTime.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800", "GMT+08:00");
//        endTime=format.format(format1.parse(endTimeTemp));
//
//        holiday.setStartTime(startTime);
//        holiday.setEndTime(endTime);
//        holiday.setStatus(addHolidayReq.getStatus());
//        holiday.setNoAgree(addHolidayReq.getNoAgree());
//
//        boolean save = holidayService.save(holiday);
//        if (!save) {
//            return Result.error("添加失败");
//        }
//        return Result.success("添加成功");
        // TODO
        return null;
    }


    /**
     * 获取请假基本信息
     * @param id
     * @return
     */
    @GetMapping("/info")
    public Result<HolidayResp> getHolidayDetail(@RequestParam("id") String id) {
        Holiday holiday = holidayService.getById(id);
        Integer typeId = holiday.getTypeId();
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<Config>();
        queryWrapper.eq(Config::getId,typeId);
        Config one = configService.getOne(queryWrapper);
        String name = one.getName();

        HolidayResp holidayResp = new HolidayResp();
        holidayResp.setId(holiday.getId());
        holidayResp.setNo(holiday.getNo());
        holidayResp.setUserNo(holiday.getUserNo());
        holidayResp.setTypeId(name);
        holidayResp.setBz(holiday.getBz());
        // TODO
//        holidayResp.setStartTime(holiday.getStartTime());
//        holidayResp.setEndTime(holiday.getEndTime());
        holidayResp.setStatus(holiday.getStatus());
        holidayResp.setNoAgree(holiday.getNoAgree());

        return Result.success(holidayResp,"");
    }

    /**
     * 修改请假信息
     *
     * @param updateHoliday 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<String> update( UpdateHolidayReq updateHoliday) {
        Holiday holiday = holidayService.getById(updateHoliday.getId());
        holiday.setNo(updateHoliday.getNo());
        holiday.setUserNo(updateHoliday.getUserNo());
//        Integer typeId = Integer.valueOf(updateHoliday.getTypeId());
//        holiday.setTypeId(typeId);
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Config::getName,updateHoliday.getTypeId());
        Config one = configService.getOne(queryWrapper);
        String s = one.getId() + "";
        Integer aLong = Integer.valueOf(s);
        holiday.setTypeId(aLong);
        holiday.setBz(updateHoliday.getBz());
        // TODO
//        holiday.setStartTime(updateHoliday.getStartTime());
//        holiday.setEndTime(updateHoliday.getEndTime());
        holiday.setStatus(updateHoliday.getStatus());

        boolean save = holidayService.updateById(holiday);
        if (!save) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    /**
     * 修改请假信息1
     *
     * @param updateHoliday 实体对象
     * @return 修改结果
     */
    @PutMapping("/update1")
    public Result<String> update1( UpdateHolidayReq updateHoliday) {
        Holiday holiday = holidayService.getById(updateHoliday.getId());
        holiday.setNoAgree(updateHoliday.getNoAgree());

        boolean save = holidayService.updateById(holiday);
        if (!save) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    /**
     * 删除假期
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam("id") Long id) {
        holidayService.removeById(id);
        return Result.success("删除成功");
    }


}

