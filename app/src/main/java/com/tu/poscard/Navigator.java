package com.tu.poscard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.tu.poscard.data.model.Bankcard;
import com.tu.poscard.data.model.SelectMode;
import com.tu.poscard.data.model.Statement;
import com.tu.poscard.ui.BankcardAddActivity;
import com.tu.poscard.ui.BankcardDetailActivity;
import com.tu.poscard.ui.BankcardListActivity;
import com.tu.poscard.ui.SoldAddActivity;
import com.tu.poscard.ui.StatementAddActivity;

import io.card.payment.CardIOActivity;

/**
 * 导航
 *
 * @auther Tu
 * @date 2018/12/21
 * @email enum@foxmail.com
 */
public class Navigator {
    public static final int REQUEST_CODE_SOLD_ADD = 999;
    public static final int REQUEST_CODE_SOLD_LIST = 998;
    public static final int REQUEST_CODE_STATEMENT_ADD = 989;
    public static final int REQUEST_CODE_STATEMENT_LIST = 988;
    public static final int REQUEST_CODE_BANKCARD_LIST = 978;
    public static final int REQUEST_CODE_BANKCARD_SCAN = 100;
    public static final int REQUEST_CODE_BANKCARD_DETAIL=101;

    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_SELECT = "EXTRA_SELECT";

    public static void startBankcardListActivity(Activity activity, int requestCode, SelectMode selectMode) {
        Intent intent = new Intent(activity, BankcardListActivity.class);
        intent.putExtra(EXTRA_SELECT, selectMode);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startBankcardListActivity(Context context) {
        Intent intent = new Intent(context, BankcardListActivity.class);
        intent.putExtra(EXTRA_SELECT, SelectMode.NONE);
        context.startActivity(intent);
    }

    public static void bankcardAddActivity(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, BankcardAddActivity.class), requestCode);
    }

    public static void bankcardAddActivity(Activity activity, int requestCode, Bankcard bankcard) {
        Intent intent = new Intent(activity, BankcardAddActivity.class);
        intent.putExtra(EXTRA_DATA, bankcard);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void bankcardActivity(Activity activity, int requestCode, Bankcard bankcard) {
        Intent intent = new Intent(activity, BankcardDetailActivity.class);
        intent.putExtra(EXTRA_DATA, bankcard);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startSoldAddActivity(Context context) {
        context.startActivity(new Intent(context, SoldAddActivity.class));
    }

    public static void startSoldAddActivity(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, SoldAddActivity.class), requestCode);
    }

    public static void startStatementAddActivity(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, StatementAddActivity.class), requestCode);
    }

    public static void startStatementAddActivity(Activity activity, int requestCode, Statement statement) {
        Intent intent = new Intent(activity, StatementAddActivity.class);
        intent.putExtra(EXTRA_DATA, statement);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startCardIOActivity(Activity activity) {
        Intent intent = new Intent(activity, CardIOActivity.class)
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
//                        .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, mCvvToggle.isChecked())
//                        .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, mCardholderNameToggle.isChecked())
                .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)//去除水印
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)//去除键盘

                .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, false);

        activity.startActivityForResult(intent, REQUEST_CODE_BANKCARD_SCAN);
    }

}
