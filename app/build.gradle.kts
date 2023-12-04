plugins {
    id("com.android.application")
    id("kotlin-android")
}


android {
    compileSdk = 33

    defaultConfig {
        namespace = "com.bbva.ingenicodevlib"
        applicationId = "com.bbva.ingenicodevlib"
        minSdk = 24
        targetSdk = 33
        versionName = "1.0"
        versionCode = 1

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //@BBVA Libs
    implementation(project(mapOf("path" to ":DeviceLib")))
    implementation(project(mapOf("path" to ":IngenicoDevLib")))

}