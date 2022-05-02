package com.itheima.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired  //注入菜品
    private DishService dishService;

    @Autowired  //注入套餐
    private SetmealService setmealService;

    @Override
    //根据
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);

        int count1 = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类关联菜品没 关联的话 抛出异常
        System.out.println(count1);
        if (count1 > 0){
            throw new CustomException("当前分类关联菜品 不能删除");
        }
        //查询当前分类关联套餐没 关联的话 抛出异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        //System.out.println(count2);
        if (count2 > 0){
            throw new CustomException("当前分类关联套餐 不能删除");
        }

        //正常删除
        super.removeById(id);
    }
}
