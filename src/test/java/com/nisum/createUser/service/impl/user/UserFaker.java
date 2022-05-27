package com.nisum.createUser.service.impl.user;

import com.nisum.createUser.models.Phone;
import com.nisum.createUser.models.User;

import java.util.Collections;

public class UserFaker {

    public static User userFaker() {
        return new User(
                "124",
                "Oscar Eduardo",
                "oscarmayor0211@gmail.com",
                "Omj*61193",
                Collections.singletonList(phoneFaker())
        );
    }

    public static Phone phoneFaker() {
        return new Phone("3182747662", "031", "+57");
    }



}
