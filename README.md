
就是转自[高仿微信可拖拽返回][1],为了应付需求变动问题，先拉下来，之后好方便维护修改。
（支持手指拖动返回，动画逐渐缩小变淡会回到原来图片的位置）

## 集成 ##
Add it in your root build.gradle at the end of repositories:
    

    allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

Add the dependency

    dependencies {
        implementation 'com.github.LvBing1995:ImageBrower:1.1.0'
    }

## 初始化API简介 ##

 |name|description|
|:---:|:---:|
| *setLoader | *图片地址加载的实现者 |
| setTranslucentStatus | 当没有使用透明状态栏，传入状态栏的高度 |
| setErrorImageRes | 图片加载失败时显示的样子 |
| setOnPictureLongPressListener | 长按回调 |
| setIndexProvider | 自定义页码UI |
| setLoadingUIProvider | 自定义加载UI |
| setOnStateChangedListener | 开始显示和退出显示时的回调 |
## 初始化配置 ##

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


## 使用 ##

    iwHelper.show(imageView,mappingViews,urlList);//imageview:选中展示的图片,mappingViews:图片SparseArray<ImageView>,urlList:图片链接List<Uri>

  [1]: https://github.com/iielse/ImageWatcher
