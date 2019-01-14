package constant;

public class Database {
    public static final String DB_NAME = "SkyLight.db";
    public static final int DB_VERSION = 7;
    public static final int QUERY_TYPE_INSERT = 0;
    public static final int QUERY_TYPE_SELECT = 1;
    public static final int QUERY_TYPE_UPDATE = 2;
    public static final int QUERY_TYPE_DELETE = 3;
    public static final String TABLE_NAME_CHARACTER = "m_character";
    public static final String TABLE_NAME_ITEM = "m_item";
    public static final String CREATE_TABLE_CHARACTER_SQL =
            "create table " + TABLE_NAME_CHARACTER + " ("
                    + "index integer primary key autoincrement,"
                    + "name text,"
                    + "type integer,"
                    + "hp integer,"
                    + "atk integer,"
                    + "skill integer"
                    + ");";
}
