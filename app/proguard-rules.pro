#Google混淆文档：https://developer.android.google.cn/studio/build/shrink-code#keep-code
#ProGuard混淆规则文档：https://www.guardsquare.com/manual/configuration/usage

# --------------------------------------------------------------  通用混淆规则  ----------------------------------------------------------------------------------------

#指定执行优化的次数，默认1次。多次可以更进一步优化代码，如果当前优化没有改进则优化结束
-optimizationpasses 5

# 包含有类名->混淆后类名的映射关系
-verbose

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

#不忽略包可见的库类成员。默认解析会跳过，只解析public类型
-dontskipnonpubliclibraryclassmembers

#将相同的混淆名称分配给相同的类成员。不如多个构造方法混淆后名称相同
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号，用于堆栈轨迹输出行号，便于定位问题
-keepattributes SourceFile,LineNumberTable

# 指定不混淆注解，内部类，泛型，匿名内部类
-keepattributes *Annotation*,InnerClasses,Signature,EnclosingMethod

# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#保留所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保留WebView不被混淆的配置
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
-keepattributes JavascriptInterface

# 混淆编译后默认生成的文件路径：./build/outputs/mapping/{release或debug}/
# mapping.txt : 混淆前后名称对照表，可以反解输出日志。可以使用-printmapping指定输出路径
# configuration ： 输出叠加后的混淆规则。可以使用-printconfiguration指定输出路径
# resources.txt : 输出被移除的资源文件，默认使用安全模式，可以手动检查未被删除并没有使用的文件
# seeds.txt : 输出没有被混淆的类和方法。可以使用-printseeds指定输出路径
# usage.txt ： 输出被移除的代码。可以使用-printusage指定输出路径

# 将mapping混淆对照表指定到app目录下
-printmapping ./mapping.txt

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.fragment.app

# androidX相关的混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**{*;}
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

# 指定不打印测试代码中的注释
-dontnote junit.framework.**
-dontnote junit.runner.**
#指定对测试代码相关问题不发出警告
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

# ---------------------------------------------------------------------- 常见第三方库的混淆规则 ---------------------------------------------------------------------

#----------devcommon----------
## common
##### EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# And if you use AsyncExecutor:
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

##### Retrofit2
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

##### okhttp4.9.0

##### Rxjava3，RxAndroid
-dontwarn java.util.concurrent.Flow*

##### Gson
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

##### bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#####
-keep class com.stl.common.utils.BLActivityCompatOUtils
-keep class com.stl.common.utils.BLActivityLifecycleUtils
-keep class android.app.ActivityThread
-keep class com.stl.common.utils.BLUriUtils

#ui
-keep class com.stl.common.ui.toast.STLToastUtils
-keep class android.app.AppOpsManager

#permission
-keep class com.stl.common.permission.AOPRequestPermission

#xutils
-keepattributes Signature,*Annotation*
-keep public class org.xutils.** {
    public protected *;
}
-keep public interface org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.** {
    public protected *;
}
-keepclassmembers @org.xutils.db.annotation.* class * {*;}
-keepclassmembers @org.xutils.http.annotation.* class * {*;}
-keepclassmembers class * {
    @org.xutils.view.annotation.Event <methods>;
}

#----------STLAnySDK----------
## 微信登陆与分享
-keep class com.tencent.mm.opensdk.** {
    *;
}
-keep class com.tencent.wxop.** {
    *;
}
-keep class com.tencent.mm.sdk.** {
    *;
}

#google登陆
#Facebook登陆，分享
#Twitter登陆，分享
#领英登陆，分享
#Line分享

####
-keep class com.stl.any.mgr.STLAnyAppManager
-keep class com.stl.any.app.STLAnyAppBase{*;}
-keep class * extends com.stl.any.app.STLAnyAppBase{*;}
-keep class com.stl.any.STLAnySDK
-keep class com.stl.any.base.STLSdkBase
-keep class * extends com.stl.any.base.STLSdkBase
-keep class com.stl.any.bean.STLAnyUserInfo{*;}
-keep class com.stl.any.param.STLSdkParam{*;}
-keep class * extends com.stl.any.param.STLSdkParam{*;}
-keep class com.stl.any.param.SdkParamWrapper{*;}
-keep class com.stl.any.param.SdkParamItem{*;}
-keep class com.stl.any.token.CheckTokenReturnModel{*;}
-keep class com.stl.any.token.UserInfoParams{*;}
-keep class com.stl.sdks.wechat.WeChatLoginExtras{*;}

