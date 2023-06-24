package com.project.usermanagement.dao.service;

import com.project.usermanagement.dao.entity.Right;
import com.project.usermanagement.dao.exception.CustomizedException;
import com.project.usermanagement.dao.exception.Right.RightNotFoundException;
import com.project.usermanagement.dao.exception.UserManagementCodes;
import com.project.usermanagement.dao.repository.RightRepository;
import com.project.usermanagement.dto.PostRightDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Slf4j
public class RightService {

    private final RightRepository rightRepository;

    public RightService(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }

    /**
     * This methode allows to get all rights.
     * @return list of rights.
     */
    public List<Right> getAllRights(){
        return rightRepository.findAllByIsEnabled(1);
    }

    /**
     * This methode allows to create a right.
     * @param createRightDto the information about the new right.
     * @return the right created.
     * @throws CustomizedException right already exist exception.
     */
    public Right createRight(PostRightDto createRightDto) throws CustomizedException {
        Right right = rightRepository.findByNameAndIsEnabled(createRightDto.getName(),1);
        if(Objects.nonNull(right)){
            throw new CustomizedException("Right already exist");
        }
        else {
            return rightRepository.save(Right
                    .builder()
                    .name(createRightDto.getName())
                    .label(createRightDto.getLabel())
                    .description(createRightDto.getDescription())
                    .build());
        }
    }


    /**
     * This methode allows to delete specific right.
     * @param rightId the id of right.
     * @return true if right has been deleted.
     * @throws RightNotFoundException right not found exception.
     */
    public boolean deleteRight(Long rightId) throws RightNotFoundException {
        Right right =rightRepository.findById(rightId)
                .orElseThrow(()->new RightNotFoundException(UserManagementCodes.RIGHT_NOT_FOUND));
        rightRepository.delete(right);
        return true;
    }
}
