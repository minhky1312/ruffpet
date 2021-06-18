package com.example.myapplication.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Loaispadapter;
import com.example.myapplication.model.loaisp;
import com.example.myapplication.util.Server;
import com.example.myapplication.util.checkConnect;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView mainrecyclerView;
    NavigationView navigationView;
    ListView mainListview;
    DrawerLayout drawerLayout;
    ArrayList<loaisp> mangloaisp;
    Loaispadapter loaispadapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(checkConnect.haveNetworkConnection(getApplicationContext())) {
            actionbar();
            actionFlipper();
            getLoaispdata();
        }
        else
        {
            checkConnect.showToast(getApplicationContext(),"Không thể kết nối internet!");
        }

    }

    private void getLoaispdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlLoaisanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i=0; i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("idlevel");
                            tenloaisp=jsonObject.getString("levelname");
                            hinhanhloaisp=jsonObject.getString("levelimg");
                            mangloaisp.add(new loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkConnect.showToast(getApplicationContext(),"Lỗi kết nối internet");
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void actionFlipper() {
        ArrayList<String> dogimg = new ArrayList<>();
        dogimg.add("https://www.rd.com/wp-content/uploads/2021/01/GettyImages-532620145.jpg");
        dogimg.add("https://i.pinimg.com/originals/82/02/b5/8202b547c05655cf05a3888c25af5273.jpg");
        dogimg.add("https://i.pinimg.com/474x/da/a5/26/daa5261022ae9b2a38cb9137cb7e24ab.jpg");
        dogimg.add("https://i.pinimg.com/originals/73/ba/db/73badbc7345e8c89fad56e3c83e2da2f.jpg");
        dogimg.add("https://i.pinimg.com/564x/ba/ed/7e/baed7eb3f971bbf3df99db7750317e9c.jpg");
        for (int i=0; i<dogimg.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(dogimg.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void init()
    {
        toolbar= findViewById(R.id.manhinhchinh);
        viewFlipper= findViewById(R.id.mainviewflipper);
        mainrecyclerView=findViewById(R.id.mainrecycler);
        navigationView=findViewById(R.id.mainnavview);
        mainListview=findViewById(R.id.mainnavlistview);
        drawerLayout=findViewById(R.id.drawverlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0,new loaisp(0,"Trang chính","https://sites.google.com/site/mauwebsitescuakhoivinhphan/_/rsrc/1493041243995/home/icontrangchi.png"));
        mangloaisp.add(1,new loaisp(1,"Thú cưng","https://i.pinimg.com/236x/3e/45/d1/3e45d16f31be358f13d88abf0fa44bf8--sticker.jpg"));
        mangloaisp.add(2,new loaisp(2,"Dịch vụ thú cưng","https://i.pinimg.com/originals/9a/01/f6/9a01f64bb9968f8aec81514653fc9ec2.png"));
        mangloaisp.add(3,new loaisp(2,"Tài khoản","https://cdn.iconscout.com/icon/free/png-512/account-profile-avatar-man-circle-round-user-30452.png"));
       loaispadapter = new Loaispadapter(mangloaisp,getApplicationContext());
        mainListview.setAdapter(loaispadapter);
    }

    private void actionbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


}