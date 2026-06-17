package fabioperettig.tt.model;

public enum Status {
    TODO("TO-DO"),
    IN_PROGRESS("IN-PROGRESS"),
    DONE("DONE");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
