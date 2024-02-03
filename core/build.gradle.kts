plugins{
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android{
    namespace = "com.moemen.core"
}

dependencies {

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
}