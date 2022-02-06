package cn.kbt.dbdtobean.log;

/**
 * @Author Kele-Bing
 * @Create 2021/9/25 16:46
 * @Version 1.0
 * @Describe API文档
 */
public class DBDToBeanAPI {

    public String dbdToBeanEntityAPI(){
        return "\tString driverName = \"\";\n" +
                "\tString url = \"\";\n" +
                "\tString username = \"\";\n" +
                "\tString password = \"\";" +
                "\tConnection connection = DBDToBeanUtils.getMysqlConnection(driverName, url, username, password);\n" +
                "\tDBDToBean dbdToBean = new DBDToBean();\n" +
                "\tdbdToBean.setConnection(connection);\n" +
                "\tdbdToBean.setPackageName(\"包路径\"); // 设置实体类包路径\n" +
                "\tdbdToBean.setAuthorName(\"作者名\"); // 设置作者名\n" +
                "\tdbdToBean.set_ToUpper(true); // 开启驼峰命名\n" +
                "\tdbdToBean.setAllComments(true); // 打开注释\n" +
                "\tdbdToBean.setHeadComment(true); // 打开类注释\n" +
                "\tdbdToBean.setCommentType(\"/*\"); // 三种注释类型：//  /*  /**\n" +
                "\tdbdToBean.setBeanFirstNameIsUp(true); // 文件名首字母大写\n" +
                "dbdToBean.setEntityLocation(\"cn.kbt.bean\"); // 实体类的包名，不填写，则默认生成在桌面" + 
                "\tString beanContent = dbdToBean.generateAttrFromTable(\"表名\"); // 生成实体类内容\n" +
                "\tString createPath = dbdToBean.exportToFile(beanContent); // 导出到实体类文件\n" +
                "\tdbdToBean.closeConnection(); // 关闭数据库";
    }

    public String dbdToBeanEntityListAPI(){
        return "\tString driverName = \"\";\n" +
                "\tString url = \"\";\n" +
                "\tString username = \"\";\n" +
                "\tString password = \"\";" +
                "\tConnection connection = DBDToBeanUtils.getMysqlConnection(driverName, url, username, password);\n" +
                "\tDBDToBean dbdToBean = new DBDToBean();\n" +
                "\tdbdToBean.setConnection(connection);\n" +
                "\tdbdToBean.setPackageName(\"包路径\"); // 设置实体类包路径\n" +
                "\tdbdToBean.setAuthorName(\"作者名\"); // 设置作者名\n" +
                "\tdbdToBean.set_ToUpper(true); // 开启驼峰命名\n" +
                "\tdbdToBean.setAllComments(true); // 打开注释\n" +
                "\tdbdToBean.setHeadComment(true); // 打开类注释\n" +
                "\tdbdToBean.setCommentType(\"/*\"); // 注释类型：//  /*  /**\n" +
                "\tdbdToBean.setBeanFirstNameIsUp(true); // 文件名首字母大写\n" +
                "dbdToBean.setEntityLocation(\"cn.kbt.bean\"); // 实体类的包名，不填写，则默认生成在桌面" +
                "\tHashMap<String, String> beanContents = dbdToBean.generateAttrFromDataBase(); // 读取 url 里数据库的所有表\n" +
                "\tString createPath = dbdToBean.exportToFile(beanContents); // 导出到实体类文件\n" +
                "\tdbdToBean.closeConnection(); // 关闭数据库";
    }

    public String dbdToBeanMVCAPI(){
        return "\tConnection connection = DBDToBeanUtils.getMysqlConnection(driverName, url, username, password);\n" +
                "\tDBDToBean dbdToBean = new DBDToBean();\n" +
                "\tdbdToBean.setConnection(connection);\n" +
                "\tdbdToBean.setPackageName(\"包路径\"); // 设置实体类包路径\n" +
                "\tdbdToBean.setAuthorName(\"作者名\"); // 设置作者名\n" +
                "\tdbdToBean.set_ToUpper(true); // 开启驼峰命名\n" +
                "\tdbdToBean.setAllComments(true); // 打开注释\n" +
                "\tdbdToBean.setHeadComment(true); // 打开类注释\n" +
                "\tdbdToBean.setCommentType(\"/*\"); // 注释类型：//  /*  /**\n" +
                "\tdbdToBean.setBeanFirstNameIsUp(true); // 文件名首字母大写\n" +
                "\tdbdToBean.setModulesName(\"dbdtobean-spring-boot-autoconfigure\"); // 模块名字，如果项目里有多个模块，可使用，反之不要使用\n" +
                "\tdbdToBean.setControllerLocation(\"cn.kbt.controller\"); // Controller 的全类名\n" +
                "\tdbdToBean.setServiceLocation(\"cn.kbt.service\"); // Service 的全类名\n" +
                "\tdbdToBean.setDaoLocation(\"cn.kbt.dao\"); // Dao 的全类名，与 Mapper 二选一，因为默认不 set，不会生成该模块的类\n" +
                "\tdbdToBean.setMapperLocation(\"cn.kbt.mapper\"); // Mapper 的全类名，与 Dao 二选一\n" +
                "\tdbdToBean.setEntityLocation(\"cn.kbt.bean\"); // 实体类的包名，不填写，默认生成在桌面\n" +
                "\tdbdToBean.setGenerateCURD(true); // 生成增删改查的基础方法\n" +
                "\tHashMap<String, String> map = dbdToBean.generateAttrFromDataBase(); // 读取 url 里数据库的所有表\n" +
                "\tdbdToBean.exportToFile(map); // 导出到文件\n" +
                "\tdbdToBean.closeConnection(); // 关闭数据库";
    }

