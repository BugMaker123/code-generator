package cn.com.ice.codeGenerator;

public class DatabaseInfo {
    /**
     * 定义数据源
     */
    private static String DATA_SOURCE="localhost:3306";

    /**
     * 数据库名称
     */
    private static String DATABASE_NAME="simple_template";

    /**
     * 时区
     */
    private static String SERVER_TIME_ZONE="Asia/Shanghai";
    private static String USER_UNICODE="true";
    private static String USE_SSL="false";
    /**
     * 字符集类型
     */
    private static String DATA_CHARSET="utf8";

    public static String USER_NAME="root";
    public static String PASSWORD="root123456";



    // useUnicode=true&useSSL=false&characterEncoding=utf8"
    // jdbc:mysql://localhost:3306/"+"simple_template"+"?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8
    public String getDatabaseInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        String mysqlUrl = stringBuilder.append("jdbc:mysql://").
                append(DATA_SOURCE).append("/").
                append(DATABASE_NAME).
                append("?").
                append("serverTimezone=").
                append(SERVER_TIME_ZONE).append("&").
                append("useUnicode=").append(USER_UNICODE).append("&").
                append("useSSL=").append(USE_SSL).append("&").
                append("characterEncoding=").append(DATA_CHARSET).toString();
        return mysqlUrl;
    }

    public static void main(String[] args) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        String str = databaseInfo.getDatabaseInfo();
        System.out.println(str);
        System.out.println("jdbc:mysql://localhost:3306/\"+\"simple_template\"+\"?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8");

    }
}
