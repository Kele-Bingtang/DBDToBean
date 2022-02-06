```js
//使用方法：1、单个表生成JavaBean，创建对象时可传入参数，为生成的JavaBean文件名，不传则默认 数据库对应的 表名，属性值对于表的字段名，JavaBean文件默认路径在电脑桌面，可指定路径
new DBDToBean();  // DBDToBean dBDToBean = new DBDToBean("文件名");
//传入数据库对象，使DBDToBean连接到数据库
setDbdToBeanProperties(String,String,String,String)
setConnection(Connection);
//设置开头的包路径，不设置则没有包路径
setPackageName(String);
//设置 是否读取数据库表的字段对应的注释，如果字段没有注释，则生成我们指定的注释
setAllComment(boolean);
//参数，1.是否生成注释，2.是否生成属性注释，3.是否生成构造器注释，4.是否生成setget方法，5.是否生成toString方法
setComment(boolean,boolean,boolean,boolean,boolean);
//是否继承jar包，与数据库的类型对应，默认为 true ，继承
setJarPackage(boolean);
//生成的注释类型，参数："//"，"/"，输入其他参数默认为 "//"
setCommentType("// 或者 /*");
//设置类的注释，不设置默认生成规定的注释，如果不要类的注释，参数改为 false
setHeadComment(boolean || String);
//设置类的注释和注释类型，与setCommentType一样，不设置默认生成规定的注释，如果不要类的注释，参数改为 false
setHeadComment(String,String);
// 数据库生成的JavaBean内容属性值是否保持一样或者小写，主要针对Oracle数据库
setFieldNameAllLower(boolean);  
//传入sql语句，返回生成JavaBean的内容，一定生成属性值，参数：1.sql语句，2.是否生成构造函数，3.是否生成setget方法，4.是否生成toString方法，返回值为生成的内容
String = generateAttrFromTable(String,boolean,boolean,boolean);

//单独判断是否在生成对应的注释，true生成，false不生成
setFieldComment(boolean)，setConstructorComment(boolean)，setSetAndGetComment(boolean)，setToStringComment(boolean)
//自定义注释，属性和setget方法的注释按顺序读取集合的内容，如果集合长度不满足，后面默认生成规定的注释
setFieldComment(List<String>)，setConstructorComment(String)，setSetAndGetComment(List<String>)，setToStringComment(String)
// 设置生成的JavaBean文件名首字母大小写，默认true，为大写
setBeanFirstNameIsUp(boolean)
//传入生成的JavaBean内容，导出为Java文件，参数：1.内容，2.指定路径，3.指定路径下的文件夹名，返回值为生成的路径
String createPath = exportToFile(String || HashMap<>,String,String);  
//关闭传入的数据库对象
closeConnection();


//使用方法2，整个数据库生成，自动读取每一个表， 生成JavaBean文件，文件名为每一个表名，属性值为对应表的 字段名
//传入文件名无效，只以表名为文件名
new DBDToBean();
setConnection(conn);
//具体多个方法与使用方法1一样
setPackageName(String);  
//传入数据库名，后面两个参数可以没有，没有默认生成在桌面，为：1.指定路径，2.指定路径的文件夹名
//返回值是一个HashMap，key为生成的表名，value为生成的内容
HashMap = generateAttrFromDataBase(String,boolean,boolean,boolean);
//与setOracleFieldName一样，可以设置在生成JavaBean时，属性值是否大写，默认小写，仅限Oracle数据库
generateAttrFromDabase(false);
//多个表，传入HashMap对象，其他参数和使用方法1描述一致
exportToFile(HashMap<String,String>)
//关闭传入的数据库对象
closeConnection();

//使用方法3，生成MVC层的各个目录，包括Controller、Service、Dao、Mapper
DBDToBean dbdToBean = new DBDToBean();
setConnection(conn);
// Mvaen目录还是普通目录，默认Maven目录
setMavenOrSimple(boolean)
// 设置模块名，如果没有，则无需设置
setModulesName(String);
// 实现类基于接口类所在的路径
setMVImplName(String);
// Mybatis的xml文件开头自定义字符串，默认使用2021/9/24前的最新版
setXmlPublicAndHttp(String);
// 设置MVC各层的路径
setControllerLocation(String)、setServiceLocation(String)、setDaoLocation(String)、setMapperLocation(String)、setEntryLocation(String);
// 是否生成基础的CRUD方法
setGenerateCURD(boolean);
// 自定义MVC文件的前缀和后缀
setPrefix()、setSuffix()、setControllerPre()、setControllerSuf()、setServiceInterPre()、setServiceImplPre()、setDaoInterPre()、setDaoImplPre()、setMapperInterPre()、setMapperXmlPre()等;

//使用方法4，Spring boot用法
// 只需在 Spring Boot的配置文件以dbdtobean开头，写好数据即可

//其他工具类：（都在DBDToBeanUtils里）
// 获取随机数字
randomNum();
//返回一个首字母大写的字符串
firstCharToUpperCase(String);  
//外界传入数据库对象，关闭数据库对象
closeConnection(Connection);  
// 更多使用方法，请自己尝试阅读源码理解，或者进行开发，完善更多的功能
```