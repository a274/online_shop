package com.online_shop.registration;

import android.os.AsyncTask;
import android.util.Log;
import com.online_shop.core.App;
import com.online_shop.core.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication extends AsyncTask<String, String, String> {
    private final String LOG_TAG = "Authentication";
    public int resp;

    @Override
    protected String doInBackground(String... par) {

        UserService userService = App.getInstance().getUserService();

        Call<Integer> userCall = userService
                .register(par[0], par[1], par[2], par[3], par[4], par[5]);

        userCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    resp = response.body();
                    Log.v(LOG_TAG, "response " + response.body());

                } else {
                    //answer.setHint(R.string.reg_error);
                    resp = -1;
                    Log.e(LOG_TAG, "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(LOG_TAG, "failure " + t);
            }
        });
        return null;
    }

}
