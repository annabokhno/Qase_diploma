package ui.dto;

public class ProjectFactory {

    public static Project getProject() {

        long id = System.currentTimeMillis();

        return Project.builder()
                .name("Automation Project " + id)
                .code("A" + (id % 100000))
                .build();
    }
}