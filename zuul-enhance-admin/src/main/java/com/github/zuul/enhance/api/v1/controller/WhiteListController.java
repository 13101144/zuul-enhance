package com.github.zuul.enhance.api.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zuul.enhance.annotation.VersionChange;
import com.github.zuul.enhance.dto.IDS;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.dto.WhiteListDto;
import com.github.zuul.enhance.entity.WhiteList;
import com.github.zuul.enhance.service.WhiteListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class WhiteListController {

    private static final Logger log = LoggerFactory.getLogger(WhiteListController.class);

    @Autowired
    private WhiteListService whiteListService;

    /**
     * 更新或新建ip白名单
     *
     * @param whiteListDto
     * @return
     */
    @VersionChange
    @PostMapping("/whitelist/createOrUpdate")
    public ResponseEntity createOrUpdateWhiteList(@RequestBody  WhiteListDto whiteListDto) {
        WhiteList whiteList = null;
        if (whiteListDto.getWhitelistId() != null) {

            whiteList = whiteListService.getOne(new QueryWrapper<WhiteList>()
                    .eq("id", whiteListDto.getWhitelistId()));
        }

        WhiteList whiteListNew = new WhiteList();
        whiteListNew.setId(whiteListDto.getWhitelistId());
        whiteListNew.setIpAddress(whiteListDto.getIpAddress());
        whiteListNew.setStatus(whiteListDto.getStatus());
        whiteListNew.setDescription(whiteListDto.getDesc());

        if (whiteList == null) {
            whiteListNew.setCreateTime(new Date());
            whiteListService.save(whiteListNew);
        } else {
            whiteListNew.setUpdateTime(new Date());
            whiteListService.updateById(whiteListNew);
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
    @DeleteMapping("/whitelist/{id}")
    public ResponseEntity deleteWhiteListById(@PathVariable Integer id) {

        whiteListService.removeById(id);
        return ResponseEntity.okay();
    }

    /**
     * 批量删除id白名单
     *
     * @param ids
     * @return
     */
    @VersionChange
    @PostMapping("/whitelist/delete")
    public ResponseEntity deleteIpWhiteList(@RequestBody IDS ids) {
        String[] idArray = ids.getIds().split(",");
        List<Integer> idList = new ArrayList<>();

        for (String id : idArray) {
            idList.add(Integer.parseInt(id));
        }

        whiteListService.removeByIds(idList);
        return ResponseEntity.okay();
    }

    /**
     * 查询ip白名单
     *
     * @return
     */
    @GetMapping("/whitelist/list")
    public ResponseEntity listWhitelist() {
        List<WhiteList> zuulWhiteList = whiteListService.list();
        return ResponseEntity.okayWithData(zuulWhiteList);
    }


}
