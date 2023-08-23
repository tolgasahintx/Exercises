package com.crisp.DataFiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {

    private final static String user1 = "id=111;name=Jenn D.;dateOfBirth=1934-06-01;address=CA";
    private final static String user2 = "id=222;name=Jack;dateOfBirth=1956-05-01;address=MD";
    private final static String user3 = "id=333;name=Bernard;dateOfBirth=1966-04-01;address=CA State";
    private final static String user4 = "id=444;name=Ross C.;dateOfBirth=2023-08-22;address=Valley State";

public static List createUserData(){
    Map<String, String> userNo1 = stringToMap(user1);
    Map<String, String> userNo2 = stringToMap(user2);
    Map<String, String> userNo3 = stringToMap(user3);
    Map<String, String> userNo4 = stringToMap(user4);
    return Arrays.asList(userNo1, userNo2, userNo3, userNo4);
    }

public static Map<String, String> stringToMap(String user){
        Map<String, String> map = new HashMap<String, String>();    
        String[] elements = user.split(";");
        for (String parts : elements) {
            String[] keyValue = parts.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
        }
}
