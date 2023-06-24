package com.project.usermanagement.dao.service;

import com.project.usermanagement.dao.entity.Right;
import com.project.usermanagement.dao.entity.User;
import com.project.usermanagement.dao.exception.CustomizedException;
import com.project.usermanagement.dao.exception.User.UserNotFoundException;
import com.project.usermanagement.dao.exception.UserManagementCodes;
import com.project.usermanagement.dao.repository.RightRepository;
import com.project.usermanagement.dao.repository.UserRepository;
import com.project.usermanagement.dto.PostUserDto;
import com.project.usermanagement.dto.PutUserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RightRepository rightRepository;

    public UserService(UserRepository userRepository, RightRepository rightRepository) {
        this.userRepository = userRepository;
        this.rightRepository = rightRepository;
    }

    /**
     * This methode allows to get all users.
     * @return list of users.
     */
    public List<User> getAllUsers(){
        return userRepository.findAllByIsEnabled(1);
    }

    /**
     * This methode allows to create a user.
     * @param createUserDto the information about the new user.
     * @return the user created.
     * @throws CustomizedException email already exist exception.
     */
    public User createUser(PostUserDto createUserDto) throws CustomizedException {

        List<User> users = userRepository.findAllByIsEnabled(1);
        boolean checkExistenceUser = users.stream().anyMatch(user -> user.getEmail().equals(createUserDto.getEmail()));
        if(checkExistenceUser){
            throw new CustomizedException("Email already exist");
        }
        List<Right> rights = rightRepository.findAll();
        List<Right> matchingValues = getMatchingIds(createUserDto.getRights(),rights);

            Optional<User> optionalUser;
            String ref;
            do {
                ref = RandomStringUtils.randomAlphanumeric(6).toUpperCase(Locale.ROOT);
                optionalUser= userRepository.findBySub(ref);
            } while (optionalUser.isPresent());
          return userRepository.save(User
                    .builder()
                    .sub(ref)
                    .email(createUserDto.getEmail())
                    .firstName(createUserDto.getFirstName())
                    .lastName(createUserDto.getLastName())
                    .rights(matchingValues)
                    .build());
        }

    /**
     *
     * @param userId the id of use.
     * @param updateUserDto the new information about the updated user.
     * @return updated user.
     * @throws UserNotFoundException user not found exception.
     */
    public User updateUser(Long userId, PutUserDto updateUserDto) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(UserManagementCodes.USER_NOT_FOUND));

        if(Objects.nonNull(updateUserDto.getRights())) {
            List<Right> rights = rightRepository.findAll();
            List<Right> matchingValues = getMatchingIds(updateUserDto.getRights(), rights);
            user.setRights(matchingValues);
        }
        user.setEmail(updateUserDto.getEmail());
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        return userRepository.save(user);
    }

    /**
     * This methode allows to delete specific user.
     * @param userId the id of user.
     * @return true if user has been deleted.
     * @throws UserNotFoundException user not found exception.
     */
    public boolean deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(UserManagementCodes.USER_NOT_FOUND));
        userRepository.delete(user);
        return true;
    }

    /**
     * Ths methode allows to get list of Ids that have right matching the given IDs.
     * @param ids list of right ids was sent.
     * @param rights list rights db.
     * @return list of ids rights.
     */
    public static List<Right> getMatchingIds(List<Long> ids, List<Right> rights) {
        if(rights.isEmpty()){
            return null;
        }
        List<Right> matchingObjects = new ArrayList<>();

        for (Long value : ids) {
            for (Right right : rights) {
                if (Objects.equals(right.getId(), value)) {
                    matchingObjects.add(right);
                    break;
                }
            }
        }

        return matchingObjects;
    }

}
