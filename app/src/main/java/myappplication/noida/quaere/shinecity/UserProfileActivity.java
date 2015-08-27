package myappplication.noida.quaere.shinecity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by intex on 8/21/2015.
 */
public class UserProfileActivity extends Activity {

    String username = "TOWJ146";
   String user_profile_url = "http://demo8.mlmsoftindia.com//ShinePanel.svc/MemberProfile/"+username+"";;
    String res = null;
    Button button;
    String url = "http://demo8.mlmsoftindia.com//ShinePanel.svc/MemberProfile/";

    //String user_profile_url = "http://demo8.mlmsoftindia.com//ShinePanel.svc/MemberProfile/TOWJ146";// + username + "";
    // String url = "https://app.gosprout.it/inspiration_gardens.json";
    TextView username_tv, user_address_tv, user_dob_tv, user_email_tv,bankname_tv,bank_account_tv,banck_branch_tv,ifsc_tv,nomineename_tv,nominee_address_tv,nominee_relation_tv,nominee_mobile_tv;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.userprofile_layout);
        //instantiating the widgets
        username_tv = (TextView) findViewById(R.id.username_tv);
        user_address_tv = (TextView) findViewById(R.id.user_address_tv);
        user_dob_tv = (TextView) findViewById(R.id.user_dob_tv);
        user_email_tv = (TextView) findViewById(R.id.user_email_tv);

        bankname_tv = (TextView) findViewById(R.id.bankname_tv);
        bank_account_tv = (TextView) findViewById(R.id.bank_account_tv);
        banck_branch_tv = (TextView) findViewById(R.id.bank_branch_tv);
        ifsc_tv = (TextView) findViewById(R.id.ifsc_tv);

        nomineename_tv = (TextView) findViewById(R.id.nominee_name_tv);
        nominee_address_tv = (TextView) findViewById(R.id.nominee_address_tv);
        nominee_relation_tv = (TextView) findViewById(R.id.nominee_relation_tv);
        nominee_mobile_tv = (TextView) findViewById(R.id.nominee_mobile_tv);
        button = (Button) findViewById(R.id.userbutton);


        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                connectService();


            }
        });
        //taking username from login activity
        // username = getIntent().getStringExtra("email");
        /*if (username == null) {
            Toast.makeText(getApplicationContext(), "kindly pass exact username", Toast.LENGTH_LONG).show();
        }*/


        // UserProfile profile = new UserProfile();
        // profile.execute(user_profile_url);


    }


    public void connectService() {
        Log.v("url", user_profile_url);
        Log.v("after click", "inside connectService()");

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setBasicAuth("key", "");

        BaseJsonHttpResponseHandler handler = new BaseJsonHttpResponseHandler() {


@Override
public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
        Log.e("Userprofile", "success  body " + rawJsonResponse);
        String response1 = rawJsonResponse;
        try { //getting Json data using Json
        JSONArray jArray = new JSONArray(response1);
        for (int i = 0; i < jArray.length(); i++) {

        JSONObject jObj = jArray.getJSONObject(i);
        //Getting personal details
        String name = jObj.getString("FirstName");
        username_tv.setText(name);
        Log.i("Name", name);

        String Address = jObj.getString("Addres");
        user_address_tv.setText(Address);
        Log.i("Address", Address);

        String DOB = jObj.getString("DOB");
        user_dob_tv.setText(DOB);
        Log.i("DOB", DOB);

        String Email = jObj.getString("EmailId");
        user_email_tv.setText(Email);
        Log.i("Email", Email);

        //Getting bank details
        String BankName = jObj.getString("BankName");
        bankname_tv.setText(BankName);
        Log.i("BankName", BankName);

        String BankAccounNo = jObj.getString("BankAccounNo");
        bank_account_tv.setText(BankAccounNo);
        Log.i("BankAccounNo", BankAccounNo);

        String BankBranchName = jObj.getString("BankBranchName");
        banck_branch_tv.setText(BankBranchName);
        Log.i("BankBranchName", BankBranchName);

        String IFSCCode = jObj.getString("IFSCCode");
        ifsc_tv.setText(IFSCCode);
        Log.i("IFSCCode", IFSCCode);


        //Getting Nominee details
        //Getting bank details
        String NomineeName = jObj.getString("NomineeName");
        nomineename_tv.setText(NomineeName);
        Log.i("NomineeName", NomineeName);

        String NomineeAddress = jObj.getString("NomineeAddress");
        nominee_address_tv.setText(NomineeAddress);
        Log.i("NomineeAddress", NomineeAddress);

        String NomineeRelation = jObj.getString("NomineeRelation");
        nominee_relation_tv.setText(NomineeRelation);
        Log.i("NomineeRelation", NomineeRelation);

        String NonineeMobile = jObj.getString("NonineeMobile");
        nominee_mobile_tv.setText(NonineeMobile);
        Log.i("NonineeMobile", NonineeMobile);


        }

        } catch (JSONException e) {

        }
        }

@Override
public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
        Log.e("new method", "error " + throwable.toString());

        }

@Override
protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
        Log.e("new method", "response  body " + rawJsonData);

        return null;
        }

@Override
public void onPreProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
        super.onPreProcessResponse(instance, response);
        Log.e("new method", "pre progress " + instance.getRequestURI());
        }
        };
        httpClient.get(this, user_profile_url, null, handler);

        }
        }
