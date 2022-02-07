package cn.kbt.dbdtobean.inter;

import java.io.IOException;

/**
 * @author Kele-Bing
 * @since 2021/9/21 21:50
 * @version 1.0
 *  MVC接口
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
