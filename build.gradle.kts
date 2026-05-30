// build.gradle.kts (Project: Calibre)

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    id("com.google.protobuf") version "0.9.4" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

// Version catalog (centralized dependency versions)
val compileSdkVersion = 34
val minSdkVersion = 26
val targetSdkVersion = 34

val kotlinVersion = "1.9.20"
val androidXVersion = "1.6.1"
val composeVersion = "1.5.4"
val roomVersion = "2.6.0"
val hiltVersion = "2.48"
val workVersion = "2.9.0"
val okhttpVersion = "4.11.0"
val retrofitVersion = "2.10.0"

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    detekt {
        config.setFrom("$rootDir/detekt.yml")
        buildUponDefaultConfig = true
        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
