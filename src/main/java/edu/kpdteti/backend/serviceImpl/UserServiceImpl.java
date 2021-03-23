package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.model.response.user.GetUserResponse;
import edu.kpdteti.backend.repository.UserRepository;
import edu.kpdteti.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResponse getUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        GetUserResponse response = new GetUserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
