package api.adapters;

import api.models.result.ResultRq;
import api.models.result.ResultRs;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;

public class ResultAdapter {

    public static ResultRs createResult(String code, ResultRq rq) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .body(rq)
                .log().all()
                .when()
                .post("/result/{code}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(ResultRs.class);
    }
}
