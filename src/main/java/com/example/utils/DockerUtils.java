package com.example.utils;

import org.springframework.util.ObjectUtils;
public abstract class DockerUtils {
    /** Environment param keys */
    private static final String ENV_KEY_HOST = "JPAAS_HOST";
    private static final String ENV_KEY_PORT = "JPAAS_HTTP_PORT";
    private static final String ENV_KEY_PORT_ORIGINAL = "JPAAS_HOST_PORT_8080";

    /** Docker host & port */
    private static String DOCKER_HOST = "";
    private static String DOCKER_PORT = "";

    /** Whether is docker */
    private static boolean IS_DOCKER;

    static {
        retrieveFromEnv();
    }

    /**
     * Retrieve docker host
     */
    public static String getDockerHost() {
        return DOCKER_HOST;
    }

    /**
     * Retrieve docker port
     */
    public static String getDockerPort() {
        return DOCKER_PORT;
    }

    /**
     * Whether a docker
     */
    public static boolean isDocker() {
        return IS_DOCKER;
    }


    private static void retrieveFromEnv() {
        // retrieve host & port from environment
        DOCKER_HOST = System.getenv(ENV_KEY_HOST);
        DOCKER_PORT = System.getenv(ENV_KEY_PORT);

        // not found from 'JPAAS_HTTP_PORT', then try to find from 'JPAAS_HOST_PORT_8080'
        if (ObjectUtils.isEmpty(DOCKER_PORT)) {
            DOCKER_PORT = System.getenv(ENV_KEY_PORT_ORIGINAL);
        }

        boolean hasEnvHost = ObjectUtils.isEmpty(DOCKER_HOST);
        boolean hasEnvPort = ObjectUtils.isEmpty(DOCKER_PORT);

        // docker can find both host & port from environment
        if (!hasEnvHost && !hasEnvPort) {
            IS_DOCKER = true;

            // found nothing means not a docker, maybe an actual machine
        } else if (hasEnvHost && hasEnvPort) {
            IS_DOCKER = false;

        } else {
            System.out.println("error");
        }
    }

}
