package code.SocialMediaAPI.usermodule.mapper;

import code.SocialMediaAPI.usermodule.dto.*;
import code.SocialMediaAPI.usermodel.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// User Mapper interface to map the UserRegisterRequest to User
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toModel(UserRegisterRequest userRegisterRequest);

    @Mapping(target = "token", ignore = true)
    UserRegisterResponse toResponse(User user);

    @Mapping(target = "token", ignore = true)
    UserLoginResponse toLoginResponse(User user);

}
