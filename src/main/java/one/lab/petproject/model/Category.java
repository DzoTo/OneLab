package one.lab.petproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Category {
    private long id;
    private String name;
    private Users user;
    private List<Transactions> transactions;

}
