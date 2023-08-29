package recruitment.Task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "github/repositories/{username}", method = RequestMethod.GET, headers="Accept=application/json")
    public List<SingleRepository> getGitHubRepositoriesWithoutForks(@PathVariable("username") String aUsername){
        List<SingleRepository> repositories = repositoryService.getRepositoriesBasedOnUsername(aUsername);
        return repositories;
    }

    @RequestMapping(value = "github/repositories/", method = RequestMethod.GET, headers="Accept=application/json")
    public String getTest(){
        return "lol";
    }
}
