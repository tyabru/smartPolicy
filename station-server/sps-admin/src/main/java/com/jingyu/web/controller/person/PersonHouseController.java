package com.jingyu.web.controller.person;

import java.util.List;

import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.person.domain.HouseAddrVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.person.domain.PersonHouse;
import com.jingyu.person.service.IPersonHouseService;
import com.jingyu.common.core.page.TableDataInfo;

import static com.jingyu.person.PersonConstants.*;

/**
 * houseController
 * 
 * @author jingyu
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/system/house")
public class PersonHouseController extends BaseController
{
    @Autowired
    private IPersonHouseService personHouseService;

    @PreAuthorize("@ss.hasPermi('system:house:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonHouse personHouse)
    {
        startPage();
        List<PersonHouse> list = personHouseService.selectPersonHouseList(personHouse);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:house:list')")
    @GetMapping("/listGroupByAddress")
    public TableDataInfo listGroupByAddress(HouseAddrVo addrVo)
    {
        List<HouseAddrVo> list = personHouseService.selectHouseListGroupByMetaAddrId(addrVo);
        return getDataTable(list);
    }


    /**
     * 查询house列表
     */
    @PreAuthorize("@ss.hasPermi('system:house:list')")
    @GetMapping("/{personId}/resident")
    public AjaxResult residentList(@PathVariable("personId") Long personId)
    {
        List<PersonHouse> list = personHouseService.selectByPersonIdAndType(personId, PERSON_TYPE_RESIDENT);
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('system:house:list')")
    @GetMapping("/{personId}/flows")
    public AjaxResult flowsList(@PathVariable("personId") Long personId)
    {
        List<PersonHouse> list = personHouseService.selectByPersonIdAndType(personId, PERSON_TYPE_FLOW);
        return AjaxResult.success(list);
    }


}
