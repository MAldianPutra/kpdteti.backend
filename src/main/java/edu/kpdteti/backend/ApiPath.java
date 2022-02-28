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
    public static final String ADMIN_AUTHOR_POPULATE = ADMIN_AUTHOR + "/populate";
    public static final String ADMIN_TOPIC = ADMIN + "/topics";
    public static final String ADMIN_TOPIC_POPULATE = ADMIN_TOPIC + "/populate";
    public static final String ADMIN_PUBLICATION = ADMIN + "/publications";
    public static final String ADMIN_POST_PUBLICATION = ADMIN_PUBLICATION + "/post";
    public static final String ADMIN_UPLOAD_PUBLICATION = ADMIN_PUBLICATION + "/upload";
    public static final String ADMIN_PUBLICATION_POPULATE = ADMIN_PUBLICATION + "/populate";

    // USER ENDPOINTS
    public static final String USER = API + "/users";

    // TOPIC ENDPOINTS
    public static final String TOPIC = API + "/topics";
    public static final String TOPIC_ALL = TOPIC + "/all";
    public static final String TOPIC_BY_TOPIC_PARENT = TOPIC + "/parents";
    public static final String TOPIC_PUBLICATIONS = TOPIC + "/publications";

    // AUTHOR ENDPOINTS
    public static final String AUTHOR = API + "/authors";
    public static final String ALL_AUTHOR = AUTHOR + "/all";
    public static final String ALL_AUTHOR_NAME = AUTHOR + "/allName";
    public static final String AUTHOR_PUBLICATIONS = AUTHOR + "/publications";

    // PUBLICATION ENDPOINTS
    public static final String PUBLICATION = API + "/publications";
    public static final String ALL_PUBLICATION = PUBLICATION + "/all";
    public static final String SEARCH_PUBLICATION = PUBLICATION + "/search";
    public static final String PUBLICATION_UPLOAD = PUBLICATION + "/upload";

    // DOWNLOAD ENDPOINTS
    public static final String PUBLICATION_DOWNLOAD = API + "/download";

    // CLASSIFICATION ENDPOINTS
    public static final String CLASSIFICATION = API + "/classifications";

}
