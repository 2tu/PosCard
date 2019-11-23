# Add project specific ProGuard rules here.

-optimizationpasses 6
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-ignorewarnings
-dontwarn

-include rules/basic.pro
-include rules/wcdb.pro
-include rules/bugly.pro


-keep class kotlin.** { *; }
-keep class com.bluelinelabs.conductor.** { *; }
-keep class com.tu.poscard.BuildConfig { *; }
-keep class android.databinding.** { *; }
-keep class com.tu.poscard.data.model.** { *; }
-keep class com.tu.poscard.ui.** { *; }