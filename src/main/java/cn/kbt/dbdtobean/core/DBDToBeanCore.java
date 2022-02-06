package cn.kbt.dbdtobean.core;

import cn.kbt.dbdtobean.config.DBDToBeanProperties;
import cn.kbt.dbdtobean.inter.IDBDToBeanCore;
import cn.kbt.dbdtobean.mvcbean.DBDToMVCDefinition;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public abstract class DBDToBeanCore implements IDBDToBeanCore {
    /** 数据库信息 **/
    private ResultSetMetaData JDBCData = null;
    /** 生成的文件名字 **/
    private String createBeanName;

    protected DBDToBeanCore() {
    }

    /**
     * 1、生成JavaBean的属性值
     * 2、是否构造函数
     * 3、是否生成set和get语句
     * 4、是否重写toString方法
     */
    @Override
    public String generateAttrFromTable(String tableName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException {
        DBDToBeanProperties dbdToBeanProperties = DBDToBeanContext.getDbdToBeanProperties();
        if(tableName == null){
            System.out.println("请输入要导出的表名或者数据库名");
            return null;
        }
        PreparedStatement stmt = getConnection().prepareStatement("select * from " + tableName);
        ResultSet rs = stmt.executeQuery();
        //通过结果集获取数据库信息
        JDBCData = rs.getMetaData();
        // 核心，存储JavaBean文件内容的缓冲区
        StringBuilder sb = new StringBuilder();
        // 判断使用什么数据库
        parseDateBaseTypeAndGetSQL("");
        // 获取定义的信息
        DBDToBeanDefinition definition = DBDToBeanContext.getDbdToBeanDefinition();
        createBeanName = definition.getCreateBeanName();
        //是否生成类注释，格式为 @author    @create   @version  @Describe
        if(DBDToBeanContext.getDefaultComment().isSetHeadComment() && DBDToBeanUtils.isEmpty(definition.getHeadComment().getHeadComments().toString())){
            definition.getHeadComment().generateHeadComments(dbdToBeanProperties.getAuthorName());
        }
        sb.append(definition.setThenGetPackageName(definition.getPackageName()));
        //添加第三方jar包
        addJarPackage(sb);
        //添加自定义类注释或者默认注释
        sb.append(definition.getHeadComment().getHeadComments().toString());
        //判断生成的JavaBean文件名是否为空，为空则以数据库表名为文件名
        if (DBDToBeanUtils.isEmpty(createBeanName)) {
           DBDToBeanContext.getDbdToBeanDefinitions().add(parseBeanName(definition,tableName));
        }
        //创建属性值
        createField(sb, getColumnInfo(tableName));
        //创建构造函数
        createConstructor(sb, isConstructor);
        //创建set和get方法
        createSetAndGet(sb, isSetAndGet, getColumnInfo(tableName));
        //创建重写toString方法
        createToString(sb, isToString);
        //最后关闭资源
        DBDToBeanUtils.close(rs, stmt);
        return sb.toString();
    }

    /**
     * 读取数据库，创建属性值
     */
    private void createField(StringBuilder sb, ResultSet columnsInfo) throws SQLException {
        sb.append("public class ").append(createBeanName).append(" {\n");
        for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
            //添加自定义注释，长度不满足则生成规定的注释
            DBDToBeanContext.getCustomComment().customFiledComment(sb, columnsInfo, parseFieldName(JDBCData.getColumnName(i)) + " ：",i);
            sb.append("\tprivate ").append(fieldType(JDBCData.getColumnClassName(i)))
                    .append(" ").append(parseFieldName(JDBCData.getColumnName(i))).append(";").append("\n");
        }
        sb.append("}");
    }

    /**
     * 读取数据库，创建构造器
     */
    private void createConstructor(StringBuilder sb, boolean isConstructor) throws SQLException {
        if (isConstructor) {
            //去掉 }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            // 是否生成无参构造器注释，没有自定义注释则生成规定的注释
            DBDToBeanContext.getCustomComment().customConstructComment(sb,true);
            sb.append("\tpublic ").append(createBeanName).append("() {\n\t}\n\n");
            //是否生成有参构造器注释，没有自定义注释则生成规定的注释
            DBDToBeanContext.getCustomComment().customConstructComment(sb,false);
            sb.append("\tpublic ").append(createBeanName).append("(");
            for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
                sb.append(fieldType(JDBCData.getColumnClassName(i))).append(" ")
                        .append(parseFieldName(JDBCData.getColumnName(i))).append(", ");
            }
            // 把最后的", "和去掉
            sb.setLength(sb.length() - 2);
            sb.append(") {\n");
            for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
                sb.append("\t\tthis.").append(parseFieldName(JDBCData.getColumnName(i))).append(" = ")
                        .append(parseFieldName(JDBCData.getColumnName(i))).append(";\n");
            }
            sb.append("\t}").append("\n}");
        }
    }

    /**
     * 读取数据库，创建set和get方法
     */
    private void createSetAndGet(StringBuilder sb, boolean isSetAndGet, ResultSet columnsInfo) throws SQLException {
        if (isSetAndGet) {
            //去掉 } 和换行
            sb.setLength(sb.length() - 2);
            sb.append("\n\n");
            for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
                String columnName = JDBCData.getColumnName(i);
                String columnClassName = JDBCData.getColumnClassName(i);
                //是否生成get注释，没有自定义注释则生成规定的注释
                DBDToBeanContext.getCustomComment().customSetGetComment(sb,columnsInfo,parseFieldName(columnName),i,false);
                String setAndGetContent = DBDToBeanUtils.isTwoCharUpper(parseFieldName(columnName))?parseFieldName(columnName):DBDToBeanUtils.firstCharToUpperCase(parseFieldName(columnName));
                sb.append("\tpublic ").append(fieldType(columnClassName)).append(" get")
                        .append(setAndGetContent).append("() {\n\t\t").append("return ")
                        .append(parseFieldName(columnName)).append(";\n\t}\n\n");
                //是否生成set注释，没有自定义注释则生成规定的注释
                DBDToBeanContext.getCustomComment().customSetGetComment(sb,columnsInfo,columnName,i,true);
                sb.append("\tpublic void set").append(setAndGetContent)
                        .append("(").append(fieldType(columnClassName)).append(" ")
                        .append(parseFieldName(columnName)).append(") {\n\t\t").append("this.")
                        .append(parseFieldName(columnName)).append(" = ").append(parseFieldName(columnName))
                        .append(";\n\t}\n\n");
            }
            sb.setLength(sb.length() - 2);
            sb.append("\n}");
        }
    }

    /**
     * 读取数据库，创建重写toString方法
     */
    private void createToString(StringBuilder sb, boolean isToString) throws SQLException {
        if (isToString) {
            sb.setLength(sb.length() - 2);
            sb.append("\n\n");
            DBDToBeanContext.getCustomComment().customToString(sb);
            sb.append("\t@Override\n\t").append("public String toString(){\n\t\t").append("return \"")
                    .append(createBeanName).append(" {\" + ")
                    .append("\n\t\t\t\t");
            for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
                String columns = JDBCData.getColumnName(i);
                sb.append("\", ").append(parseFieldName(columns)).append("='\"").append(" + ")
                        .append(parseFieldName(columns)).append(" + '\\'' + ").append("\n\t\t\t\t");
            }
            sb.append("\"}\";\n\t}");
            sb.append("\n}");
        }
    }

    /**
     * 读取数据库，在指定路径生成JavaBean文件
     * 文件名 由 表名构成
     */
    @Override
    public HashMap<String,String> generateAttrFromDataBase(String dateBaseName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException, IOException {
        String dateBaseType = DBDToBeanContext.getDbdToBeanDefinition().getDateBaseType();
        //获取不同数据库的不同的查询多表的sql语句
        PreparedStatement stmt = getConnection().prepareStatement(parseDateBaseTypeAndGetSQL(dateBaseName));
        ResultSet rs = stmt.executeQuery();
        //存储整个数据库的生成的bean文件内容
        HashMap<String,String> fileContentMap = new HashMap<>();
        while (rs.next()) {
            String tableName;
            //文件名首字母是否大写
            DBDToBeanContext.getDbdToBeanDefinitions().add(parseBeanName(DBDToBeanContext.getDbdToBeanDefinition(), rs.getString(1)));
            if (dateBaseType.equals("MySQL")) {
                tableName = rs.getString(1);
            } else if (dateBaseType.equals("Oracle")) {
                tableName = rs.getString(1).toLowerCase();
            } else {
                System.out.println("既不是Oracle也不是MySQL，该工具仅适配这两个数据库，其他数据库默认以MySQL数据库形式导出，如果默认形式报错，且需要其他数据库，请手动在下面添加需要数据库的的sql语句");
                return null;
            }
            String content = generateAttrFromTable(tableName, isConstructor, isSetAndGet, isToString);
            fileContentMap.put(createBeanName, content);
        }
        DBDToBeanUtils.close(rs, stmt);
        return fileContentMap;
    }

    /**
     * 将数据导出为java文件
     */
    @Override
    public String exportToFile(String fileContent, String path, String dirName) throws IOException {
        File location = beanLocation();
        String createPath;
        boolean mkdir;
        // 生成路径为空，则默认生成路径为桌面
        if (DBDToBeanUtils.isEmpty(path) || path.equals(" ")) {
            path = location.getPath();
        } else { // 生成路径不为空，如果路径最后有\，则去掉，生成文件时再加\，统一要求
            if (path.lastIndexOf("\\") == path.length() - 1) {
                path = path.substring(0, path.lastIndexOf("\\"));
            }
        }
        // 文件夹名不为空，则文件放到文件夹里
        if (DBDToBeanUtils.isNotEmpty(dirName) && !dirName.equals(" ")) {
            createPath = path + "\\" + dirName + "\\";
            mkdir = new File(createPath).mkdir();
            createPath = createPath + createBeanName + ".java";
        } else {  // 文件夹名为空，则文件放到生成路径下
            dirName = "";
            mkdir = new File(path).mkdir();
            createPath = path + "\\" + createBeanName + ".java";
        }
        // 如果要生成的缓存存区不为空，则生成文件，否则提示报错
        if (DBDToBeanUtils.isNotEmpty(fileContent)) {
            File file = new File(createPath);
            FileWriter fw = new FileWriter(file);
            fw.write(fileContent);
            fw.flush();
            fw.close();
        } else if (fileContent == null) {
            System.out.println("输出的内容为空");
        } else {
            System.out.println("正在【 " + path + " 】路径下为您创建随机文件夹名：【" + dirName + "】，并生成【JavaBean文件】");
        }
        return path + "\\" + dirName;
    }
    /**
     * 将数据导出为java文件
     */
    protected String exportToFiles(String createBeanName,String fileContent, String path, String dirName) throws IOException {
        this.createBeanName = createBeanName;
        return exportToFile(fileContent, path, dirName);
    }
    /**
     * 获取生成路径
     */
    protected File beanLocation(){
        DBDToMVCDefinition dbdToMVCDefinition = DBDToBeanContext.getDbdToMVCDefinition();
        // 默认生成路径：桌面
        if(DBDToBeanUtils.isNotEmpty(dbdToMVCDefinition.getEntityLocation())){
            return new File(System.getProperty("user.dir") + "\\" + dbdToMVCDefinition.getModulesName() + "\\" + dbdToMVCDefinition.getMavenOrSimple() + DBDToBeanUtils.packageToPath(dbdToMVCDefinition.getEntityLocation()));
        }else {
            return FileSystemView.getFileSystemView().getHomeDirectory();
        }
    }

    /**
     * 将数据库的对应Java的类型截取出来(去掉包名)
     */
    private String fieldType(String dataType) {
        int index = dataType.lastIndexOf(".");
        return dataType.substring(index + 1);
    }

    /**
     * 获取表名
     * 获取数据库的表的全部列信息
     */
    private ResultSet getColumnInfo(String tableName) throws SQLException {
        DatabaseMetaData metaData = getConnection().getMetaData();
        //参数：1.数据库名称，可通过数据库对象获取 2.数据库的登录名，可通过数据库对象获取 3.表名称，null代表获取所有表 4.类型类型(tabke,view等)
        ResultSet tables = metaData.getTables(getConnection().getCatalog(), getConnection().getMetaData().getUserName(), null, new String[]{"TABLE"});
        if (tables.next()) {
            //同理，不过此时的表是指定的表
            return metaData.getColumns(getConnection().getCatalog(), getConnection().getMetaData().getUserName(), tableName.toUpperCase(), null);
        }
        return null;
    }

    /**
     * 添加导入的jar包语句
     */
    private StringBuilder addJarPackage(StringBuilder sb) throws SQLException {
        if (DBDToBeanContext.getDbdToBeanDefinition().isJarPackage()) {
            for (int i = 1; i <= JDBCData.getColumnCount(); i++) {
                if (!JDBCData.getColumnClassName(i).startsWith("java.lang") && sb.indexOf(JDBCData.getColumnClassName(i)) == -1) {
                    sb.append("\n").append("import ").append(JDBCData.getColumnClassName(i)).append(";");
                }
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb;
    }
    /**
     * 解析生成的文件名字，判断是否首字母大写
     */
    private DBDToBeanDefinition parseBeanName(DBDToBeanDefinition definition,String tableName){
        //文件名首字母大写或者小写，如果Oracle表，先转小写，再将首字母大写
        if (definition.isBeanFirstNameUp()) {
            definition.setCreateBeanName(DBDToBeanUtils.firstCharToUpperCase(tableName.toLowerCase()));
        } else {
            definition.setCreateBeanName(tableName.toLowerCase());
        }
        definition.setCreateBeanName(DBDToBeanUtils._CharToUpperCase(definition.getCreateBeanName()));
        createBeanName = definition.getCreateBeanName();
        DBDToBeanDefinition dbdToBeanDefinition = new DBDToBeanDefinition();
        dbdToBeanDefinition.setCreateBeanName(definition.getCreateBeanName());
        return dbdToBeanDefinition;
    }
    /**
     * 解析数据库，获取数据库类型以及查询表的sql语句
     */
    protected String parseDateBaseTypeAndGetSQL(String dateBaseName) throws SQLException {
        DBDToBeanProperties dbdToBeanProperties = DBDToBeanContext.getDbdToBeanProperties();
        String sql = "select table_name from information_schema.tables where table_schema = '" + dateBaseName + "'";;
        //不用数据库的查询全部表的sql语句
        if (dbdToBeanProperties.getConn().getMetaData().getDatabaseProductName().equals("MySQL")) {
            sql = "select table_name from information_schema.tables where table_schema = '" + dateBaseName + "'";
            //获取数据库类型
            DBDToBeanContext.getDbdToBeanDefinition().setDateBaseType("MySQL");
        } else if (dbdToBeanProperties.getConn().getMetaData().getDatabaseProductName().equals("Oracle")) {
            sql = "select table_name from user_tables";
            //获取数据库类型
            DBDToBeanContext.getDbdToBeanDefinition().setDateBaseType("Oracle");
        } else {   //手动添加其他数据库查询全部表的语句
            DBDToBeanContext.getDbdToBeanDefinition().setDateBaseType("MySQL");
            System.out.println("既不是Oracle也不是MySQL，该工具仅适配这两个数据库，其他数据库默认以MySQL数据库形式导出，如果默认形式报错，且需要其他数据库，请手动在下面添加需要数据库的的sql语句");
        }
        return sql;
    }

    /**
     * 判断大小写
     * 返回不同的参数
     */
    private String parseFieldName(String content) {
        DBDToBeanDefinition definition = DBDToBeanContext.getDbdToBeanDefinition();
        if (definition.isFieldNameAllLower() && definition.is_ToUpper()) {
            return DBDToBeanUtils._CharToUpperCase(content.toLowerCase());
        }else if ((definition.isFieldNameAllLower())) {
            return content.toLowerCase();
        }else if(definition.is_ToUpper()) {
            return DBDToBeanUtils._CharToUpperCase(content);
        }else {
            return content;
        }
    }
    /**
     * 获取数据库源对象
     */
    public Connection getConnection() {
        return DBDToBeanContext.getDbdToBeanProperties().getConn();
    }

}
