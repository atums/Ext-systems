package learning.greet;

import learning.net.Greetable;

public class DayGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good day " + userName;
    }
}
