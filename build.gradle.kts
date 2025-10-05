plugins {
    kotlin("jvm") version "2.2.20"
    id("application")
    id("com.gradleup.shadow") version "9.2.2"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "com.xyphias"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:6.18.1.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-moshi")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.http4k:http4k-template-handlebars")
    
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.0")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.strikt:strikt-core:0.35.1")
    testImplementation("org.http4k:http4k-testing-approval")
    testImplementation("org.http4k:http4k-testing-strikt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(24)
}

application {
    mainClass = "com.xyphias.gameoflife.MainKt"
}
