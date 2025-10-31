package code.SocialMediaAPI.adminmodule.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import code.SocialMediaAPI.adminmodule.dto.AdminLoginRequest;
import code.SocialMediaAPI.adminmodule.dto.AdminLoginResponse;
import code.SocialMediaAPI.adminmodule.model.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Admin toEntity(AdminLoginRequest adminLoginRequest);

    @Mapping(target = "token", ignore = true)
    AdminLoginResponse toModel(Admin admin);
}
