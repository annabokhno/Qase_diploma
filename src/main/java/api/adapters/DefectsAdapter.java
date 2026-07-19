package api.adapters;

import api.models.defects.DefectRq;
import api.models.defects.DefectRs;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;

public class DefectsAdapter {

    public static DefectRs createDefect(String code, DefectRq rq) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .body(rq)
                .log().all()
                .when()
                .post("/defect/{code}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(DefectRs.class);
    }

    public static DefectRs getDefect(String code, int id) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .get("/defect/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(DefectRs.class);
    }

    public static void deleteDefect(String code, int id) {
        given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .delete("/defect/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}