#----------STLAnalysis----------
##### Talkingdata
-dontwarn com.tendcloud.tenddata.**
-keep class com.tendcloud.** {*;}
-keep public class com.tendcloud.tenddata.** { public protected *;}
-keepclassmembers class com.tendcloud.tenddata.**{
public void *(***);
}
-keep class com.talkingdata.sdk.TalkingDataSDK {public *;}
-keep class com.apptalkingdata.** {*;}

##### bugly (devcommon中已经添加)

##### firebase

#####
-keep class com.stl.lib_stlanalysis.analysis.AnalysisFactory
-keep class * implements com.stl.lib_stlanalysis.analysis.IAnalysis
-keep class com.stl.lib_stlanalysis.crash.CrashReportFactory
-keep class * implements com.stl.lib_stlanalysis.crash.ICrashReport

#----------pushlib----------
##### 极光推送
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}

#华为
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

#小米
-dontwarn com.xiaomi.push.**
-keep class com.xiaomi.push.** { *; }

#OPPO
-dontwarn com.coloros.mcsdk.**
-keep class com.coloros.mcsdk.** { *; }

-dontwarn com.heytap.**
-keep class com.heytap.** { *; }

-dontwarn com.mcs.**
-keep class com.mcs.** { *; }

#vivo
-dontwarn com.vivo.push.**
-keep class com.vivo.push.**{*; }
-keep class com.vivo.vms.**{*; }

#魅族
-dontwarn com.meizu.cloud.**
-keep class com.meizu.cloud.** { *; }

#FCM 无

##### MobPush
-keep class com.mob.**{*;}
-dontwarn com.mob.**

-keep class android.os.SystemProperties
-dontwarn android.os.SystemProperties
-keep class com.huawei.**{*;}
-keep class com.meizu.**{*;}
-keep class com.xiaomi.**{*;}

-dontwarn com.huawei.**
-dontwarn com.meizu.**
-dontwarn com.xiaomi.**

#####
-keep class com.stl.nimpush.StlFcmTokenService
-keep class cn.jpush.android.service.PluginFCMMessagingService
-keep class com.stl.nimpush.StlMiPushMessageReceiver
-keep class cn.jpush.android.service.PluginXiaomiPlatformsReceiver
-keep class com.mob.pushsdk.plugins.xiaomi.PushXiaoMiRevicer
-keep class com.stl.nimpush.StlHuaweiPushMessageService
-keep class cn.jpush.android.service.PluginHuaweiPlatformsService
-keep class com.mob.pushsdk.plugins.huawei.HuaweiPushService
-keep class com.stl.nimpush.StlMzPushMessageReceiver
-keep class cn.jpush.android.service.PluginMeizuPlatformsReceiver
-keep class com.mob.pushsdk.plugins.meizu.PushMeiZuRevicer
-keep class * extends com.stl.push.interfaces.PushAbstract{*;}
-keep class * implements com.stl.push.interfaces.OsPush{*;}
-keep class com.stl.push.entity**{*;}
-keep class com.stl.push.constant.StlPushConstant{*;}
-keep class com.stl.push.StlOpenClickActivity{*;}
-keep class com.stl.push.helper.PushMessageReceiverHelper{*;}
-keep class com.stl.push.helper.PushActionHelper{*;}
-keep enum com.stl.push.constant.StlPushType{*;}
-keep class com.stl.jpush.StlOpenMessage{*;}


#----------Glide4.12.0----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

#-------------------------------------------------------------------------------------------------------------------------------------------------------------------

#----------支付功能----------
# 微信支付（STLAnySDK中已经配置过）
# 支付宝支付
# google支付

# Room2.3.0
# 阿里一键登陆2.10.1。混淆keep规则已经写入aar包，不再需要单独配置。
# fastjson

# ImmersionBar沉浸式状态栏
-keep class com.gyf.immersionbar.* {*;}
-dontwarn com.gyf.immersionbar.**

# Lottie动画库
-keep class com.airbnb.lottie.samples.** { *; }

# FileDownloader
-dontwarn okhttp3.*
-dontwarn okio.**

# ijkplayer
-keep class tv.danmaku.ijk.media.player.** {*;}
-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer{*;}
-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi{*;}

# -------------------------------------------------------------------------- 项目中混淆规则 ------------------------------------------------------------------------

