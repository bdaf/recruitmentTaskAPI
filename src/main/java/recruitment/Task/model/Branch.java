package recruitment.Task.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Branch {
    private String name;
    private Commit commit;
}
