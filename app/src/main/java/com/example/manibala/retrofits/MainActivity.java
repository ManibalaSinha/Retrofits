package com.example.manibala.retrofits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manibala.retrofits.pojo.MultipleResources;
import com.example.manibala.retrofits.pojo.User;
import com.example.manibala.retrofits.pojo.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface= APIClient.getClient().create(APIInterface.class);

        //call each of API endpoints defined in Interface class and display each of fields
        //Get List resources
        Call<MultipleResources> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResources>() {
            @Override
            public void onResponse(Call<MultipleResources> call, Response<MultipleResources> response) {
                Log.d("TAG", response.code()+"");
                String displayResponse = "";
                MultipleResources resources = response.body();
                Integer text = resources.page;
                Integer total = resources.total;
                Integer totalPages = resources.totalPages;
                List<MultipleResources.Datum> datumList = resources.data;
                displayResponse += text+"Page\n" + total + "Total\n" +totalPages + "Total Pages\n";

                for (MultipleResources.Datum datum : datumList){
                    displayResponse += datum.id + ""+ datum.name+ "" + datum.pantoneValue + "" + datum.year + "\n";
                }
                responseText.setText(displayResponse);

            }

            @Override
            public void onFailure(Call<MultipleResources> call, Throwable t) {
                call.cancel();
            }
        });
        // end of Get List resources
        // Create new user
        User user = new User("morpheus", "leader");
        Call<User> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                Toast.makeText(getApplicationContext(), user1.name + "" + user1.job + " " + user1.id + "" + user1.createdAt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });
//end of new user
//get List Users
        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text+"page\n" + total + "total\n"+ totalPages + "totalPages\n", Toast.LENGTH_SHORT).show();

                for(UserList.Datum datum: datumList){
                    Toast.makeText(getApplicationContext(), "id: " + datum.id + "name: " + datum.first_name + " " + datum.last_name
                            + "avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
       //end of List Users
        //POST name and job url encoded
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus", "leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text+"page\n" + total + "total\n"+ totalPages + "totalPages\n", Toast.LENGTH_SHORT).show();

                for(UserList.Datum datum: datumList){
                    Toast.makeText(getApplicationContext(), "id: " + datum.id + "name: " + datum.first_name + " " + datum.last_name
                            + "avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
        //end of List Users
    }
}
