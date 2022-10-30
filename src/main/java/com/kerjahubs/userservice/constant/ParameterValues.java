package com.kerjahubs.userservice.constant;

public class ParameterValues {
    public static final String preferences = "PREFERENCES";
    public static final String sectors = "SECTORS";
    public static final String banners = "BANNERS";

    public class file{
        public class type{
            public static final String image = "IMAGE";
            public static final String document = "DOCUMENT";
            public static final String video = "VIDEO";

            public class group {
                public static final String banners = "BANNERS";
                public static final String classes = "CLASS";
                public static final String companies = "COMPANY";
                public static final String trainers = "TRAINER";
                public static final String cv = "CV";
                public static final String modul = "MODUL";
                public static final String users = "USER";
            }
        }

        public class path{
            public class image{
                public static final String banners = "images/banner/";
                public static final String classes = "images/class/";
                public static final String companies = "images/company/";
                public static final String trainers = "images/trainer/";
                public static final String users = "images/user/";
            }

            public class document{
                public static final String cv = "documents/cv/";
                public static final String modul = "documents/modul/";
            }

            public class video{
                public static final String course = "courses/";
            }
        }

        public class url{
            public class image{
                public static final String banners = "https://storage.kerjahubs.com/images/banner/";
                public static final String classes = "https://storage.kerjahubs.com/images/class/";
                public static final String companies = "https://storage.kerjahubs.com/images/company/";
                public static final String trainers = "https://storage.kerjahubs.com/images/trainer/";
                public static final String users = "https://storage.kerjahubs.com/images/user/";
            }

            public class document{
                public static final String cv = "https://storage.kerjahubs.com/documents/cv/";
                public static final String modul = "https://storage.kerjahubs.com/documents/modul/";
            }

            public class video{
                public static final String course = "https://storage.kerjahubs.com/courses/";
            }
        }
    }
}
