package com.itlize.korera.Service;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
@Service
public class ResourceDetailServiceImpl implements ResourceDetailService{
    private final ResourceRepository resourceRepository;
    private final ResourceDetailRepository resourceDetailRepository;
    private final UserRepository userRepository;


    @Autowired
    public ResourceDetailServiceImpl(ResourceRepository resourceRepository,
                                     ResourceDetailRepository resourceDetailRepository,
                                     UserRepository userRepository){
        this.resourceRepository = resourceRepository;
        this.resourceDetailRepository = resourceDetailRepository;
        this.userRepository  = userRepository;
    }

    @Override
    public Set<ResourceDetail> getResourceDetailsByResourceId(long resourceId) {
        Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
        return resource.getResourceDetails();
    }

    @Override
    public Set<ResourceDetail> getResourceDetailsByResourceName(String resourceName) {
        Resource resource = this.resourceRepository.getResourceByResourceName(resourceName);
        return resource.getResourceDetails();
    }

    @Override
    public ResourceDetail getResourceDetailByResourceDetailName(String resourceDetailName) {
        ResourceDetail resourceDetail = this.resourceDetailRepository
                                        .findResourceDetailByDetailName(resourceDetailName);
        return resourceDetail;
    }

    @Override
    public Boolean addResourceDetail(ResourceDetail resourceDetail, String username, long resourceId) {
        try {

            User user = this.userRepository.findByUsername(username);

            resourceDetail.setCreated_date(new Date());
            resourceDetail.setLatest_modified_by(user);

            Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
            Set<ResourceDetail> resourceDetailSet = resource.getResourceDetails();
            resourceDetailSet.add(resourceDetail);
            resource.setLatest_modified_date(new Date());
            resource.setLatest_modified_by(user);
            this.resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println("Error when adding resourceDetail: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateResourceDetail(ResourceDetail resourceDetail, String username, long resourceId) {
        try {

            User user = this.userRepository.findByUsername(username);

            long resourceDetailID = resourceDetail.getResourceDetailID();

            resourceDetail.setCreated_date(new Date());
            resourceDetail.setLatest_modified_by(user);

            Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
            Set<ResourceDetail> resourceDetailSet = resource.getResourceDetails();

            Iterator<ResourceDetail> itr = resourceDetailSet.iterator();
            while(itr.hasNext()){
                ResourceDetail re = itr.next();
                if( resourceDetailID == re.getResourceDetailID()){
                    re.setLatest_modified_by(user);
                    re.setLatest_updated(new Date());
                    re.setDetailName(resourceDetail.getDetailName());
                    re.setdetailDescription(resourceDetail.getdetailDescription());
                    break;
                }
            }

            this.resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println("Error when updating resourceDetail: "+e);
            return false;
        }
        return true;
    }


    @Override
    public Boolean deleteResourceDetail(long resourceDetailId, long resourceId) {
        ResourceDetail resourceDetail = this.resourceDetailRepository.findResourceDetailByResourceDetailID(resourceDetailId);

        Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
        Set<ResourceDetail> resourceDetailSet = resource.getResourceDetails();
        try {
            resourceDetailSet.remove(resourceDetail);
        }catch(Exception e){
            System.out.println("Error when updating resourceDetail: "+e);
            return false;
        }
        return true;
    }
}
