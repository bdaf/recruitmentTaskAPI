package recruitment.Task.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SingleRepository {
    public static final String BRANCHES_TO_URL_SUFFIX = "/branches";

    private String name;
    private Owner owner;
    private String urlToBranches;
    private boolean fork;
    private List<Branch> branches;

    public void setUrlBranchBasedOnUrl() {
        urlToBranches = "/repos/" + owner.getLogin() + "/" + name + BRANCHES_TO_URL_SUFFIX;
    }
}
