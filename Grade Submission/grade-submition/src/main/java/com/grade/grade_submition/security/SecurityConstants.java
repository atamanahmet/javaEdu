package com.grade.grade_submition.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "bQeThWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)"; // Your
                                                                                                                // secret
                                                                                                                // should
                                                                                                                // always
                                                                                                                // be
                                                                                                                // strong
                                                                                                                // (uppercase,
                                                                                                                // lowercase,
                                                                                                                // numbers,
                                                                                                                // symbols)
                                                                                                                // so
                                                                                                                // that
                                                                                                                // nobody
                                                                                                                // can
                                                                                                                // potentially
                                                                                                                // decode
                                                                                                                // the
                                                                                                                // signature.

    public static final int TOKEN_EXPIRE_IN_MILLISECONDS = 7200000;
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
}
