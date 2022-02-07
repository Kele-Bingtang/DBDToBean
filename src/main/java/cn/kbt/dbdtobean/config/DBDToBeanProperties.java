package cn.kbt.dbdtobean.config;

import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.sql.Connection;

public class DBDToBeanProperties {
    /**
     * 数据库源对象
     **/
    private Connection conn;
    /**
     * 数据库驱动
     **/
    private String driverName;
    /**
     * 数据库url
     **/
    private String url;
    /**
     * 数据库用户名
     **/
    private String username;
    /**
     * 数据库密码
     **/
    private String password;
    /**
     * 数据库类型
     **/
    private String dateBaseType = "MySQL";
    /**
     * 作者
     **/
    private String authorName = System.getenv().get("USERNAME");

    public DBDToBeanProperties() {
    }

    public Connection getConn() {
        if (conn == null) {
            if (dateBaseType.equals("MySQL")) {
                conn = DBDToBeanUtils.getMysqlConnection(driverName, url, username, password);
            } else if (dateBaseType.equals("Oracle")) {
                conn = DBDToBeanUtils.getOracleConnection(driverName, url, username, password);
            }
        }
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateBaseType() {
        return dateBaseType;
    }

    public void setDateBaseType(String dateBaseType) {
        this.dateBaseType = dateBaseType;
    }

    public String getAuthorName() {
        if (DBDToBeanUtils.isEmpty(authorName)) {
            return "";
        }
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
