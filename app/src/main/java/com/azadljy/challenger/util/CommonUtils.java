package com.azadljy.challenger.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtils {

    public interface CommonInterface {
        void execute();
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }


    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static String longToDate(long lo, String pattern) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);//yyyy-MM-dd HH:mm:ss
        return sd.format(date);
    }


    /**
     *     * 获取两个日期之间的间隔天数
     *     * @return
     *     
     */
    public static int getGapCount(String startTime, String endTime, String pattern) throws ParseException {
        Date startDate = stringToDate(startTime, pattern);
        Date endDate = stringToDate(endTime, pattern);
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }


    public static void hideIM(Context context, View edt) {
        try {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            IBinder windowToken = edt.getWindowToken();
            if (windowToken != null) {
                im.hideSoftInputFromWindow(windowToken, 0);
            }
        } catch (Exception e) {

        }
    }


    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dip2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    /**
     * px转换dip
     */
    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * px转换sp
     */
    public static int px2sp(Context context, int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp转换px
     */
    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 读取本地json
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //获取当前时间
    public static String getCurrentData(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获取当前日期前或后n天日期
    public static long getDate(int days, String date, String format) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(stringToDate(date, format));
            c.add(Calendar.DATE, days);
            Date d = c.getTime();
            return dateToLong(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    //获取当前日期前或后n天日期
    public static long getDate(int days, long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        return dateToLong(d);
    }


    public static GregorianCalendar getBeforeDays(GregorianCalendar value, int days) {
        if (days != 0) {
            value.add(5, -days);
            return value;
        } else
            return value;
    }

    public static GregorianCalendar getAfterDays(GregorianCalendar value, int days) {
        if (days != 0) {
            value.add(5, days);
            return value;
        } else
            return value;
    }


    public static String FormatDateTime(int year, int month, int day) {
        String d = Integer.toString(year);
        String v = d;
        d = Integer.toString(month);
        if (d.length() < 2)
            v = v + "-0" + d;
        else
            v = v + "-" + d;
        d = Integer.toString(day);
        if (d.length() < 2)
            v = v + "-0" + d;
        else
            v = v + "-" + d;

        return v;
    }


    /**
     * 进行添加水印图片和文字
     *
     * @param src
     * @param waterMak
     * @return
     */
    public static Bitmap getWatermarkBitmap(Bitmap src, Bitmap waterMak, String title, Context context) {
        if (src == null) {
            return src;
        }
        // 获取原始图片与水印图片的宽与高
        int w = src.getWidth();
        int h = src.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0, 0, null);

        if (src != null && !src.isRecycled()) {
            src.recycle();
            src = null;
        }

        // 在src的右下角添加水印
        Paint paint = new Paint();
        //paint.setAlpha(100);

        if (waterMak != null) {
            int ww = waterMak.getWidth();
            int wh = waterMak.getHeight();
            mCanvas.drawBitmap(waterMak, w - ww - 5, h - wh - 5, paint);
            if (waterMak != null && !waterMak.isRecycled()) {
                waterMak.recycle();
                waterMak = null;
            }
        }

        // 开始加入文字
        if (null != title) {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.parseColor("#FCC505"));
            textPaint.setTextSize(sp2px(context, 15));
            String familyName = "宋体";
            Typeface typeface = Typeface.create(familyName,
                    Typeface.BOLD);
            textPaint.setTypeface(typeface);
//            textPaint.setTextAlign(Align.LEFT);
            //mCanvas.drawText(title, w / 2, 25, textPaint);

            float textWidth = textPaint.measureText(title);
//            mCanvas.drawText(title, w - textWidth - 10, h - 46, textPaint);
            String data[] = title.split("~");

            mCanvas.drawText(data[0], CommonUtils.dip2px(context, 9), h - 100, textPaint);
            mCanvas.drawText(data[1], CommonUtils.dip2px(context, 9), h - 50, textPaint);
        }

        //todo   修改了
//		mCanvas.save(Canvas.ALL_SAVE_FLAG);
        mCanvas.save();
        mCanvas.restore();
        return newBitmap;
    }


    //通过经纬度获取距离(单位：米)
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }


    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }


    //设置Margins
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        Log.e("TAG", "isContainChinese: " + m.find());
        if (m.find()) {
            return true;
        }
        return false;
    }


}
