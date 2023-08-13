plugins {
   id("com.android.application")
   id("org.jetbrains.kotlin.android")
   kotlin("kapt")
   id("com.google.dagger.hilt.android")
   id("com.google.devtools.ksp")

}

android {
   namespace = "com.example.animeapp"
   compileSdk = 33
   buildFeatures {
      viewBinding = true
   }
   defaultConfig {

      applicationId = "com.example.animeapp"
      minSdk = 24
      targetSdk = 33
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
   kotlinOptions {
      jvmTarget = "1.8"
   }
}

dependencies {


   val lifecycle_version = "2.6.1"
   annotationProcessor( "com.google.dagger:hilt-compiler:2.47")
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

//Hilt
   implementation("com.google.dagger:hilt-android:2.47")
  kapt("com.google.dagger:hilt-android-compiler:2.47")
   //navigation
   implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
   implementation("androidx.navigation:navigation-ui-ktx:2.6.0")



   val room_version = "2.5.2"

   implementation("androidx.room:room-runtime:$room_version")
   annotationProcessor("androidx.room:room-compiler:$room_version")

   // To use Kotlin annotation processing tool (kapt)
   kapt("androidx.room:room-compiler:$room_version")
   implementation ("androidx.room:room-ktx:2.5.2")
   // retrofit

   implementation("com.squareup.retrofit2:retrofit:2.9.0")

// GSON

   implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// coroutine

   implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

   implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

   //Glide

   implementation ("com.github.bumptech.glide:glide:4.15.1")

   implementation("androidx.core:core-ktx:1.10.1")
   implementation("androidx.appcompat:appcompat:1.6.1")
   implementation("com.google.android.material:material:1.9.0")
   implementation("androidx.constraintlayout:constraintlayout:2.1.4")
   testImplementation("junit:junit:4.13.2")
   androidTestImplementation("androidx.test.ext:junit:1.1.5")
   androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

   implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
   implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
   implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")

   implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
   implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
   implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")

}
kapt {
   correctErrorTypes = true
}