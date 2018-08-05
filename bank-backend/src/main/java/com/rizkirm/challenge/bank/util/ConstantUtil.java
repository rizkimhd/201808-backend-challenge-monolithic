package com.rizkirm.challenge.bank.util;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class ConstantUtil {

    public static final class PageParameter {
        public static final String LIST_DATA = "listData";
        public static final String TOTAL_PAGES = "totalPages";
        public static final String TOTAL_ELEMENTS = "totalElements";
    }

    public static final class ResponseMessage {
        public static final String DATA_NOT_FOUND = "DATA_NOT_FOUND";
        public static final String OK = "OK";
        public static final String BAD_REQUEST = "BAD_REQUEST";
        public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
        public static final String UNAUTHORIZED = "UNAUTHORIZED";
    }

    public static final class TransactionType {
        public static final String TRANSFER_DB = "TRANSFER";
        public static final String WITHDRAWAL_DB = "WITHDRAWAL";
        public static final String DEPOSIT_DB = "DEPOSIT";

        public static final String TRANSFER_FE = "Transfer";
        public static final String WITHDRAWAL_FE = "Withdrawal";
        public static final String DEPOSIT_FE = "Deposit";
    }

    public static final class Regex {
        public static final String EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    }
}