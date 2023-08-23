package com.jingyu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jingyu.common.core.domain.entity.SysDept;
<<<<<<< HEAD
import org.springframework.security.core.parameters.P;
=======
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

/**
 * 部门管理 数据层
 * 
 * @author ruoyi
 */
public interface SysDeptMapper
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 根据角色ID查询部门树信息
     * 
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门
     * 
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

<<<<<<< HEAD
    String queryChildIdsByTypeAndDeptId(@Param("deptId") Long deptId,@Param("deptType") String deptType);

    /**
     * 根据ID查询自己和自己所有的子部门
     * 该方法只查询部分必要字段并且包括deptId对应的数据
     *
     * @param ancestors 部门ancestors
     * @return 部门列表
     */
    List<SysDept> selectDeptTreeByAncestors(@Param("ancestors") String ancestors);

=======
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改所在部门正常状态
     * 
     * @param deptIds 部门ID组
     */
    public void updateDeptStatusNormal(Long[] deptIds);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

<<<<<<< HEAD
    public int updateDeptId(@Param("origin")Long origin, @Param("newDeptId")Long deptId);

=======
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);
<<<<<<< HEAD

    /**
     * 查询当前部门下deptId最大的id
     * */
    Long queryMaxIdInThisLevel(Long parentId);


    SysDept queryBelongDeptByTypeAndId(@Param("deptId") Long deptId,@Param("deptType") String deptType);


=======
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
}
