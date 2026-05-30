# Keep all public classes and methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Room database
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao interface *
-keep @androidx.room.Database class *
-keepclasseswithmembers class * {
    @androidx.room.* <fields>;
}

# Hilt
-keepclasseswithmembernames class * {
    @javax.inject.* <fields>;
    @javax.inject.* <methods>;
}

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**

# Gson
-keepattributes Signature
-keep class com.google.gson.stream.** { *; }
-keepclassmembernames class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Readium
-keep class org.readium.r2.** { *; }
-dontwarn org.readium.r2.**

# iText
-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**

# Keep all permission-related classes (important!)
-keep class android.content.pm.** { *; }
-keep public class android.Manifest$permission { *; }

# Debug symbols
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
