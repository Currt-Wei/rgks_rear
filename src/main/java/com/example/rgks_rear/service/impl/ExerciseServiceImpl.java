package com.example.rgks_rear.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgks_rear.dto.SaveExerciseDTO;
import com.example.rgks_rear.mapper.ExerciseMapper;
import com.example.rgks_rear.pojo.Exercise;
import com.example.rgks_rear.pojo.User;
import com.example.rgks_rear.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 面试题表 服务实现类
 * </p>
 *
 * @author 软工课设车队！
 * @since 2021-05-15
 */
@Service
public class ExerciseServiceImpl extends ServiceImpl<ExerciseMapper, Exercise> implements IExerciseService {

    public boolean save(Exercise exercise){
        Exercise curr= lambdaQuery().eq(Exercise::getExerciseId, exercise.getExerciseId()).one();
        if (curr!=null){
            int i = lambdaUpdate().getBaseMapper().updateById(exercise);
            if(i==0){
                log.error("Exercise is deleted before save");
                return false;
            }
        }else{
            int i = lambdaUpdate().getBaseMapper().insert(exercise);
            return i>0;
        }
        return true;
    }

    public Exercise queryById(Long exerciseId){
        Exercise res=lambdaQuery().eq(Exercise::getExerciseId,exerciseId).eq(Exercise::getIsDeleted,0).one();
        if(res!=null){
            log.debug("查询成功");
            return res;
        }else{
            return null;
        }
    }
}
