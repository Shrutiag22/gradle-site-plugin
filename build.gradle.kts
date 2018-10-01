import org.gradle.internal.impldep.org.junit.platform.launcher.EngineFilter.includeEngines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    groovy
    `java-gradle-plugin`
    kotlin("jvm").version("1.2.71")
}

apply(from = "$rootDir/gradle/build-scan.gradle")
apply(from = "$rootDir/gradle/functional-test.gradle")
apply(from = "$rootDir/gradle/publishing-portal.gradle")
apply(from = "$rootDir/gradle/ghpages-sample.gradle")

val kotlinVersion by extra { "1.2.71" }
val junitPlatformVersion by extra { "1.1.0" }
val spekVersion by extra { "2.0.0-alpha.1" }

group = "com.github.gradle-guides"
version = "0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
    maven(url = "https://dl.bintray.com/spekframework/spek-dev")
}

dependencies {
    compile("org.freemarker:freemarker:2.3.26-incubating")

    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    testImplementation(kotlin("test", kotlinVersion))
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testRuntimeOnly(kotlin("reflect", kotlinVersion))
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude(group = "org.junit.platform")
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
}

gradlePlugin {
    plugins {
        create("sitePlugin") {
            id = "com.github.gradle-guides.site"
            implementationClass = "org.gradle.plugins.site.SitePlugin"
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

// Configure test task to use Spek2 TestEngine
val test by tasks.getting(Test::class) {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}
