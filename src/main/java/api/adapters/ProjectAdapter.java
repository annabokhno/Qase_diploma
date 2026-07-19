package api.adapters;

import api.models.project.ProjectRq;
import api.models.project.ProjectRs;
import api.models.project.ProjectsRs;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProjectAdapter {

    public static ProjectRs createProject(ProjectRq rq) {
        return given()
                .spec(spec)
                .body(rq)
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_project_schema.json"))
                .spec(ok200)
                .extract()
                .as(ProjectRs.class);
    }

    public static ProjectsRs getProjects() {
        return given()
                .spec(spec)
                .log().all()
                .when()
                .get("/project")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(ProjectsRs.class);
    }

    public static ProjectRs getProject(String code) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .log().all()
                .when()
                .get("/project/{code}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(ProjectRs.class);
    }

    public static ProjectRs updateProject(String code, ProjectRq rq) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .body(rq)
                .log().all()
                .when()
                .patch("/project/{code}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(ProjectRs.class);
    }

    public static void deleteProject(String code) {
        given()
                .spec(spec)
                .pathParams("code", code)
                .log().all()
                .when()
                .delete("/project/{code}")
                .then()
                .log().all()
                .spec(ok200);
    }


}

