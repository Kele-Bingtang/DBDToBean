package cn.kbt.dbdtobean.core;


import cn.kbt.dbdtobean.config.DBDToBeanProperties;
import cn.kbt.dbdtobean.log.DBDToBeanLog;
import cn.kbt.dbdtobean.mvcbean.AbstractDBDToMVC;
import cn.kbt.dbdtobean.mvcbean.DBDToMVC;
import cn.kbt.dbdtobean.mvcbean.DBDToMVCDefinition;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBDToBean extends DBDToBeanCore {

    private boolean isMultimediaContent = false;

    public DBDToBean() {
        this(null);
    }

    public DBDToBean(String createBeanName) {
        DBDToBeanContext.getDbdToBeanDefinition().setCreateBeanName(createBeanName);
    }

    public void setAuthorName(String authorName) {
        DBDToBeanContext.getDbdToBeanProperties().setAuthorName(authorName);
    }

    public void setAuthorName(boolean authorName) {
        if (!authorName) {
            DBDToBeanContext.getDbdToBeanProperties().setAuthorName(null);
        }
    }

    public void setCreateBeanName(String createBeanName) {
        DBDToBeanContext.getDbdToBeanDefinition().setCreateBeanName(createBeanName);
    }

    public String generateAttrFromTable(String tableName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException {
        return super.generateAttrFromTable(tableName, isConstructor, isSetAndGet, isToString);
    }

    public String generateAttrFromTable(String tableName) throws SQLException {
        return generateAttrFromTable(tableName, true, true, true);
    }

    public String generateAttrFromTable(String tableName, boolean isConstructor) throws SQLException {
        return generateAttrFromTable(tableName, isConstructor, true, true);
    }

    public String generateAttrFromTable(String tableName, boolean isConstructor, boolean isSetAndGet) throws SQLException {
        return generateAttrFromTable(tableName, isConstructor, isSetAndGet, true);
    }

    public void setPackageName(String packageName) {
        DBDToBeanContext.getDbdToBeanDefinition().setPackageName(packageName);
    }

    public void setPackageName(String packageName, boolean jarPackage) {
        DBDToBeanContext.getDbdToBeanDefinition().setPackageNameAndJarPackage(packageName, jarPackage);
    }

    public void setJarPackage(boolean jarPackage) {
        DBDToBeanContext.getDbdToBeanDefinition().setJarPackage(jarPackage);
    }

    public void setHeadComment(boolean setHeadComment) {
        DBDToBeanContext.getDefaultComment().setSetHeadComment(setHeadComment);
    }

    public void setHeadComment(String headComment) {
        DBDToBeanContext.getDbdToBeanDefinition().getHeadComment().setHeadComment(headComment);
    }

    public void setCommentType(String commentType) {
        DBDToBeanContext.getCustomComment().setCommentType(commentType);
    }

    public void setHeadComment(String headComment, String commentType) {
        DBDToBeanContext.getDbdToBeanDefinition().getHeadComment().setHeadComment(headComment, commentType);
    }

    public void setAllComments(boolean generateAllComment) {
        DBDToBeanContext.getDefaultComment().setAllComments(generateAllComment);
    }

    public void setComment(boolean isFieldComment, boolean isConstructorComment, boolean isSetAndGetComment, boolean isToStringComment) {
        DBDToBeanContext.getDefaultComment().setComment(isFieldComment, isConstructorComment, isSetAndGetComment, isToStringComment);
    }

    public void setFieldComment(boolean isFieldComment) {
        setComment(isFieldComment, DBDToBeanContext.getDefaultComment().isConstructorComment(), DBDToBeanContext.getDefaultComment().isSetAndGetComment(), DBDToBeanContext.getDefaultComment().isToStringComment());
    }

    public void setFiledComment(List<String> filedComment) {
        DBDToBeanContext.getCustomComment().setFiledComment(filedComment);
    }

    public void setConstructorComment(boolean isConstructorComment) {
        setComment(DBDToBeanContext.getDefaultComment().isFieldComment(), isConstructorComment, DBDToBeanContext.getDefaultComment().isSetAndGetComment(), DBDToBeanContext.getDefaultComment().isToStringComment());
    }

    public void setConstructorComment(String constructorComment) {
        DBDToBeanContext.getCustomComment().setParamConstructorComment(constructorComment);
    }

    public void setConstructorComment(String nullConstructorComment, String constructorComment) {
        DBDToBeanContext.getCustomComment().setConstructorComment(nullConstructorComment, constructorComment);
    }

    public void setSetAndGetComment(boolean isSetAndGetComment) {
        setComment(DBDToBeanContext.getDefaultComment().isFieldComment(), DBDToBeanContext.getDefaultComment().isConstructorComment(), isSetAndGetComment, DBDToBeanContext.getDefaultComment().isToStringComment());
    }

    public void setSetAndGetComment(List<String> SetComment, List<String> GetComment) {
        DBDToBeanContext.getCustomComment().setSetAndGetComment(SetComment, GetComment);
    }

    public void setSetComment(List<String> SetComment) {
        DBDToBeanContext.getCustomComment().setSetComment(SetComment);
    }

    public void setGetComment(List<String> GetComment) {
        DBDToBeanContext.getCustomComment().setGetComment(GetComment);
    }

    public void setToStringComment(boolean isToStringComment) {
        setComment(DBDToBeanContext.getDefaultComment().isFieldComment(), DBDToBeanContext.getDefaultComment().isConstructorComment(), DBDToBeanContext.getDefaultComment().isSetAndGetComment(), isToStringComment);
    }

    public void setToStringComment(String toStringComment) {
        DBDToBeanContext.getCustomComment().setToStringComment(toStringComment);
    }

    public void setBeanFirstNameIsUp(boolean beanFirstNameUp) {
        DBDToBeanContext.getDbdToBeanDefinition().setBeanFirstNameUp(beanFirstNameUp);
    }

    public void setFieldNameAllLower(boolean fieldNameAllLower) {
        DBDToBeanContext.getDbdToBeanDefinition().setFieldNameAllLower(fieldNameAllLower);
    }

    public void generateAttrFromDataBase(boolean fieldNameAllLower) throws SQLException, IOException {
        DBDToBeanContext.getDbdToBeanDefinition().setFieldNameAllLower(fieldNameAllLower);
        generateAttrFromDataBase(null, true, true, true);
    }

    public HashMap<String, String> generateAttrFromDataBase() throws SQLException, IOException {
        return generateAttrFromDataBase(getConnection().getCatalog(), true, true, true);
    }

    public HashMap<String, String> generateAttrFromDataBase(String dateBaseName) throws SQLException, IOException {
        return generateAttrFromDataBase(dateBaseName, true, true, true);
    }

    public HashMap<String, String> generateAttrFromDataBase(String dateBaseName, boolean isConstructor) throws SQLException, IOException {
        return generateAttrFromDataBase(dateBaseName, isConstructor, true, true);
    }

    public HashMap<String, String> generateAttrFromDataBase(String dateBaseName, boolean isConstructor, boolean isSetAndGet) throws SQLException, IOException {
        return generateAttrFromDataBase(dateBaseName, isConstructor, isSetAndGet, true);
    }

    public HashMap<String, String> generateAttrFromDataBase(String dateBaseName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException, IOException {
        return super.generateAttrFromDataBase(dateBaseName, isConstructor, isSetAndGet, isToString);
    }

    public String exportToFile(String fileContent) throws IOException {
        String createPath = exportToFile(fileContent, null, null);
        System.out.println("创建【" + DBDToBeanContext.getDbdToBeanDefinition().getCreateBeanName() + "】文件成功，位于：" + new File(createPath).getAbsolutePath());
        return createPath;
    }

    public String exportToFile(String fileContent, String path) throws IOException {
        String createPath = exportToFile(fileContent, path, null);
        System.out.println("创建【" + DBDToBeanContext.getDbdToBeanDefinition().getCreateBeanName() + "】文件成功，位于：" + new File(createPath).getAbsolutePath());
        return createPath;
    }

    public String exportToFile(String fileContent, String path, String dirName) throws IOException {
        if (!isMultimediaContent) {
            if (DBDToBeanUtils.isEmpty(path)) {
                if (DBDToBeanUtils.isEmpty(dirName) && DBDToBeanUtils.isEmpty(DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation())) {
                    System.out.println("正在【电脑桌面】路径下为您创建【JavaBean文件】");
                } else if (DBDToBeanUtils.isEmpty(DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation())) {
                    System.out.println("正在【电脑桌面】路径下的【" + dirName + "】文件夹里为您创建【JavaBean文件】");
                }
            } else if (DBDToBeanUtils.isEmpty(dirName)) {
                System.out.println("正在【" + path + "】路径下为您创建【JavaBean文件】");
            } else {
                System.out.println("正在【" + path + "】路径下的【" + dirName + "】文件夹里为您创建【JavaBean文件】");
            }
        }
        String createPath = super.exportToFile(fileContent, path, dirName);
        DBDToMVC dbdToMVC = new DBDToMVC();
        dbdToMVC.dbdToMVC();
        return createPath;
    }

    public String exportToFile(HashMap<String, String> fileContentMap) throws IOException, SQLException {
        return this.exportToFile(fileContentMap, null, null);
    }

    public String exportToFile(HashMap<String, String> fileContentMap, String path) throws IOException, SQLException {
        return this.exportToFile(fileContentMap, path, null);
    }

    public String exportToFile(HashMap<String, String> fileContentMap, String path, String dirName) throws IOException, SQLException {
        isMultimediaContent = true;
        String createPath = "";
        System.out.println("系统检测到提供的数据库共有【" + fileContentMap.size() + "】个表，准备生成文件");
        if ((DBDToBeanUtils.isEmpty(dirName) || dirName.equals(" ")) && DBDToBeanUtils.isEmpty(path)) {
            // 默认生成一个文件夹，以数据库名字+随机数字为文件夹名
            if (DBDToBeanUtils.isEmpty(DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation())) {
                dirName = super.getConnection().getMetaData().getDatabaseProductName() + "_" + DBDToBeanUtils.randomNum();
            }
            // 生成文件夹
            createPath = super.exportToFile("", path, dirName);
            System.out.println("在【" + createPath + "】处创建成功！");
        }
        int i = 1;
        path = path != null ? path : super.beanLocation().getPath();
        System.out.println("正在【" + path + "】路径下" + (dirName == null ? "" : "的【" + dirName + "】文件夹里") + "为您创建【JavaBean文件】:");
        for (Map.Entry<String, String> entry : fileContentMap.entrySet()) {
            System.out.println("正在创建第【" + i++ + "】个文件：" + entry.getKey());
            createPath = super.exportToFiles(entry.getKey(), entry.getValue(), path, dirName);
        }
        DBDToMVC dbdToMVC = new DBDToMVC();
        dbdToMVC.dbdToMVC();
        System.out.println("所有文件创建完毕，位于" + new File(createPath).getAbsolutePath());
        return createPath;
    }

    public void set_ToUpper(boolean _ToUpper) {
        DBDToBeanContext.getDbdToBeanDefinition().set_ToUpper(_ToUpper);
    }

    public String firstCharToUpperCase(String fieldName) {
        return DBDToBeanUtils.firstCharToUpperCase(fieldName);
    }

    public String firstCharToLowerCase(String fieldName) {
        return DBDToBeanUtils.firstCharToLowerCase(fieldName);
    }

    public String _CharToUpperCase(String name) {
        return DBDToBeanUtils._CharToUpperCase(name);
    }

    public void setDbdToBeanProperties(String driverName, String url, String username, String password) {
        DBDToBeanContext.getDbdToBeanProperties().setDriverName(driverName);
        DBDToBeanContext.getDbdToBeanProperties().setUrl(url);
        DBDToBeanContext.getDbdToBeanProperties().setUsername(username);
        DBDToBeanContext.getDbdToBeanProperties().setPassword(password);
    }

    public void setModulesName(String modulesName) {
        DBDToBeanContext.getDbdToMVCDefinition().setModulesName(modulesName);
    }

    public void setEntityLocation(String entityLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setEntityLocation(entityLocation);
    }

    public void setControllerLocation(String controllerLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setControllerLocation(controllerLocation);
    }

    public void setServiceLocation(String serviceLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setServiceLocation(serviceLocation);
    }

    public void setDaoLocation(String daoLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setDaoLocation(daoLocation);
    }

    public void setMapperLocation(String mapperLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperLocation(mapperLocation);
    }

    public String getMapperXMLLocation() {
        return DBDToBeanContext.getDbdToMVCDefinition().getMapperXmlLocation();
    }

    public void setMapperXMLLocation(String mapperXMLLocation) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperXmlLocation(mapperXMLLocation);
    }

    public void setConnection(Connection conn) {
        DBDToBeanContext.getDbdToBeanProperties().setConn(conn);
    }

    public void closeConnection() throws SQLException {
        if (null != DBDToBeanContext.getDbdToBeanProperties().getConn()) {
            DBDToBeanContext.getDbdToBeanProperties().getConn().close();
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void setDbdToBeanProperties(DBDToBeanProperties dbdToBeanPorperties) {
        DBDToBeanContext.setDbdToBeanProperties(dbdToBeanPorperties);
    }

    public String getDateBaseType() throws SQLException {
        super.parseDateBaseTypeAndGetSQL("");
        return DBDToBeanContext.getDbdToBeanDefinition().getDateBaseType();
    }

    // ----------------------------------- DBDToMVC -----------------------------------

    public void setPrefix(String prefix) {
        DBDToBeanContext.getDbdToMVCDefinition().setPrefix(prefix);
    }

    public void setSuffix(String suffix) {
        DBDToBeanContext.getDbdToMVCDefinition().setSuffix(suffix);
    }

    public void setControllerPre(String controllerPre) {
        DBDToBeanContext.getDbdToMVCDefinition().setControllerPre(controllerPre);
    }

    public void setControllerSuf(String controllerSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setControllerSuf(controllerSuf);
    }

    public void setServiceInterPre(String servicePre) {
        DBDToBeanContext.getDbdToMVCDefinition().setServiceInterPre(servicePre);
    }

    public void setServiceInterSuf(String serviceSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setServiceInterSuf(serviceSuf);
    }

    public void setServiceImplPre(String servicePre) {
        DBDToBeanContext.getDbdToMVCDefinition().setServiceImplPre(servicePre);
    }

    public void setServiceImplSuf(String serviceSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setServiceImplSuf(serviceSuf);
    }

    public void setDaoInterPre(String daoPre) {
        DBDToBeanContext.getDbdToMVCDefinition().setDaoInterPre(daoPre);
    }

    public void setDaoInterSuf(String daoSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setDaoInterSuf(daoSuf);
    }

    public void setDaoImplPre(String daoPre) {
        DBDToBeanContext.getDbdToMVCDefinition().setDaoImplPre(daoPre);
    }

    public void setDaoImplSuf(String daoSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setDaoImplSuf(daoSuf);
    }


    public void setMapperInterPre(String mapperPre) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperInterPre(mapperPre);
    }

    public void setMapperInterSuf(String mapperSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperInterSuf(mapperSuf);
    }

    public void setMapperXmlPre(String mapperPre) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperXmlPre(mapperPre);
    }

    public void setMapperXmlSuf(String mapperSuf) {
        DBDToBeanContext.getDbdToMVCDefinition().setMapperXmlSuf(mapperSuf);
    }

    public DBDToBeanDefinition getDbdToBeanDefinition() {
        return DBDToBeanContext.getDbdToBeanDefinition();
    }

    public List<DBDToBeanDefinition> getDbdToBeanDefinitions() {
        return DBDToBeanContext.getDbdToBeanDefinitions();
    }

    public DBDToMVCDefinition getDbdToMVCDefinition() {
        return DBDToBeanContext.getDbdToMVCDefinition();
    }

    public void setGenerateCURD(boolean generateCURD) {
        DBDToBeanContext.getDbdToMVCDefinition().setGeneratecurd(generateCURD);
    }

    public void setMVImplName(String implLocation) {
        AbstractDBDToMVC.IMPL_NAME = implLocation;
    }

    public void setXmlPublicAndHttp(String xmlPublicAndHttp) {
        AbstractDBDToMVC.xmlPublicAndHttp = xmlPublicAndHttp;
    }

    public void setMavenOrSimple(boolean mavenOrSimple) {
        DBDToBeanContext.getDbdToMVCDefinition().setMavenOrSimple(mavenOrSimple);
    }

    public DBDToBeanLog getLogInfo() {
        return new DBDToBeanLog();
    }

    public void explain(ResultSet rs) throws SQLException {
        DBDToBeanUtils.explain(rs);
    }

}
