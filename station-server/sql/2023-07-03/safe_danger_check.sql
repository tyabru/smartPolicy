INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1062, '安全隐患检查管理', 0, 5, 'safe_check', NULL, NULL, 1, 0, 'M', '0', '0', '', 'excel', 'admin', '2023-06-27 18:55:47', 'admin', '2023-07-08 10:57:06', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1063, '隐患检查登记', 1062, 1, 'checkdanger', 'safecheck/checkdanger/index', NULL, 1, 0, 'C', '0', '0', 'safecheck:checkdanger:list', 'form', 'admin', '2023-06-27 19:10:07', 'admin', '2023-07-08 11:00:53', '安全隐患检查登记菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1064, '安全隐患检查登记查询', 1063, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkdanger:query', '#', 'admin', '2023-06-27 19:10:07', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1065, '安全隐患检查登记新增', 1063, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkdanger:add', '#', 'admin', '2023-06-27 19:10:07', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1066, '安全隐患检查登记修改', 1063, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkdanger:edit', '#', 'admin', '2023-06-27 19:10:07', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1067, '安全隐患检查登记删除', 1063, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkdanger:remove', '#', 'admin', '2023-06-27 19:10:07', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1068, '安全隐患检查登记导出', 1063, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkdanger:export', '#', 'admin', '2023-06-27 19:10:07', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1075, '整改通知下发', 1062, 1, 'rectifynoticedistribute', 'safecheck/rectifynoticedistribute/index', NULL, 1, 0, 'C', '0', '0', 'safecheck:rectifynoticedistribute:list', 'email', 'admin', '2023-06-27 19:43:33', 'admin', '2023-07-08 10:58:54', '整改通知书下发菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1076, '整改通知书下发查询', 1075, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifynoticedistribute:query', '#', 'admin', '2023-06-27 19:43:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1077, '整改通知书下发新增', 1075, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifynoticedistribute:add', '#', 'admin', '2023-06-27 19:43:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1078, '整改通知书下发修改', 1075, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifynoticedistribute:edit', '#', 'admin', '2023-06-27 19:43:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1079, '整改通知书下发删除', 1075, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifynoticedistribute:remove', '#', 'admin', '2023-06-27 19:43:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1080, '整改通知书下发导出', 1075, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifynoticedistribute:export', '#', 'admin', '2023-06-27 19:43:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1081, '整改结果审核', 1062, 1, 'rectifyresultreview', 'safecheck/rectifyresultreview/index', NULL, 1, 0, 'C', '0', '0', 'safecheck:rectifyresultreview:list', 'checkbox', 'admin', '2023-06-27 19:58:27', 'admin', '2023-07-08 11:06:04', '整改结果审核菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1082, '整改结果审核查询', 1081, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifyresultreview:query', '#', 'admin', '2023-06-27 19:58:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1083, '整改结果审核新增', 1081, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifyresultreview:add', '#', 'admin', '2023-06-27 19:58:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1084, '整改结果审核修改', 1081, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifyresultreview:edit', '#', 'admin', '2023-06-27 19:58:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1085, '整改结果审核删除', 1081, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifyresultreview:remove', '#', 'admin', '2023-06-27 19:58:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1086, '整改结果审核导出', 1081, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:rectifyresultreview:export', '#', 'admin', '2023-06-27 19:58:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1087, '模板管理', 1062, 1, 'checkplacedict', 'safecheck/checkplacedict/index', NULL, 1, 0, 'C', '0', '0', 'safecheck:checkplacedict:list', 'dict', 'admin', '2023-06-28 19:13:56', 'admin', '2023-07-08 11:04:12', '安全隐患检查场所字典菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1088, '场所字典查询', 1087, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkplacedict:query', '#', 'admin', '2023-06-28 19:13:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1089, '场所字典新增', 1087, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkplacedict:add', '#', 'admin', '2023-06-28 19:13:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1090, '场所字典修改', 1087, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkplacedict:edit', '#', 'admin', '2023-06-28 19:13:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1091, '场所字典删除', 1087, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkplacedict:remove', '#', 'admin', '2023-06-28 19:13:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1092, '场所字典导出', 1087, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'safecheck:checkplacedict:export', '#', 'admin', '2023-06-28 19:13:56', '', NULL, '');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (30, 0, '加油站', '1', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:29:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (31, 1, '液化气站', '2', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:30:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (32, 2, '培训机构', '3', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:31:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (33, 3, '小区', '4', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:31:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (34, 4, '商场超市', '5', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:31:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (35, 5, '宾馆', '6', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:32:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (36, 6, '寄递物流', '7', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:32:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (37, 7, '九小场所', '8', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:32:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (38, 8, '企事业单位', '9', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:33:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (39, 9, '娱乐场所', '10', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:33:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (40, 10, '校园', '11', 'place_list', NULL, 'default', 'N', '0', 'admin', '2023-06-27 18:33:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (41, 47, '是否制定和落实内部治安保卫制度（门卫、值班、巡查制度；工作、生产、经营、教学、科研等场所安全管理制度；现金、票据、印鉴、有价证券等物品使用、保管、储存、运输等安全管理制度；消防、安全管理制度；治安防范培训制度；单位内部发生治安案件、涉嫌刑事犯罪案件的报备制度）', '47', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:50:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (42, 0, '散装汽油实名登记', '1', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:52:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (43, 1, '消防设施是否完好无损', '2', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:53:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (44, 2, '视频监控是否正常运转', '3', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:53:39', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (45, 3, '水、电、器是否存在安全隐患', '4', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:53:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (46, 4, '是否制定消防、反恐应急预案', '5', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:54:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (47, 5, '是否定期开展消防、反恐演练（影视资料）', '6', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:54:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (48, 6, '是否定期开展消防、反恐知识技能培训', '7', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:54:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (49, 7, '是否配备反恐安防器材', '8', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:55:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (50, 8, '岗位工作人员是否进行安全背景审查', '9', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-06-28 21:55:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, 9, '重点部位是否安装侵入报警装置', '10', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:51:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (101, 10, '是否有门卫，佩戴红袖标，是否有门卫制度及值班安排', '11', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:52:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (102, 11, '门卫执勤是否配备防卫器械', '12', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:52:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (103, 12, '重点部位是否安装防盗门', '13', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:53:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (104, 13, '消防设施是否配备齐全，安全出口、疏散通道、消防通道是否畅通，是否按规定设置消防疏散指示标志和应急照明', '14', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:53:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (105, 14, '学校是否设置一键报警系统并与属地接警中心联网', '15', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:53:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (106, 15, '易燃易爆等危险化学品储存室、财务室、实验室是否安装视频监控,双人双锁', '16', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:54:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (107, 16, '视频影像资料保存期限不少于90天，出现故障，应24小时内恢复功能', '17', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:55:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (108, 17, '是否配备专职兼职保安，是否落实巡逻、值班、值守制度，对外来车辆人员是否落实登记制度', '18', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:55:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (109, 18, '是否定制治安突发事件预案、定期组织开展演练', '19', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:55:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (110, 19, '是否对重要岗位人员进行安全背景审查（物业、保安、后勤人员、内部教职工）', '20', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:56:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (111, 20, '是否对校车定期维护、年审等', '21', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:56:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (112, 21, '是否落实封闭式管理', '22', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:56:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (113, 22, '是否按照规定落实门卫及保安巡逻制度,且巡查记录登记详细规范', '23', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:57:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (114, 23, '是否按规定留存视频影像资料', '24', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:57:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (115, 24, '消防通道是否堵塞', '25', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:57:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (116, 25, '消防通道是否按标准划线、标名、立牌、表示化管理', '26', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:57:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (117, 26, '是否存在飞线充电', '27', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:58:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (118, 27, '是否设置电动车集中充电桩(配备灭火器)', '28', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:58:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (119, 28, '安全通道是否堵塞', '29', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:58:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (120, 29, '消防设施是否正常完好', '30', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:59:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (121, 30, '是否购置反恐安防器材', '31', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 12:59:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (122, 31, '是否按规定落实一人一证实名登记', '32', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:00:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (123, 32, '是否取得特种行业许可证', '33', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:00:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (124, 33, '实名登记标志、三禁标志是否张贴', '34', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:00:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (125, 34, '专职保安配备情况以及扫黑除恶线索摸排', '35', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:01:11', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (126, 35, '是否按规定收寄实名登记', '36', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:01:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (127, 36, '是否开收寄验试开箱验货', '37', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:01:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (128, 37, '是否过X光机安检', '38', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:02:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (129, 38, '实名登记标志、三禁标志是否张贴是否设置必要的治安防范措施（巡逻、值班警卫，监控90天、门禁系统、人脸识别、安防器材）', '39', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:02:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (130, 39, '是否配备专职或兼职保安', '40', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:02:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (131, 40, '是否定期开展安全防范知识、消防技能培训等', '41', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:03:06', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (132, 41, '是否悬挂疏散标志', '42', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:03:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (133, 42, '疏散通道是否阻塞', '43', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:03:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (134, 43, '是否为三合一场所', '44', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:04:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (135, 44, '从业人员是否熟练掌握消防技能', '45', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:04:22', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (136, 45, '是否建立落实主要负责人治安保卫工作责任制、是否制定治安保卫组织机构', '46', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:04:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (137, 47, '是否设置必要的治安防范措施（巡逻、值班警卫，监控90天、门禁系统、人脸识别、安防器材）', '48', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:05:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (138, 48, '内部治安保卫机构、治安保卫人员是否履行规定职责(开展治安防范宣传教育；落实相关制度，根据需要检查进入本单位人员的证件，登记出入物品和车，开展巡逻和检查、治安隐患排查等)', '49', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:05:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (139, 49, '是否确定治安重点部位、落实设置必要的技术防范设施（财务室、实验室）', '50', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:05:51', 'admin', '2023-07-08 13:06:07', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (140, 50, '是否定期开展安全防范演练（影视资料）', '51', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:06:22', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (141, 51, '是否定期开展安全防范知识技能培训', '52', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:06:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (142, 52, '是否张贴三禁标志', '53', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:07:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (143, 53, '三本台账是否齐全并按规填写', '54', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:07:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (144, 54, '是否按规定实时如实将从业人员、营业日志、安全巡查传输公安系统', '55', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:07:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (145, 55, '包厢、包间门窗是否按要求设置0.4米*0.2米透明玻璃', '56', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:07:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (146, 56, '营业大厅、包厢、包间内是否设置可调式亮度照明灯', '57', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:08:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (147, 57, '是否设置安检门、安检设备等', '58', 'check_items', NULL, 'default', 'N', '0', 'admin', '2023-07-08 13:08:29', '', NULL, NULL);


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, '场所列表', 'place_list', '0', 'admin', '2023-06-27 18:27:51', '', NULL, '场所列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, '安全隐患检查项列表', 'check_items', '0', 'admin', '2023-06-28 21:38:23', '', NULL, '安全隐患检查项列表');

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 08/07/2023 09:53:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_rectify_result_review_record
-- ----------------------------
DROP TABLE IF EXISTS `check_rectify_result_review_record`;
CREATE TABLE `check_rectify_result_review_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `chected_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位',
  `place_id` int(11) NOT NULL COMMENT '场所',
  `check_date` datetime NOT NULL COMMENT '检查日期',
  `checked_unit_director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位负责人',
  `checked_unit_director_acccout_number` bigint(20) NOT NULL COMMENT '被检查人账号',
  `check_police` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '责任民警',
  `check_safty_danger` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题隐患',
  `rectify_measure` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整改措施',
  `finish_date` datetime NOT NULL COMMENT '完成时间',
  `rectify_notice_photo` blob NOT NULL COMMENT '整改通知书签字照片',
  `rectify_result_site_photo` blob NOT NULL COMMENT '整改结果现场照片',
  `review_status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '审核状态（0：待审核1：审核通过2：审核不通过）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '整改结果审核记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_rectify_result_review_record
