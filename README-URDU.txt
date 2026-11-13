APK BANANE KA TAREEQA (GitHub, free)

1. github.com par account banayein -> New repository -> naam likhein -> Create.
2. "uploading an existing file" par click karein, is ZIP ko EXTRACT karke
   uski SAARI files aur folders (.github folder samet) upload karein -> Commit changes.
   .github folder lazmi upload hona chahiye, warna build nahi chalega.
3. Upar "Actions" tab -> "Build APK" -> "Run workflow". 2-4 minute intezar.
4. Complete hone par run kholein -> neeche Artifacts mein "app-apk" download karein
   -> ZIP kholein -> app-debug.apk phone mein install karein.

APNI WEBSITE LAGANE KE LIYE:
 app/src/main/java/com/myapp/web/MainActivity.java mein
 w.loadUrl("https://example.com") wali line mein apna link likhein.
APP KA NAAM: app/src/main/AndroidManifest.xml mein android:label="My App" badlein.
ICON: app/src/main/res/mipmap-xxxhdpi/ic_launcher.png badlein (192x192 PNG).
HTML APP: loadUrl wali line ko
 w.loadUrl("file:///android_asset/www/index.html") karein aur apni files
 app/src/main/assets/www/ mein rakhein.
Ya phir APK Maker website se apni details ke sath naya project ZIP banayein.
