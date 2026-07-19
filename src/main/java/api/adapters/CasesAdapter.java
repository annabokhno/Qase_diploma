package api.adapters;

import api.models.cases.CaseRq;
import api.models.cases.CaseRs;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;

public class CasesAdapter {

    public static CaseRs createCase(String code, CaseRq rq) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .body(rq)
                .log().all()
                .when()
                .post("/case/{code}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    public static CaseRs getCase(String code, int id) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .get("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    public static void deleteCase(String code, int id) {
        given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .delete("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}

