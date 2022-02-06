package cn.kbt.dbdtobean.comment;

import cn.kbt.dbdtobean.utils.DBDToBeanUtils;


/**
 * @Author Kele-Bing
 * @Create 2021/9/20 17:27
 * @Version 1.0
 * @Describe 类注释信息
 */
public class HeadComment extends AbstractComment{
    private String author = "@Author ";
    private String createTime = "@Create ";
    private String version = "@Version 1.0";
    private String describe = "@Describe ";
    private StringBuilder headComments = null;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public StringBuilder getHeadComments() {
        if(headComments == null){
            headComments = new StringBuilder();
        }
        return headComments;
    }

    public void setHeadComments(StringBuilder headComments) {
        this.headComments = headComments;
    }

    public String getHeadComment() {
        return headComments.toString();
    }

    /**
     * 自定义的类注释
     *
     * @param headComment 注释内容
     */
    public void setHeadComment(String headComment) {
        //缓存区添加自定义的类注释
        super.parseCommentType(headComments, headComment, "/*");
    }
    /**
     * 自定义的类注释和设置注释的类型
     *
     * @param headComment 注释内容
     * @param commentType 注释类型
     */
    public void setHeadComment(String headComment, String commentType) {
        super.parseCommentType(headComments, headComment, commentType);
    }
    
    public StringBuilder generateHeadComments(String author){
        this.headComments.setLength(0);
        this.headComments.append("/**\n * ")
                .append(this.author).append(author)
                .append("\n * ").append(this.createTime).append(DBDToBeanUtils.getCurrentTime())
                .append("\n * ").append(this.version)
                .append("\n * ").append(this.describe)
                .append("\n */\n");
        return headComments;
    }
    
}
