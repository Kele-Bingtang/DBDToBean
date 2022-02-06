package cn.kbt.dbdtobean.inter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public interface IDBDToBeanCore {
    // 根据单表生成Java文件
    String generateAttrFromTable(String tableName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException;
    // 根据数据库生成Java文件
    HashMap<String,String> generateAttrFromDataBase(String dateBaseName, boolean isConstructor, boolean isSetAndGet, boolean isToString) throws SQLException, IOException;
    // 导出Java文件
    String exportToFile(String fileContent, String path, String dirName) throws IOException;
}
