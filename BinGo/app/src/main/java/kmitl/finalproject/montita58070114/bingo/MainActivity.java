package kmitl.finalproject.montita58070114.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private LoginButton btnLogin;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setReadPermissions(Arrays.asList("user_photos", "email", "public_profile"));

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                goBingoGame();


//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,last_name,link,email,picture");
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
//                        try {
//                            String str_email = jsonObject.getString("email");
//                            Toast.makeText(MainActivity.this, str_email, Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.i("user", jsonObject.toString());
//
//
//                    }
//                });
//                request.setParameters(parameters);
//                request.executeAsync();


            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Cancel Login ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(MainActivity.this, "Error " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goBingoGame() {
        Intent intent = new Intent(this, BingoActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}