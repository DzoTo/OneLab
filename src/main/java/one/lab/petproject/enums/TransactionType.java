package one.lab.petproject.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    INCOME("INCOME"),
    EXPENSE("EXPENSE");
    private String value;
    private TransactionType(String value) {
        this.value = value;
    }
}
