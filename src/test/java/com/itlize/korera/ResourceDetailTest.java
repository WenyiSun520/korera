package com.itlize.korera;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ResourceDetailTest {
    @Autowired
    private ResourceDetailRepository resourceDetailRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Test
    // !! Make sure your resource table contains data!
    public void saveResourceDetail(){
        Resource resource = this.resourceRepository.getResourceByResourceName("Masonry");
        List<ResourceDetail> detailList = resource.getResourceDetails();
        ResourceDetail resourceDetail = new ResourceDetail();
        resourceDetail.setResource(resource);
        resourceDetail.setDetailName("description");
        resourceDetail.setDescription("Masonry is the craft of building a structure with brick, stone, or similar material");
        resourceDetail.setCreatedDate(new Date());
        resourceDetail.setLast_modified(new Date());

        try{
            detailList.add(resourceDetail);
            this.resourceRepository.save(resource);
            this.resourceDetailRepository.save(resourceDetail);

        }catch(Exception e){
            System.out.println("There is an error when saving resource detail: ");
            System.out.println(e);
        }
    }

    @Test
    public void getResourceDetailByResource(){
        Resource resource = this.resourceRepository.getResourceByResourceName("Masonry");
        Iterable<ResourceDetail> detailList = this.resourceDetailRepository.findResourceDetailsByResource(resource);
        detailList.forEach(System.out::println);
    }

    @Test
    public void updateResourceDetail(){
    ResourceDetail detail = this.resourceDetailRepository.findResourceDetailByDetailName("description");
    System.out.println(detail);
    detail.setDescription("hello world!"); //update description
    detail.setLast_modified(new Date()); //update modified_date
    this.resourceDetailRepository.save(detail);

    }

    @Test
    public void deleteResourceDetail(){
        ResourceDetail detail = this.resourceDetailRepository.findResourceDetailByDetailName("description");
        Resource resource = detail.getResource();
        System.out.println(resource.getResourceDetails());
        //remove the detail from its resource
        List<ResourceDetail> reource_detailList = resource.getResourceDetails()
                .stream().filter(de -> !(de.equals(detail)))
                .toList();
        resource.setResourceDetails(reource_detailList);
        System.out.println(reource_detailList);
        this.resourceRepository.save(resource);//update resource
        this.resourceDetailRepository.delete(detail);//delete the detail

    }
}
