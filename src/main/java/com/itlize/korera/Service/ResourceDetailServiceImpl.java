package com.itlize.korera.Service;

import com.itlize.korera.DTO.ResourceDetailDTO;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        if(resource != null) {
            return resource.getResourceDetails();
        }else{
            throw new PathVariableNotFound("resource");
        }
    }


    @Override
    public  List<ResourceDetailDTO> getResourceDetailsByResourceName(String resourceName) {

       List<Resource> resourceList =  this.resourceRepository.getResourcesByResourceNameContainsIgnoreCase(resourceName);
        if(resourceList == null) {
            throw new PathVariableNotFound("resource");
        }
        List<ResourceDetailDTO> result  = new ArrayList<>();

        for(Resource resource: resourceList){
            Set<ResourceDetail> detailList = resource.getResourceDetails();
            for(ResourceDetail detail:detailList){
                ResourceDetailDTO resourceDetailDTO = new ResourceDetailDTO(detail.getResourceDetailID(),detail.getDetailName(),detail.getdetailDescription(),detail.getCreated_date(),detail.getLatest_updated(),detail.getLatest_modified_by().getUsername(), detail.getResource().getResourceID(),detail.getResource().getResourceName());
                result.add(resourceDetailDTO);
            }

        }
       return result;

    }
    public Set<String> getDistinctDetailTypes() {
        List<ResourceDetail> detailList = this.resourceDetailRepository.findAll();
        return detailList.stream()
                .map(ResourceDetail::getDetailName)
                .collect(Collectors.toSet());
    }
    @Override
    public List<ResourceDetailDTO> getResourceDetailsByResourceDetailName(String resourceDetailName) {
        List<ResourceDetail> list = this.resourceDetailRepository.findResourceDetailsByDetailName(resourceDetailName);
        List<ResourceDetailDTO> result = new ArrayList<>();
        for(ResourceDetail detail:list){
            ResourceDetailDTO resourceDetailDTO = new ResourceDetailDTO(detail.getResourceDetailID(),detail.getDetailName(),
                    detail.getdetailDescription(),detail.getCreated_date(),detail.getLatest_updated(),detail.getLatest_modified_by().getUsername(),
                    detail.getResource().getResourceID(), detail.getResource().getResourceName());
            result.add(resourceDetailDTO);

        }
        return result;
    }

    @Override
    public ResourceDetail getResourceDetailByResourceDetailName(String resourceDetailName) {
        ResourceDetail resourceDetail = this.resourceDetailRepository
                                        .findResourceDetailByDetailName(resourceDetailName);
        return resourceDetail;
    }

    @Override
    public Boolean addResourceDetail(ResourceDetail resourceDetail, String username, long resourceId) {

           if(!this.userRepository.existsUserByUsername(username)|| !this.resourceRepository.existsById(resourceId)){
               throw new PathVariableNotFound("username or resource");
           }
        try {
            User user = this.userRepository.findByUsername(username);
            Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);

            resourceDetail.setCreated_date(new Date());
            resourceDetail.setLatest_updated(new Date());
            resourceDetail.setLatest_modified_by(user);
            resourceDetail.setResource(resource);

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
            Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
            if(user == null || resource == null) throw new PathVariableNotFound("username or resource");

            long resourceDetailName = resourceDetail.getResourceDetailID();
            Set<ResourceDetail> resourceDetailSet = resource.getResourceDetails();

            Iterator<ResourceDetail> itr = resourceDetailSet.iterator();
            while(itr.hasNext()){
                ResourceDetail re = itr.next();
                if( resourceDetailName == re.getResourceDetailID()){
                    re.setLatest_modified_by(user);
                    re.setLatest_updated(new Date());
                    re.setDetailName(resourceDetail.getDetailName());
                    re.setdetailDescription(resourceDetail.getdetailDescription());
                    break;
                }
            }
        // System.out.println(resourceDetailSet);
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
        if(resourceDetail == null || resource == null) throw new PathVariableNotFound("resourceId or resourceDetailId");

        Set<ResourceDetail> resourceDetailSet = resource.getResourceDetails();
        try {
            resourceDetailSet.remove(resourceDetail);
            System.out.println(resourceDetailSet);
            this.resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println("Error when updating resourceDetail: "+e);
            return false;
        }
        return true;
    }
}
