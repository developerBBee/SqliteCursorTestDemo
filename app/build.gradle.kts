plugins {
    id("com.android.application")
}

android {
    namespace = "jp.developer.bbee.sqlitecursortestdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "jp.developer.bbee.sqlitecursortestdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.robolectric:robolectric:4.9")
    testImplementation("org.mockito:mockito-core:5.6.0")
    testImplementation("com.google.truth:truth:1.1.5")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("android.arch.core:core-testing:1.1.1")
    androidTestImplementation("org.mockito:mockito-core:5.6.0")
    androidTestImplementation("com.google.truth:truth:1.1.5")

}