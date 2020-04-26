package ru.relex.charity.services.constraint;

public final class ConstraintMessage {
    private ConstraintMessage() {

    }

    public static class Field {
        private Field() {}

        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String FIRST_NAME = "FIRSTNAME";
        public static final String LAST_NAME = "LASTNAME";
        public static final String EMAIL = "EMAIL";
        public static final String PERSONAL_INFO = "PERSONALINFO";
        public static final String NAME = "NAME";
        public static final String PARENT_ID = "PARENT_ID";
        public static final String TITLE = "TITLE";
        public static final String CATEGORY_ID = "CATEGORY_ID";
        public static final String AD_STATUS_ID = "AD_STATUS_ID";
    }

    public static class Constraint {
        private Constraint() {}

        public static final String IS_NULL = "_NULL";
        public static final String IS_EMPTY = "_EMPTY";
        public static final String TOO_LONG = "_TOOLONG";
        public static final String TOO_SHORT = "_TOOSHORT";
        public static final String DUPLICATE = "_DUPLICATE";
    }
}
