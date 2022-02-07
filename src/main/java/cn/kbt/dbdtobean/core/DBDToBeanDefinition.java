package cn.kbt.dbdtobean.core;

import cn.kbt.dbdtobean.utils.DBDToBeanUtils;
import cn.kbt.dbdtobean.comment.HeadComment;

/**
 * @author Kele-Bing
 * @version 1.0
 * 定义信息类
 * @since 2021/9/20 11:38
 */
public class DBDToBeanDefinition {
    /**
     * 开头类路径
     **/
    private final static String PACKAGE = "package ";
    /**
     * 生成的文件名
     **/
    private String createBeanName;
    /**
     * 文件名首字母是否大写
     **/
    private boolean beanFirstNameUp = true;
    /**
     * 是否导入文件需要的jar包
     **/
    private boolean jarPackage = true;
    /**
     * 数据库生成的JavaBean内容属性值是否保持一样或者小写
     **/
    private boolean fieldNameAllLower = false;
    /**
     * 去掉下划线后的首字母大写
     **/
    private boolean _ToUpper = false;
    /**
     * 数据库类型
     **/
    private String dateBaseType = "MySQL";
    /**
     * 包路径名
     **/
    private String packageName;
    /**
     * 类注释，无法设置
     **/
    private HeadComment headComment;


    public String getCreateBeanName() {
        return createBeanName;
    }

    public void setCreateBeanName(String createBeanName) {
        this.createBeanName = createBeanName;
    }

    public boolean isBeanFirstNameUp() {
        return beanFirstNameUp;
    }

    public void setBeanFirstNameUp(boolean beanFirstNameUp) {
        this.beanFirstNameUp = beanFirstNameUp;
    }

    public boolean isJarPackage() {
        return jarPackage;
    }

    public void setJarPackage(boolean jarPackage) {
        this.jarPackage = jarPackage;
    }

    public boolean isFieldNameAllLower() {
        return fieldNameAllLower;
    }

    public void setFieldNameAllLower(boolean fieldNameAllLower) {
        this.fieldNameAllLower = fieldNameAllLower;
    }

    public boolean is_ToUpper() {
        return _ToUpper;
    }

    public void set_ToUpper(boolean _ToUpper) {
        this._ToUpper = _ToUpper;
    }

    public String getDateBaseType() {
        return dateBaseType;
    }

    public void setDateBaseType(String dateBaseType) {
        this.dateBaseType = dateBaseType;
    }

    public String getPackageName() {
        if (DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation())) {
            return DBDToBeanContext.getDbdToMVCDefinition().getEntityLocation();
        }
        if (packageName == null) {
            return "";
        }
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String setThenGetPackageName(String packageName) {
        return PACKAGE + packageName + ";\n";
    }

    public HeadComment getHeadComment() {
        if (headComment == null) {
            headComment = new HeadComment();
        }
        return headComment;
    }

    public void setHeadComment(HeadComment headComment) {
        this.headComment = headComment;
    }

    public void setPackageNameAndJarPackage(String packageName, boolean jarPackage) {
        this.packageName = PACKAGE + packageName + ";\n";
        this.jarPackage = jarPackage;
    }

}
