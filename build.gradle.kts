plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
    kotlin("jvm") version "1.8.21"
}

group = "com.unistream.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.3"
val assertJVersion = "3.26.3"
val allureVersion = "2.29.1"
val aspectJVersion = "1.9.22.1"
val lombokVersion = "1.18.32"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

dependencies {
    // JUnit 5 - core test framework
    implementation(platform("org.junit:junit-bom:$junitVersion"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.junit.platform:junit-platform-launcher")
    // Assertions library
    implementation("org.assertj:assertj-core:$assertJVersion")
    // Allure support for JUnit 5
    implementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    implementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-assertj")
    implementation("io.qameta.allure:allure-rest-assured")
    implementation("org.aspectj:aspectjweaver:${aspectJVersion}")
    // API testing core library
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    // Lombok - no boilerplate code
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    implementation(kotlin("stdlib"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    jvmArgs = listOf(
        "-Dfile.encoding=UTF-8"
    )

    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}