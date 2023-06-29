package learning.greet;

import learning.net.Greetable;

public class EvenigGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good evening " + userName;
    }
}
