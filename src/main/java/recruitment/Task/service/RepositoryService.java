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
        return fillBranchesDataInRepositories(notForkRepositories);
    }

    private List<SingleRepository> fillBranchesDataInRepositories(List<SingleRepository> aRepositories) {
        List<SingleRepository> resultRepositories = List.copyOf(aRepositories);
        aRepositories.forEach(SingleRepository::setUrlBranchBasedOnUrl);
        aRepositories.forEach(r -> r.setBranches(gitHubApiService.getRepositoryBranchesWithNameAndCommitSha(r)));
        return resultRepositories;
    }

    private List<SingleRepository> filterAndGetOnlyNotForks(List<SingleRepository> repositories) {
        return repositories.stream().filter(r -> !r.isFork()).toList();
    }
}
