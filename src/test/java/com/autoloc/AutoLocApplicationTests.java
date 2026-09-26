package com.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:autoloc-test;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class AutoLocApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextStartsAndJpaCreatesAllDomainTables() {
        Set<String> tables = jdbcTemplate.queryForList(
                        "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
                        String.class)
                .stream()
                .map(name -> name.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());

        assertThat(tables).contains(
                "agence", "vehicule", "equipement", "client", "employe",
                "reservation", "contrat", "paiement", "maintenance",
                "vehicule_equipement");
    }
}
