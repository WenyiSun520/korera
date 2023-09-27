package com.itlize.korera;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ResourceTest {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addResource(){
        User manager = this.userRepository.findByUsername("winnieee.sun@gamil.com");
        Resource resource = new Resource((long)040000,"Masonry");
        resource.setLatest_modified_by(manager);
        resource.setCreated_date(new Date());
        resource.setLatest_modified_date(new Date());

        if(this.resourceRepository.existsResourceByResourceName("Masonry")) {
           System.out.println("The database has already contained the data: "+ resource );
        }else{
            this.resourceRepository.save(resource);
        }
    }

    @Test
    public void updateSubResourceList(){
        User manager = this.userRepository.findByUsername("siqi@gamil.com");
        List<Resource> subList = new ArrayList<>();
        Resource unitMasonry = new Resource((long)042000,"Unit Masonry");
        Resource stoneMasonry = new Resource((long)044300,"Stone Masonry");
        subList.add(unitMasonry);
        subList.add(stoneMasonry);

        Resource parentSource = this.resourceRepository.getResourceByResourceName("Masonry");
        parentSource.setLatest_modified_date(new Date());
        parentSource.setLatest_modified_by(manager);

        updateSubResourceHelper(subList, parentSource);
    }
    private void updateSubResourceHelper(List<Resource> subList, Resource parentSource){
       Set<Resource> subResourceSet =  parentSource.getSubResourceSet();
        for(Resource resource: subList){
            if(!subResourceSet.contains(resource)){
                try {
                    resource.setParentResource(parentSource);
                    subResourceSet.add(resource);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        //bug:
        // this.resourceRepository.updateResourceBySubResourceList(subResourceList);
        this.resourceRepository.save(parentSource);
    }
    @Test
    public void deleteSubResource(){
        Resource subSource = this.resourceRepository.getResourceByResourceName("Stone Masonry");
        Resource parentSource = subSource.getParentResource();
        Set<Resource> subSet = parentSource.getSubResourceSet();
        subSet.remove(subSource);
        this.resourceRepository.save(parentSource);
    }

    @Test
    public void deleteParentResource(){
        Resource parentSource = this.resourceRepository.getResourceByResourceName("Masonry");
        this.resourceRepository.delete(parentSource);

    }


}
