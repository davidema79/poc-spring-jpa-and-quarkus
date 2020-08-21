package eu.davidemartorana.cloud.gcp.quarkus.poc.api;

import eu.davidemartorana.cloud.gcp.quarkus.poc.exceptions.NotFoundException;
import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities.Version;
import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.repos.VersionsRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("versions")
public class VersionsController {

    @Autowired
    private VersionsRepository versionsRepository;

    @GetMapping(path = {"/", ""})
    public List<Version> getVersions(@RequestParam(value = "componentId", required = false) final Long componentId) {
        if(componentId != null) {
            return this.versionsRepository.findAllByComponentId(componentId);
        }

        return IterableUtils.toList(this.versionsRepository.findAll());
    }

    @GetMapping("/{id}")
    public Version getVersionById(@PathVariable("id") final long id) {
        return this.versionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The version with id '" + id + "' could not be found."));
    }

    @PostMapping(path = {"","/"})
    public Version addVersion(@RequestBody Version version){
        if(version.getId()!= null) {
            throw new IllegalArgumentException("A new version cannot have a version ID already set.");
        }

        return this.versionsRepository.save(version);
    }

    @PutMapping("/{id}")
    public Version modifyVersion(@PathVariable("id") final long id, @RequestBody Version version){
        this.versionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The version with id '" + id + "' could not be found."));

        if( version.getId() != id) {
            throw new IllegalArgumentException("The version ID cannot be changed.");
        }

        version.setId(id);

        return this.versionsRepository.save(version);
    }


}
