package com.itlize.korera;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootTest
public class ResourceDetailTest {
    @Autowired
    private ResourceDetailRepository resourceDetailRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    // !! Make sure your resource table contains data!
    public void saveResourceDetail(){
        //get the user and resource
        User manager = this.userRepository.findByUsername("siqi@gamil.com");
        System.out.println(manager);
        Resource resource = this.resourceRepository.getResourceByResourceName("Masonry");
        //create a new detail
        ResourceDetail resourceDetail = new ResourceDetail();
        resourceDetail.setResource(resource);
        resourceDetail.setDetailName("quantity");
        resourceDetail.setdetailDescription("255");
        resourceDetail.setCreated_date(new Date());
        resourceDetail.setModifiedInformation(manager, new Date());
        //save the detail to resource
        try{
            Set<ResourceDetail> detailList = resource.getResourceDetails();
            detailList.add(resourceDetail);
            System.out.println("detailList: "+  detailList );
            detailList.add( resourceDetail );
            System.out.println("after add a resourcedetail detailList: "+  detailList );
            //update resource and save resourcedetail to DB
            this.resourceRepository.save(resource);
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
    User manager = this.userRepository.findByUsername("mark@gamil.com");
    ResourceDetail detail = this.resourceDetailRepository.findResourceDetailByDetailName("quantity");
    System.out.println(detail);
    detail.setdetailDescription("hello world!"); //update description

    this.resourceDetailRepository.save(detail);

    }

    @Test
    public void deleteResourceDetail(){
        ResourceDetail detail = this.resourceDetailRepository.findResourceDetailByDetailName("quantity");
        Resource resource = detail.getResource();
        System.out.println(resource.getResourceDetails());
        //remove the detail from its resource
        if(resource.getResourceDetails().remove(detail)){
            System.out.println(resource.getResourceDetails());
            this.resourceRepository.save(resource);//update resource
            this.resourceDetailRepository.delete(detail);//delete the detail

        }


    }
}
