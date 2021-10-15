package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.model.response.user.GetUserResponse;
import edu.kpdteti.backend.repository.UserRepository;
import edu.kpdteti.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResponse getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new EntityNotFoundException("User not found with id " + userId);
        }
        GetUserResponse response = new GetUserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
