package one.lab.petproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IncomeOutcome {
    private double income;
    private double outcome;
}
