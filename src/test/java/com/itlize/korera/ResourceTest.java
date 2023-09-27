package com.itlize.korera;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Repositories.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ResourceTest {
    @Autowired
    private ResourceRepository resourceRepository;
    @Test
    public void addResource(){
        Resource resource = new Resource((long)040000,"Masonry");
        if(this.resourceRepository.existsResourceByResourceName("Masonry")) {
           System.out.println("The database has already contained the data: "+ resource );
        }else{
            this.resourceRepository.save(resource);
        }
    }
    @Test
    public void updateSubResourceList(){
        List<Resource> subList = new ArrayList<>();
        Resource unitMasonry = new Resource((long)042000,"Unit Masonry");
        Resource stoneMasonry = new Resource((long)044300,"Stone Masonry");
        subList.add(unitMasonry);
        subList.add(stoneMasonry);

        Resource parentSource = this.resourceRepository.getResourceByResourceName("Masonry");

        updateSubResourceHelper(subList, parentSource);
    }
    private void updateSubResourceHelper(List<Resource> subList, Resource parentSource){
        List<Resource> subResourceList =  parentSource.getSubResourceList();
        for(Resource resource: subList){
            if(!subResourceList.contains(resource)){
                try {
                    resource.setParentResource(parentSource);
                    subResourceList.add(resource);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        this.resourceRepository.save( parentSource);
    }
    @Test
    public void deleteSubResource(){
        Resource parentSource = this.resourceRepository.getResourceByResourceName("Masonry");
        List<Resource> subList = parentSource.getSubResourceList();
        subList.remove(0);
        this.resourceRepository.save(parentSource);
    }

    @Test
    public void deleteParentResource(){
        Resource parentSource = this.resourceRepository.getResourceByResourceName("Masonry");
        this.resourceRepository.delete(parentSource);

    }


}
