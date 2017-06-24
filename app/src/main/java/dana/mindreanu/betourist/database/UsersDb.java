package dana.mindreanu.betourist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsersDb extends SQLiteOpenHelper {

	  public static final String TABLE_USERS = "Users";
	  public static final String COLUMN_ID = "UserID";
	  public static final String COLUMN_EMAIL = "Email";
	  public static final String COLUMN_TYPE = "Type";
	  public static final String COLUMN_COOKIEVAL = "CookieVal";
	  public static final String COLUMN_ISGCMREGISTERED = "IsGcmRegistered";

	  private static final String DATABASE_NAME = "betourist.db.users";
	  private static final int DATABASE_VERSION = 4;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
			+ TABLE_USERS + "(" + COLUMN_ID
	      	+ " integer primary key autoincrement, " 
	      	+ COLUMN_EMAIL + " text not null, "
  			+ COLUMN_TYPE + " text not null,"
  			+ COLUMN_COOKIEVAL + " text,"
			+ COLUMN_ISGCMREGISTERED +" integer)";

	  public UsersDb(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
		  database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(UsersDb.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
	    onCreate(db);
	  }

	} 