package recruitment.Task.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.reactive.function.client.WebClient;
import recruitment.Task.model.Branch;
import recruitment.Task.model.SingleRepository;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubAPIService {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    private final WebClient localApiClient;

    @Autowired
    public GitHubAPIService(WebClient aLocalApiClient) {
        localApiClient = aLocalApiClient;
    }

    public List<SingleRepository> getRepositoriesBasedOnUsername(String aUsername) {
        return localApiClient
                .get()
                .uri("/users/" + aUsername + "/repos")
                .retrieve()
                .bodyToFlux(SingleRepository.class).collect(Collectors.toList())
                .block(REQUEST_TIMEOUT);
    }

    public List<Branch> getRepositoryBranchesWithNameAndCommitSha(SingleRepository aSingleRepository) {
        List<Branch> branches = localApiClient
                .get()
                .uri(aSingleRepository.getUrlToBranches())
                .retrieve()
                .bodyToFlux(Branch.class).collect(Collectors.toList())
                .block(REQUEST_TIMEOUT);
        return branches;
    }
}
