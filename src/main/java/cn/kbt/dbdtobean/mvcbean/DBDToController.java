package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;

/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/21 21:18
 */
public class DBDToController extends AbstractDBDToMVC {
    /**
     * Controller名
     **/
    protected final static String CONTROLLER_NAME = "Controller";

    /**
     * 创建Controller层以及文件内容
     *
     * @param createBeanName 文件名
     * @return 内容
     * @throws IOException IO 异常
     */
    public String controllerBean(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        return super.createBean(definition, createBeanName, CONTROLLER_NAME, null);
    }

}
