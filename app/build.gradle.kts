plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.developermaheshsofttechltd.diplomahelper"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.developermaheshsofttechltd.diplomahelper"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packaging {

        resources.excludes.add("META-INF/*")

    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("com.google.android.play:app-update-ktx:2.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation("com.anggrayudi:storage:1.5.5")
    implementation("com.airbnb.android:lottie:6.2.0")
//    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    // Lifecycle and Kotlin Coroutines

    implementation("io.ak1:bubbletabbar:1.0.8")
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")

    // Gson and networking
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("org.asynchttpclient:async-http-client:3.0.0")
    implementation("com.codepath.libraries:asynchttpclient:2.2.0")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    implementation(libs.github.imageslideshow)

    // Testing

    implementation(libs.multidex)


}