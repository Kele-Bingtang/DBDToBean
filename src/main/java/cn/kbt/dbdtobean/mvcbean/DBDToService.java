package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;

/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/21 21:45
 */
public class DBDToService extends AbstractDBDToMVC {
    /**
     * Service接口基础名
     **/
    protected final static String SERVICE_INTERFACE_NAME = "Service";
    /**
     * Service实现类基础名
     **/
    protected final static String SERVICE_IMPL_NAME = "ServiceImpl";
    /**
     * Service接口完整名
     **/
    protected static String interfacesName = null;

    /**
     * 生成Service接口内容
     * @param createBeanName 文件名
     * @return 接口名
     * @throws IOException IO 异常
     */
    public String serviceInterfaces(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        interfacesName = super.createInterfaces(definition, createBeanName, SERVICE_INTERFACE_NAME);
        return interfacesName;
    }

    /**
     * 生成Service实现类内容
     * @param createBeanName 文件名
     * @return 内容
     * @throws IOException IO 异常
     */
    public String serviceBean(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        return super.createBean(definition, createBeanName, SERVICE_IMPL_NAME, interfacesName);
    }
}
