package com.jingyu.contradiction.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.contradiction.domain.ContradictionTangles;
import com.jingyu.contradiction.domain.DisputingPersonnel;
import com.jingyu.contradiction.mapper.ContradictionTanglesMapper;
import com.jingyu.contradiction.mapper.DisputingPersonnelMapper;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.mapper.PoliceFileManagementsMapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.contradiction.mapper.MediationRecordsMapper;
import com.jingyu.contradiction.domain.MediationRecords;
import com.jingyu.contradiction.service.IMediationRecordsService;

import javax.annotation.Resource;
import javax.security.auth.login.Configuration;

/**
 * 纠纷调解记录Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Service
public class MediationRecordsServiceImpl implements IMediationRecordsService 
{
    @Resource
    private MediationRecordsMapper mediationRecordsMapper;

    @Resource
    private DisputingPersonnelMapper disputingPersonnelMapper;

    @Resource
    private PoliceFileManagementsMapper policeFileManagementsMapper;

    @Resource
    private ContradictionTanglesMapper contradictionTanglesMapper;

    /**
     * 查询纠纷调解记录
     * 
     * @param id 纠纷调解记录主键
     * @return 纠纷调解记录
     */
    @Override
    public MediationRecords selectMediationRecordsById(Long id)
    {
        return mediationRecordsMapper.selectMediationRecordsById(id);
    }

    /**
     * 查询纠纷调解记录列表
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 纠纷调解记录
     */
    @Override
    public List<MediationRecords> selectMediationRecordsList(MediationRecords mediationRecords)
    {
        List<MediationRecords> recordsList = mediationRecordsMapper.selectMediationRecordsList(mediationRecords);
        for (MediationRecords records : recordsList) {
            //查询参与纠纷人员姓名
            handleDataRecords(records);
        }
        return recordsList;
    }

    /**
     * 查询参与纠纷人员姓名
     * */
    public MediationRecords handleDataRecords(MediationRecords mediationRecords) {
        String personnelNames = "";
        String ids = mediationRecords.getParticipantsInDisputes();
        String[] idList = ids.split(",");
        for (int i = 0; i < idList.length; i++) {
            DisputingPersonnel disputingPersonnel = disputingPersonnelMapper.selectDisputingPersonnelById(Long.parseLong(idList[i]));
            if (i == idList.length-1) {
                personnelNames += disputingPersonnel.getPeopleName() ;
            } else {
                personnelNames += disputingPersonnel.getPeopleName() + ",";
            }
        }
        mediationRecords.setParticipantsInDisputesName(personnelNames);
        return mediationRecords;
    }

    /**
     * 新增纠纷调解记录
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 结果
     */
    @Override
    public int insertMediationRecords(MediationRecords mediationRecords)
    {
        int i = mediationRecordsMapper.insertMediationRecords(mediationRecords);
        if (i > 0) {
            //文件数据处理
            PoliceFileManagements policeFileManagements = fileDataHandle(mediationRecords);
            //新增文件
            int j = policeFileManagementsMapper.insertPoliceFileManagements(policeFileManagements);
            if (j > 0) {
                //查询新增文件
                List<PoliceFileManagements> policeFileManagements1 = policeFileManagementsMapper.selectPoliceFileManagementsList(policeFileManagements);
                List<MediationRecords> mediationRecords1 = mediationRecordsMapper.selectMediationRecordsList(mediationRecords);
                MediationRecords mediationRecords2 = new MediationRecords();
                mediationRecords2.setId(mediationRecords1.get(0).getId());
                mediationRecords2.setFileId(policeFileManagements1.get(0).getId());
                mediationRecordsMapper.updateMediationRecords(mediationRecords2);
            }
        }
        return i;
    }

    /**
     * 修改纠纷调解记录
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 结果
     */
    @Override
    public int updateMediationRecords(MediationRecords mediationRecords)
    {
        int i = mediationRecordsMapper.updateMediationRecords(mediationRecords);
        if (i > 0) {
            //文件修改
            policeFileManagementsMapper.updatePoliceFileManagements(fileDataHandle(mediationRecords));
        }
        return i;
    }

    /**
     * 纠纷调解文件数据处理
     * */
    public PoliceFileManagements fileDataHandle(MediationRecords mediationRecords) {
        PoliceFileManagements policeFileManagements = new PoliceFileManagements();
        if (StringUtils.isNotNull(mediationRecords.getId()) && mediationRecords.getId() > 0) {
            policeFileManagements.setId(mediationRecords.getFileId());
        }
        ContradictionTangles contradictionByCaseCode = contradictionTanglesMapper.getContradictionByCaseCode(mediationRecords.getCaseCode());
        policeFileManagements.setDeptId(contradictionByCaseCode.getDeptId());
        policeFileManagements.setFileName(mediationRecords.getFileName());
        policeFileManagements.setFolderName(mediationRecords.getCaseCode());
        policeFileManagements.setFilePath(mediationRecords.getFilePath());
        policeFileManagements.setFileType(1L);
        return policeFileManagements;
    }

    /**
     * 批量删除纠纷调解记录
     * 
     * @param ids 需要删除的纠纷调解记录主键
     * @return 结果
     */
    @Override
    public int deleteMediationRecordsByIds(Long[] ids)
    {
        return mediationRecordsMapper.deleteMediationRecordsByIds(ids);
    }

    /**
     * 删除纠纷调解记录信息
     * 
     * @param id 纠纷调解记录主键
     * @return 结果
     */
    @Override
    public int deleteMediationRecordsById(Long id)
    {
        return mediationRecordsMapper.deleteMediationRecordsById(id);
    }
}
