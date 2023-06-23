package com.apys.learning.greet;

import com.apys.learning.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good morning " + userName;
    }
}
