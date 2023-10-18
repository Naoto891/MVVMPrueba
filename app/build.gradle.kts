plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}


android {
    namespace = "com.example.mvvmprueba"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmprueba"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //Dependencias adicionales
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")
    implementation("androidx.emoji2:emoji2:1.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.postgresql:postgresql:42.2.4")
    implementation ("org.jetbrains.exposed:exposed-core:0.43.0")
    implementation ("org.jetbrains.exposed:exposed-dao:0.43.0")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.43.0")
    implementation ("org.jetbrains.exposed:exposed-java-time:0.43.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation ("com.zaxxer:HikariCP:5.0.1")  // Asegúrate de usar la última versión disponible
    //
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")
    //
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}