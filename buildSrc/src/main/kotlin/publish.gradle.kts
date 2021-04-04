import org.gradle.api.publish.maven.internal.publication.DefaultMavenPom

plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    val autoServiceVersion: String by project
    kapt("com.google.auto.service:auto-service:$autoServiceVersion")
    compileOnly("com.google.auto.service:auto-service-annotations:$autoServiceVersion")
}

tasks.register<Jar>("sourcesJar") {
    group = "documentation"
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            pom {
                name.set("liquibase-kotlin-dsl")
                description.set("kotlin dsl plugin for liquibase")
                url.set("https://github.com/F43nd1r/liquibase-kotlin-dsl")

                scm {
                    connection.set("scm:git:https://github.com/F43nd1r/liquibase-kotlin-dsl.git")
                    developerConnection.set("scm:git:git@github.com:F43nd1r/liquibase-kotlin-dsl.git")
                    url.set("https://github.com/F43nd1r/liquibase-kotlin-dsl.git")
                }

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("f43nd1r")
                        name.set("Lukas Morawietz")
                    }
                }
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/F43nd1r/liquibase-kotlin-dsl")
            credentials {
                username = project.findProperty("githubUser") as? String ?: ""
                password = project.findProperty("githubPackageKey") as? String ?: ""
            }
        }
    }
}
