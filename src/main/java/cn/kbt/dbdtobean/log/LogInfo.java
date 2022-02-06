package cn.kbt.dbdtobean.log;

/**
 * create by chenyicai And Kele
 * ---------------------------------
 * V1.0：
 * create time : 2021/6/28/20:18:52
 * addOrigin time : 2021/6/29/18:59:04
 * addComment time : 2021/6/30/14:14:28
 * addJarPackage time : 2021/6/30/16:27:27
 * mysql testEnd time : 2021/6/30/18:54:29
 * oracle testEnd time : 2021/7/1/16:48:01
 * ---------------------------------
 * V1.1：
 * startTime : 2021/7/5/11:01:30
 * endTime : 2021/7/5/13:13:52
 * ---------------------------------
 * V1.2：
 * startTime : 2021/8/9/13:31:27
 * addSpringBootStater
 * endTime : 2021/8/9/17:38:19
 * ---------------------------------
 * V1.3：
 * startTime : 2020/9/19/16:14:32
 * endTime : 2020/9/24/15:52:49
 * V1.4：
 * startTime : 2020/2/6/13:14:25
 * endTime : 2020/2/6/22:44:55
 */
public class LogInfo {
    public String logInfo(){
        return "V1.0(2021/6/28/20:18:52 - 2021/7/1/16:48:01)\n" +
                "实现了数据库的表自动生成JavaBean文件的基本功能\n" +
                "\t大功能：\n" +
                "\t\t1.单个表生成JavaBean\n" +
                "\t\t2.一个数据库的所有表生成JavaBean\n" +
                "\t小功能：\n" +
                "\t\t1.添加了选择 生成构造器和setget方法和重写toString方法\n" +
                "\t\t2.添加了选择 获取数据库的注释功能，如果没有，则生成规定的注释，也可以自定义注释\n" +
                "\t\t3.添加了选择 设置生成文件所在的包路径，即开头的 package xxxx;\n" +
                "\t\t4.添加了选择 导入Java自带的jar包，即非java.lang的jar包\n" +
                "\t\t5.添加了选择 类注释功能，如果没有，则生成规定的注释，也可以自定义注释\n" +
                "\t\t6.添加了选择 生成单个JavaBean文件的路径和文件夹名字，两个参数，路径不能有不存在的文件夹，默认路径在电脑桌面\n" +
                "\t\t7.添加了选择 生成一个数据库的JavaBean文件的路径和文件夹名字，默认路径在电脑桌面的一个文件夹里，该文件夹以该数据库名字+随机数字生成\n" +
                "\t\t8.添加了选择 Oracle数据库生成的JavaBean文件中，属性值可以大小写，默认全大写，因为Oracle的字段全是大写\n" +
                "\t\t9.目前仅测试了MySQL和Oracle\n" +
                "\t\t\n" +
                "V1.1(2021/7/5/11:01:30 - 2021/7/5/13:13:52)\n" +
                "\t1.添加了选择 去掉下划线并且把下划线后的首字母改为大写，即驼峰命名法\n" +
                "\t2.解决 Oracle数据库生成单个表的JavaBean文件，无法设置属性字符串为小写\n" +
                "\t3.分离了各个功能，优化了结构，减少代码重复，提高一些性能和代码的维护性\n" +
                "\t4.解决 多个表生成JavaBean时，类注释只出现在第一个JavaBean里\n" +
                "\t5.解决 多个表生成JavaBean时，设置去掉下划线，文件名没有去掉下划线\n" +
                "\t\n" +
                "V1.2(2021/8/9/13:31:27 - 2021/8/9/17:38:19)\n" +
                "\t1.支持maven，提供与spring-boot的starter启动类，无需手动导入jar包，只需导入依赖即可：dbdtobean-spring-boot-start\n" +
                "\t2.优化整体结构，降低耦合度，提供对外接口\n" +
                "\t3.去掉了数据库所有表生成时，自动生成文件功能，现在文件路径的接口都移到exportToFile中，需要手动调用该接口\n" +
                "\t4.生成JavaBean文件时，路径可以有不存在的文件夹，不存在则会在路径后创建文件夹\n" +
                "\t5.解决了 当字段为x_xxx时，生成set和get，与Java定义的set和get不规范，\n" +
                "\t\t如：private String bUser \n" +
                "\t\t原版：生成set和get 为       public void setBUser(String bUser){this.bUser = bUser;}  public String getBUser(){return bUser;}  \n" +
                "\t\t新版：方法名符合Java规范    public void setbUser(String bUser){this.bUser = bUser;}  public String getbUser(){return bUser;}  \n" +
                "\t6.支持springboot的自动装配，并且提供properties类，只需在application配置文件里以dbdtobean开头的数据库参数，即可自动生成数据库Connection对象\n" +
                "\t7.把类注解的@CreateTime替换为@Create\n" +
                "\t8.支持类注释@Author的自定义，默认读取电脑的用户名\n" +
                "\t\n" +
                "\tbug：1.mysql的blob类型无法转换成Java的byte[]类型\n" +
                "\t后期：beanNameUpOrLow作用是把首字母改为大小写，该名称应该改为FirstBeanNameUpOrLow\n" +
                "\t\t拆分文件名和属性值，因为下划线的统一使用set_ToUpper，应该一分为二，文件名下划线可改为setBeanName_ToUpper，属性值为setField_ToUpper\n" +
                "\t\tsetFieldNameIsLower不合理，默认把字段名改为小写，应该把默认改为不改变字段名称，提高布尔值才确定改不改\n" +
                "\t\n" +
                "V1.3(2021.9.19 16:14:32 - 2021.9.24 15:52:49) \n" +
                "\t(2021.9.19)\n" +
                "\t1.添加CustomComment和DefaultComment类，将注释相关内容从核心类移出到这两个类，减少耦合度，提高代码优雅度\n" +
                "\t2.添加上下文DBDToBeanContext类，使用单例模式存储需要在核心类使用的其他类，全局管理者\n" +
                "\t3.将核心类的工具方法，移出到DBDToBeanUtils工具类里，对外提供API，如首字母小写，获取随机数等\n" +
                "\t4.将beanNameUpOrLow改为beanFirstNameUp\n" +
                "\t\n" +
                "\t(2021.9.20)\n" +
                "\t1.添加DBDToBeanDefinition类，将配置信息相关内容从核心类移出到这个类，减少耦合度，提高代码优雅度\n" +
                "\t2.优化核心类代码，减少不必要的判断等，使得代码更加简洁优雅\n" +
                "\t3.添加HeadComment头部注释类，自定义Author等信息\n" +
                "\t4.优化控制台输出提示信息\n" +
                "\t5.导出文件API添加返回值：导出文件所在的路径\n" +
                "\t\n" +
                "\t(2021.9.21)中秋节\n" +
                "\t1.添加两个接口，统一规范\n" +
                "\t2.简单实现了 指定mvc路径，即可生成以数据库表名为前缀的mvc的Java文件，mvc指的是Controller、Service、Dao、Mapper\n" +
                "\t3.不指定mvc路径，则不生产对应的Java文件\n" +
                "\t\n" +
                "\t(2021.9.21)\n" +
                "\t1.添加AbstractDBDToMVC类，基于不同的mvc层生成不同的文件，所有mvc类继承此类\n" +
                "\t2.添加DBDToMVCDefinition类，存有mvc的前缀和后缀\n" +
                "\t3.添加自定义前缀和后缀，包括统一的mvc前缀和后缀，以及不同的mvc(Controller、Service、Dao、Mapper)的前缀和后缀\n" +
                "\t4.提供默认的前缀和后缀，如实现类后缀为Impl\n" +
                "\t\n" +
                "\t纠结：目前生成不同的mvc文件需要不同的mvc的静态常量进行判断，但是DBDToMVCDefinition类有了与静态常量对应的变量，是否替换，去掉不必要的静态常量呢？\n" +
                "\t\n" +
                "\t(2021.9.23)\n" +
                "\t1.提供自定义实现类的相比较父类接口的路径，默认为impl\n" +
                "\t2.将DBDToBeanProperties类移入DBDToBeanContext类，实现单例以及统一管理\n" +
                "\t3.生成的MVC文件提供 CRUD(增删改查) 共5个方法生成，默认不开启生成\n" +
                "\t4.MVC文件 支持生成类注释，与实体类注释开启同步\n" +
                "\t\n" +
                "\t后期（未实现）：提供自定义接口，实现自定义CRUD方法，每个MVC文件统一生成这些方法\n" +
                "\t\n" +
                "\t(2021.9.24)\n" +
                "\t1.支持maven目录以及普通目录\n" +
                "\t2.普通目录下，Mapper的xml文件与接口文件处于同一个目录\n" +
                "\t3.提供API使用说明，位于LogInfo类的方法里\n" +
                "\t\n" +
                "\t(2021.9.25)\n" +
                "\t1.提供选择 生成mvc文件，类上方加上对他的mvc注解\n" +
                "\t2.优化spring boot的starter，提供更多的配置信息，即直接在配置文件设置好参数，即可导入，如数据库驱动，url等\n" +
                "\t3.添加 spring boot配置文件输入dbdbtobean的参数，会有提示的功能\n" +
                "\t4.优化dbdtobean多个类加入spring容器的顺序\n" +
                "\t\n" +
                "V1.4(2022.2.6 13:14:25 - 2020.2.6 22:44:55) \n" +
                "\n" +
                "\t1.优化生成 MVC 文件时，import 的间距过长\n" +
                "\t2.生成 Controller 类时，自动调用 Service 对应的方法，以及 Service 调用 Dao 或 Mapper 对应的方法（两者都存在时，Mapper 优先级大于 Dao）\n" +
                "\t";
    }
}
