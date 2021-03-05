package com.analyzing.sql;

import java.io.File;
import java.io.PrintWriter;

/**
* @description 根据Mapper文件反向生成数据库表结构
* @author los
* @date 2021/3/5 14:35
* @param
* @return
*/
public class MapperParseTest {

    public static void main(String[] args) throws Exception {
        String packageName = "com.dao";
        StringBuilder sb = new StringBuilder();
        searchFile(packageName, sb);

        // 导出文件
        File fp = new File("/Users/los/database1.sql");
        PrintWriter pfp = new PrintWriter(fp);
        pfp.print(sb.toString());
        pfp.close();
    }

    /**
    * @description 查找mapper文件， 并生成sql语句
    * @author los
    * @date 2021/3/5 14:35
    * @param
    * @return
    */
    public static void searchFile(String packageName, StringBuilder sb) throws Exception {
        //URL url = MapperParseTest.class.getClassLoader().getResource(packageName.replace(".", "/"));
        File dir = new File("/Users/los/IdeaProjects/gitee/Singapore/src/test/java/singapore/mapper");
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                searchFile(packageName.replace(".", "/") + "/" + file.getName(), sb);
            } else {
                if (file.getName().indexOf("Mapper.xml") > 0) {
                    sb.append(MapperParse.parse(file));
                }
            }
        }
    }
}
