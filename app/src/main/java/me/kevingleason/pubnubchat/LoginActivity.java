package me.kevingleason.pubnubchat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Login Activity for the first time the app is opened, or when a user clicks the sign out button.
 * Saves the username in SharedPreferences.
 */
public class LoginActivity extends Activity {

    private String mUsername;
    //private TextView info;
    private String mUsername2;
    private LoginButton loginButton;
    private TextView info;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        LoginManager.getInstance().logOut();
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        //info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                /*info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                ); */

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                // Log.v("Output", response.getJSONObject().toString());
                                Log.i("Output", response.toString());
                                //mUsername =
                                //Log.i("Output2", response.getJSONObject().names().toString());
                                //mUsername2 = response.getJSONObject().getString("name");
                                final JSONObject jsonObject = response.getJSONObject();
                                try {
                                    mUsername = jsonObject.getString("name");

                                    Log.i("Output2", mUsername);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                SharedPreferences sp = getSharedPreferences(Constants.CHAT_PREFS,MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(Constants.CHAT_USERNAME, mUsername);
                                edit.apply();
                                loginButton.setVisibility(View.INVISIBLE);
                                //Intent intent = new Intent("me.kevingleason.pubnubchat.MainActivity");
                                Intent intent = new Intent(null, ChannelActivity.class);
                                startActivity(intent);
                                //finish();

                            }
                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "name");
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }


        });
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        // If already logged in show the home view
        if (accessToken != null) {//<- IMPORTANT
            Intent intent = new Intent(null, MainActivity.class);
            startActivity(intent);
            finish();//<- IMPORTANT
        }
        /*SharedPreferences sp = getSharedPreferences(Constants.CHAT_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.CHAT_USERNAME, mUsername);
        edit.apply();

        Intent intent = new Intent(null, MainActivity.class);
        startActivity(intent);
*/


        //mUsername = (EditText) "arjuny";
       // mUsername = (EditText) findViewById(R.id.login_username);
/*
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String lastUsername = extras.getString("oldUsername", "");
            mUsername = lastUsername;
        }
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Takes the username from the EditText, check its validity and saves it if valid.
     *   Then, redirects to the MainActivity.
     * param view Button clicked to trigger call to joinChat
     */
    /*public void joinChat(View view){
        String username = mUsername;//.getText().toString();
        if (!validUsername(username))
            return;

        SharedPreferences sp = getSharedPreferences(Constants.CHAT_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.CHAT_USERNAME, username);
        edit.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    } */

    /**
     * Optional function to specify what a username in your chat app can look like.
     * @param username The name entered by a user.
     * @return
     */
    /*private boolean validUsername(String username) {
        if (username.length() == 0) {
           // mUsername.setError("Username cannot be empty.");
            return false;
        }
        if (username.length() > 16) {
           // mUsername.setError("Username too long.");
            return false;
        }
        return true;
    }*/
}
