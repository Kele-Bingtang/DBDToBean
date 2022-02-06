package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 23:09
 * @Version 1.0
 * @Describe
 */
public class DBDToMapper extends AbstractDBDToMVC{

    protected final static String MAPPER_INTERFACE_NAME = "Mapper";
    protected final static String MAVEN_MAPPER_XML_HONE = "src\\main\\resources\\";
    protected final static String SIMPLE_MAPPER_XML_HONE = "src\\";
    protected static String interfacesName = null;

    /**
     * 生成Mapper层接口目录以及内容
     */
    public String mapperInterfaces(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        interfacesName = super.createInterfaces(definition, createBeanName, MAPPER_INTERFACE_NAME);
        return interfacesName;
    }
    /**
     * 生成Mapper层xml文件目录以及内容
     */
    public void mapperXML(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        File file = new File(System.getProperty("user.dir") + "\\" + definition.getModulesName() + "\\" + definition.getMapperPath() + DBDToBeanUtils.packageToPath(definition.getMapperXmlLocation()));
        boolean mkdir = file.mkdir();
        createBeanName = parseMVCName(DBDToBeanContext.getDbdToMVCDefinition(), createBeanName, MAVEN_MAPPER_XML_HONE);
        file = new File(file +  "\\" + createBeanName + ".xml");
        FileWriter fw = new FileWriter(file);
        fw.write(createXmlStart());
        fw.flush();
        fw.close();
    }
    /**
     * 生成Mapper层xml文件开头配置
     */
    public String createXmlStart(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper\n\t\t" +
                xmlPublicAndHttp +
                 ">\n" +
                "<mapper namespace = \"" +
                DBDToBeanContext.getDbdToMVCDefinition().getMapperLocation() +  "." + interfacesName +
                "\">" + (DBDToBeanContext.getDbdToMVCDefinition().isGeneratecurd() ? "\n\n\t" + createXmlBeansCURD() : "") + 
                "\n\n</mapper>";
    }
    /**
     * 生成Mapper层xml文件CURD目录
     */
    public String createXmlBeansCURD(){
        String beanName = DBDToBeanUtils.firstCharToUpperCase(DBDToBeanContext.getDbdToBeanDefinition().getCreateBeanName());
        return "<select id=\"query" + beanName + "ById\" " +
                " parameterType=\"string\"" +
                " resultType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + beanName + "\">" +
                "\n\t\t\n" +
                "\t</select>\n\n" + "\t" + 
                
                "<select id=\"query" + beanName + "s\"" +
                " resultType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + beanName + "\">" +
                "\n\t\t\n" + "\t</select>\n\n" + "\t" +

                "<insert id=\"insert"+ beanName + "\"" + 
                " parameterType=\"" + 
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + beanName + "\">" +
                "\n\t\t\n" + "\t</insert>\n\n" + "\t" +

                "<update id=\"update"+ beanName + "\"" +
                " parameterType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + beanName + "\">" +
                "\n\t\t\n" + "\t</update>\n\n" + "\t" +

                "<delete id=\"delete"+ beanName + "ById\"" +
                " parameterType=\"string\">" +
                "\n\t\t\n" + "\t</delete>";
    }
}
