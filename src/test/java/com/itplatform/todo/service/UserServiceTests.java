package com.itplatform.todo.service;

import com.itplatform.todo.domain.user.User;
import com.itplatform.todo.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class UserServiceTests {

    private final UserRepository mockUserRepository = mock(UserRepository.class);
    private final UserService userService = new UserServiceImpl(mockUserRepository);

    @Test
    @DisplayName("UserService.findByName으로 유저 객체를 반환 받는다.")
    public void findByNameTest() throws Exception {

        final String userName = "userName";
        User dummyUser = new User();
        dummyUser.setName(userName);
        when(mockUserRepository.findByName(userName)).thenReturn(Optional.of(dummyUser));

        Assertions.assertEquals(userName, userService.findByName(userName).getName());
    }

    @Test
    @DisplayName("UserService.findByName으로 유저를 못찾는 경우 Error가 발생 한다.")
    public void findByNameExceptionTest() {

        final String userName = "Unknown User";
        when(mockUserRepository.findByName(userName)).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> {
            try {
                userService.findByName(userName);
            } catch (Exception e) {
                Assertions.assertEquals("존재하지 않는 사용자 입니다.", e.getMessage());
                throw e;
            }
        });
    }

    @Test
    @DisplayName("UserService.toggleUserRole으로 유저의 일반 / 관리자을 전환한다.")
    public void toggleUserRoleTest() throws Exception {
        User dummyUser = new User();
        dummyUser.setId(1L);
        dummyUser.setAdmin(true); // 관리자
        when(mockUserRepository.findById(dummyUser.getId())).thenReturn(Optional.of(dummyUser));
        when(mockUserRepository.save(dummyUser)).thenReturn(dummyUser);

        // 관리자 -> 일반
        User standardUser = userService.toggleUserRole(dummyUser.getId().toString());
        // 유저는 일반 유저 이다.
        Assertions.assertFalse(standardUser.isAdmin());

        // 일반 -> 관리자
        User adminUser = userService.toggleUserRole(dummyUser.getId().toString());
        // 유저는 관리자 이다.
        Assertions.assertTrue(adminUser.isAdmin());
    }

    @Test
    @DisplayName("UserService.toggleUserRole 에서 에러가 발생 하는지 테스트 한다.")
    public void toggleUserRoleExceptionTest() {
        final long userId = 1L;
        when(mockUserRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> {
            try {
                userService.toggleUserRole(String.valueOf(userId));
            } catch (Exception e) {
                Assertions.assertEquals("존재하지 않는 사용자 입니다.", e.getMessage());
                throw e;
            }
        });
    }

    @Test
    @DisplayName("UserService.findByDel 에서 true/false의 삭제 상태에 따라서 데이터를 검색한다.")
    public void findByDelTest() {
        final User dummyUser1 = new User();
        final User dummyUser2 = new User();
        when(mockUserRepository.findByDel(true)).thenReturn(List.of(dummyUser1, dummyUser2));
        final User dummyUser3 = new User();
        when(mockUserRepository.findByDel(false)).thenReturn(List.of(dummyUser3));

        Assertions.assertEquals(2, userService.findByDel(true).size());
        Assertions.assertEquals(1, userService.findByDel(false).size());

    }

    @Test
    @DisplayName("UserService.save 에서 유저 정보를 저장 한다.")
    public void saveTest() {
        final User dummyUser = new User();
        dummyUser.setName("dummyUser");
        when(mockUserRepository.save(dummyUser)).thenReturn(dummyUser);

        Assertions.assertEquals(dummyUser.getName(), userService.save(dummyUser).getName());

    }
}
