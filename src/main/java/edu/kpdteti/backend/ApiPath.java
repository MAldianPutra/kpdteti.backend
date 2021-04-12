package edu.kpdteti.backend;

public class ApiPath {

    // FIXED VARIABLES
    private static final String API = "/api";
    private static final String EXCLUSION = API + "/exc";

    // AUTHENTICATION ENDPOINTS
    public static final String AUTH = API + "/auth";
    public static final String REGISTER = AUTH + "/user";
    public static final String REGISTER_ADMIN = AUTH + "/admin";
    public static final String LOGIN = AUTH + "/login";

    // ADMIN ENDPOINTS
    public static final String ADMIN = API + "/admin";
    public static final String ADMIN_AUTHOR = ADMIN + "/authors";
    public static final String ADMIN_TOPIC = ADMIN + "/topics";
    public static final String ADMIN_TOPIC_PARENT = ADMIN + "/topicParents";

    // USER ENDPOINTS
    public static final String USER = API + "/users";
    public static final String USER_UPDATE_PASSWORD = USER + "/update/password";

    // TOPIC PARENT ENDPOINTS
    public static final String TOPIC_PARENT = API + "/parents";

    // TOPIC ENDPOINTS
    public static final String TOPIC = API + "/topics";
    public static final String TOPIC_BY_TOPIC_PARENT = TOPIC + "/parents";
    public static final String TOPIC_PUBLICATIONS = TOPIC + "/publications";

    // AUTHOR ENDPOINTS
    public static final String AUTHOR = API + "/authors";
    public static final String AUTHOR_PUBLICATIONS = AUTHOR + "/publications";

    // PUBLICATION ENDPOINTS
    public static final String PUBLICATION = API + "/publications";
    public static final String PUBLICATION_DOWNLOAD = PUBLICATION + "/download";

}
