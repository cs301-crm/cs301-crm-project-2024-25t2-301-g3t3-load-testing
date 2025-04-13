package example.endpoints;

import static example.utils.Keys.*;
import static example.utils.Config.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.http.*;
import java.util.Optional;

public class APIendpoints {

  // Add authentication header if an access token exists in the session
//  public static final HttpProtocolBuilder withAuthenticationHeader(
//      HttpProtocolBuilder protocolBuilder) {
//    return protocolBuilder.header(
//        "Authorization", "Bearer #{JWT_TOKEN}"
//    );
//  }

  // Define login request
  public static final HttpRequestActionBuilder initialLogin =
      http("InitialLogin")
          .post("/auth/login")
          .header("Content-Type", "application/json")
          .body(
                  StringBody(
                          """
                          {
                              "email": "#{email}",
                              "password": "#{password}" 
                          }
                          """
                  )
          )
          .check(status().is(200));

  // Define get OTP request
  public static final HttpRequestActionBuilder getOtp=
          http("GetOtp")
                  .get("/auth/otp/#{email}")
                  .check(status().is(200))
                  .check(bodyString().saveAs("OTP"));

  // Define verify OTP Request
  public static final HttpRequestActionBuilder verifyOtp =
          http("VerifyOtp")
                  .post("/auth/verify-otp")
                  .header("Content-Type", "application/json")
                  .body(
                          StringBody(
                                  """
                                  {
                                      "email": "#{email}",
                                      "oneTimePassword": "#{OTP}" 
                                  }
                                  """
                          )
                  )
                  .check(status().is(200))
                  .check(jmesPath("message.jwt").saveAs("JWT_TOKEN"));

  // Define create client request
  public static final HttpRequestActionBuilder createClient =
          http("CreateClient")
                  .post("/clients")
                  .header("Content-Type", "application/json")
                  .header("Authorization", "Bearer #{JWT_TOKEN}")
                  .body(
                          StringBody(
                                  """
                                  {
                                      "firstName": "#{firstName}",
                                      "lastName": "#{lastName}",
                                      "dateOfBirth": "#{dateOfBirth}",
                                      "gender": "#{gender}",
                                      "emailAddress": "#{emailAddress}",
                                      "phoneNumber": "#{phoneNumber}",
                                      "address": "#{address}",
                                      "city": "#{city}",
                                      "state": "#{state}",
                                      "country": "#{country}",
                                      "postalCode": "#{postalCode}",
                                      "nric": "#{nric}",
                                      "agentId": "#{agentId}",
                                      "accounts": "#{accounts}" 
                                  }
                                  """
                          )
                  )
                  .check(status().is(201));