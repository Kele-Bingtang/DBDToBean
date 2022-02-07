package cn.kbt.dbdtobean.core;

import cn.kbt.dbdtobean.comment.CustomComment;
import cn.kbt.dbdtobean.comment.DefaultComment;
import cn.kbt.dbdtobean.config.DBDToBeanProperties;
import cn.kbt.dbdtobean.mvcbean.DBDToMVCDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/19 15:33
 * 上下文
 */
public class DBDToBeanContext {
    private DBDToBeanContext() {
    }

    /**
     * 支持Spring boot配置文件，只需在spring boot配置文件写入数据库配置等信息即可获取数据库源对象
     **/
    private static volatile DBDToBeanProperties dbdToBeanProperties;
    /**
     * 可自定义注释，不自定义有默认注释
     **/
    private static volatile CustomComment customComment;
    /**
     * 默认注释，判断是否生成注释等信息
     **/
    private static volatile DefaultComment defaultComment;
    /**
     * 定义类，针对单表，有很多定义信息，如生成的JavaBean文件名，去掉下划线后的首字母大写等
     **/
    private static DBDToBeanDefinition dbdToBeanDefinition;
    /**
     * 定义类集合，针对多个表
     **/
    private static volatile List<DBDToBeanDefinition> dbdToBeanDefinitions;
    /**
     * MVC定义类，生成MVC文件的信息
     **/
    private static DBDToMVCDefinition dbdToMVCDefinition;

    public static DBDToBeanProperties getDbdToBeanProperties() {
        if (dbdToBeanProperties == null) {
            synchronized (DBDToBeanContext.class) {
                if (dbdToBeanProperties == null) {
                    dbdToBeanProperties = new DBDToBeanProperties();
                    return dbdToBeanProperties;
                }
            }
        }
        return dbdToBeanProperties;
    }

    public static CustomComment getCustomComment() {
        if (customComment == null) {
            synchronized (DBDToBeanContext.class) {
                if (customComment == null) {
                    customComment = new CustomComment();
                    return customComment;
                }
            }
        }
        return customComment;
    }

    public static DefaultComment getDefaultComment() {
        if (defaultComment == null) {
            synchronized (DBDToBeanContext.class) {
                if (defaultComment == null) {
                    defaultComment = new DefaultComment();
                    return defaultComment;
                }
            }
        }
        return defaultComment;
    }

    public static DBDToBeanDefinition getDbdToBeanDefinition() {
        if (dbdToBeanDefinition == null) {
            synchronized (DBDToBeanContext.class) {
                if (dbdToBeanDefinition == null) {
                    dbdToBeanDefinition = new DBDToBeanDefinition();
                    return dbdToBeanDefinition;
                }
            }
        }
        return dbdToBeanDefinition;
    }

    public static List<DBDToBeanDefinition> getDbdToBeanDefinitions() {
        if (dbdToBeanDefinitions == null) {
            synchronized (DBDToBeanContext.class) {
                if (dbdToBeanDefinitions == null) {
                    dbdToBeanDefinitions = new ArrayList<DBDToBeanDefinition>();
                    return dbdToBeanDefinitions;
                }
            }
        }
        return dbdToBeanDefinitions;
    }

    public static DBDToMVCDefinition getDbdToMVCDefinition() {
        if (dbdToMVCDefinition == null) {
            synchronized (DBDToBeanContext.class) {
                if (dbdToMVCDefinition == null) {
                    dbdToMVCDefinition = new DBDToMVCDefinition();
                    return dbdToMVCDefinition;
                }
            }
        }
        return dbdToMVCDefinition;
    }

    public static void setDbdToBeanProperties(DBDToBeanProperties dbdToBeanProperties) {
        DBDToBeanContext.dbdToBeanProperties = dbdToBeanProperties;
    }

    public static void setDbdToBeanDefinition(DBDToBeanDefinition dbdToBeanDefinition) {
        DBDToBeanContext.dbdToBeanDefinition = dbdToBeanDefinition;
    }

    public static void setCustomComment(CustomComment customComment) {
        DBDToBeanContext.customComment = customComment;
    }

    public static void setDefaultComment(DefaultComment defaultComment) {
        DBDToBeanContext.defaultComment = defaultComment;
    }

    public static void setDbdToBeanDefinitions(List<DBDToBeanDefinition> dbdToBeanDefinitions) {
        DBDToBeanContext.dbdToBeanDefinitions = dbdToBeanDefinitions;
    }

    public static void setDbdToMVCDefinition(DBDToMVCDefinition dbdToMVCDefinition) {
        DBDToBeanContext.dbdToMVCDefinition = dbdToMVCDefinition;
    }


}
