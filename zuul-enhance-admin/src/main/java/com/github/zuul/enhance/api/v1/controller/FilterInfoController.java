package com.github.zuul.enhance.api.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.zuul.enhance.annotation.VersionChange;
import com.github.zuul.enhance.dto.FilterDto;
import com.github.zuul.enhance.dto.IDS;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.FilterInfo;
import com.github.zuul.enhance.service.FilterInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class FilterInfoController {

    private static final Logger log = LoggerFactory.getLogger(FilterInfoController.class);

    @Autowired
    private FilterInfoService filterInfoService;

    /**
     * 更新或新建过滤器
     *
     * @param filterDto
     * @return
     */
    @VersionChange
    @PostMapping("/filter/createOrUpdate")
    public ResponseEntity createOrUpdateFilter(@RequestBody FilterDto filterDto) {
        FilterInfo filterInfo = null;
        if (filterDto.getFilterId() != null) {
            filterInfo = filterInfoService.getOne(new QueryWrapper<FilterInfo>()
                    .eq("id", filterDto.getFilterId()));
        }

        FilterInfo filterInfoNew = new FilterInfo();
        filterInfoNew.setId(filterDto.getFilterId());
        filterInfoNew.setFilterName(filterDto.getFilterName());
        filterInfoNew.setStatus(filterDto.getStatus());
        filterInfoNew.setDescription(filterDto.getDesc());

        if (filterInfo == null) {
            filterInfoNew.setCreateTime(new Date());
            filterInfoService.save(filterInfoNew);
        } else {
            filterInfoNew.setUpdateTime(new Date());
            filterInfoService.updateById(filterInfoNew);
        }

        return ResponseEntity.okay();
    }

    /**
     * 根据id删除过滤器
     *
     * @param id
     * @return
     */
    @VersionChange
    @DeleteMapping("/filter/{id}")
    public ResponseEntity deleteFilterById(@PathVariable String id) {

        filterInfoService.removeById(id);
        return ResponseEntity.okay();
    }

    /**
     * 批量删除id白名单
     *
     * @param ids
     * @return
     */
    @VersionChange
    @DeleteMapping("/filter/delete")
    public ResponseEntity deleteFilterList(@RequestBody IDS ids) {
        String[] idArray = ids.getIds().split(",");
        List<Integer> idList = new ArrayList<>();

        for (String id : idArray) {
            idList.add(Integer.parseInt(id));
        }

        filterInfoService.removeByIds(idList);
        return ResponseEntity.okay();
    }

    /**
     * 查询ip白名单
     *
     * @return
     */
    @GetMapping("/filter/list")
    public ResponseEntity listFilter() {
        List<FilterInfo> zuulFilterList = filterInfoService.list();
        return ResponseEntity.okayWithData(zuulFilterList);
    }

}
