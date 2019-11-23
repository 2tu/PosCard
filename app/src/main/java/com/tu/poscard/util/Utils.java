package com.tu.poscard.util;

import android.text.TextUtils;

import com.tu.poscard.data.model.Bankcard;
import com.tu.poscard.data.model.PaymentStatusEnum;
import com.tu.poscard.data.model.Statement;
import com.tu.poscard.data.model.WrapSettleType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import timber.log.Timber;

/**
 * @auther Tu
 * @date 2018/12/21
 * @email enum@foxmail.com
 */
public final class Utils {

    public static final String DATE_FORMAT_YM_U = "yyyy年MM月";
    public static final String DATE_FORMAT_YMD_U = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_YM = "yyyyMM";
    public static final String DATE_FORMAT_YMD = "yyyyMMdd";
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";


    public static final String DATE_FORMAT_MD_U = "MM月dd日";
    public static final String DATE_FORMAT_MD = "MMdd";

    /**
     * 还款日
     *
     * @param bankCard {@link Bankcard}
     * @return yyyyMMdd
     */
    public static String paymentDueDate(Bankcard bankCard) {
        SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (!TextUtils.isEmpty(bankCard.getPayment_due_date())) {
            //还款日
            int dueDay = Integer.valueOf(bankCard.getPayment_due_date());
            if (dueDay < nowDay) {
                calendar.add(Calendar.MONTH, 1);
            }
            calendar.set(Calendar.DAY_OF_MONTH, dueDay);
            return dd.format(calendar.getTime());
        } else {
            //账单日 + due day
            if (!TextUtils.isEmpty(bankCard.getPayment_due_day()) && !TextUtils.isEmpty(bankCard.getStatement_date())) {
                int statementDate = Integer.valueOf(bankCard.getStatement_date());
                if (nowDay < statementDate) {
                    calendar.add(Calendar.MONTH, -1);
                }
                calendar.set(Calendar.DAY_OF_MONTH, statementDate);
                calendar.add(Calendar.DAY_OF_YEAR, Integer.valueOf(bankCard.getPayment_due_day()));

                return dd.format(calendar.getTime());
            }
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date       日期
     * @param oldPattern 日期格式
     * @param newPattern 新日期格式
     * @return 新日期格式化后的日期
     */
    public static String formatDate(String date, String oldPattern, String newPattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldPattern);
        try {
            Date pDate = simpleDateFormat.parse(date);
            simpleDateFormat = new SimpleDateFormat(newPattern);
            return simpleDateFormat.format(pDate);
        } catch (ParseException e) {
            Timber.e(e);
        }
        return null;
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期格式化
     *
     * @param pattern 日期格式
     * @return 新日期格式化后的日期
     */
    public static String formatDate(int year, int month, int day, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取月的最小最大
     *
     * @param ymu 2018年12月
     * @return [20181201, 20181231]
     */
    public static String[] getMonth(String ymu) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YM_U);
        try {
            Date pDate = simpleDateFormat.parse(ymu);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            SimpleDateFormat ymd = new SimpleDateFormat(DATE_FORMAT_YMD);
            String minDay = ymd.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            ymd = new SimpleDateFormat(DATE_FORMAT_YMD);
            String maxDay = ymd.format(calendar.getTime());


            return new String[]{minDay, maxDay};
        } catch (ParseException e) {
            Timber.e(e);
        }
        return null;
    }

    public static String[] getMonthStrar(String ymu) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YM_U);
        try {
            Date pDate = simpleDateFormat.parse(ymu);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            SimpleDateFormat ymd = new SimpleDateFormat(DATE_FORMAT_YMD);
            String minDay = ymd.format(calendar.getTime());
            return new String[]{minDay};
        } catch (ParseException e) {
            Timber.e(e);
        }
        return null;
    }

    /**
     * 还款日剩余天数
     *
     * @param paymentDueDate
     * @return
     */
    public static Integer paymentDay(String paymentDueDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date pamentDate = simpleDateFormat.parse(paymentDueDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pamentDate);

            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(new Date());


            int day1 = calendarNow.get(Calendar.DAY_OF_YEAR);
            int day2 = calendar.get(Calendar.DAY_OF_YEAR);

            int year1 = calendarNow.get(Calendar.YEAR);
            int year2 = calendar.get(Calendar.YEAR);
            if (year1 != year2)   //同一年
            {
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                    {
                        timeDistance += 366;
                    } else    //不是闰年
                    {
                        timeDistance += 365;
                    }
                }

                return timeDistance + (day2 - day1);
            } else    //不同年
            {
                System.out.println("判断day2 - day1 : " + (day2 - day1));
                return day2 - day1;
            }

        } catch (ParseException e) {
            Timber.e(e);
        }
        return null;
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 格式化 银行卡
     *
     * @param bankcard {@link Bankcard}
     * @return
     */
    public static String bankcard(Bankcard bankcard) {
        String cardno = toString(bankcard.getCardno());
        cardno = cardno.length() > 4 ? cardno.substring(cardno.length() - 4) : cardno;
        return toString(bankcard.getName()) + " - " + toString(bankcard.getBank()) + " - " + cardno;
    }

    /**
     * 格式化银行卡号
     *
     * @param cardNo
     * @return
     */
    public static String formatCardNo(String cardNo) {
        return cardNo.replaceAll("\\d{4}(?!$)", "$0 ");
    }

    /**
     * 格式化结算方式的公式
     *
     * @param wrapSettleType
     * @return
     */
    public static String settleExpression(WrapSettleType wrapSettleType) {
        return "(a * "
                + MathUtils.toString(MathUtils.defaultBigDecimal(wrapSettleType.getServiceCharge()).divide(new BigDecimal(100)), 4) + " + " + MathUtils.toString(wrapSettleType.getExtraCharge()) + ")";
    }

    /**
     * 格式化金额
     *
     * @param bigDecimal
     * @return
     */
    public static String formatMoney(BigDecimal bigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat(",##0.00");
        return decimalFormat.format(bigDecimal);
    }

    /**
     * 计算还款状态
     *
     * @param paymentDueDate {@link Statement#payment_due_date}
     * @param newBalance     {@link Statement#new_balance}
     * @param payment        {@link Statement#payment}
     * @return {@link PaymentStatusEnum}
     */
    public static PaymentStatusEnum paymentStatus(String paymentDueDate, BigDecimal newBalance, BigDecimal payment) {

        Integer day = Utils.paymentDay(paymentDueDate);
        return
                (null == day || BigDecimal.ZERO.compareTo(MathUtils.subtract(newBalance, payment)) != -1)
                        ? PaymentStatusEnum.PAY_OFF
                        : day >= 0 ? PaymentStatusEnum.REPAYMENT_PENDING : PaymentStatusEnum.OVERDUE;

    }

}
