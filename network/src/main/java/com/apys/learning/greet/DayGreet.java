package com.apys.learning.greet;

import com.apys.learning.net.Greetable;

public class DayGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good day " + userName;
    }
}
