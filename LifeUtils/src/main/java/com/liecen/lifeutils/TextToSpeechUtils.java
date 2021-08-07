package com.liecen.lifeutils;

import android.speech.tts.TextToSpeech;

import com.vastweb.mainappmvp.app.MyApplication;

public class TextToSpeechUtils {

    public static TextToSpeech textToSpeech = null;//创建自带语音对象

    public static void initSpeech() {
        if (textToSpeech != null ) return;
        textToSpeech = new TextToSpeech(MyApplication.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    // Toast.makeText(MainActivity.this,"成功输出语音",
                    // Toast.LENGTH_SHORT).show();
                    // Locale loc1=new Locale("us");
                    // Locale loc2=new Locale("china");

                    textToSpeech.setPitch(1.0f);//方法用来控制音调
                    textToSpeech.setSpeechRate(0.9f);//用来控制语速

                    //判断是否支持下面两种语言
                    int result1 = textToSpeech.setLanguage(java.util.Locale.US);
                    int result2 = textToSpeech.setLanguage(java.util.Locale.SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

                    android.util.Log.i("c", "US支持否？--》" + a +
                            "\nzh-CN支持否》--》" + b);
                    LogUtil.v("TextToSpeechUtils",status);

                } else {
                }

            }
        });


    }

    public static void startSpek(String speechText) {
        if (textToSpeech == null ){
            initSpeech();
        }
        textToSpeech.setPitch(1.0f);
        // 设置语速
        textToSpeech.setSpeechRate(0.9f);
        textToSpeech.speak(speechText,//输入中文，若不支持的设备则不会读出来
                TextToSpeech.QUEUE_FLUSH, null);
    }

    public static void stopSpek(String speechText) {
        textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        textToSpeech.shutdown(); // 关闭，释放资源
    }

}
