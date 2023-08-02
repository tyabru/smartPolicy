package com.jingyu.community.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jingyu.common.core.domain.Cascader;
import com.jingyu.common.core.domain.TreeSelect;
import com.jingyu.common.exception.CustomException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.community.domain.StructureImportVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityStructureMapper;
import com.jingyu.community.domain.CommunityStructure;
import com.jingyu.community.service.ICommunityStructureService;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

/**
 * 小区房屋结构和地址信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-17
 */
@Service
@RequiredArgsConstructor
public class CommunityStructureServiceImpl implements ICommunityStructureService 
{
    private final CommunityStructureMapper communityStructureMapper;
    private final JdbcTemplate jdbcTemplate;

    /**
     * 查询小区房屋结构和地址信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 小区房屋结构和地址信息
     */
    @Override
    public CommunityStructure selectCommunityStructureById(Long id)
    {
        return communityStructureMapper.selectCommunityStructureById(id);
    }

    /**
     * 查询小区房屋结构和地址信息列表
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 小区房屋结构和地址信息
     */
    @Override
    public List<CommunityStructure> selectCommunityStructureList(CommunityStructure communityStructure)
    {
        return communityStructureMapper.selectCommunityStructureList(communityStructure);
    }

    /**
     * 新增小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    @Override
    public int insertCommunityStructure(CommunityStructure communityStructure)
    {
        return communityStructureMapper.insertCommunityStructure(communityStructure);
    }

    /**
     * 修改小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    @Override
    public int updateCommunityStructure(CommunityStructure communityStructure)
    {
        return communityStructureMapper.updateCommunityStructure(communityStructure);
    }

    /**
     * 批量删除小区房屋结构和地址信息
     * 
     * @param ids 需要删除的小区房屋结构和地址信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityStructureByIds(Long[] ids)
    {
        return communityStructureMapper.deleteCommunityStructureByIds(ids);
    }

    /**
     * 删除小区房屋结构和地址信息信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityStructureById(Long id)
    {
        return communityStructureMapper.deleteCommunityStructureById(id);
    }

    @Override
    @Transactional
    public int batchInsert(List<StructureImportVo> list) {
        int size = list.size();
        int maxSend = 5000;
        int limit = size/maxSend + (size % maxSend > 0? 1: 0);
        AtomicInteger count = new AtomicInteger();
        Stream.iterate(0, n->n+1).limit(limit).forEach(a->{
            List<StructureImportVo> sendList = list.stream()
                    .skip(a*maxSend).limit(maxSend).parallel().collect(Collectors.toList());
            System.out.printf("执行下标：%s, 此次执行条数：%s, a*maxSend = %s, maxSend = %s\n", a, sendList.size(), a*maxSend, maxSend);
            if(sendList.size() > 0) {
                try {
                    count.addAndGet(communityStructureMapper.batchInsertFormVo(sendList));
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (message.contains("Duplicate entry")) {
                        Pattern p = Pattern.compile("'([^']+)'\\s+for key '([^']+)'");
                        Matcher m = p.matcher(message);
                        if(m.find()) {
                            message = "重复的地址编码【"+m.group(1)+"】";
                            throw new CustomException(message);
                        }
                    }
                    throw new RuntimeException(e);
                }

            }
        });
        return count.get();
    }

    @Override
    public List<Map<String, Object>> selectCommunityList(String queryString) {
        return communityStructureMapper.selectCommunityList(queryString);
    }

    @Override
    public List<Cascader<CommunityStructure>> querySelectorList(CommunityStructure structure) {
        Map<String, Object> map = new HashMap<>();
        List<CommunityStructure> list = communityStructureMapper.selectRoomListByCommunityCode(structure);
        List<Cascader<CommunityStructure>> treeList = new ArrayList<>();
        for (CommunityStructure item : list) {
            List<String> values = parseCascaderIds(item);
            List<Cascader<CommunityStructure>> filterItem = treeList.stream()
                    .filter(aa -> aa.getLabel().equals(item.getDong()))
                    .collect(Collectors.toList());
            if(filterItem.size() < 1) {
                treeList.add(Cascader.parseCascader(values, item.getMetaAddrId(), item));
            } else {
                filterItem.get(0).addChildren(values.subList(1, values.size()), item.getMetaAddrId(), item);
            }
        }
        return treeList;
    }

    public List<String> parseCascaderIds(CommunityStructure structure){
        List<String> list = new ArrayList<>();
        list.add(structure.getDong());
        if(StringUtils.isNotEmpty(structure.getUnit())) {
            list.add(structure.getUnit());
        } else if(StringUtils.isNotEmpty(structure.getCeng())) {
            list.add(structure.getCeng());
        }
        list.add(structure.getRoom());
        return list;
    }
}
