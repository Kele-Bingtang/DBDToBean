package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;

/**
 * @Author Kele-Bing
 * @Create 2021/9/22 19:15
 * @Version 1.0
 * @Describe
 */
public class DBDToMVCDefinition {
    /** Controller位置 **/
    private String controllerLocation = null;
    /** Service位置 **/
    private String serviceLocation = null;
    /** Dao位置 **/
    private String daoLocation = null;
    /** Mapper位置 **/
    private String mapperLocation = null;
    /** Mapper的xml位置 **/
    private String mapperXmlLocation = "mapper";
    /** 所有文件前缀 **/
    private String prefix = "";
    /** 所有文件后缀 **/
    private String suffix = "";
    /** Controller文件前缀 **/
    private String controllerPre = "";
    /** Controller文件后缀 **/
    private String controllerSuf = "Controller";
    /** Service接口文件前缀 **/
    private String serviceInterPre = "";
    /** Service接口文件后缀 **/
    private String serviceInterSuf = "Service";
    /** Service实现文件前缀 **/
    private String serviceImplPre = "";
    /** Service实现文件后缀 **/
    private String serviceImplSuf = "ServiceImpl";
    /** Dao接口文件前缀 **/
    private String daoInterPre = "";
    /**  Dao接口文件后缀 **/
    private String daoInterSuf = "Dao";
    /** Dao实现文件前缀 **/
    private String daoImplPre = "";
    /** Dao实现文件后缀 **/
    private String daoImplSuf = "DaoImpl";
    /** Mapper接口文件前缀 **/
    private String mapperInterPre = "";
    /** Mapper接口文件后缀 **/
    private String mapperInterSuf = "Mapper";
    /** Mapper的xml文件前缀 **/
    private String mapperXmlPre = "";
    /** Mapper的xml文件后缀 **/
    private String mapperXmlSuf = "Mapper";
    /** 实体类路径 **/
    private String entityLocation = null;
    /** 所在模块名 **/
    private String modulesName = null;
    /** 是否生成CURD **/
    private boolean generatecurd = false;
    /** maven目录或普通目录 **/
    private boolean mavenOrSimple = true;
    /** 是否生成MVC注解 **/
    private boolean mvcAnnotation = true;

    public String getControllerLocation() {
        return controllerLocation;
    }

    public void setControllerLocation(String controllerLocation) {
        this.controllerLocation = controllerLocation;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getDaoLocation() {
        return daoLocation;
    }

    public void setDaoLocation(String daoLocation) {
        this.daoLocation = daoLocation;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getMapperXmlLocation() {
        return mapperXmlLocation;
    }

    public void setMapperXmlLocation(String mapperXmlLocation) {
        this.mapperXmlLocation = mapperXmlLocation;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getControllerPre() {
        return controllerPre;
    }

    public void setControllerPre(String controllerPre) {
        this.controllerPre = controllerPre;
    }

    public String getControllerSuf() {
        return controllerSuf;
    }

    public void setControllerSuf(String controllerSuf) {
        this.controllerSuf = controllerSuf;
    }

    public String getServiceInterPre() {
        return serviceInterPre;
    }

    public void setServiceInterPre(String serviceInterPre) {
        this.serviceInterPre = serviceInterPre;
    }

    public String getServiceInterSuf() {
        return serviceInterSuf;
    }

    public void setServiceInterSuf(String serviceInterSuf) {
        this.serviceInterSuf = serviceInterSuf;
    }

    public String getServiceImplPre() {
        return serviceImplPre;
    }

    public void setServiceImplPre(String serviceImplPre) {
        this.serviceImplPre = serviceImplPre;
    }

    public String getServiceImplSuf() {
        return serviceImplSuf;
    }

    public void setServiceImplSuf(String serviceImplSuf) {
        this.serviceImplSuf = serviceImplSuf;
    }

    public String getDaoInterPre() {
        return daoInterPre;
    }

    public void setDaoInterPre(String daoInterPre) {
        this.daoInterPre = daoInterPre;
    }

    public String getDaoInterSuf() {
        return daoInterSuf;
    }

    public void setDaoInterSuf(String daoInterSuf) {
        this.daoInterSuf = daoInterSuf;
    }

    public String getDaoImplPre() {
        return daoImplPre;
    }

    public void setDaoImplPre(String daoImplPre) {
        this.daoImplPre = daoImplPre;
    }

    public String getDaoImplSuf() {
        return daoImplSuf;
    }

    public void setDaoImplSuf(String daoImplSuf) {
        this.daoImplSuf = daoImplSuf;
    }

    public String getMapperInterPre() {
        return mapperInterPre;
    }

    public void setMapperInterPre(String mapperInterPre) {
        this.mapperInterPre = mapperInterPre;
    }

    public String getMapperInterSuf() {
        return mapperInterSuf;
    }

    public void setMapperInterSuf(String mapperInterSuf) {
        this.mapperInterSuf = mapperInterSuf;
    }

    public String getMapperXmlPre() {
        return mapperXmlPre;
    }

    public void setMapperXmlPre(String mapperXmlPre) {
        this.mapperXmlPre = mapperXmlPre;
    }

    public String getMapperXmlSuf() {
        return mapperXmlSuf;
    }

    public void setMapperXmlSuf(String mapperXmlSuf) {
        this.mapperXmlSuf = mapperXmlSuf;
    }

    public String getEntityLocation() {
        if(entityLocation == null){
            return "";
        }
        return entityLocation;
    }

    public void setEntityLocation(String entityLocation) {
        this.entityLocation = entityLocation;
    }

    public String getModulesName() {
        if(modulesName == null){
            return "";
        }
        return modulesName;
    }

    public void setModulesName(String modulesName) {
        this.modulesName = modulesName;
    }

    public boolean isGeneratecurd() {
        return generatecurd;
    }

    public void setGeneratecurd(boolean generatecurd) {
        this.generatecurd = generatecurd;
    }

    public boolean isMavenOrSimple() {
        return mavenOrSimple;
    }

    public void setMavenOrSimple(boolean mavenOrSimple) {
        this.mavenOrSimple = mavenOrSimple;
    }

    public boolean isMvcAnnotation() {
        return mvcAnnotation;
    }

    public void setMvcAnnotation(boolean mvcAnnotation) {
        this.mvcAnnotation = mvcAnnotation;
    }

    /**
     * 获取Maven或者普通目录
     */
    public String getMavenOrSimple() {
        if(mavenOrSimple){
            return AbstractDBDToMVC.MAVEN_HONE;
        }else {
            return AbstractDBDToMVC.SIMPLE_HONE;
        }
    }

    /**
     * 获取Mapper的xml路径
     */
    public String getMapperPath() {
        if(mavenOrSimple){
            return DBDToMapper.MAVEN_MAPPER_XML_HONE;
        }else {
            DBDToMVCDefinition dbdToMVCDefinition = DBDToBeanContext.getDbdToMVCDefinition();
            dbdToMVCDefinition.setMapperXmlLocation(dbdToMVCDefinition.getMapperLocation());
            return DBDToMapper.SIMPLE_MAPPER_XML_HONE;
        }
    }

}
