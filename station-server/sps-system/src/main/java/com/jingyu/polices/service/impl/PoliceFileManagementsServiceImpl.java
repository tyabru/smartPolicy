package com.jingyu.polices.service.impl;

import java.io.File;
import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceFileManagementsMapper;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.service.IPoliceFileManagementsService;

import javax.annotation.Resource;

/**
 * 日常文件管理Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceFileManagementsServiceImpl implements IPoliceFileManagementsService 
{
    @Value("${jingyu.profile}")
    private String filePath;

    @Resource
    private PoliceFileManagementsMapper policeFileManagementsMapper;

    /**
     * 查询日常文件管理
     * 
     * @param id 日常文件管理主键
     * @return 日常文件管理
     */
    @Override
    public PoliceFileManagements selectPoliceFileManagementsById(Long id)
    {
        return policeFileManagementsMapper.selectPoliceFileManagementsById(id);
    }

    /**
     * 查询日常文件管理列表
     * 
     * @param policeFileManagements 日常文件管理
     * @return 日常文件管理
     */
    @Override
    public List<PoliceFileManagements> selectPoliceFileManagementsList(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.selectPoliceFileManagementsList(policeFileManagements);
    }

    /**
     * 新增日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    @Override
    public int insertPoliceFileManagements(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.insertPoliceFileManagements(policeFileManagements);
    }

    /**
     * 修改日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    @Override
    public int updatePoliceFileManagements(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.updatePoliceFileManagements(policeFileManagements);
    }

    /**
     * 批量删除日常文件管理
     * 
     * @param ids 需要删除的日常文件管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceFileManagementsByIds(Long[] ids)
    {
        for (Long id : ids) {
            //删除文件
            deleteFile(id);
        }
        return policeFileManagementsMapper.deletePoliceFileManagementsByIds(ids);
    }

    /**
     * 删除日常文件管理信息
     * 
     * @param id 日常文件管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceFileManagementsById(Long id)
    {
        //删除文件
        deleteFile(id);
        return policeFileManagementsMapper.deletePoliceFileManagementsById(id);
    }

    /**
     * 删除文件
     * */
    public void deleteFile(Long id) {
        PoliceFileManagements policeFileManagements = policeFileManagementsMapper.selectPoliceFileManagementsById(id);
        String fileUrlPath = filePath + policeFileManagements.getFilePath();
        File file = new File(fileUrlPath);
        if (file.exists()) {
            file.delete();
        } else {
            throw new ClassCastException("文件不存在,请确定路径是否正确!");
        }
    }
}
