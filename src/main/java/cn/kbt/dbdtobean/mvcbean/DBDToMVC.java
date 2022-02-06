package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.core.DBDToBeanDefinition;
import cn.kbt.dbdtobean.inter.IDBDToMVC;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 21:33
 * @Version 1.0
 * @Describe MVC核心类
 */
public class DBDToMVC implements IDBDToMVC {
    /** 定义类信息 **/
    List<DBDToBeanDefinition> definitions = DBDToBeanContext.getDbdToBeanDefinitions();
    /**
     * 生成Controller层所有内容
     */
    @Override
    public void dbdToController() throws IOException {
        if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getControllerLocation())){
            DBDToController dbdToController = new DBDToController();
            for (DBDToBeanDefinition definition : definitions) {
                dbdToController.controllerBean(definition.getCreateBeanName());
            }
        }
    }
    /**
     * 生成Service层所有内容
     */
    @Override
    public void dbdToService() throws IOException {
        if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getServiceLocation())){
            DBDToService dbdToService = new DBDToService();
            for (DBDToBeanDefinition definition : definitions) {
                dbdToService.serviceInterfaces(definition.getCreateBeanName());
                dbdToService.serviceBean(definition.getCreateBeanName());
            }
        }
    }
    /**
     * 生成Dao层所有内容
     */
    @Override
    public void dbdToDao() throws IOException {
        if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getDaoLocation())){
            DBDToDao dbdToDao = new DBDToDao();
            for (DBDToBeanDefinition definition : definitions) {
                dbdToDao.daoInterfaces(definition.getCreateBeanName());
                dbdToDao.daoBean(definition.getCreateBeanName());
            }
        }
    }
    /**
     * 生成Mapper层所有内容
     */
    @Override
    public void dbdToMapper() throws IOException {
        if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getMapperLocation())){
            DBDToMapper dbdToMapper = new DBDToMapper();
            for (DBDToBeanDefinition definition : definitions) {
                dbdToMapper.mapperInterfaces(definition.getCreateBeanName());
                dbdToMapper.mapperXML(definition.getCreateBeanName());
            }
        }
    }
    /**
     * 整合生成MVC内容的API
     */
    public void dbdToMVC() throws IOException {
        this.dbdToController();
        this.dbdToService();
        this.dbdToDao();
        this.dbdToMapper();
    }
}
