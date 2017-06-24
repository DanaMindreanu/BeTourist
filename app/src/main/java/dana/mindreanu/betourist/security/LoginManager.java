package dana.mindreanu.betourist.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import dana.mindreanu.betourist.servercommunication.ServerValues;

public class LoginManager
{
    private static LoginManager instance = null;
    private User loggedInUser = null;
    private Context context;
    private volatile boolean isDoingWork = false;

    /**
     * Constructor that creates user with context
     * @param ctx
     */
    private LoginManager(Context ctx){
        this.context = ctx;
    }

    /**
     * Get instance of the login manager
     * @return
     */
    public static LoginManager getInstance(Context ctx)
    {
        if(instance == null){
            synchronized(LoginManager.class){
                if(instance == null){
                    instance = new LoginManager(ctx);
                }
            }
        }

        return instance;
    }

    /**
     * Gets the logged in user
     * @return
     */
    public User getLoggedInUser()
    {
        if(loggedInUser == null){
           // loggedInUser = tryDefaultLogin();
        }
        return loggedInUser;
    }

//    /**
//     * Logging in a user: if the user is the first time on this device, create user in database
//     * @param email
//     * @param password
//     * @return
//     */
//    public User loginUser(final String email, final String password){
//        isDoingWork = true;
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpPost httppost = new
//                        HttpPost(ServerValues.SERVER_LOCATION + "/user/login");
//
//                ArrayList<NameValuePair> data = new ArrayList<NameValuePair>(1);
//                data.add(new BasicNameValuePair("email", email));
//                data.add(new BasicNameValuePair("password", password));
//
//                try{
//                    httppost.setEntity(new UrlEncodedFormEntity(data));
//                    final HttpResponse response = httpclient.execute(httppost);
//
//                    String responseText = EntityUtils.toString(response.getEntity());
//                    JSONObject responseJson = (JSONObject)JSONValue.parse(responseText);
//
//                    if(responseJson.get("error").toString().equalsIgnoreCase("1")){
//                        loggedInUser = null;
//                    }
//                    else {
//                        loggedInUser = UsersRepository.getInstance(context).getUser(email);
//                        if(loggedInUser == null){
//                            loggedInUser = UsersRepository.getInstance(context).createUser(email);
//                        }
//                        loggedInUser.setCookieVal(responseJson.get("session_id").toString());
//                        UsersRepository.getInstance(context).updateUser(loggedInUser);
//
//                        SharedPreferences sharedPref = context.getSharedPreferences("GuardianAngelPrefs", Context.MODE_PRIVATE);
//                        Editor editor = sharedPref.edit();
//
//                        editor.putInt("userId", loggedInUser.getUserId());
//                        editor.apply();
//
//                        Log.d("GuardianAngel", "Successfully logged in user");
//                    }
//                }
//                catch(Exception e){
//                    Log.e("GuardianAngel", e.toString());
//                }
//                finally{
//                    isDoingWork = false;
//                }
//
//                return null;
//            }
//
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//        while(isDoingWork){
//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//            }
//        }
//        return loggedInUser;
//    }
//
//    /**
//     * Tries the saved user instance
//     * @return
//     */
//    private User tryDefaultLogin(){
//        SharedPreferences sharedPref = context.getSharedPreferences("GuardianAngelPrefs", Context.MODE_PRIVATE);
//        int userId = sharedPref.getInt("userId", -1);
//
//        if(userId != -1)
//            return UsersRepository.getInstance(context).getUser(userId);
//
//        return null;
//    }
//
//    /**
//     * Registers a new user
//     */
//    public void registerUser(final String email, final String password, final UserType userType,
//                             final String guardian) {
//        isDoingWork = true;
//
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpPost httppost = new
//                        HttpPost(ServerValues.SERVER_LOCATION + "/user/registration");
//
//                ArrayList<NameValuePair> data = new ArrayList<NameValuePair>(1);
//                data.add(new BasicNameValuePair("email", email));
//                data.add(new BasicNameValuePair("password", password));
//                data.add(new BasicNameValuePair("type", userType.toString()));
//
//                try{
//                    httppost.setEntity(new UrlEncodedFormEntity(data));
//                    final HttpResponse response = httpclient.execute(httppost);
//
//                    String responseText = EntityUtils.toString(response.getEntity());
//                    JSONObject responseJson = (JSONObject)JSONValue.parse(responseText);
//
//                    if(responseJson.get("error").toString().equalsIgnoreCase("1")){
//                        loggedInUser = null;
//                    }
//                    else {
//                        User usr = UsersRepository.getInstance(context).createUser(email);
//                        usr.setParentUser(guardian);
//                        usr.setType(userType);
//                        usr.setIsGcmRegistered(0);
//                        UsersRepository.getInstance(context).updateUser(usr);
//
//                        LoginManager.getInstance(context).loginUser(email, password);
//                        LoginManager.getInstance(context).addParentToUser(guardian);
//                    }
//                }
//                catch(Exception e){
//                    Log.d("GuardianAngel", e.toString());
//                }
//                isDoingWork = false;
//                return null;
//            }
//
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//        while(isDoingWork){}
//    }
//
//    protected void addParentToUser(String guardian) throws ClientProtocolException, IOException {
//        // TODO Auto-generated method stub
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new
//                HttpPost(ServerValues.SERVER_LOCATION + "/receiver/add_sender");
//        User usr = LoginManager.getInstance(context).getLoggedInUser();
//        ArrayList<NameValuePair> data = new ArrayList<NameValuePair>(1);
//        data.add(new BasicNameValuePair("session_id", usr.getCookieVal()));
//        data.add(new BasicNameValuePair("sender_email", guardian));
//
//        httppost.setEntity(new UrlEncodedFormEntity(data));
//        final HttpResponse response = httpclient.execute(httppost);
//
//        String responseText = EntityUtils.toString(response.getEntity());
//        JSONObject responseJson = (JSONObject)JSONValue.parse(responseText);
//
//        if(!responseJson.get("error").toString().equalsIgnoreCase("1")){
//            usr.setParentUser(guardian);
//            UsersRepository.getInstance(context).updateUser(usr);
//        }
//    }
}
