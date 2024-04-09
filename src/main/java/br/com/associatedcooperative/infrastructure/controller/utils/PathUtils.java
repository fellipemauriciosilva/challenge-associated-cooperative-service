package br.com.associatedcooperative.infrastructure.controller.utils;

public class PathUtils {

    public static final String API = "/api";
    public static final String VERSION_APP_V1 = "/v1";
    public static final String VERSION_APP_V2 = "/v2";
    public static final String CLIENT = API + VERSION_APP_V1 + "/client";

    public static class V1 {
        public static class Meeting {
            public static final String MEETING = CLIENT + "/meeting";
        }
        public static class Member {
            public static final String MEMBER = CLIENT + "/member";
        }
        public static class Ruling {
            public static final String RULING = CLIENT + "/ruling";
        }
        public static class Vote {
            public static final String VOTE = CLIENT + "/vote";
        }
    }

    public static class Meeting {
        public static final String FIND_ALL = "/findall";
        public static final String ID = "/{id}";
        public static final String FIND = "/find" + ID;
        public static final String CREATE = "/create";
        public static final String CLOSE = "/close" + ID;
    }

    public static class Member {
        public static final String CREATE = "/create";
        public static final String CPF = "/{cpf}";
        public static final String FIND = "/find" + CPF;
        public static final String STATUS = "/status" + CPF;
    }

    public static class Ruling {
        public static final String CREATE = "/create";
        public static final String ID = "/{id}";
        public static final String FIND = "/find" + ID;
        public static final String STATUS = "/status" + ID;
        public static final String RESULT = "/result" + ID;
        public static final String REPORT = "/report" + ID;
    }

    public static class Vote {
        public static final String CREATE = "/create";
    }

    public static class Placeholder {
        public static final String ID = "id";
        public static final String CPF = "cpf";
        public static final String STATUS = "status";
    }

    public static class V2 {
    }
}

