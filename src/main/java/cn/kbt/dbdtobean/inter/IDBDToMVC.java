package cn.kbt.dbdtobean.inter;

import java.io.IOException;

/**
 * @Author Kele-Bing
 * @Create 2021/9/21 21:50
 * @Version 1.0
 * @Describe MVC接口
 */
public interface IDBDToMVC {
    // Controller相关
    void dbdToController() throws IOException;
    // Service相关
    void dbdToService() throws IOException;
    // Dao相关
    void dbdToDao() throws IOException;
    // Mapper相关
    void dbdToMapper() throws IOException;
}
