package bakend.user.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAutorityJsonCreator {
    @JsonCreator
    public SimpleGrantedAutorityJsonCreator(@JsonProperty("authority") String role){

    }
}
