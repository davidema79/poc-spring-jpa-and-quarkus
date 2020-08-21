package eu.davidemartorana.cloud.gcp.quarkus.poc.api;

import eu.davidemartorana.cloud.gcp.quarkus.poc.exceptions.NotFoundException;
import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities.Contributor;
import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.repos.ContributorsRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contributors")
public class ContributorsController {

    @Autowired
    private ContributorsRepository contributorsRepository;

    @GetMapping(path = {"/", ""})
    public List<Contributor> getContributors(@RequestParam("componentId") final Long componentId) {
        if(componentId == null) {
            return IterableUtils.toList(this.contributorsRepository.findAll());
        }

        return this.contributorsRepository.findAllByComponentId(componentId);
    }

    @GetMapping("/{id}")
    public Contributor getContributorById(@PathVariable("id") final long id) {
        return this.contributorsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The contributor with id '" + id + "' could not be found."));
    }

    @PostMapping(path = {"","/"})
    public Contributor addContributor(@RequestBody Contributor contributor){
        if(contributor.getId()!= null) {
            throw new IllegalArgumentException("A new contributor cannot have a contributor ID already set.");
        }

        return this.contributorsRepository.save(contributor);
    }

    @PutMapping("/{id}")
    public Contributor modifyContributor(@PathVariable("id") final long id, @RequestBody Contributor contributor){
        final Contributor oldContributor = this.contributorsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The contributor with id '" + id + "' could not be found."));

        if( contributor.getId() != null && contributor.getId() != id) {
            throw new IllegalArgumentException("The contributor ID cannot be changed.");
        }

        contributor.setId(id);

        return this.contributorsRepository.save(contributor);
    }


}
