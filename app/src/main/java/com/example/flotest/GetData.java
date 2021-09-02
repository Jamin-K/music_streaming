package com.example.flotest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    String errorString = null;
    private String ip = "14.44.6.129";
    private static String TAG = "flotest";
    String title[];
    String artist[];
    String imgurl[];


    public String[] getTitle(){
        return title;
    }

    public String[] getArtist(){
        return artist;
    }

    public String[] getImgurl(){
        return imgurl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(result == null){
            Log.d(TAG, "error"+result);
        }
        else{
            Log.d(TAG, "song list call");
            try{
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.getString("kjm");
                JSONArray jsonArray = new JSONArray(data);
                title = new String[jsonArray.length()];
                artist = new String[jsonArray.length()];
                imgurl = new String[jsonArray.length()];

                for(int i = 0 ; i<jsonArray.length(); i++){
                    JSONObject subObject = jsonArray.getJSONObject(i);
                    title[i] = subObject.getString("title");
                    artist[i] = subObject.getString("artist");
                    imgurl[i] = subObject.getString("imgurl");
                }

            }
            catch (JSONException e){
                e.printStackTrace();
            }


            //System.out.println(result);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String serverURL = "http://"+ip+"/getsonglist.php";
        //String serverURL = "http://"+ip+"/query.php";
        //String postParameters = "title = 바람기억 &artist 나얼";
        try{
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            System.out.println("-------------------------------------- connect");


            //OutputStream outputStream = httpURLConnection.getOutputStream();
            //outputStream.write(postParameters.getBytes("UTF-8"));
            //outputStream.flush();
            //outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "response code - " + responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }


            bufferedReader.close();

            System.out.println("-------------------------------------- input complete");


            return sb.toString().trim();

        }
        catch (Exception e){
            Log.d(TAG, "InsertData: Error ", e);
            errorString = e.toString();

            return null;

        }


    }
}
