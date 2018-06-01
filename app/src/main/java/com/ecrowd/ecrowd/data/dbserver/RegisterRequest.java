package com.ecrowd.ecrowd.data.dbserver;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
//    private static final String REGISTER_REQUEST_URL = "http://ecrowd.000webhostapp.com/ecrowdserver/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String email, String first_name, String last_name,
                           String password, Response.Listener<String> listener) {
        super(Method.POST, DBServerHelper.REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("username", username);
        params.put("email", email);
        params.put("first_name", first_name);
        params.put("Last_name", last_name);
        params.put("password", password);
        params.put("role", "user");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
