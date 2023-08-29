package recruitment.Task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruitment.Task.model.SingleRepository;
import recruitment.Task.api.GitHubAPIService;

import java.util.List;

@Service
public class RepositoryService {

    private final GitHubAPIService gitHubApiService;

    @Autowired
    public RepositoryService(GitHubAPIService aGitHubAPIService) {
        gitHubApiService = aGitHubAPIService;
    }

    public List<SingleRepository> getRepositoriesBasedOnUsername(String aUsername) {
        List<SingleRepository> allUserRepositories = gitHubApiService.getRepositoriesBasedOnUsername(aUsername);
        List<SingleRepository> notForkRepositories = filterAndGetOnlyNotForks(allUserRepositories);
        List<SingleRepository> resultRepositories = fillBranchesDataInRepositories(notForkRepositories);
        return resultRepositories;
    }

    private List<SingleRepository> fillBranchesDataInRepositories(List<SingleRepository> aRepositories) {
        List<SingleRepository> resultRepositories = List.copyOf(aRepositories);
        aRepositories.forEach(r -> r.setUrlBranchBasedOnUrl());
        aRepositories.forEach(r -> r.setBranches(gitHubApiService.getRepositoryBranchesWithNameAndCommitSha(r)));
        return resultRepositories;
    }

    private List<SingleRepository> filterAndGetOnlyNotForks(List<SingleRepository> repositories) {
        return repositories.stream().filter(r -> !r.isFork()).toList();
    }
    /*
    return webClient
         .get()
         .uri(uriBuilder -> uriBuilder
             .path("/students/{0}")
             .build(id))
         .retrieve()
         .onStatus(HttpStatus::isError, resp -> resp.createException()
             .map(WebClientGraphqlException::new)
             .flatMap(Mono::error)
         ).bodyToFlux(Student.class).collect(Collectors.toList()); // This Line
     */
}
