package org.example.config;

public interface AppProperties extends AppConfig{
    String appIdConfig();
    String bootstrapServerHost();
    String bootstrapServerPort();
    String serdeKey();
    String serdeValue();
    String registryHost();
    String registryPort();
}
