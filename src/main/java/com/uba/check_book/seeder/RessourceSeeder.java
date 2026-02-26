package com.uba.check_book.seeder;

import com.uba.check_book.entity.Resource;
import com.uba.check_book.repository.ResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
public class RessourceSeeder implements CommandLineRunner {
    private final ResourceRepository resourceRepository;

    public  RessourceSeeder(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }

    @Override
    public  void run(String... args) throws Exception{
        if (resourceRepository.count() > 0) {
            return;
        }
        List<Resource> resources = List.of(
                createResource("user"),
                createResource("resource"),
                createResource("role"),
                createResource("transaction"),
                createResource("checkbook")
        );
        resourceRepository.saveAll(resources);
        System.out.println("Resources seeded successfully ✅");
    }

    private Resource createResource(String name) {
        Resource resource = new Resource();
        resource.setName(name);
        return resource;
    }
}
