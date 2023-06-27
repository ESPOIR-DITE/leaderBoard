package org.example.config.streamEnv;


import io.github.cdimascio.dotenv.Dotenv;

public class EnvAppConfig implements EnvApp {
    private final Dotenv dotenv = Dotenv.load();

    @Override
    public String candidateTopic() {
        return dotenv.get("CANDIDATES_TOPIC");
    }

    @Override
    public String VoterTopic() {
        return dotenv.get("VOTERS_TOPIC");
    }

    @Override
    public String appIdConfig() {
        return dotenv.get("APPLICATION_ID_CONFIG");
    }

    @Override
    public String bootstrapServerHost() {
        return dotenv.get("BOOTSTRAP_SERVERS_HOST");
    }

    @Override
    public String bootstrapServerPort() {
        return dotenv.get("BOOTSTRAP_SERVERS_PORT");
    }

    @Override
    public String serdeKey() {
        return dotenv.get("SERDE_KEY");
    }

    @Override
    public String serdeValue() {
        return dotenv.get("SERDE_VALUE");
    }

    @Override
    public String registryHost() {
        return dotenv.get("SCHEMA_REGISTRY_HOST");
    }
    @Override
    public String registryPort() {
        return dotenv.get("SCHEMA_REGISTRY_PORT");
    }
}
