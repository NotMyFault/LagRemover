import org.cadixdev.gradle.licenser.LicenseExtension
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("org.cadixdev.licenser") version "0.5.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
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
    maven {
        name = "bStats"
        url = uri("https://repo.codemc.org/repository/maven-public")
    }
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:1.8")
    implementation(kotlin("stdlib-jdk8"))
}

version = "1.0.2"

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set(null as String?)
    dependencies {
        include(dependency("org.bstats:bstats-bukkit:1.8"))
        relocate("org.bstats", "de.n0tmyfaultog.metrics")
    }
}

configure<LicenseExtension> {
    header = rootProject.file("LICENSE-HEADER")
    include("**/*.kt")
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

tasks.named("build").configure {
    dependsOn("shadowJar")
}