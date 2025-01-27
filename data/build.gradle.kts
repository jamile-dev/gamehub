plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kt.lint)
}

android {
    namespace = "dev.jamile.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        val apiKey = System.getenv("API_KEY")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
    sourceSets.getByName("main") {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }

    sourceSets.getByName("test") {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }

    ktlint {
        android.set(true)
        outputToConsole.set(true)
        coloredOutput.set(true)
        ignoreFailures.set(false)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    dependencies {
        implementation(project(":domain"))
        implementation(project(":testsupport"))

        implementation(libs.androidx.appcompat)
        implementation(libs.hilt.android)
        implementation(libs.retrofit)
        implementation(libs.okhttp.logging.interceptor)
        implementation(libs.retrofit.converter.gson)
        implementation(libs.paging)
        implementation(libs.pagingCompose)
        implementation(libs.androidx.room.ktx)
        implementation(libs.androidx.room.runtime)
        implementation(libs.androidx.monitor)
        implementation(libs.androidx.junit.ktx)

        ksp(libs.androidx.room.compiler)
        ksp(libs.hilt.compiler)

        androidTestImplementation(kotlin("test"))

        testImplementation(kotlin("test"))
        testImplementation(libs.junit)
        testImplementation(libs.mockk)
        testImplementation(libs.kotlin.coroutines.test)
        testImplementation(libs.androidx.core.testing)
        testImplementation(libs.androidx.room.testing)

        androidTestImplementation(libs.junit)
        androidTestImplementation(libs.androidx.runner)
        androidTestImplementation(libs.androidx.rules)
        androidTestImplementation(libs.androidx.room.testing)
        androidTestImplementation(libs.androidx.core.testing)
        androidTestImplementation(libs.kotlin.coroutines.test)
        androidTestImplementation(libs.mockk)
    }
}
