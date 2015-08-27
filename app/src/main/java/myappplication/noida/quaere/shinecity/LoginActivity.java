package myappplication.noida.quaere.shinecity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by intex on 8/21/2015.
 */
public class LoginActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    //Declaration of widgets
    EditText id_editText, pswd_editText;
    CheckBox remember_me;
    Button login_button;
    ProgressDialog dialogPd;
    String email, password;
    String login_url = "";
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    CheckBox checkBox;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_activity);
        id_editText = (EditText) findViewById(R.id.editTextId);
        pswd_editText = (EditText) findViewById(R.id.editTextPswd);
        login_button = (Button) findViewById(R.id.login_button);
        checkBox = (CheckBox) findViewById(R.id.remember_me_check);

        // dialogPd.cancel();
        //calling method to save the data
        sharedPrefernces();
        Log.v("shrdprfs before click", "Shared prefrences");
        //when login button will be clicked
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = id_editText.getText().toString().trim();
                password = pswd_editText.getText().toString().trim();
                System.out.println("Entered email :" + email + " Entered password :" + password);

                Log.v("Inside on click", "login btn click");
                if (view == login_button) {
                    //null validation
                    if (email.equals("") && password.equals("")) {
                        Log.i("k1", "null");
                        Toast.makeText(getApplicationContext(),
                                "please enter user id and psw",
                                Toast.LENGTH_SHORT).show();
                        id_editText.requestFocus();
                    } else if (email.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "please enter user id", Toast.LENGTH_SHORT)
                                .show();
                        id_editText.requestFocus();
                    } else if (password.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "please enter psw", Toast.LENGTH_SHORT).show();
                        pswd_editText.requestFocus();
                    }
                    //email pattern validation
                    else if (email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@\"\n" +
                            "            + \"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        Toast.makeText(getApplicationContext(),
                                "please enter a valid id", Toast.LENGTH_SHORT)
                                .show();
                        id_editText.requestFocus();
                    } else if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(),
                                "please enter atleast 6 digits or characters",
                                Toast.LENGTH_SHORT).show();
                        pswd_editText.requestFocus();
                    } else {


                        editor = sharedpreferences.edit();
                        editor.putString("useremail", email);
                        editor.putString("password", password);
                        editor.putString("logged", "logged");
                        editor.commit();
                        //login();
                        // sendPostRequest(email, password);
                        Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                }

            }
        });


    }


    // using shared prefernce to remember the login detail
    private void sharedPrefernces() {
        Log.v("Inside shredprefs()", "Shared prefrences");
        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        Log.v("is not checked", "Shared prefrences");
      //  if (checkBox.isChecked()) {
             if (sharedpreferences.getString("logged", "").toString().equals("logged")) {
            Log.v("inside if checked", "Shared prefrences");
            Intent i = new Intent(
                    LoginActivity.this,
                    UserProfileActivity.class);
            i.putExtra("USEREMAIL", sharedpreferences
                    .getString("useremail", "").toString());
            i.putExtra("PASSWORD", sharedpreferences.getString("password", "")
                    .toString());
            // i.putExtra("USERNAME",sharedpreferences.getString("username",
            // "").toString());
            i.putExtra("CHECK", true);
            Log.v("ifcheckd startactivity", "Shared prefrences");
            startActivity(i);
        }
        // }

       /* public void login(){

            dialogPd = new ProgressDialog(LoginActivity.this);
            dialogPd.setTitle("In Progress");
            dialogPd.setMessage("Please Wait");
            dialogPd.show();


        }*/
    }
}
