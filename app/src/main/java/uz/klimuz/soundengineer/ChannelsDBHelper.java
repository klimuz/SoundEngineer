package uz.klimuz.soundengineer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ChannelsDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "channels.db";
    private static final int DB_VERSION = 1;

    public ChannelsDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ChannelsContract.ChannelsEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ChannelsContract.ChannelsEntry.DROP_COMMAND);
        onCreate(db);
    }
}
