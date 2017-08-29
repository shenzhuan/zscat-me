package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class NullWarpper extends BaseControllerWarpper {

    public NullWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {


    }

}
