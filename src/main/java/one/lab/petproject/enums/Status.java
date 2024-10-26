package one.lab.petproject.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVATED("ACTIVATED"),
    DEACTIVATED("DEACTIVATED");
    private String status;
    Status(String status) {
        this.status = status;
    }
}
