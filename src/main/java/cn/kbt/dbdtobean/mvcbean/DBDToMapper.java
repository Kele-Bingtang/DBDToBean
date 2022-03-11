package cn.kbt.dbdtobean.mvcbean;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/21 23:09
 */
public class DBDToMapper extends AbstractDBDToMVC {

    protected final static String MAPPER_INTERFACE_NAME = "Mapper";
    protected final static String MAVEN_MAPPER_XML_HONE = "src\\main\\resources\\";
    protected final static String SIMPLE_MAPPER_XML_HONE = "src\\";
    protected static String interfacesName = null;
    private String entityName;

    /**
     * 生成Mapper层接口目录以及内容
     *
     * @param createBeanName 文件名
     * @return 内容
     * @throws IOException IO 异常
     */
    public String mapperInterfaces(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        interfacesName = super.createInterfaces(definition, createBeanName, MAPPER_INTERFACE_NAME);
        return interfacesName;
    }

    /**
     * 生成Mapper层xml文件目录以及内容
     *
     * @param createBeanName 文件名
     * @throws IOException IO 异常
     */
    public void mapperXML(String createBeanName) throws IOException {
        createBeanName = DBDToBeanUtils._CharToUpperCase(createBeanName);
        this.entityName = createBeanName;
        DBDToMVCDefinition definition = DBDToBeanContext.getDbdToMVCDefinition();
        File file = new File(System.getProperty("user.dir") + "\\" + definition.getModulesName() + "\\" + definition.getMapperPath() + DBDToBeanUtils.packageToPath(definition.getMapperXmlLocation()));
        boolean mkdir = file.mkdir();
        String createFileName = parseMVCName(definition, createBeanName, MAVEN_MAPPER_XML_HONE);
        file = new File(file + "\\" + createFileName + ".xml");
        FileWriter fw = new FileWriter(file);
        fw.write(createXmlStart());
        fw.flush();
        fw.close();
    }

    /**
     * 生成Mapper层xml文件开头配置
     *
     * @return 内容
     */
    public String createXmlStart() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper\n\t\t" +
                xmlPublicAndHttp +
                ">\n" +
                "<mapper namespace = \"" +
                DBDToBeanContext.getDbdToMVCDefinition().getMapperLocation() + "." + interfacesName +
                "\">" + (DBDToBeanContext.getDbdToMVCDefinition().isGeneratecurd() ? "\n\n\t" + createXmlBeansCURD() : "") +
                "\n\n</mapper>";
    }

    /**
     * 生成Mapper层xml文件CURD目录
     *
     * @return 内容
     */
    public String createXmlBeansCURD() {
        return "<select id=\"query" + entityName + "ById\" " +
                " parameterType=\"string\"" +
                " resultType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + entityName + "\">" +
                "\n\t\t\n" +
                "\t</select>\n\n" + "\t" +

                "<select id=\"query" + entityName + "List\"" +
                " resultType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + entityName + "\">" +
                "\n\t\t\n" + "\t</select>\n\n" + "\t" +

                "<insert id=\"insert" + entityName + "\"" +
                " parameterType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + entityName + "\">" +
                "\n\t\t\n" + "\t</insert>\n\n" + "\t" +

                "<update id=\"update" + entityName + "\"" +
                " parameterType=\"" +
                DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation() + "." + entityName + "\">" +
                "\n\t\t\n" + "\t</update>\n\n" + "\t" +

                "<delete id=\"delete" + entityName + "ById\"" +
                " parameterType=\"string\">" +
                "\n\t\t\n" + "\t</delete>";
    }
}
