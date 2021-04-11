import org.gradle.api.publish.maven.internal.publication.DefaultMavenPom

plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
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

tasks.register<Jar>("javadocJar") {
    group = "documentation"
    from(tasks["dokkaJavadoc"])
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
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

signing {
    val signingKey = project.findProperty("signingKey") as? String ?: System.getenv("SIGNING_KEY")
    val signingPassword = project.findProperty("signingPassword") as? String ?: System.getenv("SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["maven"])
}
