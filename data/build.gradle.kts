dependencies {
    runtime("org.yaml:snakeyaml:1.24")
    runtime("org.liquibase:liquibase-core:3.8.0")
    runtime("org.postgresql:postgresql:42.2.11")

    runtime("com.zaxxer:HikariCP:3.4.2")

    runtime("org.springframework:spring-jdbc:5.2.4.RELEASE")

    implementation("org.springframework.boot:spring-boot-autoconfigure:2.2.5.RELEASE")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.1")
}