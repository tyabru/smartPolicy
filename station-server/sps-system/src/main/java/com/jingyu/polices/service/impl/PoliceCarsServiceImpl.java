package com.jingyu.polices.service.impl;

import java.io.File;
import java.util.List;

import com.jingyu.common.exception.CustomException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.polices.domain.PoliceFileManagements;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceCarsMapper;
import com.jingyu.polices.domain.PoliceCars;
import com.jingyu.polices.service.IPoliceCarsService;

import javax.annotation.Resource;

/**
 * 警用车辆Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceCarsServiceImpl implements IPoliceCarsService 
{
    @Value("${jingyu.profile}")
    private String filePath;

    @Resource
    private PoliceCarsMapper policeCarsMapper;

    /**
     * 查询警用车辆
     * 
     * @param id 警用车辆主键
     * @return 警用车辆
     */
    @Override
    public PoliceCars selectPoliceCarsById(Long id)
    {
        return policeCarsMapper.selectPoliceCarsById(id);
    }

    /**
     * 查询警用车辆列表
     * 
     * @param policeCars 警用车辆
     * @return 警用车辆
     */
    @Override
    public List<PoliceCars> selectPoliceCarsList(PoliceCars policeCars)
    {
        return policeCarsMapper.selectPoliceCarsList(policeCars);
    }

    /**
     * 新增警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    @Override
    public int insertPoliceCars(PoliceCars policeCars)
    {
        //数据校验
        checkEquipmentNumber(policeCars);
        return policeCarsMapper.insertPoliceCars(policeCars);
    }

    /**
     * 修改警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    @Override
    public int updatePoliceCars(PoliceCars policeCars)
    {
        //数据校验
        checkEquipmentNumber(policeCars);
        return policeCarsMapper.updatePoliceCars(policeCars);
    }

    /**
     * 校验车载设备编码是否重复使用,车辆照片是否修改
     */
    public void checkEquipmentNumber(PoliceCars policeCars) {
        if (policeCars.getIsVehicle().equals("Y")) {
            PoliceCars car = policeCarsMapper.getPoliceCarByEquipmentNumber(policeCars.getEquipmentNumber());
            if (policeCars.getId() != null && StringUtils.isNotNull(car) && car.getId() != policeCars.getId()) {
                throw new CustomException("车载编码" + policeCars.getEquipmentNumber() + "与车牌号" + car.getLicenseNumber() + "绑定,请重新选择车载！");
            } else if (policeCars.getId() == null && StringUtils.isNotNull(car)) {
                throw new CustomException("车载编码" + policeCars.getEquipmentNumber() + "与车牌号" + car.getLicenseNumber() + "绑定,请重新选择车载！");
            }
        } else {
            policeCars.setEquipmentNumber("null");
        }
        if (policeCars.getId() != null) {
            PoliceCars cars = policeCarsMapper.selectPoliceCarsById(policeCars.getId());
            //照片路径不同，则删除原照片
            if (!policeCars.getVehiclePhotos().equals(cars.getVehiclePhotos())) {
                deleteVehiclePhotos(policeCars.getId());
            }
        }
    }

    /**
     * 批量删除警用车辆
     * 
     * @param ids 需要删除的警用车辆主键
     * @return 结果
     */
    @Override
    public int deletePoliceCarsByIds(Long[] ids)
    {
        for (Long id : ids) {
            //删除车辆照片
            deleteVehiclePhotos(id);
        }
        return policeCarsMapper.deletePoliceCarsByIds(ids);
    }

    /**
     * 删除警用车辆信息
     * 
     * @param id 警用车辆主键
     * @return 结果
     */
    @Override
    public int deletePoliceCarsById(Long id)
    {
        //删除车辆照片
        deleteVehiclePhotos(id);
        return policeCarsMapper.deletePoliceCarsById(id);
    }

    /**
     * 查询警用车辆列表
     *
     * @param licenseNumber 车牌号
     * @return 警用车辆集合
     */
    @Override
    public PoliceCars getPoliceCarByLicenseNumber(String licenseNumber) {
        return policeCarsMapper.getPoliceCarByLicenseNumber(licenseNumber);
    }

    /**
     * 查询警用车辆列表
     *
     * @param carCode 车辆编码
     * @return 警用车辆集合
     */
    @Override
    public PoliceCars getPoliceCarByCarCode(String carCode) {
        return policeCarsMapper.getPoliceCarByCarCode(carCode);
    }

    /**
     * 查询警用车辆列表
     *
     * @param equipmentNumber 车载设备编码
     * @return 警用车辆集合
     */
    @Override
    public PoliceCars getPoliceCarByEquipmentNumber(String equipmentNumber) {
        return policeCarsMapper.getPoliceCarByEquipmentNumber(equipmentNumber);
    }

    /**
     * 删除车辆照片
     * */
    public void deleteVehiclePhotos(Long id) {
        PoliceCars policeCars = policeCarsMapper.selectPoliceCarsById(id);
        String fileUrlPath = filePath + policeCars.getVehiclePhotos();
        File file = new File(fileUrlPath);
        if (file.exists()) {
            file.delete();
        } else {
            throw new ClassCastException("车辆照片不存在,请确定照片路径是否正确!");
        }
    }
}
