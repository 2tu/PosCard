package com.tu.poscard.data.db;


import android.content.ContentValues;
import android.content.Context;

import com.tencent.wcdb.DatabaseErrorHandler;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteDirectCursor;
import com.tencent.wcdb.database.SQLiteOpenHelper;
import com.tu.poscard.data.model.Statement;
import com.tu.poscard.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tu
 * @date 2018/12/11
 * @email enum@foxmail.com
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "pos-card.db";
    static final int DATABASE_VERSION = 6;

    // The test database is taken from SQLCipher test-suit.
    //
    // To be compatible with databases created by the official SQLCipher
    // library, a SQLiteCipherSpec must be specified with page size of
    // 1024 bytes.
    static final SQLiteCipherSpec CIPHER_SPEC = new SQLiteCipherSpec()
            .setPageSize(1024);


    // We don't want corrupted databases get deleted or renamed on this sample,
    // so use an empty DatabaseErrorHandler.
    static final DatabaseErrorHandler ERROR_HANDLER = new DatabaseErrorHandler() {
        @Override
        public void onCorruption(SQLiteDatabase dbObj) {
            // Do nothing
        }
    };

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CIPHER_SPEC, null,
                DATABASE_VERSION, ERROR_HANDLER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE settle_type ( id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name  varchar(256)," +
                "d_service_charge  double NOT NULL DEFAULT 1," +
                "d_extra_charge  double NOT NULL DEFAULT 0," +
                "t_service_charge  double NOT NULL DEFAULT 1," +
                "t_extra_charge  double NOT NULL DEFAULT 0," +
                "create_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))," +
                "update_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))" +
                ");");
        db.execSQL("CREATE TABLE bank_card ( id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "cardno  varchar(256)," +
                "nik_name  varchar(256)," +
                "name  varchar(256)," +
                "bank_id  INTEGER," +
                "bank  varchar(256)," +
                "card_type  varchar(256) DEFAULT NULL," +
                "credit_limit  double DEFAULT NULL," +
                "statement_date  varchar(256)," +
                "payment_due_date  varchar(256)," +
                "payment_due_day  varchar(3) DEFAULT NULL," +
                "description  varchar(1024) DEFAULT NULL," +
                "create_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))," +
                "update_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))" +
                ");");

        db.execSQL("CREATE TABLE statement ( id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "bankcard_id INTEGER DEFAULT NULL," +
                "bankcard  varchar(256)," +
                "new_balance  double," +
                "min_payment  double," +
                "new_payment  double NOT NULL DEFAULT 0," +
                "payment_due_date  varchar(256)," +
                "event_id  varchar(256)," +
                "status INTEGER NOT NULL DEFAULT 0," +
                "create_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))," +
                "update_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))" +
                ");");

        db.execSQL("CREATE TABLE sold ( id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "sold  varchar(256)," +
                "description  varchar(256)," +
                "amount  double," +
                "bankcard_id INTEGER DEFAULT NULL," +
                "bankcard  varchar(256)," +
                "trans_type  varchar(256)," +
                "service_charge  double," +
                "extra_charge  double," +
                "create_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))," +
                "update_time  TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))" +
                ");");

        // OPTIONAL: backup master info for corruption recovery.
        // However, we want to test recovery feature, so omit backup here.

        //RepairKit.MasterInfo.save(db, db.getPath() + "-mbak", PASSPHRASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2) {
            String sql_column_add_payment = "ALTER TABLE statement ADD COLUMN payment double NOT NULL DEFAULT 0;";
            db.execSQL(sql_column_add_payment);
        } else if (oldVersion == 3) {
            String sql_column_add_credit_limit = "ALTER TABLE bank_card ADD COLUMN credit_limit double DEFAULT NULL;";
            String sql_column_add_description = "ALTER TABLE bank_card ADD COLUMN description  varchar(1024) DEFAULT NULL;";

            db.execSQL(sql_column_add_credit_limit);
            db.execSQL(sql_column_add_description);
        } else if (oldVersion == 4) {
            String sql_column_add_new_payment = "ALTER TABLE statement ADD COLUMN new_payment double NOT NULL DEFAULT 0;";
            db.execSQL(sql_column_add_new_payment);
            com.tencent.wcdb.Cursor cursor = db.rawQueryWithFactory(SQLiteDirectCursor.FACTORY, "select id,payment from statement;", null, "statement");

            List<Statement> statements = new ArrayList<>();
            while (cursor.moveToNext()) {
                Statement data = new Statement();
                data.setId(cursor.getInt(cursor.getColumnIndex("id")));
                data.setNew_payment(new BigDecimal(cursor.getDouble(cursor.getColumnIndex("payment"))));

                statements.add(data);
            }
            cursor.close();

            if (statements.size() > 0) {
                for (Statement data : statements
                        ) {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put("new_payment", null == data.getPayment() ? "0" : Utils.formatMoney(data.getPayment()));
                    db.update("statement", contentValues, "id = ?", new String[]{String.valueOf(data.getId())});
                }

            }

        }else if(oldVersion == 5){
            // 增加日历字段
            String sql_column_add_new_payment = "ALTER TABLE statement ADD COLUMN event_id varchar(256);";
            db.execSQL(sql_column_add_new_payment);
        }
    }
}