package cn.kbt.dbdtobean.utils;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DBDToBeanUtils {
    /**
     * 关闭资源
     */
    public static void close(ResultSet rs, PreparedStatement pstmt) {
        try {
            if (null != rs) {
                rs.close();
            }
            if (null != pstmt) {
                pstmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭资源
     */
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (null != rs) {
                rs.close();
            }
            if (null != pstmt) {
                pstmt.close();
            }
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean isEmpty(Object content){
        return content == null || content.equals("");
    }

    public static boolean isNotEmpty(Object content){
        return !isEmpty(content);
    }

    /**
     * 去掉字符串的下划线，并且下划线后的首字母大写
     */
    public static String _CharToUpperCase(String name) {
        StringBuilder charUpper = new StringBuilder();
        String[] splitName = name.split("_");
        charUpper.append(splitName[0]);
        for (int i = 1; i < splitName.length; i++) {
            if (splitName[i] != null) {
                String s = firstCharToUpperCase(splitName[i]);
                charUpper.append(s);
            }
        }
        return charUpper.toString();
    }

    /**
     * 将字符串的首字母转为大写
     */
    public static String firstCharToUpperCase(String fieldName) {
        char[] chars = fieldName.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        } else {
            return fieldName;
        }
    }

    /**
     * 将字符串的首字母转为小写
     */
    public static String firstCharToLowerCase(String fieldName) {
        if (fieldName.substring(0, 1).equals(fieldName.substring(0, 1).toUpperCase())) {
            char[] chars = fieldName.toCharArray();
            chars[0] += 32;
            return String.valueOf(chars);
        } else {
            return fieldName;
        }
    }

    /**
     * 字符串转为小写
     */
    public String toLowerCase(String content){
        char[] contentChars = content.toCharArray();
        for (int i = 0; i < contentChars.length; i++) {
            if(contentChars[i] >= 'A' && contentChars[i] <= 'Z'){
                contentChars[i] += 32;
            }
        }
        return String.valueOf(contentChars);
    }
    /**
     * 字符串转为大写
     */
    public String toUpperCase(String content){
        char[] contentChars = content.toCharArray();
        for (int i = 0; i < contentChars.length; i++) {
            if(contentChars[i] >= 'a' && contentChars[i] <= 'z'){
                contentChars[i] -= 32;
            }
        }
        return String.valueOf(contentChars);
    }

    /**
     * 如果字符串第二个位置的字母为大写，则返回true，反之false
     * 符合set和get方法，tTs ==> 生成set和get为：settTs(){}   gettTs(){}
     * 而不是 setTTs(){}  getTTs(){}
     */
    public static boolean isTwoCharUpper(String content){
        if(content.length() > 2){
            return content.substring(1, 2).equals(content.substring(1, 2).toUpperCase());
        }
        return false;
    }

    /**
     * 0-1000随机数字，生成文件名
     */
    public static int randomNum() {
        //随机数字，生成文件名
        List<Integer> randomNum = new ArrayList<Integer>();
        int nextInt = new Random().nextInt(1000);
        for (Integer i : randomNum) {
            if (i == nextInt) {
                return randomNum();
            }
        }
        randomNum.add(nextInt);
        return nextInt;
    }
    /**
     * 格式化并获取当前时间
     */
    public static String getCurrentTime(){
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    /**
     * 根据格式格式化并获取当前时间
     */
    public static String getCurrentTime(String format){
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }
    /**
     * 全类名转为类路径
     */
    public static String packageToPath(String content){
        return content.replace(".", "/");
    }

    /**
     * 获取Mysql的数据库源对象
     */
    public static Connection getMysqlConnection(String driverName, String url, String username, String password) {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            if (null == conn) {
                throw new RuntimeException("数据库连接失败");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 获取Oracle的数据库源对象
     */
    public static Connection getOracleConnection(String driverName, String url, String username, String password) {
        Connection conn = null;
        try {
            Class.forName(driverName);
            // conn = DriverManager.getConnection("jdbc:oracle:thin:@112.74.169.231:1521:orcl", userName, passWord);
            conn = DriverManager.getConnection(url, username, password);
            if (null == conn) {
                throw new RuntimeException("数据库连接失败");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 解释信息
     * 查询的表的信息的内容
     */
    public static void explain(ResultSet rs) throws SQLException {
        ResultSetMetaData data = rs.getMetaData();
        for (int i = 1; i <= data.getColumnCount(); i++) {
            // 获得所有列的数目及实际列数
            int columnCount = data.getColumnCount();
            // 获得指定列的列名
            String columnName = data.getColumnName(i);
            // 获得指定列的列值
            int columnType = data.getColumnType(i);
            // 获得指定列的数据类型名
            String columnTypeName = data.getColumnTypeName(i);
            // 所在的Catalog名字
            String catalogName = data.getCatalogName(i);
            // 对应数据类型的java类
            String columnClassName = data.getColumnClassName(i);
            // 在数据库中类型的最大字符个数
            int columnDisplaySize = data.getColumnDisplaySize(i);
            // 默认的列的标题
            String columnLabel = data.getColumnLabel(i);
            //获取查询数据所在的表名
            String tableName = data.getTableName(i);
            // 获得列的模式
            String schemaName = data.getSchemaName(i);
            // 某列类型的精确度(类型的长度)
            int precision = data.getPrecision(i);
            // 小数点后的位数
            int scale = data.getScale(i);
            // 是否自动递增
            boolean isAutoInctement = data.isAutoIncrement(i);
            // 在数据库中是否为货币型
            boolean isCurrency = data.isCurrency(i);
            // 是否为空
            int isNullable = data.isNullable(i);
            // 是否为只读
            boolean isReadOnly = data.isReadOnly(i);
            // 能否出现在where中
            boolean isSearchable = data.isSearchable(i);
            System.out.println(columnCount);
            System.out.println("获得列" + i + "的字段名称:" + columnName);
            System.out.println("获得列" + i + "的类型,返回SqlType中的编号:" + columnType);
            System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
            System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
            System.out.println("获得列" + i + "对应数据类型的类:" + columnClassName);
            System.out.println("获得列" + i + "在数据库中类型的最大字符个数:" + columnDisplaySize);
            System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
            System.out.println("获得列" + i + "的所在的表名:" + tableName);
            System.out.println("获得列" + i + "的模式:" + schemaName);
            System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
            System.out.println("获得列" + i + "小数点后的位数:" + scale);
            System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
            System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
            System.out.println("获得列" + i + "是否为空:" + isNullable);
            System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
            System.out.println("获得列" + i + "能否出现在where中:" + isSearchable);
        }
    }


}
