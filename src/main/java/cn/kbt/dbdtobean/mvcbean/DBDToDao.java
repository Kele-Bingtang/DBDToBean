package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;

/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/21 22:50
 */
public class DBDToDao extends AbstractDBDToMVC {
    /**
     * Dao层接口基础名
     **/
    protected final static String DAO_INTERFACE_NAME = "Dao";
    /**
     * Dao层实现类基础名
     **/
    protected final static String DAO_IMPL_NAME = "DaoImpl";
    /**
     * Dao层接口完整名
     **/
    protected static String InterfacesName = null;

    /**
     * 创建Dao层接口目录以及内容
     *
     * @param createBeanName 文件名
     * @return 内容
     * @throws IOException IO 异常
     */
    public String daoInterfaces(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        InterfacesName = super.createInterfaces(definition, createBeanName, DAO_INTERFACE_NAME);
        return InterfacesName;
    }

    /**
     * 生成Dao层实现类目录以及内容
     *
     * @param createBeanName 文件名
     * @return 内容
     * @throws IOException IO 异常
     */
    public String daoBean(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        return super.createBean(definition, createBeanName, DAO_IMPL_NAME, InterfacesName);
    }


}
