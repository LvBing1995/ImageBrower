package com.lvbing.imagebrower;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ielse.imagewatcher.ImageWatcher;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.lvbing.imagebrower.widget.CustomLoadingUIProvider2;
import com.lvbing.imagebrower.widget.DecorationLayout;
import com.lvbing.imagebrower.widget.MessagePicturesLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MessagePicturesLayout messagePicturesLayout;//仿微信朋友圈的图片展示形式
    private ImageWatcherHelper iwHelper;
    private DecorationLayout layDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*boolean isTranslucentStatus = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            isTranslucentStatus = true;
        }*/
        layDecoration = new DecorationLayout(this);
        messagePicturesLayout = findViewById(R.id.messageId);
        iwHelper = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
               // .setTranslucentStatus(!isTranslucentStatus ? Utils.calcStatusBarHeight(this) : 0) // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
                .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
                .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
                    @Override
                    public void onPictureLongPress(ImageView v, Uri uri, int pos) {
                        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
                        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
                    @Override
                    public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position, Uri uri, float animatedValue, int actionTag) {
                        Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri + "][" + animatedValue + "][" + actionTag + "]");
                    }

                    @Override
                    public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
                        if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
                            Toast.makeText(getApplicationContext(), "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
                            Toast.makeText(getApplicationContext(), "退出了查看大图", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setOtherView(layDecoration)//自定义覆盖一个viewpagerItem页面
                .addOnPageChangeListener(layDecoration)
                .setLoadingUIProvider(new CustomLoadingUIProvider2());//自定义loading
        messagePicturesLayout.setCallback(new MessagePicturesLayout.Callback() {
            @Override
            public void onThumbPictureClick(ImageView imageView, SparseArray<ImageView> mappingViews, List<Uri> urlList) {
                iwHelper.show(imageView,mappingViews,urlList);
            }
        });
        initData();
    }

    public void initData(){
       List<String> list = new ArrayList<>();
       //list.add(resourceIdToUri(this,R.drawable.ic_launcher_round));
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406246&di=878bb696a9eee9b92ec895c5a118be48&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F158%2Fw1000h1558%2F20180514%2Ffba1-hapkuvk5849711.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557206406244&di=954a93de3db7bd21a9c9b6eadf9a25e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc004e90edb36638ede23433f8be341b7deb6b82eb1c73-8T7AUd_fw658");
        messagePicturesLayout.set(convert(list));
    }
    private List<Uri> convert(List<String> data) {
        List<Uri> list = new ArrayList<>();
        for (String d : data) list.add(Uri.parse(d));
        return list;
    }
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
