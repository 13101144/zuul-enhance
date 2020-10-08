package com.github.zuul.enhance.api.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.zuul.enhance.annotation.VersionChange;
import com.github.zuul.enhance.dto.IDS;
import com.github.zuul.enhance.dto.RateLimitDto;
import com.github.zuul.enhance.dto.ResponseEntity;

import com.github.zuul.enhance.entity.RateLimit;
import com.github.zuul.enhance.entity.WhiteList;
import com.github.zuul.enhance.service.RateLimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RateLimitController {

    private static final Logger log = LoggerFactory.getLogger(RateLimitController.class);

    @Autowired
    private RateLimitService rateLimitService;

    /**
     * 新建或者更新限流规则
     *
     * @param rateLimitDto
     * @return
     */
    @VersionChange
    @PostMapping("/ratelimit/createOrUpdate")
    public ResponseEntity createOrUpdateRateLimit(@RequestBody RateLimitDto rateLimitDto) {

        RateLimit rateLimit = null;

        if (rateLimitDto.getRatelimitId() != null) {
            rateLimit = rateLimitService.getOne(new QueryWrapper<RateLimit>()
                    .eq("id", rateLimitDto.getRatelimitId()));
        }

        RateLimit rateLimitNew = new RateLimit();
        rateLimitNew.setRouteId(rateLimitDto.getRouteId());
        rateLimitNew.setId(rateLimitDto.getRatelimitId());
        rateLimitNew.setRate(rateLimitDto.getRate());

        if (rateLimit == null) {
            rateLimitNew.setCreateTime(new Date());
            rateLimitService.save(rateLimitNew);
        } else {
            rateLimitNew.setUpdateTime(new Date());
            rateLimitService.updateById(rateLimitNew);
        }

        return ResponseEntity.okay();
    }

    /**
     * 根据id删除限流规则
     *
     * @param id
     * @return
     */
    @VersionChange
    @DeleteMapping("/ratelimit/{id}")
    public ResponseEntity deleteRateLimitById(@PathVariable String id) {

        rateLimitService.removeById(id);
        return ResponseEntity.okay();
    }

    /**
     * 批量删除限流规则
     *
     * @param ids
     * @return
     */
    @VersionChange
    @DeleteMapping("/ratelimit/delete")
    public ResponseEntity deleteRateLimitList(@RequestBody IDS ids) {

        String[] idArray = ids.getIds().split(",");
        List<Integer> idList = new ArrayList<>();

        for (String id : idArray) {
            idList.add(Integer.parseInt(id));
        }

        rateLimitService.removeByIds(idList);
        return ResponseEntity.okay();
    }



    /**
     * 查询限流规则
     *
     * @return
     */
    @GetMapping("/ratelimit/list")
    public ResponseEntity listRateLimitlist() {
        List<RateLimit> rateLimitList = rateLimitService.list();
        return ResponseEntity.okayWithData(rateLimitList);
    }


}
