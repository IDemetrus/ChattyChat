package com.example.chattychat.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattychat.R;
import com.example.chattychat.adapters.MessageAdapter;
import com.example.chattychat.models.Message;
import com.example.chattychat.repositories.JsonApi;
import com.example.chattychat.repositories.RetrofitClient;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private JsonApi jsonApi;
    private RecyclerView recyclerView;
    private String channelId = "5d8f08e6fc0dc500177650d2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        jsonApi = retrofit.create(JsonApi.class);

        // View
        recyclerView = findViewById(R.id.rw_message_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(jsonApi.getMessages(channelId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Message>>() {
                    @Override
                    public void accept(ArrayList<Message> messages) throws Exception {
                        displayData(messages);
                    }
                })
        );
    }

    private void displayData(ArrayList<Message> messages) {
        MessageAdapter adapter = new MessageAdapter(this, messages);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
