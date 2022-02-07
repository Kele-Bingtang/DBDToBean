package cn.kbt.dbdtobean.comment;


/**
 * @author Kele-Bing
 * @version 1.0
 * 默认注释类
 * @since 2021/9/19 15:10
 */
public class DefaultComment {
    /**
     * 是否生成全部注释
     **/
    private boolean allComments = false;
    /**
     * 是否生成类注释
     **/
    private boolean setHeadComment = true;
    /**
     * 是否生成属性注释
     **/
    private boolean fieldComment = false;
    /**
     * 是否生成构造器注释
     **/
    private boolean constructorComment = false;
    /**
     * 是否生成setter和getter注释
     **/
    private boolean setAndGetComment = false;
    /**
     * 是否生成toString注释
     **/
    private boolean toStringComment = false;

    public boolean isAllComments() {
        return allComments;
    }

    public void setAllComments(boolean allComments) {
        this.allComments = allComments;
        this.setHeadComment = allComments;
        this.fieldComment = allComments;
        this.constructorComment = allComments;
        this.setAndGetComment = allComments;
        this.toStringComment = allComments;
    }

    public boolean isSetHeadComment() {
        return setHeadComment;
    }

    public void setSetHeadComment(boolean setHeadComment) {
        this.setHeadComment = setHeadComment;
    }

    public boolean isFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(boolean fieldComment) {
        this.fieldComment = fieldComment;
    }

    public boolean isConstructorComment() {
        return constructorComment;
    }

    public void setConstructorComment(boolean constructorComment) {
        this.constructorComment = constructorComment;
    }

    public boolean isSetAndGetComment() {
        return setAndGetComment;
    }

    public void setSetAndGetComment(boolean setAndGetComment) {
        this.setAndGetComment = setAndGetComment;
    }

    public boolean isToStringComment() {
        return toStringComment;
    }

    public void setToStringComment(boolean toStringComment) {
        this.toStringComment = toStringComment;
    }

    public boolean generateComment() {
        return fieldComment || constructorComment || setAndGetComment || toStringComment;
    }

    public void setComment(boolean isFieldComment, boolean isConstructorComment, boolean isSetAndGetComment, boolean isToStringComment) {
        this.setFieldComment(isFieldComment);
        this.setConstructorComment(isConstructorComment);
        this.setSetAndGetComment(isSetAndGetComment);
        this.setToStringComment(isToStringComment);
    }

}
