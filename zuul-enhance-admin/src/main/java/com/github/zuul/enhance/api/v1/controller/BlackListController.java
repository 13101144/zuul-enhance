package com.github.zuul.enhance.api.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zuul.enhance.annotation.VersionChange;
import com.github.zuul.enhance.dto.BlackListDto;
import com.github.zuul.enhance.dto.IDS;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.BlackList;
import com.github.zuul.enhance.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class BlackListController {

    private static final Logger log = LoggerFactory.getLogger(BlackListController.class);

    @Autowired
    private BlackListService blackListService;

    /**
     * 更新或新建ip黑名单
     *
     * @param blackListDto
     * @return
     */
    @VersionChange
    @PostMapping("/blacklist/createOrUpdate")
    public ResponseEntity createOrUpdateBlackList(@RequestBody  BlackListDto blackListDto) {

        BlackList blackList = null;
        if (blackListDto.getBlacklistId() != null) {

            blackList = blackListService.getOne(new QueryWrapper<BlackList>()
                    .eq("id", blackListDto.getBlacklistId()));
        }

        BlackList blackListNew = new BlackList();
        blackListNew.setId(blackListDto.getBlacklistId());
        blackListNew.setIpAddress(blackListDto.getIpAddress());
        blackListNew.setStatus(blackListDto.getStatus());
        blackListNew.setDescription(blackListDto.getDesc());

        if (blackList == null) {
            blackListNew.setCreateTime(new Date());
            blackListService.save(blackListNew);
        } else {
            blackListNew.setUpdateTime(new Date());
            blackListService.updateById(blackListNew);
        }

        return ResponseEntity.okay();
    }

    /**
     * 根据id删除白名单
     *
     * @param id
     * @return
     */
    @VersionChange
    @DeleteMapping("/blacklist/{id}")
    public ResponseEntity deleteWhiteListById(@PathVariable Integer id) {

        blackListService.removeById(id);
        return ResponseEntity.okay();
    }

    /**
     * 批量删除id白名单
     *
     * @param ids
     * @return
     */
    @VersionChange
    @PostMapping("/blacklist/delete")
    public ResponseEntity deleteIpBlackList(@RequestBody  IDS ids) {
        String[] idArray = ids.getIds().split(",");
        List<Integer> idList = new ArrayList<>();

        for (String id : idArray) {
            idList.add(Integer.parseInt(id));
        }

        blackListService.removeByIds(idList);
        return ResponseEntity.okay();
    }

    /**
     * 查询ip白名单
     *
     * @return
     */
    @GetMapping("/blacklist/list")
    public ResponseEntity listBlacklist() {
        List<BlackList> zuulBlackList = blackListService.list();
        return ResponseEntity.okayWithData(zuulBlackList);
    }
}
