package uz.klimuz.soundengineer;

import android.provider.BaseColumns;

public class ChannelsContract {
    public static final class ChannelsEntry implements BaseColumns{
        public static final String TABLE_NAME = "channels";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_RIO_NAME = "rioName";
        public static final String COLUMN_RIO_NUMBER = "rioNumber";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PICKUP = "pickup";
        public static final String COLUMN_NOTE = "note";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_NUMBER +
                " " + TYPE_INTEGER + ", " + COLUMN_RIO_NAME + " " + TYPE_TEXT + ", " + COLUMN_RIO_NUMBER +
                " " + TYPE_TEXT + ", " + COLUMN_NAME + " " + TYPE_TEXT + ", " + COLUMN_PICKUP +
                " " + TYPE_TEXT + ", " + COLUMN_NOTE + " " + TYPE_TEXT + ")";
        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
