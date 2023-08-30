package recruitment.Task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import recruitment.Task.model.SingleRepository;
import recruitment.Task.service.RepositoryService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController()
public class GitHubRepositoriesController {
    private final RepositoryService repositoryService;

    @Autowired
    public GitHubRepositoriesController(RepositoryService aRepositoryService) {
        repositoryService = aRepositoryService;
    }

    @GetMapping("github/repositories/{username}")
    public List<SingleRepository> getGitHubRepositoriesWithoutForks(@PathVariable("username") String aUsername){
        return repositoryService.getRepositoriesBasedOnUsername(aUsername);
    }
}
