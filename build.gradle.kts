import net.minecrell.gradle.licenser.LicenseExtension
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("net.minecrell.licenser") version "0.4.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
    kotlin("jvm") version "1.4.21"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = sourceCompatibility
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    maven {
        name = "Paper"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    mavenCentral()
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}

version = "1.0.0"

configure<LicenseExtension> {
    header = rootProject.file("LICENSE-HEADER")
    include("**/*.java")
    newLine = false
}

bukkit {
    name = "LagRemover"
    main = "de.n0tmyfaultog.lagremover.LagRemoverPlugin"
    author = "NotMyFault"
    apiVersion = "1.13"
    description = "A solid plugin terminating your lag"
    version = rootProject.version.toString()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}