    public String dbdToBeanCustomerMVCAPI(){
        return "\tConnection connection = DBDToBeanUtils.getMysqlConnection(driverName, url, username, password);\n" +
                "\tDBDToBean dbdToBean = new DBDToBean();\n" +
                "\tdbdToBean.setConnection(connection);\n" +
                "\tdbdToBean.setPackageName(\"包路径\"); // 设置实体类包路径\n" +
                "\tdbdToBean.setAuthorName(\"作者名\");\n" +
                "\tdbdToBean.set_ToUpper(true); // 开启驼峰命名\n" +
                "\tdbdToBean.setAllComments(true); // 打开注释\n" +
                "\tdbdToBean.setHeadComment(true); // 打开类注释\n" +
                "\tdbdToBean.setCommentType(\"/*\"); // 注释类型：//  /*  /**\n" +
                "\tdbdToBean.setBeanFirstNameIsUp(true); // 文件名首字母大写\n" +
                "\tdbdToBean.setModulesName(\"所在模块名\");  // 模块名字，如果项目里有多个模块，可使用，反之不要使用\n" +
                "\tdbdToBean.setControllerLocation(\"cn.kbt.controller\"); // Controller 的全类名\n" +
                "\tdbdToBean.setServiceLocation(\"cn.kbt.service\"); // Service 的全类名\n" +
                "\tdbdToBean.setDaoLocation(\"cn.kbt.dao\"); // Dao 的全类名，与 Mapper 二选一，因为默认不 set，不会生成该模块的类\n" +
                "\tdbdToBean.setMapperLocation(\"cn.kbt.mapper\"); // Mapper 的全类名，与 Dao 二选一\n" +
                "\tbdToBean.setEntityLocation(\"cn.kbt.bean\"); // 实体类的包名，不填写，默认生成在桌面\n" +
                "\tdbdToBean.setGenerateCURD(true); // 生成增删改查的基础方法\n" +
                "\tdbdToBean.setPrefix(\"ke\"); // 所有类文件名添加前缀\n" +
                "\tdbdToBean.setSuffix(\"ng\"); // 所有类文件名添加后缀\n" +
                "\tdbdToBean.setControllerPre(\"le\"); // Controller 类文件名添加前缀\n" +
                "\tdbdToBean.setControllerSuf(\"bi\"); // Controller 类文件名添加后缀\n" +
                "\tdbdToBean.setServiceInterPre(\"le\"); // Service 接口类文件名添加前缀\n" +
                "\tdbdToBean.setServiceInterSuf(\"bi\"); // Service 接口类文件名添加后缀\n" +
                "\tdbdToBean.setServiceImplPre(\"ni\"); // Service 实现类文件名添加前缀\n" +
                "\tdbdToBean.getServiceImplSuf(\"hao\"); // Service 实现类文件名添加后缀\n" + 
                "\tdbdToBean.setDaoInterPre(\"le\"); // Dao 接口类文件名添加前缀\n" +
                "\tdbdToBean.setDaoInterSuf(\"bing\"); // Dao 接口类文件名添加前后缀\n" +
                "\tdbdToBean.setDaoImplPre(\"bing\"); // Dao 实现类文件名添加前前缀\n" +
                "\tdbdToBean.setDaoImplSuf(\"tang\"); // Dao 实现类文件名添加前后缀\n" +
                "\tdbdToBean.setMapperInterPre(\"xue\"); // Mapper 接口类文件名添加前前缀\n" +
                "\tdbdToBean.setMapperInterSuf(\"li\"); // Mapper 接口类文件名添加前后缀\n" +
                "\tdbdToBean.setMapperXmlPre(\"yin\"); // Mapper xml 文件名添加前前缀\n" +
                "\tdbdToBean.setMapperXmlSuf(\"ju\"); // Mapper xml 文件名添加前后缀\n" +
                "\tHashMap<String, String> map = dbdToBean.generateAttrFromDataBase();\n" +
                "\tdbdToBean.exportToFile(map);\n" +
                "\tdbdToBean.closeConnection();";
    }
}
