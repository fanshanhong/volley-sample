package com.fan.volleysample;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

public class GsonRequest<T> extends Request<T> {
    private final Gson mGson;
    private Response.Listener<T> mListener;
    private Class<T> mClass;
    private Type type;


    public GsonRequest(int  method, String url, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener){
        super(method, url,  errorListener);

        this.mListener = listener;

        mGson = new Gson();
        this.type = type;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    "UTF-8");
            jsonString = "[{\n" +
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
            jsonString = "{\n" +
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
//            jsonString = "{\n" +
//                    "    \"weatherinfo\": {\n" +
//                    "        \"city\": \"鍖椾含\",\n" +
//                    "        \"cityid\": \"101010100\",\n" +
//                    "        \"temp\": \"27.9\",\n" +
//                    "        \"WD\": \"鍗楅\uE5D3\",\n" +
//                    "        \"WS\": \"灏忎簬3绾\\ufffd\",\n" +
//                    "        \"SD\": \"28%\",\n" +
//                    "        \"AP\": \"1002hPa\",\n" +
//                    "        \"njd\": \"鏆傛棤瀹炲喌\",\n" +
//                    "        \"WSE\": \"<3\",\n" +
//                    "        \"time\": \"17:55\",\n" +
//                    "        \"sm\": \"2.1\",\n" +
//                    "        \"isRadar\": \"1\",\n" +
//                    "        \"Radar\": \"JC_RADAR_AZ9010_JB\",\n" +
//                    "\"list\": [\"a\", \"b\", \"c\"]\n" +
//                    "    }\n" +
//                    "}";


        //T result =     mGson.fromJson(jsonString, mClass);

       T result = mGson.fromJson(jsonString, type);

                return Response.success(
                    result,
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