-- ----------------------------
INSERT INTO `check_rectify_result_review_record` VALUES (1, '急急急', 1, '2023-07-05 00:00:00', '花开花落', 124536457357, '还款记录', '回家了', '一路UN', '2023-07-29 00:00:00', 0x2F70726F66696C652F75706C6F61642F323032332F30372F30372FE695B4E694B9E9809AE79FA5E4B9A65F3230323330373037323135343433413030312E646F63, 0x2F70726F66696C652F75706C6F61642F323032332F30372F30372FE5BEAEE4BFA1E59BBEE789875F32303233303232333137353934325F3230323330373037313234323437413030322E6A7067);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 08/07/2023 09:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_danger_record
-- ----------------------------
DROP TABLE IF EXISTS `check_danger_record`;
CREATE TABLE `check_danger_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `chected_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位',
  `place_id` int(11) NOT NULL COMMENT '场所',
  `check_date` datetime NOT NULL COMMENT '检查日期',
  `checked_unit_director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位负责人',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `check_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '检查人员',
  `check_item_results` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '检查项填写结果',
  `other_safty_danger` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '其他安全隐患',
  `check_result` blob NULL COMMENT '检查记录表签名文件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全隐患检查记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_danger_record
-- ----------------------------
INSERT INTO `check_danger_record` VALUES (1, '阿里巴巴集团', 9, '2023-06-01 00:00:00', '蔡崇信', '张三', '0,1,1,1,1,1,1,1,1', NULL);
INSERT INTO `check_danger_record` VALUES (2, '中石油', 1, '2023-06-08 00:00:00', '马化腾', '王健林', '0,0,0,1,1,1,1,1,1', NULL);
INSERT INTO `check_danger_record` VALUES (3, '急急急', 1, '2023-07-06 00:00:00', '看看', '力量', '1,1,1,1,1,1,1,1,1', NULL);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 08/07/2023 09:53:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_place_dict
-- ----------------------------
DROP TABLE IF EXISTS `check_place_dict`;
CREATE TABLE `check_place_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `place_name` int(11) NOT NULL COMMENT '场所行业名称',
  `check_items` set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '检查项列表',
  `rectify_notice_template_params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整改通知书模板参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全隐患检查场所字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_place_dict
