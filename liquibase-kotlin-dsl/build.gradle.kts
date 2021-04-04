plugins {
    publish
}

dependencies {
    api(project(":script-definition"))
    implementation("org.liquibase:liquibase-core:4.3.1")
}