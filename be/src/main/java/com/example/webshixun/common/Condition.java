package com.example.webshixun.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


@Data
public abstract class Condition<T> {

    protected Integer pageNum = 1;

    protected Integer pageSize = 10;

    public Page<T> getPage() {
        return new Page<>(pageNum, pageSize);
    }

    public LambdaQueryWrapper<T> getLambdaWrapper() {
        return new LambdaQueryWrapper<>();
    }
}
