package cn.longrise.www.androidprogress.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018-4-17.
 */

/**
 * 创建数据库时，默认路径是：/data/data/包名(cn.longrise.www.androidprogress)/databases/数据库名称
 * 构造方法中第二个参数如果只是数据库名称，则使用默认路径
 * 如果需要特定路径，则给构造方法中第二个参数传为：完整路径+数据库名称(如：/mnt/sdcard/temp/database.db)
 */

/**
 * 当运行getReadableDatabase()和getWritableDatabase()方法时，都会创建或者打开数据库。
 * 当表或者数据库不存在时，则创建，此时会运行onCreate()方法。
 * 当表和数据库均存在时，则不运行onCreate()方法。
 *
 * 当数据库版本发生变化时，则运行onUpgrade()方法。
 */

/**
 * 当对数据库版本要求不严格时，也就是不需要经常回滚数据库版本时，利用数据库版本进行控制onCreate()和onUpgrade()就会显得很鸡肋。
 * 因为，每次增加一个表格时，就需要更改数据库版本，才能运行onCreate()方法。
 */

/**
 * Android开发时，由于使用google给的SQLiteDatabase中的方法执行增删改查时，并不方便，因此大多开发继续使用传统SQL语句
 */

/**
 * 参考：https://www.jianshu.com/p/1b9899d48007
 */

public class SQLHelper extends SQLiteOpenHelper{
    private Context mContext;

    public static final String USER_SQL = "create table user(" +
            "id integer primary key autoincrement," +
            "name varchar," +
            "password varchar," +
            "phone varchar(11)" +
            "email varchar)";

    private SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public SQLHelper(Context context){
        this(context, PublicResource.DATABASE_NAME, null, PublicResource.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
