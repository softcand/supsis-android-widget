import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.supsis.supsis_android_preview"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xuse-ir"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}
afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.softcand"
                artifactId = "supsis-android-widget"
                version = "1.0.0"
                from(components["release"])
                pom {
                    name.set("Supsis Android Widget")
                    description.set("An Android Widget for Supsis")
                    url.set("https://github.com/softcand/supsis-android-widget")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("softcand")
                            name.set("Abdu Samed Akgul")
                            email.set("samedakgul99@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/softcand/supsis-android-widget.git")
                        developerConnection.set("scm:git:ssh://github.com/softcand/supsis-android-widget.git")
                        url.set("https://github.com/softcand/supsis-android-widget")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "Supsis Android Widget"
                url = uri("https://maven.pkg.github.com/softcand/supsis-android-widget")

                credentials {
                    val localProperties = Properties()
                    val localPropertiesFile = rootProject.file("local.properties")
                    if (localPropertiesFile.exists()) {
                        localProperties.load(localPropertiesFile.inputStream())
                    } else {
                        throw GradleException("local.properties file not found.")
                    }
                    username = localProperties.getProperty("GITHUB_USERNAME")
                    password = localProperties.getProperty("GITHUB_PASS")
                }
            }
        }
    }
}
dependencies {
    // AndroidX WebKit library
    implementation("androidx.webkit:webkit:1.6.0")
    // Gson library
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.compose.runtime:runtime:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
}