package com.jingyu.contradiction.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.mapper.PoliceFileManagementsMapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.contradiction.mapper.ContradictionTanglesMapper;
import com.jingyu.contradiction.domain.ContradictionTangles;
import com.jingyu.contradiction.service.IContradictionTanglesService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 矛盾纠纷基本信息Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Service
public class ContradictionTanglesServiceImpl implements IContradictionTanglesService 
{
    @Resource
    private ContradictionTanglesMapper contradictionTanglesMapper;

    @Resource
    private PoliceFileManagementsMapper policeFileManagementsMapper;

    /**
     * 查询矛盾纠纷基本信息
     * 
     * @param id 矛盾纠纷基本信息主键
     * @return 矛盾纠纷基本信息
     */
    @Override
    public ContradictionTangles selectContradictionTanglesById(Long id)
    {
        return contradictionTanglesMapper.selectContradictionTanglesById(id);
    }

    /**
     * 查询矛盾纠纷基本信息列表
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 矛盾纠纷基本信息
     */
    @Override
    public List<ContradictionTangles> selectContradictionTanglesList(ContradictionTangles contradictionTangles)
    {
        return contradictionTanglesMapper.selectContradictionTanglesList(contradictionTangles);
    }

    /**
     * 新增矛盾纠纷基本信息
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertContradictionTangles(ContradictionTangles contradictionTangles)
    {
        int i = contradictionTanglesMapper.insertContradictionTangles(contradictionTangles);
        if (i > 0) {
            //文件数据处理
            PoliceFileManagements policeFileManagements = fileDataHandle(contradictionTangles);
            //新增纠纷文件
            int j = policeFileManagementsMapper.insertPoliceFileManagements(policeFileManagements);
            if (j > 0) {
                //查询新增的文件
                List<PoliceFileManagements> policeFileManagements1 = policeFileManagementsMapper.selectPoliceFileManagementsList(policeFileManagements);
                //查询矛盾纠纷信息
                ContradictionTangles contradictionByCaseCode = contradictionTanglesMapper.getContradictionByCaseCode(contradictionTangles.getCaseCode());
                ContradictionTangles contradictionTangles1 = new ContradictionTangles();
                contradictionTangles1.setId(contradictionByCaseCode.getId());
                contradictionTangles1.setFileId(policeFileManagements1.get(0).getId());
                //回填文件ID
                contradictionTanglesMapper.updateContradictionTangles(contradictionTangles1);
            }
        }
        return i;
    }

    /**
     * 修改矛盾纠纷基本信息
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateContradictionTangles(ContradictionTangles contradictionTangles)
    {
        int i = contradictionTanglesMapper.updateContradictionTangles(contradictionTangles);
        if (i > 0) {
            //修改文件
            policeFileManagementsMapper.updatePoliceFileManagements(fileDataHandle(contradictionTangles));
        }
        return i;
    }

    /**
     * 纠纷文件数据处理
     * */
    public PoliceFileManagements fileDataHandle(ContradictionTangles contradictionTangles) {
        PoliceFileManagements policeFileManagements = new PoliceFileManagements();
        if (StringUtils.isNotNull(contradictionTangles.getId()) && contradictionTangles.getId() > 0) {
            policeFileManagements.setId(contradictionTangles.getFileId());
        }
        policeFileManagements.setDeptId(contradictionTangles.getDeptId());
        policeFileManagements.setFolderName(contradictionTangles.getCaseCode());
        policeFileManagements.setFileName(contradictionTangles.getFileName());
        policeFileManagements.setFilePath(contradictionTangles.getFilePath());
        policeFileManagements.setFileType(0L);
        return policeFileManagements;
    }

    /**
     * 批量删除矛盾纠纷基本信息
     * 
     * @param ids 需要删除的矛盾纠纷基本信息主键
     * @return 结果
     */
    @Override
    public int deleteContradictionTanglesByIds(Long[] ids)
    {
        return contradictionTanglesMapper.deleteContradictionTanglesByIds(ids);
    }

    /**
     * 删除矛盾纠纷基本信息信息
     * 
     * @param id 矛盾纠纷基本信息主键
     * @return 结果
     */
    @Override
    public int deleteContradictionTanglesById(Long id)
    {
        return contradictionTanglesMapper.deleteContradictionTanglesById(id);
    }

    /**
     * 查询矛盾纠纷基本信息
     *
     * @param caseCode 矛盾纠纷案件编码
     * @return 矛盾纠纷基本信息
     */
    @Override
    public ContradictionTangles getContradictionByCaseCode(String caseCode) {
        return contradictionTanglesMapper.getContradictionByCaseCode(caseCode);
    }
}
