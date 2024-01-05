package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Holiday;
import com.example.webshixun.mapper.HolidayMapper;
import com.example.webshixun.service.HolidayService;
import org.springframework.stereotype.Service;


@Service("holidayService")
public class HolidayServiceImpl extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {

}

