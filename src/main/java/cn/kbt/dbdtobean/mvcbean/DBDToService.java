package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 21:45
 * @Version 1.0
 * @Describe
 */
public class DBDToService extends AbstractDBDToMVC{
    /** Service接口基础名 **/
    protected final static String SERVICE_INTERFACE_NAME = "Service";
    /** Service实现类基础名 **/
    protected final static String SERVICE_IMPL_NAME = "ServiceImpl";
    /** Service接口完整名 **/
    protected static String interfacesName = null;

    /**
     * 生成Service接口内容
     */
    public String serviceInterfaces(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        interfacesName = super.createInterfaces(definition, createBeanName, SERVICE_INTERFACE_NAME);
        return interfacesName;
    }
    /**
     * 生成Service实现类内容
     */
    public String serviceBean(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        return super.createBean(definition, createBeanName, SERVICE_IMPL_NAME, interfacesName);
    }
}
