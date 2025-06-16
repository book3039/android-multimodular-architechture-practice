import builds.BuildConfig
import builds.BuildCreator
import builds.BuildDimension
import deps.DependenciesVersion
import deps.androidx
import deps.hilt
import deps.loginModule
import deps.okHttp
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import flavors.BuildFlavor
import plugs.BuildPlugins
import release.ReleaseConfig
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

plugins {
  id(plugs.BuildPlugins.ANDROID_APPLICATION)
  id(plugs.BuildPlugins.KOTLIN_ANDROID)
  id(plugs.BuildPlugins.ANDROID)
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.KTLINT)
  id(plugs.BuildPlugins.SPOTLESS)
  id(plugs.BuildPlugins.DETEKT)
  id(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)
  id(plugs.BuildPlugins.DOKKA)
}

android {
  namespace = BuildConfig.APP_ID
  compileSdk = BuildConfig.COMPILE_SDK_VERSION

  defaultConfig {
    applicationId = BuildConfig.APP_ID
    minSdk = BuildConfig.MIN_SDK_VERSION
    targetSdk = BuildConfig.TARGET_SDK_VERSION
    versionCode = ReleaseConfig.VERSION_CODE
    versionName = ReleaseConfig.VERSION_NAME

    testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
  }

  signingConfigs {
    BuildSigning.Release(project).create(this)
    BuildSigning.ReleaseExternalQA(project).create(this)
    BuildSigning.Debug(project).create(this)
  }

  buildTypes {
    BuildCreator.Release(project).create(this).apply {
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
    }

    BuildCreator.Debug(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.DEBUG)
    }

    BuildCreator.ReleaseExternalQA(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
    }
  }

  flavorDimensions.add(BuildDimension.APP)
  flavorDimensions.add(BuildDimension.STORE)

  productFlavors {
    BuildFlavor.Google.create(this)
    BuildFlavor.Huawei.create(this)

    BuildFlavor.Client.create(this)
    BuildFlavor.Driver.create(this)
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  composeOptions {
    kotlinCompilerExtensionVersion = DependenciesVersion.KOTLIN_COMPILER
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }
}

dependencies {
  loginModule()
  androidx()
  hilt()
  room()
  okHttp()
  retrofit()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
