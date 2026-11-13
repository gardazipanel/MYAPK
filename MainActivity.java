package com.myapp.web;
import android.app.*;import android.content.*;import android.content.pm.PackageManager;import android.graphics.Color;import android.media.MediaPlayer;import android.net.Uri;import android.os.Bundle;import android.view.*;import android.webkit.*;import android.widget.*;import android.graphics.BitmapFactory;
import java.util.*;
public class MainActivity extends Activity{
 WebView w;ValueCallback<Uri[]> fc;MediaPlayer mp;FrameLayout root;
 static final boolean EXT_IN=true;static final boolean SPLASH=true;static final String BG="#4f46e5",NC="#ffffff",POS="center",NAME="My App";static final boolean SHOWN=true;
 @Override protected void onCreate(Bundle b){super.onCreate(b);
  root=new FrameLayout(this);w=new WebView(this);root.addView(w);setContentView(root);
  WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);s.setGeolocationEnabled(true);s.setMediaPlaybackRequiresUserGesture(false);
  w.setWebViewClient(new WebViewClient(){public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest r){String u=r.getUrl().toString();
   if(u.startsWith("file:")||EXT_IN)return false;try{startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(u)));}catch(Exception e){}return true;}});
  w.setWebChromeClient(new WebChromeClient(){
   public void onPermissionRequest(PermissionRequest r){r.grant(r.getResources());}
   public void onGeolocationPermissionsShowPrompt(String o,GeolocationPermissions.Callback c){c.invoke(o,true,true);}
   public boolean onShowFileChooser(WebView v,ValueCallback<Uri[]> cb,FileChooserParams p){fc=cb;try{startActivityForResult(p.createIntent(),7);}catch(Exception e){fc=null;return false;}return true;}});
  w.loadUrl("https://example.com");
  askOnce();if(SPLASH)splash();}
 void askOnce(){SharedPreferences sp=getSharedPreferences("p",0);if(sp.getBoolean("asked",false))return;sp.edit().putBoolean("asked",true).apply();
  List<String> l=new ArrayList<>();l.add("android.permission.CAMERA");l.add("android.permission.RECORD_AUDIO");l.add("android.permission.ACCESS_FINE_LOCATION");l.add("android.permission.ACCESS_COARSE_LOCATION");l.add("android.permission.READ_EXTERNAL_STORAGE");
  if(android.os.Build.VERSION.SDK_INT>=23&&!l.isEmpty())requestPermissions(l.toArray(new String[0]),1);}
 void splash(){LinearLayout v=new LinearLayout(this);v.setOrientation(LinearLayout.VERTICAL);v.setBackgroundColor(Color.parseColor(BG));v.setGravity(Gravity.CENTER);
  try{ImageView i=new ImageView(this);i.setImageBitmap(BitmapFactory.decodeStream(getAssets().open("splash_logo.png")));
   LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(300,300);p.gravity=POS.equals("left")?Gravity.LEFT:POS.equals("right")?Gravity.RIGHT:Gravity.CENTER_HORIZONTAL;v.addView(i,p);}catch(Exception e){}
  if(SHOWN){TextView t=new TextView(this);t.setText(NAME);t.setTextSize(24);t.setTextColor(Color.parseColor(NC));t.setGravity(Gravity.CENTER);v.addView(t);}
  root.addView(v,new FrameLayout.LayoutParams(-1,-1));
  try{android.content.res.AssetFileDescriptor a=getAssets().openFd("splash_music.mp3");mp=new MediaPlayer();mp.setDataSource(a.getFileDescriptor(),a.getStartOffset(),a.getLength());mp.prepare();mp.start();}catch(Exception e){}
  try{getAssets().openFd("splash_video.mp4");VideoView vv=new VideoView(this);root.addView(vv,new FrameLayout.LayoutParams(-1,-1));vv.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw/splash_video"));vv.start();}catch(Exception e){}
  v.postDelayed(()->{root.removeView(v);if(mp!=null)mp.release();},2500);}
 @Override protected void onActivityResult(int q,int r,Intent d){if(q==7&&fc!=null){fc.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(r,d));fc=null;}}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}