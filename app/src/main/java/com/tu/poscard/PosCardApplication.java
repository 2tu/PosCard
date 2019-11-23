package com.tu.poscard;

import android.app.Application;

import com.tencent.wcdb.database.SQLiteOpenHelper;
import com.tu.poscard.data.db.DBHelper;
import com.tu.poscard.data.model.WrapSettleType;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tu
 * @date 2018/12/13
 * @email enum@foxmail.com
 */
public class PosCardApplication extends Application {
  public SQLiteOpenHelper dbHelper;
  private List<WrapSettleType> settleTypeList = new ArrayList<>();

  @Override public void onCreate() {
    super.onCreate();
    initDB();
  }

  private void initDB() {
    dbHelper = new DBHelper(this);
  }

  @Override public void onTerminate() {
    super.onTerminate();
    dbHelper.close();
    dbHelper = null;
  }

  public void setSettleTypeList(List<WrapSettleType> settleTypeList) {
    this.settleTypeList.clear();
    this.settleTypeList.addAll(settleTypeList);
  }

  public List<WrapSettleType> getSettleTypeList() {
    return settleTypeList;
  }
}
