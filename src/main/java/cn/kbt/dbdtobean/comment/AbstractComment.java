package cn.kbt.dbdtobean.comment;

/**
 * @author Kele-Bing
 * @version 1.0
 * @since 2021/9/20 17:33
 * 抽象注释类
 */
public abstract class AbstractComment {

    /**
     * 两个参数：
     * // 代表 在上方生成注释  默认
     * /* 代表在上方生成 /**  *'/'注释
     */
    private String commentType = "//";

    /**
     * 给类的内容注释
     * 解析注释类型，生成不同类型的注释
     *
     * @param sb      字符串
     * @param comment 注释
     * @return 字符串
     */
    protected StringBuilder parseCommentType(StringBuilder sb, String comment) {
        if (commentType.equals("//")) {
            sb.append("\t// ").append(comment).append("\n");
        } else if (commentType.equals("/*")) {
            sb.append("\t/*\n\t\t").append(comment).append("\n\t*/\n");
        } else if (commentType.equals("/**")) {
            sb.append("\t/**\n\t * ").append(comment).append("\n\t */\n");
        } else {
            sb.append("\t// ").append(comment).append("\n");
        }
        return sb;
    }

    /**
     * 设置注释的类型
     *
     * @param commentType 注释的类型
     */
    public void setCommentType(String commentType) {
        if (!commentType.equals("//") && !commentType.equals("/*") && !commentType.equals("/**")) {
            this.commentType = "//";
        } else {
            this.commentType = commentType;
        }
    }

    /**
     * 给类的内容注释
     * 解析注释类型，生成不同类型的注释
     *
     * @param sb          字符串
     * @param comment     注释
     * @param commentType 注释的类型
     * @return 字符串
     */
    protected StringBuilder parseCommentType(StringBuilder sb, String comment, String commentType) {
        if (commentType.equals("//")) {
            sb.append("\t// ").append(comment).append("\n");
        } else if (commentType.equals("/*")) {
            sb.append("\t/*\n\t\t").append(comment).append("\n\t*/\n");
        } else if (commentType.equals("/**")) {
            sb.append("\t/**\n\t * ").append(comment).append("\n\t */\n");
        } else {
            sb.append("/**\n * ").append(comment).append("\n */\n");
        }
        return sb;
    }


}
