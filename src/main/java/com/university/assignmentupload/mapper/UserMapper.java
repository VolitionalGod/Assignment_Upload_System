package com.university.assignmentupload.mapper;

import com.university.assignmentupload.dto.UserDto;
import com.university.assignmentupload.dto.UserPostDto;
import com.university.assignmentupload.model.Role;
import com.university.assignmentupload.model.User;
import org.mapstruct.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Named("roleIdToRoleList")
    static List<Role> roleIdToRoleList(String roldId) {
        Role role = new Role();
        role.setRoleId(roldId);
        return  List.of(role);
    }

    @Named("roleListToRoleId")
    static String roleListToRoleId(Collection<Role> roles) {
        return roles.stream().findFirst().orElse(new Role()).getRoleId();
    }




    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "enabled", source = "enabled")
    @Mapping(target = "roles", source = "roleId", qualifiedByName = "roleIdToRoleList")
    User userPostDtoToUser(UserPostDto userPostDto);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "enabled", source = "enabled")
    @Mapping(target = "roleId", source = "roles", qualifiedByName = "roleListToRoleId")
    UserDto userToUserDto(User user);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "enabled", source = "enabled")
    @Mapping(target = "roles", source = "roleId", qualifiedByName = "roleIdToRoleList")
    User userDtoToUser(UserDto UserDto);

    List<UserDto> usersListToUserDtoList(List<User> usersList);

}


@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@interface RoleListToRoleId {
}

@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@interface RoleIdToRoleList {
}