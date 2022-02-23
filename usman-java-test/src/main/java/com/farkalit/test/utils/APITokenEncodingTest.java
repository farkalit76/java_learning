/**
 * 
 */
package com.farkalit.test.utils;

import java.util.Base64;
import java.util.Objects;
import com.farkalit.test.models.UserInfo;


/**
 * To generate the User specific x-api-key for the authentication. It must be in the same token
 * format = id:channel:username:password
 * 
 * @author farkalitusman
 *
 */
public class APITokenEncodingTest {

  /**
   * 
   * @param originalString
   */
  public static String encodeWithPadding(String originalString) {
    // Encoding string using simple encode
    String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes());
    // System.out.println("Encoded string : " + encodedString);
    return encodedString;

  }

  private static String getDecodedValue(String xAuth) {
    if (Objects.nonNull(xAuth)) {
      return new String(Base64.getDecoder().decode(xAuth.getBytes()));
    }
    return null;
  }

  public static void main(String[] args) {
    // id:channel:username:password

    runDevelopment();
    System.out.println("\n");
    runProduction();


  }

  private static void runDevelopment() {

    String hybrisString = "101:hybris:hybris-dev:hybris@1234";

    String hybrisKey = encodeWithPadding(hybrisString);
    System.out.println("Dev-hybrisString apikey:" + hybrisKey);

    UserInfo hybrisUser = getUserInfoFromToken("Mah" + hybrisKey);
    System.out.println("hybrisUser:" + hybrisUser.toString());

    String cityplusString = "102:cityplus:cityplus-dev:cityplus@1234";

    String cityplusKey = encodeWithPadding(cityplusString);
    System.out.println("Dev-cityplusKey apikey:" + cityplusKey);

    UserInfo cityplusUser = getUserInfoFromToken("Mac" + cityplusKey);
    System.out.println("cityplusUser:" + cityplusUser.toString());

    String omscallString = "103:omscall:omscall-dev:omscall@1234";

    String omsKey = encodeWithPadding(omscallString);
    System.out.println("Dev-omsKey apikey:" + omsKey);

    UserInfo omsUser = getUserInfoFromToken("Mao" + omsKey);
    System.out.println("omsUser:" + omsUser.toString());

  }

  private static void runProduction() {

    String hybrisString = "101:hybris:hybris-prodcs:hybris@1234";

    String hybrisKey = encodeWithPadding(hybrisString);
    System.out.println("Prod-hybrisString apikey:" + hybrisKey);

    UserInfo hybrisUser = getUserInfoFromToken("Mah" + hybrisKey);
    System.out.println("hybrisUser:" + hybrisUser.toString());

    String cityplusString = "102:cityplus:cityplus-prodcs:cityplus@1234";

    String cityplusKey = encodeWithPadding(cityplusString);
    System.out.println("Prod-cityplusKey apikey:" + cityplusKey);

    UserInfo cityplusUser = getUserInfoFromToken("Mac" + cityplusKey);
    System.out.println("cityplusUser:" + cityplusUser.toString());

    String omscallString = "103:omscall:omscall-prodcs:omscall@1234";

    String omsKey = encodeWithPadding(omscallString);
    System.out.println("Prod-omsKey apikey:" + omsKey);

    UserInfo omsUser = getUserInfoFromToken("Mao" + omsKey);
    System.out.println("omsUser:" + omsUser.toString());
  }
  /**
   * 
   * @param xAuth
   * @return
   */
  public static UserInfo getUserInfoFromToken(String xAuth) {

    UserInfo user = new UserInfo();
    String decdString = getStringValue(xAuth);
    if (decdString == null) {
      return null;
    } else {
      String[] result = decdString.split(":");
      user.setUserId(Long.parseLong(result[0]));
      user.setChannel(result[1]);
      user.setUsername(result[2]);
      user.setPassword(result[3]);
      return user;
    }
  }

  /**
   * Decode the API key.
   * 
   * @param xAuth
   * @return
   */
  private static String getStringValue(String xAuth) {
    if (Objects.nonNull(xAuth)) {
      xAuth = xAuth.substring(3, xAuth.length() - 1);
      return new String(Base64.getDecoder().decode(xAuth.getBytes()));
    }
    return null;
  }
}
