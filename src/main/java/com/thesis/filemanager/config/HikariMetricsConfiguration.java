package com.thesis.filemanager.config;

import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HikariMetricsConfiguration {

    @Bean
    public MeterBinder hikariCpMetrics(DataSource dataSource) {
        // This ensures we only apply this logic if the data source is a HikariDataSource.
        if (dataSource instanceof HikariDataSource hikariDataSource) {
            return registry -> {
                Gauge.builder("hikaricp.connections.active", hikariDataSource, ds -> ds.getHikariPoolMXBean().getActiveConnections())
                        .description("Total active connections")
                        .register(registry);
                Gauge.builder("hikaricp.connections.idle", hikariDataSource, ds -> ds.getHikariPoolMXBean().getIdleConnections())
                        .description("Total idle connections")
                        .register(registry);
                Gauge.builder("hikaricp.connections.pending", hikariDataSource, ds -> ds.getHikariPoolMXBean().getThreadsAwaitingConnection())
                        .description("Total pending connections")
                        .register(registry);
                Gauge.builder("hikaricp.connections.total", hikariDataSource, ds -> ds.getHikariPoolMXBean().getTotalConnections())
                        .description("Total connections")
                        .register(registry);
            };
        }
        // If not a HikariDataSource, return an empty binder.
        return registry -> {};
    }
}