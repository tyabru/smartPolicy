package com.jingyu.community.service.impl;

import java.util.Arrays;
import java.util.List;

import com.jingyu.common.exception.base.BaseException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.encryption_decryption.SensitiveNewsHander;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.community.domain.Company;
import com.jingyu.community.mapper.CompanyMapper;
import com.jingyu.community.service.ICompanyDescService;
import com.jingyu.community.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位基本信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService
{
    private final CompanyMapper companyMapper;
    private final ICompanyDescService companyDescService;

    /**
     * 查询单位基本信息
     * 
     * @param id 单位基本信息主键
     * @return 单位基本信息
     */
    @Override
    public Company selectCompanyById(Long id)
    {
        return companyMapper.selectCompanyById(id);
    }

    /**
     * 查询单位基本信息列表
     * 
     * @param company 单位基本信息
     * @return 单位基本信息
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增单位基本信息
     * 
     * @param company 单位基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCompany(Company company)
    {
        String username = SecurityUtils.getUsername();
        company.setCreateTime(DateUtils.getNowDate());
        company.setCreateBy(username);
        if(codeIsExists(company.getCompanyCode())) {
            throw new BaseException("单位编码已存在!");
        }
        SensitiveNewsHander.revertEncryptAttrs(company);
        int i = companyMapper.insertCompany(company);
        if(i > 0) {
            String ids = company.getDescIds();
            if(StringUtils.isNotEmpty(ids)) {
                long[] idArr = Arrays.stream(ids.split(",")).mapToLong(Long::parseLong).toArray();
                for (long id : idArr) {
                    companyDescService.updateCompanyIdById(id, company.getId());
                }
            }
        }
        return i;
    }

    /**
     * 修改单位基本信息
     * 
     * @param company 单位基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCompany(Company company)
    {
        String username = SecurityUtils.getUsername();
        company.setUpdateTime(DateUtils.getNowDate());
        company.setUpdateBy(username);
        if(codeIsExistsExcludeSelf(company.getId(), company.getCompanyCode())) {
            throw new BaseException("单位编码已存在!");
        }
        Company old = selectCompanyById(company.getId());
        old.setIdentityCode(AESUtil.decrypt(old.getIdentityCode()));
        old.setPhone(AESUtil.decrypt(old.getPhone()));
        old.setContactPhone(AESUtil.decrypt(old.getContactPhone()));
        SensitiveNewsHander.revertNotEditAttrs(company,old);
        SensitiveNewsHander.revertEncryptAttrs(company);
        int i = companyMapper.updateCompany(company);
        String ids = company.getDescIds();
        if(StringUtils.isNotEmpty(ids)) {
            long[] idArr = Arrays.stream(ids.split(",")).mapToLong(Long::parseLong).toArray();
            for (long id : idArr) {
                companyDescService.updateCompanyIdById(id, company.getId());
            }
        }
        return i;
    }

    /**
     * 批量删除单位基本信息
     * 
     * @param ids 需要删除的单位基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCompanyByIds(Long[] ids)
    {
        for (Long id : ids) {
            deleteCompanyById(id);
        }
        return ids.length;
    }

    /**
     * 删除单位基本信息信息
     * 
     * @param id 单位基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCompanyById(Long id)
    {
        companyDescService.deleteDescByCompanyId(id);
        return companyMapper.deleteCompanyById(id);
    }

    public boolean codeIsExistsExcludeSelf(Long id, String code) {
        if(StringUtils.isEmpty(code)) {
            // 如果code为空, 则默认为true
            return true;
        }
        Company company = new Company();
        company.setCompanyCode(code);
        company.getParams().put("excludeId", id);
        int count = companyMapper.countByCondition(company);
        return count > 0;
    }

    private boolean codeIsExists(String code) {

        if(StringUtils.isEmpty(code)) {
            // 如果code为空, 则默认为true
            return true;
        }
        Company company = new Company();
        company.setCompanyCode(code);
        int count = companyMapper.countByCondition(company);
        return count > 0;
    }
}