-- ----------------------------
INSERT INTO `check_place_dict` VALUES (1, 1, '1,2,3,4,5,6,7,8,9', NULL);
INSERT INTO `check_place_dict` VALUES (2, 2, '2,3,4,5,6,7,8,9,10', NULL);
INSERT INTO `check_place_dict` VALUES (3, 3, '11,12,13,14,15,16,17,18,19,20,21,22', NULL);
INSERT INTO `check_place_dict` VALUES (4, 4, '2,4,19,23,24,25,26,27,28,29', '《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《陕西省消防监督检查条例》《企事业单位内部治安保卫条例》《公安机关监督检查企事业单位内部治安保卫工作规定》');
INSERT INTO `check_place_dict` VALUES (5, 5, '2,4,5,6,7,9,24,31', NULL);
INSERT INTO `check_place_dict` VALUES (6, 6, '2,9,19,24,32,33,34,35', '《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《旅馆业治安管理办法》《企事业单位内部治安保卫条例》《中华人民共和国反恐怖主义法》');
INSERT INTO `check_place_dict` VALUES (7, 7, '2,9,19,36,37,38,39,40,41', NULL);
INSERT INTO `check_place_dict` VALUES (8, 8, '4,24,30,42,43,44,45', NULL);
INSERT INTO `check_place_dict` VALUES (9, 9, '9,19,40,46,47,48,49,50,51,52', '《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《金融机构营业场所和金库安全防范设施建设许可实施办法》）《企事业单位内部治安保卫条例》《中华人民共和国反恐怖主义法》《公安机关监督检查企事业单位内部治安保卫工作规定》');
INSERT INTO `check_place_dict` VALUES (10, 10, '24,30,33,40,53,54,55,56,57,58', '《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《娱乐场所管理条例》《企事业单位内部治安保卫条例》《中华人民共和国反恐怖主义法》');
INSERT INTO `check_place_dict` VALUES (11, 11, '11,12,13,14,15,16,17,18,19,20,21,22', '《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《公安机关实施保安服务管理条例》《中小学幼儿园安全防范工作规范》《中华人民共和国反恐怖主义法》《公安机关监督检查企事业单位内部治安保卫工作规定》《校车安全管理条例》');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 08/07/2023 09:53:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_rectify_notice_distribute_record
-- ----------------------------
DROP TABLE IF EXISTS `check_rectify_notice_distribute_record`;
CREATE TABLE `check_rectify_notice_distribute_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `chected_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位',
  `place_id` int(11) NOT NULL COMMENT '场所',
  `check_date` datetime NOT NULL COMMENT '检查日期',
  `checked_unit_director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被检查单位负责人',
  `checked_unit_director_acccout_number` bigint(20) NOT NULL COMMENT '被检查人账号',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '被检查人电话号码',
  `check_police` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '责任民警',
  `check_safty_danger` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题隐患',
  `rectify_measure` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整改措施',
  `finish_date` datetime NOT NULL COMMENT '完成时间',
  `finish_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '完成状态(0:正常1：超期）',
  `notice_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '短信提醒状态（0:未提醒1：已提醒）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '整改通知书下发记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_rectify_notice_distribute_record
-- ----------------------------
INSERT INTO `check_rectify_notice_distribute_record` VALUES (1, '四川大学', 11, '2023-06-02 00:00:00', '马化腾', 13581379325, '刘强东', '思政教育不到位', '限期整改', '2023-06-30 00:00:00', '超期');
INSERT INTO `check_rectify_notice_distribute_record` VALUES (2, '新东方', 3, '2023-07-07 00:00:00', '俞敏洪', 676858585855, '马云', '消防设备陈旧', '限期整改', '2023-07-29 00:00:00', '正常');

SET FOREIGN_KEY_CHECKS = 1;
