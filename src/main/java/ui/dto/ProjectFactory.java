package ui.dto;

public class ProjectFactory {

    public static Project getProject() {
        long id = System.currentTimeMillis();

        return new Project(
                "Automation Project " + id,
                "A" + (id % 100000)
        );
    }
}