package com.fan.volleysample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "11122";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNetworkImageView();
            }
        });

//        testXMLRequest();
        testGsonRequest();



        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonString = "{\n" +
                        "    \"weatherinfo\": {\n" +
                        "        \"city\": \"鍖椾含\",\n" +
                        "        \"cityid\": \"101010100\",\n" +
                        "        \"temp\": \"27.9\",\n" +
                        "        \"WD\": \"鍗楅\uE5D3\",\n" +
                        "        \"WS\": \"灏忎簬3绾\\ufffd\",\n" +
                        "        \"SD\": \"28%\",\n" +
                        "        \"AP\": \"1002hPa\",\n" +
                        "        \"njd\": \"鏆傛棤瀹炲喌\",\n" +
                        "        \"WSE\": \"<3\",\n" +
                        "        \"time\": \"17:55\",\n" +
                        "        \"sm\": \"2.1\",\n" +
                        "        \"isRadar\": \"1\",\n" +
                        "        \"Radar\": \"JC_RADAR_AZ9010_JB\"\n" +
                        "    }\n" +
                        "}";
                Type type = new TypeToken<Weather>(){}.getType();
                Weather result = new Gson().fromJson(jsonString, type);
                System.out.println();


               String jsonString2 = "[{\n" +
                        "    \"weatherinfo\": {\n" +
                        "        \"city\": \"鍖椾含\",\n" +
                        "        \"cityid\": \"101010100\",\n" +
                        "        \"temp\": \"27.9\",\n" +
                        "        \"WD\": \"鍗楅\uE5D3\",\n" +
                        "        \"WS\": \"灏忎簬3绾\\ufffd\",\n" +
                        "        \"SD\": \"28%\",\n" +
                        "        \"AP\": \"1002hPa\",\n" +
                        "        \"njd\": \"鏆傛棤瀹炲喌\",\n" +
                        "        \"WSE\": \"<3\",\n" +
                        "        \"time\": \"17:55\",\n" +
                        "        \"sm\": \"2.1\",\n" +
                        "        \"isRadar\": \"1\",\n" +
                        "        \"Radar\": \"JC_RADAR_AZ9010_JB\"\n" +
                        "    }\n" +
                        "},{\n" +
                        "    \"weatherinfo\": {\n" +
                        "        \"city\": \"鍖椾含\",\n" +
                        "        \"cityid\": \"101010100\",\n" +
                        "        \"temp\": \"27.9\",\n" +
                        "        \"WD\": \"鍗楅\uE5D3\",\n" +
                        "        \"WS\": \"灏忎簬3绾\\ufffd\",\n" +
                        "        \"SD\": \"28%\",\n" +
                        "        \"AP\": \"1002hPa\",\n" +
                        "        \"njd\": \"鏆傛棤瀹炲喌\",\n" +
                        "        \"WSE\": \"<3\",\n" +
                        "        \"time\": \"17:55\",\n" +
                        "        \"sm\": \"2.1\",\n" +
                        "        \"isRadar\": \"1\",\n" +
                        "        \"Radar\": \"JC_RADAR_AZ9010_JB\"\n" +
                        "    }\n" +
                        "}]";
                Type type2 = new TypeToken<List<Weather>>(){}.getType();
                List<Weather> result2 = new Gson().fromJson(jsonString2, type2);
                System.out.println();
            }
        });

    }


    private void testGsonRequest() {
        Type type = new TypeToken<List<Weather>>(){}.getType();
// fromJson返回Map或者List, ClassCastException!
//        Response<String> o = new Gson().fromJson(json, type);/
        Type type1 = new TypeToken<Weather>(){}.getType();
        Type tt  = new CCTypeToken<Weather>().getType(); // 不好用
        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(Request.Method.GET, "http://www.weather.com.cn/data/sk/101010100.html", type1, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                WeatherInfo weatherInfo = response.getWeatherinfo();
                Log.d("TAG", "city is " + weatherInfo.getCity());
                Log.d("TAG", "temp is " + weatherInfo.getTemp());
                Log.d("TAG", "time is " + weatherInfo.getTime());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(gsonRequest);

    }


    private void testXMLRequest(){
        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET, "http://flash.weather.com.cn/wmaps/xml/china.xml", new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                if ("city".equals(nodeName)) {
                                    String pName = response.getAttributeValue(0);
                                    Log.d("TAG", "pName is " + pName);
                                }
                                break;
                        }
                        eventType = response.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(xmlRequest);
    }

    private void testNetworkImageView(){

       NetworkImageView networkImageView =  findViewById(R.id.network_image_view);

       ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
           @Override
           public Bitmap getBitmap(String url) {
               return null;
           }

           @Override
           public void putBitmap(String url, Bitmap bitmap) {

           }
       });

       networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
       networkImageView.setErrorImageResId(R.mipmap.ic_launcher_round);
       networkImageView.setImageUrl("https://avatar.csdn.net/5/6/A/3_u010356768.jpg", imageLoader);
    }

    private void  testImageLoader(){

        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        final ImageView imageView = findViewById(R.id.image);
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round);
        imageLoader.get("https://avatar.csdn.net/5/6/A/3_u010356768.jpg", imageListener);
    }

    private void testImageRequest(){
        final ImageView imageView = findViewById(R.id.image);
        ImageRequest imageRequest = new ImageRequest("https://avatar.csdn.net/5/6/A/3_u010356768.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                imageView.setImageBitmap(response);
            }
        }, 90, 90, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);
    }

    private void testJsonRequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://www.tianqiapi.com/api/?version=v1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               try {
                   Log.d(TAG, response.toString());
                   Log.d(TAG, response.getString("errmsg"));
                   Log.d(TAG, response.getString("errcode"));
               }catch (Exception e) {
                   e.printStackTrace();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            public void cancel() {
                super.cancel();
            }

            @Override
            public byte[] getBody() {
                return super.getBody();
            }

            @Override
            public String getBodyContentType() {
                return super.getBodyContentType();
            }

            @Override
            public Priority getPriority() {
                return super.getPriority();
            }

            @Override
            protected String getParamsEncoding() {
                return super.getParamsEncoding();
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    private void testStringRequest(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu1.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        requestQueue.add(stringRequest);
    }
}

class CCTypeToken<T> extends TypeToken<T> {

    public CCTypeToken() {
        super();
    }
}