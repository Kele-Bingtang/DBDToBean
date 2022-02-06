package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 21:18
 * @Version 1.0
 * @Describe
 */
public class DBDToController extends AbstractDBDToMVC{
    /** Controller名 **/
    protected final static String CONTROLLER_NAME = "Controller";
    /**
     * 创建Controller层以及文件内容
     */
    public String controllerBean(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        return super.createBean(definition, createBeanName, CONTROLLER_NAME, null);
    }
    
}
