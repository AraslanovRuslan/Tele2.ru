package ru.tele2.tests.mobile.helpers;

import static ru.tele2.tests.mobile.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static ru.tele2.tests.mobile.helpers.CustomApiListener.withCustomTemplates;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic("asdasdqwdffsfdwe_FJixVj", "Lstx5wXmrYFxG5o5G46S")
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
