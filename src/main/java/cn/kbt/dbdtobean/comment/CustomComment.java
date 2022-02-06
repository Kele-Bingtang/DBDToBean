package cn.kbt.dbdtobean.comment;

import cn.kbt.dbdtobean.core.DBDToBeanContext;
import cn.kbt.dbdtobean.utils.DBDToBeanUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Kele-Bing
 * @Create 2021/9/19 15:01
 * @Version 1.0
 * @Describe 自定义注释类
 */
public class CustomComment extends AbstractComment{
    
    private final static String NULL_CONSTRUCTOR = "无参构造器";
    private final static String PARAM_CONSTRUCTOR = "有参构造器，进行属性值的初始化";
    private final static String SET = "设置 ";
    private final static String GET = "获取 ";
    private final static String REMARKS = "REMARKS";
    private final static String FIELD_NAME = " 的属性值";
    private final static String TO_STRING = "重写toString方法，使用该方法可以在控制台打印属性的数据";
    /**
     * JavaBean内容的自定义注释
     */
    private List<String> filedComment = null;
    private String nullConstructorComment = "";
    private String paramConstructorComment = "";
    private String toStringComment = "";
    private List<String> SetComment = null;
    private List<String> GetComment = null;

    public CustomComment() {
    }
    
    public List<String> getFiledComment() {
        return filedComment;
    }

    public void setFiledComment(List<String> filedComment) {
        this.filedComment = filedComment;
    }

    public String getNullConstructorComment() {
        return nullConstructorComment;
    }

    public void setNullConstructorComment(String nullConstructorComment) {
        this.nullConstructorComment = nullConstructorComment;
    }

    public String getParamConstructorComment() {
        return paramConstructorComment;
    }

    public void setParamConstructorComment(String paramConstructorComment) {
        this.paramConstructorComment = paramConstructorComment;
    }
   

    public String getToStringComment() {
        return toStringComment;
    }

    public void setToStringComment(String toStringComment) {
        this.toStringComment = toStringComment;
    }

    public List<String> getSetComment() {
        return SetComment;
    }

    public void setSetComment(List<String> setComment) {
        SetComment = setComment;
    } 
    

    public List<String> getGetComment() {
        return GetComment;
    }

    public void setGetComment(List<String> getComment) {
        GetComment = getComment;
    }

    public void setConstructorComment(String nullConstructorComment,String constructorComment) {
        this.nullConstructorComment = nullConstructorComment;
        this.paramConstructorComment = constructorComment;
    }
    
    public void setSetAndGetComment(List<String> getComment,List<String> setComment) {
        this.GetComment = getComment;
        SetComment = setComment;
    }
    /*
        自定义属性注释
    */
    public StringBuilder customFiledComment(StringBuilder sb, ResultSet columnsInfo, String content, int i) throws SQLException {
        if (DBDToBeanContext.getCustomComment().getFiledComment() != null && DBDToBeanContext.getCustomComment().getFiledComment().size() >= i) {
            if (DBDToBeanUtils.isNotEmpty(columnsInfo)) {
                columnsInfo.next();
            }
            //解析注释类型，生成不同类型的注释
            super.parseCommentType(sb, DBDToBeanContext.getCustomComment().getFiledComment().get(i - 1));
        } else {  //没有自定义注释，获取数据库的注释
            if (DBDToBeanContext.getDefaultComment().isFieldComment()) {
                if (DBDToBeanUtils.isNotEmpty(columnsInfo)) {
                    columnsInfo.next();
                    //获取数据库的注释
                    if (DBDToBeanUtils.isNotEmpty(columnsInfo.getString(REMARKS))) {
                        super.parseCommentType(sb, columnsInfo.getString(REMARKS));
                    } else {  //没有自定义注释，数据库的字段没有注释，生成规定的注释
                        //解析注释类型，生成不同类型的注释
                        super.parseCommentType(sb, content);
                    }
                } else {  //没有自定义注释，数据库的字段没有注释，生成规定的注释
                    //解析注释类型，生成不同类型的注释
                    super.parseCommentType(sb, content);
                }
            }
        }
        return sb;
    }
    /*
         自定义构造器注释
     */
    public StringBuilder customConstructComment(StringBuilder sb,boolean nullConstruct){
        CustomComment customComment = DBDToBeanContext.getCustomComment();
        if(nullConstruct){
            //是否生成无参构造器注释，没有自定义注释则生成规定的注释
            if (DBDToBeanContext.getDefaultComment().isConstructorComment()) {
                if (DBDToBeanUtils.isNotEmpty(customComment.getNullConstructorComment())) {
                    super.parseCommentType(sb, DBDToBeanContext.getCustomComment().getNullConstructorComment());
                } else {
                    super.parseCommentType(sb, NULL_CONSTRUCTOR);
                }
            }
        }else{
            //是否生成有参构造器注释，没有自定义注释则生成规定的注释
            if (DBDToBeanContext.getDefaultComment().isConstructorComment()) {
                if (DBDToBeanUtils.isNotEmpty(customComment.getParamConstructorComment())) {
                    super.parseCommentType(sb, customComment.getParamConstructorComment());
                } else {
                    super.parseCommentType(sb, PARAM_CONSTRUCTOR);
                }
            }
        }
        return sb;
    }
    /*
        自定义setter和getter注释
    */
    public StringBuilder customSetGetComment(StringBuilder sb,ResultSet columns,String content,int i,boolean set) throws SQLException {
        CustomComment customComment = DBDToBeanContext.getCustomComment();
        //是否生成set注释，没有自定义注释则生成规定的注释
        if (DBDToBeanUtils.isNotEmpty(customComment.getGetComment()) && customComment.getGetComment().size() >= i) {
            if(set){
                if (DBDToBeanUtils.isNotEmpty(columns)) {
                    columns.next();
                }
            }
            super.parseCommentType(sb, customComment.getGetComment().get(i - 1));
        } else {
            //是否生成get注释，没有自定义注释则生成规定的注释
            generateSetGetComment(sb, columns, set,content);
        }
        return sb;
    }
    /*
        生成setter和getter注释
    */
    private void generateSetGetComment(StringBuilder sb,ResultSet columns,boolean set,String fieldName) throws SQLException {
        String setGetString = GET;
        if (DBDToBeanContext.getDefaultComment().isSetAndGetComment()) {
            if (DBDToBeanUtils.isNotEmpty(columns)) {
                if(!set){
                    columns.next();
                }else {
                    setGetString = SET;
                }
                if (DBDToBeanUtils.isNotEmpty(columns.getString(REMARKS))) {
                    super.parseCommentType(sb, setGetString + columns.getString(REMARKS) + FIELD_NAME);
                } else {
                    super.parseCommentType(sb, setGetString + fieldName + FIELD_NAME);
                }
            } else {
                super.parseCommentType(sb, setGetString + fieldName + FIELD_NAME);
            }
        }
    }
    /*
         自定义toString注释
    */
    public StringBuilder customToString(StringBuilder sb) throws SQLException {
        if (DBDToBeanContext.getDefaultComment().isToStringComment()) {
            if (DBDToBeanUtils.isNotEmpty(DBDToBeanContext.getCustomComment().getToStringComment())) {
                super.parseCommentType(sb, DBDToBeanContext.getCustomComment().getToStringComment());
            } else {
                super.parseCommentType(sb, TO_STRING);
            }
        }
        return sb;
    }
    
}
