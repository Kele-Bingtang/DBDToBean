package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 23:30
 * @Version 1.0
 * @Describe 抽象MVC类
 */
public abstract class AbstractDBDToMVC {
    /** Maven目录 **/
    public final static String MAVEN_HONE = "src\\main\\java\\";
    /** 普通目录 **/
    protected final static String SIMPLE_HONE = "src\\";
    /** 实现类文件夹名 **/
    public static String IMPL_NAME = "impl";
    /** Mapper的xml开头配置 **/
    public static String xmlPublicAndHttp = "PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
            "\t\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"";
    /** 是否需要接口类 **/
    protected boolean isInterface = false;
    /** 注解 **/
    protected String mvcAnnotation = "";

    /**
     * 创建接口类
     */
    public String createInterfaces(DBDToMVCDefinition definition,String createBeanName,String mvcName) throws IOException {
        String location = parseLocation(definition, mvcName);
        File file = mvcFilePath(DBDToBeanContext.getDbdToMVCDefinition(), null, location);
        boolean mkdir = file.mkdirs();
        createBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), createBeanName, mvcName);
        file = new File(file +  "\\" + createBeanName + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(mvcInterContent(definition, createBeanName, mvcName));
        fw.flush();
        fw.close();
        return createBeanName;
    }
    /**
     * 创建普通类或者接口的实现类
     */
    public String createBean(DBDToMVCDefinition definition,String createBeanName,String mvcName,String mvcInterfaceName) throws IOException {
        String location = parseLocation(definition, mvcName);
        File file = mvcFilePath(definition, mvcInterfaceName, location);
        boolean mkdir = file.mkdirs();
        // 给 createBeanName 加上 Controller 或 Service 或 Dao 或 Mapper
        createBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), createBeanName, mvcName);
        file = new File(file +  "\\" + createBeanName + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write(mvcBeanContent(definition, createBeanName, mvcName, mvcInterfaceName));
        fw.flush();
        fw.close();
        return createBeanName;
    }
    /**
     * 解析路径，获取生成的路径
     */
    public File mvcFilePath(DBDToMVCDefinition definition,String mvcInterfaceName,String location){
        File file;
        if(DBDToBeanUtils.isNotEmpty(mvcInterfaceName)){
            isInterface = true;
            file = new File(System.getProperty("user.dir") + "\\" + definition.getModulesName() + "\\" + definition.getMavenOrSimple() + DBDToBeanUtils.packageToPath(location) + "\\" + IMPL_NAME);
        }else {
            isInterface = false;
            file = new File(System.getProperty("user.dir") + "\\" + definition.getModulesName() + "\\" + definition.getMavenOrSimple() + DBDToBeanUtils.packageToPath(location));
        }
        return file;
    }
    /**
     * 生成接口类内容
     */
    public String mvcInterContent(DBDToMVCDefinition definition,String createBeanName,String mvcName){
        StringBuilder content = new StringBuilder();
        String location = parseLocation(definition, mvcName);
        content.append(DBDToBeanContext.getDbdToBeanDefinition().setThenGetPackageName(location)).append("\n");
        if(DBDToBeanContext.getDefaultComment().isSetHeadComment()){
            content.append(DBDToBeanContext.getDbdToBeanDefinition().getHeadComment().generateHeadComments(DBDToBeanContext.getDbdToBeanProperties().getAuthorName()).toString());
        }
        if(definition.isMvcAnnotation() && mvcName.equals(DBDToMapper.MAPPER_INTERFACE_NAME)){
            content.insert(content.indexOf(";") + 1, "\nimport org.apache.ibatis.annotations." + mvcAnnotation.substring(1) + ";");
            content.append(mvcAnnotation).append("\n");
        }
        content.append("public interface ").append(createBeanName).append(" {\n\n");
        // 截取实体类名字
        int index = createBeanName.indexOf(mvcName);
        String entityName = createBeanName.substring(0,index);
        new DBDToCurd().generateInterCURD(content,entityName);
        content.append("}");
        return content.toString();
    }
    /**
     * 生成普通类内容或者接口的实现类内容
     */
    public String mvcBeanContent(DBDToMVCDefinition definition,String createBeanName,String mvcName,String mvcInterfaceName){
        StringBuilder content = new StringBuilder();
        String location = parseLocation(definition, mvcName);
        DBDToCurd dbdToCurd = new DBDToCurd();
        // 截取实体类名字
        int index = createBeanName.indexOf(mvcName);
        String entityName = createBeanName.substring(0, index);
        // 调用类，如 Controller 调用 Service，Service 调用 Mapper
        String importBeanName = null;
        if(isInterface){
            // Service、Dao、Mapper
            content.append(DBDToBeanContext.getDbdToBeanDefinition().setThenGetPackageName(location + "." + IMPL_NAME))
                    .append("import ").append(location).append(".").append(mvcInterfaceName).append(";\n\n");
            if(DBDToBeanContext.getDefaultComment().isSetHeadComment()){
                content.append(DBDToBeanContext.getDbdToBeanDefinition().getHeadComment().generateHeadComments(DBDToBeanContext.getDbdToBeanProperties().getAuthorName()).toString());
            }
            // if(definition.isMvcAnnotation() && mvcName.equals(DBDToDao.DAO_IMPL_NAME)){
            //     content.insert(content.indexOf(";") + 1, "\nimport org.apache.ibatis.annotations." + mvcAnnotation.substring(1) + ";");
            //     content.append(mvcAnnotation).append("\n");
            // }else if(definition.isMvcAnnotation()){
            //     content.insert(content.indexOf(";") + 1, "\nimport org.springframework.stereotype." + mvcAnnotation.substring(1) + ";");
            //     content.append(mvcAnnotation).append("\n");
            // }
            if(definition.isMvcAnnotation() && !mvcName.equals(DBDToDao.DAO_IMPL_NAME)){
                content.insert(content.indexOf(";") + 1, "\nimport org.springframework.stereotype." + mvcAnnotation.substring(1) + ";");
                content.append(mvcAnnotation).append("\n");
            }
            content.append("public class ").append(createBeanName).append(" implements ").append(mvcInterfaceName).append(" {\n\n");
            if(mvcName.equals(DBDToService.SERVICE_IMPL_NAME)){
                if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getMapperLocation())) {
                    importBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), entityName, DBDToMapper.MAPPER_INTERFACE_NAME);

                    content.insert(content.indexOf(";") + 1, "\nimport " + DBDToBeanContext.getDbdToMVCDefinition().getMapperLocation() + "." + importBeanName + ";\nimport org.springframework.beans.factory.annotation.Autowired;");
                    content.append("\t@Autowired\n\tprivate ").append(importBeanName).append(" ").append(DBDToBeanUtils.firstCharToLowerCase(importBeanName)).append(";\n\n");
                }else if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getDaoLocation())){
                    importBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), entityName, DBDToDao.DAO_IMPL_NAME);

                    content.insert(content.indexOf(";") + 1, "\nimport " + DBDToBeanContext.getDbdToMVCDefinition().getDaoLocation() + "." + IMPL_NAME + "." + importBeanName + ";\nimport org.springframework.beans.factory.annotation.Autowired;");
                    content.append("\t@Autowired\n\tprivate ").append(importBeanName).append(" ").append(DBDToBeanUtils.firstCharToLowerCase(importBeanName)).append(";\n\n");
                }
            }
        }else {
            // Controller
            content.append(DBDToBeanContext.getDbdToBeanDefinition().setThenGetPackageName(location)).append("\n");
            if(DBDToBeanContext.getDefaultComment().isSetHeadComment()){
                content.append(DBDToBeanContext.getDbdToBeanDefinition().getHeadComment().generateHeadComments(DBDToBeanContext.getDbdToBeanProperties().getAuthorName()).toString());
            }
            if(definition.isMvcAnnotation()){
                content.insert(content.indexOf(";") + 1, "import org.springframework.stereotype." + mvcAnnotation.substring(1) + ";");
                content.append(mvcAnnotation).append("\n");
            }
            content.append("public class ").append(createBeanName).append(" {\n\n");
            if(DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getServiceLocation())) {
                importBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), entityName, DBDToService.SERVICE_IMPL_NAME);
                // String serviceName = DBDToBeanUtils.firstCharToUpperCase(entityName) + DBDToService.SERVICE_IMPL_NAME;
                content.insert(content.indexOf(";") + 1, "\nimport " + DBDToBeanContext.getDbdToMVCDefinition().getServiceLocation() + "." + IMPL_NAME + "." + importBeanName + ";\nimport org.springframework.beans.factory.annotation.Autowired;\n");
                content.append("\t@Autowired\n\tprivate ").append(importBeanName).append(" ").append(DBDToBeanUtils.firstCharToLowerCase(importBeanName)).append(";\n\n");
            }
        }
        dbdToCurd.createImplCURD(content, entityName, isInterface, importBeanName);
        content.append("}");
        return content.toString();
    }
    /**
     * 解析位置，获取不同的MVC全类路径
     */
    public String parseLocation(DBDToMVCDefinition definition,String mvcName){
        String location = DBDToBeanContext.getDbdToBeanDefinition().getPackageName();
        switch (mvcName) {
            case DBDToController.CONTROLLER_NAME:
                location = definition.getControllerLocation();
                if(!mvcAnnotation.equals("@Controller"))
                    mvcAnnotation = "@Controller";
                break;
            case DBDToService.SERVICE_IMPL_NAME:
            case DBDToService.SERVICE_INTERFACE_NAME:
                location = definition.getServiceLocation();
                if(!mvcAnnotation.equals("@Service"))
                    mvcAnnotation = "@Service";
                break;
            case DBDToDao.DAO_IMPL_NAME:
            case DBDToDao.DAO_INTERFACE_NAME:
                location = definition.getDaoLocation();
                if(!mvcAnnotation.equals("@Mapper"))
                    mvcAnnotation = "@Mapper";
                break;
            case DBDToMapper.MAPPER_INTERFACE_NAME:
                location = definition.getMapperLocation();
                if(!mvcAnnotation.equals("@Mapper"))
                    mvcAnnotation = "@Mapper";
                break;
        }
        return location;
    }
    /**
     * 解析MVC的文件名字，封装文件名字的前桌和后缀
     */
    public String parseMVCName(DBDToMVCDefinition definition,String createBeanName,String mvcName){
        switch (mvcName) {
            case DBDToController.CONTROLLER_NAME:
                return definition.getPrefix() + definition.getControllerPre() + createBeanName + definition.getControllerSuf() + definition.getSuffix();
            case DBDToService.SERVICE_INTERFACE_NAME:
                return definition.getPrefix() + definition.getServiceInterPre() + createBeanName + definition.getServiceInterSuf() + definition.getSuffix();
            case DBDToService.SERVICE_IMPL_NAME:
                return definition.getPrefix() + definition.getServiceImplPre() + createBeanName + definition.getServiceImplSuf() + definition.getSuffix();
            case DBDToDao.DAO_INTERFACE_NAME:
                return definition.getPrefix() + definition.getDaoInterPre() + createBeanName + definition.getDaoInterSuf() + definition.getSuffix();
            case DBDToDao.DAO_IMPL_NAME:
                return definition.getPrefix() + definition.getDaoImplPre() + createBeanName + definition.getDaoImplSuf() + definition.getSuffix();
            case DBDToMapper.MAPPER_INTERFACE_NAME:
                return definition.getPrefix() + definition.getMapperInterPre() + createBeanName + definition.getMapperInterSuf() + definition.getSuffix();
            case DBDToMapper.MAVEN_MAPPER_XML_HONE:
                return definition.getMapperXmlPre() + createBeanName + definition.getMapperXmlSuf();
        }
        return createBeanName;
    }

}
