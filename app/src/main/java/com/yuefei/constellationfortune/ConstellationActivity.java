package com.yuefei.constellationfortune;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstellationActivity extends AppCompatActivity {

    private static final String TAG = "ConstellationActivity";

    CollapsingToolbarLayout collapsingToolbarLayout;

    CircleImageView imageView;
    TextView headConstellationName;

    Calendar calendar;
    TextView day;
    TextView month;

    TextView dayFortune;
    TextView weekFortune;
    TextView monthFortune;
    TextView yearFortune;

    LinearLayout midLayout;
    TextView color;
    TextView number;
    TextView qFriend;

    TextView tv_index;
    LinearLayout indexLayout;
    TextView all;
    TextView love;
    TextView work;
    TextView money;
    TextView health;
    TextView summary;
    VerticalBar all_bar;
    VerticalBar love_bar;
    VerticalBar work_bar;
    VerticalBar money_bar;
    VerticalBar health_bar;

    TextView tv_summary;
    CardView card_view;
    CardView cardView1;  //job
    CardView cardView2;  //love
    CardView cardView3;  //money
    CardView cardView4;  //work
    CardView cardView5;  //health
    TextView jobTv;
    TextView loveTv;
    TextView moneyTv;
    TextView workTv;
    TextView healthTv;
    //本界面展示的星座
    String constellationName = MyDialogFragment.constellation1.getName();
    int constellationImg = MyDialogFragment.constellation1.getImage();

    String type = "today";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);

        initView();
        initData();
        SharedPreferences sharedPreferences = getSharedPreferences("ConstellationActivity", MODE_PRIVATE);
        boolean isFirstLauncher = sharedPreferences.getBoolean("isFirst", true);
        if (isFirstLauncher) {
            sharedPreferences.edit().putBoolean("isFirst", false).apply();
            headConstellationName.performClick();
        }

    }

    private void initView() {
        //设置toolbar
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#5F8EFA"));

        imageView = findViewById(R.id.constellation_head_image);
        headConstellationName = findViewById(R.id.head_constellation_name);

        day = findViewById(R.id.day);
        month = findViewById(R.id.month);

        dayFortune = findViewById(R.id.day_fortune);
        weekFortune = findViewById(R.id.week_fortune);
        monthFortune = findViewById(R.id.month_fortune);
        yearFortune = findViewById(R.id.year_fortune);

        midLayout = findViewById(R.id.mid_layout);
        color = findViewById(R.id.color);
        number = findViewById(R.id.number);
        qFriend = findViewById(R.id.QFriend);
        tv_index = findViewById(R.id.tv_index);
        indexLayout = findViewById(R.id.index_layout);
        all = findViewById(R.id.all);
        love = findViewById(R.id.love);
        work = findViewById(R.id.work);
        money = findViewById(R.id.money);
        health = findViewById(R.id.health);
        summary = findViewById(R.id.summary);
        all_bar = findViewById(R.id.all_bar);
        love_bar = findViewById(R.id.love_bar);
        work_bar = findViewById(R.id.work_bar);
        money_bar = findViewById(R.id.money_bar);
        health_bar = findViewById(R.id.health_bar);

        tv_summary = findViewById(R.id.tv_summary);
        card_view = findViewById(R.id.card_view);
        cardView1 = findViewById(R.id.card_view1);
        cardView2 = findViewById(R.id.card_view2);
        cardView3 = findViewById(R.id.card_view3);
        cardView4 = findViewById(R.id.card_view4);
        cardView5 = findViewById(R.id.card_view5);
        jobTv = findViewById(R.id.job_tv);
        loveTv = findViewById(R.id.love_tv);
        moneyTv = findViewById(R.id.money_tv);
        workTv = findViewById(R.id.work_tv);
        healthTv = findViewById(R.id.health_tv);

        //默认选中 日运
        dayFortune.setTextColor(Color.RED);
        dayFortune.setTextSize(20);
        //指数柱形图用不同颜色展示
        all_bar.setPaint_color(Color.CYAN);
        love_bar.setPaint_color(Color.YELLOW);
        work_bar.setPaint_color(Color.BLUE);
        money_bar.setPaint_color(Color.RED);
        health_bar.setPaint_color(Color.GREEN);

        headConstellationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(), "constellation_select");
                myDialogFragment.setOnDialogFragmentClickListener(new MyDialogFragment.OnDialogFragmentClickListener() {
                    @Override
                    public void onDialogFragmentClickListener(String name, int image) {
                        constellationName = name;
                        constellationImg = image;
                        collapsingToolbarLayout.setTitle(name);
                        initFortuneView();
                        dayFortune.setTextColor(Color.RED);
                        dayFortune.setTextSize(20);
                        hideOtherViews();
                        showDayFortuneView();
                        type = "today";
                        SharedPreferences sharedPreferences = getSharedPreferences("ConstellationActivity", MODE_PRIVATE);
                        sharedPreferences.edit().putString("ConstellationName", name)
                                .putInt("ConstellationImage", image)
                                .apply();
                        initData();
                    }
                });
            }
        });

        weekFortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFortuneView();
                weekFortune.setTextColor(Color.RED);
                weekFortune.setTextSize(20);
                type = "week";
                hideOtherViews();
                showWeekCardView();
                initData();
            }
        });
        dayFortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFortuneView();
                dayFortune.setTextColor(Color.RED);
                dayFortune.setTextSize(20);
                hideOtherViews();
                showDayFortuneView();
                type = "today";
                initData();
            }
        });
        monthFortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFortuneView();
                monthFortune.setTextColor(Color.RED);
                monthFortune.setTextSize(20);
                hideOtherViews();
                showMonthFortuneView();
                type = "month";
                initData();
            }
        });
        yearFortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFortuneView();
                yearFortune.setTextColor(Color.RED);
                yearFortune.setTextSize(20);
                hideOtherViews();
                showYearFortuneView();
                type = "year";
                getYearFortune();
            }
        });
    }

    private void getYearFortune() {
        calendar = Calendar.getInstance();
        day.setText(calendar.get(Calendar.YEAR) + "年");
        month.setText("");

        RetrofitManager.getService().getYearFortune(constellationName, type, RetrofitManager.KEY).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONObject mima = jsonObject.getJSONObject("mima");
                        String s = (String) mima.getJSONArray("text").get(0);
                        summary.setText(s);
                        loveTv.setText(jsonObject.getJSONArray("love").get(0).toString());
                        moneyTv.setText(jsonObject.getJSONArray("finance").get(0).toString());
                        workTv.setText(jsonObject.getJSONArray("career").get(0).toString());
                        healthTv.setText(jsonObject.getJSONArray("health").get(0).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "数据显示异常！", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "请求网络数据异常！", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hideOtherViews() {
        midLayout.setVisibility(View.GONE);
        tv_index.setVisibility(View.GONE);
        indexLayout.setVisibility(View.GONE);
        tv_summary.setVisibility(View.GONE);
        card_view.setVisibility(View.GONE);

        cardView1.setVisibility(View.GONE);
        cardView2.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        cardView4.setVisibility(View.GONE);
        cardView5.setVisibility(View.GONE);
    }

    private void showYearFortuneView() {
        showMonthFortuneView();
    }

    private void showMonthFortuneView() {
        card_view.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.VISIBLE);
        cardView3.setVisibility(View.VISIBLE);
        cardView4.setVisibility(View.VISIBLE);
        cardView5.setVisibility(View.VISIBLE);
    }

    private void showDayFortuneView() {
        midLayout.setVisibility(View.VISIBLE);
        tv_index.setVisibility(View.VISIBLE);
        indexLayout.setVisibility(View.VISIBLE);
        tv_summary.setVisibility(View.VISIBLE);
        card_view.setVisibility(View.VISIBLE);
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ConstellationActivity", MODE_PRIVATE);
        constellationName = sharedPreferences.getString("ConstellationName", MyDialogFragment.constellation1.getName());
        constellationImg = sharedPreferences.getInt("ConstellationImage", MyDialogFragment.constellation1.getImage());
        headConstellationName.setText(constellationName);
        collapsingToolbarLayout.setTitle(constellationName);
        Glide.with(this).load(constellationImg).into(imageView);

        calendar = Calendar.getInstance();
        switch (type) {
            case "today":
                day.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                month.setText(" / " + (calendar.get(Calendar.MONTH) + 1) + "月");
                break;
            case "week":
                day.setText(calendar.get(Calendar.YEAR) + "年第" + calendar.get(Calendar.WEEK_OF_YEAR) + "周");
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                String start = (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                String end = (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                day.setText(start + "-" + end);
                month.setText("");
                break;
            case "month":
                day.setText((calendar.get(Calendar.MONTH) + 1) + "月");
                month.setText("");
                break;
        }
        //获取运
        RetrofitManager.getService().getConstellation(constellationName, type, RetrofitManager.KEY).enqueue(new Callback<Constellation>() {
            @Override
            public void onResponse(Call<Constellation> call, Response<Constellation> response) {
                if (response.body() != null) {
                    switch (type) {
                        case "today":
                            try {
                                Constellation constellation = response.body();
                                color.setText(constellation.getColor());
                                number.setText(constellation.getNumber());
                                qFriend.setText(constellation.getQFriend());

                                all.setText(constellation.getAll() + "%");
                                all_bar.setHeight(Integer.parseInt(constellation.getAll()));
                                love.setText(constellation.getLove() + "%");
                                love_bar.setHeight(Integer.parseInt(constellation.getLove()));
                                work.setText(constellation.getWork() + "%");
                                work_bar.setHeight(Integer.parseInt(constellation.getWork()));
                                money.setText(constellation.getMoney() + "%");
                                money_bar.setHeight(Integer.parseInt(constellation.getMoney()));
                                health.setText(constellation.getHealth() + "%");
                                health_bar.setHeight(Integer.parseInt(constellation.getHealth()));

                                summary.setText(constellation.getSummary());
                                all_bar.invalidate();
                                love_bar.invalidate();
                                work_bar.invalidate();
                                money_bar.invalidate();
                                health_bar.invalidate();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "数据显示异常！", Toast.LENGTH_LONG).show();
                            }
                            break;
                        case "week": {
                            Constellation constellation = response.body();
                            jobTv.setText(constellation.getJob());
                            loveTv.setText(constellation.getLove());
                            moneyTv.setText(constellation.getMoney());
                            workTv.setText(constellation.getWork());
                            break;
                        }
                        case "month": {
                            Constellation constellation = response.body();
                            summary.setText(constellation.getAll());
                            loveTv.setText(constellation.getLove());
                            moneyTv.setText(constellation.getMoney());
                            workTv.setText(constellation.getWork());
                            healthTv.setText(constellation.getHealth());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Constellation> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "请求网络数据异常！", Toast.LENGTH_LONG).show();
            }
        });
    }

    //初始化 日运，周运，月运，年运的textview
    public void initFortuneView() {
        dayFortune.setTextSize(17);
        weekFortune.setTextSize(17);
        monthFortune.setTextSize(17);
        yearFortune.setTextSize(17);
        dayFortune.setTextColor(Color.BLACK);
        weekFortune.setTextColor(Color.BLACK);
        monthFortune.setTextColor(Color.BLACK);
        yearFortune.setTextColor(Color.BLACK);
    }

    public void showWeekCardView() {
        cardView1.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.VISIBLE);
        cardView3.setVisibility(View.VISIBLE);
        cardView4.setVisibility(View.VISIBLE);
        cardView5.setVisibility(View.GONE);
    }
}